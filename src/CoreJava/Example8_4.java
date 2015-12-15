package CoreJava;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 * 
 * @author fluo
 *
 * this class share mouse usage
 * 
 * 
 * 
 */
public class Example8_4 extends Example {

    public Example8_4() {
        super("Example8_4");
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
                JFrame frame = new MouseFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

class MouseFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 200;
    
    public MouseFrame() {
        setTitle("Mouse Test");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        
        MouseComponent comp = new MouseComponent();
        add(comp);
    }
}

class MouseComponent extends JComponent {
    private static final int SIDELENGTH = 10;
    private ArrayList<Rectangle2D> squares;
    private Rectangle2D current;
    
    public MouseComponent() {
        squares = new ArrayList<Rectangle2D>();
        current = null;
        
        addMouseListener(new MouseHandler());
        addMouseMotionListener(new MouseMotionHandler());
    }
    
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        for (Rectangle2D r : squares) {
            g2.draw(r);
        }
    }
    
    public Rectangle2D find(Point2D p) {
        for (Rectangle2D r : squares) {
            if (r.contains(p)) return r;
        }
        return null;
    }
    
    public void add(Point2D p) {
        double x = p.getX();
        double y = p.getY();
        
        current = new Rectangle2D.Double(x - SIDELENGTH/2, y - SIDELENGTH/2, SIDELENGTH, SIDELENGTH);
        squares.add(current);
        repaint();
    }
    
    public void remove(Rectangle2D s) {
        if (s == null) return;
        if (s == current) current = null;
        squares.remove(s);
        repaint();
    }
    
    class MouseHandler extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            current = find(e.getPoint());
            if (current != null) remove(current);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            current = find(e.getPoint());
            if (current == null) add(e.getPoint());
        }
    }
    
    class MouseMotionHandler implements MouseMotionListener {

        @Override
        public void mouseDragged(MouseEvent e) {
            if (current == null)
                return;
            
            int x = e.getX();
            int y = e.getY();
            
            current.setFrame(x - SIDELENGTH/2, y - SIDELENGTH/2, SIDELENGTH, SIDELENGTH);
            repaint();
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            if (find(e.getPoint()) == null)
                setCursor(Cursor.getDefaultCursor());
            else
                setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        }
    }
}