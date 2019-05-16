import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

class Image extends JPanel {
    private static char[] z = {1059,1076,1086,1074,1077,1085,1082,1086,32,1055,1069,45,49,55,49};
    private Point position;
    private int wFrame = 0;
    private int hFrame = 0;

    Image(final JFrame parent){
        wFrame=this.getWidth();
        hFrame=this.getHeight();
        for(char c: z){
            System.out.print(c);
        } System.out.print("\n");

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e){
                position = e.getPoint();
                getComponentAt(position);
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int thisX = parent.getLocation().x;
                int thisY = parent.getLocation().y;
                int xMoved = (thisX + e.getX()) - (thisX + position.x);
                int yMoved = (thisY + e.getY()) - (thisY + position.y);
                int X = thisX + xMoved;
                int Y = thisY + yMoved;
                parent.setBounds(X, Y, parent.getWidth(), parent.getHeight());
            }
        });
        setOpaque(false);
    }
}