package Formes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import Dessin.Dessin;
import Dessin.IDessinable;

/**
 *
 * @author
 */
abstract public class FormeCirculaire extends AbstractForme{

    //-------------- variables d'instance (attributs)--------------------
    /**
     * abscisse du centre du cercle
     */
    protected int x;

    /**
     * ordonnée du centre du cercle
     */
    protected int y;

    /**
     * rayon du cercle
     */
    protected int r;
    
    /**
     * dessin
     */
    public Dessin dess;
    
    /**
     * contour du cercle
     */
    protected Path2D path;
    
       /**
     * ep du contour
     */
    protected float epTrait;
       /**
     * couleur du contour
     */
    protected Color cTrait;
           /**
     * couleur du remplissage
     */
    protected Color cRemplissage;

    
     //--------------   Constructeurs  --------------------
    protected FormeCirculaire(int x, int y, int r) {
        super(x, y, 1.f, null, null);
        this.r = r;
    }
    
     protected FormeCirculaire(int x, int y, int r, float ep, Color cTrait, Color cRemplissage) {
      this(x,y,r);
      this.epTrait=ep;
      this.cTrait=cTrait;
      this.cRemplissage=cRemplissage;
    }

    //-------------- Méthodes--------------------
    
    /**
     * setDessin fixe le dessin dans lequel on affiche les formes
     * @param d 
     */
    @Override
    public void setDessin(Dessin d) {
        this.dess = d;
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
    public void dessiner(Graphics g) {
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
