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
public class Bullet {
    public int type;
    public int color;
    public double x;
    public double y;
    public double deg;
    public double vel;
    public double dx;
    public double dy;
    public double size;
    public int remainingLife;
    public int life;
    public double damage;
    public double delay;
    public String tag;
    public boolean utilBool = true;
    public Bullet(int startx, int starty, int type, int color, double deg, double vel, double size, int life, double damage, String tag) {
        this.deg = deg;
        this.vel = vel;
        this.type = type;
        this.color = color;
        this.size = size;
        this.life = life;
        this.damage = damage;
        dx = Math.sin(Math.toRadians(deg)) * vel;
        dy = Math.cos(Math.toRadians(deg)) * vel;
        this.tag = tag;
        this.x = startx;
        this.y = starty;
    }
    public void updatePosition() { //moves bullet by dy/dx
        x += dx;
        y += dy;
    }
    public void setDeg(double deg) { //sets vector angle
        dx = Math.sin(Math.toRadians(deg)) * vel;
        dy = Math.cos(Math.toRadians(deg)) * vel;
    }
    public void setVel(double vel) { //sets vector force
        dx = Math.sin(Math.toRadians(deg)) * vel;
        dy = Math.cos(Math.toRadians(deg)) * vel;
    }
    public boolean lifetime() { //checks if bullet has died for every frame update of GameDisplay. Also contains special conditions utilizing boolean
        remainingLife += 1;
        return (remainingLife >= life);
    }
}