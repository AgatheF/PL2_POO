package ufrim2ag.m2pcci.pl2.tp8.formes;

import java.awt.Color;
import java.awt.Rectangle;
import ufrim2ag.m2pcci.pl2.tp8.dessin.Dessin;

/**
 * Classe abstraite racine de la hiérarchie des formes
 *
 * @author Philippe Genoud - UJF Grenoble - Lab LIG-Steamer
 */
abstract class AbstractForme implements IForme {

    /**
     * abscisse du point de référence de la forme
     */
    protected int x;

    /**
     * ordonnée du point de référence de la forme
     */
    protected int y;

    /**
     * Le dessin dans lequel la forme se situe
     */
    protected Dessin dessin;

    /**
     * La couleur utilisée pour dessiner le contour la forme. si cette couleur
     * est nulle, La couleur utilisée sera celle du contexte graphique (l'objet
     * Graphics passé en paramètre de la méthode dessiner).
     */
    protected Color coulTrait = null;

    /**
     * l'épaisseur du trait pour le contour
     */
    protected float epaisseurTrait = 1.0f;

    /**
     * La couleur de remplissage de la forme. Si cette couleur est nulle soit seul
     * le contour de la forme sera dessiné (par exemple pour un Cercle), ou si il
     * s'agit d'une forme pleine (par exemple un Disque), la couleur utilisée
     * sera la couleur courante du contexte graphique.
     */
    protected Color coulRemplissage = null;
    
    /**
     * la boite englobante de la forme
     */
    protected Rectangle bounds;

    /**
     * Forme avec couleur uniforme.
     *
     * @param x l'abscisse du point de référence de la forme
     * @param y l'ordonnée du point de référence de la forme
     * @param coulTrait la couleur du contour de la forme
     * @param coulRemplissage la couleur de remplissage
     */
    protected AbstractForme(int x, int y, float epaisseurTrait, Color coulTrait, Color coulRemplissage) {
        this.x = x;
        this.y = y;
        this.epaisseurTrait = epaisseurTrait;
        this.coulTrait = coulTrait;
        this.coulRemplissage = coulRemplissage;
    }

    /**
     * Forme sans coulTrait. La coulTrait utilisée sera celle du contexte
     * graphique (l'objet Graphics utilisé pour dessiner).
     *
     * @param x l'abscisse du point de référence de la forme
     * @param y l'ordonnée du point de référence de la forme
     */
    protected AbstractForme(int x, int y) {
        this(x, y, 1.0f, null, null);
    }

    public void setEpaisseurTrait(float epaisseurTrait) {
        this.epaisseurTrait = epaisseurTrait;
    }

    // méthodes de l'interface IDessinable implémentées par AbstractForme
    @Override
    public void placerA(int px, int py) {
        x = px;
        y = py;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public Dessin getDessin() {
        return dessin;
    }

    @Override
    public void setDessin(Dessin dessin) {
        this.dessin = dessin;
    }

    @Override
    public Rectangle getBounds() {
        return bounds;
    }

}
