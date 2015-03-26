package ufrim2ag.m2pcci.pl2.tp8;

import ufrim2ag.m2pcci.pl2.tp8.dessin.Dessin;
import ufrim2ag.m2pcci.pl2.tp8.formes.Cercle;
import ufrim2ag.m2pcci.pl2.tp8.formes.Etoile;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import ufrim2ag.m2pcci.pl2.tp8.animation.CircularAnimator;
import ufrim2ag.m2pcci.pl2.tp8.animation.FormeAnimee;
import ufrim2ag.m2pcci.pl2.tp8.animation.ReboundAnimator;
import ufrim2ag.m2pcci.pl2.tp8.chenille.Chenille;
import ufrim2ag.m2pcci.pl2.tp8.chenille.ChenilleCouleur;
import ufrim2ag.m2pcci.pl2.tp8.formes.PolygoneRegulier;

/**
 * Application permettant de dessiner des formes et d'en deplacer certaines
 * d'entre elles.
 *
 * @author Philippe Genoud
 */
public class AnimationFormes3 {

    public static void main(String[] args) {

        // création de la fenêtre de l'application
        JFrame laFenetre = new JFrame("Chenilles, etc.");
        laFenetre.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        laFenetre.setSize(512, 512);
        // création de la zône de dessin dans la fenêtre
        Dessin d = new Dessin();
        laFenetre.getContentPane().add(d);
        // affiche la fenêtre
        laFenetre.setVisible(true);

        // les objets fixes de la zone de dessin
        d.ajouterObjet(new Cercle(400, 430, 30, 3.0f, Color.BLUE, Color.GRAY));
        // les formes animées
        d.ajouterObjet(new FormeAnimee(
                new Etoile(350, 100, 50, 8.f, Color.RED, Color.YELLOW), 
                new CircularAnimator(250, 250, 180, 0, 5)
        ));
        d.ajouterObjet(new FormeAnimee(
                new PolygoneRegulier(130, 240, 40, 5, 4.0f, Color.DARK_GRAY, null),
                new ReboundAnimator(5, 5)
        ));

        // la chenille animée
        d.ajouterObjet(new Chenille(d, 10, 10));
        // la chenille couleur animée
        d.ajouterObjet(new ChenilleCouleur(Color.CYAN,d, 10, 10));
        while (true) {
            // la zone de dessin se réaffiche
            d.repaint();
            // un temps de pause pour avoir le temps de voir le nouveau dessin
            d.pause(50);
            // fait réaliser un déplacement élémentaire à tous les objets animés
            d.deplaceAnimables();
        }

    }

} // AnimationFormes2

