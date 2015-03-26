package ufrim2ag.m2pcci.pl2.tp8.formes;

import java.awt.Color;
import java.awt.Rectangle;

/**
 * Une forme inscrite dans un cercle. Le point de référence de la forme est le
 * centre du cercle
 *
 * @author Philippe Genoud - UJF Grenoble - Lab LIG-Steamer
 */
public abstract class FormeCirculaire extends AbstractForme {

    /**
     * rayon du cercle où la forme est inscrite
     */
    protected int r;

    /**
     * Forme circulaire colorée
     *
     * @param x l'abscisse du centre du cercle.
     * @param y l'ordonnée du centre du cercle.
     * @param r le rayon du cercle
     * @param epaisseurTrait l'epaisseur du trait contour
     * @param cTrait la couleur du contour de la forme
     * @param cRemplissage la couleur de remplissage de la forme
     */
    public FormeCirculaire(int x, int y, int r, float epaisseurTrait, Color cTrait ,Color cRemplissage) {
        super(x, y, epaisseurTrait, cTrait, cRemplissage);
        this.r = r;
        bounds = new Rectangle(x - r, y - r, 2 * r + 1, 2 * r + 1);
    }



    /**
     * Forme circulaire. La couleur utilisée sera celle du contexte graphique
     * (l'objet Graphics utilisé pour dessiner).
     *
     * @param x l'abscisse du centre du cercle.
     * @param y l'ordonnée du centre du cercle.
     * @param r le rayon du cercle
     */
    public FormeCirculaire(int x, int y, int r) {
        this(x, y, r, 1.f, null, null);
    }

    @Override
    public void placerA(int px, int py) {
        super.placerA(px, py);
        bounds.setLocation(px - r, py - r);
    }

    /**
     * le rayon du cercle dans lequel la forme est inscrite
     *
     * @return le rayon
     */
    public int getR() {
        return r;
    }

}
