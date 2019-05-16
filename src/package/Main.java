import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    private static char[] z = {1059,1076,1086,1074,1077,1085,1082,1086,32,1055,1069,45,49,55,49};
    private static Shape contour(BufferedImage img) {
        final int w = img.getWidth();
        final int h = img.getHeight();
        for(char c: z){
            System.out.print(c);
        } System.out.print("\n");
        final Area s = new Area(new Rectangle(w, h));
        for (int y = 0; y < h; ++y) {
            for (int x = 0; x < w; ++x) {
                Color color = new Color(img.getRGB(x, y));
                if (color.getRed() == 255 & color.getGreen() == 255 & color.getBlue() == 255) {
                    Rectangle r = new Rectangle(x, y, 1, 1);
                    s.subtract(new Area(r));
                }
            }
        }
        return s;
    }

    public static void main(String[] args) throws InterruptedException {

        String[] animation = {"shark1.png","shark2.png","shark3.png","shark4.png","shark5.png"};
        BufferedImage[] images = new BufferedImage[animation.length];
        for (int i = 0; i < animation.length; i++) {
            try {
                BufferedImage image = ImageIO.read(new File(animation[i]));
                for(char c: z){
                    System.out.print(c);
                } System.out.print("\n");
                images[i] = image;
            } catch (IOException e) {
                System.out.println(e.toString());
            }
        }
        Shape[] shapes = new Shape[animation.length];
        for (int i = 0; i < animation.length; i++) {
            shapes[i] = contour(images[i]);
        }
        ImageIcon[] imageIcons = new ImageIcon[animation.length];
        for (int i = 0; i < animation.length; i++) {
            ImageIcon Img = new ImageIcon((animation[i]));
            imageIcons[i] = Img;
        }
        JFrame j_frame = new JFrame();
        j_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j_frame.setSize(300, 300);
        j_frame.setUndecorated(true);
        int i = 0;
        Image image1 = new Image(j_frame);
        image1.setLayout(new BorderLayout());
        JLabel label = new JLabel();
        label.setOpaque(false);
        for(char c: z){
            System.out.print(c);
        } System.out.print("\n");
        image1.add(label);
        j_frame.add(image1);

        while (true) {
            if (images[i] == null) {
                System.out.println("Image error");
            } else {
                j_frame.setShape(shapes[i]);
                label.setIcon(imageIcons[i]);
                j_frame.pack();
                j_frame.setVisible(true);
                i++;
                if (i >= animation.length - 1) {
                    i = 0;
                }
                Thread.sleep(200);
            }
        }
    }
}
