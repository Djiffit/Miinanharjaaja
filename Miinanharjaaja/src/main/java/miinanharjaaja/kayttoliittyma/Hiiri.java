/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miinanharjaaja.kayttoliittyma;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.SwingUtilities;
import miinanharjaaja.logiikka.Alue;

public class Hiiri implements MouseListener {

    private Tila tila;

    public void setAlue(Alue alue) {
        this.alue = alue;
    }
    private Alue alue;

    public Hiiri(Tila tila) {
        this.tila = tila;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        if (SwingUtilities.isLeftMouseButton(e)) {
            if (tila.getState() == tila.palautaMenu()) {

                menuToiminnot(mx, my);

            } else {
                int ruutuja = alue.getX();
                double leveys = Math.ceil((mx - (tila.getX() - 900) / 2) / 900.0 * ruutuja);
                double korkeus = Math.ceil((my - 63) / 900.0 * ruutuja);
                if (leveys > 0 && korkeus > 0 && leveys < ruutuja + 1 && korkeus < ruutuja + 1) {
                    alue.avaaRuutu((int) leveys - 1, (int) korkeus - 1);
                }
                System.out.println(mx + " " + my);
                System.out.println((int) leveys + " " + (int) korkeus + " " + korkeus + " " + leveys);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        if (SwingUtilities.isRightMouseButton(e)) {
            if (tila.getState() == tila.palautaPeli()) {
                int ruutuja = alue.getX();
                double leveys = Math.ceil((mx - (tila.getX() - 900) / 2) / 900.0 * ruutuja);
                double korkeus = Math.ceil((my - 63) / 900.0 * ruutuja);
                if (leveys > 0 && korkeus > 0 && leveys < ruutuja + 1 && korkeus < ruutuja + 1) {
                    alue.lukitseRuutu((int) leveys - 1, (int) korkeus - 1);
                }
                System.out.println(mx + " " + my);
                System.out.println((int) leveys + " " + (int) korkeus + " " + korkeus + " " + leveys);
            }
        }

    }

    private void menuToiminnot(int mx, int my) {
        if (mx >= tila.getX() / 2 - 130 && mx < tila.getX() / 2 + 170) {
            if (my >= 270 && my <= 420) {

                tila.stateGame();
            }
        }
        if (mx >= tila.getX() / 2 - 130 && mx < tila.getX() / 2 + 170) {
            if (my >= 470 && my <= 620) {
                tila.stateGame();
                System.out.println("Nappi2");
            }
        }
        if (mx >= tila.getX() / 2 - 130 && mx < tila.getX() / 2 + 170) {
            if (my >= 670 && my <= 820) {
                tila.stateGame();
                System.out.println("Nappi3");
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}