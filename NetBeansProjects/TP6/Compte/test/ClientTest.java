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
public class ClientTest {
    
    public ClientTest() {
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
    public void testSomeMethod() {
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getInfosTitulaire method, of class Client.
     */
    @Test
    public void testGetInfosTitulaire() {
        System.out.println("getInfosTitulaire");
        Client instance = new Client("prenomClient", "nomClient","adresseClient");
        String expResult = "prenomClient, nomClient, adresseClient";
        String result = instance.getInfosTitulaire();
        assertEquals(expResult, result);
        //fail("The test case is a prototype.");
    }
    
}
