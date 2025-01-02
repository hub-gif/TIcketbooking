package database;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Update_Ticket {
    public void Update_database(Connection conn, String Ticket_ID,String Rail_ID, String Way, String EnddingStation,
                                String StartStation,String Location,String Level,double Price) {

        /*该方法用于更新已有的数据*/
        try {
            String SQL = "UPDATE ticket SET Price=?,StartStation = ?,EnddingStation = ?, " +
                    "Level=?,Ticket_ID=?,Way=?,Location=? WHERE Rail_ID=?";
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setDouble(1, Price);
            pstmt.setString(2, StartStation);
            pstmt.setString(3,EnddingStation);
            pstmt.setString(4,Level);
            pstmt.setString(5,Ticket_ID);
            pstmt.setString(6,Way);
            pstmt.setString(7,Location);
            pstmt.setString(8,Rail_ID);




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
