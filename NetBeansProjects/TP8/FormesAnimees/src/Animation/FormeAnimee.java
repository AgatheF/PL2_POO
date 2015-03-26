/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Animation;

import Dessin.Dessin;
import Formes.IForme;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Classe qui implements IForme(pour fonction de dessin) et IAnimable (fonctions d'animation)
 * Anime une forme dans un dessin a l'aide d'un animator
 * @author agathe
 */
public class FormeAnimee implements IForme, IAnimable{

    
    /*--------------------- Attributs ------------------- */
    
    protected IForme forme;
    protected IFormeAnimator animator;
    
    /*--------------------- Constructeur ------------------- */
    public FormeAnimee(IForme forme, IFormeAnimator animator){
        this.forme=forme;
        this.animator=animator;
    }
    
    
    /*--------------------- Méthodes ------------------- */
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
        forme.placerA(x,y);
    }

    @Override
    public Rectangle getBounds() {
        return forme.getBounds();
    }

    @Override
    public void dessiner(Graphics g) {
        forme.dessiner(g);
    }

    @Override
    public void setDessin(Dessin d) {
        forme.setDessin(d);
    }

    @Override
    public void deplacer() {
         animator.deplacer(forme);
    }
    
}
