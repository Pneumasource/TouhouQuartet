public class Bullet {
    public int type, palette, remainingLife, life, hitRadius;
    public double x, y, deg, vel, dx, dy, size;
    public String tag;
    public boolean grazed;
    public Bullet(int startx, int starty, int type, int palette, double deg, double vel, double size, int life, int hitRadius, String tag) {
        this.deg = deg;
        this.vel = vel;
        this.type = type;
        this.palette = palette;
        this.size = size;
        this.life = life;
        dx = Math.sin(Math.toRadians(deg)) * vel;
        dy = Math.cos(Math.toRadians(deg)) * vel;
        this.hitRadius = hitRadius;
        this.tag = tag;
        this.x = startx;
        this.y = starty;
    }
    public void updatePosition() { //moves bullet by dy/dx
        x += dx;
        y += dy;
    }
    public void setDeg(double deg) { //sets vector angle
        this.deg = deg;
        dx = Math.sin(Math.toRadians(deg)) * vel;
        dy = Math.cos(Math.toRadians(deg)) * vel;
    }
    public void setVel(double vel) { //sets vector force
        this.vel = vel;
        dx = Math.sin(Math.toRadians(deg)) * vel;
        dy = Math.cos(Math.toRadians(deg)) * vel;
    }
    public boolean lifetime() { //checks if bullet has died for every frame update of GameDisplay.
        remainingLife += 1;
        return (remainingLife >= life);
    }
}