package design_pattern.lab1.简单工厂;



/**
 * @author fang
 */
public class PersonFactory {
    public static Person getPerson(String type){
        Person person = null;
        if("M".equals(type)){
            person = new Man();
        }else if("W".equals(type)){
            person = new Woman();
        }else if("R".equals(type)){
            person = new Robot();
        }
        return person;
    }
}
