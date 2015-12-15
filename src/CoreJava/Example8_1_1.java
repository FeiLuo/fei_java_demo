package CoreJava;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 * @author fluo
 *
 * Learn Points:
 *  1. ActionListener
 *  2. Intern function for 3 buttons
 */
public class Example8_1_1 extends Example {

    public Example8_1_1() {
        super("Example8_1_1");
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean doExample() {
        return false;
    }
    
    @Override
    public void example() {
        ButtonFrame8_1_1 frame = new ButtonFrame8_1_1();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

class ButtonFrame8_1_1 extends JFrame {
//    private static final int DEFAULT_WIDTH = 300;
//    private static final int DEFAULT_HEIGHT = 200;
    private JPanel panel;

    public ButtonFrame8_1_1() {
        setTitle("Button Test");
        
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screensize);

        panel = new JPanel();
        add(panel);

        makeButton("Red", Color.RED);
        makeButton("Yellow", Color.YELLOW);
        makeButton("Blue", Color.BLUE);
    }
    
    public void makeButton(String text, final Color c) {
        JButton button = new JButton(text);
        panel.add(button);
        
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.setBackground(c);
            }
        });
    }

}
