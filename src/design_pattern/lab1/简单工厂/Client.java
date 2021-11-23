package design_pattern.lab1.简单工厂;

/**
 * @author fang
 */
public class Client {
    public static void main(String[] args) {
        Person person;
        String type = XMLUtil.getPersonType();
        person = PersonFactory.getPerson(type);
        person.display();
    }
}
