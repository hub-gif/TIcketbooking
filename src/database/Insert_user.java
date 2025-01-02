package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class Insert_user {
    /*该方法用于插入数据，根据你的需要来插入数据

     */
    public void CreateNewUser(Connection conn,String Username,String Password,
                              String Phonenumber,int UserID){
        Statement stam;
        try {
          stam= conn.createStatement();
          String SQL="INSERT INTO information_user(ID,password,UserName,PhoneNumber) VALUES(?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1,UserID);
            pstmt.setString(2,Password);
            pstmt.setString(3,Username);
            pstmt.setString(4,Phonenumber);
            int rowsAffected = pstmt.executeUpdate();
            if(rowsAffected>0)
                System.out.println("插入成功");
            else System.out.println("插入失败");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
