package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Insert_CheCi {
    //该方法用于创造一个车次，由于该方法中车次数量较多，因此该方法只创造一个带有编号的车次，具体的信息更新放到了更新中
    public void CreateCheCi(Connection conn,String ID){
        Statement stam;
        try {
            stam= conn.createStatement();
            String SQL="INSERT INTO checi(ID) VALUES(?)";
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1,ID);
            int rowsAffected = pstmt.executeUpdate();
            if(rowsAffected>0)
                System.out.println("插入成功");
            else System.out.println("插入失败");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }



}
