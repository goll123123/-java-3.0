package utils;

import java.sql.*;

public class JDBCUtils {
    // 构造方法私有化
    private JDBCUtils(){}
    // 设计三个属性存储: 连接url, 用户名, 密码
    private static String url;
    private static String username;
    private static String password;
    // 在连接数据的时候驱动类只需要注册一次就可以了
    // 要把注册的过程接到静态代码块中
    static {
        try {
            // 注册驱动类
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 给三个静态属性赋值,使用工具类是要记的修改数据库名称
            url = "jdbc:mysql://localhost:3306/food_master?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false";
            username = "root";
            password = "root1234";
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    // 提供一个方法用来生成Connection对象
    public static Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    // 提供一个方法关闭PreparedStatement和Connection对象
    public static void close(Connection con, PreparedStatement ps){
        if (ps != null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    // 提供一个方法关闭ResultSet,PreparedStatment, Connection
    public static void close(ResultSet set, PreparedStatement ps, Connection con){
        if (set != null){
            try {
                set.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        close(con, ps);
    }


}
