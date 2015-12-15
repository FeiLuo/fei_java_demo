package CoreJava;

/**
 * 
 * @author FLuo
 * the Example4_4 is used to test parameters changes
 */

final public class Example4_4 extends Example {

    public Example4_4() {
        super("Example4_4");
    }
    
    @Override
    public void example() {
        /*
         * Test 1: Method isn't modify numeric parameters
         */
        System.out.println("Testing tripleValue:");
        double percent = 10;
        System.out.println("Before: percent=" + percent);
        tripleValue(percent);
        System.out.println("After: percent=" + percent);
        
        /*
         * Test 2: Method can't attach new objects to object parameters
         */
        System.out.println("Testing swap:");
        Employee4_4 a = new Employee4_4("Alice", 7000);
        Employee4_4 b = new Employee4_4("Bob", 6000);
        System.out.println("Before: a=" + a.getEmployeeName());
        System.out.println("Before: b=" + b.getEmployeeName());
        swap(a, b);
        System.out.println("After: a=" + a.getEmployeeName());
        System.out.println("After: b=" + b.getEmployeeName());
    }
    
    public static void tripleValue(double x) {
        x = 3 * x;
        System.out.println("End of value: x=" + x);
    }
    
    public static void swap(Employee4_4 x, Employee4_4 y){
        Employee4_4 temp = x;
        x = y;
        y = temp;
        
        System.out.println("End of method: x=" + x.getEmployeeName());
        System.out.println("End of method: y=" + y.getEmployeeName());
    }
}

class Employee4_4 {
    public Employee4_4(String name, double salary) {
        setEmployeeName(name);
        employeeSalary = salary;
    }
    
    public void raiseSalary(double byPercent){
        setEmployeeSalary(employeeSalary * (1 + byPercent / 100)); 
    }
    
    /**
     * @return the employeeName
     */
    public String getEmployeeName() {
        return employeeName;
    }

    /**
     * @param employeeName the employeeName to set
     */
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    /**
     * @return the employeeSalary
     */
    public double getEmployeeSalary() {
        return employeeSalary;
    }

    /**
     * @param employeeSalary the employeeSalary to set
     */
    public void setEmployeeSalary(double employeeSalary) {
        this.employeeSalary = employeeSalary;
    }
    
    private String employeeName;
    private double employeeSalary;

}