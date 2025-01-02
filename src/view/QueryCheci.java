package view;
import database.Get_Checi;
import database.Get_Ticket;
import model.Checi;
import model.Customer;
import model.MySystem;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import com.toedter.calendar.JCalendar;
import model.Ticket;

public class QueryCheci extends JFrame { // 定义QueryCheci类，继承自JFrame，用于创建GUI窗口
    public JTable CheciTable; // 定义JTable对象，用于显示车次信息
    public DefaultTableModel tableModel; // 定义DefaultTableModel对象，用于管理表格数据
    public JTextField dateField; // 定义日期文本框
    public boolean isDateSelected = false; // 标记用户是否选择了日期
    MySystem system; // 定义System对象，用于访问系统功能
    Checi[] checis;
    Customer customer;
    Date date;      //当前日期
    Date selectedDate;  //用户选择的日期

    // 定义下拉框
    JComboBox<String> transportTypeComboBox;

    public QueryCheci(MySystem system, Customer customer) { // 构造函数，接收一个System对象
        this.system = system;
        this.customer=customer;
        setTitle("订票系统 - 票务查询"); // 设置窗口标题
        setSize(1000, 800); // 设置窗口大小
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置默认关闭操作
        setLocationRelativeTo(null); // 将窗口居中显示

        // 定义一个足够大的数组，假设查询结果不会太多
        checis = new Checi[100];

        // 创建工具栏
        JPanel toolBar = new JPanel();
        toolBar.setLayout(new GridBagLayout()); // 设置工具栏布局为GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints(); // 创建布局约束对象
        gbc.insets = new Insets(5, 5, 5, 5); // 设置组件间的间距

        JTextField starStationField = new JTextField(20); // 创建出发地文本框
        JTextField endStationField = new JTextField(20); // 创建目的地文本框
        JButton searchButton = new JButton("查询"); // 创建查询按钮
        JButton viewBookingsButton = new JButton("查看订票记录"); // 创建查看订票记录按钮

        // 创建日期选择器
        JButton dateButton = new JButton("选择日期");
        dateField = new JTextField(20);
        dateField.setEditable(false); // 设置文本框不可编辑
        dateField.setText(getTodayDateString()); // 设置默认值为系统当前日期

        // 创建下拉框并添加选项
        transportTypeComboBox = new JComboBox<>(new String[]{"筛选","火车", "高铁", "飞机", "轮渡", "汽车"});
        transportTypeComboBox.setSelectedIndex(0); // 默认没有选中任何选项

        // 添加出发地标签和文本框到工具栏
        gbc.gridx = 0;
        gbc.gridy = 0;
        toolBar.add(new JLabel("始发地："), gbc);
        gbc.gridx = 1;
        toolBar.add(starStationField, gbc);

        // 添加目的地标签和文本框到工具栏
        gbc.gridx = 2;
        gbc.gridy = 0;
        toolBar.add(new JLabel("目的地："), gbc);
        gbc.gridx = 3;
        toolBar.add(endStationField, gbc);

        // 添加日期选择器和查询按钮到工具栏
        gbc.gridx = 0;
        gbc.gridy = 2;
        toolBar.add(dateButton, gbc);
        gbc.gridx = 1;
        toolBar.add(dateField, gbc);
        gbc.gridx = 2;
        toolBar.add(searchButton, gbc);

        // 添加查询按钮到工具栏
        gbc.gridx = 2;
        gbc.gridy = 2;
        toolBar.add(searchButton, gbc);

        // 添加下拉框到工具栏
        gbc.gridx = 4;
        toolBar.add(transportTypeComboBox, gbc);

        // 添加查看订票记录按钮到工具栏
        gbc.gridx = 3;
        gbc.gridy = 4;
        toolBar.add(viewBookingsButton, gbc);

        // 添加“我的订票”按钮到工具栏
        gbc.gridx = 5;
        gbc.gridy = 4;
        JButton myTicketsButton = new JButton("我的订票");
        toolBar.add(myTicketsButton, gbc);

        // 创建表格
        String[] columnNames = {"交通编号", "出发地", "目的地","日期", "出发时间", "到达时间", "票价", "剩余座位", "操作"};
        tableModel = new DefaultTableModel(columnNames, 0); // 初始化表格模型
        CheciTable = new JTable(tableModel); // 初始化表格
        JScrollPane scrollPane = new JScrollPane(CheciTable); // 创建滚动面板，包含表格

// 添加订票按钮到表格
        String checiID = null;
        CheciTable.getColumn("操作").setCellRenderer(new BookingButtonRender()); // 设置操作列的渲染器
        CheciTable.getColumn("操作").setCellEditor( new BookingTicket(new JCheckBox(),system,customer,checiID)); // 设置操作列的编辑器
        // 布局
        setLayout(new BorderLayout()); // 设置窗口布局为BorderLayout
        add(toolBar, BorderLayout.NORTH); // 将工具栏添加到窗口的北边
        add(scrollPane, BorderLayout.CENTER); // 将滚动面板添加到窗口的中心

        // 尝试解析当前文本框中的日期
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            // 尝试解析当前文本框中的日期
            date = dateFormat.parse(dateField.getText());
        } catch (Exception e) {
            // 如果解析失败，默认为当前日期
            date = new Date();
        }
        selectedDate=date;  //初始默认为当前日期

        // 添加事件监听器
        dateButton.addActionListener(e -> chooseDate());
        searchButton.addActionListener(e -> searchCheci(starStationField.getText(), endStationField.getText(), selectedDate)); // 为查询按钮添加事件监听器
        viewBookingsButton.addActionListener(e -> showBookings()); // 为查看订票记录按钮添加事件监听器
        myTicketsButton.addActionListener(e -> queryUserTicket(customer));// 为“我的订票”按钮添加事件监听器
    }

    // 新增方法：选择日期
    public void chooseDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        date = null;
        try {
            // 尝试解析当前文本框中的日期
            date = dateFormat.parse(dateField.getText());
        } catch (Exception e) {
            // 如果解析失败，默认为当前日期
            date = new Date();
        }

        // 创建一个 JCalendar 用于日期选择
        JCalendar calendar = new JCalendar();
        calendar.setMinSelectableDate(new Date()); // 禁止选择今天之前的日期
        calendar.setMaxSelectableDate(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 365)); // 禁止选择超过1年的日期
        calendar.setDate(date); // 设置初始日期

        // 创建一个对话框，包含 JCalendar 组件
        int returnVal = JOptionPane.showConfirmDialog(
                null, // 父容器为空时，窗口居中
                calendar,
                "选择日期",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (returnVal == JOptionPane.OK_OPTION) {
            // 用户点击了 OK
            isDateSelected = true;

            // 获取选择的日期
            selectedDate = calendar.getDate();

            // 格式化并更新日期文本框
            dateField.setText(dateFormat.format(selectedDate));
        } else {
            // 用户点击了 Cancel 或关闭了对话框
            isDateSelected = false;
        }
    }

    // 修改后的查询方法，动态处理查询结果并更新表格
    public void searchCheci(String starStation, String endStation,Date date) {
        // 从数据源获取checi数据
        Get_Checi getCheci = new Get_Checi();

        // 获取用户选择的交通工具类型
        String selectedType = (String) transportTypeComboBox.getSelectedItem();

        // 调用 Get_CheCi 方法获取车次信息
        boolean hasTickets;
        // 清空 checis 数组（假设 checis 是一个对象数组）
        Arrays.fill(checis, null);
        if (selectedType.trim().equals("筛选")) {
            hasTickets = getCheci.Get_CheCi(system.conn, starStation, endStation, checis, date);
        } else {
            hasTickets = getCheci.Get_CheCi(system.conn, starStation, endStation, checis, selectedType,date);
        }

        // 清空当前表格数据
        tableModel.setRowCount(0);

        // 如果有票，遍历数组并显示查询结果
        if (hasTickets) {
            for (Checi c : checis) {
                if (c != null) {
                    // 如果用户选择了交通工具类型，则只显示该类型的车次
                    if (selectedType.trim().equals("筛选") || selectedType.equals(c.getType())) {
                        Object[] rowData = {
                                c.getID(), // 车次ID
                                c.getStarStation(), // 出发地
                                c.getEndingStation(), // 目的地
                                c.getData(),       //日期
                                c.getStarTime(), // 出发时间
                                c.getEndTime(), // 到达时间
                                c.getPrice(), // 票价
                                c.getTicketNumber(), // 剩余座位
                                "订票" // 操作按钮的列内容
                        };
                        tableModel.addRow(rowData); // 将查询结果添加到表格
                    }
                }
            }
        } else {
            // 如果没有票，显示一条信息
            JOptionPane.showMessageDialog(this, "没有找到符合条件的车次或票已售完！", "查询结果", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // 新增方法：获取系统当前日期字符串
    private String getTodayDateString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(new Date());
    }

    public void showBookings() {
        BookingRecordFrame recordFrame = new BookingRecordFrame(); // 创建订票记录窗口
        recordFrame.setVisible(true); // 显示订票记录窗口
    }

    public void queryUserTicket(Customer customer) {
        // 创建一个新的JFrame来显示订票信息
        JFrame ticketFrame = new JFrame("我的订票");
        ticketFrame.setSize(600, 400);
        ticketFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ticketFrame.setLocationRelativeTo(null);

        // 创建表格模型和表格
        String[] columnNames = {"列车ID", "出发日期", "出发地点", "途径地", "目的地", "出发时间", "到达时间", "座位类型"};
        DefaultTableModel ticketTableModel = new DefaultTableModel(columnNames, 0);
        JTable ticketTable = new JTable(ticketTableModel);

        // 填充表格数据
        Ticket[] tickets = new Ticket[100];
        Get_Ticket getTicket=new Get_Ticket();
        getTicket.getUserTicket(system.conn,customer,tickets);

        for (Ticket ticket : tickets) {
            if (ticket != null) { // 确保 ticket 不为 null
                Object[] rowData = {
                        ticket.getCheci_ID(), // 车次ID
                        ticket.getData(), // 出发日期
                        ticket.getStartStation(), // 出发地点
                        ticket.getWay(), // 途径地
                        ticket.getEndingStation(), // 目的地
                        ticket.getStarTime(), // 出发时间
                        ticket.getEndTime(), // 到达时间
                        ticket.getLevel() // 座位类型
                };
                ticketTableModel.addRow(rowData);
            }
        }

        // 将表格添加到滚动面板中，并添加到窗口
        JScrollPane scrollPane = new JScrollPane(ticketTable);
        ticketFrame.add(scrollPane, BorderLayout.CENTER);
        ticketFrame.setVisible(true);
    }
}