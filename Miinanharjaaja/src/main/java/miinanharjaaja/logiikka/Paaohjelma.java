/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miinanharjaaja.logiikka;

import java.io.FileNotFoundException;
import java.io.IOException;
import miinanharjaaja.kayttoliittyma.*;
import miinanharjaaja.pisteet.HuippupisteManageri;

public class Paaohjelma {

    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException {
        HuippupisteManageri manageri = new HuippupisteManageri();
        manageri.lisaaPisteet("pentti", 1);
        manageri.lisaaPisteet("Antti", 1);
        manageri.lisaaPisteet("Tomi", 1);
        manageri.lisaaPisteet("Soimo", 1);
        manageri.lisaaPisteet("Heuikki", 1);
        Paaikkuna paaikkuna = new Paaikkuna();
        paaikkuna.run();
    }

}
