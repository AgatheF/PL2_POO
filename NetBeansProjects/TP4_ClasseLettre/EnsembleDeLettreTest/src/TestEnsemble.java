/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author fureta
 */

import java.util.Scanner;
// EnsembleDeLettre1 dans un autre package, il faut l'importer pour l'utiliser
import ufrim2ag.m2pcci.pl2.tp3.EnsembleDeLettres1; 

public class TestEnsemble {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        EnsembleDeLettres1 ensResultat; // variable temp avant affichage
        
        // Creation d'ensemble de lettres a partir de chaines de caracteres
        String chaine1="azerty";
        EnsembleDeLettres1 e1=new EnsembleDeLettres1(chaine1);
        String chaine2="qwerty";
        EnsembleDeLettres1 e2=new EnsembleDeLettres1(chaine2);
        String chaine3="xcvb";
        EnsembleDeLettres1 e3=new EnsembleDeLettres1(chaine3);
        // note: méthode toString(): conversion + mise en forme ({, ordre alpha, ..)
        System.out.println(e1);
        System.out.println(e2);
        System.out.println(e3);
        
        // Intersections e1 int e2 et e1 int e3
       ensResultat=e1.intersection1(e2);
       System.out.println(ensResultat);
       ensResultat=e1.intersection1(e3);
       System.out.println(ensResultat);
       
       // Unions e1 U e2 et e1 U e3 et e2 Ue3
       ensResultat=e1.union(e2);
       System.out.println(ensResultat);
       ensResultat=e1.union(e3);
       System.out.println(ensResultat);
       ensResultat=e2.union(e3);
       System.out.println(ensResultat);
       
       
        System.out.println("code entier pour la lettre a est: " + ((int) 'a'));
        char c='B';
        System.out.println("code entier pour le caractere est: " + ((int)c));
    }
    
    
}
