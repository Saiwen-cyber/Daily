package soa_lab4.cs_3.BLL;

import soa_lab4.cs_3.DAL.DateBase;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * @author ASUS
 */
public class LinkedManBLL {
    private static DateBase dateBase;

    public LinkedManBLL() {
        dateBase = new DateBase();
    }

    public static void add(LinkedMan man){
        dateBase.addLinkedMan(man);
    }
    public static void delete(int id){
        dateBase.deleteLinkedMan(id);
    }
    public static void update(LinkedMan man){
        dateBase.deleteLinkedMan(man.getID());
        dateBase.addLinkedMan(man);
    }
    public static List<LinkedMan> query() throws SQLException {
        return dateBase.queryLinkedMan();
    }

}
