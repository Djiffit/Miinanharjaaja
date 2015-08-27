
package miinanharjaaja.kayttoliittyma;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *Nappaimisto tulkitsee nappaimistön painallukset ja suorittaa niille asiankuuluvat komennot
 * 
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
    
    /**
     * Nappien toiminnot
     */

    /**
     * Nappien toiminnot
     * @param e näppäimen painaminen
     */
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE && (tila.getState() == tila.palautaPeli() || tila.getState() == tila.palautaPiste())) {
            tila.stateMenu();
        }
    }

}
