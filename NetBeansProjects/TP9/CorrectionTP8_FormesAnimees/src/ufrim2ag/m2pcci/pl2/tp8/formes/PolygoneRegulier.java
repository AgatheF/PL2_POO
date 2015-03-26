/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufrim2ag.m2pcci.pl2.tp8.formes;

import java.awt.Color;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;

/**
 *
 * @author Philippe Genoud - UJF Grenoble - Lab LIG-Steamer
 */
public class PolygoneRegulier extends FormeCirculaireReguliere {

    /**
     * Un polygone régulier avec couleur de remplissage et couleur de contour
     *
     * @param x l'abscisse du centre du cercle où est inscrite la forme et sur
     * lequel les sommets du contour de la forme.
     * @param y l'ordonnée du centre du cercle où est inscrite la forme et sur
     * lequel les sommets du contour de la forme.
     * @param r le rayon du cercle où est inscrite la forme et sur lequel les
     * sommets du contour de la forme.
     * @param nbSommets le nombre de sommets de la forme
     * @param epaisseurTrait epaisseur du trait matérialisant le contour
     * @param couleur la couleur de remplissage
     * @param couleurTrait la couleur du trait de contour
     */
    public PolygoneRegulier(int x, int y, int r, int nbSommets, float epaisseurTrait,
            Color couleur, Color couleurTrait) {
        super(x, y, r, nbSommets, epaisseurTrait, couleur, couleurTrait);
    }

    /**
     * Un polygone régulier
     *
     * @param x l'abscisse du centre du cercle où est inscrite la forme et sur
     * lequel les sommets du contour de la forme.
     * @param y l'ordonnée du centre du cercle où est inscrite la forme et sur
     * lequel les sommets du contour de la forme.
     * @param r le rayon du cercle où est inscrite la forme et sur lequel les
     * sommets du contour de la forme.
     * @param nbSommets le nombre de sommets de la forme

     */
    public PolygoneRegulier(int x, int y, int r, int nbSommets) {
        this(x, y, r, nbSommets, 1.0f, null, null);
    }

    @Override
    protected Path2D relierSommets(Point2D.Float[] sommets) {
        Path2D p = new Path2D.Float();
        p.moveTo(sommets[0].getX(), sommets[0].getY());
        for (int i = 1; i < sommets.length; i++) {
            p.lineTo(sommets[i].getX(), sommets[i].getY());
        }
        p.closePath();
        return p;
    }
}
