import java.awt.event.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.swing.Timer;
import javax.swing.JPanel;
import java.util.*;
import java.io.*;
public class GameRunner extends JPanel implements KeyListener, ActionListener {
    Bullet b;
    BulletSprite cl = new BulletSprite();
    BufferedImage player = cl.BTABLE[0][0][18], bulletImg, gamerender;
    boolean up, down, left, right, focsus, bombing;
    int x, y, size, dy, dx, focus = 7, W = player.getWidth(), H = player.getHeight(), gametime = 1;
    ArrayList<Bullet> bullets = new ArrayList<>(); //add mindustry/sol'srng/woomyarras spellcards
    GameState gamestate = new GameState(0, 0, 1, 999, 999, 100, 1, 0);
    Spellcard spell = new SC1(gamestate.difficulty);
    Rectangle bound;
    public GameRunner() throws IOException {
        addKeyListener(this); 
        setFocusable(true);
        setPreferredSize(new Dimension(1440, 900));
        setBackground(Color.BLACK);
        
        int[][] palette = spell.getPalette();
        for (int i = 0; i < palette.length; i++) {
            cl.setPalette(palette[i][0], palette[i][1], palette[i][2], palette[i][3]);
        }

        size = 30;
        x = (int)getPreferredSize().getWidth() / 2;
        y = (int)getPreferredSize().getHeight() / 2;

        bound = new Rectangle(300, 50, 
        (int)(this.getPreferredSize().getWidth() - 600), 
        (int)(this.getPreferredSize().getHeight() - 100));

        Timer t = new Timer(17, this);
        t.start(); //updates frame every 17 milisecond by calling actionperformed
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
                UI gauges render
                player bombs render
                player hitbox render
                player border render
                game over screen
        */
        
        g.drawImage(player, x - W / 2, y - H / 2, (int)W, (int)H, this);

        for(int i = 0; i < bullets.size(); i++) {
            bullets.set(i, spell.bulletUpdate(bullets.get(i)));
            b = bullets.get(i);
            bulletImg = cl.BTABLE[b.palette][(int)(b.deg + 180) % 360][b.type];

            int distance = (int)Math.sqrt(Math.pow(b.x - x, 2) + Math.pow(b.y - y, 2));
            if (distance < 15 && !b.grazed) {
                gamestate.graze += 1;
                b.grazed = true;
            }
            if (distance < b.hitRadius) {
                gamestate.life -= 1;
            }
            if (bombing && gamestate.bomb > 0 && distance < 200) {
                if (b.remainingLife > 29) {
                    b.remainingLife = b.life - 29;
                }
                b.setVel(b.vel - 0.3);
                b.setDeg(Math.toDegrees(Math.atan2(b.x - x, b.y - y)) + 90);
                bullets.set(i, b);
            }

            double p = 1.0;
            if (b.remainingLife > (b.life - 30)) {
                p = (double)(b.life - b.remainingLife) / 30;
            } else if (b.remainingLife < 30) {
                p = (double)b.remainingLife / 30;
            }

            g.drawImage(bulletImg, 
            (int)(b.x - (bulletImg.getWidth() / 2 / b.size) * p), 
            (int)(b.y - (bulletImg.getHeight() / 2 / b.size) * p), 
            (int)(bulletImg.getWidth() / (b.size / p)), 
            (int)(bulletImg.getHeight() / (b.size / p)), 
            null);
            
            //g.setColor(Color.RED); g.drawArc((int)b.x - b.hitRadius, (int)b.y - b.hitRadius, b.hitRadius * 2, b.hitRadius * 2, 0, 360);

            bullets.get(i).updatePosition();
            if (bullets.get(i).lifetime()) {
                bullets.remove(i);
                i--;
            }
        }

        if (bombing && gamestate.bomb > 0) {
            gamestate.bomb -= 1;
        }

        int minX = (int)bound.getMinX(), minY = (int)bound.getMinY(),
        maxX = (int)bound.getMaxX(), maxY = (int)bound.getMaxY(),
        width = (int)bound.getWidth(), height = (int)bound.getHeight();
        for(int i = minX; i >= 0; i--) {
            g.setColor(new Color(0, 0, 0, 1 - i / (float)minX));
            g.drawRect(minX - i, minY - i, width + 2 * i, height + 2 * i);
        }
        for(int i = 3; i >= 0; i--) {
            int sine = (int)(10 * i * Math.pow(Math.sin(gametime * 0.01), 2));
            g.setColor(new Color(Color.HSBtoRGB((float)((gametime % 360.0) / 360.0), 1, 1f / (i + 1))));
            g.drawRect(minX - sine - i, minY - sine - i, width + 2 * sine + 2 * i, height + 2 * sine + 2 * i);
        }

        g.setFont(new Font("Monospaced", Font.BOLD, 20));
        g.drawString("Life: " + gamestate.life, minX, maxY + 32);
        for(int i = 0; i < 6; i++) {
            g.fill3DRect(minX + i + 120, maxY + 10 + i, (int)(200 * (gamestate.life / 1000.0)) - 2 * i, 30 - 2 * i, true);
        }
        g.drawString("Bomb: " + gamestate.bomb, minX + 340, maxY + 32);
        for(int i = 0; i < 6; i++) {
            g.fill3DRect(minX + i + 460, maxY + 10 + i, (int)(200 * (gamestate.bomb / 1000.0)) - 2 * i, 30 - 2 * i, true);
        }
        g.drawString("Graze: " + gamestate.graze, minX + 680, maxY + 32);

        x = Math.max(minX, Math.min(x + dx, maxX));
        y = Math.max(minY, Math.min(y + dy, maxY));
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            addBullet(1);
        } catch (Exception ioe) {
            ioe.printStackTrace();
        }
        gametime += 1;
        spell.updateTime();
        repaint();//calls paintcomponent to update frame
    }
    public void addBullet(int spellnum) {
        if (spell == null) {
            spell = spell.getSpellcard(1);
            int[][] palette = spell.getPalette();
            for (int i = 0; i < palette.length; i++) {
                cl.setPalette(palette[i][0], palette[i][1], palette[i][2], palette[i][3]);
            }
        }
        //ArrayList<Bullet> bulletrequest = spell.bulletRequest((int)this.getPreferredSize().getWidth() / 2, (int)this.getPreferredSize().getHeight() / 2, x, y);
        ArrayList<Bullet> bulletrequest = spell.bulletRequest(x, y, x, y);
        for (Bullet b : bulletrequest) {
            bullets.add(b);
        }
    }
    @Override
    public void keyPressed(KeyEvent ke) {
        if (ke.getKeyCode() == KeyEvent.VK_SHIFT) {
            focsus = true;
        }
        if (ke.getKeyCode() == KeyEvent.VK_X) {
            bombing = true;
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
        if (ke.getKeyCode() == KeyEvent.VK_SHIFT) {
            focsus = false;
        }
        if (ke.getKeyCode() == KeyEvent.VK_X) {
            bombing = false;
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