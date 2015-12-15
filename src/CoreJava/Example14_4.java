package CoreJava;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Example14_4 extends Example {

    public Example14_4() {
        super("Example14_4");
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
                JFrame frame = new BounceFrame14_4();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

class BounceFrame14_4 extends JFrame {
    private BallComponent14_4 comp;
    public static final int DEFAULT_WIDTH = 450;
    public static final int DEFAULT_HEIGHT = 450;
    public static final int STEPS = 1000;
    public static final int DELAY = 10;

    public BounceFrame14_4() {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setTitle("Bounce");
        
        comp = new BallComponent14_4();
        add(comp, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        addButton(buttonPanel, "Start", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBall();
            }
        });
        
        addButton(buttonPanel, "Close", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addButton(JPanel buttonPanel, String title, ActionListener actionListener) {
        JButton button = new JButton(title);
        buttonPanel.add(button);
        button.addActionListener(actionListener);
    }

    private void addBall() {
        Ball14_4 ball = new Ball14_4();
        comp.add(ball);
        Runnable r = new BallRunnable(ball, comp);
        Thread t = new Thread(r);
        t.start();
    }   
}

class Ball14_4 {
    private static final int XSIZE = 15;
    private static final int YSIZE = 15;
    private double x = 0;
    private double y = 0;
    private double dx = 1;
    private double dy = 1;
    private Random generator = new Random();
    
    public Ball14_4() {
        dx = getRandom();
        dy = getRandom();
    }
    
    public Ellipse2D getShape() {
        return new Ellipse2D.Double(x, y, XSIZE, YSIZE);
    }
    
    public void move(Rectangle2D bounds) {
       x += dx;
       y += dy;
       if (x < bounds.getMinX()) {
          x = bounds.getMinX();
          dx = getRandom();
       }
       if (x + XSIZE >= bounds.getMaxX()) {
          x = bounds.getMaxX() - XSIZE;
          dx = -getRandom();
       }
       if (y < bounds.getMinY()) {
          y = bounds.getMinY();
          dy = getRandom();
       }
       if (y + YSIZE >= bounds.getMaxY()) {
          y = bounds.getMaxY() - YSIZE;
          dy = -getRandom();
       }
    }
    
    private int getRandom() {
        int rx = 0;
        while (rx == 0) {
            rx = generator.nextInt(5);
        }
        
        return rx;
    }
}

class BallRunnable implements Runnable {
    private Ball14_4 ball;
    private Component component;
    public static final int STEPS = 1000;
    public static final int DELAY = 5;
    
    public BallRunnable(Ball14_4 aBall, Component aComponent) {
        ball = aBall;
        component = aComponent;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= STEPS; i++) {
                ball.move(component.getBounds());
                component.repaint();
                Thread.sleep(DELAY);
            }
        }
        catch (InterruptedException e) {
        }
    }
}

class BallComponent14_4 extends JPanel {
    
    private ArrayList<Ball14_4> balls = new ArrayList<Ball14_4>();
    
    public void add(Ball14_4 b) {
        balls.add(b);
    }
    
    public void paintComponent(Graphics g) {
       super.paintComponent(g); // erase background
       Graphics2D g2 = (Graphics2D) g;
       for (Ball14_4 b : balls) {
          g2.fill(b.getShape());
       }
    }
}