import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.Random;

public class LoginRegisterUI {
    private JFrame frame;
    private JPanel loginPanel, registerPanel, recoverPasswordPanel;
    private String generatedCode = ""; // 用于存储生成的验证码

    public LoginRegisterUI() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("订票系统");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setMinimumSize(new Dimension(600, 400));
        frame.setLayout(new CardLayout());

        loginPanel = createLoginPanel();
        registerPanel = createRegisterPanel();
        recoverPasswordPanel = createRecoverPasswordPanel();

        frame.add(loginPanel, "loginPanel");
        frame.add(registerPanel, "registerPanel");
        frame.add(recoverPasswordPanel, "recoverPasswordPanel");

        // 添加登录成功面板
        LoginSuccessPanel successPanel = new LoginSuccessPanel();
        frame.add(successPanel, "successPanel");

        frame.setVisible(true);
    }

    private JPanel createRegisterPanel() {
        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(255, 250, 240));

        JLabel titleLabel = new JLabel("注册界面", SwingConstants.CENTER);
        titleLabel.setFont(new Font("微软雅黑", Font.BOLD, 36));

        JLabel phoneLabel = new JLabel("手机号:");
        JLabel idLabel = new JLabel("账号:");
        JLabel passwordLabel = new JLabel("密码:");
        JLabel confirmPasswordLabel = new JLabel("确认密码:");
        JTextField phoneField = new JTextField();
        JTextField idField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JPasswordField confirmPasswordField = new JPasswordField();
        JButton registerButton = new JButton("注册");

        // 错误提示标签
        JLabel phoneErrorLabel = new JLabel("", SwingConstants.LEFT);
        JLabel idErrorLabel = new JLabel("", SwingConstants.LEFT);
        JLabel passwordErrorLabel = new JLabel("", SwingConstants.LEFT);
        JLabel confirmPasswordErrorLabel = new JLabel("", SwingConstants.LEFT);

        phoneErrorLabel.setForeground(Color.RED);
        idErrorLabel.setForeground(Color.RED);
        passwordErrorLabel.setForeground(Color.RED);
        confirmPasswordErrorLabel.setForeground(Color.RED);

        // 添加到面板
        panel.add(titleLabel);
        panel.add(phoneLabel);
        panel.add(phoneField);
        panel.add(idLabel);
        panel.add(idField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(confirmPasswordLabel);
        panel.add(confirmPasswordField);
        panel.add(registerButton);
        panel.add(phoneErrorLabel);
        panel.add(idErrorLabel);
        panel.add(passwordErrorLabel);
        panel.add(confirmPasswordErrorLabel);

        // 返回按钮
        JButton backButton = new JButton("返回");
        panel.add(backButton);
        backButton.setBounds(10, 10, 80, 30);

        // 监听窗口变化，动态设置控件大小和位置
        panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int width = panel.getWidth();
                int height = panel.getHeight();

                int fieldWidth = (int) (width * 0.4);
                int fieldHeight = 40;

                titleLabel.setBounds((width - fieldWidth) / 2, height / 8, fieldWidth, 50);

                phoneLabel.setBounds((width - fieldWidth) / 2 - 100, height / 3, 80, fieldHeight);
                phoneField.setBounds((width - fieldWidth) / 2, height / 3, fieldWidth, fieldHeight);
                phoneErrorLabel.setBounds((width - fieldWidth) / 2, height / 3 + 45, fieldWidth, 20);

                idLabel.setBounds((width - fieldWidth) / 2 - 100, height / 3 + 70, 80, fieldHeight);
                idField.setBounds((width - fieldWidth) / 2, height / 3 + 70, fieldWidth, fieldHeight);
                idErrorLabel.setBounds((width - fieldWidth) / 2, height / 3 + 115, fieldWidth, 20);

                passwordLabel.setBounds((width - fieldWidth) / 2 - 100, height / 3 + 140, 80, fieldHeight);
                passwordField.setBounds((width - fieldWidth) / 2, height / 3 + 140, fieldWidth, fieldHeight);
                passwordErrorLabel.setBounds((width - fieldWidth) / 2, height / 3 + 185, fieldWidth, 20);

                confirmPasswordLabel.setBounds((width - fieldWidth) / 2 - 100, height / 3 + 210, 100, fieldHeight);
                confirmPasswordField.setBounds((width - fieldWidth) / 2, height / 3 + 210, fieldWidth, fieldHeight);
                confirmPasswordErrorLabel.setBounds((width - fieldWidth) / 2, height / 3 + 255, fieldWidth, 20);

                registerButton.setBounds((width - fieldWidth) / 2, height / 3 + 290, fieldWidth, 50);
            }
        });

        // 实时验证输入
        phoneField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validateRegisterInput(phoneField, idField, passwordField, confirmPasswordField, phoneErrorLabel, idErrorLabel, passwordErrorLabel, confirmPasswordErrorLabel);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                validateRegisterInput(phoneField, idField, passwordField, confirmPasswordField, phoneErrorLabel, idErrorLabel, passwordErrorLabel, confirmPasswordErrorLabel);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validateRegisterInput(phoneField, idField, passwordField, confirmPasswordField, phoneErrorLabel, idErrorLabel, passwordErrorLabel, confirmPasswordErrorLabel);
            }
        });

        // ... 其他 DocumentListener ...

        registerButton.addActionListener(e -> {
            String phone = phoneField.getText().trim();
            String id = idField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();
            String confirmPassword = new String(confirmPasswordField.getPassword()).trim();

            if (validateRegisterInput(phoneField, idField, passwordField, confirmPasswordField, phoneErrorLabel, idErrorLabel, passwordErrorLabel, confirmPasswordErrorLabel)) {
                // 注册逻辑
                JOptionPane.showMessageDialog(frame, "注册成功", "提示", JOptionPane.INFORMATION_MESSAGE);
                switchPanel("loginPanel");
            } else {
                JOptionPane.showMessageDialog(frame, "请检查您的输入", "错误", JOptionPane.ERROR_MESSAGE);
            }
        });

        backButton.addActionListener(e -> switchPanel("loginPanel"));

        return panel;
    }

    // 更新 validateRegisterInput 方法以包含手机号的验证
    private boolean validateRegisterInput(JTextField phoneField, JTextField idField, JPasswordField passwordField, JPasswordField confirmPasswordField, JLabel phoneErrorLabel, JLabel idErrorLabel, JLabel passwordErrorLabel, JLabel confirmPasswordErrorLabel) {
        String phone = phoneField.getText().trim();
        String id = idField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();
        String confirmPassword = new String(confirmPasswordField.getPassword()).trim();

        boolean validPhone = phone.matches("\\d{11}");
        boolean validId = id.length() >= 6;
        boolean validPassword = password.length() >= 6;
        boolean validConfirmPassword = password.equals(confirmPassword);

        phoneErrorLabel.setText(validPhone ? "" : "手机号格式不正确");
        idErrorLabel.setText(validId ? "" : "账号不能为空，且至少6位");
        passwordErrorLabel.setText(validPassword ? "" : "密码不能为空，且至少6位");
        confirmPasswordErrorLabel.setText(validConfirmPassword ? "" : "密码和确认密码不一致");

        return validPhone && validId && validPassword && validConfirmPassword;
    }

    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(240, 248, 255));

        JLabel titleLabel = new JLabel("登录界面", SwingConstants.CENTER);
        titleLabel.setFont(new Font("微软雅黑", Font.BOLD, 36));

        JLabel idLabel = new JLabel("账号:");
        JLabel passwordLabel = new JLabel("密码:");
        JTextField idField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JLabel promptLabel = new JLabel("", SwingConstants.CENTER);
        promptLabel.setForeground(Color.RED);
        JButton loginButton = new JButton("登录");
        JButton registerButton = new JButton("注册");
        JButton recoverPasswordButton = new JButton("忘记密码");

        // 错误提示标签
        JLabel idErrorLabel = new JLabel("", SwingConstants.LEFT);
        JLabel passwordErrorLabel = new JLabel("", SwingConstants.LEFT);

        idErrorLabel.setForeground(Color.RED);
        passwordErrorLabel.setForeground(Color.RED);

        // 添加到面板
        panel.add(titleLabel);
        panel.add(idLabel);
        panel.add(idField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(promptLabel);
        panel.add(loginButton);
        panel.add(registerButton);
        panel.add(recoverPasswordButton);
        panel.add(idErrorLabel);
        panel.add(passwordErrorLabel);

        // 监听窗口变化，动态设置控件大小和位置
        panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int width = panel.getWidth();
                int height = panel.getHeight();

                int fieldWidth = (int) (width * 0.4);
                int fieldHeight = 40;

                titleLabel.setBounds((width - fieldWidth) / 2, height / 8, fieldWidth, 50);

                idLabel.setBounds((width - fieldWidth) / 2 - 100, height / 3, 80, fieldHeight);
                idField.setBounds((width - fieldWidth) / 2, height / 3, fieldWidth, fieldHeight);
                idErrorLabel.setBounds((width - fieldWidth) / 2, height / 3 + 45, fieldWidth, 20);

                passwordLabel.setBounds((width - fieldWidth) / 2 - 100, height / 3 + 70, 80, fieldHeight);
                passwordField.setBounds((width - fieldWidth) / 2, height / 3 + 70, fieldWidth, fieldHeight);
                passwordErrorLabel.setBounds((width - fieldWidth) / 2, height / 3 + 115, fieldWidth, 20);

                promptLabel.setBounds((width - fieldWidth) / 2, height / 3 + 120, fieldWidth, 30);

                int buttonWidth = fieldWidth / 2 - 20;
                loginButton.setBounds((width - fieldWidth) / 2, height / 3 + 180, buttonWidth, 50);
                registerButton.setBounds((width - fieldWidth) / 2 + buttonWidth + 40, height / 3 + 180, buttonWidth, 50);
                recoverPasswordButton.setBounds((width - fieldWidth) / 2, height / 3 + 250, fieldWidth, 30);
            }
        });

        // 实时验证输入
        idField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validateLoginInput(idField, passwordField, idErrorLabel, passwordErrorLabel);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                validateLoginInput(idField, passwordField, idErrorLabel, passwordErrorLabel);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validateLoginInput(idField, passwordField, idErrorLabel, passwordErrorLabel);
            }
        });

        passwordField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validateLoginInput(idField, passwordField, idErrorLabel, passwordErrorLabel);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                validateLoginInput(idField, passwordField, idErrorLabel, passwordErrorLabel);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validateLoginInput(idField, passwordField, idErrorLabel, passwordErrorLabel);
            }
        });

        loginButton.addActionListener(e -> {
            String id = idField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            // 登录验证
            boolean isValidLogin = validateLogin(id, password);

            if (isValidLogin) {
                // 登录成功，显示登录成功面板
                showLoginSuccessPanel();
            } else {
                // 登录失败，显示错误提示
                JOptionPane.showMessageDialog(frame, "账号或密码错误", "错误", JOptionPane.ERROR_MESSAGE);
            }
        });

        registerButton.addActionListener(e -> switchPanel("registerPanel"));
        recoverPasswordButton.addActionListener(e -> switchPanel("recoverPasswordPanel"));

        return panel;
    }

    private JPanel createRecoverPasswordPanel() {
        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(255, 250, 240));

        JLabel titleLabel = new JLabel("找回密码", SwingConstants.CENTER);
        titleLabel.setFont(new Font("微软雅黑", Font.BOLD, 36));

        JLabel phoneLabel = new JLabel("手机号:");
        JLabel codeLabel = new JLabel("验证码:");
        JLabel passwordLabel = new JLabel("新密码:");
        JLabel confirmPasswordLabel = new JLabel("确认密码:");

        JTextField phoneField = new JTextField();
        JTextField codeField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JPasswordField confirmPasswordField = new JPasswordField();

        JButton sendCodeButton = new JButton("发送验证码");
        JButton confirmButton = new JButton("确认修改");

        JLabel phoneErrorLabel = new JLabel("", SwingConstants.LEFT);
        JLabel codeErrorLabel = new JLabel("", SwingConstants.LEFT);
        JLabel passwordErrorLabel = new JLabel("", SwingConstants.LEFT);
        JLabel confirmPasswordErrorLabel = new JLabel("", SwingConstants.LEFT);

        phoneErrorLabel.setForeground(Color.RED);
        codeErrorLabel.setForeground(Color.RED);
        passwordErrorLabel.setForeground(Color.RED);
        confirmPasswordErrorLabel.setForeground(Color.RED);

        // 添加控件
        panel.add(titleLabel);
        panel.add(phoneLabel);
        panel.add(phoneField);
        panel.add(codeLabel);
        panel.add(codeField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(confirmPasswordLabel);
        panel.add(confirmPasswordField);
        panel.add(sendCodeButton);
        panel.add(confirmButton);
        panel.add(phoneErrorLabel);
        panel.add(codeErrorLabel);
        panel.add(passwordErrorLabel);
        panel.add(confirmPasswordErrorLabel);

        // 返回按钮
        JButton backButton = new JButton("返回");
        panel.add(backButton);
        backButton.setBounds(10, 10, 80, 30);

        // 监听窗口变化，动态调整控件大小和位置
        panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int width = panel.getWidth();
                int height = panel.getHeight();

                int fieldWidth = (int) (width * 0.4);
                int fieldHeight = 40;

                titleLabel.setBounds((width - fieldWidth) / 2, height / 10, fieldWidth, 50);

                phoneLabel.setBounds((width - fieldWidth) / 2 - 100, height / 5, 80, fieldHeight);
                phoneField.setBounds((width - fieldWidth) / 2, height / 5, fieldWidth, fieldHeight);
                phoneErrorLabel.setBounds((width - fieldWidth) / 2, height / 5 + 45, fieldWidth, 20);

                codeLabel.setBounds((width - fieldWidth) / 2 - 100, height / 5 + 60, 80, fieldHeight);
                codeField.setBounds((width - fieldWidth) / 2, height / 5 + 60, fieldWidth, fieldHeight);
                codeErrorLabel.setBounds((width - fieldWidth) / 2, height / 5 + 105, fieldWidth, 20);

                passwordLabel.setBounds((width - fieldWidth) / 2 - 100, height / 5 + 120, 80, fieldHeight);
                passwordField.setBounds((width - fieldWidth) / 2, height / 5 + 120, fieldWidth, fieldHeight);
                passwordErrorLabel.setBounds((width - fieldWidth) / 2, height / 5 + 165, fieldWidth, 20);

                confirmPasswordLabel.setBounds((width - fieldWidth) / 2 - 100, height / 5 + 180, 100, fieldHeight);
                confirmPasswordField.setBounds((width - fieldWidth) / 2, height / 5 + 180, fieldWidth, fieldHeight);
                confirmPasswordErrorLabel.setBounds((width - fieldWidth) / 2, height / 5 + 225, fieldWidth, 20);

                sendCodeButton.setBounds((width - fieldWidth) / 2 + fieldWidth + 10, height / 5 + 60, 150, fieldHeight);
                confirmButton.setBounds((width - fieldWidth) / 2, height / 5 + 250, fieldWidth, 50);
            }
        });

        // 发送验证码
        sendCodeButton.addActionListener(e -> {
            String phone = phoneField.getText().trim();

            if (phone.isEmpty() || !phone.matches("\\d{11}")) {
                phoneErrorLabel.setText("请输入有效的手机号");
                return;
            }

            // 生成验证码并发送（这里简化处理）
            generatedCode = generateVerificationCode();
            JOptionPane.showMessageDialog(frame, "验证码已发送到您的手机", "提示", JOptionPane.INFORMATION_MESSAGE);
            phoneErrorLabel.setText("");
        });

        // 确认修改密码
        confirmButton.addActionListener(e -> {
            String code = codeField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();
            String confirmPassword = new String(confirmPasswordField.getPassword()).trim();

            if (!code.equals(generatedCode)) {
                codeErrorLabel.setText("验证码错误");
            } else if (!password.equals(confirmPassword)) {
                passwordErrorLabel.setText("两次密码不一致");
            } else {
                // 这里可以加入实际修改密码的逻辑
                JOptionPane.showMessageDialog(frame, "密码修改成功", "提示", JOptionPane.INFORMATION_MESSAGE);
                switchPanel("loginPanel");
            }
        });

        backButton.addActionListener(e -> switchPanel("loginPanel"));

        return panel;
    }

    private String generateVerificationCode() {
        Random rand = new Random();
        int code = rand.nextInt(999999);
        return String.format("%06d", code
        ); // 保证6位数字
    }

    private void switchPanel(String panelName) {
        CardLayout layout = (CardLayout) frame.getContentPane().getLayout();
        layout.show(frame.getContentPane(), panelName);
    }

    private boolean validateLogin(String id, String password) {
        // 简单的验证逻辑
        // 你可以根据实际需求替换为从数据库中查询
        return id.equals("admin") && password.equals("123456");
    }

    private void validateLoginInput(JTextField idField, JPasswordField passwordField, JLabel idErrorLabel, JLabel passwordErrorLabel) {
        String id = idField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        boolean validId = id.length() >= 6;
        boolean validPassword = password.length() >= 6;

        idErrorLabel.setText(validId ? "" : "账号不能为空");
        passwordErrorLabel.setText(validPassword ? "" : "密码不能为空");
    }

    // 登录成功面板
    private class LoginSuccessPanel extends JPanel {
        public LoginSuccessPanel() {
            setBackground(Color.WHITE);
            setPreferredSize(new Dimension(800, 600));
            setLayout(null);

            JLabel titleLabel = new JLabel("登录成功");
            titleLabel.setFont(new Font("微软雅黑", Font.BOLD, 48));
            titleLabel.setForeground(Color.BLUE);
            titleLabel.setBounds(300, 200, 200, 50);
            add(titleLabel);

            JLabel welcomeLabel = new JLabel("欢迎使用订票系统");
            welcomeLabel.setFont(new Font("微软雅黑", Font.PLAIN, 24));
            welcomeLabel.setForeground(Color.GRAY);
            welcomeLabel.setBounds(300, 280, 300, 30);
            add(welcomeLabel);
        }
    }

    private void showLoginSuccessPanel() {
        CardLayout layout = (CardLayout) frame.getContentPane().getLayout();
        layout.show(frame.getContentPane(), "successPanel");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginRegisterUI::new);
    }
}