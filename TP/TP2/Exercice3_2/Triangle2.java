/** 
*   Programme  qui affiche x lignes 
*   de 1 à x caracteres etoiles 
*	Avec boucles for
*/

import java.util.Scanner;

public class Triangle2{

	public static void main(String[] args){
	
		Scanner sc = new Scanner(System.in);
	
		// taille du motif saisie par l'utilisateur
		int tailleMotif;
		int i,j;
		System.out.print("Donnez la taille du motif: ");
		tailleMotif=sc.nextInt(); 
	
		for (i=1; i<=tailleMotif; i++){

			for (j=1; j<=i; j++){
			System.out.print("*");
			}
			System.out.print("\n");
		}
	}
}
