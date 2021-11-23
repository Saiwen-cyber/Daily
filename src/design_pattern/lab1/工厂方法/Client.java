package design_pattern.lab1.工厂方法;

/**
 * @author fang
 */
public class Client {
    public static void main(String[] args) {
        Person person;
        PersonFactory factory;
        factory = (PersonFactory) XMLUtil.getBean();
        person = factory.createPerson();
        person.display();
    }
}
