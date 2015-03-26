import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Programme de démonstration de JDBC. Permet de se connecter à une BD Oracle et
 * de tester différentes commandes SQL sur des tables représentant les
 * consommations de café de différents programmeurs.
 *
 * Les différentes opérations possibles sont : <ol> <li>Plus gros consommateurs
 * de café sur une semaine</li> <li>Nombre total de tasse pour un programmeur
 * donné</li> <li>La liste triées des consommations sur une semaine</li>
 * <li>Saisir les consommations de tous les programmeurs pour une semaine
 * donnée</li> <li>Exécuter une requête quelconque saisie au clavier et afficher
 * ses résultats</li> </ol>
 *
 *
 * @author Philippe.Genoud@imag.fr
 */
public class GestionCafes {

    /**
     * le scanner pour la saisie des données au clavier
     */
    private static Scanner sc = new Scanner(System.in);
    /**
     * la connexion jdbc utilisée pour effectuer les différentes requêtes
     */
    private static Connection conn = null;

    //------------------------------------------------------------------------
    //------------ FONCTIONS CONSULTATIONS-------------------------------
    /**
     * Affiche le ou les programmeurs ayant consommé le nombre maximum de café
     * en une semaine et leur consommation pour cette semaine.
     *
     * @throws java.sql.SQLException
     */
    public static void plusGrosConsommateurs() throws SQLException {

        System.out.println("Les plus gros consommateurs de cafés sont :");

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT PROGRAMMEUR,PRENOM,NOM,NB_TASSES,NO_SEMAINE FROM CONSOS_CAFE c\n"
                + "       JOIN  PROGRAMMEURS p ON p.ID=c.PROGRAMMEUR WHERE\n"
                + "       c.NB_TASSES=(SELECT MAX(NB_TASSES) FROM CONSOS_CAFE)");

        while (rs.next()) {
            System.out.println(rs.getString("PRENOM") + " " + rs.getString("NOM"));
        }

    }

    /**
     * Affiche pour une semaine donnée la liste des programmeurs triée dans
     * l'ordre décroissant selon leur nombre de consommations et le nombre total
     * de tasses consommées cette semaine
     *
     * @throws java.sql.SQLException
     */
    public static void consommationsPourUneSemaine() throws SQLException {

        System.out.print("Numéro de la semaine : ");
        int numeroDeSemaine = sc.nextInt();

        // par ordre decroissant du nombre de tasses dans la requete
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT PROGRAMMEUR,  PRENOM, NOM, NB_TASSES "
                + "FROM CONSOS_CAFE c JOIN PROGRAMMEURS p ON c.PROGRAMMEUR=p.ID "
                + "WHERE c.NO_SEMAINE=" + numeroDeSemaine + "ORDER BY NB_TASSES DESC");

        while (rs.next()) {
            System.out.println(rs.getString("PRENOM") + " " + rs.getString("NOM") + "  "
                    + rs.getString("NB_TASSES"));
        }

    }

    /**
     * pour un programmeur donné affiche le nombre total de tasses de café
     * consommées.
     *
     * @throws java.sql.SQLException
     */
    public static void nbreTotalDeTasses() throws SQLException {

        System.out.print("Identifiant du programmeur : ");
        int idProgrammeur = sc.nextInt();

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(
                "SELECT SUM(NB_TASSES) as NbTotTasse FROM CONSOS_CAFE WHERE PROGRAMMEUR="
                + idProgrammeur);

        while (rs.next()) {
            System.out.println(rs.getString("NbTotTasse"));
        }
    }
//-----------------------------------------------------------------------------

//------------------  SAISIE/UPDATE  --------------------------------------
    /**
     * Saisit un numéro de semaine et ensuite pour chaque programmeur permet de
     * rentrer le nombre de tasses qu'il a consommées durant cette semaine.
     *
     * @throws java.sql.SQLException
     */
    public static void saisirConsommations() throws SQLException {

        boolean finSaisie = false;
        PreparedStatement ps = conn.prepareStatement("INSERT INTO CONSOS_CAFE (NO_SEMAINE, PROGRAMMEUR, NB_TASSES) "
                + "VALUES(?,?,?)");
        while (!finSaisie) {
            System.out.print("Numéro de de la semaine : ");
            int noSemaine = sc.nextInt();
            System.out.print("Saisir l'identifiant du programmeur : ");
            int idProgrammeur = sc.nextInt();
            System.out.print("Saisir le nombre de tasses: ");
            int nbTasses = sc.nextInt();

            ps.setInt(1, noSemaine);   //methode setXXX(int indice colonne, valeur)
            ps.setInt(2, idProgrammeur);
            ps.setInt(3, nbTasses);
            ps.executeUpdate();
            System.out.println(ps.getUpdateCount());

            System.out.print("Voulez-vous continuer la saisie? Choix: oui ou non ");
            String reponse = sc.next();
            //A RETENIR comparaison chaine de caractere avec equals (car objet)!
            finSaisie = !"oui".equals(reponse);

//            if ("oui".equals(reponse)) {
//                finSaisie = false;
//            } else {
//                finSaisie = true;
//            }
        }
    }
    /**
     * 
     * @throws SQLException 
     */

    public static void supprimerToutesLesLignes() throws SQLException {

        Statement stmt = conn.createStatement();
        System.out.print("Quelle table voulez-vous reinitialisee? ");
            String tableAReinitialisee = sc.next();
        ResultSet rs = stmt.executeQuery("select * from "+tableAReinitialisee);
        while(rs.next()){
            rs.deleteRow();
        }
    }

    //-------------------------------------------------------------------------
    //---------------------  Requete Libre  ----------------------------------
    /**
     * Exécute une requête libre définie par une chaîne donnée au clavier et
     * affiche les méta données concernant le résultat de cette requête
     * quelconque.<br/> <ul> <li>Si la command renvoie un ResultSet (Query)
     * cette méthode indique : <ul> <li>le nombre de colonnes, et pour chaque
     * colonne le nom et le type de la colonne.</li> <li>le contenu du resultSet
     * est affiché ligne par ligne sur la sortie standard.</li> </ul> </li>
     * <li>Si la commande ne renvoie pas un ResultSet (Update) cette méthode
     * indique le nombre de lignes de la table qui ont été modifiées. <li> </ul>
     * @throws java.sql.SQLException
     */
    public static void requeteLibreEtMetaDonnees() throws SQLException {

        System.out.print("Rentrez votre requête : ");
        String cmd = sc.next() + sc.nextLine();

        Statement stmt = conn.createStatement();

        // le statement renvoi un booleen true= query, false=update

        if (stmt.execute(cmd)) {  
            // resultat de la requete query
            ResultSet rs = stmt.getResultSet();
            // MetaDonnee de la table resultat
            ResultSetMetaData rsmd = rs.getMetaData();

            System.out.print("Le nombre de colonne de la table resultat est : "
                    + rsmd.getColumnCount() + "\n");

            for (int i = 1; i < rsmd.getColumnCount(); i++) {
                System.out.println("Colonne: " + i + " NOM: " + rsmd.getColumnName(i) + " TYPE: " + rsmd.getColumnTypeName(i));
            }
            System.out.println("\n");
            while (rs.next()) {
                for (int i = 1; i < rsmd.getColumnCount(); i++) {
                    System.out.print(rs.getString(i) + "\t");
                }
                System.out.println();
            }
        }
        else{
            System.out.println("Nombre de modifications: "+stmt.getUpdateCount());
        }

    }

    // -------------  APPLICATION CHARGEMENT FICHIER ----------------------
    public static void chargerData(String fileName)
            throws FileNotFoundException, IOException, SQLException {
        // 1. Création d'un reader pour lire dans le document
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
            // try avec ressources, qui permet de fermer automatiquement le reader

            //supprimerToutesLesLignes();// ne fonctionne pas car table de type read only
            PreparedStatement ps = conn.prepareStatement("INSERT INTO PROGRAMMEURS"
                + "(ID, NOM, PRENOM, BUREAU) "
                + "VALUES(?,?,?,?)");
                        
        String ligne; // chaîne contenant la ligne courante.
        ligne = reader.readLine();
        // Affichage de la premiere ligne = noms des colonnes
        String[] valSaisies = ligne.toUpperCase().split(",");
              
        // Pour afficher nom des colonnes en premieres lignes theoriquement
        System.out.print("Colonnes de la table chargee: ");
        for (int i = 0; i < valSaisies.length; i++) {
            System.out.print(valSaisies[i]+"  ");
            
        }
        ligne = reader.readLine(); // Ligne suivante
        while (ligne != null) {
            ligne = ligne.trim(); // enlève les espaces en début et fin de ligne
            if (!"".equals(ligne) && !ligne.startsWith("#")) {  // ligne non vide et non commentaire
                // recupération dans un tableau de chaînes des différents élements de la ligne
                valSaisies = ligne.toUpperCase().split(",");
                // chaine passee en majuscule et découpée selon les morceaux séparés des , 
                //(expression régulière en paramètre de split)

                // valeurs du fichier données à la requete prepared
                ps.setInt(1, parseInt(valSaisies[0]));
                ps.setString(2, valSaisies[1]);
                ps.setString(3, valSaisies[2]);
                ps.setInt(4, parseInt(valSaisies[3]));
                
                //execution de la mise a jour
                ps.executeUpdate();
                // passage à la ligne suivante
                ligne = reader.readLine();
            } // fin du if
        } // fin du while --> plus de ligne
        System.out.println(ps.getUpdateCount()); // affichage nombre d eligne modifiees
    }

    
    public static void chargerDataBatch(String fileName)
            throws FileNotFoundException, IOException, SQLException {
        // 1. Création d'un reader pour lire dans le document
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
            // try avec ressources, qui permet de fermer automatiquement le reader

            //supprimerToutesLesLignes();// ne fonctionne pas car table de type read only
            PreparedStatement ps = conn.prepareStatement("INSERT INTO PROGRAMMEURS"
                + "(ID, NOM, PRENOM, BUREAU) "
                + "VALUES(?,?,?,?)");
                        
        String ligne; // chaîne contenant la ligne courante.
        ligne = reader.readLine();
        // Affichage de la premiere ligne = noms des colonnes
        String[] valSaisies = ligne.toUpperCase().split(",");
            
        while (ligne != null) {
            ligne = ligne.trim(); // enlève les espaces en début et fin de ligne
            if (!"".equals(ligne) && !ligne.startsWith("#")) {  // ligne non vide et non commentaire
                // recupération dans un tableau de chaînes des différents élements de la ligne
                valSaisies = ligne.toUpperCase().split(","); // la chaine est mise en
                // chaine passee en majuscule et découpée selon les morceaux séparés des , 
                //(expression régulière en paramètre de split)

                // valeurs du fichier données à la requete prepared
                ps.setInt(1, parseInt(valSaisies[0]));
                ps.setString(2, valSaisies[1]);
                ps.setString(3, valSaisies[2]);
                ps.setInt(4, parseInt(valSaisies[3]));
                
                //execution de la mise a jour
                ps.addBatch();
                // passage à la ligne suivante
                ligne = reader.readLine();
            } // fin du if
        } // fin du while --> plus de ligne
        System.out.println(ps.getUpdateCount()); // affichage nombre d eligne modifiees
        int [] updateCounts = ps.executeBatch();
    }
    
    
    
    /**
     * affiche le menu présentant les différentes opérations possibles
     */
    public static void affMenu() {
        System.out.println("\n\n------------------------------------------");
        System.out.println("1 : Plus gros consommateurs sur une semaine");
        System.out.println("2 : Nombre total de tasses consommées par un programmeur");
        System.out.println("3 : Consommations pour une semaine donnée");
        System.out.println("4 : Sasie des consommations pour une semaine");
        System.out.println("5 : Requête Libre ");
        System.out.println("6 : Charger des donnees à partir d'un fichier ");
        System.out.println("7 : Charger des donnees à partir d'un fichier BATCH");
        System.out.println("0 : Quitter l'application");
    }

    public static void main(String[] args) throws SQLException, IOException {

        // TODO
        // chargement driver --> via bibliotheques du projet
        // connexion a la base -- ATTENTION bien mettre des chaine de caractere
        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@im2ag-oracle.e.ujf-grenoble.fr:1521:ufrima", "fureta", "bd2015");
        } catch (SQLException e) {
            System.out.println("erreur conexion" + e.getMessage());
        }
        System.out.println("Connexion réussie !");

        boolean encore = true;
        do {
            affMenu();
            try {
                System.out.print("votre choix : ");
                int rep = sc.nextInt();

                System.out.println("\n\n");

                switch (rep) {
                    case 0:
                        System.out.print("voulez-vous vraimment quitter le programme O/N ?");
                        encore = sc.next().toUpperCase().charAt(0) != 'O';
                        break;
                    case 1:
                        try {
                            plusGrosConsommateurs();
                        } catch (SQLException e) {
                            System.out.println("erreur de statement " + e.getMessage());
                        }
                        break;

                    case 2:
                        try {
                            nbreTotalDeTasses();
                        } catch (SQLException e) {
                            System.out.println("erreur de statement " + e.getMessage());
                        }
                        break;
                    case 3:
                        try {
                            consommationsPourUneSemaine();
                        } catch (SQLException e) {
                            System.out.println("erreur de statement " + e.getMessage());
                        }
                        break;
                    case 4:
                        try {
                            saisirConsommations();
                        } catch (SQLException e) {
                            System.out.println("erreur de statement " + e.getMessage());
                        }
                        break;
                    case 5:
                        try {
                            requeteLibreEtMetaDonnees();
                        } catch (SQLException e) {
                            System.out.println("erreur de statement " + e.getMessage());
                        }
                        break;
                        case 6:
                        try {
                             // chargement des données à partir d'un fichier
                            //Scanner sc = new Scanner(System.in);
                            //System.out.print("Entrez le chemin du fichier: ");
                            //String cheminRelatif = sc.nextLine();
                            //String cheminAbsolu = System.getProperty("user.dir") + "/" +cheminRelatif;
                            String cheminAbsolu = "/home/agathe/Documents/CCI/PL2/NetBeansProjects/TP10/TP10_JDBC/data/progs.data"; //+ cheminRelatif;
                            chargerData(cheminAbsolu);
                            System.out.println("test "+cheminAbsolu);
                        } catch (SQLException | FileNotFoundException|NumberFormatException e) {
                            // COMMENT tester type exception avant d'afficher le message ????
                            System.out.println("erreur de statement " + e.getMessage());
                            System.out.println("erreur de format, la saisie n'est pas un entier " + e.getMessage());
                            System.out.println("erreur de fichier: fichier saisie non trouvé " + e.getMessage());
                        }
                        break;
                        case 7:
                        try {
                             // chargement des données à partir d'un fichier
                            String cheminAbsolu = "/home/agathe/Documents/CCI/PL2/NetBeansProjects/TP10/TP10_JDBC/data/progs.data"; //+ cheminRelatif;
                            chargerData(cheminAbsolu);
                            System.out.println("test "+cheminAbsolu);
                        } catch (SQLException | FileNotFoundException|NumberFormatException e) {
                            // COMMENT tester type exception avant d'afficher le message ????
                            System.out.println("erreur de statement " + e.getMessage());
                            System.out.println("erreur de format, la saisie n'est pas un entier " + e.getMessage());
                            System.out.println("erreur de fichier: fichier saisie non trouvé " + e.getMessage());
                        }
                        break;
                       
                    default:
                        System.out.println("valeur erronée !");
                }  // end switch
            } catch (InputMismatchException ex) {
                System.out.println("saisie incorrecte\nRecommencez !!");
                sc.nextLine();   // pour "vider" le scanner
            }
        } while (encore);

        // TODO
        // Fermer la connexion à la BD
    }
//    catch (SQLException e) {
//            System.out.println("error de connexion:" + e.getMessage());
};
