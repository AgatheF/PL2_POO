package ufrim2ag.m2pcci.pl2.tp5.comptes;

/**
 * Compte.java<br>
 *
 * Modélise un compte Bancaire.
 *
 * Un compte possède :
 * <ul>
 * <li>un numéro unique. Chaque compte possède un numéro différent fixé à la
 * création du compte.</li>
 * <li>un titulaire (une Personne)</li>
 * <li>un debit maximal autorise</li>
 * <li>un decouvert maximal autorise</li>
 * <li>un solde (solde >= -decouvert maximal autorisé).<li>
 * </ul>
 *
 * @author Philippe Genoud (Philippe.Genoud@imag.fr)
 */
public class Compte {

    //-------------------------------------------------------------------------
    // constantes
    //-------------------------------------------------------------------------  
    /**
     * constante définissant le débit maximal autorisé par défaut
     */
    private static final double DEFAULT_DEBITMAX = 1000;

    /**
     * constante définissant le découvert maximal autorisé par défaut
     */
    private static final double DEFAULT_DECOUVERTMAX = 800;

    //--------------------------------------------------------------------------
    // variables de classe
    //--------------------------------------------------------------------------  
    /**
     * un compteur du nombre de comptes précédemment créés, utilisé pour
     * attribuer les numéros de compte.
     */
    private static int nbCptsCree = 0;

    //----------------------------------------------------------------------------
    // attributs (variables d'instance)
    //----------------------------------------------------------------------------  
    /**
     * numéro du compte
     */
    private final int numero;

    /**
     * le titulaire du compte
     */
    private Personne titulaire;

    /**
     * solde du compte (en euros)
     */
    private double solde;

    /**
     * decouvert maximal autorise (en euros)
     */
    private double decouvertMax;

    /**
     * débit maximal autorisé (en euros)
     */
    private double debitMax;

    //-------------------------------------------------------------------------
    // Constructeurs
    //-------------------------------------------------------------------------
    /**
     * Constructeur d'un compte.
     *
     * @param tit le titulaire du compte
     * @param depotInitial le dépot initial sur le compte à sa création.
     * <code>depotInitial</code>doit être positif ou nul. Si il est < 0, le
     * compte est initialisé avec un solde nul. 
     * @param decMax decouvert maximal autorisé pour ce compte. Si decMax < 0,
     *        le decouvert maximal autorisé pour ce compte est initialisé avec
     *        la valeur par défaut.
     * @param debMax débit maximal autorisé pour ce compte. si debMax < 0 
     *        le débit maximal autorisé pour ce compte est initialisé avec
     *        la valeur par défaut.
     */
    public Compte(Personne tit, double depotInitial, double decMax, double debMax) {
        nbCptsCree++;
        numero = nbCptsCree;
        titulaire = tit;
        solde = depotInitial;
        decouvertMax = (decMax < 0)?DEFAULT_DECOUVERTMAX:decMax;
        debitMax = (debMax < 0)?DEFAULT_DEBITMAX:debMax;
    }

    /**
     * Constructeur d'un compte. Seuls le titulaire et le dépot initial sont
     * spécifiés. Le decouvert max et le debit max autorisés sont initialisés
     * avec les valeurs par défaut.
     *
     * @param tit le titulaire du compte
     * @param depotInitial le depot sur le compte à sa création
     */
    public Compte(Personne tit, double depotInitial) {
        this(tit, depotInitial, DEFAULT_DECOUVERTMAX, DEFAULT_DEBITMAX);
    }

    /**
     * Constructeur d'un compte. Seul le titulaire est spécifié. Le solde est
     * initialisé à 0 et le decouvert max et le debit max autorisés sont
     * initialisés avec les valeurs par défaut.
     *
     * @param tit le titulaire du compte
     */
    public Compte(Personne tit) {
        this(tit, 0.0, DEFAULT_DECOUVERTMAX, DEFAULT_DEBITMAX);
    }

      //------------------------------------------------------------------------
    // méthodes
    //------------------------------------------------------------------------  
    //---------------------- accesseurs --------------------------------------
    /**
     * @return le numero du compte
     */
    public int getNumero() {
        return numero;
    }

    /**
     * @return le titulaire compte
     */
    public Personne getTitulaire() {
        return titulaire;
    }

    /**
     * @return le solde du compte
     */
    public double getSolde() {
        return solde;
    }

    /**
     * renvoie le découvert maximal autorisé.
     *
     * @return le decouvert maximal
     */
    public double getDecouvertMax() {
        return decouvertMax;
    }

    /**
     * renvoie le débit maximal autorisé.
     *
     * @return le débit maximal autorisé.
     */
    public double getDebitMax() {
        return debitMax;
    }

    //-------------- modifieurs --------------------------------------------
    /**
     * fixe le decouvert maximal autorisé.
     *
     * @param decMax nouvelle valeur pour le découvert maximal.
     * @return false si l'opération n'a pu être effectuée (decMAX <0)
     */
    public boolean setDecouvertMax(double decMax) {
        if (decMax < 0) {
            return false;
        } else {
            decouvertMax = decMax;
            return true;
        }
    }

    /**
     * fixe le débit maximal autorisé.
     *
     * @param debMax nouvelle valeur pour le débit maximal.
     * @return false si l'opération n'a pu être effectuée (debMax <0)
     */
    public boolean setDebitMax(double debMax) {
        if (debMax < 0) {
            return false;
        } else {
            debitMax = debMax;
            return true;
        }
    }

    //------------- autres methodes -------------------------------------------
    /**
     * renvoie la situation de decouvert du compte.
     *
     * @return <code>true</code> si le compte est à découvert,
     * <code>false</code> sinon.
     */
    public boolean estADecouvert() {
        return solde < 0.00;
    }

    /**
     * renvoie le decouvert du compte.
     *
     * @return 0 si le solde du compte est positif ou nul, la valeur absolue du
     * solde si il est negatif.
     */
    public double leDecouvert() {
        if (this.estADecouvert()) {
            return -solde;
        } else {
            return 0;
        }
    }

    /**
     * renvoie le montant maximal qu'il est possible de débiter à un instant
     * donné.Ce montant est le minimum entre le débit maximal autorisé et la
     * somme du solde du compte et du découvert maximum autorisé
     *
     * @return le montant du retrait maximal possible.
     */
    private double retraitMaxPossible() {
        return Math.min(solde + decouvertMax, debitMax);
    }

    /**
     * depose la somme s sur le compte.
     *
     * @param s la somme à déposer. s doit être > 0. Si ce n'est pas le cas
     * l'opération est sans effet sur le compte
     * @return <code>true</code> si le compte à pu être crédité du montant
     * <code>s</code>, <code>false</code>.
     */
    public boolean crediter(double s) {
        if (s > 0.00) {
            solde += s;
            return true;
        } else {
            return false;
        }
    }

    /**
     * débite le compte d'un montant donné et renvoie un boolean indiquant si
     * l'opération s'est correctement effectuée. Si le montant spécifié pour le
     * retrait est supérieur au retrait maximal possible ou inférieur à 0,
     * l'opération est sans effet.
     *
     * @param r la somme à retirer
     * @return <code>true</code> si le compte à pu être débité du montant
     * <code>r</code>, <code>false</code>.
     */
    public boolean debiter(double r) {
        if ((r >= 0) && (r <= retraitMaxPossible())) {
            solde -= r;
            return true;
        } else {
            return false;
        }
    }

    /**
     * effectue un virement vers un autre compte et renvoie un boolean indiquant
     * si l'opération s'est correctement effectuée.
     *
     * @param r le montant du virement (la somme a retirer de ce compte et à
     * déposer sur l'autre compte).
     * @param c compte vers lequel le virement est effectué.
     * @return  <code>true</code> si le virement a pu être réalisé,
     * <code>false</code> sinon (r <0 ou r > au retrait maximal possible sur ce
     * compte.
     */
    public boolean virer(double r, Compte c) {
        if (this.debiter(r)) {
            c.crediter(r);
            return true;
        } else {
            return false;
        }
    }

    /**
     * renvoie sous une forme de chaîne de caractères une description du compte.
     *
     * @return une chaîne donnant la situation du compte.
     */
    @Override
    public String toString() {
        return "-------------------------------------------\n"
                + "Compte " + numero + "\n"
                + "Titulaire " + titulaire + "\n"
                + "Solde : " + solde + " frs\n"
                + "decouvert maximal autorisé " + decouvertMax + "frs\n"
                + "débit maximal autorisé " + debitMax + "frs\n"
                + ((estADecouvert()) ? "Attention ! Vous êtes à découvert\n" : "\n");
    }

    //-------------------------------------------------------------------------
    // programme principal pour le test (rapide... mais loin d'être exhaustif)
    // de la classe Compte. Il faudra faire un test unitaire JUnits
    //-------------------------------------------------------------------------
    public static void main(String[] args) {
        Personne p1 =new Personne("DUPONT", "Jean", "Grenoble");
        Personne p2 = new Personne("DURAND", "Sophie", "Méribel Les Allues");
        Compte c1 = new Compte(p1, 1000);
        Compte c2 = new Compte(p2, 50000, 5000, 6000);
        Compte c3 = new Compte(p2);
        System.out.println(c1);
        System.out.println(c2);
        c1.debiter(300);
        c2.debiter(600);
        c1.crediter(500);
        System.out.println(c1);
        System.out.println(c2);
        c2.virer(15000, c1);
        System.out.println(c1);
        System.out.println(c2);
        c3.debiter(300);
        System.out.println(c3);
    }
}
