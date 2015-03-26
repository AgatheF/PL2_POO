/** 
 *   Programme qui convertit une temperature saisie 
 *	au clavier en farenheit en degres
 */

import java.util.Scanner;

public class Degres {
	public static void main(String[] args){
      
		Scanner sc = new Scanner(System.in); //pour scanner des valeurs saisies au clavier

	   System.out.print("entrez une température en degres Fahrenheit: ");
		float tf = sc.nextFloat();
		float tc = 5.f/9.f*(tf-32.f); //conversion
	   System.out.println("cette temperature equivaut a: " + tc  + " degres celsius");  // println=remet a la ligne

   }
}
