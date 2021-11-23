//designpatterns.abstractfactory.SpringAirCondition.java
package design_pattern.lab1.abstractfactory;

/**
 * @author ASUS
 */
public class HaierAirCondition implements AirCondition {
	@Override
	public void display() {
		System.out.println("海尔空调生产出来了");
	}
}
