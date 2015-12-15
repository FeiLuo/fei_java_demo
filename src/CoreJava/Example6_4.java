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
 * this class is used to test inner class
 */
public class Example6_4 extends Example {

    public Example6_4() {
        super("Example6_4");
    }

    public boolean doExample() {
        return false;
    }
    
    @Override
    public void example() {
        TalkingClock clock = new TalkingClock(2000, true);
        clock.start();
        
        JOptionPane.showMessageDialog(null, "Stop timer?");
        clock.stop();
    }

}

class TalkingClock {
    public TalkingClock(int interval, boolean beep) {
        this.interval = interval;
        this.beep = beep;
    }
    
    public void start(){
        ActionListener listener = new TimerPrinter();
        timer = new Timer(interval, listener);
        timer.start();
        
        JOptionPane.showMessageDialog(null, "Stop timer?");
        timer.stop();
    }
    
    public void stop() {
        timer.stop();
    }
    
    private int interval;
    private boolean beep;
    private Timer timer;
    
    private class TimerPrinter implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Date now = new Date();
            System.out.println("At the tone, the time is " + now);
            if (beep) { 
                Toolkit.getDefaultToolkit().beep();
            }
        }
    }
}
