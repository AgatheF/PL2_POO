package m2pcci.im2ag.tp11.cafes.dao;

import com.sun.rowset.CachedRowSetImpl;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;
import m2pcci.im2ag.tp11.cafes.model.Programmeur;

/**
 * Méthodes d'accès JDBC aux données de consommations de café des programmeurs
 * 
 * @author Philippe Genoud - LIG STeamer - UJF Grenoble
 */
public class ConsommationsDAO {
    
    public static final String CONSOS_SEMAINE =
            "SELECT PROGRAMMEUR,  PRENOM, NOM, NB_TASSES FROM "
                + "CONSOS_CAFE c JOIN PROGRAMMEURS p ON c.PROGRAMMEUR=p.ID "
                + " WHERE c.NO_SEMAINE=%s ORDER BY NB_TASSES DESC";
    
    public static final String CONSOS_PROGRAMMEUR =
            "SELECT NO_SEMAINE, NB_TASSES FROM "
                + "CONSOS_CAFE c JOIN PROGRAMMEURS p ON c.PROGRAMMEUR=p.ID "
                + " WHERE c.PROGRAMMEUR=%s ORDER BY NO_SEMAINE";

    public static List<Programmeur> consosParSemaine(DataSource ds, int noSemaine) 
            throws SQLException {

        String query = String.format(CONSOS_SEMAINE,noSemaine);
        try (Connection conn = ds.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {
            List<Programmeur> programmeurs = new ArrayList<>();
            boolean lignePaire = false;
            while (rs.next()) {
                int nbTasses = rs.getInt("NB_TASSES");
                String nom = rs.getString("NOM");
                String prenom = rs.getString("PRENOM");
                programmeurs.add(new Programmeur(nom, prenom, nbTasses));
            }
            return programmeurs;
        }
    }
    
    /**
     * 
     * @param dsName le nom de la source de données
     * @param query  la requête
     * @param selecteur la valeur du selecteur pour la requête
     * @return le Cached row set correspondant à la requête
     * @throws SQLException si pb avec JDBC
     */
    private static CachedRowSet getConsos(String dsName, String query, int selecteur) 
            throws SQLException {
        CachedRowSet crs = new CachedRowSetImpl();
        crs.setDataSourceName(dsName);
        crs.setCommand(String.format(query,selecteur));
        crs.execute();
        return crs;
    }
    
    /**
     * Return le CachedRowSet correspondant aux consommations d'un programmeur
     * @param dsName le nom de la source de données
     * @param progId l'identifiant du programmeur
     * @return le Cached row set correspondant à la requête
     * @throws SQLException si pb avec JDBC
     */
    public static CachedRowSet getConsosProgrammeur(String dsName, int progId) throws SQLException {
        return getConsos(dsName,CONSOS_PROGRAMMEUR,progId);
    }
    
    /**
     * Return le CachedRowSet correspondant aux consommations d'une semaine
     * @param dsName le nom de la source de données
     * @param noSemaine le numéro de la semaine
     * @return le Cached row set correspondant à la requête
     * @throws SQLException si pb avec JDBC
     */
    public static CachedRowSet getConsosSemaine(String dsName, int noSemaine) throws SQLException {
        return getConsos(dsName,CONSOS_SEMAINE,noSemaine);
    }
}
