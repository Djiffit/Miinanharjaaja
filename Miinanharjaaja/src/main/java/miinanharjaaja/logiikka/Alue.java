package miinanharjaaja.logiikka;

import miinanharjaaja.logiikka.*;
import java.util.ArrayList;

public class Alue {

    private int x;
    private int ruudut;
    private int taso;
    private ArrayList<Ruutu>[] ruudukko;

    public Alue(int x, int taso) {
        this.x = x;
        this.ruudut = 0;
        this.taso = taso;
        if (x >= 1) {
            this.ruudukko = new ArrayList[x];
        }
        alustaAlue();
    }

    public ArrayList[] getRuudukko() {
        return ruudukko;
    }

    public int getX() {
        return x;
    }

    public void alustaAlue() {
        if (x >= 1) {
            for (int i = 0; i < x; i++) {
                this.ruudukko[i] = new ArrayList<Ruutu>();
                for (int j = 0; j < x; j++) {
                    this.ruudukko[i].add(new Ruutu(taso));
                }
            }
            for (int i = 0; i < x; i++) {
                for (int j = 0; j < x; j++) {
                    ArrayList<Ruutu> lista = this.ruudukko[i];
                    if (lista.get(j).isMiina()) {
                        lisaaMiina(i, j);
                    } else {
                        this.ruudut++;
                    }
                }
            }
        }

    }

    public void tulostaAlue() {
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < x; j++) {
                ArrayList<Ruutu> lista = this.ruudukko[i];
                if (lista.get(j).isAvattu()) {
                    System.out.print(lista.get(j).getViereisetMiinat());
                } else {
                    System.out.print("-");
                }
            }
            System.out.println("");
        }
    }

    public void tulostaAlueHavio() {
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < x; j++) {
                ArrayList<Ruutu> lista = this.ruudukko[i];
                if (lista.get(j).isMiina()) {
                    System.out.print("+");
                } else {
                    System.out.print("-");
                }

            }
            System.out.println("");
        }
    }

    boolean avaa(int z, int t) {
        ArrayList<Ruutu> lista = this.ruudukko[z];
        if (lista.get(t).isMiina()) {
            return true;
        } else {
            lista.get(t).setAvattu(true);
            avaaViereiset(z, t);
            return false;
        }
    }

    public void avaaViereiset(int z, int t) {

        ArrayList<Ruutu> lista = this.ruudukko[z];
        if (t + 1 < x) {
            if (!lista.get(t + 1).isMiina() && !lista.get(t + 1).isAvattu()) {
                avaa(z, t + 1);
            }
        }
        if (t - 1 >= 0) {
            if (!lista.get(t - 1).isMiina() && !lista.get(t - 1).isAvattu()) {
                avaa(z, t - 1);
            }
        }
        if (z + 1 < x) {
            lista = this.ruudukko[z + 1];
            if (!lista.get(t).isMiina() && !lista.get(t).isAvattu()) {
                avaa(z + 1, t);
            }
        }
        if (z - 1 >= 0) {
            lista = this.ruudukko[z - 1];
            if (!lista.get(t).isMiina() && !lista.get(t).isAvattu()) {
                avaa(z - 1, t);
            }
        }

    }

    public void lisaaMiina(int i, int j) {
        ArrayList<Ruutu> lista = this.ruudukko[i];
        if (j - 1 >= 0) {
            lista.get(j - 1).setViereisetMiinat(1);
        }
        if (j + 1 < x) {
            lista.get(j + 1).setViereisetMiinat(1);
        }
        if (i - 1 >= 0) {
            lista = this.ruudukko[i - 1];
            lista.get(j).setViereisetMiinat(1);
            if (j - 1 >= 0) {
                lista.get(j - 1).setViereisetMiinat(1);
            }
            if (j + 1 < x) {
                lista.get(j + 1).setViereisetMiinat(1);
            }
        }
        if (i + 1 < x) {
            lista = this.ruudukko[i + 1];
            lista.get(j).setViereisetMiinat(1);
            if (j - 1 >= 0) {
                lista.get(j - 1).setViereisetMiinat(1);
            }
            if (j + 1 < x) {
                lista.get(j + 1).setViereisetMiinat(1);
            }
        }
    }

    public void setRuudut(int ruudut) {
        this.ruudut = ruudut;
    }

    public double getAvatutRuudut() {
        double avatut = 0;
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < x; j++) {
                ArrayList<Ruutu> lista = this.ruudukko[i];
                if (lista.get(j).isAvattu()) {
                    avatut++;
                }
            }
        }
        return avatut / this.ruudut;
    }

}
