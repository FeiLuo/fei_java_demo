package Actions;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ExceptionAction extends ActionBase {
    private Calculator calc;
    
    public ExceptionAction() {
        super("ExceptionAction");
    }
    
    public boolean doAction() {
        return false;
    }

    @Override
    public void action() {
        test();
    }
    
    private void test() {
        calc = new Calculator();
        
        try {
            calc.devide(20, 0);
            calc.devide(20, 2);
        } catch (IllegalArgumentException e) {
            System.out.println("Print Error: the devide number is zero!");
        }
        
        testException1();
        
        Rethrow.printFile("D:/file");
        
        NeverCaught.run();
    }
    
    // Example 1: Simple Exception
    private class Calculator {
        public int devide(int num1, int num2) {
            if (num2 == 0) {
                throw new IllegalArgumentException("Throw Exception: the devide number is zero!");
            }
            
            return num1 / num2;
        }
    }
    
    // Example 2: Exception path
    private static void testException1() {
        int[] ints = new int[] {1, 2, 3, 4};
        System.out.println("Before Exception:");
        try {
            System.out.println(ints[4]);
            System.out.println("Does it be run?");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Out of boudary!");
        }
        System.out.println("After Exception!");
    }
    
    // Example 3: Re-throw Exception
    private static class Rethrow {
        public static void readFile(String file) throws FileNotFoundException {
            try {
                new BufferedInputStream(new FileInputStream(file));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.err.println("I don't know how to process exception, so re-throw it!");
                //重新抛出异常
                throw e;
            }
        }
         
        public static void printFile(String file) {
            try {
                readFile(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    
    // Example 4: 
    private static class ExceptionA extends Exception {
        /**
         * 
         */
        private static final long serialVersionUID = 1L;

        public ExceptionA(String str) {
            super();
        }
    }
     
    private static class ExceptionB extends ExceptionA {
     
        /**
         * 
         */
        private static final long serialVersionUID = 1L;

        public ExceptionB(String str) {
            super(str);
        }
    }
     
    private static class ExceptionC extends ExceptionA {
        /**
         * 
         */
        private static final long serialVersionUID = 1L;

        public ExceptionC(String str) {
            super(str);
        }
    }
    

    private static class NeverCaught {
        static void f() throws ExceptionB {
            throw new ExceptionB("exception b");
        }
     
        static void g() throws ExceptionC {
            try {
                f();
            } catch (ExceptionB e) {
                ExceptionC c = new ExceptionC("exception c");
                //异常连
                c.initCause(e);
                throw c;
            }
        }
     
        static public void run() {
            try {
                System.out.println("Exception new testing: ");
                g();
            } catch (ExceptionC e) {
                e.printStackTrace();
            }
        }
     
    }
}

