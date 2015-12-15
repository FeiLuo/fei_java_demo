package CoreJava;

import java.util.*;

/**
 * 
 * @author fluo
 *
 * 1. Template
 * 2. public static <T extends Comparable> Pair<T> minmax(T[] a)
 * 
 * 
 */
public class Example12_1 extends Example {

    public Example12_1() {
        super("Example12_1");
    }

    @Override
    public void example() {
        GregorianCalendar[] birthdays = {
                new  GregorianCalendar(1906, Calendar.DECEMBER, 9),
                new  GregorianCalendar(1815, Calendar.DECEMBER, 10),
                new  GregorianCalendar(1903, Calendar.DECEMBER, 3),
                new  GregorianCalendar(1910, Calendar.JUNE, 22),
        };
        
        Pair<GregorianCalendar> mm = ArrayAlg12_1.minmax(birthdays);
        System.out.println("min = " + mm.getFirst().getTime());
        System.out.println("max = " + mm.getSecond().getTime());
    }

}

class Pair<T> {
    private T first;
    private T second;
    
    public Pair() { first = null; second = null; }
    public Pair(T first, T second) { this.first = first; this.second = second; }
    
    public T getFirst() {
        return first;
    }
    public void setFirst(T first) {
        this.first = first;
    }
    public T getSecond() {
        return second;
    }
    public void setSecond(T second) {
        this.second = second;
    }
}

class ArrayAlg12_1 {
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static <T extends Comparable> Pair<T> minmax(T[] a) {
        if (a == null || a.length == 0)
            return null;
        T min = a[0];
        T max = a[0];
        for (int i = 0; i < a.length; i++) {
            if (min.compareTo(a[i]) > 0)
                min = a[i];
            if (max.compareTo(a[i]) < 0)
                max = a[i];
        }
        return new Pair<T>(min, max);
    }
}