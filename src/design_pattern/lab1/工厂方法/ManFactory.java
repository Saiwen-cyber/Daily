package design_pattern.lab1.工厂方法;

/**
 * @author fang
 */
public class ManFactory implements PersonFactory{
    @Override
    public Person createPerson() {
        return new Man();
    }
}
