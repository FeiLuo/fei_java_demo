package CoreJava;

import java.util.Random;

/**
 * 
 * @author FLuo
 * the Example4_5 is used to test initialization block and static initialization block
 */

final public class Example4_5 extends Example {
    
    public Example4_5() {
        super("Example4_5");
    }

    @Override
    public void example() {
        // TODO Auto-generated method stub

    }

}

class Employee4_5 {
    public Employee4_5(String name, double salary) {
        employeeName = name;
        employeeSalary = salary;
    }
    
    public Employee4_5(double salary) {
        this("Employee $" + nextId, salary);
    }
    
    public Employee4_5() {
        // name initialized to "";
        // salary not explicitly set
        // id initialized in initializaion block
    }
    
    public String getName() {
        return employeeName;
    }
    
    public double getSalary() {
        return employeeSalary;
    }
    
    public void raiseSalary(double byPercent){
        employeeSalary = employeeSalary * (1 + byPercent / 100); 
    }
    
    /**
     * @return the employeeId
     */
    public int getEmployeeId() {
        return employeeId;
    }

    /**
     * @param employeeId the employeeId to set
     */
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    private static int nextId;
    
    private String employeeName = "";
    private double employeeSalary;
    private int employeeId;
    
    // static initialization block
    static {
        Random generator = new Random();
        nextId = generator.nextInt(10000);
    }
    
    // object initialization block
    {
        setEmployeeId(nextId);
        nextId++;
    }
}
