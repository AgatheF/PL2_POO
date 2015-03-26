package ufrim2ag.m2pcci.pl2.tp8.animation;

import ufrim2ag.m2pcci.pl2.tp8.dessin.Dessin;
import ufrim2ag.m2pcci.pl2.tp8.formes.IForme;

/**
 * Interface definissant les méthodes que doit posséder un objet pour pouvoir 
 être deplacer des formes.
 * 
 * @author Philippe Genoud - UJF Grenoble - Lab LIG-Steamer
 */
public interface IFormeAnimator {

    /**
     * anime une forme, c'est à dire fait effectuer à celle-ci un déplacement
     * élémentaire dans le dessin où elle se situe.
     * @param f la forme à déplacer
     */
    void deplacer(IForme f);
}
