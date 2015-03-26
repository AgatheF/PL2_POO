/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formes;

import Dessin.IDessinable;
import java.awt.Rectangle;

/**
 * Une forme est un objet dessinable défini par :
 * - un point de référence (qui permet de positionner la forme dans le plan) avec fonction placerA
 * - une boite englobante (Rectangle englobant défini par son coin supérieur
 *   gauche, sa largeur et sa hauteur) récupérée avec fonction getBound.
 * @author agathe
 */
public interface IForme extends IDessinable {
    
    /**
     * renvoie l'abscisse du point de référence de la forme
     *
     * @return l'abscisse du point de référence
     */
    public int getX();

    /**
     * renvoie l'ordonnée du point de référence de la forme
     *
     * @return l'ordonnée du point de référence
     */
    public int getY();

    /**
     * positionne le point de référence de la forme en un point donné
     *
     * @param x nouvelle abscisse du point de reference
     * @param y nouvelle ordonnée du point de reference
     */
    public void placerA(int x, int y);
    
    /**
     * renvoie la boite englobante de la forme
     * @return la boite englobante de la forme
     */
    public Rectangle getBounds();

    
}
