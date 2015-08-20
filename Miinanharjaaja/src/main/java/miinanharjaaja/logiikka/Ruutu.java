/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miinanharjaaja.logiikka;

import miinanharjaaja.logiikka.*;
import java.util.Random;

/**
 * Pelikentän ruudun luokka, joka sisältää ruudun ominaisuudet
 */

public class Ruutu {

    private boolean miina;
    private int taso;
    private boolean lukittu;
    private int viereisetMiinat;
    private boolean avattu;

    public Ruutu(int taso) {
        this.taso = taso;
        this.lukittu = false;
        this.avattu = false;
        this.miina = laskeMiina();
        this.viereisetMiinat = 0;
    }

    public void setMiina(boolean miina) {
        this.miina = miina;
    }

    public void setLukittu(boolean lukittu) {
        this.lukittu = lukittu;
    }

    public void setViereisetMiinat(int viereisetMiinat) {
        this.viereisetMiinat = this.viereisetMiinat + viereisetMiinat;
    }

    public void setAvattu(boolean avattu) {
        this.avattu = avattu;
    }

    public boolean isMiina() {
        return miina;
    }

    public boolean isAvattu() {
        return avattu;
    }

    public boolean isLukittu() {
        return lukittu;
    }

    public int getViereisetMiinat() {
        return viereisetMiinat;
    }
    
    /**
     * Laskee onko ruutu miina vaikeustason pohjalta
     */


    public boolean laskeMiina() {
        Random luku = new Random();
        double sattuma = luku.nextDouble();
        if (sattuma <= taso * 0.15) {
            return true;
        } else {
            return false;
        }
    }
}
