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

    void kaynnista() {
        pelaa();
        tulosta("Paina enter jatkaaksesi");

    }

    private static String pelaaja() {
        Scanner lukija = new Scanner(System.in);
        return lukija.nextLine();
    }

    private static void tulosta(String teksti) {
        System.out.println(teksti);
    }

    void pelaa() {
        while (true) {
            this.alue.tulostaAlue();
            tulosta("Anna x-koord");
            int y = Integer.parseInt(pelaaja());
            tulosta("Anna y-koord");
            int x = Integer.parseInt(pelaaja());
            if (x <= this.alue.getX() && y <= this.alue.getX() && x >= 1 && y >= 1) {
                this.havio = this.alue.avaa(x - 1, y - 1);
            }
            if (this.havio) {
                if (havio()) {
                    break;
                }
            }
            if (alue.getAvatutRuudut() == 1) {
                voitto();
                break;
            }
        }
    }

    private boolean havio() {
        tulosta("Harjaaja menetetty");
        this.menetykset++;
        tulosta("Haluatko jatkaa? K/E");
        if (pelaaja().toLowerCase().equals("e")) {
            this.havio = false;
            this.alue.tulostaAlueHavio();
            tulosta("Se oli siinä, löysit " + alue.getAvatutRuudut() + " turvallista ruutua.");
            return true;
        } else {
            this.havio = false;
            return false;
        }
    }

    private void voitto() {
        this.alue.tulostaAlueHavio();
        tulosta("Onnittelut! Olet miinan har jaa ja. " + this.menetykset + " harjaajaa menetettiin tämän alueen siivoamisessa.");
    }
}
