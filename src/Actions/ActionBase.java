package Actions;

/**
 * 
 * @author FLuo
 * the base class for all test point class
 *
 */
abstract class ActionBase {
	public ActionBase(String name) {
	    CLASS_NAME = name;
	}
	
	public abstract void action();
	
	public String getClassName() {
		return CLASS_NAME;
	}
	
	public boolean doAction() {
	    return true;
	}
	
	public void PrintTitle() {
	    System.out.println("");
	    System.out.println("//====================================================");
	    System.out.println("// " + getClassName());
	    System.out.println("//====================================================");
	}
	
	private final String CLASS_NAME;
}