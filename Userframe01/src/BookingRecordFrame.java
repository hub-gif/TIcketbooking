

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class BookingRecordFrame extends JFrame {
    private JTable bookingTable;
    private DefaultTableModel tableModel;

    public BookingRecordFrame() {
        setTitle("我的订票记录");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 关闭窗口时只需释放资源，不退出程序
        setLocationRelativeTo(null);

        // 创建表格模型
        String[] columnNames = {"订单状态", "航班号", "出发地", "目的地", "起飞时间", "乘客姓名", "身份证号", "联系电话", "票价", "支付操作"};
        tableModel = new DefaultTableModel(columnNames, 0);

        // 创建表格
        bookingTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(bookingTable);

        // 添加一些测试数据
        updateBookingTable();

        // 设置表格的一些属性
        bookingTable.setRowHeight(25);
        bookingTable.getTableHeader().setReorderingAllowed(false);

        add(scrollPane);

        // 添加支付操作按钮的事件监听器
        bookingTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTable table = (JTable) evt.getSource();
                int row = table.rowAtPoint(evt.getPoint());
                int column = table.columnAtPoint(evt.getPoint());
                if (column == table.getColumn("支付操作").getModelIndex()) {
                    if (row >= 0) {
                        String flightNumber = (String) table.getValueAt(row, 1);
                        updateBookingTable();
                    }
                }
            }
        });
    }

    private void updateBookingTable() {
        // 这里应该添加代码来从数据源获取订单数据

    }
}