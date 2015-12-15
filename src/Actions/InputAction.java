package Actions;

import java.util.*;

public class InputAction extends ActionBase {
    public InputAction() {
        super("InputAction");
    }
    
    public boolean doAction() {
        return false;
    }
    
    public void action() {
        Scanner in = new Scanner(System.in);
        
        // get first input
        System.out.print("What's your name? ");
        String name = in.nextLine();
        
        System.out.print("How old are you! ");
        int age = in.nextInt();
        
        System.out.println("Hello, " + name + ". Next year, you'll be " + (age + 1));
        
    }
}
