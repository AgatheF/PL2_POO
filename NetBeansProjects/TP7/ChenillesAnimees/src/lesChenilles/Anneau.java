/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lesChenilles;
import java.awt.Graphics;
/**
 *
 * @author agathe
 */
public class Anneau {
    
    // ------------------------- Attributs (Variables d instance)---------------------------------
    // A Documenter 
    protected int rayon;
    protected int y;
    protected int x;
   

    // ------------------------- Constructeur---------------------------------    
    /**
    * crée un Anneau en fixant sa position initiale et son rayon
    * @param xInit abscisse du centre de l'anneau
    * @param yInit ordonnée du centre de l'anneau
    * @param r rayon de l'anneau
    */
    public Anneau(int xInit, int yInit, int r) {
        
        x=xInit;
        y=yInit;
        rayon=r;
    }

    // ------------------------- Méthodes --------------------------------- 
    /**
    * retourne abscisse du centre de l'anneau
    * @return abscisse du centre de l'anneau
    */
    public int getX(){
        return this.x;
    }


    /**
    * retourne ordonnée du centre de l'anneau
    * @return ordonnée du centre de l'anneau
    */
    public int getY(){
        return this.y;
    }

    /** positionne le centre de l'anneau en un point donné
    * @param px abscisse du point
    * @param py ordonnée du point
    */
    public void placerA(int px, int py){
        this.x=px;
        this.y=py;
    }

    /**
    * affiche l'anneau en le matérialisant par un cercle noir
    * @param g objet de classe Graphics qui prend en charge la gestion
    * de l'affichage dans la fenêtre de dessin
    */
    public void dessiner(Graphics g){
        // coin supérieur gauche, largeur, hauteur
         g.drawOval(this.x -rayon, this.y -rayon, 2*this.rayon, 2*this.rayon);
    }
   
    
}


