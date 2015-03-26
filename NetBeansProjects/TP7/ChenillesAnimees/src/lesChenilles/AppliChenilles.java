
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author agathe
 */
package lesChenilles;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class AppliChenilles {

    public static void main(String[] args) {

        // la fenêtre graphique
        JFrame laFenetre = new JFrame("LES PETITES CHENILLES");
        laFenetre.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        laFenetre.setSize(600, 600);

        // créé la zone de dessin et la place dans la fenêtre
        Dessin d = new Dessin();
        laFenetre.add(d); 

        //  affiche la fenêtre
        laFenetre.setVisible(true);
        //laFenetre.pack();

        // creation d'une chenille
        Chenille c1 = new Chenille(d);
        // creation d'un tableau de chenille
        Chenille[] lesChenilles;
        lesChenilles = new Chenille[6];

        // on rajoute cet objet la zône de dessin
        d.ajouterObjet(c1);
        lesChenilles[0] = new Chenille(d);
        d.ajouterObjet(lesChenilles[0]);
        for (int i = 1; i < lesChenilles.length; i++) {
            lesChenilles[i] = new Chenille(d);
            d.ajouterObjet(lesChenilles[i]);
        }

        // la boucle d'animation
        // c'est une boucle infinie, le programme devra être interrompu
        // par CTRL-C ou en cliquant dans le cas de fermeture de la fenêtre
        while (true) {
            // le visage effectue un déplacement élémentaire
            c1.deplacer();
            lesChenilles[0].deplacer();
            for (int i = 1; i < lesChenilles.length; i++) {
                lesChenilles[i].deplacer();

                // la zone de dessin se réaffiche
                d.repaint();

                // un temps de pause pour avoir le temps de voir le nouveau dessin
                d.pause(30);

            }
        }
    } 
}