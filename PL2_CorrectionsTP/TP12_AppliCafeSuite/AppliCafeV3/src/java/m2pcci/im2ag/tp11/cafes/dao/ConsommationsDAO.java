/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m2pcci.im2ag.tp11.cafes.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import m2pcci.im2ag.tp11.cafes.model.Programmeur;

/**
 *
 * @author genoud
 */
public class ConsommationsDAO {
    
    

    public static List<Programmeur> consosParSemaine(DataSource ds, int noSemaine) throws SQLException {

        StringBuilder consosHTML = new StringBuilder();
        String query = "SELECT PROGRAMMEUR,  PRENOM, NOM, NB_TASSES FROM "
                + "CONSOS_CAFE c JOIN PROGRAMMEURS p ON c.PROGRAMMEUR=p.ID "
                + " WHERE c.NO_SEMAINE=" + noSemaine
                + " ORDER BY NB_TASSES DESC";

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
}
