package database;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Delete_Ticket {
    public void Delete_information(Connection conn, String Ticket_ID) {
        try {
            String SQL = "DELETE FROM ticket WHERE Ticket_ID =?";
            PreparedStatement state = conn.prepareStatement(SQL);
            state.setString(1, Ticket_ID);
            int answer = state.executeUpdate();
            if (answer > 0) {
                System.out.println("删除成功");

            } else System.out.println("删除失败");


        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

}
