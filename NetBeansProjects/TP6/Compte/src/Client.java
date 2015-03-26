/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author agathe
 */
public class Client {
    
// ------------------------- Attributs ---------------------------------  
    
String nom;
String prenom;
String adresse;

/**
 * nombreDeCompteTitulaire
 */
int nombreDeCompteDuTitulaire;

Compte[] comptes; // tableau de comptes de classe Compte

// ------------------------- Constructeur --------------------------------- 

/**
 * Constructeur d un Client avec nom, prenom et adresse
 * @param prenom
 * @param nom
 * @param adresse 
 */
public Client(String prenom, String nom, String adresse){
    this.nom=nom;
    this.prenom=prenom;
    this.adresse=adresse;
    this.nombreDeCompteDuTitulaire=0;
    this.comptes=new Compte[10];
}


// ------------------------- Méthodes --------------------------------- 

/**
 * addCompte
 * ajoute une reference vers un Compte
 * @return adresse vers Compte
 */
void addCompte(Compte compte){
    comptes[nombreDeCompteDuTitulaire]=compte;
    nombreDeCompteDuTitulaire++;   
}

 /** 
  * getInfosTitulaire
  * @return nom, prenom et adresse du titulaire
  */
 public String getInfosTitulaire(){    
     return prenom+ ", " +nom +", " +adresse;     
 }


}
