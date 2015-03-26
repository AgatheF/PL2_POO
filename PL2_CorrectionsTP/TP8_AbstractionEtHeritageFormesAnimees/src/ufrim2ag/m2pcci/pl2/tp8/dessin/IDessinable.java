
package ufrim2ag.m2pcci.pl2.tp8.dessin;

import java.awt.Graphics;

/**
 * Interface definissant les méthodes que doit posséder un objet pour pouvoir 
 * être dessiné.
 * @author Philippe Genoud - UJF Grenoble - Lab LIG-Steamer
 */
public interface IDessinable {
    
    /**
     * dessine l'objet
     * @param g le contexte graphique utilisé pour dessiner l'objet
     */
    void dessiner(Graphics g);
    
    /**
     * fixe le dessin dans lequel l'objet se situe
     * @param d le dessin dans lequel l'objet se situe
     */
    void setDessin(Dessin d);
    
    /**
     * renvoie le dessin dans lequel l'objet se situe
     * @return le dessin dans lequel l'objet se situe
     */
    Dessin getDessin();
}
