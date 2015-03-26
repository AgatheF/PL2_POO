package ufrim2ag.m2pcci.pl2.tp8.chenille;

import java.awt.Graphics;
import ufrim2ag.m2pcci.pl2.tp8.animation.CapAnimator;
import ufrim2ag.m2pcci.pl2.tp8.animation.FormeAnimee;
import ufrim2ag.m2pcci.pl2.tp8.animation.IAnimable;
import ufrim2ag.m2pcci.pl2.tp8.animation.IFormeAnimator;
import ufrim2ag.m2pcci.pl2.tp8.dessin.Dessin;
import ufrim2ag.m2pcci.pl2.tp8.formes.Cercle;
import ufrim2ag.m2pcci.pl2.tp8.formes.Disque;
import ufrim2ag.m2pcci.pl2.tp8.formes.FormeCirculaire;

/**
 *
 * @author <a href="mailto: "Philippe Genoud</a> 
 */
public class Chenille implements IAnimable {

    //------- variables d'instance (attributs) --------------------------
    /**
     * tableau stockant les références des anneaux de la chenille
     */
    private final FormeCirculaire[] lesAnneaux;
    /**
     * la tête de la chenille
     */
    private final FormeAnimee laTete;



    //-------- Constructeurs ---------------------------------------------
    /**
     * crée une chenille en spécifiant la feuille de dessin dans laquelle elle
     * se déplace, le rayon et le nombre de ces anneaux. La chenille sera 
     * dirigée par un CapAnimator
     *
     * @param d la feuille de dessin où se situe la chenille
     * @param r rayon des anneaux de la chenille
     * @param nbAnneaux nombre d'anneaux de la chenille
     */
    public Chenille(Dessin d, int r, int nbAnneaux) {
        this(d, r, nbAnneaux, new CapAnimator(0.0, r));
    }
    
    /**
     * crée une chenille en spécifiant la feuille de dessin dans laquelle elle
     * se déplace, le rayon et le nombre de ces anneaux et l'animateur qui
     * la dirigera.
     *
     * @param d la feuille de dessin où se situe la chenille
     * @param r rayon des anneaux de la chenille
     * @param nbAnneaux nombre d'anneaux de la chenille
     * @param animator l'animateur associé à la tête de la Chenille
     */
    public Chenille(Dessin d, int r, int nbAnneaux, IFormeAnimator animator) {
        if (animator == null)
            animator = new CapAnimator(0.0, r);
        // crée une tête au centre de la fenêtre et de cap 0
        laTete = new FormeAnimee(new Disque(d.getLargeur() / 2, d.getHauteur() / 2, r), 
                animator
        );
        int xTete = laTete.getX();
        int yTete = laTete.getY();

        // 1) créer le tableau
        lesAnneaux = new Cercle[nbAnneaux];
        // 2) remplir le tableau en créeant les anneau et en stockant
        //    leur référence dans les éléments du tableau.
        // créé les anneaux, à gauche les uns des autres. Le premier
        // (Anneau n° 0) étant à gauche de la tête
        for (int i = 0; i < lesAnneaux.length; i++) {
            lesAnneaux[i] = new Cercle(xTete - (i + 1) * r, yTete, r);
        }
    }

    /**
     * affiche la chenille.
     *
     * @param g cet objet de classe Graphics passé en paramètre est l'objet qui
     * prend en charge la gestion de l'affichage dans la fenêtre de dessin.
     * C'est cet objet qui gère le "contexte graphique" pour cette fenêtre.
     */
    @Override
    public void dessiner(Graphics g) {
        // dessiner la tête
        laTete.dessiner(g);
        for (FormeCirculaire anneau : lesAnneaux) {
            anneau.dessiner(g);
        }
    }

    /**
     * fait effectuer à la chenille un déplacement élémentaire en avant dans la
     * direction indiquée par son cap. Le cap subit une déviation alétoire d'un
     * angle de plus ou moins 30 degrés. Si la tête de la chenille atteint un
     * des bords , son cap est alors dévié de 90°.
     */
    @Override
    public void deplacer() {
        // fait avancer les anneaux.
        // le ième anneau prends la place du (i-1)ème anneau, l'anneau 0 prenant la place
        // de la tête
        for (int i = lesAnneaux.length - 1; i > 0; i--) {
            lesAnneaux[i].placerA(lesAnneaux[i - 1].getX(), lesAnneaux[i - 1].getY());
        }
        lesAnneaux[0].placerA(laTete.getX(), laTete.getY());
        laTete.deplacer();
    }

    @Override
    public void setDessin(Dessin d) {
        laTete.setDessin(d);
    }

    @Override
    public Dessin getDessin() {
        return laTete.getDessin();
    }

}// Chenille
