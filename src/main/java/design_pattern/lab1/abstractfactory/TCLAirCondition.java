//designpatterns.abstractfactory.SummerAirCondition.java
package design_pattern.lab1.abstractfactory;

public class TCLAirCondition implements AirCondition {
	@Override
	public void display() {
		System.out.println("TCL空调生产出来了");
	}	
}