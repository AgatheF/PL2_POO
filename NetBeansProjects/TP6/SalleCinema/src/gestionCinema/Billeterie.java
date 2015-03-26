/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionCinema;

/**
 *
 * @author agathe
 */
import java.util.Scanner;
import javax.swing.JComboBox;

public class Billeterie {

    public static void main(String[] args) {

        // Creation des objets Salle dans un tableau
        SalleCinema[] lesSallesDuPate;
        lesSallesDuPate = new SalleCinema[4];

        lesSallesDuPate[0] = new SalleCinema("Le nom des gens", 220, 5.80);
        lesSallesDuPate[1] = new SalleCinema("Le pere noel est une ordure", 250, 5.80);
        lesSallesDuPate[2] = new SalleCinema("The lady", 220, 5.8);
        lesSallesDuPate[3] = new SalleCinema("Le roi lion 8", 180, 5.0);

        // vente avec menu deroulant a voir JComboBox et Jframe pour la fenetre
//        JComboBox listeNbPlaces = new JComboBox();
//        for (int i = 0; i < 10; i++) {
//            listeNbPlaces.addItem(i+1);
//        } 
        //Application pour les ventes
        Scanner sc = new Scanner(System.in);
        boolean venteTerminee = false;
        int nbPlacesNormal = 0;
        int nbPlacesReduit = 0;
        int numeroSalle;
        double montantAPayer;

        System.out.println("Entrer le numero de salle concernee par la vente ");
        numeroSalle = sc.nextInt();
        while (!venteTerminee) {

            if (numeroSalle <= lesSallesDuPate.length) {
                System.out.println("Combien de place au tarif normal ");
                nbPlacesNormal = sc.nextInt();
                
                System.out.println("Combien de place au tarif reduit ");
                nbPlacesReduit = sc.nextInt();

                // actualisation des ventes pour la salle et calcul du montant a payer
                lesSallesDuPate[numeroSalle-1].vendrePlaces(nbPlacesNormal, false);
                lesSallesDuPate[numeroSalle-1].vendrePlaces(nbPlacesReduit, true);
                montantAPayer=lesSallesDuPate[numeroSalle-1].prixNormal*(nbPlacesNormal+nbPlacesReduit*0.8);
                
                System.out.println("La vente est t-elle terminee? Tapez o ou n");
                
                boolean saisieOk=false;
                while (!saisieOk){
                    if ("o".equals(sc.next())) {
                        saisieOk=true;
                        venteTerminee = true;
                        System.out.println("\n\nLe montant total a payer est de "+montantAPayer+" €. Bon film!\n\n");
                    }
                    else if ("n".equals(sc.next())) {
                        saisieOk=true;
                        System.out.println("Entrer le numero de salle concernee par la vente ");
                        numeroSalle = sc.nextInt();
                        }
                    else {
                        System.out.println("Veuillez saisir o si la vente est terminee et n si non");
                    }
                }
            } 
            else {
                System.out.println("Le numero de salle est incorrect.\n Veuillez saisir a nouveau le numero de salle");
                numeroSalle = sc.nextInt();
            }
        }
        // affichage de l etat des salles: chiffres d affaire et nombre de places restantes
        
        System.out.println("\n"+lesSallesDuPate[numeroSalle-1].titreFilm);
        System.out.println("Nombre de places vendues au tarif normal: "+lesSallesDuPate[numeroSalle-1].nbPlacesVenduesNormal);
        System.out.println("Nombre de places vendues au tarif reduit: "+lesSallesDuPate[numeroSalle-1].nbPlacesVenduesReduit);
        System.out.println("Nombre de places restantes: "+lesSallesDuPate[numeroSalle-1].nbPlacesDisponibles());
        System.out.println("Chiffre d'affaire de la salle:" + lesSallesDuPate[numeroSalle-1].chiffreAffaires()+" €");
            
        for (int i = 0; i < lesSallesDuPate.length; i++) {
            System.out.println("\n"+lesSallesDuPate[i].titreFilm);
            System.out.println("Nombre de places restantes: "+lesSallesDuPate[i].nbPlacesDisponibles());
            
        }
        //System.out.println(lesSallesDuPate[numeroSalle-1].toString());
        
    }
}

