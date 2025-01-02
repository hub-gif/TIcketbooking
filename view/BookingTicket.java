package view;

import model.Customer;
import model.MySystem;

import javax.swing.*;
import java.awt.*;

/**
 * 自定义的单元格编辑器，用于在 JTable 中显示按钮并处理点击事件。
 * 该按钮允许用户点击并执行特定操作（如输入乘客信息并进行查询）。
 */
class BookingTicket extends DefaultCellEditor {
    JButton button; // 按钮组件
    String label; // 按钮显示的文本
    boolean isPushed; // 标志按钮是否被点击
    JTable table; // 当前 JTable 对象
    int row; // 被点击按钮所在的行
    MySystem system;
    Customer customer;
    String checiID;
    int checiNumber;

    /**
     * 构造函数，传入一个 JCheckBox 作为默认编辑器的构造器
     * @param checkBox 默认使用的 JCheckBox，作为按钮的编辑器
     */
    public BookingTicket(JCheckBox checkBox, MySystem system, Customer customer, String checiID) {
        super(checkBox); // 调用父类构造器设置默认的编辑器为 JCheckBox
        this.system=system;
        this.customer=customer;
        this.checiID=checiID;
        button = new JButton(); // 创建按钮实例
        button.setOpaque(true); // 设置按钮背景色为不透明
        button.addActionListener(e -> fireEditingStopped()); // 为按钮添加点击事件，点击后停止编辑
    }

    /**
     * 返回表格单元格的编辑组件（即按钮），并根据当前行和列的数据更新按钮的显示文本。
     * @param table 当前 JTable
     * @param value 当前单元格的值
     * @param isSelected 当前单元格是否被选中
     * @param row 当前单元格所在的行
     * @param column 当前单元格所在的列
     * @return 返回按钮组件
     */
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.table = table; // 记录当前的 JTable
        this.row = row; // 记录当前的行号
        label = "订票"; // 设置按钮文本为“订票”
        button.setText(label); // 设置按钮显示文本
        isPushed = true; // 标记按钮被点击
        return button; // 返回按钮作为编辑组件
    }

    /**
     * 返回单元格的编辑结果。在此方法中，按钮点击后会弹出多个输入框，收集乘客信息。
     * 然后触发查询操作。
     * @return 返回按钮的文本（“订票”）
     */
    @Override
    public Object getCellEditorValue() {
        if (isPushed) { // 判断按钮是否被点击
            checiID = (String) table.getValueAt(row, 0);  // 获取当前行的车次编号
            // 弹出订票界面
            Booking booking = new Booking((JFrame) SwingUtilities.getWindowAncestor(table), system, customer, checiID);
            booking.setVisible(true);
        }
        isPushed = false; // 按钮点击后标记为未点击
        return label; // 返回按钮的文本（“订票”）
    }
}
