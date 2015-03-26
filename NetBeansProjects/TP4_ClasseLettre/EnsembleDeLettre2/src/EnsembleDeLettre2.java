/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author agathe
 */
public class EnsembleDeLettre2 {

    /** 
     * Constructeurs
    */ 
    
    /**
     * Ensemble de lettre vide
     * créé un nouvel ensemble initialement vide
     */
    public EnsembleDeLettre2() {
    }

    /**
     * Ensemble de lettre aléatoire 
     * créé  un nouvel ensemble de lettre
     * de taille nbLettre saisie en parametre
     * @param nbLettre
     */
    public  EnsembleDeLettre2(int nbLettre) {
          //tirage aléatoire de nbLettre (boucle for?)
    }

    /**
     * Ensemble de lettre Créer un nouvel
     * ensemble qui contient toutes
     * les lettres présentes dans la chaîne. 
     * @param s
     */
    public EnsembleDeLettre2(String s) {
         
         //lecture chaine avec classe Scanner puis 
        // construction ensemble de lettre (ex ordre alpha?)
    }

    /**
     * Méthodes
     */
    
    /**
     * affiche la chaine à l'ecran
     */
    public void afficher() {

        System.out.println("{ ");
        // affichage caractere boucle sur length de EnssembleDeLettre possible?
        System.out.println("}");

    }

    /**
     * estVide testsi l'ensemble est vide
     * @return vrai si elle est vide fausse sinon
     */
    public boolean estVide() {

        return (this.equals(""));  // Ensemble DeLettre n'est pas de type String? 
    }
    
    /** cardinal calcul le nombre d'element dans l'ens
     * @return le nombre d’éléments de l’ensemble
     */ 
    public int cardinal() {
        return 5;
    }
    
    /** estIncluDans test si la chaine 
     * en parametre est inclut dans l'ensemble=objet sur lequel la méthode est appliquée
     * (ou tester inverse?)
     * @param chaine
     * @return vrai si la chaine est inclut faux sinon
     */ 
    public boolean estIncluDans(EnsembleDeLettre2 chaine) {
        return true;
    }
    
    /** lettreEstDans test si une lettre st dans l'ensemble
     * @param c
     * @return vrai si la lettre
     * en parametre est inclut dans l'ensemble 
     */ 
    public boolean lettreEstDans(char c) {
        return true;
    }
    
    /** intersectionEnsemble crée un ens contenant les elements 
     * appartenant a l'ens et a un ens donné en parametre
     * @param s
     * @return un ensembleDeLettre contenant l'intersection de l'ens et de s
     */ 
    public EnsembleDeLettre2 intersectionEnsemble(String s) {
        return ;
    }
    
      /** unionEnsemble crée un ens contenant l'union les elements 
     * appartenent a l'ens ou a un ens donné en parametre
     * @param s
     * @return un ensembleDeLettre contenant l'union de l'ens et de s
     */ 
    public EnsembleDeLettre2 unionEnsemble(String s) {
        return ;
    }
    
      /** differenceEnsemble crée un ensemble avec les elements de
     * l’ensemble auquel on soustrait les elements un autre ensemble donné en parametre
     * @param s
     * @return un ensembleDeLettre correspondant à la différence 
     * de l’ensemble avec un autre ensemble donné en parametre
     */ 
    public EnsembleDeLettre2 differenceEnsemble(String s) {
        return ;
    }
    
     /** unionDisjointeEnsemble crée un ensemble avec les elements
     * qui appartienne a l'ens ou a un autre ensemble donné en parametre
     * mais pas aux deux ensembles
     * @param s
     * @return uun ensembleDeLettre correspondant à la différence 
     * de l’ensemble avec un autre ensemble donné en parametre
     */ 
    public EnsembleDeLettre2 unionDisjointeEnsemble(String s) {
        return ;
    }
      
        
  
}


