/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miinanharjaaja.logiikka;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Konsta
 */
public class KelloTest {

    public KelloTest() {
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
     * Test of etene method, of class Kello.
     */
    @Test
    public void testEtene() {
        System.out.println("etene");
        Kello instance = new Kello();
        instance.etene();
        assertEquals(instance.getSekunnit(), 1);
        assertEquals(instance.getMinuutit(), 0);
        assertEquals(instance.getTunnit(), 0);
    }

    @Test
    public void testEteneMinuutit() {
        System.out.println("etene");
        Kello instance = new Kello();
        instance.setSekunnit(59);
        instance.etene();
        assertEquals(instance.getSekunnit(), 0);
        assertEquals(instance.getMinuutit(), 1);

    }

    @Test
    public void testEteneTunnit() {
        System.out.println("etene");
        Kello instance = new Kello();
        instance.setMinuutit(59);
        instance.setSekunnit(59);
        instance.etene();
        assertEquals(instance.getSekunnit(), 0);
        assertEquals(instance.getMinuutit(), 0);
        assertEquals(instance.getTunnit(), 1);
    }

    /**
     * Test of setPaalla method, of class Kello.
     */
    @Test
    public void testSetPaalla() {
        System.out.println("setPaalla");
        boolean paalla = false;
        Kello instance = new Kello();
        instance.setPaalla(paalla);
        assertEquals(instance.isPaalla(), false);
    }

    /**
     * Test of toString method, of class Kello.
     */

    @Test

    public void testToStringNolla() {
        System.out.println("toString");
        Kello instance = new Kello();
        String expResult = "";
        String result = instance.toString();
        assertEquals("00:00:00", result);
    }

    public void testToStringKymmenenToimii() {
        System.out.println("toString");
        Kello instance = new Kello();
        instance.setMinuutit(10);
        instance.setSekunnit(10);
        String result = instance.toString();
        assertEquals("00:10:10", result);
    }

}
