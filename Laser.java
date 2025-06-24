import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.JFrame;
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
public class Laser {
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private double x;
    private double y;
    private double deg;
    private double vel;
    private double dx;
    private double dy;
    private double size;
    private double remainingLife;
    private double life;
    private double damage;
    private double delay;
    private String type;
    private String color;
    public BufferedImage img;
    public Laser(int x1, int y1, int x2, int y2, int deg, double size, double life, double delay, double damage, String shape, String color) //laser constructor
    {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.deg = deg;
        this.color = color;
        this.size = size;
        this.life = life;
        this.delay = delay;
        this.damage = damage;
        type = shape;
        dx = Math.sin(Math.toRadians(deg)) * vel;
        dy = Math.cos(Math.toRadians(deg)) * vel;
    }
    public void updateSprite() { //moves laser origin by dy/dx
        x1 += dx;
        y1 += dy;
        x2 += dx;
        y2 += dy;
    }
    public void setAngle(int deg) { //changes radial vector direction
        this.deg = deg;
    }
    public void setSize(int size) { //changes laser thickness
        this.size = size;
    }
    public void switchType(String type) { //changes bullet sprite
        this.type = type;
    }
    public boolean lifetime() { //checks if bullet has died for every frame update of GameDisplay. Also contains special conditions utilizing boolean
        remainingLife += 1.0;
        return (remainingLife >= life);
    }
    public BufferedImage resize(BufferedImage bimg, int width, int height) { //returns resized danmaku image
        return (BufferedImage)(bimg.getScaledInstance(width, height, bimg.SCALE_DEFAULT));
    }
}
