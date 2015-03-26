package ufrim2ag.m2pcci.pl2.tp6;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Philippe Genoud - UJF Grenoble - Lab LIG-Steamer
 */
public class ChenilleCouleur extends Chenille {
    private Color coul;

    /**
     * 
     * @param coul
     * @param d
     * @param r
     * @param nbAnneaux 
     */
    public ChenilleCouleur(Color coul, Dessin d, int r, int nbAnneaux) {
        super(d, r, nbAnneaux);
        this.coul = coul;
    }

    @Override
    public void dessiner(Graphics g) {
        Graphics gd = g.create();
        gd.setColor(coul);
        super.dessiner(gd); 
    }

}
