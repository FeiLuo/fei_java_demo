package CoreJava;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

/**
 * 
 * @author fluo
 *
 *  1. KeyStroke for hot key
 *  2. InputMap, ActionMap
 *  3. putValue for action
 *  
 *  
 */
public class Example8_3 extends Example {

    public Example8_3() {
        super("Example8_3");
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
                ActionFrame frame = new ActionFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
            
        });
    }
}

class ActionFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 500;
    private static final int DEFAULT_HEIGHT = 500;
    private JPanel buttonPanel;
    
    public ActionFrame() {
        setTitle("Action Test");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        
        buttonPanel = new JPanel();
        
        Action redAction = new ColorAction8_3("Red", new ImageIcon("resource/Example8_3/red-ball.gif"), Color.RED);
        Action yellowAction = new ColorAction8_3("Yellow", new ImageIcon("resource/Example8_3/yellow-ball.gif"), Color.YELLOW);
        Action blueAction = new ColorAction8_3("Blue", new ImageIcon("Resource/Example8_3/blue-ball.gif"), Color.BLUE);
        
        buttonPanel.add(new JButton(redAction));
        buttonPanel.add(new JButton(yellowAction));
        buttonPanel.add(new JButton(blueAction));
        
        add(buttonPanel);
        
        InputMap imap = buttonPanel.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        imap.put(KeyStroke.getKeyStroke("ctrl R"), "panel.red");
        imap.put(KeyStroke.getKeyStroke("ctrl Y"), "panel.yellow");
        imap.put(KeyStroke.getKeyStroke("ctrl B"), "panel.blue");
        
        ActionMap amap = buttonPanel.getActionMap();
        amap.put("panel.red", redAction);
        amap.put("panel.yellow", yellowAction);
        amap.put("panel.blue", blueAction);
    }

    class ColorAction8_3 extends AbstractAction {
        
        public ColorAction8_3(String name, Icon icon, Color c) {
            putValue(Action.NAME, name);
            putValue(Action.SMALL_ICON, icon);
            putValue(Action.SHORT_DESCRIPTION, "Set panel color to " + name.toLowerCase());
            putValue("Color", c);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Color c = (Color) getValue("Color");
            buttonPanel.setBackground(c);
        }
    }
}





