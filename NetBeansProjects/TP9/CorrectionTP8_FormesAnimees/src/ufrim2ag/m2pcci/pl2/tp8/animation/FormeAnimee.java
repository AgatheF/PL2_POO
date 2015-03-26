/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufrim2ag.m2pcci.pl2.tp8.animation;

import java.awt.Graphics;
import java.awt.Rectangle;
import ufrim2ag.m2pcci.pl2.tp8.dessin.Dessin;
import ufrim2ag.m2pcci.pl2.tp8.formes.IForme;

/**
 * Une forme animée. C'est une combinaison de : - une forme - un dessin dans
 * lequel la forme se déplace. - un animateur chargé de faire effectuer à la
 * forme un déplacement élémentaire
 *
 * @author Philippe Genoud - UJF Grenoble - Lab LIG-Steamer
 */
public class FormeAnimee implements IForme, IAnimable {

    // Attributs
    protected IForme forme;
    protected IFormeAnimator animator;

    // Constructeur 
    public FormeAnimee(IForme forme, IFormeAnimator animator) {
        this.forme = forme;
        this.animator = animator;
    }

    @Override
    public void deplacer() {
        animator.deplacer(forme);
    }

    @Override
    public void dessiner(Graphics g) {
        forme.dessiner(g);
    }

    @Override
    public int getX() {
        return forme.getX();
    }

    @Override
    public int getY() {
        return forme.getY();
    }

    @Override
    public void placerA(int x, int y) {
        forme.placerA(x, y);
    }

    @Override
    public Rectangle getBounds() {
        return forme.getBounds();
    }

    @Override
    public void setDessin(Dessin d) {
        this.forme.setDessin(d);
    }

    @Override
    public Dessin getDessin() {
        return this.forme.getDessin();
    }

}
