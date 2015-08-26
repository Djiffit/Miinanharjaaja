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
 *
 * @author Konsta
 */
public class HuippupisteManageri {

    private ArrayList<Pisteet> pistelista;
    private static final String HUIPPUPISTE_TIEDOSTO = "huippupiste.dat";
    ObjectOutputStream outputStream = null;
    ObjectInputStream inputStream = null;

    public HuippupisteManageri() {
        this.pistelista = new ArrayList<Pisteet>();
    }

    public ArrayList<Pisteet> getPisteet() throws IOException, FileNotFoundException, ClassNotFoundException {
        lataaPisteet();
        jarjesta();
        return pistelista;
    }

    public void lisaaPisteet(String nimi, int pojot) throws IOException, FileNotFoundException, ClassNotFoundException {
        lataaPisteet();
        pistelista.add(new Pisteet(nimi, pojot));
        paivitaTiedosto();
    }

    public void jarjesta() {
        Collections.sort(pistelista);
    }

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
