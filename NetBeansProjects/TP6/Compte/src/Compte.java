/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author agathe
 */
public class Compte {
  
    
// ------------------------- Attributs ---------------------------------     

/**
 * compteurDeCompteur
 * pour incrementer le numeroDeCompte de +1 a chaque creation d un compte
 */
 private static int compteurDeCompte=0;

/**
 * valeurs par defaut à la creation d un compte
 */
private static final double decouvertMaximalAutoriseParDefaut=800;
private static final double debitMaximalAutoriseParDefaut=1000;
 
/**
 * numeroDeCompte
 * entier unique > 0 permettant d'identifier un compte
 * attribue dans selon l ordre de creation des compte 
 */    
int numeroDeCompte;

 
 /**
  *titulaire
  *pointeur vers la personne associee au compte
  */
 private Client titulaire;  //Classe Client dans meme package
 
 /**
  * soldeDuCompte
  * somme disponible sur le compte en Euros
  * peut etre superieur, egale ou inferieur a 0.
  */
 double soldeDuCompte;
 
 /**
  * decouvertDuCompte --> methode calculateDecouvert seulement??????
  * si solde du compte si <0 = abs(soldeDuCompte) sinon =0
  */
double decouvertDuCompte;

/**
 * decouvertMaximalAutorise 
 * Valeur minimale que eput atteindre le soldeDuCompte
 * fixe a l'ouverture du compte ou 800€ par defaut et modifiable ensuite
 */

double decouvertMaximalAutorise;

/** 
 * debitMaximalAutorise
 * Valeur maximale du montant debite en une fois
 */

double debitMaximalAutorise;


// ------------------------- Constructeurs ---------------------------------

/**
 * Constructeur d un compte avec valeur par defaut
 * @param client. 
 */
public Compte(Client client){
    decouvertMaximalAutorise=decouvertMaximalAutoriseParDefaut;
    debitMaximalAutorise=debitMaximalAutoriseParDefaut;
    soldeDuCompte=0;
    
    compteurDeCompte++;
    numeroDeCompte=compteurDeCompte;
    
    titulaire=client;
}

/**
 * Constructeur d un compte avec apport initial
 * @param client
 * @param apportInitial
 */
public Compte(Client client, double apportInitial){
    decouvertMaximalAutorise=decouvertMaximalAutoriseParDefaut;
    debitMaximalAutorise=debitMaximalAutoriseParDefaut;
    soldeDuCompte=apportInitial;
    
    compteurDeCompte++;
    numeroDeCompte=compteurDeCompte;
    
    titulaire=client;
}

/**
 * Constructeur d un compte avec decouvert et debit max autorise 
     * @param client
     * @param decouvertMaximal
     * @param debitMaximal
 */
public Compte(Client client, double decouvertMaximal, double debitMaximal){
    decouvertMaximalAutorise=decouvertMaximal;
    debitMaximalAutorise=debitMaximal;
    soldeDuCompte=0;
    
    compteurDeCompte++;
    numeroDeCompte=compteurDeCompte;
    
    titulaire=client;
}

/**
 * Constructeur d un compte avec decouvert et debit max autorise et apport initial
 * @param client
 * @param decouvertMaximal
 * @param debitMaximal
 * @param apportInitial
 */
public Compte(Client client, double decouvertMaximal, double debitMaximal, double apportInitial){
    decouvertMaximalAutorise=decouvertMaximal;
    debitMaximalAutorise=debitMaximal;
    soldeDuCompte=apportInitial;
    
    compteurDeCompte++;
    numeroDeCompte=compteurDeCompte;
    
    titulaire=client;
}
// ------------------------- Methodes ---------------------------------

/**
 * calculateDecouvertDuCompte sans parametre
 * @return le decouvert du compte ou 0 si aps de decouvert
 */
private double calculateDecouvertDuCompte(){
    if (decouvertDuCompte <0){
        return Math.abs(decouvertDuCompte);
    }
    else{
        return 0;     
    }
}

/**
 * crediter( double montant)
 * ajoute le montant au soldeDuCompte
 * @param montant
 * @return le nouveau solde du compte
 */

 public void crediter(double montant){
     this.soldeDuCompte=this.soldeDuCompte+montant;
 } 
 
 /**
 * debiter( double montant)
 * si le montant finale est superieur au decouvert autorise 
 * retire le montant au soldeDuCompte sinon ne fait rien
 * @param montant
 */

 public void debiter(double montant){
     if ((this.soldeDuCompte-montant)> -this.decouvertMaximalAutorise) {
        this.soldeDuCompte=this.soldeDuCompte-montant;
     }
    else {
        System.out.println("Le decouvert maximal autorise pour ce compte ne permet pas de debiter ce montant ");              
    }   
 } 
 
 
 /**
 * effectuerVirement( double montant, int numeroDeCompteReceveur)
 * si le montant finale est superieur au decouvert autorise 
 * debitele montant au soldeDuCompte et le credite au compte receveur
 * @param montant
 * @param numeroDeCompteReceveur
 */

 public void effectuerVirement(double montant, int numeroDeCompteReceveur ){
     if ((this.soldeDuCompte-montant)> this.decouvertMaximalAutorise) {
        this.soldeDuCompte=this.soldeDuCompte-montant;
        // ajoute le montant au solde du compte associe au numeroDeCompteReceveur 
        // comment faire ??
     }
    else {
        System.out.println("Le decouvert maximal autorise pour ce compte ne permet pas d effectuer ce virement");              
    }     
 }
 
 // --- set attribut du compte
 
 /**
  * setSoldeDuCompte(depotInitial) 
  * attribue au solde du compte valeur du depot initial
  * methode appelee a la creation du compte uniquement
  * @param depotInitial
  * @return le solde du compte
  */
 public double setSoldeDuCompte(double depotInitial){
     this.soldeDuCompte=depotInitial;
     return soldeDuCompte;
 }
 
 /**
  * setDecouvertMaximalAutorise
  * attribue au decouvertMaximalAutorise la valeur du montant
  * @param montant
  * @return le decouvertMaximalAutorise
  */
 public double setDecouvertMaximalAutorise(double montant){
     this.decouvertMaximalAutorise=montant;
     return decouvertMaximalAutorise;
 }
 
 /**
  * setDebitMaximalAutorise
  * attribue au DebitMaximalAutorise la valeur du montant
  * @param montant
  * @return le debitMaximalAutorise
  */
 public double setDebitMaximalAutorise(double montant){
     this.debitMaximalAutorise=montant;
     return debitMaximalAutorise;
 }
 
 // --- get informations sur le compte

/** 
  * getNumeroDeCompte
  * @return le numero de compte 
  */ 
 public double getNumerodeCompte(){    
     return numeroDeCompte;     
 }
 
  /**
  * getTitulaire
  * @return nom, prenom et daresse du titulaire
  */
 public String getTitulaire(){    
     return titulaire.getInfosTitulaire();     
 }
 
  /** 
  * getSoldeDuCompte
  * @return le solde du compte
  */
 public double getSoldeDuCompte(){    
     return soldeDuCompte;     
 }
 /** 
  * getDebitMaximalAutorise
  * @return le debit maximal autorise pour ce compte
  */
 public double getDebitMaximalAutorise(){    
     return debitMaximalAutorise;     
 }
 
  /** 
  * getDecouvertMaximalAutorise
  * @return le debit maximal autorise pour ce compte
  */
 public double getDecouvertMaximalAutorise(){    
     return decouvertMaximalAutorise;     
 }
 
 
 /** 
  * situationCompte
  * test si le compte est a decouvert ou non
  * @return vrai si le compte est a decouvert faux sinon
  */
 public boolean situationCompte(){    
     return soldeDuCompte<0;     
 }
 
  /** 
  * debitAutorise
  * @return le montant du debit maximal autorise 
  * en fonction du solde et du debit maximal autorise du compte
  */
 
 public double getDebitAutorise(){    
     return Math.min(decouvertMaximalAutorise+soldeDuCompte,debitMaximalAutorise);     
 }
 
    public static void main(String[] args) {
        
        Client client1= new Client("TRUC", "Adrien", "Rue des fleurs");
        Compte compte1= new Compte(client1);
        client1.addCompte(compte1);
        compte1.crediter(2195.5);
       
        System.out.println("Debit max autorise:"+compte1.getDebitMaximalAutorise());
        System.out.println("Debit autorise:"+compte1.getDebitAutorise());
        System.out.println("Solde du compte:"+compte1.getSoldeDuCompte());
        System.out.println("Compte a decouvert?:"+compte1.situationCompte());
        System.out.println("Titulaire:"+compte1.getTitulaire());
        
        compte1.debiter(2000);
        System.out.println("Solde du compte:" +compte1.getSoldeDuCompte());
        System.out.println("Debit autorise:"+compte1.getDebitAutorise());
    }
}


