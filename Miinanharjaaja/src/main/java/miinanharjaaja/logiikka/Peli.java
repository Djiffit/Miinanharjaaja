package miinanharjaaja.logiikka;

    /**
     * Pelin tilaa ylläpitävä luokka joka yhdistää logiikan elementit
     */
public class Peli {

    private Alue alue;
    private boolean havio;
    private boolean voitto;
    private int menetykset;
    private Kello kello;
    private boolean lahetetty;

    /**
     * Alustaa Pelin ja luo kellon
     * @param alue
     */
    public Peli(Alue alue) {
        this.alue = alue;
        this.menetykset = 0;
        this.havio = false;
        this.lahetetty = true;
        this.voitto = false;
        this.kello = new Kello();
    }

    public boolean isHavio() {
        return havio;
    }

    public boolean isLahetetty() {
        return lahetetty;
    }

    public void setLahetetty(boolean lahetetty) {
        this.lahetetty = lahetetty;
    }

    public Alue getAlue() {
        return alue;
    }

    public boolean havio() {
        return this.havio;
    }

    public Kello getKello() {
        return kello;
    }

    public boolean isVoitto() {
        return voitto;
    }
    
    /**
     * Tarkistaa onko kaikki pelin ruudut avattu
     * @return voiton status
     */

    public boolean voitto() {
        if (alue.getAvatutRuudut() == 1) {
            kello.setPaalla(false);
            this.voitto = true;
            return true;
        } else {
            return false;
        }
    }

    public int getMenetykset() {
        return menetykset;
    }

    /**
     * Lukitsee ruudun tai poistaa lukinnan
     * @param x x koordinaatti
     * @param y y koordinaatti
     */
    public void lukitseRuutu(int x, int y) {
        this.alue.lukitseRuutu(x, y);
    }
    
    /**
     * Avaa ruudun ja muuttaa havion tilan, jos kyseessä miina
     * @param x x koordinaatti
     * @param y y koordinaatti
     */

    public void avaaRuutu(int x, int y) {
        boolean tulos = this.alue.avaa(x, y);
        setLahetetty(false);
        if (tulos) {
            this.havio = true;
            lukitseRuutu(x, y);
        }
    }
    
    /**
     * Jatkaa peliä jos pelaaja haluaa miinaan osumisen jälkeen
     */

    public void jatka() {
        this.havio = false;
        menetykset++;
    }
    
    /**
     * Siirtää kellon eteenpäin
     */
    
    public void etene() {
        this.kello.etene();
    }
    
    /**
     * Palauttaa kellon ajan
     * @return Kellonaika
     */
    
    public String kelloAika() {
        return this.kello.toString();
    }
    
    /**
     * Laskee paljon pisteitä on saavutettu
     * @return pistemäärä
     */

    public int pisteet() {
        return (int) (alue.getX() * alue.getAvatutRuudut() * alue.getTaso() * 100 * Math.pow(0.95, menetykset));
    }
}
