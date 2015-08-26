/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miinanharjaaja.pisteet;

import java.io.Serializable;

public class Pisteet implements Serializable, Comparable<Pisteet> {

    private int pisteet;
    private String nimi;

    public int getPisteet() {
        return pisteet;
    }

    public String getNimi() {
        return nimi;
    }

    public Pisteet(String nimi, int pisteet) {
        this.pisteet = pisteet;
        this.nimi = nimi;
    }

    @Override
    public int compareTo(Pisteet o) {
        if(this.pisteet > o.getPisteet()) {
            return 1;
        } else if(this.pisteet == o.getPisteet()) {
            return 0;
        } else {
            return -1;
        }
    }
}
