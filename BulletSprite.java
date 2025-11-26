import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
public class BulletSprite {
    public BufferedImage[][][] BTABLE = new BufferedImage[360][360][50];
    public BulletSprite () throws IOException {
        for (int i = 0; i < 38; i++) {
            BTABLE[0][0][i] = resizeImage(ImageIO.read(new File("Images/bullet" + i + ".png")), 200, 150);
            for (int j = 0; j < 360; j++) {
                BTABLE[0][j][i] = rotateImage(BTABLE[0][0][i], -j);
            }
            System.out.println(i);
        }
    }
    public void setPalette(int palette, int type, int hue, int graditude, float sat, float satitude, float val, float valitude) {
        for (int j = 0; j < 360; j++) {
            BTABLE[palette][j][type] = recolorImage(BTABLE[0][j][type], 
            hue + (int)(Math.cos(Math.PI * j / 180.0) * graditude),
            sat + (float)(Math.cos(Math.PI * j / 180.0) * satitude),
            val + (float)(Math.cos(Math.PI * j / 180.0) * valitude)
            );
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
    public BufferedImage recolorImage(BufferedImage src, int hueDeg, float satTarget, float valMul) {
            final int w = src.getWidth(), h = src.getHeight();
    final int[] srcPix = src.getRGB(0, 0, w, h, null, 0, w);
    final int[] dstPix = new int[srcPix.length];

    final float hue = ((hueDeg % 360) + 360) % 360 / 360f;

    // 임계값: “하양으로 남길” 밝기/색차 기준, “회색으로 간주할” 색차 기준
    final int WHITE_LUMA_THR = 250;     // r,g,b >= 250 → 거의 하양
    final int GRAY_DELTA_THR = 10;      // |r-g|,|r-b|,|g-b| <= 10 → 회색 계열로 간주

    for (int i = 0; i < srcPix.length; i++) {
        int p = srcPix[i];
        int a = (p >>> 24) & 0xFF;
        if (a == 0) { dstPix[i] = 0x00000000; continue; }

        int r = (p >>> 16) & 0xFF;
        int g = (p >>>  8) & 0xFF;
        int b =  p         & 0xFF;

        // 거의 하양? 그대로 둔다(완전 흰색 유지)
        if (r >= WHITE_LUMA_THR && g >= WHITE_LUMA_THR && b >= WHITE_LUMA_THR) {
            dstPix[i] = (a << 24) | (r << 16) | (g << 8) | b;
            continue;
        }

        // 회색 계열? → hue 입히고 채도/밝기 조정
        int rg = Math.abs(r - g), rb = Math.abs(r - b), gb = Math.abs(g - b);
        if (rg <= GRAY_DELTA_THR && rb <= GRAY_DELTA_THR && gb <= GRAY_DELTA_THR) {
            float[] hsb = Color.RGBtoHSB(r, g, b, null);
            float s = clamp01(satTarget);           // 회색은 s≈0 → 목표 채도로 강제
            float v = clamp01(hsb[2] * valMul);     // 밝기는 원본 기반 배율
            int rgb = Color.HSBtoRGB(hue, s, v) & 0x00FFFFFF;
            dstPix[i] = (a << 24) | rgb;
        } else {
            // 회색 아니면 원본 유지(원하면 여기서도 약한 틴트 가능)
            dstPix[i] = (a << 24) | (r << 16) | (g << 8) | b;
        }
    }

    BufferedImage dst = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
    dst.setRGB(0, 0, w, h, dstPix, 0, w);
    return dst;

    }
    private static float clamp01(float v) {
        return v < 0f ? 0f : (v > 1f ? 1f : v);
    }
}