/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miinanharjaaja.logiikka;

import miinanharjaaja.logiikka.*;
import java.util.Scanner;

public class Paaohjelma {

    public static void main(String[] args) {
        paavalikko();
    }

    private static void tulosta(String teksti) {
        System.out.println(teksti);
    }

    private static String pelaaja() {
        Scanner lukija = new Scanner(System.in);
        return lukija.nextLine();
    }

    private static void paavalikko() {
        while (true) {
            tulosta("Pelaa tai poistu");
            if (pelaaja().toLowerCase().equals("pelaa")) {
                luoPeli();
            }
            if (pelaaja().toLowerCase().equals("poistu")) {
                break;
            }
        }
    }

    private static void luoPeli() {
        tulosta("Ruudukon koko");
        int koko = Integer.parseInt(pelaaja());
        tulosta("Vaikeustaso 0-4");
        int taso = Integer.parseInt(pelaaja());
        Peli peli = new Peli(new Alue(koko, taso));
        peli.kaynnista();
    }

}
