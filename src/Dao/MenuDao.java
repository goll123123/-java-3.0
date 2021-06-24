package Dao;

import entity.Dish;
import entity.DishDetail;
import myException.*;
import utils.JDBCUtils;
import utils.Staus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MenuDao {

    public MenuDao() {
    }



//    public void insert(Dish dish){
//        menu.totalMenu.put(dish.getId(),dish);
//    }

    public void insert(Dish dish) throws Exception{
        String id = dish.getId();
        String name = dish.getDishDetail().getName();
        String price  = dish.getDishDetail().getPrice();
        int price_;
        try {
            price_ = Integer.parseInt(price);
        }catch (Exception e){
            throw new ChangeException(Staus.Change_To.getMessage());
        }
        // 获取连接
        Connection con =  JDBCUtils.getConnection();
        // 书写sql语句
        String sql = "INSERT INTO menu VALUES (?,?,?,0,0,0)";
        // 获取执行对象
        PreparedStatement ps = con.prepareStatement(sql);
        // 绑定数据
        ps.setString(1, id);
        ps.setString(2,name);
        ps.setInt(3,price_);
        // 执行sql语句
        try {
            int row = ps.executeUpdate();
        }catch (Exception e){
            throw new InsertException(Staus.Dish_Exist.getMessage());
        }
        // 关闭资源
        JDBCUtils.close(con, ps);

    }

//    public Dish selectByDishId(String id){
//     Dish dish =   menu.totalMenu.get(id);
//        if (dish == null){
//            throw new SelectDishException(Staus.No_DishId.getMessage());
//        }else {
//            return dish;
//        }
//    }

    public Dish selectByDishId(String id) throws Exception{
        Dish dish = null;
        // 获取连接
        Connection con =  JDBCUtils.getConnection();
        // 书写sql语句
        String sql = "select * from menu where id = ?";
        // 获取执行对象
        PreparedStatement ps = con.prepareStatement(sql);
        // 绑定数据
        ps.setString(1, id);
        // 执行sql语句
        ResultSet set = ps.executeQuery();
        if (set.isBeforeFirst() == false){
           throw new SelectDishException(Staus.No_DishId.getMessage());
        }
        while (set.next()) {
            // 根据字段名取出字段下的信息
            String id_ = set.getString("id");
            String name_ = set.getString("name");
            String price_ = set.getString("price");
            DishDetail dishDetail = new DishDetail(name_,price_);
             dish = new Dish(id_,dishDetail);
        }
        // 关闭资源
        JDBCUtils.close(con, ps);
        return dish;
    }

//    public ArrayList<Dish> queryAll(){
//        ArrayList<Dish> arrayList = new ArrayList<>();
//        if (menu.totalMenu == null){
//            return arrayList;
//        }
//
//        for (Dish dish:menu.totalMenu.values()){
//            arrayList.add(dish);
//        }
//        return arrayList;
//    }

    public ArrayList<Dish> queryAll() throws Exception{
        ArrayList<Dish> dishList = new ArrayList<>();
        // 获取连接
        Connection con =  JDBCUtils.getConnection();
        // 书写sql语句
        String sql = "select * from menu";
        // 获取执行对象
        PreparedStatement ps = con.prepareStatement(sql);
        // 执行sql语句
        ResultSet set = ps.executeQuery();
//        if (set == null){
//            throw new SelectDishException(Staus.No_DishId.getMessage());
//        }
        while (set.next()) {
            // 根据字段名取出字段下的信息
            String id_ = set.getString("id");
            String name_ = set.getString("name");
            String price_ = set.getString("price");
            int morning = set.getInt("morning");
            int afternoon = set.getInt("afternoon");
            int evening = set.getInt("evening");
            DishDetail dishDetail = new DishDetail(name_,price_);
            Dish dish = new Dish(id_,morning,afternoon,evening,dishDetail);
            dishList.add(dish);
        }
        // 关闭资源
        JDBCUtils.close(con, ps);
        return dishList;
    }
//
//    public Dish deleteDishById(String id){
//      return   menu.totalMenu.remove(id);
//    }


    public void deleteDishById(String id)throws Exception{
        // 获取连接
        Connection con =  JDBCUtils.getConnection();
        // 书写sql语句
        String sql = "DELETE FROM menu WHERE id = ?";
        // 获取执行对象
        PreparedStatement ps = con.prepareStatement(sql);
        // 绑定数据
        ps.setString(1, id);
        // 执行sql语句

            int row = ps.executeUpdate();

        // 关闭资源
        JDBCUtils.close(con, ps);
    }


    public int setMorning(String id)throws Exception{

        // 获取连接
        Connection con =  JDBCUtils.getConnection();
        // 书写sql语句
        String sql = "UPDATE menu SET morning = 1 WHERE id = ?";
        // 获取执行对象
        PreparedStatement ps = con.prepareStatement(sql);
        // 绑定数据
        ps.setString(1, id);
        // 执行sql语句

            int row = ps.executeUpdate();

        // 关闭资源
        JDBCUtils.close(con, ps);
     return row;
    }

    public int setAfternoon(String id)throws Exception{

        // 获取连接
        Connection con =  JDBCUtils.getConnection();
        // 书写sql语句
        String sql = "UPDATE menu SET afternoon = 1 WHERE id = ?";
        // 获取执行对象
        PreparedStatement ps = con.prepareStatement(sql);
        // 绑定数据
        ps.setString(1, id);
        // 执行sql语句

        int row = ps.executeUpdate();

        // 关闭资源
        JDBCUtils.close(con, ps);
        return row;
    }


    public int setEvening(String id)throws Exception{

        // 获取连接
        Connection con =  JDBCUtils.getConnection();
        // 书写sql语句
        String sql = "UPDATE menu SET evening = 1 WHERE id = ?";
        // 获取执行对象
        PreparedStatement ps = con.prepareStatement(sql);
        // 绑定数据
        ps.setString(1, id);
        // 执行sql语句

        int row = ps.executeUpdate();

        // 关闭资源
        JDBCUtils.close(con, ps);
        return row;
    }

}
