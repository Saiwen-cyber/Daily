package design_pattern.lab1.工厂方法;

/**
 * @author fang
 */
public class WomanFactory implements PersonFactory{
    @Override
    public Person createPerson() {
        return new Woman();
    }
}