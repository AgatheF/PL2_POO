package ufrim2ag.m2pcci.pl2.tp6;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * Ouvre une fenêtre et affiche une chenille, intialement positionnée au centre
 * de la fenêtre et qui ensuite se déplace de manière aléatoire.
 * 
 * 
 * @author Philippe Genoud
 */
public class AppliChenille1 {

	public static void main(String[] args) {
                
                int nbObjets=1;
		// création de la fenêtre de l'application
		JFrame laFenetre = new JFrame("Formes Animees");
		laFenetre.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		laFenetre.setSize(512, 512);

		// création de la zône de dessin dans la fenêtre
		Dessin d = new Dessin();
		laFenetre.getContentPane().add(d);

		// affiche la fenêtre
		laFenetre.setVisible(true);

	// creation d'un tableau d objets dessinable IDessinables
        // ajout des objet a la zône de dessin
        Chenille[] lesObjets = new Chenille[nbObjets];
        for (int i = 0; i < nbObjets; i++) {
            lesObjets[i] = new Chenille(d, 15, 10);
            d.ajouterObjet(lesObjets[i]);
        }

        // la boucle d'animation
        while (true) {

            // fait réaliser aux chenilles un déplacement élémentaire
            for (int i = 0; i < nbObjets; i++) {
                lesObjets[i].deplacer();
            }

            // la zone de dessin se réaffiche
            d.repaint();

            // un temps de pause pour avoir le temps de voir le nouveau dessin
            d.pause(50);

        }

    }

} // AppliChenille1

