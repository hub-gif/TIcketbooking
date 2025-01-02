package database;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Delete_user {
    public void Delete_information(Connection conn, int ID) {
        try {
            String SQL = "DELETE FROM information_user WHERE ID =?";
            PreparedStatement state = conn.prepareStatement(SQL);
            state.setInt(1, ID);
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
