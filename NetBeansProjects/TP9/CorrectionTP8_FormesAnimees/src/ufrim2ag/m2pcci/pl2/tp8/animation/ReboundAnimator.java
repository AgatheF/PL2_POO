package ufrim2ag.m2pcci.pl2.tp8.animation;

import ufrim2ag.m2pcci.pl2.tp8.dessin.Dessin;
import ufrim2ag.m2pcci.pl2.tp8.formes.IForme;

/**
 *
 * @author Philippe Genoud - UJF Grenoble - Lab LIG-Steamer
 */
public class ReboundAnimator implements IFormeAnimator {

    /**
     * déplacement élémentaire horizontal de la forme. Par défaut 5 pixels.
     */
    private int dx = 5;

    /**
     * deplacement élémentaire vertical de la forme . Par défaut 5 pixels.
     */
    private int dy = 5;

    public ReboundAnimator(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Donne la valeur du déplacement élémentaire horizontal.
     *
     * @return valeur de dx, déplacement élémentaire horizontal.
     */
    public int getDx() {
        return dx;
    }

    /**
     * Fixe déplacement élémentaire horizontal.
     *
     * @param v Valeur à affecter à dx, déplacement élémentaire horizontal.
     */
    public void setDx(int v) {
        this.dx = v;
    }

    public int getDy() {
        return dy;
    }

    public void setDy(int v) {
        this.dy = v;
    }

    /**
     * Inverse sens du déplacement horizontal.
     */
    public void inverserDx() {
        dx = -dx;
    }

    /**
     * Inverse sens du déplacement vertical.
     */
    public void inverserDy() {
        dy = -dy;
    }

    /**
     * Inverse sens des déplacements horizontal et vertical.
     */
    public void inverserDxEtDy() {
        dx = -dx;
        dy = -dy;
    }

    /**
     * Fait effectuer à la forme un déplacement élementaire. La position du
     * point de référence la forme est modifiée en lui ajoutant le déplacement
     * élémentaire défini par dx et dy. Si la boite englobante de le forme
     * dépasse de l'un des bords de la zone de dessin sa direction de
     * déplacement est inversée.
     *
     * @param f
     */
    @Override
    public void deplacer(IForme f) {
        Dessin d = f.getDessin();
        if (f.getBounds().getX() < 0 || f.getBounds().getX() + f.getBounds().getWidth() > d.getLargeur()) {
            // le bord gauche ou le bord droit est atteint
            inverserDx();
        }
        if (f.getBounds().getY() < 0 || f.getBounds().getY() + f.getBounds().getHeight() > d.getHauteur()) {
            // le bord haut ou le bord bas est atteint
            inverserDy();
        }
        f.placerA(f.getX() + dx, f.getY() + dy);
    }
}
