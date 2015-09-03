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
public class RuutuTest {

    private Alue alue;

    public RuutuTest() {
        this.alue = new Alue(3, 100);
    }

    /**
     * Test of setMiina method, of class Ruutu.
     */
    @Test
    public void testSetMiina() {
        boolean miina = false;
        Ruutu ruudu = new Ruutu(100);
        ruudu.setMiina(miina);
        assertEquals(false, ruudu.isMiina());
    }

    /**
     * Test of setViereisetMiinat method, of class Ruutu.
     */
    @Test
    public void testSetViereisetMiinat() {
        int viereisetMiinat = 1;
        Ruutu ruutu = new Ruutu(0);
        ruutu.setViereisetMiinat(viereisetMiinat);
        assertEquals(1, ruutu.getViereisetMiinat());
    }

    /**
     * Test of laskeMiina method, of class Ruutu.
     */
    @Test
    public void testLaskeMiinaNolla() {
        Alue alueNolla = new Alue(3, 0);
        int miinojenMaara = 0;
        ArrayList[] ruudukko = alueNolla.getRuudukko();
        for (int i = 0; i < ruudukko.length; i++) {
            for (int j = 0; j < ruudukko.length; j++) {
                ArrayList<Ruutu> lista = ruudukko[i];
                if (lista.get(j).isMiina()) {
                    miinojenMaara++;
                }
            }
        }
        assertEquals(0, miinojenMaara);
    }
    
        @Test
    public void testLaskeMiinaSata() {
        Alue alueNolla = new Alue(3, 1000);
        int miinojenMaara = 0;
        ArrayList[] ruudukko = alueNolla.getRuudukko();
        for (int i = 0; i < ruudukko.length; i++) {
            for (int j = 0; j < ruudukko.length; j++) {
                ArrayList<Ruutu> lista = ruudukko[i];
                if (lista.get(j).isMiina()) {
                    miinojenMaara++;
                }
            }
        }
        assertEquals(9, miinojenMaara);
    }

}
