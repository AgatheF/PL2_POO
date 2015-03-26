package ufrima2g.m2pcci.pl2.tp9;
import java.io.FileNotFoundException;
import ufrima2g.m2pcci.pl2.tp9.reader.DessinFileReader;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import ufrim2ag.m2pcci.pl2.tp8.dessin.Dessin;

/**
 * @author Philippe Genoud
 */
public class TP9 {

    public static void main(String[] args) throws Exception {
        
       // variable pour traitement des erreurs
        boolean erreurNomFichier;
        
        // création de la fenêtre de l'application
        JFrame laFenetre = new JFrame("Chenilles, etc.");
        laFenetre.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        laFenetre.setSize(512, 512);

        // création de la zône de dessin dans la fenêtre
        Dessin d = new Dessin();
        laFenetre.getContentPane().add(d);

        // affiche la fenêtre
        laFenetre.setVisible(true);

        // chargement des données à partir d'un fichier
        erreurNomFichier = true;
        while (erreurNomFichier) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Entrez le chemin relatif pour accéder au fichier de données : ");
            String cheminRelatif = sc.nextLine();
            String cheminAbsolu = System.getProperty("user.dir") + "/" + cheminRelatif;

            try {
                DessinFileReader.chargerDessinables(cheminAbsolu, d);
                erreurNomFichier=false; // si le nom est correct le erreurNomFichier passe à faux et pas d'exception lancée
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage()); //message d erreur predefini dans la methode de FileNotFoundE
                erreurNomFichier = true; // erreur non fichier a true pour reexecuter la boucle
            }
        }

        while (true) {
            // la zone de dessin se réaffiche
            d.repaint();
            // un temps de pause pour avoir le temps de voir le nouveau dessin
            d.pause(50);
            // fait réaliser aux objets animés un déplacement élémentaire
            d.deplaceAnimables();
        }

    }

} // AppliChenille1

