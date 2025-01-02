package view;
import database.Get_Checi; // 导入车次信息获取类
import model.Customer;
import view.BookingTicket;
import model.Checi; // 导入车次模型类
import model.MySystem; // 导入系统模型类
import javax.swing.*; // 导入Swing组件库
import javax.swing.table.DefaultTableModel; // 导入表格模型类
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import java.awt.*; // 导入AWT组件库

public class QueryCheci extends JFrame { // 定义QueryCheci类，继承自JFrame，用于创建GUI窗口
    public JTable CheciTable; // 定义JTable对象，用于显示车次信息
    public DefaultTableModel tableModel; // 定义DefaultTableModel对象，用于管理表格数据
    MySystem system; // 定义System对象，用于访问系统功能
    Checi[] checis;
    Customer customer;

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

        // 添加事件监听器
        searchButton.addActionListener(e -> searchCheci(starStationField.getText(), endStationField.getText())); // 为查询按钮添加事件监听器
        viewBookingsButton.addActionListener(e -> showBookings()); // 为查看订票记录按钮添加事件监听器
    }

    // 修改后的查询方法，动态处理查询结果并更新表格
    public void searchCheci(String starStation, String endStation) {
        // 从数据源获取checi数据
        Get_Checi getCheci = new Get_Checi();

        // 获取用户选择的交通工具类型
        String selectedType = (String) transportTypeComboBox.getSelectedItem();

        // 调用 Get_CheCi 方法获取车次信息
        boolean hasTickets;
        if (selectedType.trim().equals("筛选")) {
            hasTickets = getCheci.Get_CheCi(system.conn, starStation, endStation, checis);
        } else {
            hasTickets = getCheci.Get_CheCi(system.conn, starStation, endStation, checis, selectedType);
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

    public void showBookings() {
        BookingRecordFrame recordFrame = new BookingRecordFrame(); // 创建订票记录窗口
        recordFrame.setVisible(true); // 显示订票记录窗口
    }
}