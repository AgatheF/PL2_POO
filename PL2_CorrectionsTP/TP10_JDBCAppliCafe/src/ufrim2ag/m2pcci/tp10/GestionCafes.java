package ufrim2ag.m2pcci.tp10;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Properties;
import java.util.Scanner;

/**
 * Programme de démonstration de JDBC. Permet de se connecter à une BD Oracle et
 * de tester différentes commandes SQL sur des tables représentant les
 * consommations de café de différents programmeurs.
 *
 * Les différentes opérations possibles sont :
 * <ol>
 * <li>Plus gros consommateurs de café sur une semaine</li>
 * <li>Nombre total de tasse pour un programmeur donné</li>
 * <li>La liste triées des consommations sur une semaine</li>
 * <li>Saisir les consommations de tous les programmeurs pour une semaine
 * donnée</li>
 * <li>Exécuter une requête quelconque saisie au clavier et afficher ses
 * résultats</li>
 * <li>Exporter une table vers un fichier texte (CSV)</li>
 * <li>Importer une table depuis un fichier texte (CSV)</li>
 * </ol>
 * <p>
 * Les paramètres d'accès à la BD (classe du driver, url, nom d'utilisateur et
 * mot de passe ) sont fixés dans un fichier properties spécifié en argument de
 * la ligne de commande au lancement du programme. La forme de ce fichier est la
 * suivante :
 * </p>
 * <pre>
 *     jdbcDriver=oracle.jdbc.driver.OracleDriver
 *     dataBaseUrl=jdbc:oracle:thin:@im2ag-oracle.e.ujf-grenoble.fr:1521:ufrima
 *     userName=votreLogin
 *     passwd=votreMotDePasse
 * </pre>
 *
 * @author Philippe.Genoud@imag.fr
 */
public class GestionCafes {

    /**
     * le scanner pour la saisie des données au clavier
     */
    private static final Scanner sc = new Scanner(System.in);
    /**
     * la connexion jdbc utilisée pour effectuer les différentes requêtes
     */
    private static Connection conn = null;

    /**
     * les paramètres de conexion
     */
    private static String driverClassName = "oracle.jdbc.OracleDriver";
    private static String dataBaseUrl
            = "jdbc:oracle:thin:@im2ag-oracle.e.ujf-grenoble.fr:1521:ufrima";
    private static String nomLogin = "votreLogin";
    private static String motDePasse = "votreMotDePasse";

    /**
     * Affiche le ou les programmeurs ayant consommé le nombre maximum de café
     * en une semaine et leur consommation pour cette semaine là.
     *
     * @throws java.sql.SQLException
     */
    public static void plusGrosConsommateurs() throws SQLException {

        System.out.println("Les plus gros consommateurs de cafés sont :");
        String query = "SELECT PROGRAMMEUR,PRENOM,NOM,NB_TASSES,NO_SEMAINE" + ""
                + " FROM CONSOS_CAFE c "
                + "JOIN  PROGRAMMEURS p ON p.ID=c.PROGRAMMEUR WHERE "
                + "c.NB_TASSES=(SELECT MAX(NB_TASSES) FROM CONSOS_CAFE)";
        try (Statement stmt = conn.createStatement(); 
                ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                String nom = rs.getString("NOM");
                String prenom = rs.getString("PRENOM");
                int noSemaine = rs.getInt("NO_SEMAINE");
                int nbTasses = rs.getInt("NB_TASSES");
                System.out.println(prenom + " " + nom + " a consommé " + nbTasses
                        + " tasses de café la semaine " + noSemaine);
            }
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
        try (Statement stmt = conn.createStatement()) {
            String query = "SELECT PROGRAMMEUR,  PRENOM, NOM, NB_TASSES FROM "
                    + "CONSOS_CAFE c JOIN PROGRAMMEURS p ON c.PROGRAMMEUR=p.ID "
                    + " WHERE c.NO_SEMAINE=" + numeroDeSemaine
                    + " ORDER BY NB_TASSES DESC";
            try (ResultSet rs = stmt.executeQuery(query)) {
                int nbTassesTotal = 0;   // le nombre total de tasses
                while (rs.next()) {
                    int nbTasses = rs.getInt("NB_TASSES");
                    int id = rs.getInt("PROGRAMMEUR");
                    String nom = rs.getString("NOM");
                    String prenom = rs.getString("PRENOM");
                    System.out.println("Programmeur " + id + " " + prenom + " " + nom
                            + " : " + nbTasses);
                    nbTassesTotal += nbTasses;
                }

                System.out.println("Le nombre total de tasses consommé la semaine "
                        + numeroDeSemaine + " est " + nbTassesTotal);
            }
        }
        System.out.println("Opération non encore implémentée");
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
        String query = "SELECT SUM(NB_TASSES) FROM "
                + "CONSOS_CAFE  WHERE PROGRAMMEUR=" + idProgrammeur;
        try (Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                int nbTasses = rs.getInt(1);
                System.out.println("Programmeur " + idProgrammeur
                        + " a connsommé un total de : " + nbTasses);
            } else {
                System.out.println("Programmeur inexistant ");
            }
        }
    }

    /**
     * Saisit un numéro de semaine et ensuite pour chaque programmeur permet de
     * rentrer le nombre de tasses qu'il a consommées durant cette semaine.
     * @throws java.sql.SQLException
     */
    public static void sasirConsommations() throws SQLException {

        System.out.print("Numéro de de la semaine : ");
        int noSemaine = sc.nextInt();
        String query = "SELECT ID, PRENOM, NOM FROM PROGRAMMEURS"
                + " WHERE ID NOT IN (SELECT PROGRAMMEUR FROM "
                + " CONSOS_CAFE WHERE NO_SEMAINE ="
                + noSemaine + ") ORDER BY ID";
        try (PreparedStatement ps = conn.prepareStatement(
                "insert into CONSOS_CAFE (NO_SEMAINE, PROGRAMMEUR, NB_TASSES) "
                + " values (?, ?, ?)"); 
                Statement stmt = conn.createStatement(); 
                ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {

                int id = rs.getInt(1);
                System.out.print("nbre de tasse pour "
                        + rs.getString("PRENOM") + " "
                        + rs.getString("NOM") + " (" + id + ") :");
                int nbTasses = sc.nextInt();
                ps.setInt(1, noSemaine);
                ps.setInt(2, id);
                ps.setInt(3, nbTasses);
                ps.executeUpdate();
            }
        }
    }

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
        try (Statement stmt = conn.createStatement()) {
            if (stmt.execute(cmd)) {
                try (ResultSet rs = stmt.getResultSet()) {
                    ResultSetMetaData rsmd = rs.getMetaData();
                    int numberOfColumns = rsmd.getColumnCount();
                    System.out.println("le résultat contient " + numberOfColumns
                            + " colonnes");
                    for (int i = 1; i <= numberOfColumns; i++) {
                        System.out.println("--------------------------------");
                        System.out.println("Colonne : " + i + "\nNOM "
                                + rsmd.getColumnName(i) + " TYPE : "
                                + rsmd.getColumnTypeName(i));
                    }
                    System.out.println("--------------------------------");
                    
                    //affichage du resutlat de la requête
                    System.out.println("Résultats de la requête\n");
                    while (rs.next()) {
                        for (int i = 1; i <= numberOfColumns; i++) {
                            System.out.print(rs.getString(i) + " ");
                        }
                        System.out.println();
                    }
                }
                
            } else {
                System.out.println("Requête effectuée");
                System.out.println("Nombre de lignes modifiées "
                        + stmt.getUpdateCount());
            }
        }
    }

    /**
     * affiche le menu présentant les différentes opérations possibles
     */
    public static void affMenu() {
        System.out.println("\n\n------------------------------------------");
        System.out.println("1 : Plus gros consommateurs sur une semaine");
        System.out.println("2 : Nombre total de tasses consommées par un programmeur");
        System.out.println("3 : Consommations pour une semaine donnée");
        System.out.println("4 : Saisie des consommations pour une semaine");
        System.out.println("5 : Requête Libre ");
        System.out.println("6 : Exporter une table vers un fichier texte (CVS) ");
        System.out.println("7 : Importer une table depuis un fichier texte (CSV) ");
        System.out.println("0 : Quitter l'application");
    }

    /**
     * chargement du driver JDBC et ouverture d'une connexion
     */
    public static void ouvrirConnexion() {
        try {
            //chargement explicite du driver (inutile pour ojdbc6.jar qui est
            // un driver compatible JDBC 4.0)
            Class.forName(driverClassName);
            System.out.println("Driver " + driverClassName + "chargé");
            // ouverture de la connexion
            conn = DriverManager.getConnection(dataBaseUrl, nomLogin, motDePasse);
        } catch (ClassNotFoundException e) {
            // exception pouvant être levée par Class.forName
            System.out.println("erreur dans le chargement du driver");
            System.out.println(e.getMessage());
            System.exit(0);  // inutile d'aller plus loin, on arrête le programme
        } catch (SQLException e) {
            // exception pouvant être levée par DriverManager.getConnection
            System.out.println("Impossible de se connecter à " + dataBaseUrl);
            System.out.println("avec comme nom d'utilisateur : " + nomLogin);
            System.out.println("et comme mot de passe " + motDePasse);
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

    /**
     * exporte une table vers un fichier text CSV
     *
     * @throws IOException en cas d'erreur d'écriture dans le fichier
     * @throws SQLException en cas de problème SQL à la lecture de la table à
     * exporter
     */
    private static void exporterTable() throws IOException, SQLException {
        System.out.print("nom de la table à exporter : ");
        String nomTable = sc.next() + sc.nextLine();
        System.out.print("nom du fichier : ");
        String filePath = sc.nextLine();
        TableReaderWriter.writeTable(conn, nomTable, filePath);
    }

    /**
     * importe une table depuis un fichier text CSV
     *
     * @throws IOException en cas d'erreur de lecture dans le fichier
     * @throws SQLException en cas de problème SQL à l'écriture des données dans
     * la table à importer
     */
    private static void importerTable() throws IOException, SQLException {
        System.out.print("nom de la table à importer : ");
        String nomTable = sc.next() + sc.nextLine();
        System.out.print("nom du fichier : ");
        String filePath = sc.nextLine();
        TableReaderWriter.readTable(conn, filePath, nomTable);
    }

    /**
     * le programme principal.
     *
     * @param args le nom d'un fichier properties (pas obligatoire) contenant
     * les infos de connexion à la BD (classe du driver jdbc et url de
     * connexion). Si aucun nom de fichier properties n'est spécifié on prend
     * les valeurs par défaut fixées à la déclaration des variables.
     */
    public static void main(String[] args) {

        if (args.length > 0) {
            // lecture des paramètres BD dans le fichier properties
            // défini par args{0]
            String propFileName = args[0];
            try {
                Properties options = new Properties();
                options.load(new FileInputStream(new File(propFileName)));
                driverClassName = options.getProperty("jdbcDriver");
                dataBaseUrl = options.getProperty("dataBaseUrl");
                nomLogin = options.getProperty("userName");
                motDePasse = options.getProperty("passwd");
            } catch (FileNotFoundException fne) {
                System.out.println("fichier " + propFileName + " non trouvé");
                System.out.println("On prend les valeurs par défaut");
            } catch (IOException ioe) {
                System.out.println("problème lecture du ficher properties " + ioe.getMessage());
                System.exit(0);
            }
        }
        ouvrirConnexion();

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
                        plusGrosConsommateurs();
                        break;
                    case 2:
                        nbreTotalDeTasses();
                        break;
                    case 3:
                        consommationsPourUneSemaine();
                        break;
                    case 4:
                        sasirConsommations();
                        break;
                    case 5:
                        requeteLibreEtMetaDonnees();
                        break;
                    case 6:
                        exporterTable();
                        break;
                    case 7:
                        importerTable();
                        break;
                    default:
                        System.out.println("valeur erronée !");
                }  // end switch
            } catch (SQLException ex) {
                System.out.println("Problème Base de Données " + ex.getMessage());
            } catch (InputMismatchException ex) {
                System.out.println("saisie incorrecte\nRecommencez !!");
                sc.nextLine();   // pour "vider" le scanner
            } catch (IOException ex) {
                System.out.println("Problème d'Entrée/Sortie" + ex.getMessage());
            }
        } while (encore);

        // Fermer la connexion JDBC à la BD
        try {
            conn.close();
        } catch (SQLException sqle) {
            // que faire de plus ?
            System.out.println(sqle.getMessage());
        }
    }
}
