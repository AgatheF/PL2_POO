package ufrim2ag.m2pcci.pl2.tp4;

/**
 * Ce programme évalue les performances de trois méthodes d'intersection2 :
 - intersection2 qui crée un ensemble résultat vide et ensuite modifie
   directement son tableau de booleens
 - intersection21 qui concatène des chaines (String) pour construire la chaîne
   contenant toutes les lettres de l'ensemble et ensuite créer l'ensemble
   résultat à partir de celle-ci.
 - intersection23 qui construit comme intersection21 la chaîne
   contenant toutes les lettres de l'ensemble et ensuite créer l'ensemble
   résultat à partir de celle-ci. Mais au lieu d'utiliser des objets de type
   String intersection23 utilise des objets de type StringBuilder
 * 
 * @author Philippe.Genoud@imag.fr
 */
public class CompareIntersectionMethods {

    public static void main(String[] args) {
        EnsembleDeLettres1 e1 = new EnsembleDeLettres1("abcdefghijklmnopqrstuvwxyz");
        EnsembleDeLettres1 e2 = new EnsembleDeLettres1("abcdefghijklmnopqrstuvwxyz");
        EnsembleDeLettres1 e3 = null;

        int nbIter = 1000000;
        if (args.length != 0)
            nbIter = Integer.parseInt(args[0]);

        System.out.println("Nombre d'itérations " + nbIter);
        
        long t1 = System.currentTimeMillis();
        for (int i = 0; i < nbIter; i++) {
            e3 = e1.intersection1(e2);
        }
        long t2 = System.currentTimeMillis();

        System.out.println("durée intersection1 (String)" + (t2 - t1) + " ms  " + (t2 - t1) / 1000.0 + " s");

        t1 = System.currentTimeMillis();
        
        for (int i = 0; i < nbIter; i++) {
            e3 = e1.intersection1(e2);
        }
        t2 = System.currentTimeMillis();

        System.out.println("durée intersection2 (tableau) " + (t2 - t1) + " ms  " + (t2 - t1) / 1000.0 + " s");

        t1 = System.currentTimeMillis();
        e3 = null;
        for (int i = 0; i < nbIter; i++) {
            e3 = e1.intersection3(e2);
        }
        t2 = System.currentTimeMillis();

        System.out.println("durée intersection3 (StringBuilder)" + (t2 - t1) + " ms  " + (t2 - t1) / 1000.0 + " s");
    }
}
