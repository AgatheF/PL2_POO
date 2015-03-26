package m2pcci.im2ag.tp11.cafes.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import m2pcci.im2ag.tp11.cafes.dao.ConsommationsDAO;
import m2pcci.im2ag.tp11.cafes.model.Programmeur;

/**
 * Cette servlet affiche pour une semaine donnée la liste des consommations de
 * café d'un programmeur.
 *
 * @author Philippe Genoud - LIG-Steamer - UJF
 */
@WebServlet(name = "ConsosCafesServlet", urlPatterns = {"/consosCafes"})
public class ConsosCafesServlet extends HttpServlet {

    /**
     * la data source gérée par le conteneur de servlets (Tomcat)
     */
    @Resource (name="jdbc/BD_CAFES")
    private DataSource dataSource;


    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // récuperation du paramètre numSem,numéro de la semaine.
        // selon le client (et son support de HTML5) le format du paramètre de
        // de la requête peut être différent. On a ici du code qui permet
        // de prendre en compte la spécificité du client (on considère le cas
        // particulier de Chrome). 
        // bien sûr dans la "vraie vie", le traitement devrait être fait dans la
        // page HTML, avec un script JavaScript, pour envoyer un numéro de semaine
        // normalisé quel que soit le support de HTML5. Le faire ici à uniquement
        // un but pédagogique, l'objectif étant de vous montrer la manipulation
        // des en-têtes (headers) de requêtes HTTP.
        int noSemaine = 1;
        String numSem = request.getParameter("numSem");

        String userAgent = request.getHeader("User-Agent");
        if (userAgent.toLowerCase().contains("chrome")) {
            // le  paramètre numSem de la requête est de la forme
            // <année>-W<numero de semaine>, par exemple pour la
            // 7ème semaine de 2015 on aura numSem=2015-W07
            // on ne considère donc que les deux derniers caractères
            numSem = numSem.substring(numSem.length() - 2);
        }
        try{
            noSemaine = Integer.parseInt(numSem);
        }catch (NumberFormatException e){
            request.setAttribute("messageErreur", "rentrer un nombre");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return; // pour ne pas faire toute la suite
        }
        
        
        String affichage = request.getParameter("affichage");

        try  {
            List<Programmeur> programmeurs =  ConsommationsDAO.consosParSemaine(dataSource, noSemaine);
            //Objet programmeur =liste transmis  avec la requete et recuperer l27 sonsoParSemaine.jsp
            request.setAttribute("consos",programmeurs); 
        } catch (SQLException ex) {
            log("SEVERE echec  JDBC", ex);
            throw new ServletException(ex.getMessage(), ex);
        }

        String viewUrl;
        if (affichage.equals("pdf")) {
            viewUrl = "/consosPDF";
        } else {
            viewUrl = "/WEB-INF/consoParSemaine.jsp";
        }
        request.getRequestDispatcher(viewUrl).forward(request,response);
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
