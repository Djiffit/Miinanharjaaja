/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miinanharjaaja.kayttoliittyma;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import miinanharjaaja.pisteet.HuippupisteManageri;

/**
 * Pääikkuna alustaa käyttöliittymän, ja pitää huolen tasaisesta
 * päivitysnopeudesta
 */
public class Paaikkuna extends JFrame implements Runnable {

    private JFrame frame;
    public int height;
    public int width;
    private boolean running;
    private double FPS;
    private double targetTime;
    private Tila tila;
    private Piirtaja piirtaja;
    private Hiiri hiiri;
    private HuippupisteManageri manageri;

    public Paaikkuna() {
        height = 1020;
        width = 1813;
        running = false;
        FPS = 60;
        targetTime = 1000 / FPS;
        tila = new Tila(width, height);
        piirtaja = new Piirtaja(tila);
        hiiri = new Hiiri(tila);
        manageri = new HuippupisteManageri();
    }

    /**
     * Pelin päälooppi, joka alustaa käyttöliittymän ja pelin
     */
    @Override
    public void run() {
        alustaLiittyma();
        long lastTime = System.nanoTime();
        double ns = 1000000000 / FPS;
        int paivityksia = 0;
        double muutos = 0;
        long timer = System.currentTimeMillis();
        while (running) {
            long now = System.nanoTime();
            muutos += (now - lastTime) / ns;
            lastTime = now;
            if (muutos >= 1) {
                muutos--;
                paivityksia++;
                draw();
            }
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println(paivityksia + " Updates");
                paivityksia = 0;
                if (tila.getState() == tila.palautaPeli()) {
                    tila.getPeli().etene();
                }
            }
            if (tila.getPeli() == null && tila.getState() == tila.palautaPeli()) {
                tila.updatePeli();
            }
            if (tila.getPeli() != null && tila.getPeli().havio()) {
                pelinJatkaminen();
            }
            if (tila.getPeli() != null && tila.getPeli().voitto()) {
                tilanteenaVoitto();
            }

        }

    }

    private void tilanteenaVoitto() throws HeadlessException {
        String nimi;
        piirtaja.voitto();
        if (!tila.getPeli().isLahetetty()) {
            while (true) {
                nimi = JOptionPane.showInputDialog("Anna nimesi! 3-15 merkkiä");
                if (nimi != null && (nimi.length() < 15 && nimi.length() > 2)) {
                    break;
                }
            }
            int piste = tila.getPeli().pisteet();
            try {
                manageri.lisaaPisteet(nimi, piste);

            } catch (IOException ex) {
                Logger.getLogger(Paaikkuna.class
                        .getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Paaikkuna.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            tila.getPeli().setLahetetty(true);
        }
    }

    private void pelinJatkaminen() throws HeadlessException {
        int luku = JOptionPane.showConfirmDialog(this, "Harjaaja menetetty, jatketaanko?", "Häviö", JOptionPane.YES_NO_OPTION);
        if (luku == JOptionPane.YES_OPTION) {
            tila.getPeli().jatka();
        } else {
            tila.stateMenu();
            tila.setPeli(null);
        }
    }

    /**
     * Piirtaa pelin 60 kertaa sekunnissa
     */
    public void draw() {
        tila.setX(frame.getWidth());
        tila.setY(frame.getHeight());
        frame.repaint();
    }

    private void alustaLiittyma() {
        frame = new JFrame("Miinanharjaaja");
        frame.setPreferredSize(new Dimension(width, height));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(1500, 1020));
        frame.pack();
        luoKomponentit(frame);
        running = true;
        frame.setVisible(true);
        frame.addMouseListener(hiiri);
        frame.addKeyListener(new Nappaimisto(tila));
        tila.setManageri(manageri);
    }

    private void luoKomponentit(Container container) {
        container.add(piirtaja);
    }

}
