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
public class Tete extends Anneau {

    // ------------ Nouvel attribut cap ---------------------
    protected double cap;

    // ------------------------- Constructeur---------------------------------    
    /**
     * crée une Tete en fixant sa position initiale et son rayon
     *
     * @param xInit abscisse du centre de la tete
     * @param yInit ordonnée du centre de la tete
     * @param r rayon de l'anneau
     * @param capInit cap de la tete
     */
    public Tete(int xInit, int yInit, int r, double capInit) {
        super(xInit, yInit, r);
        cap = capInit;
    }

    // ------------------------- Méthodes---------------------------------
    /**
     * affiche la tete en le matérialisant par un cercle plein
     *
     * @param g objet de classe Graphics qui prend en charge la gestion de
     * l'affichage dans la fenêtre de dessin
     */
    @Override
    public void dessiner(Graphics g) {
        g.fillOval(this.x -rayon, this.y -rayon, 2 * this.rayon, 2 * this.rayon);
    }

    /**
     * test si le cap permet de deplacer la chenille sans qu'elle ne sorte de la
     * fenetre
     *
     * @param xMax
     * @param yMax
     * @return true si le deplacement est possible false sinon
     */
    public boolean capOK(int xMax, int yMax) {

        return this.x + rayon * Math.cos(Math.toRadians(cap)) > rayon
                && this.y + rayon * Math.sin(Math.toRadians(cap)) > rayon
                && this.x + rayon * Math.cos(Math.toRadians(cap)) < (xMax - rayon)
                && this.y + rayon * Math.sin(Math.toRadians(cap)) < (yMax - rayon);
    }

    /**
     * ajoute deltaC au cap de la tete
     *
     * @param deltaC
     */
    public void devierCap(double deltaC) {
        this.cap = cap + deltaC;
    }

    /**
     * deplace la tete d'une distance egale a son rayon dans la direction du cap
     * si capOk
     * @param d
     */

    public void deplacerSelonCap(Dessin d) {

        double deltaC = Math.random() * 60 - 30; // deltaC aleatoire entre -30 et +30
        this.devierCap(deltaC);

        //deviation de 5 en 5 --> signe aletaoire=sens aleatoire
        while (!capOK(d.getLargeur(), d.getHauteur())) {
            double deviation=Math.random()*2-1; // deviation entre -1 et 1--> sens aleatoire
            this.devierCap(deviation*5/ Math.abs(deviation));
        }
        this.x = (int) (this.x + rayon * Math.cos(Math.toRadians(cap)));
        this.y = (int) (this.y + rayon * Math.sin(Math.toRadians(cap)));
    }

}
