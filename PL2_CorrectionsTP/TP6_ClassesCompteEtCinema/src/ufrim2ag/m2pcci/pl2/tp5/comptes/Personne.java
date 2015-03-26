package ufrim2ag.m2pcci.pl2.tp5.comptes;

/**
 * Représente une personne définie par son nom, prenom et son adresse.
 *
 * @author Philippe Genoud - UJF Grenoble - Lab LIG-Steamer
 */
public class Personne {

    // une fois la personne créée, son nom et prénom ne peuvent plus 
    // être modifiés (d'où le modifieur 'final' dans leur déclaration
    // et l'absence de 'setter' pour ces attributs.
    private final String nom;
    private final String prenom;
    private String adresse;

    /**
     * Créee une nouvelle personne
     * @param nom son nom
     * @param prenom son prénom
     * @param adresse son adresse
     */
    public Personne(String nom, String prenom, String adresse) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return nom + ' ' + prenom + '\n'
                + "adresse: " + adresse + '\n';
    }

}
