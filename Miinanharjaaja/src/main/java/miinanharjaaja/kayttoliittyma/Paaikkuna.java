/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miinanharjaaja.kayttoliittyma;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.PopupMenu;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import miinanharjaaja.logiikka.Alue;
import miinanharjaaja.logiikka.Peli;

/**
 *
 * @author Konsta
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

    @Override
    public void run() {
        alustaLiittyma();
        long lastTime = System.nanoTime();
        double ns = 1000000000 / FPS;
        int updates = 0;
        double delta = 0;
        long timer = System.currentTimeMillis();
        while (running) {
            if (peli == null && state.getState() == state.palautaPeli()) {
                int ruutuja = -1;
                while (ruutuja < 1 || ruutuja > 100) {
                    ruutuja = Integer.parseInt(JOptionPane.showInputDialog(this, "Monta ruutua? Max. 99", null));
                }
                int taso = Integer.parseInt(JOptionPane.showInputDialog(this, "Vaikeustaso 0-4", null));
                peli = new Peli(new Alue(ruutuja, taso));
                piirtaja.setAlue(peli.getAlue());
                hiiri.setAlue(peli.getAlue());
            }
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
            }

        }

    }

    public void draw() {
        state.setX(frame.getWidth());
        state.setY(frame.getHeight());
        frame.repaint();
    }

    private void alustaLiittyma() {
        frame = new JFrame("Miinanharjaaja");
        frame.setPreferredSize(new Dimension(width, height));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        luoKomponentit(frame);
        running = true;
        frame.setVisible(true);
        frame.addMouseListener(hiiri);
        frame.addKeyListener(new Nappaimisto(state));
    }

    private void luoKomponentit(Container container) {
        container.add(piirtaja);
    }

}
