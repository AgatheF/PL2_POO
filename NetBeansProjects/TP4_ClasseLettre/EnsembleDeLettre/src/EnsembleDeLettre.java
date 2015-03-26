import com.sun.corba.se.spi.presentation.rmi.PresentationDefaults;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author fureta
 */
public class EnsembleDeLettre {

    // creation d'un tableau de booleen pour representation de l'ensemble
    boolean[] present = new boolean[26];

    /**
     * Constructeurs
     */
    /**
     * Ensemble de lettre vide créé un nouvel ensemble initialement vide
     */
    public EnsembleDeLettre() {
//        for (int i = 0; i < present.length; i++) {
//            present[i] = false;
//        }
    }

    /**
     * Ensemble de lettre Créer un nouvel ensemble qui contient toutes les
     * lettres présentes dans la chaîne.
     *
     * @param s
     */
    public EnsembleDeLettre(String s) {

        String s2 = s.toLowerCase();
        for (int i = 0; i < s2.length(); i++) {
            int rang = (int) s2.charAt(i) - (int) 'a';
            //entier(lettre i) - entier(a)= rang i dans le tableau present
            present[rang] = true;
        }
    }

    /**
     * Ensemble de lettre aléatoire créé un nouvel ensemble de lettre de taille
     * nbLettre saisie en parametre
     *
     * @param nbLettre
     */
    public EnsembleDeLettre(int nbLettre) {
        int rangAleatoire;
        for (int i = 0; i < nbLettre; i++) {
            // rang de 0 à 25: (nb aleatoire < 1) * 26
            do {
                rangAleatoire = (int) (Math.random() * 26);
            } while (present[rangAleatoire]); // on retire si la lettre piochee etait deja true

            present[rangAleatoire] = true;
        }
    }

    /**
     * Méthodes
     */
    /**
     * toString: Convertit et met en forme l'ensemble de lettre
     *
     * @return
     */
    public String toString() {
        String chaineRes = "{";
        // comment ecrire :chaineRes[0]='{';

        for (int i = 0; i < present.length; i++) {
            if (present[i]) {
                char c = convertToChar(i);
                chaineRes = chaineRes + c;
            }
        }
        chaineRes = chaineRes + '}';
        return chaineRes;
    }

    /**
     * convertToChar
     *
     * @param i
     * @return un caractere correspondant a l'indice
     */
    public char convertToChar(int i) {
        return (char) (i + (int) ('a'));

    }

    /**
     * cardinal calcul le nombre d'element dans l'ens
     *
     * @return le nombre d’éléments de l’ensemble
     */
    public int cardinal() {
        int cardinal = 0;
        for (int i = 0; i < present.length; i++) {
            if (present[i] == true) {
                cardinal = cardinal + 1;
            }
        }
        return cardinal;
    }

    /**
     * estVide test si l'ensemble est vide
     *
     * @return vrai si elle est vide fausse sinon
     */
    public boolean estVide() {
        return (this.cardinal() == 0);

    }

    /**
     * estIncluDans test si la chaine (a laquelle on applqiue la methode = this)
     * est inclut dans la chaine en parametre
     *
     * @param e
     * @return vrai si la chaine est inclut faux sinon
     */
    public boolean estIncluDans(EnsembleDeLettre e) {
        for (int i = 0; i < present.length; i++) {
            if (this.present[i] == true && e.present[i] != this.present[i]) {
                return false;
            }
        }
        return true;
        //vrai toute les lettre presente dans this le sont dans e
    }

    /**
     * lettreEstDans test si une lettre st dans l'ensemble
     *
     * @param c
     * @return vrai si la lettre en parametre est inclut dans l'ensemble
     */
    public boolean lettreEstDans(char c) {
        int rangC = (int) (c) - (int) ('a');
        return (present[rangC] == true);
    }

    /**
     * intersectionEnsemble crée un ens contenant les elements appartenant a
     * l'ens et a un ens donné en parametre
     *
     * @param e
     * @return un ensembleDeLettre contenant l'intersection de l'ens et d un ens
     * e
     */
    public EnsembleDeLettre intersectionEnsemble(EnsembleDeLettre e) {
        EnsembleDeLettre eInter = new EnsembleDeLettre(); // creation d'un ensemble vide
        for (int i = 0; i < present.length; i++) {
            //element i dans eInter si dans this ET dans e
            eInter.present[i] = this.present[i] && e.present[i];
        }
        return eInter;
    }

    /**
     * unionEnsemble crée un ens contenant l'union les elements appartenent a
     * l'ens ou a un ens e donné en parametre
     *
     * @param e
     * @return un ensembleDeLettre contenant l'union de l'ens et de s
     */
    public EnsembleDeLettre unionEnsemble(EnsembleDeLettre e) {
        EnsembleDeLettre eUnion = new EnsembleDeLettre(); // creation d'un ensemble vide
        for (int i = 0; i < present.length; i++) {
            //element i dans eInter si dans this OU dans e
            eUnion.present[i] = this.present[i] || e.present[i];
        }
        return eUnion;
    }

    /**
     * differenceEnsemble crée un ensemble avec les elements de l’ensemble
     * auquel on soustrait les elements un autre ensemble donné en parametre
     *
     * @param e
     * @return un ensembleDeLettre correspondant à la différence de l’ensemble
     * avec un autre ensemble donné en parametre
     */
    public EnsembleDeLettre differenceEnsemble(EnsembleDeLettre e) {
        EnsembleDeLettre eDifference = new EnsembleDeLettre(); // creation d'un ensemble vide
        for (int i = 0; i < present.length; i++) {
            //element i dans eDifference si dans this et pas dans e
            eDifference.present[i] = this.present[i] && !e.present[i];
        }
        return eDifference;
    }

    /**
     * unionDisjointeEnsemble crée un ensemble avec les elements qui
     * appartiennent a l'ens ou a un autre ensemble donné en parametre mais pas
     * aux deux ensembles
     *
     * @param e
     * @return uun ensembleDeLettre correspondant à la différence de l’ensemble
     * avec un autre ensemble donné en parametre
     */
    public EnsembleDeLettre unionDisjointeEnsemble(EnsembleDeLettre e) {
        EnsembleDeLettre eUnionDisjointe = new EnsembleDeLettre(); // creation d'un ensemble vide
        for (int i = 0; i < present.length; i++) {
            //element i dans eUnionDisjointe si dans this et pas e ou inversement
            eUnionDisjointe.present[i] = (this.present[i] && !e.present[i]) || (!this.present[i] && e.present[i]);
        }
        return eUnionDisjointe;
    }
}
