package im2ag.m2cci.tp11.nbrecache.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import im2ag.m2cci.tp11.nbrecache.model.NombreInconnu;
import javax.servlet.annotation.WebInitParam;

/**
 * Cette servlet permet de démarer une partie du jeu du nombre caché. Elle tire
 * au hasard un nombre inconnu qu'elle stocke dans la session de l'utilisateur.
 * Une fois ce tirage effectué, la servlet redirige la requête vers la page JSP
 * pageJeu.jsp qui se charge d'afficher le formulaire permettant à l'utilisateur
 * de faire une proposition.
 *
 * @author Philippe Genoud (Philippe.Genoud@imag.fr)
 */

// les annoations permettent de définir les informations de configuration par
// défaut de la servlet. Si elles sont redéfinies dans le fichier de déploiement
// (web.xml) de la servlet, les valeurs présentent dans web.xml écrasent 
// les valeurs définies par les annotations.
@WebServlet(name = "InitialiseurPartie", urlPatterns = {"/debuterPartie"},
        initParams = {
            @WebInitParam(name = "borneMin", value = "0"),
            @WebInitParam(name = "borneMax", value = "10"),
            @WebInitParam(name = "nbessaisMax", value = "3")
        }
)
public class ActionInitialiser extends HttpServlet {

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

        // recuperation de l'objet session, et si nécessaire (c'est la première
        // fois que l'on passe par cette servlet) création de cette session
        HttpSession session = request.getSession(true);

        // récupération des paramètres d'intialisation de la servlet qui
        // permettent de définir les caractéristiques du nomnre inconnu.
        int borneMin;
        int borneMax;
        int nbessaisMax;
        try {
            borneMin = Integer.parseInt(getServletConfig().getInitParameter("borneMin"));
            borneMax = Integer.parseInt(getServletConfig().getInitParameter("borneMax"));
            nbessaisMax = Integer.parseInt(getServletConfig().getInitParameter("nbessaisMax"));
        } catch (NumberFormatException ex) {
            borneMin = 0;
            borneMax = 100;
            nbessaisMax = 10;
        }
        // création d'un nombre inconnu et stockage de celui-ci dans la session
        NombreInconnu valeurCachee
                = new NombreInconnu(nbessaisMax, borneMin, borneMax);
        session.setAttribute("nombreInconnu", valeurCachee);
        // redirection vers la page de jeu
        request.getRequestDispatcher("/WEB-INF/pageJeu.jsp").forward(request, response);

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
