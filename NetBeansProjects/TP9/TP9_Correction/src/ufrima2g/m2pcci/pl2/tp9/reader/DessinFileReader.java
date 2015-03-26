package ufrima2g.m2pcci.pl2.tp9.reader;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import ufrim2ag.m2pcci.pl2.tp8.animation.CapAnimator;
import ufrim2ag.m2pcci.pl2.tp8.animation.CircularAnimator;
import ufrim2ag.m2pcci.pl2.tp8.animation.FormeAnimee;
import ufrim2ag.m2pcci.pl2.tp8.animation.IFormeAnimator;
import ufrim2ag.m2pcci.pl2.tp8.animation.ReboundAnimator;
import ufrim2ag.m2pcci.pl2.tp8.chenille.ChenilleCouleur;
import ufrim2ag.m2pcci.pl2.tp8.dessin.Dessin;
import ufrim2ag.m2pcci.pl2.tp8.dessin.IDessinable;
import ufrim2ag.m2pcci.pl2.tp8.formes.Cercle;
import ufrim2ag.m2pcci.pl2.tp8.formes.Disque;
import ufrim2ag.m2pcci.pl2.tp8.formes.Etoile;
import ufrim2ag.m2pcci.pl2.tp8.formes.FormeCirculaire;
import ufrim2ag.m2pcci.pl2.tp8.formes.IForme;
import ufrim2ag.m2pcci.pl2.tp8.formes.PolygoneRegulier;

/**
 * Cette classe permet de lire un fichier texte contenant la description des
 * différents objets à afficher et à animer dans la fenêtre de l'application.
 *
 * @author Philippe Genoud - UJF Grenoble - Lab LIG-Steamer
 */
public class DessinFileReader {

    /**
     *
     * @param fileName le nom (en fait le chemin) du fichier texte
     * @param dessin le dessin auquel les objets créés à partir des descriptions
     * contenues dans le fichier sont ajoutés
     *
     * @throws FileNotFoundException si fileName ne correspond pas un fichier
     * existant
     * @throws IOException en cas d'erreur de lecture (au niveau système)
     * description ne correspond pas au nombre d'arguments attendus.
     */
    public static void chargerDessinables(String fileName, Dessin dessin)
            throws FileNotFoundException, IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            // try avec ressources, qui permet de fermer automatiquement le reader

            String ligne; // chaîne contenant la ligne courante.
            int noLigne = 0;    // numéro de la ligne en cours d'analyse.
            IForme forme; // la dernière forme créée
            IFormeAnimator animator = null; // le dernier animateur lu
            ligne = reader.readLine();
            while (ligne != null) {
                try {
                    noLigne++;
                    System.out.println(ligne);  // affiche la ligne
                    ligne = ligne.trim(); // enlève les espaces en début et fin de ligne
                    if (!"".equals(ligne) && !ligne.startsWith("#")) {
                        // ligne non vide et non commentaire
                        // recupération dans un tableau de chaînes des différents élements de la ligne
                        String[] tokens = ligne.toUpperCase().split("\\s+"); // la chaine est mise en
                                // majuscule et découpée selon les morceaux séparés par un ou plusieurs
                                // espace (expression régulière en paramètre de split)
                        switch (tokens[0]) {
                            case "F":
                                forme = lireForme(tokens[1], Arrays.copyOfRange(tokens, 2, tokens.length));
                                System.out.println("--> forme créée");
                                if (animator != null) {
                                    dessin.ajouterObjet(new FormeAnimee(forme, animator));
                                    animator = null;
                                } else {
                                    dessin.ajouterObjet(forme);
                                }
                                break;
                            case "A":
                                animator = lireAnimator(tokens[1], Arrays.copyOfRange(tokens, 2, tokens.length));
                                System.out.println("--> animateur créé");
                                break;
                            case "C":

                                dessin.ajouterObjet(lireChenille(dessin, Arrays.copyOfRange(tokens, 1, tokens.length), animator));
                                System.out.println("--> chenille créé");
                                break;
                            default:
                                throw new UnknownObjectException(tokens[0]);
                        } // fin du switch 
                    } // fin du if (! ligne.equals(""))
                } catch (UnknownObjectException | NombreArgumentsIncorrect | NumberFormatException ex) {
                    System.out.println("ERREUR: Ligne ignorée");
                    System.out.println(ex.getMessage());
                }
                // passage à la ligne suivante
                ligne = reader.readLine();
            } // fin du while
        } // fin du try avec resources
    }

    /**
     *
     * @param typeForme le type de la forme
     * @param paramsForme le tableau des paramètres de la forme
     *
     * @return la référence d'un objet forme correspondant à la description
     *
     * @throws UnknownFormeException si le type de forme n'est pas reconnu.
     *
     * @throws NombreArgumentsIncorrect si le nombre de paramètres de
     * description n'est pas le nombre attendu.
     */
    private static IForme lireForme(String typeForme, String[] paramsForme) throws
            UnknownFormeException, NombreArgumentsIncorrect {
        switch (typeForme) {
            case "POLYR":
                return lirePolyRegulier(paramsForme);
            case "ETOILE":
            case "DISQUE":
            case "CERCLE":
                return lireDescrCercle(typeForme,paramsForme);
            default:
                throw new UnknownFormeException(typeForme);
        }
    }

    /**
     *
     * @param params les paramètres permettant de décrire un polygone régulier
     *
     * @return la référence d'un objet PolygoneRegulier correspondant à ces
     * paramètres
     *
     * @throws NombreArgumentsIncorrect si le nombre de paramètres de
     * description n'est pas le nombre attendu (ici 4 ou 7).
     */
    private static PolygoneRegulier lirePolyRegulier(String[] params) throws NombreArgumentsIncorrect {
        if (params.length != 7) {
            throw new NombreArgumentsIncorrect("POLYR", params.length, 7);
        }
        int x = Integer.parseInt(params[0]);
        int y = Integer.parseInt(params[1]);
        int r = Integer.parseInt(params[2]);
        int n = Integer.parseInt(params[3]);
        Color c = new Color(Integer.parseInt(params[4]), Integer.parseInt(params[5]),
                Integer.parseInt(params[6]));
        return new PolygoneRegulier(x, y, r, n, 1.0f, c, c);
    }

    /**
     * construit une forme décrite à partir des paramètres lus dans un fichier de
     * descriptions, ces paramètres étant la description d'un cercle et d'une couleur.
     * La forme peut être un cercle, un disque ou une étoile
     * 
     * @param params les paramètre décrivant la forme (x, y, r, r, v, b)
     * @return la forme (Etoile, Disque ou Cercle)
     * @throws NombreArgumentsIncorrect si le nomnre de paramètres de
     * description ne correspond pas au nombre attendu.
     */
    private static FormeCirculaire lireDescrCercle(String typeForme, String[] params) throws NombreArgumentsIncorrect,
            UnknownFormeException {
        if (params.length != 6) {
            throw new NombreArgumentsIncorrect(typeForme, params.length, 6);
        }
        int x = Integer.parseInt(params[0]);
        int y = Integer.parseInt(params[1]);
        int r = Integer.parseInt(params[2]);
        Color c = new Color(Integer.parseInt(params[3]), Integer.parseInt(params[4]),
                Integer.parseInt(params[5]));
        switch (typeForme) {
            case "ETOILE":
                return new Etoile(x, y, r, 1.0f, c, c);
            case "CERCLE":
                return new Cercle(x, y, r, 1.0f, c, c);
            case "DISQUE":
                return new Disque(x, y, r, 1.0f, c, c);
            default: throw new UnknownFormeException(typeForme);
        }
    }

    //-------------------------------------------------------------------
    // gestion des animateurs
    //-------------------------------------------------------------------
    /**
     *
     * @param typeAnimator le type de l'animateur
     * @param paramsAnimator le tableau des paramètres de l'animateur
     *
     * @return la référence d'un objet animateur correspondant à la description
     *
     * @throws UnknownAnimatorException si le type d'animateur n'est pas
     * reconnu.
     *
     * @throws NombreArgumentsIncorrect si le nombre de paramètres de
     * description n'est pas le nombre attendu.
     */
    private static IFormeAnimator lireAnimator(String typeAnimator, String[] paramsAnimator)
            throws UnknownAnimatorException, NombreArgumentsIncorrect {
        switch (typeAnimator) {
            case "CAP":
                return lireCapAnimator(paramsAnimator);
            case "REB":
                return lireRectiligneAnimator(paramsAnimator);
            case "CIRC":
                return lireCircularAnimator(paramsAnimator);
            default:
                throw new UnknownAnimatorException(typeAnimator);
        }
    }

    /**
     * construit une animateur de type CapAnimator à partir des paramètres
     * définis dans un fichier de description textuel
     *
     * @param paramsAnimator les paramètre décrivant l'animateur
     * @return l'animateur
     * @throws NombreArgumentsIncorrect si le nomnre de paramètres de
     * description ne correspond pas au nombre attendu.
     */
    private static CapAnimator lireCapAnimator(String[] paramsAnimator)
            throws NombreArgumentsIncorrect {
        if (paramsAnimator.length != 2) {
            throw new NombreArgumentsIncorrect("CAP", paramsAnimator.length, 2);
        }
        return new CapAnimator(
                Double.parseDouble(paramsAnimator[0]),
                Integer.parseInt(paramsAnimator[1]));
    }

    /**
     * construit une animateur de type ReboundAnimator à partir des paramètres
     * définis dans un fichier de description textuel
     *
     * @param paramsAnimator les paramètre décrivant l'animateur
     * @return l'animateur
     * @throws NombreArgumentsIncorrect si le nomnre de paramètres de
     * description ne correspond pas au nombre attendu.
     */
    private static ReboundAnimator lireRectiligneAnimator(String[] paramsAnimator) throws NombreArgumentsIncorrect {
        if (paramsAnimator.length != 2) {
            throw new NombreArgumentsIncorrect("RECT", paramsAnimator.length, 2);
        }
        return new ReboundAnimator(
                Integer.parseInt(paramsAnimator[0]),
                Integer.parseInt(paramsAnimator[1]));
    }

    /**
     * construit une animateur de type CircularAnimator à partir des paramètres
     * définis dans un fichier de description textuel
     *
     * @param paramsAnimator les paramètre décrivant l'animateur
     * @return l'animateur
     * @throws NombreArgumentsIncorrect si le nomnre de paramètres de
     * description ne correspond pas au nombre attendu.
     */
    private static CircularAnimator lireCircularAnimator(String[] paramsAnimator) throws NombreArgumentsIncorrect {
        if (paramsAnimator.length != 5) {
            throw new NombreArgumentsIncorrect("CIRC", paramsAnimator.length, 5);
        }
        return new CircularAnimator(Integer.parseInt(paramsAnimator[0]), Integer.parseInt(paramsAnimator[1]),
                Integer.parseInt(paramsAnimator[2]), Integer.parseInt(paramsAnimator[3]), Integer.parseInt(paramsAnimator[4]));
    }

    //--------------------------------------------------------------------------
    // gestion des Chenilles
    //--------------------------------------------------------------------------
    /**
     * construit une a chenille à partir des paramètres définis dans un fichier
     * de description textuel
     *
     * @param d le dessin ou se trouve la chenille
     * @param params les paramètres décrivant la chenille
     * @return la chenille
     * @throws NombreArgumentsIncorrect si le nomnre de paramètres de
     * description ne correspond pas au nombre attendu.
     */
    private static IDessinable lireChenille(Dessin d, String[] params, IFormeAnimator animator)
            throws NombreArgumentsIncorrect {
        if (params.length != 5) {
            throw new NombreArgumentsIncorrect("CHENILLE", params.length, 5);
        }
        int r = Integer.parseInt(params[0]);
        int nbA = Integer.parseInt(params[1]);
        Color coul = new Color(Integer.parseInt(params[2]), Integer.parseInt(params[3]),
                Integer.parseInt(params[4]));
        return new ChenilleCouleur(coul, d, r, nbA, animator);
    }
}
