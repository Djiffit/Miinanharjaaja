package miinanharjaaja.kayttoliittyma;

import javax.swing.JOptionPane;
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

    /**
     * Lista eri ruuduista pelissä
     */
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

    private STATE tilanne;

    public STATE getState() {
        return tilanne;
    }

    public void setPeli(Peli peli) {
        this.peli = peli;
    }

    public Tila(int x, int y) {
        tilanne = STATE.MENU;
        this.x = x;
        this.y = y;
    }

    public Peli getPeli() {
        return peli;
    }

    /**
     * Päivittää aktiivisen pelin uuteen
     */
    public void updatePeli() {
        String tashoo = "";
        String valinta = "";
        while (true) {
            String[] vaihtoehdot = {"10", "12", "15", "18", "20", "25", "30", "36", "45", "50", "60", "75", "90", "100", "300"};
            valinta = (String) JOptionPane.showInputDialog(null, "Monta ruutua?", "Ruutujen määrä", JOptionPane.QUESTION_MESSAGE, null, vaihtoehdot, vaihtoehdot[1]);
            if (valinta != null && valinta.length() > 1) {
                break;
            }
        }
        while (true) {
            String[] vaihtoehdot = {"1", "2", "3", "4", "5", "6"};
            tashoo = (String) JOptionPane.showInputDialog(null, "Vaikeustaso? Miinoja on 0,05 * vaikeustaso", "Vaikeustaso", JOptionPane.QUESTION_MESSAGE, null, vaihtoehdot, vaihtoehdot[2]);
            if (tashoo != null && tashoo.length() > 0) {
                break;
            }
        }
        peli = new Peli(new Alue(Integer.parseInt(valinta), Integer.parseInt(tashoo)));
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

    /**
     * Asettaa menutilan
     */
    public void stateMenu() {
        tilanne = STATE.MENU;
    }

    /**
     *Asettaa pistelistatilan
     */
    public void stateScore() {
        tilanne = STATE.HIGHSCORE;
    }

    /**
     * Palauttaa menun tilan
     * @return menutila
     */
    public STATE palautaMenu() {
        return STATE.MENU;
    }

    /**
     * Palauttaa pelitilan
     * @return pelitila
     */
    public STATE palautaPeli() {
        return STATE.GAME;
    }

    /**
     * Palauttaa pistelistan tilan
     * @return Pistelistatila
     */
    public STATE palautaPiste() {
        return STATE.HIGHSCORE;
    }

    /**
     * Asettaa pelin tilaksi
     */
    public void stateGame() {

        tilanne = STATE.GAME;

    }
}
