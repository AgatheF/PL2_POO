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
public class CounterTest {
    
    public CounterTest() {
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
     * Test of increment method, of class Counter.
     */
    @Test
    public void testIncrement() {
        System.out.println("increment");
        Counter instance = new Counter(5);
        int expResult = 6;
        int result = instance.increment();
        assertEquals(expResult, result);
        //fail("The test case is a prototype.");
    }

    /**
     * Test of decrement method, of class Counter.
     */
    @Test
    public void testDecrement() {
        System.out.println("decrement");
        Counter instance = new Counter(2);
        int expResult = 1;
        int result = instance.decrement();
        assertEquals(expResult, result);
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getCount method, of class Counter.
     */
    @Test
    public void testGetCount() {
        System.out.println("getCount");
        Counter instance = new Counter(2);
        int expResult = 2;
        int result = instance.getCount();
        assertEquals(expResult, result);
        //fail("The test case is a prototype.");
    }

    /**
     * Test of add method, of class Counter.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        Counter c = new Counter(2);
        Counter instance = new Counter(3);
        Counter expResult = new Counter(5);
        Counter result = instance.add(c);
        
        // comparaison valeur du compteur avec méthode getCount deja testee
        int valExpResult=expResult.getCount();
        int valResult=result.getCount(); 
        assertEquals(valExpResult, valResult);
        //fail("The test case is a prototype.");
    }

    /**
     * Test of sub method, of class Counter.
     */
    @Test
    public void testSub() {
        System.out.println("sub");
        Counter c = new Counter (3);
        Counter instance = new Counter(5);
        Counter expResult = new Counter(2);
        Counter result = instance.sub(c);
        //fail("The test case is a prototype.");
        
        // comparaison valeur du compteur avec méthode getCount deja testee
        int valExpResult=expResult.getCount();
        int valResult=result.getCount(); 
        assertEquals(valExpResult, valResult);
        //fail("The test case is a prototype.");
    }
    
}
