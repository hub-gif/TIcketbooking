package view;

import javax.swing.*;

public class MainFrame1 extends JFrame {

    private static MainFrame1 instance;

    private MainFrame1() {
        setTitle("交通查询系统");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setContentPane(new AdminPanel());
    }

    public static MainFrame1 getInstance() {
        if (instance == null) {
            instance = new MainFrame1();
        }
        return instance;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> MainFrame1.getInstance().setVisible(true));
    }
}