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
public class Chenille {

    // ------------------------- Attributs---------------------------------
   
    // A DOCUMENTER  + revoir choix type private, private final ...
    private final Tete laTete;
    private final Anneau[] lesAnneaux;
    private Dessin zoneDessin;

    // ------------------------- Constructeur---------------------------------
    /**
     * Constructeur d'une chenille composee d une tete et d un tableau d anneaux
     *
     * @param zoneDessin
     */
    
    // VOIR pour inserer nombre d'anneau et rayon en param
    public Chenille(Dessin zoneDessin) {

        int xMax = zoneDessin.getLargeur();
        int yMax = zoneDessin.getHauteur();
        // creation de la tete placee au centre de la zone de dessin
        laTete = new Tete(xMax / 2, yMax / 2, 12, 0);
        lesAnneaux = new Anneau[12];

        // creation des anneaux places en ligne horizontalement
        for (int i = 0; i < lesAnneaux.length; i++) {
            int xAnneau = laTete.x - (i + 1) * laTete.rayon;
            int yAnneau = laTete.y;

            lesAnneaux[i] = new Anneau(xAnneau, yAnneau, laTete.rayon);
        }
    }

    /**
     * dessiner une chenille
     *
     * @param g
     */
    public void dessiner(Graphics g) {
        laTete.dessiner(g);
        for (int i = 0; i < lesAnneaux.length; i++) {
            lesAnneaux[i].dessiner(g);
        }
    }

    /**
     * defini la zone de dessin dans laquelle la chenille s'affiche
     *
     * @param d
     * @see Dessin
     */
    public void setDessin(Dessin d) {
        this.zoneDessin = d;
    }

    /**
     * pour deplacer la tete et les anneaux de la chenille
     */
    public void deplacer() {

        // decalage des anneaux de la queue vers la tete
        for (int i = lesAnneaux.length-1; i >0 ; i--) {
            lesAnneaux[i].x = lesAnneaux[i - 1].x;
            lesAnneaux[i].y = lesAnneaux[i - 1].y;
        }
 
        // decalage du premier anneau a l aplace de la tete
        lesAnneaux[0].x = laTete.x;
        lesAnneaux[0].y = laTete.y;

        // deplacer la tete selon un cap
        laTete.deplacerSelonCap(zoneDessin);

        

    }
}
