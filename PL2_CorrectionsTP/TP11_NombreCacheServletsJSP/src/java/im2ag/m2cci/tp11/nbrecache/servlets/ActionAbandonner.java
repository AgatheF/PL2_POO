package im2ag.m2cci.tp11.nbrecache.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import im2ag.m2cci.tp11.nbrecache.model.NombreInconnu;

/**
 * Cette servlet permet de démarer une partie du jeu du nombre caché. Elle tire
 * au hasard un nombre inconnu qu'elle stocke dans la session de l'utilisateur.
 * Une fois ce tirage effectué, la servlet redirige la requête vers la page JSP
 * pageJeu.jsp qui se charge d'afficher le formulaire permettant à l'utilisateur
 * de faire une proposition.
 *
 * @author Philippe Genoud (Philippe.Genoud@imag.fr)
 */
@WebServlet(name = "FinPartie", urlPatterns = {"/abandonner"})
public class ActionAbandonner extends HttpServlet {

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

        // recuperation de l'objet session
        HttpSession session = request.getSession(true);
        // récupération du  nombre inconnu stocké dans la session
        NombreInconnu valeurCachee = (NombreInconnu) session.getAttribute("nombreInconnu");
        // preparation des messages  defin de partie et stockage de ceux-ci 
        // comme attributs de la requête
        request.setAttribute("message1","Pourquoi abandonner ?");
        request.setAttribute("message2","Le nombre caché était " +
                valeurCachee.getValeur());
        request.setAttribute("styleFinPartie","perdu");
        // redirection vers la page de fin de partie
        request.getRequestDispatcher("/WEB-INF/finPartie.jsp").forward(request, response);
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
        return "Initialise une partie de nombre caché";
    }// </editor-fold>
}
