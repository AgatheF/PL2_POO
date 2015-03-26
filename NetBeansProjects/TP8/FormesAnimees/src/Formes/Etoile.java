/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formes;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.nio.file.Path;

/**
 *
 * @author agathe
 */
public class Etoile extends FormeCirculaire {

    //--------------------     Constructeur      --------------------

    public Etoile(int x, int y, int r) {
        super(x, y, r);

    }

    public Etoile(int x, int y, int r, float ep, Color cTrait, Color cRemplissage) {
        // appel constructeur formeCirculaire avec ep et couleur
        super(x,y,r,ep,cTrait,cRemplissage);
    }

    //--------------------     Methodes      --------------------
    // Etape 2
    // construction du chemin reliant les points
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
