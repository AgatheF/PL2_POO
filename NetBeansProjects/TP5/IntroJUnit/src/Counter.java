/**
 * @author Philippe Genoud (Philippe.Genoud@imag.fr)
 *
 * D�finit un compteur entier avec des op�rations permettant de l'incr�menter
 * et de le d�cr�menter. Ce classe a pour but de permettre aux �tudiants
 * d'exp�rimenter avec JUnit.
 */
public class Counter {

        /**
         * la valeur du compteur
         */
	private int count;
	
        /**
         * cr�e un compteur initialis� � 0
         */
	public Counter() {
		count = 0;
	}
        
        /**
         * cr�e un compteur en sp�cifiant une valeur initiale
         * @param c la valeur initiale du compteur
         */
	public Counter(int c){
		count = c;
	}
	
	/**
         * incremente le compteur et retourne sa nouvelle valeur.
         * @return la nouvelle valeur du compteur apr�s incr�mentation
         */
        public int increment() {
		return ++count;  // count++ = erreur car retourne la valeur puis incrémente
	}
	
        /**
         * d�cr�mente le compteur et retourne sa nouvelle valeur
         * @return la nouvelle valeur du compteur apr�s d�cr�mentation
         */
	public int decrement() {
		return --count;
	}
	
        /**
         * retourne la valeur du compteur
         * @return la valeur du compteur
         */
	public int getCount() {
		return count;
	}
	
        /**
         * Cr�ation d'un nouveau compteur dont la valeur est la somme
         * de la valeur de ce compteur avec celle du compteur pass� en param�tre
         * @param le compteur � ajouter � ce compteur 
         * @return le compteur cr�� 
         */
	public Counter add(Counter c) {
		return new Counter(this.count + c.count);
		
	}
	
        /**
         * Cr�ation d'un nouveau compteur dont la valeur est la diff�rence
         * entre la valeur de ce compteur et celle du compteur pass� en param�tre
         * @param le compteur � soustraire � ce compteur
         * @return le compteur cr�� 
         */
	public Counter sub(Counter c) {
		return new Counter(this.count - c.count);
		
	}
        
}
