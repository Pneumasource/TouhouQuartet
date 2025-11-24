import javax.swing.*;
import java.awt.Dimension;
import java.io.IOException;

public class TouhouQuartet {
    public static void main(String[] args) throws IOException {
        try {
            System.setProperty("sun.java2d.opengl","true");
            JFrame frame = new JFrame("Touhou Four");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            GameRunner g = new GameRunner();
            frame.add(g);
            frame.setPreferredSize(new Dimension(1440, 900));
            frame.pack();
            frame.setVisible(true);
            frame.toFront();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("OMG");
        }
    }
}
