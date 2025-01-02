package view;

import model.Warden;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeTicket {
    public static void main(String[] args) {
        // 创建 JFrame 窗体
        JFrame frame = new JFrame("管理员修改票务界面");
        frame.setSize(600, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // 创建返回按钮
        JButton backButton = new JButton("返回");
        backButton.setBounds(10, 10, 60, 30);
        frame.add(backButton);

        // 输入交通编号标签和输入框
        JLabel inputLabel = new JLabel("输入交通编号：");
        inputLabel.setBounds(100, 50, 100, 30);
        frame.add(inputLabel);

        JTextField trafficNumberField = new JTextField();
        trafficNumberField.setBounds(200, 50, 150, 30);
        frame.add(trafficNumberField);

        // 查询按钮
        JButton queryButton = new JButton("查询");
        queryButton.setBounds(100, 100, 80, 30);
        frame.add(queryButton);

        // 停运按钮
        JButton stopButton = new JButton("停运");
        stopButton.setBounds(200, 100, 80, 30);
        frame.add(stopButton);

        // 提示区
        JTextArea hintArea = new JTextArea();
        hintArea.setBounds(300, 100, 200, 100);
        hintArea.setEditable(false);
        hintArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        hintArea.setText("提示信息将显示在此处");
        frame.add(hintArea);

        // 返回按钮事件
        backButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "返回主界面"));

        // 查询按钮事件
        queryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String trafficNumber = trafficNumberField.getText();
                if (trafficNumber.isEmpty()) {
                    hintArea.setText("请输入交通编号！");
                } else {
                    hintArea.setText("查询成功：\n交通编号 " + trafficNumber + " 的信息...");
                }
            }
        });

        // 停运按钮事件
        stopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String trafficNumber = trafficNumberField.getText();
                if (trafficNumber.isEmpty()) {
                    hintArea.setText("请输入交通编号！");
                    return;
                }

                // 弹出确认对话框
                int confirm = JOptionPane.showConfirmDialog(frame, "确认停运交通编号：" + trafficNumber + "？", "确认停运",
                        JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    // 要求输入管理员密码
                    String password = JOptionPane.showInputDialog(frame, "请输入管理员密码：");
                    Warden warden = new Warden();
                    if (warden.change_ticket(password)) {
                        hintArea.setText("停运成功！交通编号：" + trafficNumber + " 已停运。");
                    } else {
                        hintArea.setText("停运失败！密码错误，无法停运交通编号：" + trafficNumber);
                    }
                } else {
                    hintArea.setText("停运操作已取消。");
                }
            }
        });

        // 设置窗口可见
        frame.setVisible(true);
    }
}
