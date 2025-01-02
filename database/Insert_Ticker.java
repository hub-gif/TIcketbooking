package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Insert_Ticker {
    public void CreateCheCi(Connection conn, String ID){
        Statement stam;
        /*该方法是根据车次的ID创建的票的ID，我们只需要根据车次ID创建票所对应的铁路ID即可
        *
        *
        * */
        try {
            stam= conn.createStatement();
            String SQL="INSERT INTO ticket(Rail_ID) VALUES(?)";
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
