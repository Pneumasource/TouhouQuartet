import java.awt.event.*;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.Timer;
import javax.swing.JPanel;
import java.awt.image.*;
import java.util.*;
import java.io.*;
import javax.imageio.*;
import java.awt.Image;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.geom.*;
public class GameRunner extends JPanel implements KeyListener, ActionListener {
    Bullet b;
    BulletSprite cl = new BulletSprite();
    BufferedImage emp = ImageIO.read(new File("arrowhead.png")), 
    blanc = ImageIO.read(new File("mediTwinkle.png")), e;
    boolean up, down, left, right, focsus;
    int x, y, size, dy, dx, focus = 7, bw = emp.getWidth(), bh = emp.getHeight();
    double W = emp.getWidth() / 10.0, H = emp.getHeight() / 10.0, p = 0, rotation = 1;
    ArrayList<Bullet> bullets = new ArrayList<>(); //ADD MINDUSTRY SPELLCARDS
    ArrayList<Rectangle> hitbox = new ArrayList<>();
    public GameRunner() throws IOException {
        
        Timer t = new Timer(17, this);
        size = 30;
        x = 400 - (bw / 20);
        y = 400 - (bh / 20);
        //bullet initialization example
        /*for(int j = 0; j < 6; j++) {
            for(int i = 0; i < 0; i++) {
                bullets.add(new Bullet(400, 400, i * 6 + j + 180, 4 + j * 0.5, 20, 2000, 0, "ph", bw, bh));
            }
            for(int i = 0; i < 60; i++) {
                bullets.add(new Bullet(400, 400, i * 6 + j + 180, 4 + j * 0.5, 10, 2000, 0, "phe", bw, bh));
            }
        }*/
        addKeyListener(this); 
        setFocusable(true);
        System.out.println(bw + "    " + bh);
        t.start(); //updates frame every second by calling actionperformed
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        /*
            start UI part:
                background render
                weather effect
                start, extra start, spell practice, stage practice, character profile, music room, settings, quit buttons
                loading screen
            danmaku display part:
                background render
                player render
                mob render
                boss render
                player shots render
                spell sprites render
                dialogue sprites render
                dialogue box render
                danmaku render
                player bombs render
                player hitbox render
                player border render
                UI gauges render
                game over screen
        */
        x += dx;
        y += dy;
        double w = bw / 10.0;
        double h = bh / 10.0;
        g.drawImage(emp, x + (int)((W - w) / 2), y + (int)((H - h) / 2), (int)w, (int)h, this);
        for(int i = 0; i < bullets.size(); i++) {
            b = bullets.get(i);
            if (bullets.get(i).tag.equals("l")) {
                bullets.get(i).deg += 0.5;
                bullets.get(i).setDeg(bullets.get(i).deg);
                e = cl.BTABLE[0][(int)(b.deg + 180) % 360][b.type];
            } else if (bullets.get(i).tag.equals("ll")) {
                bullets.get(i).deg -= 0.5;
                bullets.get(i).setDeg(bullets.get(i).deg);
                e = cl.BTABLE[0][(int)(b.deg + 180) % 360][b.type + 5];
            }
            if (b.remainingLife < 30) {
                p = (1.0 / 30) * b.remainingLife;
            } else if (b.remainingLife > (b.life - 30)) {
                p = 1.0 - ((1.0 / 30) * (b.remainingLife % (b.life - 30)));
            }
            g.drawImage(e, (int)(b.x - (e.getWidth() / 2 / b.size) * p), 
            (int)(b.y - (e.getHeight() / 2 / b.size) * p), 
            (int)(e.getWidth() / (b.size / p)), (int)(e.getHeight() / (b.size / p)), null);
            bullets.get(i).updatePosition();
            if (bullets.get(i).lifetime()) {
                bullets.remove(i);
                //hitbox.remove(i);
                i--;
            }
        }
        //System.out.println(bullets.size());
        g.dispose();
    }
    public double angleCheck(double deg) {
        if (deg > 360) {
            deg = 0;
        } else if (deg < 0) {
            deg = 360;
        }
        return deg;
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        rotation += 3;
        rotation *= 1.001;
        try {
            addBullet(3);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        repaint();//calls paintcomponent to update frame
    }
    public void addBullet(int spellnum) throws IOException {
        for(int i = 0; i < 2; i++) {
            int type = 1;
            bullets.add(new Bullet(400, 400, type, 9, rotation + 180 * i, 3, 1, 200, 0, "l"));
            bullets.add(new Bullet(400, 400, type, 9, rotation + 180 * i, 3.1, 1, 200, 0, "ll"));
            bullets.add(new Bullet(400, 400, type, 9, rotation + 180 * i, 3.2, 1, 200, 0, "l"));
            bullets.add(new Bullet(400, 400, type, 9, rotation + 180 * i, 3.3, 1, 200, 0, "ll"));
            bullets.add(new Bullet(400, 400, type, 9, rotation + 180 * i, 3.4, 1, 200, 0, "l"));
            bullets.add(new Bullet(400, 400, type, 9, rotation + 180 * i, 3.5, 1, 200, 0, "ll"));
            bullets.add(new Bullet(400, 400, type, 9, rotation + 180 * i, 3.6, 1, 200, 0, "l"));
            bullets.add(new Bullet(400, 400, type, 9, rotation + 180 * i, 3.7, 1, 200, 0, "ll"));
        }
    }
    @Override
    public void keyPressed(KeyEvent ke) {
        if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
            focsus = true;
        }
        if (ke.getKeyCode() == KeyEvent.VK_UP) {
            up = true;
        } else if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
            down = true;
        } 
        if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
            left = true;
        } else if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
            right = true;
        }
        moveCheck();
    }
    @Override
    public void keyReleased(KeyEvent ke) {
        if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
            focsus = false;
        }
        if (ke.getKeyCode() == KeyEvent.VK_UP) {
            up = false;
        } else if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
            down = false;
        } 
        if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
            left = false;
        } else if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
            right = false;
        }
        moveCheck();
    }
    public void moveCheck() {
        if (focsus) {
            focus = 2;
        } else {
            focus = 5;
        }
        if (up) {
            dy = -1 * focus;
        } else if (down) {
            dy = focus;
        } else {
            dy = 0;
        }
        if (left) {
            dx = -1 * focus;
        } else if (right) {
            dx = focus;
        } else {
            dx = 0;
        }
    }
    @Override
    public void keyTyped(KeyEvent ke) {
        
    }
}