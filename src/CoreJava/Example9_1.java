package CoreJava;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 * @author fluo
 *
 *
 *
 */
public class Example9_1 extends Example {

    public Example9_1() {
        super("Example9_1");
    }

    @Override
    public boolean doExample() {
        return true;
    }

    @Override
    public void example() {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                JFrame frame = new Calculate9_1Frame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

class Calculate9_1Frame extends JFrame {

    public Calculate9_1Frame() {
        setTitle("Calculator");
        Calculator9_1Panel panel = new Calculator9_1Panel();
        add(panel);
        pack();
    }
}

class Calculator9_1Panel extends JPanel {
    private JButton display;
    private JPanel panel;
    private double result;
    private String lastCommand;
    private boolean start;
    
    public Calculator9_1Panel() {
        setLayout(new BorderLayout());
        
        result = 0;
        lastCommand = "";
        start = true;
        
        display = new JButton("0");
        display.setEnabled(false);
        add(display, BorderLayout.NORTH);
        
        ActionListener insert = new InsertAction();
        ActionListener command = new CommandAction();
        
        panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4));
        
        addButton("7", insert);
        addButton("8", insert);
        addButton("9", insert);
        addButton("/", command);
        
        addButton("4", insert);
        addButton("5", insert);
        addButton("6", insert);
        addButton("*", command);
        
        addButton("1", insert);
        addButton("2", insert);
        addButton("3", insert);
        addButton("-", command);
        
        addButton("0", insert);
        addButton(".", insert);
        addButton("=", command);
        addButton("+", command);
        
        add(panel, BorderLayout.CENTER);
    }

    private void addButton(String label, ActionListener listener) {
        JButton button = new JButton(label);
        button.addActionListener(listener);
        panel.add(button);
    }
    
    private class InsertAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String input = e.getActionCommand();
            if (start) {
                display.setText("");
                start = false;
            }
            display.setText(display.getText() + input);
        }
    }
    
    private class CommandAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            
            if (start) {
                if (command.equals("-")) {
                    display.setText(command);
                    start = false;
                } 
                else {
                    lastCommand = command;
                }
            }
            else {
                calculate(Double.parseDouble(display.getText()));
                lastCommand = command;
                start = true;
            }
        }
    }
    
    public void calculate(double x) {
        if (lastCommand.equals("+")) result += x;
        else if (lastCommand.equals("-")) result -= x;
        else if (lastCommand.equals("*")) result *= x;
        else if (lastCommand.equals("/")) result /= x;
        else if (lastCommand.equals("=")) result = x;
        display.setText(""+result);
    }
}
