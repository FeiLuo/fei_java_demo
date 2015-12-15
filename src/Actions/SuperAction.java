package Actions;

/**
 * 
 * @author FLuo
 * the SuperAction is used to test super() method.
 */
final public class SuperAction extends ActionBase {

    public SuperAction() {
        super("SuperAction");
    }

    @Override
    public void action() {
        c = new SupperClassC(); 
    }

    SupperClassC c;
}

class SupperClassA {

    public SupperClassA() {
        this.salary = 100;
        System.out.println("SupperClassA salary=" + this.getSalary());
    }

    /**
     * @return the salary
     */
    public double getSalary() {
        return salary;
    }

    protected double salary;
}

class SupperClassB extends SupperClassA {

    public SupperClassB() {
        super();
        System.out.println("SupperClassB salary=" + this.getSalary());
    }

    /**
     * @return the salary
     */
    public double getSalary() {
        return (super.getSalary() * 1.2);
    }
}

class SupperClassC extends SupperClassB {

    public SupperClassC() {
        super();
        System.out.println("SupperClassC salary=" + this.getSalary());
    }

    /**
     * @return the salary
     */
    public double getSalary() {
        return (super.getSalary() * 10);
    }
}

