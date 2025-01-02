package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class database {
    public Connection Select() {
        String url = "jdbc:mysql://10.143.49.1:3306/ruangong";
        String username = "homework";
        String password = "123456";
        Connection connection=null;

        try {
            // 建立连接
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database!");

            // 创建语句
            Statement statement = connection.createStatement();

            // 执行查询
            ResultSet resultSet = statement.executeQuery("SELECT * FROM information_user");

            // 处理结果集
            while (resultSet.next()) {
                System.out.println("UserName: " + resultSet.getString("UserName"));
                System.out.println("ID: " + resultSet.getString("ID"));
            }

            // 关闭连接
            //connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
