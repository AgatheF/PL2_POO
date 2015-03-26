package ufrim2ag.m2pcci.pl2.tp6;

import Animation.CircularAnimator;
import Animation.FormeAnimee;
import Animation.IAnimable;
import Chenilles.Chenille;
import Chenilles.ChenilleCouleur;
import Dessin.Dessin;
import Dessin.IDessinable;
import Formes.Etoile;
import Formes.IForme;
import Formes.PolynomeRegulier;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * Ouvre une fenêtre et affiche des objets animes ou non
 *
 *
 * @author Agathe FURET
 */
public class AppliFormesAnimees {

    public static void main(String[] args) {

        int nbObjets = 2;
        int tailleFenetre=512;
        // création de la fenêtre de l'application
        JFrame laFenetre = new JFrame("Formes Animees");
        laFenetre.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        laFenetre.setSize(tailleFenetre,tailleFenetre);

        // création de la zône de dessin dans la fenêtre
        Dessin d = new Dessin();
        laFenetre.getContentPane().add(d);

        // affiche la fenêtre
        laFenetre.setVisible(true);

        // creation d'un tableau d objets dessinable IDessinables
        // ajout des objet a la zône de dessin
        IDessinable[] lesObjets = new IAnimable[nbObjets];
        // objet 0= Etoile animee circulairement
        lesObjets[0] = new FormeAnimee(
                new Etoile(400,400,50,8.f, Color.RED, Color.YELLOW),
                new CircularAnimator(400, 400, 30, 0, 20));
        
        /*Objet 1= chenille de couleur CHENILLE A REPRENDRE AVEC INTERFACE IANIMABLE
        lesObjets[1] = new ChenilleCouleur(Color.BLUE, d,25,8);*/
        
        /*Objets 1= hexagone n=8 anime circulairement
        lesObjets[1] = new FormeAnimee(
                new PolynomeRegulier(400, 50,50, 8)
                new CircularAnimator(400,350, 75, 0, 8)) ;*/
        
        //Objets 2= hexagone n=6 anime circulairement
        lesObjets[1] = new FormeAnimee(
                new PolynomeRegulier(50, 300,50, 6),
                new CircularAnimator(50,300, 75, 0, 5)) ;

        // ajouter les objets
        d.ajouterObjet(lesObjets[0]); 
        d.ajouterObjet(lesObjets[1]);
        d.repaint(); // on redessine la fenetre 
    }

} // AppliFormesAnimees1

