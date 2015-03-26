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
public class EnsembleDeLettreTest {
    
    public EnsembleDeLettreTest() {
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
     * Test of toString method, of class EnsembleDeLettre.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        EnsembleDeLettre instance = new EnsembleDeLettre("essai");
        String expResult = "{aeis}";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of convertToChar method, of class EnsembleDeLettre.
     */
    @Test
    public void testConvertToChar() {
        System.out.println("convertToChar");
        int i = 0; //a = indice 0
        EnsembleDeLettre instance = new EnsembleDeLettre();
        char expResult = 'a';
        char result = instance.convertToChar(i);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of cardinal method, of class EnsembleDeLettre.
     */
    @Test
    public void testCardinal() {
        System.out.println("cardinal");
        EnsembleDeLettre instance = new EnsembleDeLettre("test");
        int expResult = 3;
        int result = instance.cardinal();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test1 of estVide method, of class EnsembleDeLettre.
     * Avec ensemble vide
     */
    @Test
    public void testEstVide1() {
        System.out.println("estVide");
        EnsembleDeLettre instance = new EnsembleDeLettre();
        boolean expResult = true;
        boolean result = instance.estVide();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
     /**
     * Test2 of estVide method, of class EnsembleDeLettre.
     * Avec ensemble non vide
     */
    @Test
    public void testEstVide2() {
        System.out.println("estVide");
        EnsembleDeLettre instance = new EnsembleDeLettre("nonVide");
        boolean expResult = false;
        boolean result = instance.estVide();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test1 of estIncluDans method, of class EnsembleDeLettre.
     */
    @Test
    public void testEstIncluDans1() {
        System.out.println("estIncluDans");
        EnsembleDeLettre e = new EnsembleDeLettre("edcba");
        EnsembleDeLettre instance = new EnsembleDeLettre("abc");
        boolean expResult = true;
        boolean result = instance.estIncluDans(e);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
     /**
     * Test2 of estIncluDans method, of class EnsembleDeLettre.
     */
    @Test
    public void testEstIncluDans2() {
        System.out.println("estIncluDans");
        EnsembleDeLettre e = new EnsembleDeLettre("xyzab");
        EnsembleDeLettre instance = new EnsembleDeLettre("abc");
        boolean expResult = false;
        boolean result = instance.estIncluDans(e);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    
    
    
    
    
    /**
     * Test1 of lettreEstDans method, of class EnsembleDeLettre.
     * La lettre st dans l ensemble
     */
    @Test
    public void testLettreEstDans1() {
        System.out.println("lettreEstDans");
        char c = 'b';
        EnsembleDeLettre instance = new EnsembleDeLettre("abc");
        boolean expResult = true;
        boolean result = instance.lettreEstDans(c);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    
     /**
     * Test2 of lettreEstDans method, of class EnsembleDeLettre.
     * La lettre n est pas dans l ensemble
     */
    @Test
    public void testLettreEstDans2() {
        System.out.println("lettreEstDans");
        char c = 'z';
        EnsembleDeLettre instance = new EnsembleDeLettre("abc");
        boolean expResult = false;
        boolean result = instance.lettreEstDans(c);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of intersectionEnsemble method, of class EnsembleDeLettre.
     */
    @Test
    public void testIntersectionEnsemble() {
        System.out.println("intersectionEnsemble");
        EnsembleDeLettre e = new EnsembleDeLettre("acbfed");
        EnsembleDeLettre instance = new EnsembleDeLettre("efghij");
        EnsembleDeLettre expResult = new EnsembleDeLettre("ef");
        EnsembleDeLettre result = instance.intersectionEnsemble(e);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
