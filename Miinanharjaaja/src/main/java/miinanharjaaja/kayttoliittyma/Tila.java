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

    private int valitseRuudut(int valinta) {
        switch (valinta) {
            case 0:
                return 10;
            case 1:
                return 12;
            case 2:
                return 15;
            case 3:
                return 18;
            case 4:
                return 20;
            case 5:
                return 25;
            case 6:
                return 30;
            case 7:
                return 36;
            case 8:
                return 45;
            case 9:
                return 50;
            case 10:
                return 60;
            case 11:
                return 75;
            case 12:
                return 90;
            case 13:
                return 100;
            case 14:
                return 300;
        }
        return -55;
    }

    private int valitseTaso(int tashoo) {
        switch (tashoo) {
            case 0:
                return 1;
            case 1:
                return 2;
            case 2:
                return 3;
            case 3:
                return 4;
            case 4:
                return 5;
            case 5:
                return 6;
        }
        return -55;
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
        int tashoo = -1;
        int valinta = -1;
        while (true) {
            String[] vaihtoehdot = {"10", "12", "15", "18", "20", "25", "30", "36", "45", "50", "60", "75", "90", "100", "300"};
            valinta = JOptionPane.showOptionDialog(null, "Monta ruutua?", "Ruutujen määrä", JOptionPane.PLAIN_MESSAGE, JOptionPane.QUESTION_MESSAGE, null, vaihtoehdot, vaihtoehdot[1]);
            valinta = valitseRuudut(valinta);
            if (valinta >= 0) {
                break;
            }
        }
        while (true) {
            String[] vaihtoehdot2 = {"1", "2", "3", "4", "5", "6"};
            tashoo = JOptionPane.showOptionDialog(null, "Vaikeustaso? Miinoja on 0,05 * vaikeustaso", "Vaikeustaso", JOptionPane.PLAIN_MESSAGE, JOptionPane.QUESTION_MESSAGE, null, vaihtoehdot2, vaihtoehdot2[1]);
            tashoo = valitseTaso(tashoo);
            if (tashoo >= 0) {
                break;
            }
        }
        peli = new Peli(new Alue(valinta, (tashoo)));
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
     * Asettaa pistelistatilan
     */
    public void stateScore() {
        tilanne = STATE.HIGHSCORE;
    }

    /**
     * Palauttaa menun tilan
     *
     * @return menutila
     */
    public STATE palautaMenu() {
        return STATE.MENU;
    }

    /**
     * Palauttaa pelitilan
     *
     * @return pelitila
     */
    public STATE palautaPeli() {
        return STATE.GAME;
    }

    /**
     * Palauttaa pistelistan tilan
     *
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
