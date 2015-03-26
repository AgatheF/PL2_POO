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
import ufrim2ag.m2pcci.pl2.tp8.dessin.Dessin;
import ufrim2ag.m2pcci.pl2.tp8.dessin.IDessinable;
import ufrim2ag.m2pcci.pl2.tp8.formes.Cercle;
import ufrim2ag.m2pcci.pl2.tp8.formes.Etoile;
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
     * @throws UnknownObjectException en cas de type d'objet non supporté
     * @throws UnknownFormeException en cas de type de forme non supporté
     * @throws NombreArgumentsIncorrect quand le nombre d'arguments d'une
     * description ne correspond pas au nombre d'arguments attendus.
     */
    public static void chargerDessinables(String fileName, Dessin dessin)
            throws FileNotFoundException, IOException, UnknownObjectException, UnknownFormeException,
            NombreArgumentsIncorrect {
        // Buffered reader ouvert et fermé automatiquement "à la fin du try"
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            // try avec ressources, qui permet de fermer automatiquement le reader

            String ligne; // chaîne contenant la ligne courante.
            int noLigne = 0;    // numéro de la ligne en cours d'analyse.
            IForme forme; // la dernière forme créée
            IFormeAnimator animator = null; // le dernier animateur lu

            while ((ligne = reader.readLine()) != null) {

                noLigne++;
                ligne = ligne.trim();
                System.out.println(ligne);
                if (!"".equals(ligne) && !ligne.startsWith("#")) {

                    // ligne non vide et non commentaire
                    // recupération dans un tableau de chaînes des différents élements de la ligne
                    String[] tokens = ligne.toUpperCase().split(" ");
                    try {
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
                                System.out.println("Animateur");
                                animator = lireAnimator(tokens[1], Arrays.copyOfRange(tokens, 2, tokens.length));
                                break;
                            case "C":
                                System.out.println("Chenille");
                                dessin.ajouterObjet(lireChenille(tokens));
                                break;
                            default:
                                throw new UnknownObjectException(tokens[0]); 
                        }
                    } 
                    // erreurs catchee sur nbArgs, Forme et Animateur inconnu et format de nbre
                    //envoyees dans les fonctions appelees dans ce main
                    catch (NombreArgumentsIncorrect | UnknownFormeException | UnknownAnimatorException | NumberFormatException e1) {
                        System.out.println(e1.getMessage()); // message nombre arguments incorrects ou typeForme non traité
                    }
                } // fin du if (! ligne.equals(""))
            }
        }
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
                return lireEtoile(paramsForme);
            case "CERCLE":
                return lireCercle(paramsForme);
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
        Color c = null;
        if (params.length == 7) {
            c = new Color(Integer.parseInt(params[4]), Integer.parseInt(params[5]),
                    Integer.parseInt(params[6]));
        }
        return new PolygoneRegulier(x, y, r, n, 1.0f, c, c);
    }

    private static Etoile lireEtoile(String[] params) throws NombreArgumentsIncorrect {
        if (params.length != 6) {
            throw new NombreArgumentsIncorrect("ETOILE", params.length, 6);
        }
        int x = Integer.parseInt(params[0]);  //param issus de la lecture du fichier convertit en int
        int y = Integer.parseInt(params[1]);
        int r = Integer.parseInt(params[2]);
        Color c = null;
        if (params.length == 6) {
            c = new Color(Integer.parseInt(params[3]), Integer.parseInt(params[4]),
                    Integer.parseInt(params[5])); //transforme les 3 derniers parametres en couleur c=color(RVB) 
        }
        return new Etoile(x, y, r, 1.0f, c, c);
    }

    private static Cercle lireCercle(String[] params) throws NombreArgumentsIncorrect {
        if (params.length != 6) {
            throw new NombreArgumentsIncorrect("CERCLE", params.length, 6);
        }
        int x = Integer.parseInt(params[0]);  //param issus de la lecture du fichier convertit en int
        int y = Integer.parseInt(params[1]);
        int r = Integer.parseInt(params[2]);
        Color c = null;
        if (params.length == 6) {
            c = new Color(Integer.parseInt(params[3]), Integer.parseInt(params[4]),
                    Integer.parseInt(params[5])); //transforme les 3 derniers parametres en couleur c=color(RVB) 
        }
        return new Cercle(x, y, r, 1.0f, c, c);
    }

    private static IFormeAnimator lireAnimator(String typeAnim, String[] params) throws
            NombreArgumentsIncorrect, UnknownAnimatorException {
        switch (typeAnim) {
            case "CIRCULAR":
                return lireAnimCirculaire(params);
            case "REBOUND":
                return lireAnimRebound(params);
            case "CAP":
                return lireAnimCap(params);
            default:
                throw new UnknownAnimatorException(typeAnim); // A MODIFIER ANIMATION PAS FORME
        }
        //To change body of generated methods, choose Tools | Templates.
    }

    private static IDessinable lireChenille(String[] tokens) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static CircularAnimator lireAnimCirculaire(String[] params) throws NombreArgumentsIncorrect {
        if (params.length != 5) {
            throw new NombreArgumentsIncorrect("CIRCULAIRE", params.length, 5);
        }
        int x = Integer.parseInt(params[0]);  //param issus de la lecture du fichier convertit en int
        int y = Integer.parseInt(params[1]);
        int r = Integer.parseInt(params[2]);
        int angle = Integer.parseInt(params[3]);
        int deltaAngle = Integer.parseInt(params[4]);
        return new CircularAnimator(x, y, r, angle, deltaAngle);
    }

private static ReboundAnimator lireAnimRebound(String[] params) throws NombreArgumentsIncorrect {
        if (params.length != 2) {
            throw new NombreArgumentsIncorrect("Rebound", params.length, 2);
        }
        int dx = Integer.parseInt(params[0]);  //param issus de la lecture du fichier convertit en int
        int dy = Integer.parseInt(params[1]);
        return new ReboundAnimator(dx, dy);
    }

    private static IFormeAnimator lireAnimCap(String[] params) throws NombreArgumentsIncorrect {
        if (params.length != 2) {
            throw new NombreArgumentsIncorrect("Cap", params.length, 2);
        }
        int cap = Integer.parseInt(params[0]);  //param issus de la lecture du fichier convertit en int
        int depElem = Integer.parseInt(params[1]);
        return new CapAnimator(cap, depElem);
    }
}    