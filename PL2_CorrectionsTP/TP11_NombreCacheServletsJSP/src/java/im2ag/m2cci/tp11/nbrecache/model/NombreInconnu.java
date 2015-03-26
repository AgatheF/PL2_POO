package im2ag.m2cci.tp11.nbrecache.model;

/**
 * Un nombre inconnu est un nombre tiré au hasard dans un intervalle fixé et 
 * que l'on peut essayer de deviner avec un nombre d'essais limité.
 * 
 * @author Philippe Genoud (Philippe.Genoud@imag.fr)
 */
public class NombreInconnu {

    /**
     * la valeur du nombre inconnu
     */
    private int valeur;
    /**
     * la valeur du nombre maximum d'essais autorisés.
     */
    private final int nbEssaisMax;
    /**
     * le nombre d'essais qu'il reste pour découvrir le nombre inconnu.
     */
    private int nbEssaisRestant;
    /**
     * la borne supérieure de l'intervalle dans lequel se trouve le nombre
     * inconnu.
     */
    private final int borneMax;
    /**
     * la borne inférieure de l'intervalle dans lequel se trouve le
     * nombre inconnu.
     */
    private final int borneMin;

    /**
     * création d'un nouveau nombre inconnu. Le nombre est tiré au hasard entre 
     * deux bornes d'un intervalle.
     * 
     * @param nbEssaisMax nombre maximum d'essais autorisé pour découvrir le nombre
     * @param borneMin borne inférieure de l'intervalle dans lequel se situe le nombre inconnu
     * @param borneMax borne supérieure de l'intervalle dans lequel se situe le nombre inconnu
     */
    public NombreInconnu(int nbEssaisMax, int borneMin, int borneMax) {
        this.nbEssaisMax = nbEssaisMax;
        this.borneMax = borneMax;
        this.borneMin = borneMin;
        reset();
    }

    /**
     * teste une valeur par rapport au nombre inconnu. Le nombre d'essais restant
     * est décrémenté.
     * 
     * @param nb la valeur à tester
     * @return  la valeur retournée est  :
     *          <ul>
     *            <li>0  si le nombre proposé est égal au nombre inconnu<,/li>
     *        <li>un nombre > 0 si le nombre inconnu est plus grand que le nombre
     *          proposé,</li>
     *            <li>un nombre > 0 si le nombre inconnu est plus petit que le nombre
     *          proposé.</li>
     *          </ul>
     * @throws ValeurIncorrecteException si la valeur soumise n'est pas dans
     *         l'intervalle autorisé
     * @throws NbreEssaisDepasseException  si le nombre d'essais déjà effectué 
     *         dépasse le nombre maximum autorisé
     */
    public int testerValeur(int nb) throws ValeurIncorrecteException, NbreEssaisDepasseException {
        if (nb < borneMin || nb > borneMax) {
            throw new ValeurIncorrecteException("" + nb + " n'est pas dans les bornes autorisées");
        }
        if (nbEssaisRestant == 0) {
            throw new NbreEssaisDepasseException("nombre maximum d'essais autorisé atteint");
        }
        nbEssaisRestant--;
        return valeur - nb;
    }

    /**
     * retourne la borne supérieure de l'intervalle dans lequel se trouve le
     * nombre inconnu.
     * @return valeur de la borne supérieure
     */
    public int getBorneMax() {
        return borneMax;
    }

    /**
     * retourne la borne inférieure de l'intervalle dans lequel se trouve le
     * nombre inconnu.
     * @return valeur de la borne inférieure
     */
    public int getBorneMin() {
        return borneMin;
    }

    /**
     * retourne le nombre d'essais qu'il reste pour découvrir le nombre 
     * inconnu.
     * @return le nombre d'essais
     */
    public int getNbEssaisRestant() {
        return nbEssaisRestant;
    }
    
    /**
     * retourne le nombre d'essais qui ont été joués
     * @return le nombre d'essais
     */
    public int getNbEssaisJoues() {
        return this.nbEssaisMax - nbEssaisRestant;
    }

    /**
     * retourne la valeur du nombre inconnu
     * @return la valeur du nombre inconnu
     */
    public int getValeur() {
        return valeur;
    }

    /**
     * retourne la valeur du nombre maximum d'essais autorisés pour découvrir le
     * nombre.
     * @return la valeur du nombre maximum d'essais autorisés
     */
    public int getNbEssaisMax() {
        return nbEssaisMax;
    }

    /**
     * réinitialise le nombre inconnu en tirant un nouvelle valeur au hasard
     * et réinitialise le nombre d'essais restant au nombre d'essais maximum
     * autorisés.
     */
    public final void reset() {
        nbEssaisRestant = nbEssaisMax;
        valeur = (int) Math.round(Math.random() * (borneMax - borneMin) + borneMin);
    }
}
