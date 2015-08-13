package miinanharjaaja.kayttoliittyma;

public class Tila {
    
    private int x;
    private int y;

    public enum STATE{ 
        GAME,
        MENU
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

    public Tila(int x, int y) {
        state = STATE.MENU;
        this.x = x;
        this.y = y;
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
    
    public STATE palautaMenu() {
        return STATE.MENU;
    }
    
    public STATE palautaPeli() {
        return STATE.GAME;
    }
    
    public void stateGame() {
        
        state = STATE.GAME;
        
    }
}
