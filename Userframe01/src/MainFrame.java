
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MainFrame extends JFrame {
    private JTable flightTable;
    private DefaultTableModel tableModel;

    public MainFrame() {
        setTitle("订票系统 - 航班查询");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 创建工具栏
        JPanel toolBar = new JPanel();
        toolBar.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // 设置组件间的间距

        JTextField departureField = new JTextField(20);
        JTextField destinationField = new JTextField(20);
        JButton searchButton = new JButton("搜索航班");
        JButton viewBookingsButton = new JButton("查看订票记录");

        gbc.gridx = 0;
        gbc.gridy = 0;
        toolBar.add(new JLabel("出发地："), gbc);

        gbc.gridx = 1;
        toolBar.add(departureField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        toolBar.add(new JLabel("目的地："), gbc);

        gbc.gridx = 3;
        toolBar.add(destinationField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 4; // 让按钮跨越4列
        toolBar.add(searchButton, gbc);

        gbc.gridy = 2;
        toolBar.add(viewBookingsButton, gbc);

        // 创建表格
        String[] columnNames = {"航班号", "出发地", "目的地", "起飞时间", "到达时间", "票价", "剩余座位", "操作"};
        tableModel = new DefaultTableModel(columnNames, 0);
        flightTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(flightTable);

        // 添加订票按钮到表格
        flightTable.getColumn("操作").setCellRenderer(new ButtonRenderer());
        flightTable.getColumn("操作").setCellEditor(new ButtonEditor(new JCheckBox()));

        // 布局
        setLayout(new BorderLayout());
        add(toolBar, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // 添加事件监听器
        searchButton.addActionListener(e -> searchFlights(departureField.getText(), destinationField.getText()));
        viewBookingsButton.addActionListener(e -> showBookings());
    }

    void searchFlights(String departure, String destination) {
        // 这里应该添加代码来从数据源获取航班数据
        // 以下是示例数据
    }

    private void showBookings() {
        BookingRecordFrame recordFrame = new BookingRecordFrame();
        recordFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
}






