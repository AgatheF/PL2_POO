package ufrim2ag.m2pcci.pl2.tp8.animation;

import ufrim2ag.m2pcci.pl2.tp8.dessin.IDessinable;

/**
 * Interface definissant les méthodes que doit posséder un objet pour pouvoir 
 * être animé.
 * @author Philippe Genoud - UJF Grenoble - Lab LIG-Steamer
 */
public interface IAnimable extends IDessinable{
    /**
     * fait faire à l'objet un déplacement élémentaire
     */
    void deplacer();
}
