/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufrim2ag.m2pcci.pl2.tp4;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author genoud
 */
public class EnsembleDeLettres1_V0_Test {

    public EnsembleDeLettres1_V0_Test() {
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

    @Test
    public void testEnsembleDeLettresBoolean() {
        EnsembleDeLettres1 el = new EnsembleDeLettres1(true);
        assertTrue(el.estVide());
    }

    /**
     * construction de l'ensemble à partir d'une chaine ne contenant que des
     * bons caractères sans répétition "abcdeflmh"
     */
    @Test
    public void testEnsembleDeLettresString1() {

		// construction de l'ensemble à partir d'une chaine ne contenant que des bons
        // caractères sans répétition
        String s1 = "abcdeflmh";
        EnsembleDeLettres1 el1 = new EnsembleDeLettres1(s1);
        // on verifie que le cardinal de l'ensemble est correct
        assertTrue("cardinal n'est pas egal à la longueur de la chaine fournie ", el1.cardinal() == s1.length());
        // on verifie que chaque caractère de la chaine est présent dans l'ensemble
        char[] charsS1 = s1.toCharArray();
        for (int i = 0; i < charsS1.length; i++) {
            assertTrue("absence de l'un des caractères dans l'ensemble", el1.contient(charsS1[i]));
        }
    }

    /**
     * construction de l'ensemble à partir d'une chaine ne contenant que des
     * bons caractères mais avec répétiton "abcdeflmh"+"abcdeflmh"
     */
    @Test
    public void testEnsembleDeLettresString2() {

        String s1 = "abcdeflmh";
        String s2 = s1 + s1;
        EnsembleDeLettres1 el2 = new EnsembleDeLettres1(s2);
        // on verifie que le cardinal de l'ensemble est correct
        assertTrue("cardinal incorrect", el2.cardinal() == s1.length());
        // on verifie que chaque caractère de la chaine est présent dans l'ensemble
        char[] charsS2 = s2.toCharArray();
        for (int i = 0; i < charsS2.length; i++) {
            assertTrue("absence de l'un des caractères dans l'ensemble", el2.contient(charsS2[i]));
        }
    }

    /**
     * construction de l'ensemble à partir d'une chaine ne contenant que des
     * bons caractères mais avec majuscules et minuscules "abCdeFlmH"
     */
    @Test
    public void testEnsembleDeLettresString3() {

        String s3 = "abCdeFlmH";
        EnsembleDeLettres1 el3 = new EnsembleDeLettres1(s3);
        // on verifie que le cardianl de l'ensemble est correct
        assertTrue("cardinal incorrect", el3.cardinal() == s3.length());
        // on verifie que chaque caractère de la chaine est présent dans l'ensemble
        char[] charsS3 = s3.toLowerCase().toCharArray();
        for (int i = 0; i < charsS3.length; i++) {
            assertTrue("absence de l'un des caractères dans l'ensemble", el3.contient(charsS3[i]));
        }

    }

    /**
     * construction de l'ensemble vide à partir d'une chaine vide
     */
    @Test
    public void testEnsembleDeLettresString4() {

        String s = "";
        EnsembleDeLettres1 el3 = new EnsembleDeLettres1(s);
        // on verifie que le cardianl de l'ensemble est correct
        assertTrue("ensemble non vide", el3.estVide());
        assertTrue("cardinal non nul", el3.cardinal() == 0);
    }

    /**
     * construction de l'ensemble à partir d'une chaine contenant des bons
     * caractères mais aussi des caractères qui ne sont pas des lettres
     */
    @Test
    public void testEnsembleDeLettresString5() {

        String s1 = "azertyu";
        String s2 = "&4(6_@";
        String s3 = "qsdfghj";
        String s4 = s1 + s2 + s3;
        EnsembleDeLettres1 el = new EnsembleDeLettres1(s4);
        // on verifie que le cardianl de l'ensemble est correct
        assertTrue("cardinal incorrect", el.cardinal() == (s1 + s3).length());
        // on verifie que chaque caractère de la chaine est présent dans l'ensemble
        char[] charsS3 = (s1 + s3).toLowerCase().toCharArray();
        for (int i = 0; i < charsS3.length; i++) {
            assertTrue("absence de " + charsS3[i] + "dans l'ensemble {" + s1 + s3 + "}", el.contient(charsS3[i]));
        }

    }

    /**
     * teste que toutes les lettres d'un ensemble lui appartiennent et que
     * toutes les autres ne lui appartiennent pas
     */
    @Test
    public void testAppartient1() {
        String s1 = "azertyuiop";
        String s2 = "qsdfghjklmwxcvbn";
        EnsembleDeLettres1 el1 = new EnsembleDeLettres1(s1);

		// on verifie que chaque lettre de la chaine ayant servi a créér l'ensemble 
        // appartient à celui-ci
        char[] charsS1 = s1.toCharArray();
        for (int i = 0; i < charsS1.length; i++) {
            assertTrue("" + charsS1[i] + " devrait appartenir à {" + s1 + "}", el1.contient(charsS1[i]));
        }

		// on verifie que chaque lettre d'une chaîne totalement différente de la
        // premiere n'apartient pas à l'ensemble
        char[] charsS2 = s2.toCharArray();
        for (int i = 0; i < charsS2.length; i++) {
            assertFalse("" + charsS2[i] + " ne devrait pas appartenir à {" + s1 + "}", el1.contient(charsS2[i]));
        }
    }

    /**
     * teste qu'aucune lettre n'appartient à l'ensemble vide
     */
    @Test
    public void testAppartient2() {
        String s1 = "azertyuiopqsdfghjklmwxcvbn";
        EnsembleDeLettres1 elVide = new EnsembleDeLettres1(true);

		// on verifie que chaque lettre de la chaine ayant servi a créér l'ensemble 
        // appartient à celui-ci
        char[] charsS1 = s1.toCharArray();

        for (int i = 0; i < charsS1.length; i++) {
            assertFalse("" + charsS1[i] + " ne devrait pas appartenir à {}", elVide.contient(charsS1[i]));
        }
    }

    /**
     * teste qu'un caractère qui n'est pas une lettre n'appartient pas à un
     * ensemble
     */
    @Test
    public void testAppartient3() {
        String s1 = "azertyuiopqsdfghjklmwxcvbn";
        EnsembleDeLettres1 el1 = new EnsembleDeLettres1(s1);
        String s2 = "1234567890&#'{([-|]=)}";

        char[] charsS2 = s2.toCharArray();
        for (int i = 0; i < charsS2.length; i++) {
            assertFalse("" + charsS2[i] + " ne devrait pas appartenir à {" + s1 + "}", el1.contient(charsS2[i]));
        }
    }

    /**
     * teste l'inclusion d'un ensemble dans un autre
     */
    @Test
    public void testEstInclus1() {

        String s1 = "azerty";
        String s2 = "azertyuiop";

        EnsembleDeLettres1 el1 = new EnsembleDeLettres1(s1);
        EnsembleDeLettres1 el2 = new EnsembleDeLettres1(s2);
        assertTrue("{azerty} devrait être inclus dans {azertyuiop} ", el1.estInclus(el2));
        assertFalse("{azertyuiop} ne devrait être inclus dans {azerty}", el2.estInclus(el1));
    }

    /**
     * test inclusion avec ensemble vide
     */
    @Test
    public void testEstInclus2() {

        String s1 = "azerty";
        EnsembleDeLettres1 el1 = new EnsembleDeLettres1(s1);
        EnsembleDeLettres1 el3 = new EnsembleDeLettres1(true);
        assertTrue("{} devrait être inclus dans {azerty} ", el3.estInclus(el1));
        assertFalse("{azerty} ne devrait être inclus dans {}", el1.estInclus(el3));

    }

    /**
     * teste inclusion d'un ensemble avec lui même et avec un esnemble identique
     */
    @Test
    public void testEstInclus3() {

        String s1 = "azerty";
        EnsembleDeLettres1 el1 = new EnsembleDeLettres1(s1);
        assertTrue("ensemble pas inclus dans lui même", el1.estInclus(el1));

        EnsembleDeLettres1 el2 = new EnsembleDeLettres1(s1);
        assertTrue("ensemble pas inclus dans un ensemble identique", el1.estInclus(el2) && el2.estInclus(el1));

    }

    /**
     * test intersection2 de deux ensembles, le premier étant inclus dans le
     * second
     */
    @Test
    public void testIntersection1() {
        String s1 = "zert";
        String s2 = "azertyuiop";

        // teste intersection2
        EnsembleDeLettres1 el1 = new EnsembleDeLettres1(s1);
        EnsembleDeLettres1 el2 = new EnsembleDeLettres1(s2);
        EnsembleDeLettres1 el3 = el1.intersection2(el2);

        assertTrue("cardinal {zert} inter {azertyuiop} incorrect", el3.cardinal() == s1.length());
        char[] charsS1 = s1.toCharArray();
        for (int i = 0; i < charsS1.length; i++) {
            assertTrue("" + charsS1[i] + " n'appartient pas à {zert} inter {azertyuiop}", el3.contient(charsS1[i]));
        }

    }

    /**
     * teste intersection2 de deux ensembles disjoints est bien vide
     */
    @Test
    public void testIntersection2_2() {

        String s3 = "azerty";
        String s4 = "qsdfghj";

        // teste intersection2
        EnsembleDeLettres1 el4 = new EnsembleDeLettres1(s3);
        EnsembleDeLettres1 el5 = new EnsembleDeLettres1(s4);
        assertTrue("{" + s3 + "} inter {" + s4 + " } n'est pas vide ", el4.intersection2(el5).estVide());
    }

     @Test
    public void testIntersection2_1() {

        String s3 = "azerty";
        String s4 = "qsdfghj";

        // teste intersection2
        EnsembleDeLettres1 el4 = new EnsembleDeLettres1(s3);
        EnsembleDeLettres1 el5 = new EnsembleDeLettres1(s4);
        assertTrue("{" + s3 + "} inter {" + s4 + " } n'est pas vide ", el4.intersection1(el5).estVide());
    }

     @Test
    public void testIntersection2_3() {

        String s3 = "azerty";
        String s4 = "qsdfghj";

        // teste intersection2
        EnsembleDeLettres1 el4 = new EnsembleDeLettres1(s3);
        EnsembleDeLettres1 el5 = new EnsembleDeLettres1(s4);
        assertTrue("{" + s3 + "} inter {" + s4 + " } n'est pas vide ", el4.intersection3(el5).estVide());
    }

    /**
     * teste intersection2 de deux ensembles quelconques
     */
    @Test
    public void testIntersection3() {

        String inter = "wxcvbn";
        String s1 = "azerty" + inter;
        String s2 = "qsdfgh" + inter;

        // teste intersection2
        EnsembleDeLettres1 el1 = new EnsembleDeLettres1(s1);
        EnsembleDeLettres1 el2 = new EnsembleDeLettres1(s2);

        EnsembleDeLettres1 elInter = el1.intersection3(el2);

        assertTrue("cardinal {" + s1 + "} inter {" + s2 + "} incorrect", elInter.cardinal() == inter.length());
        char[] charsInter = inter.toCharArray();
        for (int i = 0; i < charsInter.length; i++) {
            assertTrue("" + charsInter[i] + " n'appartient pas à {" + s1 + "} inter {" + s2 + "}", elInter.contient(charsInter[i]));
        }

    }

    /**
     * teste le cardinal de l'ensemble {azerty} et de l'ensemble {}
     */
    @Test
    public void testCardinal() {
        String s1 = "azerty";
        EnsembleDeLettres1 el1 = new EnsembleDeLettres1(s1);
        assertTrue("cardinal de {" + s1 + "} incorrect ", el1.cardinal() == s1.length());

        EnsembleDeLettres1 el2 = new EnsembleDeLettres1(true);
        assertTrue("cardinal de {} incorrect ", el2.cardinal() == 0);
    }

    /*
     public void testAjouter() {
     fail("Not yet implemented");
     }

     public void testToString() {
     fail("Not yet implemented");
     }

     public void testCardinal() {
     fail("Not yet implemented");
     }

     public void testEstVide() {
     fail("Not yet implemented");
     }
     */
    /*
     public void testUnion() {
     fail("Not yet implemented");
     }

     public void testDifference() {
     fail("Not yet implemented");
     }

     public void testUnionDisjointe() {
     fail("Not yet implemented");
     }
     */
}
