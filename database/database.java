package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class database {
    public Connection ConnectDatabase()
    {
        String url = "jdbc:mysql://localhost:3306/RuanGong";
        String user = "root";
        String password = "";
        Connection conn=null;
        try {
            // 加载JDBC驱动
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 创建数据库连接
            conn = DriverManager.getConnection(url, user, password);

            // 创建Statement对象
            Statement stmt = conn.createStatement();

            // 执行SQL查询
            String sql = "SELECT * FROM information_user";
            ResultSet rs = stmt.executeQuery(sql);

            // 处理查询结果
            while (rs.next()) {
                int id = rs.getInt("ID");
                String name = rs.getString("UserName");
                System.out.println("ID: " + id + ", Name: " + name);
            }

            // 关闭连接
            /*rs.close();
            stmt.close();
            conn.close();*/

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
    }

