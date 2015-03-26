package m2pcci.im2ag.tp11.cafes;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

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
     * effectue une requête SQL pour obtenir les consommations pour une semaine
     * donnée et renvoie un tableau HTML.
     * @param conn la connexion jdbc
     * @param noSemaine le numéro de la semaine
     * @return une chaîne HTML correspondant au contenu de la table des résultats
     * @throws SQLException si problème jdbc
     */
    private String createTableConsoBody(Connection conn, int noSemaine) throws SQLException {

        StringBuilder consosHTML = new StringBuilder();
        String query = "SELECT PROGRAMMEUR,  PRENOM, NOM, NB_TASSES FROM "
                + "CONSOS_CAFE c JOIN PROGRAMMEURS p ON c.PROGRAMMEUR=p.ID "
                + " WHERE c.NO_SEMAINE=" + noSemaine
                + " ORDER BY NB_TASSES DESC";

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        log("INFO : requête SQL exécutée");

        boolean lignePaire = false;
        int nbTassesTotal = 0;
        while (rs.next()) {
            int nbTasses = rs.getInt("NB_TASSES");
            int id = rs.getInt("PROGRAMMEUR");
            String nom = rs.getString("NOM");
            String prenom = rs.getString("PRENOM");
            if (lignePaire) {
                consosHTML.append("<tr class=\"alt\"><td>");
            } else {
                consosHTML.append("<tr><td>");
            }
            consosHTML.append(prenom);
            consosHTML.append(" ");
            consosHTML.append(nom);
            consosHTML.append("</td><td>");
            consosHTML.append(nbTasses);
            consosHTML.append("</td></tr>");
            nbTassesTotal += nbTasses;
            lignePaire = !lignePaire;
        }
        rs.close();
        consosHTML.append("<tfoot><tr>");
        consosHTML.append("<td>nbre total de tasses </td><td>");
        consosHTML.append(nbTassesTotal);
        consosHTML.append("</td></tr></tfoot>");
        return consosHTML.toString();
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

        String consosHTML; // la chaine qui contiendra
        // le texte pour les différentes lignes du tableau HTML des consommations

        // récupération d'une connexion, qui sera automatiquement fermée à
        // la fin du bloc try, en effet il s'agit d'un try avec ressource
        // introduit avec Java7, ce qui équivaut avec un try suivi d'une clause
        // finally qui fermera la ressource (conn.close()) quoi qu'il arrive.
        try (Connection conn = dataSource.getConnection()) {
            consosHTML = createTableConsoBody(conn, noSemaine);
        } catch (SQLException ex) {
            log("SEVERE echec JDBC", ex);
            throw new ServletException(ex.getMessage(), ex);
        }

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
            out.println(consosHTML);
            out.println("</table>");
            out.println("<a href=\"index.html\">chosir une autre semaine</a>");
            out.println("</body>");
            out.println("</html>");
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
