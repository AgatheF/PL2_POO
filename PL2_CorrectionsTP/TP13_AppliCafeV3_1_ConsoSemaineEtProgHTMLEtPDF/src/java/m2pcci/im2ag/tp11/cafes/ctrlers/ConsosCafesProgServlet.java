package m2pcci.im2ag.tp11.cafes.ctrlers;

import java.io.IOException;
import java.sql.SQLException;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;
import m2pcci.im2ag.tp11.cafes.dao.ConsommationsDAO;

/**
 * Cette servlet affiche pour une semaine donnée la liste des consommations de
 * café d'un programmeur.
 *
 * @author Philippe Genoud - LIG-Steamer - UJF
 */
@WebServlet(name = "ConsosCafesProgServlet", urlPatterns = {"/consosProgrammeur"})
public class ConsosCafesProgServlet extends HttpServlet {

    /**
     * la data source gérée par le conteneur de servlets (Tomcat)
     */
    @Resource(name = "jdbc/BD_CAFES")
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

        // récuperation du paramètre idProg, id du Programmeur.
        int idProg = Integer.parseInt(request.getParameter("idProg"));
        String format = request.getParameter("formatP");

        try {
            CachedRowSet tabConsos = ConsommationsDAO.getConsosProgrammeur("java:comp/env/jdbc/BD_CAFES", idProg);
            request.setAttribute("consos", tabConsos);
            request.setAttribute("titre", "Consommations du programmeur " + idProg);
            String viewUrl;
            if (format.equals("pdf")) {
                viewUrl = "/consosPDF";
            } else {
                viewUrl = "/WEB-INF/consosHTML.jsp";
            }
            request.getRequestDispatcher(viewUrl).forward(request, response);
        } catch (SQLException ex) {
            log("SEVERE echec  JDBC", ex);
            throw new ServletException(ex.getMessage(), ex);
        }

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
