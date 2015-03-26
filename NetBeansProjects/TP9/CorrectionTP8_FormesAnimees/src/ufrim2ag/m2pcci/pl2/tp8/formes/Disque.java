package ufrim2ag.m2pcci.pl2.tp8.formes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * Forme représentant un disque.
 *
 * @author Philippe Genoud - UJF Grenoble - Lab LIG-Steamer
 */
public class Disque extends FormeCirculaire {

    /**
     * Définit un disque qui utilisera la couleur courante du contexte graphique
     *
     * @param x l'abscisse du centre du disque
     * @param y l'ordonnée du centre du disque
     * @param r le rayon du disque
     */
    public Disque(int x, int y, int r) {
        this(x, y, r,1.f,null, null);
    }

    /**
     * Définit un disque coloré
     *
     * @param x l'abscisse du centre du disque
     * @param y l'ordonnée du centre du disque
     * @param r le rayon du disque
     * @param epaisseur défini l'épaisseur du disque
     * @param cTrait la coulTrait du disque
     * @param cRemp la couleur de remplissage du disque
     */
    public Disque(int x, int y, int r, float epaisseur, Color cTrait, Color cRemp) {
        super(x, y, r, epaisseur, cTrait, cRemp);
    }

    @Override
    public void dessiner(Graphics g) {
        // on fait une copie du contexte graphique
        Graphics2D g2d = (Graphics2D) g.create();
        if (coulRemplissage != null) {
            g2d.setColor(coulRemplissage);
        }
        g2d.fillOval(x - r, y - r, 2 * r, 2 * r);
        if (coulTrait != null) {
            g2d.setColor(coulTrait);
        }
        g2d.setStroke(new BasicStroke(epaisseurTrait));
        g2d.drawOval(x - r, y - r, 2 * r, 2 * r);
    
    }

}
