package miinanharjaaja.logiikka;

import java.util.ArrayList;

/**
 * Alue on ruutuja säilövä luokka, jonka pohjalta kenttä luodaan
 */
public class Alue {

    private int x;
    private int ruudut;
    private int taso;
    private int avatutRuudut;
    private ArrayList<Ruutu>[] ruudukko;

    /**
     * 
     * @param x monta ruutua per rivi
     * @param taso vaikeustaso
     */
    public Alue(int x, int taso) {
        this.x = x;
        this.avatutRuudut = 0;
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

    public int getRuudut() {
        return ruudut;
    }

    public int getTaso() {
        return taso;
    }

    public int getX() {
        return x;
    }

    /**
     * Alustaa alueen luomalla ruudut
     */
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

    public int getARuudut() {
        return avatutRuudut;
    }

    /**
     * Avaa koordinaattien mukaisen ruudun ja palauttaa true, jos kyseessä oli
     * miina, false jos ei
     *
     * @param z x koordinaatti
     * @param t y koordinaatti
     * @return miinastatus
     */
    public boolean avaa(int z, int t) {
        ArrayList<Ruutu> lista = this.ruudukko[z];
        if (lista.get(t).isMiina() && !lista.get(t).isLukittu()) {
            return true;
        } else if (!lista.get(t).isAvattu() && !lista.get(t).isLukittu()) {
            this.avatutRuudut++;
            lista.get(t).setAvattu(true);
            avaaViereiset(z, t);
            return false;
        }
        return false;
    }

    /**
     * Vaihdaa ruudun lukkeutumistilaa
     *
     * @param z x koordinaatti
     * @param t y koordinaatti
     */
    public void lukitseRuutu(int z, int t) {
        ArrayList<Ruutu> lista = this.ruudukko[z];
        if (!lista.get(t).isAvattu()) {
            if (lista.get(t).isLukittu()) {
                lista.get(t).setLukittu(false);
            } else {
                lista.get(t).setLukittu(true);
            }
        }
    }

    /**
     * Avauksen apumetodi, avaa ruudun viereiset ruudut, jos niissä ei oel
     * miinaa, käyttää oikeavasen ja ylempialempi metodeja hyväkseen
     *
     * @param z x koordinaatti
     * @param t y koordinaatti
     */
    public void avaaViereiset(int z, int t) {
        ArrayList<Ruutu> lista = this.ruudukko[z];
        boolean avaa = false;
        if (lista.get(t).getViereisetMiinat() == 0) {
            avaa = true;
        }
        avaaViereisetYlempiAlempi(z, lista, t, avaa);
        avaaViereisetOikeaVasen(z, t, avaa);
        avaaViereisetPoikittain(z, t, avaa);
    }

    private void avaaViereisetPoikittain(int z, int t, boolean avaus) {
        ArrayList<Ruutu> tama = this.ruudukko[z];
        avaaPoikittainOikeaPuoli(z, t, avaus);
        avaaPoikittainVasenPuoli(z, t, avaus);
    }

    private void avaaPoikittainVasenPuoli(int z, int t, boolean avaa) {
        ArrayList<Ruutu> lista;
        if (z + 1 < x && t - 1 >= 0) {
            lista = this.ruudukko[z + 1];
            if (!lista.get(t - 1).isMiina() && !lista.get(t - 1).isAvattu() && (avaa)) {
                avaaP(z + 1, t - 1);
            }
        }
        if (z - 1 >= 0 && t - 1 >= 0) {
            lista = this.ruudukko[z - 1];
            if (!lista.get(t - 1).isMiina() && !lista.get(t - 1).isAvattu() && (avaa)) {
                avaaP(z - 1, t - 1);
            }
        }
    }

    private void avaaPoikittainOikeaPuoli(int z, int t, boolean avaa) {
        ArrayList<Ruutu> lista;
        if (z + 1 < x && t + 1 < x) {
            lista = this.ruudukko[z + 1];
            if (!lista.get(t + 1).isMiina() && !lista.get(t + 1).isAvattu() && (avaa)) {
                avaaP(z + 1, t + 1);
            }
        }
        if (z - 1 >= 0 && t + 1 < x) {
            lista = this.ruudukko[z - 1];
            if (!lista.get(t + 1).isMiina() && !lista.get(t + 1).isAvattu() && (avaa)) {
                avaaP(z - 1, t + 1);
            }
        }
    }

    private void avaaViereisetOikeaVasen(int z, int t, boolean avaa) {
        ArrayList<Ruutu> lista;
        if (z + 1 < x) {
            lista = this.ruudukko[z + 1];
            if (!lista.get(t).isMiina() && !lista.get(t).isAvattu() && (avaa || lista.get(t).getViereisetMiinat() == 0)) {
                avaa(z + 1, t);
            }
        }
        if (z - 1 >= 0) {
            lista = this.ruudukko[z - 1];
            if (!lista.get(t).isMiina() && !lista.get(t).isAvattu() && (avaa || lista.get(t).getViereisetMiinat() == 0)) {
                avaa(z - 1, t);
            }
        }
    }

    private void avaaViereisetYlempiAlempi(int z, ArrayList<Ruutu> lista, int t, boolean avaa) {

        if (t + 1 < x) {
            if (!lista.get(t + 1).isMiina() && !lista.get(t + 1).isAvattu() && (avaa || lista.get(t + 1).getViereisetMiinat() == 0)) {
                avaa(z, t + 1);
            }
        }
        if (t - 1 >= 0) {
            if (!lista.get(t - 1).isMiina() && !lista.get(t - 1).isAvattu() && (avaa || lista.get(t - 1).getViereisetMiinat() == 0)) {
                avaa(z, t - 1);
            }
        }
    }

    /**
     * Miinojen luonnin jälkeen asettaa kaikille miinoille oikean määrän
     * viereisiä miinoja
     *
     * @param i x koordinaatti
     * @param j y koordinaatti
     */
    public void lisaaMiina(int i, int j) {
        ArrayList<Ruutu> lista = this.ruudukko[i];
        if (j - 1 >= 0) {
            lista.get(j - 1).setViereisetMiinat(1);
        }
        if (j + 1 < x) {
            lista.get(j + 1).setViereisetMiinat(1);
        }
        lisaaMiinaVasemmalle(i, j);
        lisaaMiinaOikealle(i, j);
    }

    /**
     * Antaa tiedon oikealla olevalle miinan sijainnista
     */
    private void lisaaMiinaOikealle(int i, int j) {
        ArrayList<Ruutu> lista;
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

    /**
     * Antaa tiedon vasemmalla olevalle miinan sijainnista
     */
    private void lisaaMiinaVasemmalle(int i, int j) {
        ArrayList<Ruutu> lista;
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
    }

    public void setRuudut(int ruudut) {
        this.ruudut = ruudut;
    }

    /**
     * Metodi jolla voidaan tarkistaa, onko peli voitettu, palauttaa 1 jos
     * kaikki ruudut on avattu
     *
     * @return Palauttaa avattujen ruutujen määrän
     */
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
        if (avatut == 0 && this.ruudut == 0) {
            return 0;
        }
        return avatut / this.ruudut;
    }

    /**
     * Avaa ruudun, mutta ei avaa viereisiä joka tapauksessa
     * @param z x koordinaatti
     * @param t y koordinaatti
     */
    public void avaaP(int z, int t) {
        ArrayList<Ruutu> lista = this.ruudukko[z];
        if (lista.get(t).isMiina() && !lista.get(t).isLukittu()) {
            return;
        } else if (!lista.get(t).isAvattu() && !lista.get(t).isLukittu()) {
            this.avatutRuudut++;
            lista.get(t).setAvattu(true);
            if (lista.get(t).getViereisetMiinat() == 0) {
                avaaViereiset(z, t);
            }
        }
    }

}
