/**
 * 
 */
package CoreJava;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author FLuo
 * this example is used to test toString(), hashCode(), equals().
 */
final public class Example5_3 extends Example {

    public Example5_3() {
        super("Example5_3");
    }

    /* (non-Javadoc)
     * @see CoreJava.Example#example()
     */
    @Override
    public void example() {
        Employee5_3 alice1 = new Employee5_3("Alice Adams", 75000, 1987, 12, 15);
        Employee5_3 alice2 = alice1;
        Employee5_3 alice3 = new Employee5_3("Alice Adams", 75000, 1987, 12, 15);
        Employee5_3 bob = new Employee5_3("Bob Brandson", 50000, 1989, 10, 1);
        
        System.out.println("alice1 == alice2 :" + (alice1 == alice2));
        System.out.println("alice1 == alice3 :" + (alice1 == alice3));
        System.out.println("alice1.equals(alice3) :" + alice1.equals(alice3));
        System.out.println("alice1.equals(bob) :" + alice1.equals(bob));
        System.out.println("bob.toString(): :" + bob.toString());
        
        Manager5_3 carl = new Manager5_3("Carl Cracker", 80000, 1987, 12, 15);
        Manager5_3 boss = new Manager5_3("Carl Cracker", 80000, 1987, 12, 15);
        boss.setBonus(5000);
        System.out.println("boss.toString(): :" + boss.toString());
        System.out.println("carl.equals(boss) :" + carl.equals(boss));
        System.out.println("alice1.hashCode() :" + alice1.hashCode());
        System.out.println("alice3.hashCode() :" + alice3.hashCode());
        System.out.println("carl.hashCode() :" + carl.hashCode());
        System.out.println("boss.hashCode() :" + boss.hashCode());
    }

}

class Employee5_3 {

    public Employee5_3(String n, double s, int year, int month, int day) {
        super();
        this.name = n;
        this.salary = s;
        GregorianCalendar calendar = new GregorianCalendar(year, month, day);
        hireDay = calendar.getTime();
    }
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * @return the salary
     */
    public double getSalary() {
        return salary;
    }
    /**
     * @return the hireDay
     */
    public Date getHireDay() {
        return hireDay;
    }
    
    public void raiseSalary(double byPercent){
        salary = salary * (1 + byPercent / 100); 
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((hireDay == null) ? 0 : hireDay.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        long temp;
        temp = Double.doubleToLongBits(salary);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Employee5_3 other = (Employee5_3) obj;
        if (hireDay == null) {
            if (other.hireDay != null)
                return false;
        } else if (!hireDay.equals(other.hireDay))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (Double.doubleToLongBits(salary) != Double
                .doubleToLongBits(other.salary))
            return false;
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Employee5_3 [name=" + name + ", salary=" + salary
                + ", hireDay=" + hireDay + "]";
    }

    private String name;
    private double salary;
    private Date hireDay;
}

class Manager5_3 extends Employee5_3 {
    
    public Manager5_3(String n, double s, int year, int month, int day) {
        super(n, s, year, month, day);
        bonus = 0;
    }

    /**
     * @return the bonus
     */
    public double getBonus() {
        return bonus;
    }

    /**
     * @param bonus the bonus to set
     */
    public void setBonus(double bonus) {
        this.bonus = bonus;
    }
    
    /**
     * @return the salary
     */
    public double getSalary() {
        return super.getSalary() + getBonus();
    }
    
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Manager [bonus=" + bonus + ", toString()=" + super.toString()
                + "]";
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(bonus);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Manager5_3 other = (Manager5_3) obj;
        if (Double.doubleToLongBits(bonus) != Double
                .doubleToLongBits(other.bonus))
            return false;
        return true;
    }

    private double bonus;


}
