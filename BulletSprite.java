import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
public class BulletSprite {
    public BufferedImage[][][] BTABLE = new BufferedImage[360][360][50];
    public BulletSprite () throws IOException {
        for (int i = 0; i < 34; i++) {
            BTABLE[0][0][i] = resizeImage(ImageIO.read(new File("Images/bullet" + i + ".png")), 200, 150);
            for (int j = 0; j < 360; j++) {
                BTABLE[0][j][i] = rotateImage(BTABLE[0][0][i], -j);
            }
            System.out.println(i);
        }
    }
    public void setPalette(int palette, int type, int hue, int graditude) {
        for (int j = 0; j < 360; j++) {
            BTABLE[palette][j][type] = recolorImage(BTABLE[0][j][type], hue + (int)(Math.cos(Math.PI * j / 180.0) * graditude));
        }
    }
    public BufferedImage resizeImage(BufferedImage img, int newW, int newH) {  
        int w = img.getWidth();  
        int h = img.getHeight();  
        BufferedImage dimg = new BufferedImage(newW, newH, img.getType());  
        Graphics2D g = dimg.createGraphics();  
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
        RenderingHints.VALUE_INTERPOLATION_BILINEAR);  
        g.drawImage(img, 0, 0, newW, newH, 0, 0, w, h, null);  
        g.dispose();  
        return dimg;  
    }  
    public BufferedImage rotateImage(BufferedImage img, double angle) {
        BufferedImage gg = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = gg.createGraphics();
        g.rotate(Math.toRadians(angle), img.getWidth() / 2, img.getHeight() / 2);
        g.drawImage(img, (img.getWidth() - gg.getWidth()) / 2, (img.getHeight() - gg.getHeight()) / 2, null);
        g.dispose();
        return gg;
    }
    public BufferedImage recolorImage(BufferedImage src, int hue) {
        int w = src.getWidth(), h = src.getHeight();
        BufferedImage dst = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        final float H = (float)(hue / 360.0);

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int argb = src.getRGB(x, y);
                int a = (argb >>> 24) & 0xFF;
                if (a == 0) { // 완전 투명은 그대로
                    dst.setRGB(x, y, 0x00000000);
                    continue;
                }
                int r = (argb >>> 16) & 0xFF;
                float s = 1f - (r / 255f);
                float v = (r / 255f);
                int rgb = Color.HSBtoRGB(H, s, v) & 0x00FFFFFF;
                dst.setRGB(x, y, (a << 24) | rgb);
            }
        }
        return dst;
    }
}