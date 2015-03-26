/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author agathe
 */
@WebServlet(name = "ConsosCafesBureauServlet", urlPatterns = {"/ConsosBureau"})
public class ConsosCafesBureauServlet extends HttpServlet {

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
        int noBureau = Integer.parseInt(request.getParameter("noBureau"));
        String format = request.getParameter("format");

        try {
            CachedRowSet tabConsos = ConsommationsDAO.getConsosBureau("java:comp/env/jdbc/BD_CAFES", noBureau);
            request.setAttribute("consos", tabConsos);
            request.setAttribute("titre", "Consommations du bureau " + noBureau);
            String viewUrl;
            if (format.equals("pdf")) {
                viewUrl = "/consosPDF";
            } else {
                viewUrl = "/htmlTable";
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

