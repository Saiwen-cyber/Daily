//designpatterns.abstractfactory.Client.java
package design_pattern.lab1.abstractfactory;

public class Client {
	public static void main(String args[]) {
		EletricFactory factory;
		TV tv;
		AirCondition airCondition;
		factory = (EletricFactory) XMLUtil.getBean();
		tv = factory.createTv();
		airCondition = factory.createAirCondition();

		tv.display();
		airCondition.display();
	}
}