/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miinanharjaaja.logiikka;

import java.util.ArrayList;
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
public class PeliTest {

    public PeliTest() {
    }

    @Test
    public void testGetAlue() {
        Alue expResult = new Alue(3, 1);
        Peli instance = new Peli(expResult);

        Alue result = instance.getAlue();
        assertEquals(expResult, result);
    }

    /**
     * Test of havio method, of class Peli.
     */
//    @Test
//    public void testHavio() {
//        System.out.println("havio");
//        Peli peli = new Peli(new Alue(3, 1));
//        boolean havio = peli.havio();
//        assertEquals(peli.isHavio(), havio);
//        assertEquals(peli.getKello().isPaalla(), false);
//    }

    /**
     * Test of voitto method, of class Peli.
     */
    @Test
    public void testVoitto() {
        System.out.println("voitto");
        Peli instance = new Peli(new Alue(3, 0));
        instance.avaaRuutu(1, 1);
        boolean expResult = true;
        boolean result = instance.voitto();
        assertEquals(expResult, result);
    }

    @Test
    public void testVoittoEiVoittoa() {
        System.out.println("voitto");
        Peli instance = new Peli(new Alue(3, 11));
        boolean expResult = false;
        boolean result = instance.voitto();
        assertEquals(expResult, result);
    }

    /**
     * Test of lukitseRuutu method, of class Peli.
     */
    @Test
    public void testLukitseRuutu() {
        System.out.println("lukitseRuutu");
        int x = 0;
        int y = 0;
        Peli instance = new Peli(new Alue(3, 1));
        instance.lukitseRuutu(x, y);
        ArrayList[] lista = instance.getAlue().getRuudukko();
        ArrayList<Ruutu> ruudut = lista[0];
        assertEquals(true, ruudut.get(0).isLukittu());
    }

    /**
     * Test of avaaRuutu method, of class Peli.
     */
    @Test
    public void testAvaaRuutu() {
        System.out.println("avaaRuutu");
        int x = 0;
        int y = 0;
        Peli instance = new Peli(new Alue(3, 0));
        instance.avaaRuutu(x, y);
        ArrayList[] lista = instance.getAlue().getRuudukko();
        ArrayList<Ruutu> ruudut = lista[0];
        assertEquals(true, ruudut.get(0).isAvattu());
    }

    /**
     * Test of jatka method, of class Peli.
     */
    @Test
    public void testJatka() {
        System.out.println("jatka");
        Peli instance = new Peli(new Alue(3, 1));
        instance.jatka();
        assertEquals(instance.isHavio(), false);
        assertEquals(instance.getMenetykset(), 1);
        
    }

    /**
     * Test of etene method, of class Peli.
     */
    @Test
    public void testEtene() {
        System.out.println("etene");
        Peli instance = new Peli(new Alue(3, 1));
        instance.etene();
        assertEquals(instance.getKello().getSekunnit(), 1);
    }

    /**
     * Test of kelloAika method, of class Peli.
     */
    @Test
    public void testKelloAika() {
        System.out.println("kelloAika");
        Peli instance = new Peli(new Alue(3, 1));
        String result = instance.kelloAika();
        assertEquals(result, "00:00:00");

    }

}
