import javax.swing.*;
import java.io.IOException;

public class TouhouQuartet {
    public static void main(String[] args) throws IOException {
        SwingUtilities.invokeLater(() -> {
            try {
                JFrame frame = new JFrame("Touhou Four");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                GameRunner g = new GameRunner();
                g.setPreferredSize(new java.awt.Dimension(800, 600));
                frame.add(g);
                frame.pack();
                frame.setVisible(true);
                frame.toFront();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.print("OMG");
            }
        });
    }
}
