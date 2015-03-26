package m2pcci.im2ag.tp11.cafes.views;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.*;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import javax.sql.rowset.CachedRowSet;

/**
 *
 * @author Patrick Reignier - Philippe Genoud
 * @version
 */
@WebServlet(name = "ConsosCafesPDF", urlPatterns = {"/consosPDF"})
public class GenericConsosCafesPDF extends HttpServlet {

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

            String titre = (String) request.getAttribute("titre");
            Paragraph pTitre = new Paragraph(titre,
                    FontFactory.getFont(FontFactory.TIMES, 18, Font.BOLDITALIC, BaseColor.BLUE));
            pTitre.setAlignment(Element.ALIGN_CENTER);
            pTitre.setSpacingAfter(30f);

            document.add(pTitre);

            CachedRowSet crs = (CachedRowSet) request.getAttribute("consos");
            ResultSetMetaData crsMetadata = crs.getMetaData();
            int nbColumns = crsMetadata.getColumnCount();

            Font font = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLDITALIC, BaseColor.BLACK);
            PdfPTable table = new PdfPTable(nbColumns);

            for (int i = 1; i <= nbColumns; i++) {
                table.addCell(new Paragraph(crsMetadata.getColumnName(i), font));
            }

            font = FontFactory.getFont(FontFactory.HELVETICA, 10);
            int nbTotalTasses = 0;
            while (crs.next()) {
                for (int i = 1; i <= nbColumns; i++) {
                    table.addCell(new Phrase(" " + crs.getString(i) + " ", font));
                }
                nbTotalTasses += crs.getInt(nbColumns);
            }
            document.add(table);
            Paragraph paraTotal = new Paragraph("Nombre total de tasses: " + nbTotalTasses);
            paraTotal.setSpacingBefore(20f);
            document.add(paraTotal);
            document.close();
        } catch (DocumentException | SQLException ex) {
            Logger.getLogger(GenericConsosCafesPDF.class.getName()).log(Level.SEVERE, null, ex);
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
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
