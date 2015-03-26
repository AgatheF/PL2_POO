package ufrim2ag.m2pcci.pl2.tp8.dessin;

import ufrim2ag.m2pcci.pl2.tp8.animation.IAnimable;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

/**
 * Defini le contenu de la fenêtre de l'application d'animation des IDessinable.
 * Une zone de dessin est un JPanel qui gère un liste d'objets IDessinable.
 * Lorsqu'il se réaffiche l'objet Dessin redessinne les différents objets
 * IDessinable contenus dans cette liste.
 *
 * @author Philippe Genoud
 * @version
 */
public class Dessin extends JPanel {

    /**
     * stocke la liste des IDessinable ayant été ajoutées à cette zone de
     * dessin.
     */
    private final List<IDessinable> listeDesDessinables = new ArrayList<>();

    /**
     * retourne la largeur de la zone de dessin.
     *
     * @return la largeur.
     */
    public int getLargeur() {
        return getWidth();
    }

    /**
     * retourne la hauteur de la zone de dessin.
     *
     * @return la hauteur.
     */
    public int getHauteur() {
        return getHeight();
    }

    /**
     * ajoute un IDessinable à la zone de dessin.
     *
     * @param objDessinable le IDessinable à ajouter au Dessin
     * @see IDessinable
     */
    public void ajouterObjet(IDessinable objDessinable) {

        if (!listeDesDessinables.contains(objDessinable)) {
            // l'objet n'est pas déjà dans la liste
            // on le rajoute a la liste des objets du dessin
            listeDesDessinables.add(objDessinable);
            objDessinable.setDessin(this);
        }
    }

    /**
     * temporisation de l'animation.
     *
     * @param duree delai de temporisation en ms.
     */
    public void pause(int duree) {
        try {
            Thread.sleep(duree);
        } catch (InterruptedException e) {
        }
    }

    /**
     * affiche la zone de dessin et son contenu
     *
     * @param g le contexte graphique
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // dessiner les Objets que contient le dessin
        for (IDessinable objDessinable : listeDesDessinables) {
            objDessinable.dessiner(g);
        }
    }

    /**
     * fait effectuer un déplacement élémentaires oux objets du dessin qui sont
     * Animables
     */
    public void deplaceAnimables() {
        // depalcer les Objets Animables que contient le dessin
        for (IDessinable objDessinable : listeDesDessinables) {
            if (objDessinable instanceof IAnimable) {
                ((IAnimable) objDessinable).deplacer();
            }
        }
    }

} // Dessin
