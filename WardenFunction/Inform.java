import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Warden2 {
    // 模拟管理员权限判断
    public boolean inform(String trafficNumber, String reason) {
        return trafficNumber != null && !trafficNumber.isEmpty() &&
                reason != null && !reason.isEmpty();
    }
}

public class Inform {
    public static void main(String[] args) {
        // 创建 JFrame 窗体
        JFrame frame = new JFrame("管理员发布通知界面");
        frame.setSize(600, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // 创建返回按钮
        JButton backButton = new JButton("返回");
        backButton.setBounds(10, 10, 60, 30);
        frame.add(backButton);

        // 输入交通编号
        JLabel trafficLabel = new JLabel("输入交通编号：");
        trafficLabel.setBounds(100, 50, 100, 30);
        frame.add(trafficLabel);

        JTextField trafficNumberField = new JTextField();
        trafficNumberField.setBounds(200, 50, 200, 30);
        frame.add(trafficNumberField);

        // 输入原因
        JLabel reasonLabel = new JLabel("输入原因：");
        reasonLabel.setBounds(100, 100, 100, 30);
        frame.add(reasonLabel);

        JTextField reasonField = new JTextField();
        reasonField.setBounds(200, 100, 200, 30);
        frame.add(reasonField);

        // 发布通知按钮
        JButton publishButton = new JButton("发布通知");
        publishButton.setBounds(100, 150, 100, 30);
        frame.add(publishButton);

        // 提示区
        JTextArea hintArea = new JTextArea();
        hintArea.setBounds(200, 150, 300, 80);
        hintArea.setEditable(false);
        hintArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        hintArea.setText("提示信息将显示在此处");
        frame.add(hintArea);

        // 返回按钮事件
        backButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "返回主界面"));

        // 发布通知按钮事件
        publishButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String trafficNumber = trafficNumberField.getText();
                String reason = reasonField.getText();

                if (trafficNumber.isEmpty() || reason.isEmpty()) {
                    hintArea.setText("请输入完整的交通编号和原因！");
                    return;
                }

                // 弹出确认对话框
                int confirm = JOptionPane.showConfirmDialog(frame, "确认发布通知？\n交通编号：" + trafficNumber + "\n原因：" + reason,
                        "确认发布", JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    Warden2 warden2 = new Warden2();
                    if (warden2.inform(trafficNumber, reason)) {
                        hintArea.setText("通知发布成功！\n交通编号：" + trafficNumber + "\n原因：" + reason);
                    } else {
                        hintArea.setText("通知发布失败！\n请检查输入信息是否完整！");
                    }
                } else {
                    hintArea.setText("发布操作已取消。");
                }
            }
        });

        // 设置窗口可见
        frame.setVisible(true);
    }
}
