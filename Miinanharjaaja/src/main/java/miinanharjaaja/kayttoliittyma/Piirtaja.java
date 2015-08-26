package miinanharjaaja.kayttoliittyma;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import miinanharjaaja.logiikka.Alue;
import miinanharjaaja.logiikka.Peli;
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
    private BufferedImage image;
    private BufferedImage imageAuki;
    private BufferedImage imageLukittu;
    private Tila tila;
    private boolean voittkoko;

    public Piirtaja(Tila tila) {
        this.tila = tila;
        this.voittkoko = false;
        super.setBackground(Color.black);
        try {

            playNappi = ImageIO.read(new FileInputStream("PelaaNappi.png"));
            image = ImageIO.read(new FileInputStream("ruutu.png"));
            image1 = ImageIO.read(new FileInputStream("Ruutu1.png"));
            image2 = ImageIO.read(new FileInputStream("Ruutu2.png"));
            image3 = ImageIO.read(new FileInputStream("Ruutu3.png"));
            image4 = ImageIO.read(new FileInputStream("Ruutu4.png"));
            image5 = ImageIO.read(new FileInputStream("Ruutu5.png"));
            image6 = ImageIO.read(new FileInputStream("Ruutu6.png"));
            image7 = ImageIO.read(new FileInputStream("Ruutu7.png"));
            image8 = ImageIO.read(new FileInputStream("Ruutu8.png"));
            imageAuki = ImageIO.read(new FileInputStream("RuutuAvattu.png"));
            imageLukittu = ImageIO.read(new FileInputStream("RuutuLukittu.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        if (this.tila.getState() == tila.palautaPeli() && this.tila.getPeli() != null) {
            renderRuudut(graphics);
        } else {
            valikkoRender(graphics);
        }
        if (voittkoko && this.tila.getState() == tila.palautaPeli()) {
            Random sattuma = new Random();
            Font font = new Font("arial", Font.PLAIN, (int) (200 * sattuma.nextDouble()));
            graphics.setFont(font);
            graphics.setColor(Color.getHSBColor((float)(256 *sattuma.nextDouble()), (float)(256 *sattuma.nextDouble()), (float)(256 *sattuma.nextDouble())));
            int x = (int) (1000 * sattuma.nextDouble());
            int y = (int) (1000 * sattuma.nextDouble());
            graphics.drawString("OLET VOITTAJA!", x, y);

        } else {
            voittkoko = false;
        }
    }

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
        graphics.drawString("" + tila.getPeli().getMenetykset(), 60, 60);
        font.deriveFont(30);
        graphics.drawString(tila.getPeli().kelloAika(), 60, 120);
        graphics.drawString("" + tila.getPeli().pisteet(), 60, 180);
    }

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

    private void valikkoRender(Graphics g) {
        Font font = new Font("arial", Font.PLAIN, 80);
        g.setFont(font);
        g.setColor(Color.white);
        g.drawString("Miinanharjaaja", tila.getX() / 2 - 280, 100);
        g.drawImage(playNappi, tila.getX() / 2 - 150, 250, 300, 150, this);
        g.drawImage(playNappi, tila.getX() / 2 - 150, 450, 300, 150, this);
        g.drawImage(playNappi, tila.getX() / 2 - 150, 650, 300, 150, this);
    }

    void voitto() {
        this.voittkoko = true;
    }

}
