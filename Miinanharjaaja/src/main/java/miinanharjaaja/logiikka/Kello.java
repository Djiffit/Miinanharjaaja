/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miinanharjaaja.logiikka;

/**
 *
 * Kelloluokka on pelin kellosta huolehtiva luokka
 */
public class Kello {

    private int sekunnit;
    private int minuutit;
    private int tunnit;
    private boolean paalla;

    public int getSekunnit() {
        return sekunnit;
    }

    public int getMinuutit() {
        return minuutit;
    }

    public int getTunnit() {
        return tunnit;
    }

    public boolean isPaalla() {
        return paalla;
    }

    /**
     * Alustaa kellon
     */
    public Kello() {
        this.paalla = true;
        this.sekunnit = 0;
        this.minuutit = 0;
        this.tunnit = 0;
    }

    public void setSekunnit(int sekunnit) {
        this.sekunnit = sekunnit;
    }

    public void setMinuutit(int minuutit) {
        this.minuutit = minuutit;
    }

    public void setTunnit(int tunnit) {
        this.tunnit = tunnit;
    }
    
    /**
     * Siirtää kelloa sekunnin eteenpäin
     */

    public void etene() {
        if (paalla) {
            this.sekunnit++;
            if (this.sekunnit == 60) {
                this.sekunnit = 0;
                this.minuutit++;
                if (this.minuutit == 60) {
                    this.minuutit = 0;
                    this.tunnit++;
                }
            }
        }

    }

    public void setPaalla(boolean paalla) {
        this.paalla = paalla;
    }

    public String toString() {
        String palautaMinuutit = "" + this.minuutit;
        if (this.minuutit < 10) {
            palautaMinuutit = "0" + this.minuutit;
        }
        String palautaSekunnit = "" + this.sekunnit;
        if (this.sekunnit < 10) {
            palautaSekunnit = "0" + this.sekunnit;
        }
        String palautaTunnit = "" + this.tunnit;
        if (this.tunnit < 10) {
            palautaTunnit = "0" + this.tunnit;
        }
        return palautaTunnit + ":" + palautaMinuutit + ":" + palautaSekunnit;
    }

}
