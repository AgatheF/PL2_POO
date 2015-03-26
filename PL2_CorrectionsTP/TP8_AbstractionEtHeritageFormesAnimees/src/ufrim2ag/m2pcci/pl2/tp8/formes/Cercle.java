package ufrim2ag.m2pcci.pl2.tp8.formes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * Forme représentant un cercle
 *
 * @author Philippe Genoud - UJF Grenoble - Lab LIG-Steamer
 */
public class Cercle extends FormeCirculaire {
    
    /**
     * Définit un cercle coloré
     *
     * @param x l'abscisse du centre du cercle
     * @param y l'ordonnée du centre du cercle
     * @param r le rayon du cercle
     * @param cTrait la couleur du contour du cercle
     * @param cRemplissage la couleur de remplissage du cercle
     */
    public Cercle(int x, int y, int r, float epaisseurTrait,Color cTrait, Color cRemplissage) {
        super(x, y, r, epaisseurTrait, cTrait, cRemplissage);
    }

    /**
     * Définit un cercle
     *
     * @param x l'abscisse du centre du cercle
     * @param y l'ordonnée du centre du cercle
     * @param r le ray
     */
    public Cercle(int x, int y, int r) {
        this(x, y, r, 1.0f, null, null);
    }

    @Override
    public void dessiner(Graphics g) {
        // on fait une copie du contexte graphique
        Graphics2D g2d = (Graphics2D) g.create();
        if (coulRemplissage != null) {
            g2d.setColor(coulRemplissage);
            g2d.fillOval(x - r, y - r, 2 * r, 2 * r);
        }
        if (coulTrait != null) {
            g2d.setColor(coulTrait);
        }
        g2d.setStroke(new BasicStroke(epaisseurTrait));
        g2d.drawOval(x - r, y - r, 2 * r, 2 * r);
    }

}
