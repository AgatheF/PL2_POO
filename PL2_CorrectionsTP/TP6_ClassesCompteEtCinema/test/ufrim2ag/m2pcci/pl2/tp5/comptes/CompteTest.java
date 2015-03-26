package ufrim2ag.m2pcci.pl2.tp5.comptes;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests unitaires de la classe Compte
 * @author Philipe Genoud (Philippe.Genoud@imag.fr)
 */
public class CompteTest {

    // le compte utilisé dans tous les tests
    private Compte c1;

    public CompteTest() {
    }

    /**
     * methode effectuée avec chaque test. crée un objet Compte référencé par c1
     * avec les caractéristiques suivantes :
     * - propriétaire : Jean DUPONT de Grenoble
     * - solde initial : 1000 €
     * - découvert max autorisé: 500 €
     * - débit max autorisé : 700 €
     */
    @Before
    public void setUp() {
        c1 = new Compte(new Personne("DUPONT", "Jean", "GRENOBLE"), 1000, 500, 700);
    }


    /**
     * Test of getNumero method, of class Compte.
     */
    @Test
    public void testGetNumero() {
        System.out.println("getNumero");
        int numC1 = c1.getNumero();
        // comme on ne sait pas dans quel ordre vont être faits les tests
        // et combien de fais l'appel à new Compte aura été effectué on ne
        // peut pas tester la valeur de ce numero. Par contre on va tester que
        // si on crée un nouveau compte celui-ci aura bien un numéro obtenu
        // en incrémentant le numéro du dernier compte créé (et ceci pour
        // chacun des constructeurs de la classe Compte
        Compte c2 = new Compte(null);
        assertEquals("numero de compte incorrect", numC1 + 1, c2.getNumero());
        c2 = new Compte(null, 50000);
        assertEquals("numero de compte incorrect", numC1 + 2, c2.getNumero());
        c2 = new Compte(null, 45000, 5000, 3000);
        assertEquals("numero de compte incorrect", numC1 + 3, c2.getNumero());
    }

    /**
     * Test of getTitulaire method, of class Compte.
     */
    @Test
    public void testGetTitulaire() {
        System.out.println("getTitulaire");
        Personne  tit = c1.getTitulaire();
        assertEquals("nom incorrect" , "DUPONT", tit.getNom());
        assertEquals("prenom incorrect" , "Jean", tit.getPrenom());
        assertEquals("adresse incorrecte" , "GRENOBLE", tit.getAdresse());
    }

    /**
     * Test of getSolde method, of class Compte.
     */
    @Test
    public void testGetSolde() {
        System.out.println("getSolde");
        assertEquals("solde incorrect", 1000,c1.getSolde(), 0.01);
    }

    /**
     * Test of getDecouvertMax method, of class Compte.
     */
    @Test
    public void testGetDecouvertMax() {
        System.out.println("getDecouvertMax");
        assertEquals("decouvert max incorrect",500, c1.getDecouvertMax(), 0.01);
    }

    /**
     * Test of getDebitMax method, of class Compte.
     */
    @Test
    public void testGetDebitMax() {
        System.out.println("getDebitMax");
        assertEquals("debit max incorrect",700, c1.getDebitMax(), 0.01);
    }

    /**
     * Test of setDecouvertMax method, of class Compte.
     */
    @Test
    public void testSetDecouvertMax() {
        System.out.println("setDecouvertMax");
        // verifie que lorsque le paramètre est correct la méthode
        // setDecouvertMax retourne bien la valeur true, et que le decouvert
        // autorisé a été correctement modifié.
        assertTrue(c1.setDecouvertMax(300));
        assertEquals("decouvert max incorrect",300,c1.getDecouvertMax(),0.01);
        // verifie que lorsque le paramètre est incorrect la méthode
        // setDecouvertMax retourne bien la valeur false, et que le decouvert
        // autorisé n'a pas été modifié.
        assertFalse(c1.setDecouvertMax(-300));
        assertEquals("decouvert max incorrect",300,c1.getDecouvertMax(),0.01);
    }

    /**
     * Test of setDebitMax method, of class Compte.
     */
    @Test
    public void testSetDebitMax() {
        System.out.println("setDebitMax");
        // verifie que lorsque le paramètre est correct la méthode
        // setdebitMax retourne bien la valeur true, et que le débit
        // a été correctement modifié.
        assertTrue(c1.setDebitMax(300));
        assertEquals("debit max incorrect",300,c1.getDebitMax(),0.01);
        // verifie que lorsque le paramètre est incorrect la méthode
        // setdebitMax retourne bien la valeur false, et que le débit
        // n'a pas été modifié.
        assertFalse(c1.setDebitMax(-300));
        assertEquals("debit max incorrect",300,c1.getDebitMax(),0.01);
    }

    /**
     * Test of estADecouvert method, of class Compte.
     */
    @Test
    public void testEstADecouvert() {
        assertFalse("le compte est à découvert", c1.estADecouvert());
        c1.debiter(700);
        c1.debiter(700);
        assertTrue("le compte n'est pas à découvert", c1.estADecouvert());
    }

    /**
     * Test of leDecouvert method, of class Compte.
     */
    @Test
    public void testLeDecouvert() {
        c1.debiter(700);
        assertEquals("découvert incorrect", 0, c1.leDecouvert(), 0.01);
        c1.debiter(700);
        assertEquals("découvert incorrect", 400, c1.leDecouvert(), 0.01);
        c1.crediter(300);
        assertEquals("découvert incorrect", 100, c1.leDecouvert(), 0.01);
        c1.crediter(200);
        assertEquals("découvert incorrect", 0, c1.leDecouvert(), 0.01);
    }

    /**
     * Test of crediter method, of class Compte.
     */
    @Test
    public void testCrediter() {
        System.out.println("crediter");
        // on verifie que si le compte est crédité d'une somme positive
        // son solde a bien été incrémenté 
        c1.crediter(400);
        assertEquals("solde incorrect", 1400, c1.getSolde(), 0.01);
        // on verifie que si on credite avec une valeur négative le solde
        // reste inchangé
        c1.crediter(-400);
        assertEquals("solde incorrect", 1400, c1.getSolde(), 0.01);
    }

    /**
     * Test of debiter method, of class Compte.
     */
    @Test
    public void testDebiter() {
        System.out.println("debiter");
        // on verifie que si le compte est débité d'une somme positive
        // inférieure au debit max et que le decouvert max autorisé n'est
        // pas dépassé, le solde du code est correctement décrémenté
        c1.debiter(400);
        assertEquals("solde incorrect", 600, c1.getSolde(), 0.01);
        c1.debiter(700);
        assertEquals("solde incorrect", -100, c1.getSolde(), 0.01);
        // on verifie que si on essaye de débiter le compte avec une somme
        // plus grande que le debit max autorisé, l'opération n'est pas effectuée
        // et que le solde reste inchangé
        c1.debiter(900);
        assertEquals("solde incorrect", -100, c1.getSolde(), 0.01);
        // on verifie que si on essaye de débiter le compte avec une somme
        // qui ferait dépasser le decouvert max autorisé, l'opération n'est pas effectuée
        // et que le solde reste inchangé
        c1.debiter(500);
        assertEquals("solde incorrect", -100, c1.getSolde(), 0.01);

    }

    /**
     * Test of virer method, of class Compte.
     */
    @Test
    public void testVirer() {
        System.out.println("virer");
        Compte c2 = new Compte(null, 100, 300, 300);
        c1.virer(300, c2);
        assertEquals("solde incorrect", 700, c1.getSolde(), 0.01);
        assertEquals("solde incorrect", 400, c2.getSolde(), 0.01);
    }

}
