package view;

import database.Get_User;
import database.Insert_user;
import model.Customer;
import model.MySystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.Random;

public class LoginRegisterUI {      // 登录注册UI类，用于创建和管理登录、注册和找回密码的界面
    public JFrame frame;
    // 声明JFrame对象用于创建窗口
    public JPanel loginPanel, registerPanel, recoverPasswordPanel;
    //三个JPanel对象分别对应登录、注册和找回密码的面板
    public String generatedCode = "";
    // 用于存储生成的验证码
    MySystem system;
    Customer customer;
    public LoginRegisterUI() {
        system= new MySystem();
        customer=new Customer();
        initialize();
    }
    // 构造函数，初始化界面

    public void initialize() {     // 初始化方法，设置窗口和面板的基本属性
        frame = new JFrame("订票系统"); // 创建窗口，标题为“订票系统”
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置默认关闭操作
        frame.setSize(1000, 800); // 设置窗口大小
        frame.setMinimumSize(new Dimension(600, 400)); // 设置窗口最小大小
        frame.setLayout(new CardLayout()); // 设置布局管理器为CardLayout，用于切换面板

        // 创建三个面板，并添加到窗口中
        loginPanel = createLoginPanel();
        registerPanel = createRegisterPanel();
        recoverPasswordPanel = createRecoverPasswordPanel();

        frame.add(loginPanel, "loginPanel");
        frame.add(registerPanel, "registerPanel");
        frame.add(recoverPasswordPanel, "recoverPasswordPanel");
        frame.setVisible(true); // 设置窗口可见
    }

    /**
     * 创建注册面板的方法，用于构建用户注册界面。
     * 该面板包含输入框、标签和按钮等组件，并提供输入验证和注册逻辑。
     *
     * @return 返回构建好的注册面板对象。
     */
    public JPanel createRegisterPanel() {
        // 创建一个新的JPanel对象，设置布局管理器为null，以便手动设置组件位置和大小。
        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(255, 250, 240)); // 设置面板背景颜色

        // 创建标签和输入框组件
        JLabel titleLabel = new JLabel("注册界面", SwingConstants.CENTER);
        titleLabel.setFont(new Font("微软雅黑", Font.BOLD, 36)); // 设置标题字体样式

        JLabel nicknameLabel = new JLabel("用户昵称:");
        JTextField nicknameField = new JTextField();
        JLabel phoneLabel = new JLabel("手机号:");
        JLabel idLabel = new JLabel("账号:");
        JLabel passwordLabel = new JLabel("密码:");
        JLabel confirmPasswordLabel = new JLabel("确认密码:");
        JTextField phoneField = new JTextField();
        JTextField idField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JPasswordField confirmPasswordField = new JPasswordField();
        JButton registerButton = new JButton("注册");

        // 创建错误提示标签，并设置字体颜色为红色
        JLabel phoneErrorLabel = new JLabel("", SwingConstants.LEFT);
        JLabel idErrorLabel = new JLabel("", SwingConstants.LEFT);
        JLabel passwordErrorLabel = new JLabel("", SwingConstants.LEFT);
        JLabel confirmPasswordErrorLabel = new JLabel("", SwingConstants.LEFT);
        JLabel nicknameErrorLabel = new JLabel("", SwingConstants.LEFT);

        phoneErrorLabel.setForeground(Color.RED);
        idErrorLabel.setForeground(Color.RED);
        passwordErrorLabel.setForeground(Color.RED);
        confirmPasswordErrorLabel.setForeground(Color.RED);
        nicknameErrorLabel.setForeground(Color.RED);

        // 将组件添加到面板
        panel.add(titleLabel);
        panel.add(nicknameLabel);
        panel.add(nicknameField);
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
        panel.add(idErrorLabel);            //账户错误提示标签
        panel.add(passwordErrorLabel);
        panel.add(confirmPasswordErrorLabel);
        panel.add(nicknameErrorLabel);      // 昵称错误提示标签

        // 创建返回按钮，并添加到面板
        JButton backButton = new JButton("返回");
        panel.add(backButton);
        backButton.setBounds(10, 10, 80, 30); // 设置返回按钮的位置和大小

        // 监听窗口变化，动态设置控件大小和位置
        panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int width = panel.getWidth();
                int height = panel.getHeight();

                int fieldWidth = (int) (width * 0.4);
                int fieldHeight = 40;

                // 设置标题标签的位置和大小
                titleLabel.setBounds((width - fieldWidth) / 2, height / 8, fieldWidth, 50);

                // 设置用户昵称相关组件的位置和大小
                nicknameLabel.setBounds((width - fieldWidth) / 2 - 100, height / 8 + 60, 100, fieldHeight);
                nicknameField.setBounds((width - fieldWidth) / 2, height / 8 + 60, fieldWidth, fieldHeight);
                nicknameErrorLabel.setBounds((width - fieldWidth) / 2, height / 8 + 105, fieldWidth, 20);

                // 设置手机号相关组件的位置和大小
                phoneLabel.setBounds((width - fieldWidth) / 2 - 100, height / 3, 80, fieldHeight);
                phoneField.setBounds((width - fieldWidth) / 2, height / 3, fieldWidth, fieldHeight);
                phoneErrorLabel.setBounds((width - fieldWidth) / 2, height / 3 + 45, fieldWidth, 20);

                // 设置账号相关组件的位置和大小
                idLabel.setBounds((width - fieldWidth) / 2 - 100, height / 3 + 70, 80, fieldHeight);
                idField.setBounds((width - fieldWidth) / 2, height / 3 + 70, fieldWidth, fieldHeight);
                idErrorLabel.setBounds((width - fieldWidth) / 2, height / 3 + 115, fieldWidth, 20);

                // 设置密码相关组件的位置和大小
                passwordLabel.setBounds((width - fieldWidth) / 2 - 100, height / 3 + 140, 80, fieldHeight);
                passwordField.setBounds((width - fieldWidth) / 2, height / 3 + 140, fieldWidth, fieldHeight);
                passwordErrorLabel.setBounds((width - fieldWidth) / 2, height / 3 + 185, fieldWidth, 20);

                // 设置确认密码相关组件的位置和大小
                confirmPasswordLabel.setBounds((width - fieldWidth) / 2 - 100, height / 3 + 210, 100, fieldHeight);
                confirmPasswordField.setBounds((width - fieldWidth) / 2, height / 3 + 210, fieldWidth, fieldHeight);
                confirmPasswordErrorLabel.setBounds((width - fieldWidth) / 2, height / 3 + 255, fieldWidth, 20);

                // 设置注册按钮的位置和大小
                registerButton.setBounds((width - fieldWidth) / 2, height / 3 + 290, fieldWidth, 50);
            }
        });

        // 手机号输入验证
        phoneField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validateRegisterInput(phoneField, idField, passwordField, confirmPasswordField,
                        phoneErrorLabel, idErrorLabel, passwordErrorLabel, confirmPasswordErrorLabel,nicknameField,nicknameErrorLabel);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                validateRegisterInput(phoneField, idField, passwordField, confirmPasswordField,
                        phoneErrorLabel, idErrorLabel, passwordErrorLabel, confirmPasswordErrorLabel,nicknameField,nicknameErrorLabel);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validateRegisterInput(phoneField, idField, passwordField, confirmPasswordField,
                        phoneErrorLabel, idErrorLabel, passwordErrorLabel, confirmPasswordErrorLabel,nicknameField,nicknameErrorLabel);
            }
        });

        // 账号输入验证
        idField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validateRegisterInput(phoneField, idField, passwordField, confirmPasswordField,
                        phoneErrorLabel, idErrorLabel, passwordErrorLabel, confirmPasswordErrorLabel,nicknameField,nicknameErrorLabel);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                validateRegisterInput(phoneField, idField, passwordField, confirmPasswordField,
                        phoneErrorLabel, idErrorLabel, passwordErrorLabel, confirmPasswordErrorLabel,nicknameField,nicknameErrorLabel);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validateRegisterInput(phoneField, idField, passwordField, confirmPasswordField,
                        phoneErrorLabel, idErrorLabel, passwordErrorLabel, confirmPasswordErrorLabel,nicknameField,nicknameErrorLabel);
            }
        });

        // 密码输入验证
        passwordField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validateRegisterInput(phoneField, idField, passwordField, confirmPasswordField,
                        phoneErrorLabel, idErrorLabel, passwordErrorLabel, confirmPasswordErrorLabel,nicknameField,nicknameErrorLabel);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                validateRegisterInput(phoneField, idField, passwordField, confirmPasswordField,
                        phoneErrorLabel, idErrorLabel, passwordErrorLabel, confirmPasswordErrorLabel,nicknameField,nicknameErrorLabel);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validateRegisterInput(phoneField, idField, passwordField, confirmPasswordField,
                        phoneErrorLabel, idErrorLabel, passwordErrorLabel, confirmPasswordErrorLabel,nicknameField,nicknameErrorLabel);
            }
        });

        // 确认密码输入验证
        confirmPasswordField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validateRegisterInput(phoneField, idField, passwordField, confirmPasswordField,
                        phoneErrorLabel, idErrorLabel, passwordErrorLabel, confirmPasswordErrorLabel,nicknameField,nicknameErrorLabel);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                validateRegisterInput(phoneField, idField, passwordField, confirmPasswordField,
                        phoneErrorLabel, idErrorLabel, passwordErrorLabel, confirmPasswordErrorLabel,nicknameField,nicknameErrorLabel);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validateRegisterInput(phoneField, idField, passwordField, confirmPasswordField,
                        phoneErrorLabel, idErrorLabel, passwordErrorLabel, confirmPasswordErrorLabel,nicknameField,nicknameErrorLabel);
            }
        });

        // 注册按钮的事件监听器，点击注册按钮时执行注册逻辑
        registerButton.addActionListener(e -> {
            String phone = phoneField.getText().trim();
            String id = idField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();
            String confirmPassword = new String(confirmPasswordField.getPassword()).trim();

            if (validateRegisterInput(phoneField, idField, passwordField, confirmPasswordField, phoneErrorLabel,
                    idErrorLabel, passwordErrorLabel, confirmPasswordErrorLabel,nicknameField,nicknameErrorLabel)) {
                // 注册逻辑
                Insert_user insertUser=new Insert_user();
                insertUser.CreateNewUser(system.conn, nicknameField.getText().trim(),passwordField.getText().trim(),
                                            phoneField.getText().trim(), Integer.parseInt(idField.getText().trim()));
                JOptionPane.showMessageDialog(frame, "注册成功", "提示", JOptionPane.INFORMATION_MESSAGE);
                switchPanel("loginPanel");
            } else {
                JOptionPane.showMessageDialog(frame, "请检查您的输入", "错误", JOptionPane.ERROR_MESSAGE);
            }
        });

        // 返回按钮的事件监听器，点击返回按钮时切换到登录面板
        backButton.addActionListener(e -> switchPanel("loginPanel"));

        return panel;
    }

    // 更新 validateRegisterInput 方法以包含手机号的验证
    public boolean validateRegisterInput(JTextField phoneField, JTextField idField, JPasswordField passwordField,
                                         JPasswordField confirmPasswordField, JLabel phoneErrorLabel, JLabel idErrorLabel,
                                         JLabel passwordErrorLabel, JLabel confirmPasswordErrorLabel,JTextField nicknameField,
                                         JLabel nicknameErrorLabel) {
        // 获取输入框中的文本，并去除前后空格
        String phone = phoneField.getText().trim();
        String id = idField.getText().trim();
        String password = passwordField.getText().trim();
        String confirmPassword = confirmPasswordField.getText().trim();
        String nickname = nicknameField.getText().trim();

        // 初始假设所有输入框都有效
        boolean validPhone = true;
        boolean validId = true;
        boolean validPassword = true;
        boolean validConfirmPassword = true;

        // 昵称验证
        if (nickname.isEmpty()) {
            nicknameErrorLabel.setText("昵称不能为空");
            return false;
        } else {
            nicknameErrorLabel.setText("");
        }

        // 验证手机号（只有在手机号框不为空时才验证）
        if (!phone.isEmpty()) {
            validPhone = phone.matches("\\d{11}");
            phoneErrorLabel.setText(validPhone ? "" : "手机号格式不正确");
        } else {
            phoneErrorLabel.setText(""); // 手机号为空时不显示错误提示
        }

        Get_User getUser=new Get_User();//准备查询数据库中的用户

// 验证账号（只有在账号框不为空时才验证）
        if (!id.isEmpty()) {
            // 检查账号是否为数字
            if (!id.matches("\\d+")) {
                idErrorLabel.setText("账号仅支持数字");
            }
            // 检查账号长度是否至少为6位
            else if (id.length() < 6) {
                idErrorLabel.setText("账号至少为6位");
            } else if(getUser.checkUserID(system.conn, Integer.parseInt(id))){
                idErrorLabel.setText("已存在该账号");
            }else{
                idErrorLabel.setText(""); // 如果账号符合要求，清除错误提示
            }
        } else {
            idErrorLabel.setText(""); // 账号为空时不显示错误提示
        }

        // 验证密码（只有在密码框不为空时才验证）
        if (!password.isEmpty()) {
            validPassword = password.length() >= 6;
            passwordErrorLabel.setText(validPassword ? "" : "密码至少6位");
        } else {
            passwordErrorLabel.setText(""); // 密码为空时不显示错误提示
        }

        // 验证确认密码（只有在确认密码框不为空时才验证）
        if (!confirmPassword.isEmpty()) {
            validConfirmPassword = password.equals(confirmPassword);
            confirmPasswordErrorLabel.setText(validConfirmPassword ? "" : "密码和确认密码不一致");
        } else {
            confirmPasswordErrorLabel.setText(""); // 确认密码为空时不显示错误提示
        }

        // 返回所有验证结果的逻辑与，如果所有输入都有效，则返回true，否则返回false
        return validPhone && validId && validPassword && validConfirmPassword;
    }

    /**
     * 创建登录面板的方法，用于构建用户登录界面。
     * 该面板包含输入框、标签和按钮等组件，并提供输入验证和登录逻辑。
     *
     * @return 返回构建好的登录面板对象。
     */
    public JPanel createLoginPanel() {
        // 创建一个新的JPanel对象，设置布局管理器为null，以便手动设置组件位置和大小。
        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(240, 248, 255)); // 设置面板背景颜色

        // 创建标签和输入框组件
        JLabel titleLabel = new JLabel("登录界面", SwingConstants.CENTER);
        titleLabel.setFont(new Font("微软雅黑", Font.BOLD, 36)); // 设置标题字体样式

        JLabel idLabel = new JLabel("账号:");
        JLabel passwordLabel = new JLabel("密码:");
        JTextField idField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JLabel promptLabel = new JLabel("", SwingConstants.CENTER);
        promptLabel.setForeground(Color.RED); // 设置提示标签字体颜色为红色
        JButton loginButton = new JButton("登录");
        JButton registerButton = new JButton("注册");
        JButton recoverPasswordButton = new JButton("忘记密码");

        // 创建错误提示标签，并设置字体颜色为红色
        JLabel idErrorLabel = new JLabel("", SwingConstants.LEFT);
        JLabel passwordErrorLabel = new JLabel("", SwingConstants.LEFT);

        idErrorLabel.setForeground(Color.RED);
        passwordErrorLabel.setForeground(Color.RED);

        // 将组件添加到面板
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

        // 监听窗口大小和位置变化，动态设置控件大小和位置
        panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int width = panel.getWidth();
                int height = panel.getHeight();

                int fieldWidth = (int) (width * 0.4);
                int fieldHeight = 40;

                // 设置标题标签的位置和大小
                titleLabel.setBounds((width - fieldWidth) / 2, height / 8, fieldWidth, 50);

                // 设置账号相关组件的位置和大小
                idLabel.setBounds((width - fieldWidth) / 2 - 100, height / 3, 80, fieldHeight);
                idField.setBounds((width - fieldWidth) / 2, height / 3, fieldWidth, fieldHeight);
                idErrorLabel.setBounds((width - fieldWidth) / 2, height / 3 + 45, fieldWidth, 20);

                // 设置密码相关组件的位置和大小
                passwordLabel.setBounds((width - fieldWidth) / 2 - 100, height / 3 + 70, 80, fieldHeight);
                passwordField.setBounds((width - fieldWidth) / 2, height / 3 + 70, fieldWidth, fieldHeight);
                passwordErrorLabel.setBounds((width - fieldWidth) / 2, height / 3 + 115, fieldWidth, 20);

                // 设置提示标签的位置和大小
                promptLabel.setBounds((width - fieldWidth) / 2, height / 3 + 120, fieldWidth, 30);

                // 设置按钮的位置和大小
                int buttonWidth = fieldWidth / 2 - 20;
                loginButton.setBounds((width - fieldWidth) / 2, height / 3 + 180, buttonWidth, 50);
                registerButton.setBounds((width - fieldWidth) / 2 + buttonWidth + 40, height / 3 + 180, buttonWidth, 50);
                recoverPasswordButton.setBounds((width - fieldWidth) / 2, height / 3 + 250, fieldWidth, 30);
            }
        });

        // 实时验证账号输入
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

        // 实时验证密码输入
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

        // 登录按钮的事件监听器，点击登录按钮时执行登录逻辑
        loginButton.addActionListener(e -> {
            String id = idField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            // 执行登录验证
            Get_User getUser = new Get_User();
            int isValidLogin = getUser.checkUser(system.conn, Integer.parseInt(id),password);;

            if (isValidLogin==1) {
                // 登录成功，执行
                Get_User get_user=new Get_User();
                get_user.getUser(system.conn, Integer.parseInt(id),password,customer);
                QueryCheci queryCheci = new QueryCheci(system,customer); // 创建QueryCheci窗口
                frame.setVisible(false);
                queryCheci.setVisible(true); // 显示QueryCheci窗口

            } else if(isValidLogin==-1){
                // 登录失败，显示错误提示
                JOptionPane.showMessageDialog(frame, "账号不存在", "错误", JOptionPane.ERROR_MESSAGE);
            }else if(isValidLogin==-2){
                JOptionPane.showMessageDialog(frame, "密码错误", "错误", JOptionPane.ERROR_MESSAGE);
            }
        });

        // 注册按钮的事件监听器，点击注册按钮时切换到注册面板
        registerButton.addActionListener(e -> switchPanel("registerPanel"));
        // 忘记密码按钮的事件监听器，点击忘记密码按钮时切换到忘记密码面板
        recoverPasswordButton.addActionListener(e -> switchPanel("recoverPasswordPanel"));

        return panel;
    }
    public void validateLoginInput(JTextField idField, JPasswordField passwordField,
                                   JLabel idErrorLabel, JLabel passwordErrorLabel) {
        String id = idField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        // 初始化错误标签为空
        idErrorLabel.setText("");
        passwordErrorLabel.setText("");

        boolean validId = true;
        boolean validPassword = true;

        // 检查账号是否为空
        if (id.isEmpty()) {
            idErrorLabel.setText("");
            validId = false;
        } else {
            // 账号需要是数字且长度>=6
            if (!id.matches("\\d+")) {  // 使用正则表达式检查账号是否为数字
                idErrorLabel.setText("账号仅支持数字");
                validId = false;
            } else if (id.length() < 6) {
                idErrorLabel.setText("账号不能小于六位");
                validId = false;
            }
        }

        // 检查密码是否为空
        if (password.isEmpty()) {
            passwordErrorLabel.setText("");
            validPassword = false;
        } else if (password.length() < 6) {
            passwordErrorLabel.setText("密码不能小于六位");
            validPassword = false;
        }

        // 只有账号和密码都有效时，才返回有效的输入
    }

    /**
     * 创建找回密码面板的方法，用于构建用户找回密码的界面。
     * 该面板包含输入框、标签和按钮等组件，并提供输入验证和找回密码逻辑。
     *
     * @return 返回构建好的找回密码面板对象。
     */
    public JPanel createRecoverPasswordPanel() {
        // 创建一个新的JPanel对象，设置布局管理器为null，以便手动设置组件位置和大小。
        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(255, 250, 240)); // 设置面板背景颜色

        // 创建标题标签
        JLabel titleLabel = new JLabel("找回密码", SwingConstants.CENTER);
        titleLabel.setFont(new Font("微软雅黑", Font.BOLD, 36)); // 设置标题字体样式

        // 创建输入框和标签
        JLabel phoneLabel = new JLabel("手机号:");
        JLabel codeLabel = new JLabel("验证码:");
        JLabel passwordLabel = new JLabel("新密码:");
        JLabel confirmPasswordLabel = new JLabel("确认密码:");
        JTextField phoneField = new JTextField();
        JTextField codeField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JPasswordField confirmPasswordField = new JPasswordField();

        // 创建按钮
        JButton sendCodeButton = new JButton("发送验证码");
        JButton confirmButton = new JButton("确认修改");

        // 创建错误提示标签
        JLabel phoneErrorLabel = new JLabel("", SwingConstants.LEFT);
        JLabel codeErrorLabel = new JLabel("", SwingConstants.LEFT);
        JLabel passwordErrorLabel = new JLabel("", SwingConstants.LEFT);
        JLabel confirmPasswordErrorLabel = new JLabel("", SwingConstants.LEFT);

        // 设置错误提示标签字体颜色为红色
        phoneErrorLabel.setForeground(Color.RED);
        codeErrorLabel.setForeground(Color.RED);
        passwordErrorLabel.setForeground(Color.RED);
        confirmPasswordErrorLabel.setForeground(Color.RED);

        // 将组件添加到面板
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

        // 创建返回按钮，并添加到面板
        JButton backButton = new JButton("返回");
        panel.add(backButton);
        backButton.setBounds(10, 10, 80, 30); // 设置返回按钮的位置和大小

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

        // 实时验证手机号是否为11位数字
        phoneField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validatePhoneInput(phoneField, phoneErrorLabel);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                validatePhoneInput(phoneField, phoneErrorLabel);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validatePhoneInput(phoneField, phoneErrorLabel);
            }
        });

        // 实时验证新密码和确认密码是否一致
        passwordField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validatePasswordInput(passwordField, confirmPasswordField, passwordErrorLabel, confirmPasswordErrorLabel);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                validatePasswordInput(passwordField, confirmPasswordField, passwordErrorLabel, confirmPasswordErrorLabel);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validatePasswordInput(passwordField, confirmPasswordField, passwordErrorLabel, confirmPasswordErrorLabel);
            }
        });

        confirmPasswordField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validatePasswordInput(passwordField, confirmPasswordField, passwordErrorLabel, confirmPasswordErrorLabel);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                validatePasswordInput(passwordField, confirmPasswordField, passwordErrorLabel, confirmPasswordErrorLabel);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validatePasswordInput(passwordField, confirmPasswordField, passwordErrorLabel, confirmPasswordErrorLabel);
            }
        });

        // 发送验证码按钮的事件监听器
        sendCodeButton.addActionListener(e -> {
            String phone = phoneField.getText().trim();
            if (phone.isEmpty() || !phone.matches("\\d{11}")) {
                phoneErrorLabel.setText("请输入有效的手机号");
                return;
            }
            String generatedCode = generateVerificationCode(); // 假设这是生成验证码的方法
            JOptionPane.showMessageDialog(frame, "验证码已发送到您的手机", "提示", JOptionPane.INFORMATION_MESSAGE);
            phoneErrorLabel.setText("");
        });

        // 确认修改密码按钮的事件监听器
        confirmButton.addActionListener(e -> {
            String code = codeField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();
            String confirmPassword = new String(confirmPasswordField.getPassword()).trim();
            if (!code.equals(generatedCode)) {
                codeErrorLabel.setText("验证码错误");
            } else if (!password.equals(confirmPassword)) {
                passwordErrorLabel.setText("两次密码不一致");
            } else {
                JOptionPane.showMessageDialog(frame, "密码修改成功", "提示", JOptionPane.INFORMATION_MESSAGE);
                switchPanel("loginPanel"); // 密码修改成功后跳转到登录面板
            }
        });

        // 返回按钮的事件监听器，点击返回按钮时切换到登录面板
        backButton.addActionListener(e -> switchPanel("loginPanel"));

        return panel;
    }

    /**
     * 验证手机号输入是否为11位数字的方法。
     *
     * @param phoneField 手机号输入框
     * @param phoneErrorLabel 手机号错误提示标签
     */
    public void validatePhoneInput(JTextField phoneField, JLabel phoneErrorLabel) {
        String phone = phoneField.getText().trim();
        boolean validPhone = phone.isEmpty() || phone.matches("\\d{11}");
        phoneErrorLabel.setText(validPhone ? "" : "手机号格式不正确");
    }

    /**
     * 验证新密码和确认密码是否一致的方法。
     *
     * @param passwordField 新密码输入框
     * @param confirmPasswordField 确认密码输入框
     * @param passwordErrorLabel 新密码错误提示标签
     * @param confirmPasswordErrorLabel 确认密码错误提示标签
     */
    public void validatePasswordInput(JPasswordField passwordField, JPasswordField confirmPasswordField,
                                      JLabel passwordErrorLabel, JLabel confirmPasswordErrorLabel) {
        String password = new String(passwordField.getPassword()).trim();
        String confirmPassword = new String(confirmPasswordField.getPassword()).trim();
        boolean validPassword = password.equals(confirmPassword);
        passwordErrorLabel.setText(validPassword ? "" : "密码不能为空");
        confirmPasswordErrorLabel.setText(validPassword ? "" : "密码和确认密码不一致");
    }

    public String generateVerificationCode() {
        Random rand = new Random();
        int code = rand.nextInt(123456);
        return String.format("%06d", code
        ); // 保证6位数字
    }

    public void switchPanel(String panelName) {
        CardLayout layout = (CardLayout) frame.getContentPane().getLayout();
        layout.show(frame.getContentPane(), panelName);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginRegisterUI::new);
    }
}