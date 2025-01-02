package database;

import com.mysql.cj.x.protobuf.MysqlxNotice;
import model.Customer;
import model.Ticket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Get_Ticket {
    public void Get_Ticket(Connection conn) {
        try {
            String SQL = "SELECT * FROM ticket";
            PreparedStatement pst = conn.prepareStatement(SQL);
            ResultSet rs = pst.executeQuery();

            // 获取结果
            while (rs.next()) {
              String Level=rs.getString("Level");
              double Price=rs.getDouble("Price");
              String Rail_ID=rs.getString("Rail_ID");
              String Location=rs.getString("Location");
              String StartStation=rs.getString("StartStation");
              String EnddingStation=rs.getString("EnddingStation");
              String Way=rs.getString("Way");
              String Ticket_ID=rs.getString("Ticket_ID");
                System.out.println("车次号:" +Rail_ID +" 始发站："+StartStation +" 终点站："+EnddingStation
                        +" 座位："+Location);
                System.out.println("车座等级："+Level+" 价格："+Price+" 车票编号："+Ticket_ID);
                System.out.println("途径站点："+Way);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void Get_Tickets(Connection conn, Ticket[] tickets, int checiNumber) {
        try {
            String SQL = "SELECT * FROM ticket WHERE Checi_Number=?";
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, String.valueOf(checiNumber)); // 设置SQL查询的参数

            ResultSet rs = pstmt.executeQuery(); // 执行查询

            int index = 0; // 用于追踪Ticket数组中的索引
            while (rs.next()) { // 如果查询结果不为空
                // 从ResultSet中提取数据并设置到Ticket对象中
                Ticket ticket = new Ticket();
                ticket.setLevel(rs.getString("Level"));
                ticket.setPrice(rs.getDouble("Price"));
                ticket.setCheci_ID(rs.getString("Checi_ID"));
                ticket.setLocation(rs.getString("Location"));
                ticket.setStartStation(rs.getString("StartStation"));
                ticket.setEndingStation(rs.getString("EndingStation"));
                ticket.setWay(rs.getString("Way"));
                ticket.setTickId(rs.getString("Ticket_ID"));
                ticket.setCustomer_ID(rs.getInt("Customer_ID"));
                ticket.setNumber(rs.getInt("Number"));

                // 将填充好的Ticket对象添加到数组中
                tickets[index++] = ticket;
            }

            if (index == 0) {
                // 如果数组索引没有变化，说明没有找到票
                System.out.println("No tickets found for the given Checi_ID: " + checiNumber);
            }
        } catch (SQLException e) {
            // 处理SQL异常，例如打印日志或重新抛出异常
            throw new RuntimeException("Database access error in Get_Tickets method", e);
        }
    }
    public void Get_Ticket(Connection conn, int customer_ID, Ticket[] tickets) {
        // 初始化一个列表来存储查询结果，以便我们可以动态地添加票
        List<Ticket> ticketList = new ArrayList<>();

        try {
            String SQL = "SELECT * FROM ticket WHERE Customer_ID=?";
            PreparedStatement pst = conn.prepareStatement(SQL);
            pst.setInt(1, customer_ID); // 设置SQL查询的参数

            ResultSet rs = pst.executeQuery(); // 执行查询

            while (rs.next()) { // 如果查询结果不为空
                // 从ResultSet中提取数据并设置到Ticket对象中
                Ticket ticket = new Ticket();
                ticket.setLevel(rs.getString("Level"));
                ticket.setPrice(rs.getDouble("Price"));
                ticket.setCheci_ID(rs.getString("Checi_ID"));
                ticket.setLocation(rs.getString("Location"));
                ticket.setStartStation(rs.getString("StartStation"));
                ticket.setEndingStation(rs.getString("EndingStation"));
                ticket.setWay(rs.getString("Way"));
                ticket.setTickId(rs.getString("Ticket_ID"));
                ticket.setCustomer_ID(rs.getInt("Customer_ID"));
                ticket.setNumber(rs.getInt("Number"));

                // 将填充好的Ticket对象添加到列表中
                ticketList.add(ticket);
            }

            // 将列表转换为数组并赋值给tickets参数
            tickets = ticketList.toArray(new Ticket[0]);

            if (ticketList.isEmpty()) {
                // 如果列表为空，说明没有找到票
                System.out.println("No tickets found for the given Customer_ID: " + customer_ID);
            }
        } catch (SQLException e) {
            // 处理SQL异常，例如打印日志或重新抛出异常
            throw new RuntimeException("Database access error in Get_Ticket method", e);
        }
    }

    public double getTypePrice(Connection conn,int level,String checiID){
        double price = 0;
        boolean hasTicker=false;
        String seatType = null;
        if(level==0){
            seatType="商务座";
        } else if (level==1) {
            seatType="一等座";
        } else if (level==2) {
            seatType="二等座";
        }

        String SQL = "SELECT * FROM ticket WHERE Checi_ID=? AND Level=?";
        try {
            PreparedStatement stm = conn.prepareStatement(SQL);
            stm.setString(1, checiID);
            stm.setString(2, seatType);
            ResultSet rs = stm.executeQuery(); // 执行查询
            while(rs.next()){
                price=rs.getDouble("Price");
                hasTicker=true;
            }
            if(hasTicker==false){
                price=0;
            }
        } catch (Exception e) {
            throw new RuntimeException("获取票价时时出错", e);
        }
        return price;
    }
    // 获取某种类型的票的数量
    public int getTypeTicket(Connection conn, int ticketType,String checiID) {
        int answer = 0;
        String seatType = null;
        // 根据ticketType确定座位类型
        if (ticketType == 0) {
            seatType = "商务座";
        } else if (ticketType == 1) {
            seatType = "一等座";
        } else if (ticketType == 2) {
            seatType = "二等座";
        }

        // 这里的SQL语句是查询ticket表中符合条件的记录数
        String SQL = "SELECT COUNT(*) FROM ticket WHERE Level = ?AND Checi_ID =?";

        // 执行SQL查询
        try (PreparedStatement stm = conn.prepareStatement(SQL)) {
            // 设置查询条件
            stm.setString(1, seatType);
            stm.setString(2, checiID);

            // 执行查询
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                // 获取查询结果，返回该座位类型的票的数量
                answer = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // 可以选择抛出一个运行时异常或其他方式来处理错误
            throw new RuntimeException("获取某种类型的票的数量失败", e);
        }

        return answer;
    }
    //买票
    public void BookingTicket(Connection conn, Customer customer, int ticketNumber)  {
        // SQL 更新语句
        String updateTicketSql = "UPDATE ticket SET Customer_ID = ? WHERE Number = ?";
        String updateCheciSql = "UPDATE checi SET TicketNumber = TicketNumber - 1 WHERE ID = (SELECT Checi_ID FROM ticket WHERE Number = ?)";
            // 更新 ticket 表
            try (PreparedStatement pstmtTicket = conn.prepareStatement(updateTicketSql)) {
                pstmtTicket.setInt(1, customer.getID());  // 设置 Customer_ID
                pstmtTicket.setInt(2, ticketNumber);  // 设置 ticketNumber
                int rowsAffectedTicket = pstmtTicket.executeUpdate();

                if (rowsAffectedTicket == 0) {
                    throw new RuntimeException("未找到对应的票，更新 ticket 表失败");
                }
            } catch (SQLException e) {
                throw new RuntimeException("未找到对应的票，更新 ticket 表失败");
            }

        // 更新 checi 表
            try (PreparedStatement pstmtCheci = conn.prepareStatement(updateCheciSql)) {
                pstmtCheci.setInt(1, ticketNumber);  // 设置 ticketNumber，查询对应的 Checi_ID
                int rowsAffectedCheci = pstmtCheci.executeUpdate();

                if (rowsAffectedCheci == 0) {
                    throw new RuntimeException("未找到对应的 Checi_ID，更新 checi 表失败");
                }
            } catch (SQLException e) {
                throw new RuntimeException("未找到对应的 Checi_ID，更新 checi 表失败");
            }


    }

    //找到要买的票
    public int searchTicket(Connection conn, int ticketType, String checiID)  {
        String seatType = null;

        // 根据 ticketType 确定座位类型
        switch (ticketType) {
            case 0:
                seatType = "商务座";
                break;
            case 1:
                seatType = "一等座";
                break;
            case 2:
                seatType = "二等座";
                break;
            default:
                throw new IllegalArgumentException("Invalid ticket type");
        }

        String SQL = "SELECT Number FROM ticket WHERE Level = ? AND Checi_ID = ?";

        // 使用 try-with-resources 自动关闭资源
        try (PreparedStatement stm = conn.prepareStatement(SQL)) {
            stm.setString(1, seatType);
            stm.setString(2, checiID);

            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("Number");  // 获取票号
                } else {
                    throw new SQLException("No ticket found for the given Checi_ID and seat type.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("未找到对应要买的票");
        }
    }

}
