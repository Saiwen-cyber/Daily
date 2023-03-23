package soa_lab4.cs_3.UI;

import soa_lab4.cs_2.DateBase;
import soa_lab4.cs_2.MainView;

import javax.swing.*;
import java.sql.SQLException;

public class Main {
    public static void main(String args[]) throws SQLException {

        new DateBase();
        if(DateBase.getCon()==null){
            JOptionPane.showMessageDialog(null,"数据库连接失败！请检查是否启动数据库！",
                    "提示", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }//测试并连接数据库；
        new MainView().setVisible(true);

    }
}
