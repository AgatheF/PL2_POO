/** 
*   Programme  qui lit au clavier une suite de nombres réels positifs ou nuls (correspondant *	à des notes), terminée par la valeur -1, et calcule la moyenne olympique de ces valeurs, *	c'est à dire la moyenne des notes sans prendre en compte la note la plus élevée ni la note *	la moins élevée.	
*/

import java.util.Scanner;

public class MoyenneOlympique {
	public static void main(String[] args){
	
	Scanner sc =  new Scanner(System.in);
	
   double noteCourante; // attention! declarer noteCourante en dehors de la boucle sinon pas reconnue dans le while
	double noteMin=10.0; // initialisation max et min
	double noteMax=-1.0;
	double sommeNote=0.0;
	int nombreNote=0;
	double moyenne;
	double sommeOlympique;
	
	// pour premier test du while
	System.out.print("entrez une note>= 0 ou -1 pour arreter: ");
	noteCourante = sc.nextDouble();

	while(noteCourante != -1.0){
		nombreNote=nombreNote+1;
		sommeNote=sommeNote+noteCourante;

		if (noteCourante<noteMin){
			noteMin=noteCourante;
		}
		else if (noteCourante>noteMax){
			noteMax=noteCourante;
		}

		// pour tester noteCourante suivante
		System.out.print("entrez une note>= 0 ou -1 pour arreter: ");
		noteCourante = sc.nextDouble();
	}
	sommeOlympique=sommeNote-noteMin-noteMax;
	moyenne=sommeOlympique/(nombreNote-2);
	System.out.println("La moyenne olympique est de: " + moyenne);
	System.out.println("La somme olympique des notes est: " + sommeOlympique);
	System.out.println("Le nombre de note pour la moyenne olympique est: " + (nombreNote-2));
	
	}
}


