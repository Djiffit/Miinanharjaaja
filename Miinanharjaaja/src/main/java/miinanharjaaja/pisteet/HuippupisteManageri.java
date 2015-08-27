/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miinanharjaaja.pisteet;

import java.io.Console;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Hoitaa parhaiden pisteiden listaa ylläpitävän tiedoston kirjoittamisen ja lukemisen ja sen muuttamisen listaksi
 * 
 */
public class HuippupisteManageri {

    private ArrayList<Pisteet> pistelista;
    private static final String HUIPPUPISTE_TIEDOSTO = "huippupiste.dat";
    ObjectOutputStream outputStream = null;
    ObjectInputStream inputStream = null;

    /**
     *
     */
    public HuippupisteManageri() {
        this.pistelista = new ArrayList<Pisteet>();
    }
    
    /**
     * Lataa pisteet tiedostosta ja järjestää ne ja palauttaa järjestetyn listan
     * 
     * 
     * @return järjestetty pistelista
     * @throws java.io.IOException 
     * @throws java.io.FileNotFoundException 
     * @throws java.lang.ClassNotFoundException 
     */

    public ArrayList<Pisteet> getPisteet() throws IOException, FileNotFoundException, ClassNotFoundException {
        lataaPisteet();
        jarjesta();
        return pistelista;
    }
    
    /**
     * Lataa pisteet tiedostosta ja päivittää sen uudella merkinnällä
     * 
     * 
     * @param nimi pelaajan nimimerkki
     * @param pojot pelaajan pisteet
     * @throws java.io.IOException
     * @throws java.io.FileNotFoundException
     * @throws java.lang.ClassNotFoundException
     */

    public void lisaaPisteet(String nimi, int pojot) throws IOException, FileNotFoundException, ClassNotFoundException {
        lataaPisteet();
        pistelista.add(new Pisteet(nimi, pojot));
        paivitaTiedosto();
    }
    
    /**
     * 
     * Järjestää listan
     * 
     */

    public void jarjesta() {
        Collections.sort(pistelista);
    }
    
    /**
     * Lataa tiedostosta arraylist olion
     * 
     * 
     * @throws java.io.FileNotFoundException
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */

    public void lataaPisteet() throws FileNotFoundException, IOException, ClassNotFoundException {
        try {
            inputStream = new ObjectInputStream(new FileInputStream(HUIPPUPISTE_TIEDOSTO));
            pistelista = (ArrayList<Pisteet>) inputStream.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("FNF Error " + e);
        } catch (IOException e) {
            System.out.println("IOE Error " + e);
        } finally {
            if (outputStream != null) {
                outputStream.flush();
                outputStream.close();
            }
        }
    }
    
    /**
     * 
     * Päivittää olion ArrayListin pistetiedostoon levyllä
     * 
     * @throws java.io.FileNotFoundException
     * @throws java.io.IOException
     */

    public void paivitaTiedosto() throws FileNotFoundException, IOException {
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(HUIPPUPISTE_TIEDOSTO));
            outputStream.writeObject(pistelista);
        } catch (FileNotFoundException e) {
            System.out.println("FNF Error " + e);
        } catch (IOException e) {
            System.out.println("IO Error " + e);
        } finally {
            outputStream.flush();
            outputStream.close();
        }
    }
    
    /**
     * 
     * Palauttaa 20 korkeinta pisteitä saanutta
     * @return top20 lista
     * @throws java.io.IOException
     * @throws java.io.FileNotFoundException
     * @throws java.lang.ClassNotFoundException
     */

    public String getHuippuPisteet() throws IOException, FileNotFoundException, ClassNotFoundException {
        int max = 20;
        if (pistelista.size() < max) {
            max = pistelista.size();
        }
        lataaPisteet();
        Collections.sort(pistelista);
        String palautus = "";
        for (int i = 1; i <= max; i++) {
            palautus += i + ". " + pistelista.get(i - 1).getNimi() + ": " + pistelista.get(i - 1).getPisteet() + "\n";
        }
        return palautus;
    }

   
    
    
}
