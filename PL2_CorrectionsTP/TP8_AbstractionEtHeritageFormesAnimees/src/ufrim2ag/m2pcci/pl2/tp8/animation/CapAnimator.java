package ufrim2ag.m2pcci.pl2.tp8.animation;

import ufrim2ag.m2pcci.pl2.tp8.dessin.Dessin;
import ufrim2ag.m2pcci.pl2.tp8.formes.IForme;

/**
 * animateur qui permet de déplacer une forme de manière aléatoire. Les
 * déplacements selon une distance donnée (deplacementElem) dans une direction
 * qui varie à chaque déplacement de manière aléatoire (la variation est un
 * nombre tiré au hasard qui correspond à une déviation de cap comprise dans
 * l'intervalle [-30°, + 30°[
 *
 * @author Philippe Genoud - UJF Grenoble - Lab LIG-Steamer
 */
public class CapAnimator implements IFormeAnimator {

    protected double cap;
    protected int deplacementElem;

    /**
     *
     * @param cap le cap initial
     * @param depElem la distance de déplacement élémentaire
     */
    public CapAnimator(double cap, int depElem) {
        this.cap = cap;
        this.deplacementElem = depElem;
    }

    /**
     * modifie le cap
     *
     * @param deltaC la valeur à ajouter au cap
     */
    private void devierCap(double deltaC) {
        cap += deltaC;
        cap = normalize(cap);
    }

    private double normalize(double d) {
        double res = Math.abs(d) % 360;
        if (d < 0) {
            if (res > 180) {
                res = 360 - res;
            } else {
                res = -res;
            }
        } else {
            if (res > 180) {
                res = -(360 - res);
            }
        }
        return res;
    }

    /**
     * modifie le point de référence de la forme de manière à ce que celui-ci
     * soit translaté d'une distance définie par deplacementElemen dans la
     * direction du cap.
     *
     * @param f la forme à déplacer
     */
    private void deplacerSelonCap(IForme f) {
        f.placerA((int) (f.getX() + deplacementElem * Math.cos(Math.PI * cap / 180)),
                (int) (f.getY() + deplacementElem * Math.sin(Math.PI * cap / 180)));
    }

    @Override
    public void deplacer(IForme f) {
        // calcule un nouveau cap qui garanti que la forme reste dans la zone
        // de dessin
        this.devierCap(-30.0 + Math.random() * 60.0);
        while (!capOK(f)) {
            this.devierCap(10);
        }
        // fait avancer la forme
        this.deplacerSelonCap(f);
    }

    /**
     * teste si le cap actuel garantit que prochain déplacement de la forme
     * selon son cap maintiendra celle-ci entièrement dans la zone de dessin.
     *
     * @param f la forme à deplacer
     *
     * @return true si la boite englobante après une translation de
     * deplacementElem dans la direction du cap est entièrement dans la zone de
     * dessin
     */
    public boolean capOK(IForme f) {
        Dessin d = f.getDessin();
        double demiLargeur = f.getBounds().getWidth() / 2;
        double demiHauteur = f.getBounds().getHeight() / 2;
        int x1 = (int) (f.getX() + deplacementElem * Math.cos(Math.PI * cap / 180));
        int y1 = (int) (f.getY() + deplacementElem * Math.sin(Math.PI * cap / 180));

        return x1 >= demiLargeur && x1 <= (d.getLargeur() - demiLargeur)
                && y1 >= demiHauteur & y1 <= (d.getHauteur() - demiHauteur);
    }

}
