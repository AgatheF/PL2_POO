/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author agathe
 */
public class CompteTest {
    
    public CompteTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of crediter method, of class Compte.
     */
    @Test
    public void testCrediter() {
        System.out.println("crediter");
        double montant = 500.50;
        Client monClientTest=new Client("Adrien", "TRUC","rue des fleurs");
        Compte instance = new Compte(monClientTest,2000.00);
        double expResult = 2500.50;
        instance.crediter(montant);
        double result = instance.soldeDuCompte;
        assertEquals(expResult, result, 0.0);
        //fail("The test case is a prototype.");
    }

    /**
     * Test of debiter method, of class Compte.
     * Debit non effectuable
     */
    @Test
    public void testDebiter() {
        System.out.println("debiter");
        double montant = 2000;
        Client monClientTest=new Client("Adrien", "TRUC","rue des fleurs");
        Compte instance = new Compte(monClientTest);
        double expResult = 0.0;
        instance.debiter(montant);
        double result = instance.soldeDuCompte;
        assertEquals(expResult, result, 0.0);
        //fail("The test case is a prototype.");
    }

    /**
     * Test of effectuerVirement method, of class Compte.
     */
    @Test
    public void testEffectuerVirement() {
        System.out.println("effectuerVirement");
        double montant = 0.0;
        int numeroDeCompteReceveur = 0;
        Compte instance = new Compte();
        double expResult = 0.0;
        double result = instance.effectuerVirement(montant, numeroDeCompteReceveur);
        assertEquals(expResult, result, 0.0);
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSoldeDuCompte method, of class Compte.
     */
    @Test
    public void testSetSoldeDuCompte() {
        System.out.println("setSoldeDuCompte");
        double depotInitial = 0.0;
        Compte instance = new Compte();
        double expResult = 0.0;
        double result = instance.setSoldeDuCompte(depotInitial);
        assertEquals(expResult, result, 0.0);
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDecouvertMaximalAutorise method, of class Compte.
     */
    @Test
    public void testSetDecouvertMaximalAutorise() {
        System.out.println("setDecouvertMaximalAutorise");
        double montant = 0.0;
        Compte instance = new Compte();
        double expResult = 0.0;
        double result = instance.setDecouvertMaximalAutorise(montant);
        assertEquals(expResult, result, 0.0);
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDebitMaximalAutorise method, of class Compte.
     */
    @Test
    public void testSetDebitMaximalAutorise() {
        System.out.println("setDebitMaximalAutorise");
        double montant = 0.0;
        Compte instance = new Compte();
        double expResult = 0.0;
        double result = instance.setDebitMaximalAutorise(montant);
        assertEquals(expResult, result, 0.0);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNumerodeCompte method, of class Compte.
     */
    @Test
    public void testGetNumerodeCompte() {
        System.out.println("getNumerodeCompte");
        Compte instance = new Compte();
        double expResult = 0.0;
        double result = instance.getNumerodeCompte();
        assertEquals(expResult, result, 0.0);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDebitmaximalAutorise method, of class Compte.
     */
    @Test
    public void testGetDebitmaximalAutorise() {
        System.out.println("getDebitmaximalAutorise");
        Compte instance = new Compte();
        double expResult = 0.0;
        double result = instance.getDebitmaximalAutorise();
        assertEquals(expResult, result, 0.0);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDecouvertMaximalAutorise method, of class Compte.
     */
    @Test
    public void testGetDecouvertMaximalAutorise() {
        System.out.println("getDecouvertMaximalAutorise");
        Compte instance = new Compte();
        double expResult = 0.0;
        double result = instance.getDecouvertMaximalAutorise();
        assertEquals(expResult, result, 0.0);
        fail("The test case is a prototype.");
    }

    /**
     * Test of situationCompte method, of class Compte.
     */
    @Test
    public void testSituationCompte() {
        System.out.println("situationCompte");
        Compte instance = new Compte();
        boolean expResult = false;
        boolean result = instance.situationCompte();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of debitAutorise method, of class Compte.
     */
    @Test
    public void testDebitAutorise() {
        System.out.println("debitAutorise");
        Compte instance = new Compte();
        double expResult = 0.0;
        double result = instance.debitAutorise();
        assertEquals(expResult, result, 0.0);
        fail("The test case is a prototype.");
    }
    
}


