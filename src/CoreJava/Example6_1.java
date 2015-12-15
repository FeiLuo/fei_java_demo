package CoreJava;

import java.util.Arrays;

/**
 * 
 * @author FLuo
 * this class is used to test Arrays's sort and interface implementation
 */
public class Example6_1 extends Example {

    public Example6_1() {
        super("Example6_1");
    }

    @Override
    public void example() {
        Employee6_1[] staff = new Employee6_1[3];
        
        staff[0] = new Employee6_1("Harry", 3500); 
        staff[1] = new Employee6_1("Carl", 7500); 
        staff[2] = new Employee6_1("Tony", 5500); 
        
        Arrays.sort(staff);
        
        for (Employee6_1 employee : staff) {
            System.out.println("name=" + employee.getName() + ", salary=" + employee.getSalary());
        }
    }

}

class Employee6_1 implements Comparable<Employee6_1>{
    
    public Employee6_1(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }
    
    @Override
    public int compareTo(Employee6_1 o) {
        if (salary < o.getSalary())
            return -1;
        if (salary > o.getSalary())
            return 1;
        return 0;
    }
    
    public String getName() {
        return name;
    }
    
    public double getSalary() {
        return salary;
    }
    
    private String name;
    private double salary;
}