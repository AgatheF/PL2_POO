package ufrim2ag.m2pcci.pl2.tp5;

/**
 * Classe SalleCinema.java
 *
 * @author Philippe Genoud
 */
public class SalleCinema {

    /**
     * film joué dans la salle
     */
    private String titreFilm;

    /**
     * nombre de places de la salle
     */
    private final int nbPlaces;

    /**
     * prix unitaire (en €) d'une place (au tarif normal)
     */
    private double prixUnit;

    /**
     * nombre de places vendues au tarif normal
     */
    private int placesTarifNormal = 0;

    /**
     * nombre de places vendues au tarif réduit
     */
    private int placesTarifReduit = 0;

    //---------------------------------------------------
    // Constructeurs
    //---------------------------------------------------
    /**
     * initialise une salle de cinéma en indiquant le nom du film joué, le
     * nombre de places de la salle et le prix unitaire d'une place.
     *
     * @param titre titre du film joué dans la salle
     * @param nbp nombre de places de la salle
     * @param prix prix unitaire d'une place non numerotée
     */
    public SalleCinema(String titre, int nbp, double prix) {
        titreFilm = titre;
        nbPlaces = nbp;
        prixUnit = prix;

    }

    /**
     * indique le nombre de places non encore vendues dans la salle
     * @return le nombre de places disponibles
     */
    public int nbrePlacesDisponibles() {
        return nbPlaces - placesTarifReduit - placesTarifNormal;
    }

    /**
     * achat de places. Le nombre de place à acheter et le tarif (réduit ou non)
     * sont indiqué. Le prix à payer est affiché sur la sortie standard. Si le
     * nombre de places à acheter est superieur au nombre de places disponibles
     * aucun achat n'est effectué et un message sur la sortie standard le
     * signale.
     *
     * @param nbre nbre de places à acheter
     * @param tarifReduit true si les places sont à acheter au tarif réduit,
     * false sinon
     */
    public void vendrePlaces(int nbre, boolean tarifReduit) {
        if ((nbre > 0) && (nbre <= nbrePlacesDisponibles())) {
            if (tarifReduit) {
                placesTarifReduit += nbre;
                System.out.println("Prix à payer " + nbre * 0.8 * prixUnit + " euros");
            } else {
                placesTarifNormal += nbre;
                System.out.println("Prix à payer " + nbre * prixUnit + " euros");
            }
        } else {
            System.out.println("nombre de place demandé incorrect");
        }
    }

    /**
     * remet à zero les compteurs de nombre de places vendues.
     */
    public void remiseAZero() {
        placesTarifReduit = placesTarifNormal = 0;
    }

    /**
     * chiffre d'affaire produit par la salle
     *
     * @return la montant du chiffre d'affaire
     */
    public double chiffreAffaires() {
        return prixUnit * placesTarifNormal + 0.8 * prixUnit * placesTarifReduit;
    }

    /**
     * taux de remplissage de la salle (exprimé en pourcentage)
     *
     * @return le taux de remplissage
     */
    public double tauxRemplissage() {
        return (placesTarifNormal + placesTarifReduit) * 100.0 / nbPlaces;
    }

    /**
     * renvoie sous forme de chaine de caractères l'information associèe à la
     * salle.
     *
     * @return la chaine de caractères regroupant les valeurs des différents
     * attributs de la salle.
     */
    @Override
    public String toString() {
        return "------------------------------------------\n"
                + "Film joué : " + titreFilm + "\n"
                + "Nombre de places non numérotées : " + nbPlaces + "\n"
                + "Prix de la place " + prixUnit + "\n"
                + placesTarifNormal + " places vendues à tarif normal\n"
                + placesTarifReduit + " places vendues à tarif réduit\n"
                + "taux de remplissage " + tauxRemplissage() + "\n";
    }

    public double getPrixUnit() {
        return prixUnit;
    }

    public void setPrixUnit(double prixUnit) {
        this.prixUnit = prixUnit;
    }

    public int getPlacesTarifNormal() {
        return placesTarifNormal;
    }

    public int getPlacesTarifReduit() {
        return placesTarifReduit;
    }

    public String getTitreFilm() {
        return titreFilm;
    }

    public void setTitreFilm(String titreFilm) {
        this.titreFilm = titreFilm;
    }
   
   //--------------------------------------------------------
    // programme principal de test
    //--------------------------------------------------------
    public static void main(String[] args) {
        SalleCinema s1 = new SalleCinema("Chat blanc, chat noir", 120, 8.5);
        SalleCinema s2 = new SalleCinema("La vie est belle", 50, 7.5);

        s1.vendrePlaces(2, false); // vente de deux places à tarif normal
        s1.vendrePlaces(3, true); // vente de deux places à tarif normal

        s2.vendrePlaces(3, false); // vente de trois places à tarif normal
        s2.vendrePlaces(6, true); // vente de six places à tarif normal

        System.out.println("\nSalle 1");
        System.out.println(s1);
        System.out.println("nombre de places encore disponibles " + s1.nbrePlacesDisponibles());
        System.out.println("chiffre d'affaire " + s1.chiffreAffaires());

        System.out.println("\nSalle 2");
        System.out.println(s2);
        System.out.println("nombre de places encore disponibles " + s2.nbrePlacesDisponibles());
        System.out.println("chiffre d'affaire " + s2.chiffreAffaires());
    }
}
