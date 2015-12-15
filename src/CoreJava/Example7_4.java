package CoreJava;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

/**
 * 
 * @author fluo
 * 
 * Learn Points:
 *  1. Graphics, Graphics2D, Rectangle2D, Ellipse2D
 *  
 *   
 *   
 */
public class Example7_4 extends Example {

    public Example7_4() {
        super("Example7_4");
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public boolean doExample() {
        return false;
    }

    @Override
    public void example() {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                DrawFrame frame = new DrawFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

class DrawFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 400;
    private static final int DEFAULT_HEIGHT = 400;

    public DrawFrame() {
        setTitle("DrawTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        DrawComponent comp = new DrawComponent();
        add(comp);
    }

}

class DrawComponent extends JComponent {

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        // draw a rectangle

        double leftX = 100;
        double topY = 100;
        double width = 200;
        double height = 150;

        Rectangle2D rect = new Rectangle2D.Double(leftX, topY, width, height);
        g2.draw(rect);

        // draw the enclosed ellipse

        Ellipse2D ellipse = new Ellipse2D.Double();
        ellipse.setFrame(rect);
        g2.draw(ellipse);

        // draw a diagonal line

        g2.draw(new Line2D.Double(leftX, topY, leftX + width, topY + height));

        // draw a circle with the same center

        double centerX = rect.getCenterX();
        double centerY = rect.getCenterY();
        double radius = 150;

        Ellipse2D circle = new Ellipse2D.Double();
        circle.setFrameFromCenter(centerX, centerY, centerX + radius, centerY + radius);
        g2.draw(circle);
    }
}