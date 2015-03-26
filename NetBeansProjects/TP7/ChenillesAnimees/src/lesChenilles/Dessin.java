package lesChenilles;

import java.awt.Graphics;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * Defini le contenu de la fenêtre de l'application d'animation des Chenille. Une zone
 * de dessin est un JPanel qui gère un liste d'objets Chenille. Lorsqu'il se réaffiche
 * l'objet Dessin redessinne les différents objets Chenille contenus dans cette liste.
 *
 * @author Philippe Genoud
 * @version
 */
public class Dessin extends JPanel {
 /**
     * stocke la liste des Chenille ayant été ajoutées à cette zone de
     * dessin.
     */
    private List<Chenille> listeDesChenilles = new ArrayList<Chenille>();
    
 /**
     * retourne la largeur de la zone de dessin.
     * @return la largeur.
     */
    public int getLargeur() {
        return getWidth();
    }
    
    /**
     * retourne la hauteur de la zone de dessin.
     * @return la hauteur.
     */
    public int getHauteur() {
        return getHeight();
    }
    
    /**
     * ajoute une Chenille à la zone de dessin.
     * @param c la Chenille à ajouter au Dessin
     * @see Chenille
     */
    public void ajouterObjet(Chenille v) {
        
        if (! listeDesChenilles.contains(v)) {
            // l'objet n'est pas déjà dans la liste
            // on le rajoute a la liste des objets du dessin
            listeDesChenilles.add(v);
			// fixe le dessin associé au visage rond v : c'est CE dessin
            v.setDessin(this);
            // le dessin se réaffiche
            repaint();
        }
    }
    
    /**
     * temporisation de l'animation.
     * @param durée delai de temporisation en ms.
     */
    public void pause(int duree) {
        try {
            Thread.sleep(duree);
        } catch (Exception e) {}
    }
    
    /**
     * affiche la zone de dessin et son contenu
     * @param g le contexte graphique
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
		// on dessine chacun des visages contenus dans la zone de dessin
		for (Chenille v : listeDesChenilles)
            v.dessiner(g);
    }    
    
} // Dessin
