package ufrima2g.m2pcci.pl2.tp9.reader;

/**
 *
 * @author Philippe Genoud - UJF Grenoble - Lab LIG-Steamer
 */
public class UnknownAnimatorException extends UnknownObjectException {

    public UnknownAnimatorException(String formeType) {
        super(formeType + " est un type d'animateur non connu");
    }
}
