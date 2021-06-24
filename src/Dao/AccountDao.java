package Dao;
//
import entity.User;
import myException.LoginException;
import myException.RegisterException;
import utils.JDBCUtils;
import utils.Staus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDao {


    public AccountDao() {
    }


public  User selectById(String id) throws SQLException {
    User user = null;
    // 获取连接
    Connection con =  JDBCUtils.getConnection();
    // 书写sql语句
    String sql = "select * from account where id = ?";
    // 获取执行对象
    PreparedStatement ps = con.prepareStatement(sql);
    // 绑定数据
    ps.setString(1, id);
    // 执行sql语句
    ResultSet set = ps.executeQuery();
    // ResultSet方法介绍
    // next()  用来判断set对象是否有数据
    // 判断set集合是由有下一个行的信息,取出信息之后,next会让指针往下一行偏移
    while (set.next()) {
        // 根据字段名取出字段下的信息
        String id_ = set.getString("id");
        String name_ = set.getString("name");
        String password_ = set.getString("password");
        user = new User(id_,name_,password_);
    }
    // 关闭资源
    JDBCUtils.close(con, ps);
    if (user == null){
        throw new LoginException(Staus.Login_Lost.getMessage());
    }else {
        return user;
    }
}

    public  void insert(User user) throws SQLException {
        String id = user.getId();
        String password = user.getPassword();
        String name = user.getName();
        // 获取连接
        Connection con =  JDBCUtils.getConnection();
        // 书写sql语句
        String sql = "INSERT INTO account VALUES (?,?,?)";
        // 获取执行对象
        PreparedStatement ps = con.prepareStatement(sql);
        // 绑定数据
        ps.setString(1, id);
        ps.setString(2,password);
        ps.setString(3,name);
        // 执行sql语句
        int row = ps.executeUpdate();
        // 关闭资源
        JDBCUtils.close(con, ps);
       if (row == 0){
        throw new RegisterException(Staus.Account_Exist.getMessage());
       }
    }

}
