package miinanharjaaja.kayttoliittyma;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import miinanharjaaja.logiikka.Alue;
import miinanharjaaja.logiikka.Peli;
import miinanharjaaja.pisteet.HuippupisteManageri;

/**
 * Tila viestii käyttöliittymälle, mikä on pelin tilanne, eli piirretäänkö menu
 * ja mitä klikkauksia huomioidaan
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
        String ruutu = "";
        
        String tashoo = "";
        while (true) {
            String ruutuja = (JOptionPane.showInputDialog("Monta ruutua? Max. 99", null));
            
            if (ruutuja != null && (ruutuja.matches("[0-9]+") && ruutuja.length() > 0)) {
                ruutu = ruutuja;
                break;
            }
        }
        while (true) {
            String taso = (JOptionPane.showInputDialog("Vaikeustaso? Taso * 0,05 miinoja", null));
            if (taso != null && (taso.matches("[0-9]+") && taso.length() > 0)) {
                tashoo = taso;
                break;
            }
        }
        peli = new Peli(new Alue(Integer.parseInt(ruutu), Integer.parseInt(tashoo)));
        if (peli == null) {
            updatePeli();
        }
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
