package miinanharjaaja.logiikka;

import miinanharjaaja.logiikka.*;
import java.util.Scanner;

public class Peli {

    private Alue alue;
    private boolean havio;
    private int menetykset;

    public Peli(Alue alue) {
        this.alue = alue;
        this.menetykset = 0;
        this.havio = false;
    }

    public Alue getAlue() {
        return alue;
    }

    public boolean havio() {
        return true;
        //todo
    }

    public void voitto() {
        System.out.println("gee gee");
        //todo
    }
}
