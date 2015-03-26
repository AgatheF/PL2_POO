/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formes;

/**
 *
 * @author agathe
 */

import java.awt.geom.Path2D;
import java.awt.geom.Point2D;


public class PolynomeRegulier extends FormeCirculaire {
    
    //-------------- Attribut supplemenatire de FormeCirculaire ----------------
    
    protected int nbSommets;
    
    
    //--------------------     Constructeur      --------------------

    // new avec un nombre de sommet en parametre
    public PolynomeRegulier(int x, int y, int r, int nbSommets) {
        super(x, y, r);

    }
    
    //--------------------     Methodes      -------------------
    
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
