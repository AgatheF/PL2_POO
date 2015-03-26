package ufrima2g.m2pcci.pl2.tp9.reader;

/**
 *
 * @author Philippe Genoud - UJF Grenoble - Lab LIG-Steamer
 */
public class UnknownAnimatorException extends DessinFileReaderException {

    public UnknownAnimatorException(String animType) {
        super(animType + " est un type d'animateur non supporté");
    }
}
