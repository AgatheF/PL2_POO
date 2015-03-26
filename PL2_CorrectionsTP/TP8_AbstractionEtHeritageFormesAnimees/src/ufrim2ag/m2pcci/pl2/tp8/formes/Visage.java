package ufrim2ag.m2pcci.pl2.tp8.formes;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Cette classe permet de modéliser un visage de forme ovale.
 *
 * Un visage est défini par :
 * <ul>
 *    <li>les coordonnées xc, yc de son centre (ce centre est 
 *        le point de référence de la forme que constitue le
 *        visage)</li>
 *    <li>une largeur et une hauteur,</li>
 * </ul>
 *
 *
 * @author Philippe Genoud - UJF Grenoble - Lab LIG-Steamer
 *
 */
public class Visage extends AbstractForme {

    //---------------------------------------------------------
    // Les constantes de la classe Visage
    //---------------------------------------------------------
    /**
     * Largeur minimale pour un Visage.
     */
    public static final int LARGEUR_MIN = 15;

    /**
     * Hauteur minimale pour un Visage.
     */
    public static final int HAUTEUR_MIN = 15;

    // -------------------------------------------------------------
    // Les attributs (variables d'instance) de la classe Visage
    // -------------------------------------------------------------

    /**
     * largeur du visage. Par défaut 50 pixels.
     */
    private int largeur = 50;

    /**
     * hauteur du visage. Par défaut 50 pixels.
     */
    private int hauteur = 50;


    //---------------------------------------------------------
    // Les constructeurs de la classe Visage
    // ---------------------------------------------------------
    /**
     * Constructeur avec valeurs par défaut. Crée un visage de taille 50x50 dont
     * le coin supérieur gauche du rectangle englobant est situé au point (0,0)
     * de la zône de dessin. 
     */
    public Visage() {
        this(50, 50, 50, 50);
    }

    /**
     * Constructeur avec positionnement du visage. Crée un visage de taille
     * 50x50 mais dont la position du centre est fixée à la création. 
     *
     * @param xc abscisse du centre du visage.
     * @param yc ordonnée du centre du visage.
     */
    public Visage(int xc, int yc) {
        this(xc, yc, 50, 50);
    }

    /**
     * Constructeur avec positionnement du visage et définition de sa taille.
     * Crée un visage dont les dimensions et le centre sont fixées à la
     * création. 
     *
     * @param xc abscisse du centre du visage.
     * @param yc abscisse du centre du visage.
     * @param larg largeur du visage. La largeur doit être supérieure à
     * LARGEUR_MIN
     * @param haut hauteur du visage. La hauteur doit être supérieure à
     * HAUTEUR_MIN
     *
     * @see VisageRond#LARGEUR_MIN
     * @see VisageRond#HAUTEUR_MIN
     */
    public Visage(int xc, int yc, int larg, int haut) {
        super(xc,yc);
        largeur = Math.max(larg, LARGEUR_MIN);
        hauteur = Math.max(haut, HAUTEUR_MIN);
        bounds =  new Rectangle(xc - larg  / 2, yc - haut / 2, larg, haut);
    }



    @Override
    public void placerA(int px, int py) {
        super.placerA(px, py); 
        bounds.setLocation(px - largeur /2, py -  hauteur / 2);
    }

    
    /**
     * affiche le visage.
     *
     * @param g le contexte graphique de la zône de dessin en charge de
     * l'affichage.
     *
     * @see java.awt.Graphics
     * @see IDessinable
     */
    @Override
    public void dessiner(Graphics g) {
        
        int xhg = x - largeur / 2;
        int yhg = y - hauteur / 2;
        
        // dessiner le contour du visage
        g.drawOval(xhg, yhg, largeur, hauteur);

        // dessiner la bouche
        g.drawLine(xhg + largeur / 4, yhg + (2 * hauteur) / 3, xhg
                + (3 * largeur) / 4, yhg + (2 * hauteur) / 3);

        // dessiner les yeux
        int largeurOeil = largeur / 5;
        int hauteurOeil = hauteur / 5;
        g.drawOval(xhg + largeurOeil, yhg + hauteurOeil, largeurOeil,
                hauteurOeil);
        g.drawOval(xhg + 3 * largeurOeil, yhg + hauteurOeil, largeurOeil,
                hauteurOeil);

    }

} // Visage
