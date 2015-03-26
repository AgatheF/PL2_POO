package m2pcci.im2ag.tp11.cafes.servlets;

/*
 * ConsosCafesPDF.java
 *
 * Created on January 24, 2005, 11:06 PM
 */
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import java.util.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import m2pcci.im2ag.tp11.cafes.model.Programmeur;

/**
 *
 * @author reignier
 * @version
 */
@WebServlet(name = "ConsosCafesPDF", urlPatterns = {"/consosPDF"})
public class ConsosCafesPDF extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/pdf");

        Document document = new Document();

        try (OutputStream out = response.getOutputStream()) {
            PdfWriter.getInstance(document, out);
            document.open();

            String numSem = request.getParameter("numSem");
            Paragraph titre = new Paragraph("Consommations Semaine "
                    + numSem,
                    FontFactory.getFont(FontFactory.TIMES, 18, Font.BOLDITALIC, BaseColor.BLUE));
            titre.setAlignment(Element.ALIGN_CENTER);
            titre.setSpacingAfter(30f);

            document.add(titre);

            int prixTotal = 0;
            PdfPTable table = new PdfPTable(2);

            table.addCell(new Paragraph("Produit", FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLDITALIC, BaseColor.BLACK)));
            table.addCell(new Paragraph("Prix", FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLDITALIC, BaseColor.BLACK)));

            int nbTotalTasses = 0;
            List<Programmeur> programmeurs = (List<Programmeur>) request.getAttribute("consos");
            for (Programmeur p : programmeurs) {
                table.addCell(" " + p.getPrenom() + " " + p.getNom() + " ");
                table.addCell(" " + p.getNbTasses() + " ");
                nbTotalTasses += p.getNbTasses();
            }
            document.add(table);
            Paragraph paraTotal = new Paragraph("Nombre total de tasses: " + nbTotalTasses);
            paraTotal.setSpacingBefore(20f);
            document.add(paraTotal);
            document.close();
        } catch (DocumentException ex) {
            Logger.getLogger(ConsosCafesPDF.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     */
    public String getServletInfo() {
        return "Short description";
    }

}
