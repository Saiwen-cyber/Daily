package soa_lab4.cs_3.DAL;

import soa_lab4.cs_3.BLL.LinkedMan;
import soa_lab4.cs_3.UI.MainView;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DateBase {
        private final String conStr ="jdbc:mysql://localhost:3306/linkman?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=UTF-8&useSSL=false";
        private final String userName = "root";
        private final String userPassword ="123456";
        private static Connection con;
        private static ResultSet rs;

        public  static Connection getCon() {
//            Connection con = this.con;
            return con;
        }

        public DateBase()  {
            try {
                //加载JDBC_MySQL驱动
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection(conStr, userName, userPassword);
                System.out.println(con);
                System.out.println("[数据库连接成功]");


            } catch (Exception sqlE) {
                sqlE.printStackTrace();
            }

        }

        //信息增加
    public  Boolean addLinkedMan(LinkedMan man){
            try {
                if(con.isClosed())
                {
                    //测试连接是否中断，若连接中断，重新连接。
                    con=DateBase.getCon();
                }

                String sqlUtr="INSERT INTO linked_man (id,name,address,phone_number)  VALUES("+man.getID()+","+man.getName()+","+man.getAddress()+","+man.getPhoneNumber()+")";
                Statement Sql=con.createStatement();
                int ok=Sql.executeUpdate(sqlUtr);
            }catch (Exception e){
                return false;
            }
            return true;
        }

     public boolean deleteLinkedMan(int ID){
            String sql="DELETE FROM linked_man WHERE ID=?";
            try {
                PreparedStatement ps=con.prepareStatement(sql);
                ps.setInt(1,ID);
                int rs=ps.executeUpdate();
                ps.close();
                return rs !=0;
            } catch (SQLException e){
                e.printStackTrace();
            }
            return false;
        }



    public List<LinkedMan> queryLinkedMan() throws SQLException {
        if(con.isClosed()){
            //测试连接是否中断，若连接中断，重新连接。
            con=DateBase.getCon();
        }
        ArrayList<LinkedMan> list=new ArrayList<LinkedMan>();
        String sql = "SELECT * FROM linked_man";

        Statement statement = con.createStatement();
        ResultSet rs ;
        rs = statement.executeQuery(sql);
        int i=0;
        while (rs.next()) {
            LinkedMan man = new LinkedMan();
            
            man.setID(rs.getInt("id"));
            man.setName(rs.getString("name"));
            man.setAddress(rs.getString("address"));
            man.setPhoneNumber(rs.getString("phone_number"));
            list.add(man);

        }
        statement.close();
        return list;
    }
}







