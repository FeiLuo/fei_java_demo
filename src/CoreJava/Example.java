package CoreJava;

/**
 * 
 * @author FLuo
 * the base class for all examples in book core java
 */
public abstract class Example {
    public Example(String name) {
        EXAMPLE_NAME = name;
    }
    
    public boolean doExample() {
        return true;
    }
    
    public abstract void example();
    
    public String getExampleName() {
        return EXAMPLE_NAME;
    }
    
    public void PrintTitle() {
        System.out.println("");
        System.out.println("//====================================================");
        System.out.println("// " + getExampleName());
        System.out.println("//====================================================");
    }
    
    public final String EXAMPLE_NAME; 
}
