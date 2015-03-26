package ufrim2ag.m2pcci.tp9;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Genération de jeux de données pour les tables PROGRAMMEURS et CONSOS_CAFE.
 *
 * @author Philippe Genoud - UJF Grenoble - Lab LIG-Steamer
 */
public class DataGenerator {

    final static Charset ENCODING = StandardCharsets.UTF_8;
    private static final int NB_PROGRAMMEURS = 30;
    private static final int NB_SEMAINES = 52;

    private static final String programmeur = "%d,NOM%d,PRENOM%d,%d";
    private static final String consos = "%d,%d,%d";

    /**
     * génère les consommations sur une année
     *
     * @param consoFileName le nom du fichier à créer
     * @throws IOException
     */
    public static void generateConso(String consoFileName) throws IOException {
        // generer les consomations
        Path path = Paths.get(consoFileName);
        try (BufferedWriter writer = Files.newBufferedWriter(path, ENCODING)) {
            writer.write("NO_SEMAINE:NUMBER,PROGRAMMEUR:NUMBER,NB_TASSES:NUMBER");
            writer.newLine();
            for (int s = 1; s <= NB_SEMAINES; s++) {
                for (int i = 0; i <= NB_PROGRAMMEURS; i++) {
                    String line = String.format(consos, s, i, nbCafes());
                    writer.write(line);
                    writer.newLine();
                }
            }
        }
    }

    /**
     * génère les données pour la table PROGRAMMEURS
     *
     * @param progsFileName le nom du fichier à créer
     * @throws IOException
     */
    public static void generateProgrammeurs(String progsFileName) throws IOException {
        Path path = Paths.get(progsFileName);
        try (BufferedWriter writer = Files.newBufferedWriter(path, ENCODING)) {
            writer.write("ID:NUMBER,NOM:VARCHAR2,PRENOM:VARCHAR2,BUREAU:NUMBER");
            writer.newLine();
            for (int i = 1; i <= NB_PROGRAMMEURS; i++) {
                String line = String.format(programmeur, i, i, i, noBureau());
                writer.write(line);
                writer.newLine();
            }
        }
    }

    public static int nbCafes() {
        return (int) (Math.random() * 30);
    }

    public static int noBureau() {
        return 200 + (int) (Math.random() * 100);
    }

    public static void main(String[] args) throws IOException {
        // generer les programmeurs
        generateProgrammeurs("progs.data");
        generateConso("consos.data");
    }

}
