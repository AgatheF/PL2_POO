/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ensembledelettre;

/**
 *
 * @author agathe
 */
public class EnsembleDeLettre {

    /**
     * Constructeurs
     */
    /**
     * Ensemble de lettre vide --> créé un nouvel ensemble initialement vide
     *
     * @return
     */
    public String EnsembleDeLettre() {
        return "";
    }

    /**
     * Ensemble de lettre aléatoire --> créé un nouvel ensemble de lettre
     * aléatoire hypothèse: nombre de lettre en parametre
     *
     * @param nbLettre
     * @return
     */
    public String EnsembleDeLettre(int nbLettre) {
        return "abc";  //tirage aléatoire de nbLettre (boucle for?)
    }

    /**
     * Ensemble de lettre à prtir d'une chaine de caractere Créer un nouvel
     * ensemble à partir d’une chaîne de caractères. L’ensemble contient toutes
     * les lettres présentes dans la chaîne. !!!!!! Probleme: pas de parem non
     * plus = deja utilise !!!!
     *
     * @return
     */
    public String EnsembleDeLettre2() {
        return "abc";  //lecture chaine avec classe Scanner puis 
        // construction ensemble de lettre (ex ordre alpha?)
    }

    /**
     * Méthodes
     */
    
    /**
     * affiche la chaine à l'ecran
     * @param chaine
     */
    public void afficher(EnsembleDeLettre chaine) {

        System.out.println("{ ");
        // affichage caractere boucle sur length de EnssembleDeLettre possible?
        System.out.println("}");

    }

    /**
     * estVide renvoi vrai si la chaine testee est vide
     * @param chaine
     * @return
     */
    public boolean estVide(EnsembleDeLettre chaine) {

        return (chaine.equals(""));  // Ensemble DeLettre n'est pas de type String? 
    }
    
    /** Cardinal renvoi le nombre d’éléments de l’ensemble
     * @param chaine
     * @return 
     */ 
    public int Cardinal(EnsembleDeLettre chaine) {
        return 5;
    }
    
    
  
}


