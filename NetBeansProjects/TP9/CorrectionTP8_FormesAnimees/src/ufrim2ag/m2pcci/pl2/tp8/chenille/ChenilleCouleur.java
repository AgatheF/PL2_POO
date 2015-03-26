package ufrim2ag.m2pcci.pl2.tp8.chenille;

import java.awt.Color;
import java.awt.Graphics;
import ufrim2ag.m2pcci.pl2.tp8.animation.IFormeAnimator;
import ufrim2ag.m2pcci.pl2.tp8.dessin.Dessin;

/**
 *
 * @author Philippe Genoud - UJF Grenoble - Lab LIG-Steamer
 */
public class ChenilleCouleur extends Chenille {
    private final Color coul;

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
    
        /**
     * 
     * @param coul
     * @param d
     * @param r
     * @param nbAnneaux 
     * @param animator 
     */
    public ChenilleCouleur(Color coul, Dessin d, int r, int nbAnneaux, IFormeAnimator animator) {
        super(d, r, nbAnneaux, animator);
        this.coul = coul;
    }

    @Override
    public void dessiner(Graphics g) {
        Graphics gd = g.create();
        gd.setColor(coul);
        super.dessiner(gd); 
    }

}
