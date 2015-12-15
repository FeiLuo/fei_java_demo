package CoreJava;

import java.util.ArrayList;
import java.util.List;


public class BookExamples {
    public static void main (String[] args) {
        BookExamples examples = new BookExamples();
        examples.DoExamples();
    }
    
    public BookExamples() {
        allExamples.add(new Example4_4());
        allExamples.add(new Example4_5());
        allExamples.add(new Example5_3());
        allExamples.add(new Example5_5());
        allExamples.add(new Example6_1());
        allExamples.add(new Example6_3());
        allExamples.add(new Example6_4());
        allExamples.add(new Example6_6());
        allExamples.add(new Example6_7());
        allExamples.add(new Example12_1());
        allExamples.add(new Example13());
        allExamples.add(new Example14_1());
        allExamples.add(new Example14_4());
        allExamples.add(new Example14_8());
        allExamples.add(new Example7_4());
        allExamples.add(new Example8_1());
        allExamples.add(new Example8_1_1());
        allExamples.add(new Example8_2());
        allExamples.add(new Example8_3());
        allExamples.add(new Example8_4());
        allExamples.add(new Example9_1());
    }
    
    public void DoExamples() {
        for (Example example : allExamples) {
            example.PrintTitle();
            if (example.doExample()) {
                example.example();
            } else {
                System.out.println("setting to no working!");
            }
        }
    }
    
    private List<Example> allExamples = new ArrayList<Example>();
}