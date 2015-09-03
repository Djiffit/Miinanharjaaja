/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miinanharjaaja.logiikka;

import java.util.ArrayList;
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
        Alue odot = new Alue(3, 1);
        Peli peli = new Peli(odot);

        Alue tulos = peli.getAlue();
        assertEquals(odot, tulos);
    }

    /**
     * Test of havio method, of class Peli.
     */
    @Test
    public void testHavio() {
        Peli peli = new Peli(new Alue(3, 1));
        boolean havio = peli.havio();
        assertEquals(peli.isHavio(), havio);
    }

    /**
     * Test of voitto method, of class Peli.
     */
    @Test
    public void testVoitto() {
        Peli peli = new Peli(new Alue(3, 0));
        peli.avaaRuutu(1, 1);
        boolean ododtus = true;
        boolean tulos = peli.voitto();
        assertEquals(ododtus, tulos);
    }

    @Test
    public void testVoittoKello() {
        Peli peli = new Peli(new Alue(3, 0));
        peli.avaaRuutu(1, 1);
        boolean odotus = true;
        boolean tulos = peli.getKello().isPaalla();
        assertEquals(odotus, tulos);
    }

    @Test
    public void testVoittoEiVoittoa() {
        Peli peli = new Peli(new Alue(3, 11));
        boolean odotus = false;
        boolean tulos = peli.voitto();
        assertEquals(odotus, tulos);
    }

    /**
     * Test of lukitseRuutu method, of class Peli.
     */
    @Test
    public void testLukitseRuutu() {
        System.out.println("lukitseRuutu");
        int x = 0;
        int y = 0;
        Peli peli = new Peli(new Alue(3, 1));
        peli.lukitseRuutu(x, y);
        ArrayList[] lista = peli.getAlue().getRuudukko();
        ArrayList<Ruutu> ruudut = lista[0];
        assertEquals(true, ruudut.get(0).isLukittu());
    }

    /**
     * Test of avaaRuutu method, of class Peli.
     */
    @Test
    public void testAvaaRuutu() {
        int x = 0;
        int y = 0;
        Peli pjeli = new Peli(new Alue(3, 0));
        pjeli.avaaRuutu(x, y);
        ArrayList[] lista = pjeli.getAlue().getRuudukko();
        ArrayList<Ruutu> ruudut = lista[0];
        assertEquals(true, ruudut.get(0).isAvattu());
        assertEquals(false, pjeli.isLahetetty());
    }

    @Test
    public void testAvaaRuutuLukitsee() {
        int x = 0;
        int y = 0;
        Peli peli = new Peli(new Alue(3, 55));
        peli.avaaRuutu(x, y);
        ArrayList[] lista = peli.getAlue().getRuudukko();
        ArrayList<Ruutu> ruudut = lista[0];
        assertEquals(true, ruudut.get(0).isLukittu());
    }

    /**
     * Test of jatka method, of class Peli.
     */
    @Test
    public void testJatka() {
        System.out.println("jatka");
        Peli peli = new Peli(new Alue(3, 1));
        peli.jatka();
        assertEquals(peli.isHavio(), false);
        assertEquals(peli.getMenetykset(), 1);

    }

    /**
     * Test of etene method, of class Peli.
     */
    @Test
    public void testEtene() {
        System.out.println("etene");
        Peli peli = new Peli(new Alue(3, 1));
        peli.etene();
        assertEquals(peli.getKello().getSekunnit(), 1);
    }

    /**
     * Test of kelloAika method, of class Peli.
     */
    @Test
    public void testKelloAika() {
        System.out.println("kelloAika");
        Peli peeli = new Peli(new Alue(3, 1));
        String tulos = peeli.kelloAika();
        assertEquals(tulos, "00:00:00");

    }

    @Test
    public void testPisteenLasku() {
        Peli peeli = new Peli(new Alue(1, 1));
        int tulos = peeli.pisteet();
        assertEquals(0, tulos);

    }

}
