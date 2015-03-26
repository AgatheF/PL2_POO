/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author fureta
 */

public class EnsembleDeLettreTestManuel {
    public static void main(String[] args) {
        
        //test ensemble vide
        EnsembleDeLettre e1= new EnsembleDeLettre(); //ensemble vide
        System.out.println(e1); // utilisation automatique de toString
        
        //test constructeur a partir d'une chaine
        String chaine1;
        chaine1 = "abc";
        EnsembleDeLettre e2= new EnsembleDeLettre(chaine1);
        System.out.println(e2); 
        
        //test constructeur a partir d'une chaine
        EnsembleDeLettre e3= new EnsembleDeLettre(2);
        System.out.println(e3); 
        
        //test methode cardinal
        int cardinal=e2.cardinal();
        System.out.println("cardinal= "+cardinal);
        
        //test methode estVide?
        boolean estVide=e1.estVide();
        System.out.println("Est vide?"+estVide);
        
        //test methode estIncluDans?   ***************** A REVOIR
        String chaine2;
        chaine2 = "zxcdef";
        EnsembleDeLettre e4= new EnsembleDeLettre(chaine2);
        System.out.println(e4); 
        boolean estInclu=e2.estIncluDans(e4);
        System.out.println("Est Inclu Dans? "+estInclu);
        
        //test methode lettreEstDans?
        boolean lettreEstDans=e2.lettreEstDans('c');
        System.out.println("Lettre est dans? "+ lettreEstDans);
        
        //test intersectionensemble
        EnsembleDeLettre eInter;
        eInter=e2.intersectionEnsemble(e4);
        System.out.println("Intersection: "+ eInter );
    }
    
    
 
}
