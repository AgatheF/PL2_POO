package ufrim2ag.m2pcci.pl2.tp8.formes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;

/**
 * Une forme Circulaire dont les calculerSommets sont répartis de façon uniforme
 * sur le cercle où elle est inscrite. Une forme régulière peut être dessinée
 * avec ou sans remplissage. Il est possible de fixer la coulTrait du contour
 * différemment de la coulTrait de la forme.
 *
 * @author Philippe Genoud - UJF Grenoble - Lab LIG-Steamer
 */
public abstract class FormeCirculaireReguliere extends FormeCirculaire {

    /**
     * l'objet Path correspondant au contour de la forme régulière, exprimé dans
     * le repère dont l'origine est le centre du cercle où elle est inscrite.
     */
    protected Path2D path;

    /**
     * Une forme régulière avec coulTrait de remplissage et coulTrait de contour
     *
     * @param x l'abscisse du centre du cercle où est inscrite la forme et sur
     * lequel les calculerSommets du contour de la forme.
     * @param y l'ordonnée du centre du cercle où est inscrite la forme et sur
     * lequel les calculerSommets du contour de la forme.
     * @param r le rayon du cercle où est inscrite la forme et sur lequel les
     * calculerSommets du contour de la forme.
     * @param nbSommets le nombre de calculerSommets de la forme
     * @param epaisseurTrait epaisseur du trait matérialisant le contour
     * @param cTrait la coulTrait de remplissage
     * @param cRemp la coulTrait du trait de contour
     */
    public FormeCirculaireReguliere(int x, int y, int r, int nbSommets,
            float epaisseurTrait,
            Color cTrait, Color cRemp) {
        super(x, y, r, epaisseurTrait, cTrait, cRemp);
        path = creerPath(nbSommets);
    }

    /**
     * Une forme régulière sans coulTrait. La coulTrait utilisée sera celle du
     * contexte graphique (l'objet Graphics utilisé pour dessiner).
     *
     * @param x l'abscisse du centre du cercle où est inscrite la forme et sur
     * lequel les calculerSommets du contour de la forme.
     * @param y l'ordonnée du centre du cercle où est inscrite la forme et sur
     * lequel les calculerSommets du contour de la forme.
     * @param r le rayon du cercle où est inscrite la forme et sur lequel les
     * calculerSommets du contour de la forme.
     * @param nbSommets le nombre de calculerSommets de la forme
     */
    public FormeCirculaireReguliere(int x, int y, int r, int nbSommets) {
        this(x, y, r, nbSommets, 1.0f, null, null);
    }

    /**
     * crée l'objet Path correspondant au contour de la forme régulière.
     *
     * @param n le nombre de calculerSommets
     * @return l'objet Path matérialisant le contour de la forme.
     */
    private Path2D creerPath(int n) {
        return relierSommets(calculerSommets(n));
    }

    /**
     * calcule les sommets de la forme répartis uniformément sur le cercle de
     * centre (0,0)
     *
     * @param nbSommets le nombre de sommets de la forme
     * @return le tableau des différents points correspondants aux sommets de la
     * forme
     */
    private Point2D.Float[] calculerSommets(int nbSommets) {
        float deltaAngle = 360f / nbSommets;
        float angle = -90;
        Point2D.Float[] sommets = new Point2D.Float[nbSommets];
        for (int i = 0; i < nbSommets; i++) {
            sommets[i] = new Point2D.Float((float) Math.cos(Math.toRadians(angle)) * r,
                    (float) Math.sin(Math.toRadians(angle)) * r);
            angle += deltaAngle;
        }
        return sommets;
    }

    /**
     * crée l'objet Path correspondant au contour de la forme régulière.
     *
     * @param sommets les calculerSommets du polygone régulier où est inscrite
     * la forme
     * @return le contour de la forme
     */
    protected abstract Path2D relierSommets(Point2D.Float[] sommets);

    @Override
    public final void dessiner(Graphics g) {
        // on fait une copie du contexte graphique
        Graphics2D g2 = (Graphics2D) g.create();
        // on applique la transformation de coordonnées por placer
        // le centre du cercle en (x, y)
        g2.translate(x, y);

        // on dessine le contour de la forme
        // avec la couleur de trait spécifiée ou la couleur courante
        // du contexte graphique sinon
        if (coulTrait != null) {
            g2.setColor(coulTrait);

        }
        g2.setStroke(new BasicStroke(epaisseurTrait));
        g2.draw(path);

        // on dessine l'intérieur de la forme 
        if (coulRemplissage != null) {
            g2.setColor(coulRemplissage);
            g2.fill(path);
        }

    }
}
