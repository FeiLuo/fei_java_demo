package Actions;

public class PrintAction extends ActionBase {

    public PrintAction() {
        super("PrintAction");
    }
    
    public void action() {
        System.out.println("This is the println!");
        System.out.print("This is the print!\n");
    }
}
