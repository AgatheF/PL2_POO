/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m2pcci.im2ag.tp11.cafes.views;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.CachedRowSet;

/**
 *
 * @author genoud
 */
@WebServlet(name = "GenericTableServlet", urlPatterns = {"/htmlTable"})
public class GenericHTMLTableServlet extends HttpServlet {

    String createTableContent(CachedRowSet crs) throws SQLException {

        ResultSetMetaData crsMetadata = crs.getMetaData();
        int nbColumns = crsMetadata.getColumnCount();
        StringBuilder res = new StringBuilder();
        res.append("<thead><tr>");
        for (int i = 1; i <= nbColumns; i++) {
            res.append("<th>").append(crsMetadata.getColumnName(i)).append("</th>");
        }
        res.append("</tr></thead>");
        res.append("<tbody>");
        while (crs.next()) {
            res.append("<tr>");
            for (int i = 1; i <= nbColumns; i++) {
                res.append("<td>").append(crs.getString(i)).append("</td>");
            }
            res.append("</tr>");
        }
        res.append("</tbody>");
        return res.toString();
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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CachedRowSet tabConsos = (CachedRowSet) request.getAttribute("consos");
        String tabContent;
        try {
            tabContent = createTableContent(tabConsos);
        } catch (SQLException ex) {
            Logger.getLogger(GenericHTMLTableServlet.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServletException(ex.getMessage(), ex);
        }
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<table class=\"tabConsos\">");
        out.println(tabContent);
        out.println("</table>");

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
