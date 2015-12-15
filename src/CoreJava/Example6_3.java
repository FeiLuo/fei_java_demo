package CoreJava;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 * 
 * @author FLuo
 * the class is used for callback example
 */
public class Example6_3 extends Example {

    public Example6_3() {
        super("Example6_3");
    }
    
    public boolean doExample() {
        return false;
    }

    @Override
    public void example() {
        ActionListener listener = new TimerPrinter();
        Timer t = new Timer(2000, listener);
        t.start();
        
        JOptionPane.showMessageDialog(null, "Stop timer?");
        t.stop();
    }

}

class TimerPrinter implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        Date now = new Date();
        System.out.println("At the tone, the time is " + now);
        Toolkit.getDefaultToolkit().beep();
    }
}