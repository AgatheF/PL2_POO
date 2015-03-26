package ufrim2ag.m2pcci.pl2.tp8.formes;

import java.awt.Color;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;

/**
 * Une étoile à 5 branches.
 *
 * @author Philippe Genoud - UJF Grenoble - Lab LIG-Steamer
 */
public class Etoile extends FormeCirculaireReguliere {

    /**
     * Une étoile avec couleur de remplissage et couleur de contour
     *
     * @param x l'abscisse du centre du cercle où est inscrite la forme et sur
     * lequel les sommets du contour de l'étoile.
     * @param y l'ordonnée du centre du cercle où est inscrite la forme et sur
     * lequel les sommets du contour de l'étoile.
     * @param r le rayon du cercle où est inscrite la forme et sur lequel les
     * sommets du contour de l'étoile.
     * @param epaisseurTrait epaisseur du trait matérialisant le contour
     * @param couleur la couleur de remplissage
     * @param couleurTrait la couleur du trait de contour
     */
    public Etoile(int x, int y, int r, float epaisseurTrait,
            Color couleur, Color couleurTrait) {
        super(x, y, r, 5, epaisseurTrait, couleur, couleurTrait);
    }

    /**
     * Une étoile. La couleur utilisée pour la dessiner sera celle du contexte
     * graphique
     *
     * (l'objet Graphics utilisé pour dessiner).
     *
     * @param x l'abscisse du centre du cercle où est inscrite la forme et sur
     * lequel les sommets du contour de l'étoile.
     * @param y l'ordonnée du centre du cercle où est inscrite la forme et sur
     * lequel les sommets du contour de l'étoile.
     * @param r le rayon du cercle où est inscrite la forme et sur lequel les
     * sommets du contour de l'étoile.
     */
    public Etoile(int x, int y, int r) {
        this(x, y, r, 5, null, null);
    }

    @Override
    protected Path2D relierSommets(Point2D.Float[] sommets) {
        Path2D star = new Path2D.Float();
        star.moveTo(sommets[0].getX(), sommets[0].getY());
        star.lineTo(sommets[2].getX(), sommets[2].getY());
        star.lineTo(sommets[4].getX(), sommets[4].getY());
        star.lineTo(sommets[1].getX(), sommets[1].getY());
        star.lineTo(sommets[3].getX(), sommets[3].getY());
        star.closePath();
        return star;
    }

}
