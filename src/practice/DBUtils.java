package practice;

import java.sql.*;

/**
 * @author fang
 */
public class DBUtils {
    private static Connection conn;
    private static Statement statement;
    private static Connection getCon() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/ascii?useSSL=false";
            String username = "root";
            String password = "123456";
             conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return conn;
    }

    /**
     * 根据ascii表得到拿到字符对应十进制编码
     * @param ch
     * @return
     */
    public static int selectAscii(char ch){
        String sql = "select ASCII('"+ch+"')";
        int num = 0;

        try {
            conn = DBUtils.getCon();
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                num = rs.getInt(1);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return num;
    }

}
