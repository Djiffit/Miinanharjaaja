/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miinanharjaaja.logiikka;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author konstaku
 */
public class AlueTest {

    private Alue alue;

    public AlueTest() {
        this.alue = new Alue(2, 1);
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
     * Test of getX method, of class Alue.
     */
    @Test
    public void testGetX() {
        System.out.println("getX");
        Alue instance = new Alue(2, 1);
        int expResult = 2;
        int result = instance.getX();
        assertEquals(expResult, result);
    }

    /**
     * Test of tulostaAlue method, of class Alue.
     */
//    @Test
//    public void testTulostaAluePiirtaaOikean() {
//        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//        Alue seutu = new Alue(1,1);
//        System.setOut(new PrintStream(outContent));
//        seutu.tulostaAlue();
//        assertEquals("-" + "\n", outContent.toString());
//    }

    /**
     * Test of avaa method, of class Alue.
     */
    
    @Test
    public void testAvaaPienin() {
        System.out.println("avaa");
        int z = 0;
        int t = 0;
        ArrayList<Ruutu>[] lista = this.alue.getRuudukko();
        boolean result = this.alue.avaa(z, t);
        assertEquals(lista[0].get(0).isMiina(), result);
    }
    
    @Test
    public void avaaViereisetAvaaPienenOsan() {
        Alue instance = new Alue(3, 5);
        ArrayList<Ruutu>[] lista = instance.getRuudukko();
        lista[0].get(1).setMiina(false);
        lista[0].get(0).setMiina(false);    
        instance.avaaViereiset(0, 0);
        instance.setRuudut(2);
        assertEquals(1, instance.getAvatutRuudut(), .1);
    }
    
     @Test
    public void avaaViereisetAvaaKaikki() {
        Alue instance = new Alue(3, 0);
        instance.avaaViereiset(0, 0);
        instance.setRuudut(9);
        assertEquals(1, instance.getAvatutRuudut(), .1);
    }
    
    @Test
    public void testAvaaSuurin() {
        System.out.println("avaa");
        int z = 1;
        int t = 1;
        ArrayList<Ruutu>[] lista = this.alue.getRuudukko();
        boolean result = this.alue.avaa(z, t);
        assertEquals(lista[z].get(t).isMiina(), result);
    }

    /**
     * Test of getAvatutRuudut method, of class Alue.
     */
    @Test
    public void testGetAvatutRuudutNolla() {
        System.out.println("getAvatutRuudut");
        Alue instance = new Alue(2, 1);
        double expResult = 0.0;
        double result = instance.getAvatutRuudut();
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void testGetAvatutRuudutYksi() {
        System.out.println("getAvatutRuudut");
        Alue instance = new Alue(3, 0);
        ArrayList<Ruutu>[] lista = instance.getRuudukko();
        lista[1].get(1).setAvattu(true);
        double expResult = 1/9;
        double result = instance.getAvatutRuudut();
        assertEquals(expResult, result, result);
    }
    
    @Test 
    public void testLisaaMiinaKeskella() {
        Alue seutu = new Alue(3,0);
        seutu.lisaaMiina(1, 1);
        ArrayList<Ruutu>[] lista = seutu.getRuudukko();
        assertEquals(1, lista[0].get(1).getViereisetMiinat());
        assertEquals(1, lista[0].get(0).getViereisetMiinat());
        assertEquals(1, lista[0].get(2).getViereisetMiinat());
        assertEquals(1, lista[1].get(0).getViereisetMiinat());
        assertEquals(1, lista[1].get(2).getViereisetMiinat());
        assertEquals(1, lista[2].get(1).getViereisetMiinat());
        assertEquals(1, lista[2].get(0).getViereisetMiinat());
        assertEquals(1, lista[2].get(2).getViereisetMiinat());
        
    }
    
    @Test 
    public void alustaAlueEiTeeMitaanJosNegatiivinen() {
        Alue seutu = new Alue(-1, 0);
        seutu.alustaAlue();
    }
    
    @Test 
    public void alustaAlueLuoAlueen() {
        Alue seutu = new Alue(1, 5);
        ArrayList<Ruutu>[] lista = seutu.getRuudukko();
        assertEquals(true, lista[0].get(0).isMiina());
    }
    
     @Test 
    public void testLisaaMiinaVasenKulma() {
        Alue seutu = new Alue(3,0);
        seutu.lisaaMiina(0, 0);
        ArrayList<Ruutu>[] lista = seutu.getRuudukko();
        assertEquals(1, lista[1].get(0).getViereisetMiinat());
        assertEquals(1, lista[1].get(1).getViereisetMiinat());
        assertEquals(1, lista[0].get(1).getViereisetMiinat());
    }
    
    @Test
    public void testLisaaMiinaOikeaKulma() {
        Alue seutu = new Alue(3,0);
        seutu.lisaaMiina(2, 2);
        ArrayList<Ruutu>[] lista = seutu.getRuudukko();
        assertEquals(1, lista[1].get(2).getViereisetMiinat());
        assertEquals(1, lista[1].get(1).getViereisetMiinat());
        assertEquals(1, lista[2].get(1).getViereisetMiinat());
    }
    
    @Test
    public void testGetAvatutRuudutKaikki() {
        System.out.println("getAvatutRuudut");
        Alue instance = new Alue(2, 0);
        ArrayList<Ruutu>[] lista = instance.getRuudukko();
        instance.avaa(0, 0);
        double expResult = 1;
        double result = instance.getAvatutRuudut();
        assertEquals(expResult, result, result);
    }

}