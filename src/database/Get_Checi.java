package database;

import model.Checi;


import java.sql.*;
import java.util.Arrays;
import java.util.Date;

public class Get_Checi {
    public void Get_CheCi(Connection conn) {
        try {
            String SQL = "SELECT * FROM checi";
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            ResultSet rs = pstmt.executeQuery();

            // 获取结果
            while (rs.next()) {
                String id =rs.getString("ID");
                String StartStation= rs.getString("StartStation");
                String EndingStation=rs.getString("EndingStation");
                String date=rs.getString("date");
                String StartTime=rs.getString("StartTime");
                String EndingTime=rs.getString("EndingTime");
                String way=rs.getString("Way");
                double Price=rs.getDouble("Price");
                String Level=rs.getString("Level");
                String Location=rs.getString("Location");
                System.out.println("车次号:" +id +" 始发站："+StartStation +" 终点站："+EndingStation
                +" 出发日期："+date);
                System.out.println("出发时间："+StartTime+" 到终点站时间："+EndingTime+
                        " 票价："+Price+" 座位等次："+Level+" 车座位置:"+Location);
                System.out.println("途径站点："+way) ;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Get_CheCi(Connection conn, String checiID, Checi checi) {
        try {
            String SQL = "SELECT * FROM checi WHERE ID=?";
            PreparedStatement stm = conn.prepareStatement(SQL);
            stm.setString(1, checiID);
            ResultSet rs = stm.executeQuery();

            // 确保 rs.next() 被调用，且返回 true，表示有数据行
            if (rs.next()) {
                checi.setStarStation(rs.getString("StartStation"));
                checi.setData(rs.getString("date"));
                checi.setEndingStation(rs.getString("EndingStation"));
                checi.setStarTime(rs.getString("StartTime"));
                checi.setEndTime(rs.getString("EndingTime"));
                checi.setWay(rs.getString("Way"));
                checi.setTicketNumber(rs.getInt("TicketNumber"));
                checi.setPrice(rs.getDouble("Price"));
                checi.setType(rs.getString("Type"));
                checi.setID(rs.getString("ID"));
                checi.setNumber(rs.getInt("Number"));
            } else {
                // 如果查询没有数据，处理相应的逻辑，比如返回默认值或抛出异常
                System.out.println("未找到对应的车次信息！当前checiNumber为");
                System.out.println(checiID);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public boolean Get_CheCi(Connection conn, String startStation, String endStation, Checi[] checis, String selectedType,Date date) {
        boolean hasTickets = false;  // 结果标志，默认为没有票

        try {
            // 查询 SQL 语句，添加了终点站的条件
            String SQL = "SELECT * FROM checi WHERE StartStation=? AND EndingStation=? AND Type=? AND date = ?";
            PreparedStatement stm = conn.prepareStatement(SQL);
            stm.setString(1, startStation);  // 设置始发地
            stm.setString(2, endStation);    // 设置终点站
            stm.setString(3,selectedType);   //设置类型
            stm.setDate(4, new java.sql.Date(date.getTime()));  // 设置日期参数，注意转换为 java.sql.Date

            ResultSet rs = stm.executeQuery(); // 执行查询

            // 初始化计数器，记录数组中有效数据的数量
            int index = 0;

            // 清空数组中的数据（假设传入的数组已经足够大）
            // 在数组中实际不会清空数据，但是我们使用 index 来决定有效数据的位置

            // 遍历查询结果
            while (rs.next()) {
                // 检查当前记录的票数是否大于 0，如果有票，设置标志为 true
                int ticketNumber = rs.getInt("TicketNumber");
                if (ticketNumber > 0) {
                    hasTickets = true;  // 有票，标志为 true
                }

                // 如果数组已满，需要扩容数组
                if (index >= checis.length) {
                    // 扩容数组
                    checis = Arrays.copyOf(checis, checis.length + 10); // 每次增加10个元素
                }

                // 创建一个 Checi 对象并从 ResultSet 中获取数据
                Checi checi = new Checi();
                checi.setID(rs.getString("ID"));
                checi.setStarStation(rs.getString("StartStation"));
                checi.setEndingStation(rs.getString("EndingStation"));
                checi.setStarTime(rs.getString("StartTime"));
                checi.setEndTime(rs.getString("EndingTime"));
                checi.setTicketNumber(rs.getInt("TicketNumber"));
                checi.setData(rs.getString("date"));
                checi.setWay(rs.getString("Way"));
                checi.setPrice(rs.getDouble("Price"));
                checi.setType(rs.getString("Type"));
                checi.setNumber(rs.getInt("Number"));
                // 将 Checi 对象添加到数组中的指定位置
                checis[index] = checi;

                // 增加计数器
                index++;
            }

            // 如果查询的结果数少于传入数组的大小，可以将数组截取到有效数据长度
            if (index < checis.length) {
                checis = Arrays.copyOf(checis, index);
            }

        } catch (SQLException e) {
            // 如果查询失败，则抛出异常
            throw new RuntimeException("检索列车信息时出错", e);
        }

        // 返回查询结果，若有票则返回 true，否则返回 false
        return hasTickets;
    }
    public boolean Get_CheCi(Connection conn, String startStation, String endStation, Checi[] checis, Date date) {
        boolean hasTickets = false;  // 结果标志，默认为没有票

        try {
            // 查询 SQL 语句，添加了终点站的条件
            String SQL = "SELECT * FROM checi WHERE StartStation=? AND EndingStation=? AND date = ?";
            PreparedStatement stm = conn.prepareStatement(SQL);
            stm.setString(1, startStation);  // 设置始发地
            stm.setString(2, endStation);    // 设置终点站
            stm.setDate(3, new java.sql.Date(date.getTime()));  // 设置日期参数，注意转换为 java.sql.Date

            ResultSet rs = stm.executeQuery(); // 执行查询

            // 初始化计数器，记录数组中有效数据的数量
            int index = 0;

            // 清空数组中的数据（假设传入的数组已经足够大）
            // 在数组中实际不会清空数据，但是我们使用 index 来决定有效数据的位置

            // 遍历查询结果
            while (rs.next()) {
                // 检查当前记录的票数是否大于 0，如果有票，设置标志为 true
                int ticketNumber = rs.getInt("TicketNumber");
                if (ticketNumber > 0) {
                    hasTickets = true;  // 有票，标志为 true
                }

                // 如果数组已满，需要扩容数组
                if (index >= checis.length) {
                    // 扩容数组
                    checis = Arrays.copyOf(checis, checis.length + 10); // 每次增加10个元素
                }

                // 创建一个 Checi 对象并从 ResultSet 中获取数据
                Checi checi = new Checi();
                checi.setID(rs.getString("ID"));
                checi.setStarStation(rs.getString("StartStation"));
                checi.setEndingStation(rs.getString("EndingStation"));
                checi.setStarTime(rs.getString("StartTime"));
                checi.setEndTime(rs.getString("EndingTime"));
                checi.setTicketNumber(rs.getInt("TicketNumber"));
                checi.setData(rs.getString("date"));
                checi.setWay(rs.getString("Way"));
                checi.setPrice(rs.getDouble("Price"));
                checi.setType(rs.getString("Type"));
                checi.setNumber(rs.getInt("Number"));

                // 将 Checi 对象添加到数组中的指定位置
                checis[index] = checi;

                // 增加计数器
                index++;
            }

            // 如果查询的结果数少于传入数组的大小，可以将数组截取到有效数据长度
            if (index < checis.length) {
                checis = Arrays.copyOf(checis, index);
            }

        } catch (SQLException e) {
            // 如果查询失败，则抛出异常
            throw new RuntimeException("检索列车信息时出错", e);
        }

        // 返回查询结果，若有票则返回 true，否则返回 false
        return hasTickets;
    }
}




