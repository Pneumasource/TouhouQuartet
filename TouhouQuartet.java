import javax.swing.JFrame;
import java.io.IOException;
public class TouhouQuartet {
    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame("Touhou Four-Part Harmoney ~ Every Mind of Paradise");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GameRunner g = new GameRunner();
        frame.add(g);
        frame.pack();
        frame.setVisible(true);
    }
}
