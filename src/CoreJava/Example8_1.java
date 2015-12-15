package CoreJava;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 * @author fluo
 * Learn Points:
 *  1. ActionListener
 *  
 */
public class Example8_1 extends Example {

    public Example8_1() {
        super("Example8_1");
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
                ButtonFrame8_1 frame = new ButtonFrame8_1();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

class ButtonFrame8_1 extends JFrame {
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 200;
    private JPanel panel;

    public ButtonFrame8_1() {
        setTitle("Button Test");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        panel = new JPanel();
        add(panel);

        JButton redButton = new JButton("Red");
        JButton yellowButton = new JButton("Yellow");
        JButton blueButton = new JButton("Blue");

        panel.add(redButton);
        panel.add(yellowButton);
        panel.add(blueButton);

        ActionListener redListener = new ColorAction(Color.RED);
        ActionListener yellowListener = new ColorAction(Color.YELLOW);
        ActionListener blueListener = new ColorAction(Color.BLUE);

        redButton.addActionListener(redListener);
        yellowButton.addActionListener(yellowListener);
        blueButton.addActionListener(blueListener);
    }

    class ColorAction implements ActionListener {

        private Color color;

        public ColorAction(Color c) {
            color = c;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            panel.setBackground(color);
        }

    }
}
