package ufrim2ag.m2pcci.pl2.tp8.animation;

import ufrim2ag.m2pcci.pl2.tp8.formes.IForme;

/**
 * Animateur pour un trajectoire circulaire
 * @author Philippe Genoud - UJF Grenoble - Lab LIG-Steamer
 */
public class CircularAnimator implements IFormeAnimator {
    
    /**
     * la variation de l'angle à chaque itération
     */
    private double deltaAngle;
    
    /**
     * la rayon du cercle trajectoire
     */
    private int rayon;
    
    /**
     * l'abscisse du centre du cercle trajectoire
     */
    private int xc;
    
    /**
     * l'ordonnée du centre du cercle trajectoire
     */
    private int yc;
    
    /**
     * l'angle définissant la position courante de la forme animée sur le cercle
     * trajectoire
     */
    private double angle;

    /**
     * Constructeur pour animateur sur une trajectoire ciruclaire
     * 
     * @param xc abscisse du centre du cercle trajectoire
     * @param yc ordonnée du centre du cercle trajectoire
     * @param r rayon du cercle trajectoire
     * @param angle position initiale de la forme animée sur le cercle
     *               trajectoire
     * @param deltaAngle variation de l'angle à chaque itération
     */
    public CircularAnimator(int xc, int yc, int r, double angle, double deltaAngle ) {
        this.deltaAngle = deltaAngle;
        this.angle = angle;
        this.rayon = r;
        this.xc = xc;
        this.yc = yc;
    }

    @Override
    public void deplacer(IForme f) {
        angle += deltaAngle;
        double angleRadians = Math.toRadians(angle);
        f.placerA((int) (xc + rayon * Math.cos(angleRadians)),
                (int) (xc + rayon * Math.sin(angleRadians)));
    }

    public double getDeltaAngle() {
        return deltaAngle;
    }

    public void setDeltaAngle(double deltaAngle) {
        this.deltaAngle = deltaAngle;
    }

    public int getDist() {
        return rayon;
    }

    public void setDist(int dist) {
        this.rayon = dist;
    }

    public int getXc() {
        return xc;
    }

    public void setXc(int xc) {
        this.xc = xc;
    }

    public int getYc() {
        return yc;
    }

    public void setYc(int yc) {
        this.yc = yc;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }
    
}
