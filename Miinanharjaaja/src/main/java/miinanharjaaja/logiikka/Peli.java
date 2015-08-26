package miinanharjaaja.logiikka;

import miinanharjaaja.logiikka.*;
import java.util.Scanner;

/**
 * Pelin tilaa ylläpitävä luokka joka yhdistää logiikan elementit
 * 
 */

public class Peli {

    private Alue alue;
    private boolean havio;
    private boolean voitto;
    private int menetykset;
    private Kello kello;
    private boolean lahetetty;

    public Peli(Alue alue) {
        this.alue = alue;
        this.menetykset = 0;
        this.havio = false;
        this.lahetetty = false;
        this.voitto = false;
        this.kello = new Kello();
    }

    public boolean isHavio() {
        return havio;
    }

    public boolean isLahetetty() {
        return lahetetty;
    }

    public void setLahetetty(boolean lahetetty) {
        this.lahetetty = lahetetty;
    }

    public Alue getAlue() {
        return alue;
    }

    public boolean havio() {
        kello.setPaalla(false);
        return this.havio;
    }

    public Kello getKello() {
        return kello;
    }

    public boolean voitto() {
        if (alue.getAvatutRuudut() == 1) {
            kello.setPaalla(false);
            return true;
        } else {
            return false;
        }
    }

    public int getMenetykset() {
        return menetykset;
    }

    public void lukitseRuutu(int x, int y) {
        this.alue.lukitseRuutu(x, y);
    }

    public void avaaRuutu(int x, int y) {
        boolean tulos = this.alue.avaa(x, y);
        if (tulos) {
            this.havio = true;
            lukitseRuutu(x, y);
        }
    }

    public void jatka() {
        this.havio = false;
        menetykset++;
    }
    
    public void etene() {
        this.kello.etene();
    }
    
    public String kelloAika() {
        return this.kello.toString();
    }

    public int pisteet() {
        return (int) (alue.getX() * alue.getAvatutRuudut() * alue.getTaso() * 10 * Math.pow(0.93, menetykset));
    }
}
