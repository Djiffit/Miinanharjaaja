package miinanharjaaja.kayttoliittyma;

import javax.swing.JOptionPane;
import miinanharjaaja.logiikka.Alue;
import miinanharjaaja.logiikka.Peli;
import miinanharjaaja.pisteet.HuippupisteManageri;

/**
 * Tila viestii käyttöliittymälle, mikä on pelin tilanne, eli piirretäänkö menu ja mitä klikkauksia huomioidaan
 */

public class Tila {

    private int x;
    private int y;
    private Peli peli;
    private HuippupisteManageri manageri;

    public HuippupisteManageri getManageri() {
        return manageri;
    }

    public void setManageri(HuippupisteManageri manageri) {
        this.manageri = manageri;
    }

    public enum STATE {
        GAME,
        MENU,
        HIGHSCORE
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    private STATE state;

    public STATE getState() {
        return state;
    }

    public void setPeli(Peli peli) {
        this.peli = peli;
    }

    public Tila(int x, int y) {
        state = STATE.MENU;
        this.x = x;
        this.y = y;
    }

    public Peli getPeli() {
        return peli;
    }

    public void updatePeli() {
        int ruutuja = -1;
        while (ruutuja < 1 || ruutuja > 10000) {
            ruutuja = Integer.parseInt(JOptionPane.showInputDialog("Monta ruutua? Max. 99", null));
        }
        int taso = Integer.parseInt(JOptionPane.showInputDialog("Vaikeustaso 0-4", null));
        peli = new Peli(new Alue(ruutuja, taso));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void stateMenu() {
        state = STATE.MENU;
    }
    
    public void stateScore() {
        state = STATE.HIGHSCORE;
    }

    public STATE palautaMenu() {
        return STATE.MENU;
    }

    public STATE palautaPeli() {
        return STATE.GAME;
    }
    
    public STATE palautaPiste() {
        return STATE.HIGHSCORE;
    }

    public void stateGame() {

        state = STATE.GAME;

    }
}
