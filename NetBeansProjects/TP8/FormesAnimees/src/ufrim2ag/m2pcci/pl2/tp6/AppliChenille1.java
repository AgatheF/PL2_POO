package ufrim2ag.m2pcci.pl2.tp6;

import Chenilles.Chenille;
import Dessin.Dessin;
import Dessin.IDessinable;
import Formes.Etoile;
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

        int nbObjets = 3;
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
        IDessinable[] lesObjets = new IDessinable[nbObjets];
        // objet 0=chenille
        lesObjets[0] = new Chenille(d, 15, 10);
        //Objet 1= étoile
        lesObjets[1] = new Etoile(150, 150, 50);
        //Objets 2= hexagone n=6
        lesObjets[2] = new Etoile(300, 300, 40);

        // ajouter les objets
        d.ajouterObjet(lesObjets[0]); 
        d.ajouterObjet(lesObjets[1]);
        d.repaint(); // on redessine la fenetre 
    }

} // AppliChenille1

