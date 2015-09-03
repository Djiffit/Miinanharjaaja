package miinanharjaaja.kayttoliittyma;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import miinanharjaaja.logiikka.Ruutu;

/**
 * Piirtäjä huolehtii graafisten elementtien piirtämisestä
 */
class Piirtaja extends JPanel {

    private BufferedImage playNappi;
    private BufferedImage image1;
    private BufferedImage image2;
    private BufferedImage image3;
    private BufferedImage image4;
    private BufferedImage image5;
    private BufferedImage image6;
    private BufferedImage image7;
    private BufferedImage image8;
    private BufferedImage image9;
    private BufferedImage image10;
    private BufferedImage image11;
    private BufferedImage image12;
    private BufferedImage image;
    private BufferedImage imageAuki;
    private BufferedImage imageLukittu;
    private Tila tila;
    private boolean voittkoko;
    private double y;

    public Piirtaja(Tila tila) {
        this.tila = tila;
        this.y = tila.getY();
        this.voittkoko = false;
        super.setBackground(Color.black);
        try {

            playNappi = ImageIO.read(new FileInputStream("src\\res\\pelaaNappi.png"));
            image = ImageIO.read(new FileInputStream("src\\res\\ruutu.png"));
            image1 = ImageIO.read(new FileInputStream("src\\res\\Ruutu1.png"));
            image2 = ImageIO.read(new FileInputStream("src\\res\\Ruutu2.png"));
            image3 = ImageIO.read(new FileInputStream("src\\res\\Ruutu3.png"));
            image4 = ImageIO.read(new FileInputStream("src\\res\\Ruutu4.png"));
            image5 = ImageIO.read(new FileInputStream("src\\res\\Ruutu5.png"));
            image6 = ImageIO.read(new FileInputStream("src\\res\\Ruutu6.png"));
            image7 = ImageIO.read(new FileInputStream("src\\res\\Ruutu7.png"));
            image8 = ImageIO.read(new FileInputStream("src\\res\\Ruutu8.png"));
            image9 = ImageIO.read(new FileInputStream("src\\res\\uusiPeli.png"));
            image10 = ImageIO.read(new FileInputStream("src\\res\\poistuNappi.png"));
            image11 = ImageIO.read(new FileInputStream("src\\res\\pisteet.png"));
            image12 = ImageIO.read(new FileInputStream("src\\res\\miinanharjaaja.png"));
            imageAuki = ImageIO.read(new FileInputStream("src\\res\\RuutuAvattu.png"));
            imageLukittu = ImageIO.read(new FileInputStream("src\\res\\RuutuLukittu.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        if (this.tila.getState() == tila.palautaPeli() && this.tila.getPeli() != null) {
            renderRuudut(graphics);
        } else if (this.tila.getState() == tila.palautaMenu()) {
            valikkoRender(graphics);
        } else if (this.tila.getState() == tila.palautaPiste()) {
            try {
                pisteRender(graphics);
            } catch (IOException ex) {
                Logger.getLogger(Piirtaja.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Piirtaja.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(Piirtaja.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (voittkoko && this.tila.getState() == tila.palautaPeli()) {
            oletVoittaja(graphics);

        } else {
            voittkoko = false;
        }
    }

    /**
     * Voittoruudun piirto
     */
    private void oletVoittaja(Graphics graphics) {
        if (tila.getPeli().isVoitto()) {
            Font font = new Font("arial", Font.PLAIN, 20);
            graphics.drawString("Paina ESC palataksesi takaisin valikkoon", 50, tila.getY() - 100);
            Random sattuma = new Random();
            font = new Font("arial", Font.PLAIN, (int) (200 * sattuma.nextDouble()));
            graphics.setFont(font);
            graphics.setColor(Color.getHSBColor((float) (256 * sattuma.nextDouble()), (float) (256 * sattuma.nextDouble()), (float) (256 * sattuma.nextDouble())));
            int x = (int) (1000 * sattuma.nextDouble());
            int y = (int) (1000 * sattuma.nextDouble());
            graphics.drawString("OLET VOITTAJA!", x, y);
        }
    }

    /**
     * Pelikentän objektit
     */
    private void renderRuudut(Graphics graphics) {
        ArrayList<Ruutu>[] ruudukko = this.tila.getPeli().getAlue().getRuudukko();
        int korkeus = 40;

        int leveys = (tila.getX() - 900) / 2;
        ArrayList<Ruutu> rivi = new ArrayList<Ruutu>();
        for (int i = 0; i < ruudukko.length; i++) {
            for (int j = 0; j < ruudukko.length; j++) {
                rivi = ruudukko[i];
                graphics.drawImage(valitsePiirrettava(rivi.get(j)), i * (900 / ruudukko.length) + leveys, korkeus, (900 / ruudukko.length), (900 / ruudukko.length), this);
                korkeus += (900 / ruudukko.length);
            }
            korkeus = 40;
        }
        Font font = new Font("arial", Font.PLAIN, 40);
        graphics.setFont(font);
        graphics.setColor(Color.white);
        graphics.drawString("Menetyksiä: " + tila.getPeli().getMenetykset(), 30, 60);
        font.deriveFont(30);
        graphics.drawString(tila.getPeli().kelloAika(), 30, 120);
        graphics.drawString("Pisteet: " + tila.getPeli().pisteet(), 30, 180);
        graphics.drawString("Ruutuja: " + (tila.getPeli().getAlue().getRuudut() - tila.getPeli().getAlue().getARuudut()), 30, 240);
    }

    /**
     * Valitsee grafiikan
     */
    private Image valitsePiirrettava(Ruutu ruutu) {
        if (ruutu.isAvattu()) {
            if (ruutu.isMiina()) {
                return imageAuki;
            }
            if (ruutu.getViereisetMiinat() == 1) {
                return image1;
            }
            if (ruutu.getViereisetMiinat() == 2) {
                return image2;
            }
            if (ruutu.getViereisetMiinat() == 3) {
                return image3;
            }
            if (ruutu.getViereisetMiinat() == 4) {
                return image4;
            }
            if (ruutu.getViereisetMiinat() == 5) {
                return image5;
            }
            if (ruutu.getViereisetMiinat() == 6) {
                return image6;
            }
            if (ruutu.getViereisetMiinat() == 7) {
                return image7;
            }
            if (ruutu.getViereisetMiinat() == 0) {
                return imageAuki;
            }
            if (ruutu.getViereisetMiinat() == 8) {
                return image8;
            }
        } else if (ruutu.isLukittu()) {
            return imageLukittu;
        } else {
            return image;
        }
        return image;
    }

    /**
     * Piirtää valikon ruudut
     */
    private void valikkoRender(Graphics g) {
        Font font = new Font("arial", Font.PLAIN, 80);
        g.setFont(font);
        g.setColor(Color.white);
        g.drawImage(playNappi, tila.getX() / 2 - 350, 250, 300, 150, this);
        g.drawImage(image9, tila.getX() / 2 + 50, 250, 300, 150, this);
        g.drawImage(image11, tila.getX() / 2 - 150, 450, 300, 150, this);
        g.drawImage(image10, tila.getX() / 2 - 150, 650, 300, 150, this);
        g.drawImage(image12, tila.getX() / 2 - 450, 25, 900, 200, this);
    }

    void voitto() {
        this.voittkoko = true;
    }

    /**
     * Piirtää pisteruudun
     */
    private void pisteRender(Graphics graphics) throws IOException, FileNotFoundException, ClassNotFoundException, InterruptedException {
        Font font = new Font("arial", Font.PLAIN, 50);
        graphics.drawString("Paina ESC palataksesi takaisin valikkoon", 50, 50);
        graphics.setFont(font);
        graphics.setColor(Color.white);
        String pisteilijat = tila.getManageri().getHuippuPisteet();
        int i = 0;

        for (String line : pisteilijat.split("\n")) {
            graphics.drawString(line, tila.getX() / 2 - 200, (int) y + i * 50);
            i++;
        }
        if (y + i * 50 < 0) {
            y = tila.getY();
        }

        y += -1;

    }
}
