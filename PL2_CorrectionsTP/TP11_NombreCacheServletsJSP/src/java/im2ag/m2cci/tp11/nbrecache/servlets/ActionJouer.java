package im2ag.m2cci.tp11.nbrecache.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import im2ag.m2cci.tp11.nbrecache.model.NbreEssaisDepasseException;
import im2ag.m2cci.tp11.nbrecache.model.NombreInconnu;
import im2ag.m2cci.tp11.nbrecache.model.ValeurIncorrecteException;

/**
 * Servlet contrôleur du jeu nombre caché. Cette servlet est invoquée chaque
 * fois que l'utilisateur soumet une proposition. Cette proposition est
 * transmise comme paramètre de la requête HTTP soumise à cette servlet.
 * Celle-ci récupère ce paramètre et vérifie tout d'abord qu'il s'agit bien d'un
 * nombre entier. Dans ce cas ce nombre est comparé avec la valeur du nombre
 * inconnu stocké dans la session de l'utilisateur. En fonction du résultat de
 * cette comparaison (si elle a pu avoir lieu), un messageFinPartie indiquant la
 * position de la proposition est construit (TROP GRAND, TROP PETIT ou EGAL). Si
 * la comparaison n'a pu avoir lieu (proposition en dehors des bornes de
 * l'intervalle de valeurs ou nombre d'essais autorisé déjà atteint), un
 * messageFinPartie indiquant la raison de cet echec est construit.
 *
 * Le messageFinPartie est stocké comme attribut de la requête qui est alors
 * redirigée vers la page jsp pageJeu.jsp qui se charge de réafficher le
 * formulaire de jeu mis à jour (avec la valeur actualisée du nombre d'essais
 * restant et le messageFinPartie indiquant comment la proposition se situe par
 * rapport au nombre inconnu).
 *
 * @author Philippe Genoud (Philippe.Genoud@imag.fr)
 */
@WebServlet(name = "ControleurJeu", urlPatterns = {"/jouer"})
public class ActionJouer extends HttpServlet {

    private String creerMessageRejouer(int nbEssaisRestant) {
        return "Rejouez ! Il vous reste " + nbEssaisRestant + " essai"
                + ((nbEssaisRestant > 1) ? "s" : "");

    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        boolean partieTerminee = false;
        String message1;
        String message2
                ;
        String proposition = request.getParameter("proposition");
        HttpSession session = request.getSession(true);
        // récupère l'objet NombreInconnu stocké dans la session
        NombreInconnu valeurCachee
                = (NombreInconnu) session.getAttribute("nombreInconnu");
        
        // le code en commentaire ci-dessous a été déporté dans le filtre de
        // servlets (filtre1)
//        if (valeurCachee == null) {
//            // il n'y a pas de nombre inconnu dans la session. Par exemple
//            // parceque l'utilisateur a tappé directement l'url
//            // http://...../NombreCache/jouer dans son navigateur). 
//            // On redirige alors l'utilisateur vers la servlet qui
//            // initialise une partie.
//            request.getRequestDispatcher("/debuterPartie")
//                    .forward(request, response);
//            return;  // Attention de ne pas oublier ce return. Une fois la
//            // redirection effectuée cette servlet a fini son job !
//        }
        
        try {
            // récupère la paramètre "proposition" de la requête et le
            // converti en entier
            int valeurProposee = Integer.parseInt(proposition);

            // on compare la valeurProposee à la valeurCachée.
            int comparaison = valeurCachee.testerValeur(valeurProposee);

            // on construit un message correspondant au résultat de cette 
            // comparaison
            if (comparaison == 0) {
                message1 = "BRAVO !";
                message2 = String.format("Vous avez gagné en %d essais",
                        valeurCachee.getNbEssaisJoues());
                partieTerminee = true;
                request.setAttribute("styleFinPartie", "gagne");
            } else {
                if (valeurCachee.getNbEssaisRestant() == 0) {
                    message1 = "PERDU !";
                    message2 = String.format("Le nombre caché était %d ",
                            valeurCachee.getValeur());
                    partieTerminee = true;
                    request.setAttribute("styleFinPartie", "perdu");
                } else {
                    if (comparaison < 0) {
                        message1 = proposition + " est trop grand";
                    } else {
                        message1 = proposition + " est trop petit";
                    }
                    message2 = creerMessageRejouer(
                            valeurCachee.getNbEssaisRestant());
                }
            }
        } catch (NbreEssaisDepasseException ex) {
            // cette exception ne devrait jamais arriver
            message1 = "ERREUR !";
            message2 = "NOMBRE D'ESSAIS AUTORISE DEPASSE";
            partieTerminee = true;
        } catch (ValeurIncorrecteException ex) {
            message1 = "Valeur Incorrecte ! " + proposition
                    + " n'est pas dans l'intervalle de jeu";
            message2 = creerMessageRejouer(valeurCachee.getNbEssaisRestant());
        } catch (NumberFormatException ex) {
            message1 = "Valeur Incorrecte ! " + proposition
                    + " n'est pas un nombre";
            message2 = creerMessageRejouer(valeurCachee.getNbEssaisRestant());
        }

        // placement des messages dans la requête
        request.setAttribute("message1", message1);
        request.setAttribute("message2", message2);
        // redirection de la requête vers la page appropriée
        String urlForward;
        if (partieTerminee) {
            urlForward = "/WEB-INF/finPartie.jsp";
        } else {
            urlForward = "/WEB-INF/pageJeu.jsp";
        }
        request.getRequestDispatcher(urlForward).forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Contrôleur du jeu Nombre Caché";
    }// </editor-fold>
}
