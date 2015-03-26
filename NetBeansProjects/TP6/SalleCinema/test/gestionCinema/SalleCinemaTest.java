/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionCinema;

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
public class SalleCinemaTest {
    
    public SalleCinemaTest() {
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
     * Test of nbPlacesDisponibles method, of class SalleCinema.
     */
    @Test
    public void testNbPlacesDisponibles() {
        System.out.println("nbPlacesDisponibles");
        SalleCinema instance = new SalleCinema("testTitre", 250, 6.5);
        int expResult = 250;
        int result = instance.nbPlacesDisponibles();
        assertEquals(expResult, result);
        //fail("The test case is a prototype.");
    }

    /**
     * Test of vendrePlaces method, of class SalleCinema.
     */
    @Test
    public void testVendrePlaces() {
        System.out.println("vendrePlaces");
        int nbre = 50;
        SalleCinema instance = new SalleCinema("testTitre", 250, 6.5);
        
        //test 1: avecplace au tarif normal
        boolean tarifReduit = false;
        instance.vendrePlaces(nbre, tarifReduit);
        int expResult = 50;
        int result = instance.nbPlacesVenduesNormal;
        assertEquals(expResult, result);
        
        // test 2: avec places au tarif reduit
        boolean tarifReduit2 = false;
        instance.vendrePlaces(nbre, tarifReduit);
        int expResult2 = 50;
        int result2 = instance.nbPlacesVenduesNormal;
        assertEquals(expResult, result);
        //fail("The test case is a prototype.");
    }

    /**
     * Test of remiseAZero method, of class SalleCinema.
     */
    @Test
    public void testRemiseAZero() {
        System.out.println("remiseAZero");
        SalleCinema instance = new SalleCinema("testTitre", 250, 6.5);
        instance.vendrePlaces(10, true);
        instance.remiseAZero();
         int expResult = 250;
        int result = instance.nbPlaces-instance.nbPlacesVenduesNormal-instance.nbPlacesVenduesReduit;
        //fail("The test case is a prototype.");
    }

    /**
     * Test of chiffreAffaires method, of class SalleCinema.
     */
    @Test
    public void testChiffreAffaires() {
        System.out.println("chiffreAffaires");
        SalleCinema instance = new SalleCinema("testTitre", 250, 6.5);
        instance.vendrePlaces(2, true);
        instance.vendrePlaces(2, false);
        double expResult = 23.4;
        double result = instance.chiffreAffaires();
        assertEquals(expResult, result, 0.0);
        //fail("The test case is a prototype.");
    }

    /**
     * Test of tauxRemplissage method, of class SalleCinema.
     */
    @Test
    public void testTauxRemplissage() {
        System.out.println("tauxRemplissage");
        SalleCinema instance = new SalleCinema("testTitre", 250, 6.5);
        instance.vendrePlaces(82, false);
        double expResult = 33;  // probleme round A VOIR
        double result = instance.tauxRemplissage();
        assertEquals(expResult, result, 0.0);
        //fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class SalleCinema.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        SalleCinema instance = new SalleCinema("testTitre", 250, 6.50);
        instance.vendrePlaces(2, true);
        instance.vendrePlaces(2, false);
        String expResult = "Film joue: testTitre\nNombre de places: 250\nPrix au tarif normal: 6.5\n2 places vendues au tarif normal\n2 places vendues au tarif reduit\n";
        String result = instance.toString();
        assertEquals(expResult, result);
        //fail("The test case is a prototype.");
    }

//    /**
//     * Test of main method, of class SalleCinema.
//     */
//    @Test
//    public void testMain() {
//        System.out.println("main");
//        String[] args = null;
//        SalleCinema.main(args);
//        fail("The test case is a prototype.");
//    }
    
}
