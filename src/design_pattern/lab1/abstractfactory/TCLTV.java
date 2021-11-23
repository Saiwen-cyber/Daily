//designpatterns.abstractfactory.TCLTV.java
package design_pattern.lab1.abstractfactory;

/**
 * @author ASUS
 */
public class TCLTV implements TV {
	@Override
	public void display() {
		System.out.println("TCL电视生产出来了");
	}	
}
