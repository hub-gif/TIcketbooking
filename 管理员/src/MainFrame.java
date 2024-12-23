import javax.swing.*;

public class MainFrame extends JFrame {

    private static MainFrame instance;

    private MainFrame() {
        setTitle("交通查询系统");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setContentPane(new AdminPanel());
    }

    public static MainFrame getInstance() {
        if (instance == null) {
            instance = new MainFrame();
        }
        return instance;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> MainFrame.getInstance().setVisible(true));
    }
}