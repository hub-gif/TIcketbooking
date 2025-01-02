package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
public class Update_User {
    public void Update_database(Connection conn,int ID,String UserName,String password,String PhoneNumber) {

        /*该方法用于更新已有的数据，可根据你输入的用户的ID来更新用户的相关信息
        */
            try {

                String SQL = "UPDATE information_user SET UserName = ?,password = ? ,PhoneNumber = ?" +
                        "  WHERE ID = ?";
                PreparedStatement pstmt = conn.prepareStatement(SQL);
                pstmt.setString(1, UserName);
                pstmt.setString(2, password);
                pstmt.setString(3,PhoneNumber);
                pstmt.setInt(4,ID);

                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("更新成功!");
                } else {
                    System.out.println("没有找到要更新的元组，更新失败!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


}
