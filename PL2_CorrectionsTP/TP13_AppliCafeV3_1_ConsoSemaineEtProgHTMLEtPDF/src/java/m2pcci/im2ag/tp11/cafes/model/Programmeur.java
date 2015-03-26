/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m2pcci.im2ag.tp11.cafes.model;

/**
 *
 * @author Philippe Genoud
 */
public class Programmeur {
    
    private String nom;
    private String prenom;
    private int nbTasses;

    public Programmeur(String nom, String prenom, int nbTasses) {
        this.nom = nom;
        this.prenom = prenom;
        this.nbTasses = nbTasses;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getNbTasses() {
        return nbTasses;
    }

    public void setNbTasses(int nbTasses) {
        this.nbTasses = nbTasses;
    }
    
    
    
    
}
