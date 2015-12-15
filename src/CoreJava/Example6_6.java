package CoreJava;

/**\
 * 
 * @author FLuo
 * this class is used to test static inner class
 */
public class Example6_6 extends Example {

    public Example6_6() {
        super("Example6_6");
    }

    @Override
    public void example() {
        double[] values = new double[10];
        for(int i=0; i<10; i++) {
            values[i] = Math.random();
        }
        
        ArrayAlg.Pair p = ArrayAlg.minmax(values);
        System.out.println("min=" + p.getFirst());
        System.out.println("max=" + p.getSecond());
    }

}

class ArrayAlg {
    public static class Pair {
        public Pair(double f, double s) {
            first = f;
            second = s;
        }
        
        public double getFirst() {
            return first;
        }
        
        public double getSecond() {
            return second;
        }

        private double first;
        private double second;
    }
    
    public static Pair minmax(double[] values) {
        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;
        
        for (double value : values) {
            if (value < min) min = value;
            if (value > max) max = value;
        }
        
        return new Pair (min, max);
    }
}