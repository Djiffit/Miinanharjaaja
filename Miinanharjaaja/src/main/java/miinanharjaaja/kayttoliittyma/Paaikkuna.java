/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miinanharjaaja.kayttoliittyma;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.PopupMenu;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import miinanharjaaja.logiikka.Alue;
import miinanharjaaja.logiikka.Peli;
import miinanharjaaja.pisteet.HuippupisteManageri;

/**
 * Pääikkuna alustaa käyttöliittymän, ja pitää huolen tasaisesta
 * päivitysnopeudesta
 */
public class Paaikkuna extends JFrame implements Runnable {

    private JFrame frame;
    public int height = 1020;
    public int width = 1813;
    private Peli peli;
    private boolean running = false;

    private double FPS = 60;
    private double targetTime = 1000 / FPS;

    private Tila state = new Tila(width, height);

    private Piirtaja piirtaja = new Piirtaja(state);
    private Hiiri hiiri = new Hiiri(state);
    private HuippupisteManageri manageri = new HuippupisteManageri();

    /**
     * Pelin päälooppi, joka alustaa käyttöliittymän ja pelin
     */
    @Override
    public void run() {
        String nimi = "p";
        alustaLiittyma();
        long lastTime = System.nanoTime();
        double ns = 1000000000 / FPS;
        int updates = 0;
        double delta = 0;
        long timer = System.currentTimeMillis();
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                delta--;
                updates++;
                draw();
            }
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println(updates + " Updates");
                updates = 0;
                if (state.getState() == state.palautaPeli()) {
                    state.getPeli().etene();
                }
            }
            if (state.getPeli() == null && state.getState() == state.palautaPeli()) {
                state.updatePeli();
            }
            if (state.getPeli() != null && state.getPeli().havio()) {
                pelinJatkaminen();
            }
            if (state.getPeli() != null && state.getPeli().voitto()) {
                tilanteenaVoitto();
            }

        }

    }

    private void tilanteenaVoitto() throws HeadlessException {
        String nimi;
        piirtaja.voitto();
        if (!state.getPeli().isLahetetty()) {
            nimi = JOptionPane.showInputDialog("Anna nimesi!");
            int piste = state.getPeli().pisteet();
            try {
                manageri.lisaaPisteet(nimi, piste);
            } catch (IOException ex) {
                Logger.getLogger(Paaikkuna.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Paaikkuna.class.getName()).log(Level.SEVERE, null, ex);
            }
            state.getPeli().setLahetetty(true);
        }
    }

    private void pelinJatkaminen() throws HeadlessException {
        int luku = JOptionPane.showConfirmDialog(this, "Harjaaja menetetty, jatketaanko?", "Häviö", JOptionPane.YES_NO_OPTION);
        if (luku == JOptionPane.YES_OPTION) {
            state.getPeli().jatka();
        } else {
            state.stateMenu();
            state.setPeli(null);
        }
    }

    /**
     * Piirtaa pelin 60 kertaa sekunnissa
     */
    public void draw() {
        state.setX(frame.getWidth());
        state.setY(frame.getHeight());
        frame.repaint();
    }

    private void alustaLiittyma() {
        frame = new JFrame("Miinanharjaaja");
        frame.setPreferredSize(new Dimension(width, height));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(1200, 1020));
        frame.pack();
        luoKomponentit(frame);
        running = true;
        frame.setVisible(true);
        frame.addMouseListener(hiiri);
        frame.addKeyListener(new Nappaimisto(state));
        state.setManageri(manageri);
    }

    private void luoKomponentit(Container container) {
        container.add(piirtaja);
    }

}
