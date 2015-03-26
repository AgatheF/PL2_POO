/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufrim2ag.m2pcci.pl2.tp4;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
public class EnsembleDeLettres1Test {

    public EnsembleDeLettres1Test() {
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
     * verifie qu'un ensemble est bien conforme à une chaine, c'est dire que 
     * le cardinal de l'ensemble est le même que la longueur de la chaîne,
     * chaque lettre de la chaîne est bien présente dans l'ensemble.
     * @param ens l'ensemble à vérifier
     * @param s la chaîne de vérification
     */
    private void verifierEnsemble(EnsembleDeLettres1 ens, String s) {
        assertEquals("cardinal " + ens +  "inter {" + s + "} incorrect", s.length(), ens.cardinal());
        char[] charsS = s.toCharArray();
        for (int i = 0; i < charsS.length; i++) {
            assertTrue("" + charsS[i] + " n'appartient pas à {zert} inter {azertyuiop}", ens.contient(charsS[i]));
        }
    }

    /**
     * test l'intersection de deux ensembles, trois cas sont testés : e1 est
     * inclus dans e2. e1 et e2 sont disjoints. e1 n'est pas inclus dans e2 et
     * e1 et e2; ne sot pas disjoints.
     *
     * @param nomMethode nom de la methode d'intersection à exécuter
     */
    private void testIntersection(String nomMethode) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        // on utilise l'API de reflection pour récupérer et ensuite exécuter la méthode
        // d'intersection "nomMethode"
        Method m = EnsembleDeLettres1.class.getMethod(nomMethode, EnsembleDeLettres1.class);

        //teste que quand le premier ensemble est inclus dans le second
        // l'intersection correspond bien au premier
        String s1 = "zert";
        String s2 = "azertyuiop";

        // teste intersection2
        EnsembleDeLettres1 ens1 = new EnsembleDeLettres1(s1);
        EnsembleDeLettres1 ens2 = new EnsembleDeLettres1(s2);

        EnsembleDeLettres1 ensInter = (EnsembleDeLettres1) m.invoke(ens1, ens2);
        verifierEnsemble(ensInter,s1);

        // teste que l'intersection de deux ensembles disjoints est bien vide
        s1 = "azerty";
        s2 = "qsdfghj";

        ens1 = new EnsembleDeLettres1(s1);
        ens2 = new EnsembleDeLettres1(s2);
        assertTrue("{" + s1 + "} inter {" + s2 + " } n'est pas vide ", ((EnsembleDeLettres1) m.invoke(ens1, ens2)).estVide());

        // teste l'intersectionde deux ensembles quelconques
        String inter = "wxcvbn";
        s1 = "azerty" + inter;
        s2 = "qsdfgh" + inter;

        ens1 = new EnsembleDeLettres1(s1);
        ens2 = new EnsembleDeLettres1(s2);

        ensInter = (EnsembleDeLettres1) m.invoke(ens1, ens2);
        verifierEnsemble(ensInter, inter);
    }

    /**
     * Teste la méthode intersection1 pour l'intersection de deux ensembles,
     * dans le cas où le premier est inclus dans l'autre.
     *
     * @throws NoSuchMethodException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @Test
    public void testIntersection1() throws NoSuchMethodException, IllegalArgumentException, InvocationTargetException, IllegalAccessException {
        testIntersection("intersection1");
    }

    /**
     * Teste la méthode intersection2 pour l'intersection de deux ensembles
     *
     * @throws NoSuchMethodException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @Test
    public void testIntersection2() throws NoSuchMethodException, IllegalArgumentException, InvocationTargetException, IllegalAccessException {
        testIntersection("intersection2");
    }

    /**
     * Teste la méthode intersection3 pour l'intersection de deux ensembles.
     *
     * @throws NoSuchMethodException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @Test
    public void testIntersection3() throws NoSuchMethodException, IllegalArgumentException, InvocationTargetException, IllegalAccessException {
        testIntersection("intersection3");
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

    @Test
    public void testAjouter() {
        fail("Not yet implemented");
    }

    @Test
    public void testToString() {
        fail("Not yet implemented");
    }

    @Test
    public void testUnion() {
        fail("Not yet implemented");
    }

    @Test
    public void testDifference() {
        fail("Not yet implemented");
    }

    @Test
    public void testUnionDisjointe() {
        fail("Not yet implemented");
    }

}
