//designpatterns.abstractfactory.SummerSkinFactory.java
package design_pattern.lab1.abstractfactory;

public class HaierEletricFactory implements EletricFactory {
	@Override
	public TV createTv() {
		return new HaierTV();
	}

	@Override
	public AirCondition createAirCondition() {
		return new HaierAirCondition();
	}
}