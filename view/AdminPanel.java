package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AdminPanel extends JPanel {

    private JTextField startTimeField;
    private JTextField endTimeField;
    private JComboBox<String> transportComboBox;
    private JButton queryButton;
    private JLabel timeLabel;

    public AdminPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // 返回按钮
        JButton backButton = new JButton("返回");
        backButton.addActionListener(e -> showMainPanel());
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(backButton, gbc);

        // 时间显示框
        timeLabel = new JLabel();
        updateTimeLabel();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(timeLabel, gbc);

        // 起点输入框
        startTimeField = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(new JLabel("起点:"), gbc);
        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 5, 5, 5);
        add(startTimeField, gbc);

        // 终点输入框
        endTimeField = new JTextField(20);
        gbc.gridy++;
        gbc.insets = new Insets(5, 5, 5, 5);
        add(new JLabel("终点:"), gbc);
        gbc.gridy++;
        add(endTimeField, gbc);

        // 筛选框
        String[] transportOptions = {"火车", "飞机", "高铁", "汽车"};
        transportComboBox = new JComboBox<>(transportOptions);
        gbc.gridx = 1;
        gbc.gridy--;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 10, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(new JLabel("筛选:"), gbc);
        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 5, 5, 5);
        gbc.fill = GridBagConstraints.NONE;
        add(transportComboBox, gbc);

        // 查询按钮
        queryButton = new JButton("查询");
        queryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showQueryPanel();
            }
        });
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        add(queryButton, gbc);

        Timer timer = new Timer(1000, e -> updateTimeLabel());
        timer.start();
    }

    private void updateTimeLabel() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        timeLabel.setText("当前时间: " + sdf.format(new Date()));
    }

    private void showMainPanel() {
        MainFrame1.getInstance().setContentPane(new AdminPanel());
        MainFrame1.getInstance().revalidate();
        MainFrame1.getInstance().repaint();
    }

    private void showQueryPanel() {
        MainFrame1.getInstance().setContentPane(new QueryPanel(
                (String) transportComboBox.getSelectedItem(),
                startTimeField.getText(),
                endTimeField.getText()
        ));
        MainFrame1.getInstance().revalidate();
        MainFrame1.getInstance().repaint();
    }
}