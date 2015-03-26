/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionCinema;

import java.lang.Math;

/**
 *
 * @author agathe
 */
public class SalleCinema {
    
    // ------------------------- Attributs---------------------------------
    
    /**
     * Attributs:
     * titreFilm: titre du film joue
     * nbPlaces: nombre de places dans la salle
     * prixNormal: prix au tarif normal
     * nbPlacesVenduesNormal: nombre de places vendues au tarif normal
     * nbPlacesVenduesReduit: nombre de places vendures au tarif reduit
     */
    protected String titreFilm;
    protected int nbPlaces;
    protected double prixNormal;
    protected int nbPlacesVenduesNormal;
    protected int nbPlacesVenduesReduit;
    
    // ------------------------- Constructeur-------------------------------
    
    /**
     * Constructeur dune salle de cinema
     * @param titreFilm
     * @param nbPlaces
     * @param prixNormal 
     */
    SalleCinema (String titreFilm, int nbPlaces, double prixNormal){
        this.titreFilm=titreFilm;
        this.nbPlaces=nbPlaces;
        this.prixNormal=prixNormal;
        this.nbPlacesVenduesNormal=0;
        this.nbPlacesVenduesReduit=0;
        
    }
    // ------------------------- Méthodes ---------------------------------- 
    
    /**
     * nbPlacesDisponibles
     * @return le nombre de paces encore disponibles dans la salle
     */
    public int nbPlacesDisponibles(){
        return nbPlaces-(nbPlacesVenduesNormal+nbPlacesVenduesReduit);
    }
    
    /**
     * Vend un nombre de place nbre au tarif normal ou au tarif reduit 
     * si il y a assez de place affiche le prix total
     * sinon affiche un message indiquant que la vente n'est impossible
     * @param nbre
     * @param tarifReduit 
     */
    public int vendrePlaces(int nbre, boolean tarifReduit){
        if (nbPlacesDisponibles()>=nbre){
            if (tarifReduit){
                nbPlacesVenduesReduit=+nbre; // incrementation du nombre de place vendu au tarif reduit
//                String message="Bon film, le montant total à payer est de "+nbre*prixNormal+" €";
//                System.out.println(message);
            }
            else { 
                nbPlacesVenduesNormal=+nbre;// incrementation du nombre de place vendu au tarif normal
//                String message="Bon film, le montant total à payer est de "+nbre*prixNormal*0.8 + "€";
//                System.out.println(message);
            }
            return nbre;
        }
        else{
            String message="Desole il n y a plus assez de place dans la salle";
            System.out.println(message);
            return 0;
        }
    }
    
    /**
     * permet de remettre le compteur des nombres de places vendues a zero
     * pour une nouvelle seance
     */
    public void remiseAZero(){
        nbPlacesVenduesNormal=0;
        nbPlacesVenduesReduit=0;
    }
    
    /**
     * chiffre d'affaire depuis creation ou remise a zero de la Salle
     * @return le chiffre d'affaire produit par la salle
     */
    public double chiffreAffaires(){
        return nbPlacesVenduesNormal*prixNormal+nbPlacesVenduesReduit*prixNormal*0.8;
    }
    
    /**
     * @return le taux de remplissage de la Salle
     * en %
     */
    public double tauxRemplissage(){
        return Math.round(100*(double)((nbPlacesVenduesNormal+nbPlacesVenduesReduit))/nbPlaces);
    }
    
    
    
    /**
     * @return un affichage de la seance sous forme de chaine de caractere
     */
    @Override
    public String toString(){
        
        String affichage="Film joue: "+titreFilm+"\n"+ "Nombre de places: " +nbPlaces+ "\n"+ "Prix au tarif normal: " +prixNormal+"\n"+ nbPlacesVenduesNormal+ " places vendues au tarif normal\n"+ nbPlacesVenduesReduit+" places vendues au tarif reduit\n";
        return affichage;
     } 
    
                
    public static void main(String[] args) {
        
        SalleCinema salle1=new SalleCinema("ABBA", 280, 6.50);
        SalleCinema salle2=new SalleCinema("Le nom des gens", 310, 6.50);
        SalleCinema salle3=new SalleCinema("Le roi lion", 310, 5.50);
        
        salle1.vendrePlaces(5, true);
        salle1.vendrePlaces(275, false);
        salle1.vendrePlaces(2, false);
        salle2.vendrePlaces(4, true);
        salle3.vendrePlaces(52, false);
        
        double taux1=salle1.tauxRemplissage();
        double taux3=salle3.tauxRemplissage();
        System.out.println("taux de remplissage salle 1: "+taux1);
        System.out.println("taux de remplissage salle 3 : "+salle3.tauxRemplissage());
        
        System.out.println(salle1.toString());
        System.out.println(salle2.toString());
        System.out.println(salle3.toString());
        
//        String expResult = "Film joue: testTitre\nNombre de places: 250\nPrix au tarif normal: 6.50\n2 places vendues au tarif normal\n2 places vendues au tarif reduit\n";
//        System.out.println(expResult);
    }
    
   
}

