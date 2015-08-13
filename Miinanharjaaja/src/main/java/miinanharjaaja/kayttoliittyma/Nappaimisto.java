/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miinanharjaaja.kayttoliittyma;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Konsta
 */
public class Nappaimisto implements KeyListener {

    private Tila tila;

    public Nappaimisto(Tila tila) {
        this.tila = tila;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE && tila.getState() == tila.palautaPeli()) {
            tila.stateMenu();
        }
    }

}
