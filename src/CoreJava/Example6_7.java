package CoreJava;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Random;

/**
 * 
 * @author FLuo
 * this is the class to test Java Proxy
 */
public class Example6_7 extends Example {

    public Example6_7() {
        super("Example6_7");
    }

    @Override
    public void example() {
        Object[] elements = new Object[1000];
        
        for (int i=0; i<elements.length; i++) {
            Integer value = i + 1;
            InvocationHandler handler = new TraceHandler(value);
            Object proxy = Proxy.newProxyInstance(null,  new Class[] {Comparable.class}, handler);
            elements[i] = proxy;
        }
        
        Integer key = new Random().nextInt(elements.length) + 1;
        System.out.println("Random data " + key);
        
        int result = Arrays.binarySearch(elements, key);
        if (result >= 0) {
            System.out.println(elements[result]);
        }
    }
}

class TraceHandler implements InvocationHandler {
    private Object target;
    
    public TraceHandler(Object t) {
        target = t;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        // TODO Auto-generated method stub
        System.out.print(target);
        System.out.print("." + method.getName() + "(");
        if (args != null) {
            for (int i=0; i<args.length; i++) {
                System.out.print(args[i]);
                if (i < (args.length - 1)) {
                    System.out.print(", ");
                }
            }
        }
        System.out.println(")");
        
        return method.invoke(target, args);
    }
    
    
}