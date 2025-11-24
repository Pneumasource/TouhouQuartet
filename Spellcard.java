import java.lang.reflect.Constructor;
import java.util.ArrayList;
public abstract class Spellcard {
    private int difficulty;
    public Spellcard(int difficulty) {
        this.difficulty = difficulty;
    }
    public abstract int[][] getPalette();
    public abstract ArrayList<Bullet> bulletRequest(int centx, int centy, int vectorx, int vectory);
    public abstract Bullet bulletUpdate(Bullet b);
    public abstract String getSpellName();
    public abstract void updateTime();
    public Spellcard getSpellcard(int spellnum) {
        try {
            Class<? extends Spellcard> c = Class.forName("SC" + spellnum).asSubclass(Spellcard.class);
            Constructor<? extends Spellcard> ctor = c.getDeclaredConstructor(int.class);
            ctor.setAccessible(true);
            return ctor.newInstance(difficulty);
        } catch (Exception ioe) {
            ioe.printStackTrace();
        }
        return null;
        
    }
}
class SC1 extends Spellcard {
    private double t;
    public SC1(int difficulty) {
        super(difficulty);
    }
    public int[][] getPalette() {
        int[][] r = {{1, 25, 260, 0}, {2, 25, 280, 180}, {3, 25, 300, 50}};
        return r;
    }
    public ArrayList<Bullet> bulletRequest(int centx, int centy, int vectorx, int vectory) {
        ArrayList<Bullet> bullets = new ArrayList<>();
        for(int i = 0; i < 9; i++) {
            int type = 25;
            bullets.add(new Bullet(centx, centy, type, i % 3 + 1, Math.pow(t, 1.5) + 90 * Math.pow(-1, i) + i, 3 + 0.1 * i, 6, 300 - 30 * i, "" + i));
        }
        return bullets;
    }
    public Bullet bulletUpdate(Bullet b) {
        for (int i = 0; i < 8; i++) {
            if (b.tag.equals("" + i)) {
                b.setDeg(b.deg += 0.06 * i);
            }
        }
        return b;
    }
    public String getSpellName() {
        return "Spell one";
    }
    public void updateTime() {
        t += 1;
    }
}