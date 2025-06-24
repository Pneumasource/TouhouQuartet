import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.util.ArrayList;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
public class BulletSprite {
    public ArrayList<BufferedImage> GRAYSCALE = new ArrayList<>();
    BufferedImage[][][] BTABLE = new BufferedImage[60][360][50];
    public BulletSprite () throws IOException {
        GRAYSCALE.add(resizeImage(ImageIO.read(new File("amuletBig.png")), 200, 150)); //0 r
        GRAYSCALE.add(resizeImage(ImageIO.read(new File("amulet.png")), 120, 90)); //1 r
        GRAYSCALE.add(resizeImage(ImageIO.read(new File("arrow.png")), 125, 100)); //2 r
        GRAYSCALE.add(resizeImage(ImageIO.read(new File("arrowbig.png")), 300, 225)); //3 r
        GRAYSCALE.add(resizeImage(ImageIO.read(new File("arrowhead.png")), 100, 75)); //4 r
        GRAYSCALE.add(resizeImage(ImageIO.read(new File("roundhead.png")), 100, 75)); //5 r
        GRAYSCALE.add(resizeImage(ImageIO.read(new File("sharphead.png")), 120, 90)); //6 r
        GRAYSCALE.add(resizeImage(ImageIO.read(new File("avian.png")), 200, 150)); //7 r
        GRAYSCALE.add(resizeImage(ImageIO.read(new File("dotBulletBlack.png")), 48, 36)); //8
        GRAYSCALE.add(resizeImage(ImageIO.read(new File("ballOutline.png")), 100, 75)); //9
        GRAYSCALE.add(resizeImage(ImageIO.read(new File("ballOutline.png")), 100, 75)); //10
        GRAYSCALE.add(resizeImage(ImageIO.read(new File("bubble.png")), 300, 225)); //11
        GRAYSCALE.add(resizeImage(ImageIO.read(new File("bubbleBlack.png")), 300, 225)); //12
        GRAYSCALE.add(resizeImage(ImageIO.read(new File("coin.png")), 100, 75)); //13
        GRAYSCALE.add(resizeImage(ImageIO.read(new File("comet.png")), 200, 150)); //14 r
        GRAYSCALE.add(resizeImage(ImageIO.read(new File("crystal.png")), 100, 75)); //15 r
        GRAYSCALE.add(resizeImage(ImageIO.read(new File("ballOutline.png")), 120, 90)); //16 r
        GRAYSCALE.add(resizeImage(ImageIO.read(new File("dotBullet.png")), 48, 36)); //17
        GRAYSCALE.add(resizeImage(ImageIO.read(new File("glowDot.png")), 96, 72)); //18
        GRAYSCALE.add(resizeImage(ImageIO.read(new File("fire.png")), 100, 75)); //19 r
        GRAYSCALE.add(resizeImage(ImageIO.read(new File("flame.png")), 120, 90)); //20 r
        GRAYSCALE.add(resizeImage(ImageIO.read(new File("heart.png")), 200, 150)); //21 r
        GRAYSCALE.add(resizeImage(ImageIO.read(new File("knife.png")), 140, 105)); //22 r
        GRAYSCALE.add(resizeImage(ImageIO.read(new File("kunai.png")), 100, 75)); //23 r
        GRAYSCALE.add(resizeImage(ImageIO.read(new File("mentos.png")), 180, 135)); //24
        GRAYSCALE.add(resizeImage(ImageIO.read(new File("halfnote.png")), 120, 90)); //25
        GRAYSCALE.add(resizeImage(ImageIO.read(new File("quarternote.png")), 120, 90)); //26
        GRAYSCALE.add(resizeImage(ImageIO.read(new File("eighthnote.png")), 120, 90)); //27
        GRAYSCALE.add(resizeImage(ImageIO.read(new File("sixteenthnote.png")), 120, 90)); //28
        GRAYSCALE.add(resizeImage(ImageIO.read(new File("triplet.png")), 160, 120)); //29
        GRAYSCALE.add(resizeImage(ImageIO.read(new File("sixteendouble.png")), 140, 105)); //30
        GRAYSCALE.add(resizeImage(ImageIO.read(new File("quarterrest.png")), 120, 90)); //31
        GRAYSCALE.add(resizeImage(ImageIO.read(new File("sixteenthrest.png")), 120, 90)); //32
        GRAYSCALE.add(resizeImage(ImageIO.read(new File("nuke.png")), 400, 300)); //33
        GRAYSCALE.add(resizeImage(ImageIO.read(new File("orb.png")), 200, 150)); //34
        GRAYSCALE.add(resizeImage(ImageIO.read(new File("orbSmall.png")), 140, 105)); //35
        GRAYSCALE.add(resizeImage(ImageIO.read(new File("shuriken.png")), 140, 105)); //36 r
        GRAYSCALE.add(resizeImage(ImageIO.read(new File("spotlight.png")), 100, 75)); //37 r
        GRAYSCALE.add(resizeImage(ImageIO.read(new File("star.png")), 56, 42)); //38 r
        GRAYSCALE.add(resizeImage(ImageIO.read(new File("starBig.png")), 112, 84)); //39 r
        GRAYSCALE.add(resizeImage(ImageIO.read(new File("starHuge.png")), 224, 168)); //40 r
        GRAYSCALE.add(resizeImage(ImageIO.read(new File("starTwinkle.png")), 56, 42)); //41 r
        GRAYSCALE.add(resizeImage(ImageIO.read(new File("starBigTwinkle.png")), 112, 84)); //42 r
        GRAYSCALE.add(resizeImage(ImageIO.read(new File("starHugeTwinkle.png")), 224, 168)); //43 r
        GRAYSCALE.add(resizeImage(ImageIO.read(new File("teardrop.png")), 100, 75)); //44 r
        GRAYSCALE.add(resizeImage(ImageIO.read(new File("smolTwinkle.png")), 100, 75)); //45 r
        GRAYSCALE.add(resizeImage(ImageIO.read(new File("mediTwinkle.png")), 120, 90)); //46 r
        GRAYSCALE.add(resizeImage(ImageIO.read(new File("bigTwinkle.png")), 160, 120)); //47 r
        GRAYSCALE.add(resizeImage(ImageIO.read(new File("hugeTwinkle.png")), 240, 180)); //48 r
        GRAYSCALE.add(resizeImage(ImageIO.read(new File("wind.png")), 200, 150)); //49 r
        //total 29 rotated imgs. OMYGOD
        for (int i = 0; i < 50; i++) {
            BufferedImage e = GRAYSCALE.get(i);
            GRAYSCALE.set(i, (resizeImage(e, (int)(e.getWidth() / 2), (int)(e.getHeight() / 2))));
        }
        for (int i = 0; i < 50; i++) {
            BTABLE[0][0][i] = GRAYSCALE.get(i);
            if (true || ((i < 8) || ((13 < i) && (i < 17)) || ((18 < i) && (i < 24)) || (35 < i))) {
                for (int j = 0; j < 360; j++) {
                    BTABLE[0][j][i] = recolorImage(rotateImage(BTABLE[0][0][i], -j), j);
                    /*if (BTABLE[0][j][i] != null) {
                        for (int l = 0; l < 36; l++) {
                            BTABLE[l][j][i] = recolorImage(BTABLE[0][j][i], l * 60);
                        }
                    }*/
                }
            }
            System.out.println(i);
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
    public BufferedImage recolorImage(BufferedImage img, int hue) {
        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {
                Color c = new Color(img.getRGB(i, j), true);
                Color cc = Color.getHSBColor((float)(hue / 360.0), 1 - (float)(c.getRed() / 255.0), (float)(c.getRed() / 255.0));
                img.setRGB(i, j, new Color(cc.getRed(), cc.getGreen(), cc.getBlue(), c.getAlpha()).getRGB());
            }
        }
        /*int inputWidth = img.getWidth(), inputHeight = img.getHeight();
        int[] rgbRaster = ((DataBufferInt) img.getRaster().getDataBuffer()).getData(), rgb = new int[3];
        for(int i = 0; i < inputHeight; i++) {
            for(int j = 0; j < inputWidth; j++) {
                int index = (inputWidth  * i) + j;
                rgbRaster[index] ++;
            }
        }
        img.copyData(Raster.createWritableRaster(SampleModel.createSubsetSampleModel(rgbRaster), null));*/
        return img;
    }
}