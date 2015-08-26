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
        Paaikkuna paaikkuna = new Paaikkuna();
        paaikkuna.run();
    }

}
