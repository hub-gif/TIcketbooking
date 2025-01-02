package database;

import model.Customer;

import java.sql.Connection;
import java.sql.*;

public class Get_User {
    public void GetUserDate(Connection conn) {
        try {
            String SQL = "SELECT * FROM information_user";

                    // 创建PreparedStatement对象
                    PreparedStatement pstmt = conn.prepareStatement(SQL);

                    // 执行查询
                    ResultSet rs = pstmt.executeQuery();

                    // 获取结果
            while (rs.next()) {
                int id = rs.getInt("ID");
                String name = rs.getString("UserName");
                String password = rs.getString("password");
                String PhoneNumber = rs.getString("PhoneNumber");
                System.out.println("ID:" +id+"  name:  "+name+"  PhoneNumber："+PhoneNumber
                +"  PassWord:" +password);
            }
        } catch (Exception e) {
                    e.printStackTrace();
                }
    }
    public boolean checkUserID(Connection conn,int userID){
        String SQL = "SELECT COUNT(*) FROM information_user WHERE ID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(SQL)) {
            // 设置查询中的参数（即 userID）
            stmt.setInt(1, userID);

            // 执行查询
            try (ResultSet rs = stmt.executeQuery()) {
                // 如果查询结果中 COUNT(*) > 0，表示存在该用户
                if (rs.next() && rs.getInt(1) > 0) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();  // 处理异常，打印错误信息
        }
        return false;
    }
    public int checkUser(Connection conn, int userID, String userPassword) {
        String SQL = "SELECT password FROM information_user WHERE ID = ?";

        try (PreparedStatement stmt = conn.prepareStatement(SQL)) {
            // 设置查询中的参数（即 userID）
            stmt.setInt(1, userID);

            // 执行查询
            try (ResultSet rs = stmt.executeQuery()) {
                // 如果查询结果存在，说明该用户存在
                if (rs.next()) {
                    // 获取数据库中的密码
                    String storedPassword = rs.getString("password");

                    // 判断密码是否匹配
                    if (storedPassword.equals(userPassword)) {
                        return 1;  // 用户存在且密码正确
                    } else {
                        return -2; // 密码错误
                    }
                } else {
                    return -1; // 用户不存在
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();  // 处理异常，打印错误信息
        }
        return -1; // 默认返回 -1，表示用户不存在
    }
    public void getUser(Connection conn, int userID, String userPassword, Customer customer){
        String SQL = "SELECT * FROM information_user WHERE ID = ? AND password=?";
        try (PreparedStatement pst = conn.prepareStatement(SQL)) {
            // 设置SQL查询参数
            pst.setInt(1, userID);
            pst.setString(2, userPassword);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    // 假设表中字段与Customer类属性对应
                    customer.setName(rs.getString("UserName"));
                    customer.setPhoneNumber(rs.getString("PhoneNumber"));
                    customer.setPassword(userPassword); // 已经作为参数传入，直接设置
                    customer.setID(userID); // 已经作为参数传入，直接设置
                    customer.setInformation(rs.getString("information"));
                } else {
                    // 没有找到用户，可以抛出异常或者设置一个错误标志
                    System.out.println("No user found with the given ID and password.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // 处理SQL异常，可能需要抛出自定义异常或者设置错误处理逻辑
        }
    }

}






