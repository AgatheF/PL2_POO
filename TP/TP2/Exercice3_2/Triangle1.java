/** 
*   Programme  qui affiche x lignes 
*   de 1 à x caracteres etoiles 
*	 Avec boucles while
*/

import java.util.Scanner;

public class Triangle1{

	public static void main(String[] args){
	
		Scanner sc = new Scanner(System.in);
	
		// taille du motif saisie par l'utilisateur
		int tailleMotif;
		System.out.print("Donnez la taille du motif: ");
		tailleMotif=sc.nextInt(); 
	
		
		int i=1;
		while (i <= tailleMotif){
			int j=1;
			while (j<=i){
			System.out.print("*");
			j++;
			}
			System.out.print("\n");
			i++;
		}
	}
}
