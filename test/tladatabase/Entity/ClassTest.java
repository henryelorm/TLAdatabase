/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tladatabase.Entity;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Elorm
 */
public class ClassTest {
    
    public ClassTest() {
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
     * Test of getClassName method, of class Class.
     */
    @Test
    public void testGetClassName() {
        System.out.println("getClassName");
        Class instance = new Class();
        String expResult = "";
        String result = instance.getClassName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setClassName method, of class Class.
     */
    @Test
    public void testSetClassName() {
        System.out.println("setClassName");
        String className = "";
        Class instance = new Class();
        instance.setClassName(className);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getId method, of class Class.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Class instance = new Class();
        Long expResult = null;
        Long result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setId method, of class Class.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        Long id = null;
        Class instance = new Class();
        instance.setId(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class Class.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Class instance = new Class();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class Class.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = null;
        Class instance = new Class();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Class.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Class instance = new Class();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
