package view;

import model.Warden;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetTicket {
    public static void main(String[] args) {
        // 创建 JFrame 窗体
        JFrame frame = new JFrame("管理员设置票务界面");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // 创建返回按钮
        JButton backButton = new JButton("返回");
        backButton.setBounds(10, 10, 60, 30);
        frame.add(backButton);

        // 日期选择
        JLabel dateLabel = new JLabel("日期：");
        dateLabel.setBounds(100, 50, 60, 30);
        frame.add(dateLabel);

        JTextField monthField = new JTextField("");
        monthField.setBounds(150, 50, 30, 30);
        frame.add(monthField);

        JLabel monthLabel = new JLabel("月");
        monthLabel.setBounds(185, 50, 20, 30);
        frame.add(monthLabel);

        JTextField dayField = new JTextField("");
        dayField.setBounds(210, 50, 30, 30);
        frame.add(dayField);

        JLabel dayLabel = new JLabel("日");
        dayLabel.setBounds(245, 50, 20, 30);
        frame.add(dayLabel);

        // 起点和终点
        JTextField startField = new JTextField("");
        startField.setBounds(100, 90, 100, 30);
        frame.add(startField);

        JLabel arrowLabel = new JLabel("→");
        arrowLabel.setBounds(210, 90, 20, 30);
        frame.add(arrowLabel);

        JTextField endField = new JTextField("");
        endField.setBounds(240, 90, 100, 30);
        frame.add(endField);

        // 单选按钮（交通工具）
        JLabel singleChoiceLabel = new JLabel("选择：（单选）");
        singleChoiceLabel.setBounds(100, 130, 100, 30);
        frame.add(singleChoiceLabel);

        JRadioButton trainButton = new JRadioButton("火车");
        trainButton.setBounds(100, 160, 80, 30);

        JRadioButton planeButton = new JRadioButton("飞机", true);
        planeButton.setBounds(180, 160, 80, 30);

        JRadioButton highSpeedButton = new JRadioButton("高铁");
        highSpeedButton.setBounds(260, 160, 80, 30);

        JRadioButton carButton = new JRadioButton("汽车");
        carButton.setBounds(340, 160, 80, 30);

        ButtonGroup group = new ButtonGroup();
        group.add(trainButton);
        group.add(planeButton);
        group.add(highSpeedButton);
        group.add(carButton);

        frame.add(trainButton);
        frame.add(planeButton);
        frame.add(highSpeedButton);
        frame.add(carButton);

        // 多选按钮（座位类型）
        JLabel multiChoiceLabel = new JLabel("选择：（多选）");
        multiChoiceLabel.setBounds(100, 200, 100, 30);
        frame.add(multiChoiceLabel);

        JCheckBox firstClass = new JCheckBox("一等座");
        firstClass.setBounds(100, 230, 80, 30);
        frame.add(firstClass);

        JCheckBox secondClass = new JCheckBox("二等座", true);
        secondClass.setBounds(180, 230, 80, 30);
        frame.add(secondClass);

        JCheckBox businessClass = new JCheckBox("商务座");
        businessClass.setBounds(260, 230, 80, 30);
        frame.add(businessClass);

        JCheckBox standingTicket = new JCheckBox("站票");
        standingTicket.setBounds(340, 230, 80, 30);
        frame.add(standingTicket);

        // 数量输入
        JLabel quantityLabel = new JLabel("数量：");
        quantityLabel.setBounds(100, 270, 60, 30);
        frame.add(quantityLabel);

        JTextField quantityField = new JTextField();
        quantityField.setBounds(160, 270, 60, 30);
        frame.add(quantityField);

        // 查询按钮
        JButton submitButton = new JButton("设置");
        submitButton.setBounds(100, 320, 80, 30);
        frame.add(submitButton);

        // 订单管理按钮
        JButton orderButton = new JButton("订单管理");
        orderButton.setBounds(200, 320, 100, 30);
        frame.add(orderButton);

        // 事件监听
        Warden warden = new Warden();
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (warden.set_ticket()) {
                    JOptionPane.showMessageDialog(frame, "票务设置成功！\n日期：" + monthField.getText() + "月" + dayField.getText() + "日\n"
                            + "起点：" + startField.getText() + " → 终点：" + endField.getText() + "\n数量：" + quantityField.getText());
                } else {
                    JOptionPane.showMessageDialog(frame, "管理员权限不足！");
                }
            }
        });

        backButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "返回首页"));

        // 设置窗口可见
        frame.setVisible(true);
    }
}