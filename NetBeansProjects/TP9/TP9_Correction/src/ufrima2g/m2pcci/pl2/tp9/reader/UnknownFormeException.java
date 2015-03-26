package ufrima2g.m2pcci.pl2.tp9.reader;

/**
 *
 * @author Philippe Genoud - UJF Grenoble - Lab LIG-Steamer
 */
public class UnknownFormeException extends UnknownObjectException {

    public UnknownFormeException(String formeType) {
        super(formeType + " est un type de forme non supporté");
    }
}
