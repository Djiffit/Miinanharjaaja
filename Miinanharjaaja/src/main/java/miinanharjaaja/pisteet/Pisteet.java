/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miinanharjaaja.pisteet;

import java.io.Serializable;
/**
 * Pisteolio, joka pitää sisällään nimen ja pistemäärän, mahdollistaa pisteiden järjestämisen
 * 
 */

public class Pisteet implements Serializable, Comparable<Pisteet> {

    private int piste;
    private String nimi;

    public int getPisteet() {
        return piste;
    }

    public String getNimi() {
        return nimi;
    }

    public Pisteet(String nimi, int pisteet) {
        this.piste = pisteet;
        this.nimi = nimi;
    }

    @Override
    public int compareTo(Pisteet o) {
        if(this.piste > o.getPisteet()) {
            return -1;
        } else if(this.piste == o.getPisteet()) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public String toString() {
        return nimi + ": " + piste; 
    }
}
