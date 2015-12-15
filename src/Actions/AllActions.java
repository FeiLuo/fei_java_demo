package Actions;

import java.util.*;

public class AllActions {
    public static void main(String[] args) {
        AllActions actions = new AllActions();
        actions.RunAllActions();
    }

    public AllActions() {
        allActions.add(new PrintAction());
        allActions.add(new InputAction());
        allActions.add(new SuperAction());
        allActions.add(new ExceptionAction());
        allActions.add(new LoggerAction());
        allActions.add(new PaperAction());
    }

    public void RunAllActions() {
        for (ActionBase action : allActions) {
            action.PrintTitle();
            if (action.doAction()) {
                action.action();
            } else {
                System.out.println("setting to no working!");
            }
        }
    }

    List<ActionBase> allActions = new ArrayList<ActionBase>();
}
