/**
 * 
 */
package CoreJava;

/**
 * @author FLuo
 * this class is used to test enum class.
 */
public class Example5_5 extends Example {

    public Example5_5() {
        super("Example5_5");
    }

    @Override
    public void example() {
        String input = "LARGE";
        EnumSize size = Enum.valueOf(EnumSize.class, input);
        System.out.println("size=" + size);
        System.out.println("abbreviation=" + size.getAbbreviation());
    }
}

enum EnumSize {
    SMALL("S"), MEDIUM("M"), LARGE("L"), EXTRA_LARGE("XL");
    
    private EnumSize(String abbreviation) {
        this.abbreviation = abbreviation;
    } 
    
    public String getAbbreviation() {
        return abbreviation;
    }
    
    private String abbreviation;
}