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

    private Alue seutu;

    public AlueTest() {
        this.seutu = new Alue(2, 1);
    }

    @Test
    public void testGetX() {
        Alue alue = new Alue(2, 1);
        int expResult = 2;
        int result = alue.getX();
        assertEquals(expResult, result);
    }

    @Test
    public void testAvaaPienin() {
        int z = 0;
        int t = 0;
        ArrayList<Ruutu>[] lista = this.seutu.getRuudukko();
        boolean result = this.seutu.avaa(z, t);
        assertEquals(lista[0].get(0).isMiina(), result);
    }

    @Test
    public void avaaViereisetAvaaPienenOsan() {
        Alue alue = new Alue(3, 500);
        ArrayList<Ruutu>[] lista = alue.getRuudukko();
        lista[0].get(1).setMiina(false);
        lista[0].get(0).setMiina(false);
        lista[1].get(0).setMiina(false);
        lista[0].get(0).setMiinamaara(0);
        lista[0].get(1).setMiinamaara(3);
        alue.avaa(0, 1);
        assertEquals(true, lista[0].get(0).isAvattu());
    }

    @Test
    public void avaaPoikittainAvaaPoikittain() {
        Alue alue = new Alue(5, 50);
        ArrayList<Ruutu>[] lista = alue.getRuudukko();
        lista[1].get(1).setMiina(false);
        lista[2].get(1).setMiina(false);
        lista[3].get(1).setMiina(false);
        lista[1].get(2).setMiina(false);
        lista[2].get(2).setMiina(false);
        lista[3].get(2).setMiina(false);
        lista[1].get(3).setMiina(false);
        lista[2].get(3).setMiina(false);
        lista[3].get(3).setMiina(false);
        lista[2].get(2).setMiinamaara(0);
        alue.avaa(2, 2);
        assertEquals(true, lista[1].get(1).isAvattu());
        assertEquals(true, lista[3].get(3).isAvattu());
        assertEquals(true, lista[1].get(3).isAvattu());
        assertEquals(true, lista[3].get(1).isAvattu());
    }

    @Test
    public void avaaPoikittainAvaaOikeaVasen() {
        Alue alue = new Alue(5, 50);
        ArrayList<Ruutu>[] lista = alue.getRuudukko();
        lista[1].get(2).setMiina(false);
        lista[2].get(2).setMiina(false);
        lista[3].get(2).setMiina(false);
        lista[2].get(2).setMiinamaara(0);
        alue.avaa(2, 2);
        assertEquals(true, lista[1].get(2).isAvattu());
        assertEquals(true, lista[3].get(2).isAvattu());

    }

    @Test
    public void avaaAvaaYlosAlas() {
        Alue alue = new Alue(5, 50);
        ArrayList<Ruutu>[] lista = alue.getRuudukko();
        lista[2].get(1).setMiina(false);
        lista[2].get(2).setMiina(false);
        lista[2].get(3).setMiina(false);
        lista[2].get(2).setMiinamaara(0);
        alue.avaa(2, 2);
        assertEquals(true, lista[2].get(1).isAvattu());
        assertEquals(true, lista[2].get(3).isAvattu());

    }

    @Test
    public void avaaViereisetEiAvaaMiinoja() {
        Alue alue = new Alue(3, 50);
        ArrayList<Ruutu>[] lista = alue.getRuudukko();
        lista[0].get(1).setMiina(false);
        lista[0].get(0).setMiina(true);
        alue.avaaViereiset(0, 1);
        alue.setRuudut(0);
        assertEquals(0, alue.getAvatutRuudut(), .1);
    }

    @Test
    public void avaaViereisetAvaaKaikki() {
        Alue alue = new Alue(3, 0);
        alue.avaaViereiset(0, 0);
        alue.setRuudut(9);
        assertEquals(1, alue.getAvatutRuudut(), .1);
    }

    @Test
    public void testAvaaSuurin() {
        int z = 1;
        int t = 1;
        ArrayList<Ruutu>[] lista = this.seutu.getRuudukko();
        boolean result = this.seutu.avaa(z, t);
        assertEquals(lista[z].get(t).isMiina(), result);
    }

    @Test
    public void testAvaaMiina() {
        Alue alue = new Alue(3, 444);
        int z = 1;
        int t = 1;
        ArrayList<Ruutu>[] lista = alue.getRuudukko();
        boolean result = alue.avaa(z, t);
        assertEquals(true, result);
    }

    @Test
    public void testAvaaPRuutu() {
        Alue alue = new Alue(3, 444);
        int z = 1;
        int t = 1;
        ArrayList<Ruutu>[] lista = alue.getRuudukko();
        lista[1].get(1).setMiina(false);
        alue.avaaP(z, t);
        assertEquals(true, lista[1].get(1).isAvattu());
    }

    @Test
    public void testAvaaPViereiset() {
        Alue alue = new Alue(3, 0);
        int z = 1;
        int t = 1;
        ArrayList<Ruutu>[] lista = alue.getRuudukko();
        alue.avaaP(z, t);
        assertEquals(true, lista[2].get(2).isAvattu());
        assertEquals(true, lista[0].get(0).isAvattu());
        assertEquals(true, lista[0].get(2).isAvattu());
        assertEquals(true, lista[2].get(0).isAvattu());
    }

    @Test
    public void testAvaaPMiina() {
        Alue alue = new Alue(3, 444);
        int z = 1;
        int t = 1;
        ArrayList<Ruutu>[] lista = alue.getRuudukko();
        alue.avaaP(z, t);
        assertEquals(false, lista[1].get(1).isAvattu());
    }

    @Test
    public void testAvaaAvaaLukittua() {
        int z = 1;
        int t = 1;
        ArrayList<Ruutu>[] lista = seutu.getRuudukko();
        seutu.lukitseRuutu(z, t);
        boolean result = seutu.avaa(z, t);
        assertEquals(lista[z].get(t).isAvattu(), result);
    }

    @Test
    public void testAvaaKayttaaKaikkienavaajaa() {
        Alue alue = new Alue(3, 0);
        alue.avaa(0, 0);
        assertEquals(1, alue.getAvatutRuudut(), .1);
    }

    @Test
    public void testGetAvatutRuudutNolla() {
        Alue alue = new Alue(2, 1);
        double odotett = 0.0;
        double tulos = alue.getAvatutRuudut();
        assertEquals(odotett, tulos, 0.0);
    }

    @Test
    public void testLukitseRuutuLukitseeRuudun() {
        Alue alue = new Alue(2, 0);
        alue.lukitseRuutu(0, 0);
        ArrayList<Ruutu>[] lista = alue.getRuudukko();
        assertEquals(true, lista[0].get(0).isLukittu());
    }

    @Test
    public void testLukitseRuutuPoistaaLukitusRuudunLukituksen() {
        Alue alue = new Alue(2, 0);
        alue.lukitseRuutu(0, 0);
        ArrayList<Ruutu>[] lista = alue.getRuudukko();
        assertEquals(true, lista[0].get(0).isLukittu());
        alue.lukitseRuutu(0, 0);
        assertEquals(false, lista[0].get(0).isLukittu());
    }

    @Test
    public void testGetAvatutRuudutYksi() {
        Alue alue = new Alue(3, 0);
        ArrayList<Ruutu>[] lista = alue.getRuudukko();
        lista[1].get(1).setAvattu(true);
        double expResult = 1 / 9;
        double result = alue.getAvatutRuudut();
        assertEquals(expResult, result, result);
    }

    @Test
    public void testLisaaMiinaKeskella() {
        Alue alue = new Alue(3, 0);
        alue.lisaaMiina(1, 1);
        ArrayList<Ruutu>[] lista = alue.getRuudukko();
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
    public void alustaAlueEiTeeMitaanJosNegatiivinenX() {
        Alue alue = new Alue(-1, 0);
        alue.alustaAlue();
        assertArrayEquals(alue.getRuudukko(), null);
    }

    @Test
    public void alustaAlueLuoAlueen() {
        Alue alue = new Alue(2, 50);
        ArrayList<Ruutu>[] lista = alue.getRuudukko();
        assertEquals(true, lista[0].get(0).isMiina());
        assertEquals(true, lista[1].get(1).isMiina());
    }

    @Test
    public void testLisaaMiinaVasenKulma() {
        Alue alue = new Alue(3, 0);
        alue.lisaaMiina(0, 0);
        ArrayList<Ruutu>[] lista = alue.getRuudukko();
        assertEquals(1, lista[1].get(0).getViereisetMiinat());
        assertEquals(1, lista[1].get(1).getViereisetMiinat());
        assertEquals(1, lista[0].get(1).getViereisetMiinat());
    }

    @Test
    public void testLisaaMiinaOikeaKulma() {
        Alue alue = new Alue(3, 0);
        alue.lisaaMiina(2, 2);
        ArrayList<Ruutu>[] lista = alue.getRuudukko();
        assertEquals(1, lista[1].get(2).getViereisetMiinat());
        assertEquals(1, lista[1].get(1).getViereisetMiinat());
        assertEquals(1, lista[2].get(1).getViereisetMiinat());
    }

    @Test
    public void testGetAvatutRuudutKaikki() {
        Alue alue = new Alue(2, 0);
        ArrayList<Ruutu>[] lista = alue.getRuudukko();
        alue.avaa(0, 0);
        double odotettuTulos = 1;
        double tulos = alue.getAvatutRuudut();
        assertEquals(odotettuTulos, tulos, tulos);
    }

}
