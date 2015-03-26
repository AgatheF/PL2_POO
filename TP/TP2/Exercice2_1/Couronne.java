/** 
 *   Programme qui determine si un point donne par ces
 *	  coordonees x et y est place a l'int d'une couronne
 *   de rayon int et ext donnes et de centre (0,0) 	
 */

import java.util.Scanner;

public class Couronne {
	public static void main(String[] args){
      
		Scanner sc = new Scanner(System.in); //pour scanner des valeurs saisies au clavier

		// Saisie des rayons de la couronne
	   System.out.print("entrez un rayon exterieur: ");
		int rExt = sc.nextInt();
		System.out.print("entrez un rayon interieur: ");
		int rInt = sc.nextInt();
		// Saisie des coordonnees des points 
		System.out.print("entrez l'abscisse du point x: ");
		int x = sc.nextInt();
		System.out.print("entrez l'ordonnee du point y: ");
		int y = sc.nextInt();
		// calcul de la distance de l'origine au point P
		double dp=Math.sqrt(Math.pow(x,2)+Math.pow(y,2)); 
		// si rint<dp<rext alors le point est dans la couronne
		if (dp>rInt && dp<rExt) 
			System.out.print("Bien visé! Le point est dans la couronne\n");
		else
			System.out.print("Dommage ... Le point n'est pas dans la couronne\n");
   }
}
