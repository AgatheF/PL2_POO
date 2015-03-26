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

    // la connexion JDBC
    private Connection conn;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            // chargement du pilote JDBC
            Class.forName("oracle.jdbc.OracleDriver");
            log("INFO: driver jdbc chargé");
            // ouverture de la connexion
            conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@im2ag-oracle.e.ujf-grenoble.fr:1521:ufrima",
                    "fureta",
                    "bd2015");
        } catch (ClassNotFoundException ex) {
            // ecriture dans le fichier log de tomcat
            log("SEVERE driver jdbc non trouvé", ex);
            throw new ServletException(ex.getMessage(), ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConsosCafesServlet.class.getName()).log(Level.SEVERE, null, ex);
            log("SEVERE echec de l'ouverture de la connexion", ex);
            throw new ServletException(ex.getMessage(), ex);
        }
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
        noSemaine = Integer.parseInt(numSem);

        // on effectue une requête JDBC pour récupérer les consommations de
        // café
        try {
            Statement stmt = conn.createStatement();
            String query = "SELECT PROGRAMMEUR,  PRENOM, NOM, NB_TASSES FROM "
                    + "CONSOS_CAFE c JOIN PROGRAMMEURS p ON c.PROGRAMMEUR=p.ID "
                    + " WHERE c.NO_SEMAINE=" + noSemaine
                    + " ORDER BY NB_TASSES DESC";
            ResultSet rs = stmt.executeQuery(query);
            log("INFO : requête SQL exécutée");

            /* affiche la table des consommations */
            try (PrintWriter out = response.getWriter()) {
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
                boolean lignePaire = false;
                int nbTassesTotal = 0;
                while (rs.next()) {
                    // recuperation resultat avec rs.getInt("nomColonne")
                    int nbTasses = rs.getInt("NB_TASSES"); 
                    int id = rs.getInt("PROGRAMMEUR");
                    String nom = rs.getString("NOM");
                    String prenom = rs.getString("PRENOM");
                    
                    // ouverture de la ligne et première colonne avec classe=alt pour paire --> gestion affichage
                    if (lignePaire) {
                        out.println("<tr class=\"alt\"><td>");
                    } else {
                        out.println("<tr><td>");
                    }
                    // affichage des valeur nom, prenom dans une colonne et nbTasses dans l'autre
                    out.println(prenom);
                    out.println(" ");
                    out.println(nom);
                    out.println("</td><td>");
                    out.println(nbTasses);
                    out.println("</td></tr>");
                    nbTassesTotal += nbTasses;
                    lignePaire = !lignePaire;
                }
                rs.close();
                stmt.close();
                out.println("<tr>");
                out.println("<td>nbre total de tasses </td><td>" + nbTassesTotal + "</td>");
                out.println("</tr>");
                out.println("</tfoot>");
                out.println("</div>");
                out.println("</table>");
                out.println("<a href=\"index.html\">chosir une autre semaine</a>");
                out.println("</body>");
                out.println("</html>");
            }
        } catch (SQLException ex) {
            log("SEVERE pb SQL " + ex.getMessage(), ex);
            throw new ServletException(ex.getMessage(),ex);
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

    @Override
    public void destroy() {
        super.destroy();
        if (conn != null) {
            try {
                conn.close();

            } catch (SQLException ex) {
                Logger.getLogger(ConsosCafesServlet.class
                        .getName()).log(Level.SEVERE, null, ex);
                log(
                        "SEVERE " + ConsosCafesServlet.class
                        .getName() + " pb fermeture de connexion\n", ex);
            }
        }
    }

}
