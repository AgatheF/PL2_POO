package m2pcci.im2ag.tp11.cafes;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Cette servlet affiche pour une semaine donnée la liste des consommations de
 * café d'un programmeur.
 *
 * @author Philippe Genoud - LIG-Steamer - UJF
 */
@WebServlet(name = "ConsosCafesServlet", urlPatterns = {"/consosCafes"})
public class ConsosCafesServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
        
        // recuperation des parametre de la request (= formulaire index.html)
        String numSem = request.getParameter("numSem");
        String userAgent = request.getHeader("User-Agent");
        
        
        if (userAgent.toLowerCase().contains("chrome")) {
            // le  paramètre numSem de la requête est de la forme
            // <année>-W<numero de semaine>, par exemple pour la
            // 7ème semaine de 2015 on aura numSem=2015-W07
            // on ne considère donc que les deux derniers caractères
            numSem = numSem.substring(numSem.length() - 2);
        }
        // cast numSemaine
        noSemaine = Integer.parseInt(numSem);

        Connection conn = null; // objet Connection
        try {
            /**
             * Connexion jdbc utilisée pour effectuer la requête d'affichage des
             * consos
             */
            Class.forName("oracle.jdbc.OracleDriver"); // chargement du driver Oracle

            // etablissement de la connection
            conn = DriverManager.getConnection("jdbc:"
                    + "oracle:thin:@im2ag-oracle.e.ujf-grenoble.fr:1521:ufrima", "fureta", "bd2015");
            // par ordre decroissant du nombre de tasses = dans order by de la requete
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT PROGRAMMEUR,  PRENOM, NOM, NB_TASSES "
                    + "FROM CONSOS_CAFE c JOIN PROGRAMMEURS p ON c.PROGRAMMEUR=p.ID "
                    + "WHERE c.NO_SEMAINE=" + noSemaine + "ORDER BY NB_TASSES DESC");

            /* affiche la table des consommations */
            // 
            PrintWriter out = response.getWriter();// page de reponse = notre sortie out
            response.setContentType("text/html;charset=UTF-8");
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ConsosCafesServlet</title>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"styles/cafe.css\">");
            out.println("</head>");
            out.println("<body>");
            out.println("<div id='wrapper'>");
            out.println("<img class=\"icon\" src=\"images/coffee.png\" width=100 height=100 alt=\"logo avec une tasse de café\"/>");
            out.println("<h1>Consommations de café pour<br>la semaine : " + noSemaine + "</h1>");
            out.println("<table id='tabConsos'>");
            out.println("<thead>");
            out.println("<th>PROGRAMMEUR</th><th>NBRE TASSES</th>");
            out.println("</thead>");
            out.println("<tbody>");

            int sommeTasseTotal=0;
            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getString("PRENOM") + "</td>" + "<td>" + rs.getString("NB_TASSES") + "</td>");
                out.println("</tr>");
                // Somme total des tasses dans la semaine
                sommeTasseTotal=sommeTasseTotal+Integer.parseInt(rs.getString("NB_TASSES"));   
            }

            out.println("<tfoot>");
            out.println("<tr>");
            out.println("<td>nbre total de tasses </td><td>"+sommeTasseTotal+"</td>");
            out.println("</tr>");
            out.println("</tfoot>");
            out.println("</div>");
            out.println("</table>");
            out.println("<a href=\"index.html\">chosir une autre semaine</a>");
            out.println("</body>");
            out.println("</html>");
            out.close();
        } 
        // Toutes les exception gérées à la fin dans le meme catch
        catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ConsosCafesServlet.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServletException(ex.getMessage(),ex);
        } finally { // exception ne sont pas gérées avec ressource, il faut un finally pour rendre la connexion
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ConsosCafesServlet.class.getName()).log(Level.SEVERE, null, ex);
                    throw new ServletException(ex.getMessage(),ex);
                }
            }
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
