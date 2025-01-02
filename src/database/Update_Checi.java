package database;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Update_Checi {
    public void Update_database(Connection conn, String ID,String StartStation,String EnddingStation,
                                String Date,String StartTime,String EnddingTime,String Way,
                                double price,String Level,String Location) {

        /*该方法用于更新已有的数据*/
        try {
            String SQL = "UPDATE checi SET Price=?,StartStation = ?,EndingStation = ? ,date = ? ," +
                    "StartTime= ?,EndingTime=?,Way=?,Level=?,Location=?" +
                    "  WHERE ID = ?";
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setDouble(1, price);
            pstmt.setString(2, StartStation);
            pstmt.setString(3,EnddingStation);
            pstmt.setString(4,Date);
            pstmt.setString(5,StartTime);
            pstmt.setString(6,EnddingTime);
            pstmt.setString(7,Way);
            pstmt.setString(8,Level);
            pstmt.setString(9,Location);
            pstmt.setString(10,ID);



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
