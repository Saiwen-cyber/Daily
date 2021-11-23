//designpatterns.abstractfactory.SpringSkinFactory.java
package design_pattern.lab1.abstractfactory;

public class TCLEletricFactory implements EletricFactory {
	@Override
	public TV createTv() {
		return new TCLTV();
	}

	@Override
	public AirCondition createAirCondition() {
		return new TCLAirCondition();
	}
}