import javax.swing.*;
import java.awt.*;

public class QueryPanel extends JPanel {

    private String transportMode;
    private String startTime;
    private String endTime;

    public QueryPanel(String transportMode, String startTime, String endTime) {
        this.transportMode = transportMode;
        this.startTime = startTime;
        this.endTime = endTime;

        setLayout(new FlowLayout());

        // 返回按钮
        JButton backButton = new JButton("返回");
        backButton.addActionListener(e -> showAdminPanel());
        add(backButton);

        // 显示查询结果的标签（这里只是模拟，实际应用中应从数据库读取数据）
        JLabel resultLabel = new JLabel("查询结果（模拟数据）: " +
                "从 " + startTime + " 到 " + endTime + " 的 " + transportMode + " 信息");
        add(resultLabel);
    }

    private void showAdminPanel() {
        MainFrame.getInstance().setContentPane(new AdminPanel());
        MainFrame.getInstance().revalidate();
        MainFrame.getInstance().repaint();
    }
}