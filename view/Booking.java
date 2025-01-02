package view;
import database.Get_Checi;
import database.Get_Ticket;
import model.Checi;
import model.Customer;
import model.MySystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Booking extends JDialog {
    MySystem system;
    Customer customer;
    Checi checi;
    String checiID;

    JTextField startPlaceField;
    JTextField endPlaceField;
    JTextField dateField;
    JTextField departTimeField;
    JTextField arriveTimeField;
    JTextField trainNumberField;
    JTextField trainTypeField;
    JTextField viaStationField; // 途径地文本框
    // 添加三个文本框变量来显示剩余座位数量
    JTextField businessSeatField;
    JTextField firstSeatField;  //头等
    JTextField secondSeatField;
    JRadioButton businessSeatButton;
    JRadioButton firstSeatButton;
    JRadioButton secondSeatButton;
    ButtonGroup seatClassGroup;

    JLabel priceLabel;

    public Booking(JFrame parent, MySystem system, Customer customer, String checiID) {
        super(parent, "订票", true);
        this.system = system;
        this.customer = customer;
        checi = new Checi();
        Get_Checi getCheci = new Get_Checi();
        getCheci.Get_CheCi(system.conn, checiID, checi);
        this.checiID=checiID;

        setSize(800, 600);
        setLocationRelativeTo(parent);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10); // 设置组件之间的间距

        // 从customer对象中获取信息并设置到对应的文本框中
        startPlaceField = new JTextField(checi.getStarStation());
        startPlaceField.setEditable(false);
        viaStationField = new JTextField(checi.getWay());
        viaStationField.setEditable(false);
        endPlaceField = new JTextField(checi.getEndingStation());
        endPlaceField.setEditable(false);
        dateField = new JTextField(checi.getData());
        dateField.setEditable(false);
        departTimeField = new JTextField(checi.getStarTime());
        departTimeField.setEditable(false);
        arriveTimeField = new JTextField(checi.getEndTime());
        arriveTimeField.setEditable(false);
        trainNumberField = new JTextField(checi.getID());
        trainNumberField.setEditable(false);
        trainTypeField = new JTextField(checi.getType());
        trainTypeField.setEditable(false);
        businessSeatField = new JTextField("0");
        firstSeatField = new JTextField("0");
        secondSeatField = new JTextField("0");

        // 始发地、目的地、日期、出发时间、到站时间、列车号、列车类型标签
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("始发地:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(startPlaceField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("途径地:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(viaStationField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("目的地:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(endPlaceField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("日期:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(dateField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(new JLabel("出发时间:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(departTimeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(new JLabel("到站时间:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 5;
        panel.add(arriveTimeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(new JLabel("列车号:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 6;
        panel.add(trainNumberField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        panel.add(new JLabel("列车类型:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 7;
        panel.add(trainTypeField, gbc);

        // 创建一个面板来显示座位类型选择
        JPanel seatClassPanel = new JPanel();
        seatClassPanel.setLayout(new GridLayout(4, 2, 10, 10));  // 4行2列的网格布局，组件之间的间距为10

        // 创建座位选择按钮
        businessSeatButton = new JRadioButton("商务座");
        firstSeatButton = new JRadioButton("一等座");
        secondSeatButton = new JRadioButton("二等座");

        // 创建ButtonGroup将按钮分组
        ButtonGroup seatClassGroup = new ButtonGroup();
        seatClassGroup.add(businessSeatButton);
        seatClassGroup.add(firstSeatButton);
        seatClassGroup.add(secondSeatButton);

        // 创建座位剩余数量文本框
        businessSeatField = new JTextField(updateTicketNumber(0),5);
        firstSeatField = new JTextField(updateTicketNumber(1),5);
        secondSeatField = new JTextField(updateTicketNumber(2),5);

        // 设置文本框为只读模式
        businessSeatField.setEditable(false);
        firstSeatField.setEditable(false);
        secondSeatField.setEditable(false);

        // 将组件添加到面板中

        seatClassPanel.add(businessSeatButton); // 第一行第一个
        seatClassPanel.add(new JLabel("剩余:"));  // 第一行第二个
        seatClassPanel.add(businessSeatField);   // 第一行第三个


        seatClassPanel.add(firstSeatButton);    // 第二行第一个
        seatClassPanel.add(new JLabel("剩余:"));  // 第二行第二个
        seatClassPanel.add(firstSeatField);     // 第二行第三个

        seatClassPanel.add(secondSeatButton);   // 第三行第一个
        seatClassPanel.add(new JLabel("剩余:"));  // 第三行第二个
        seatClassPanel.add(secondSeatField);    // 第三行第三个

        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        panel.add(seatClassPanel, gbc);

        // 显示价格
        gbc.gridx = 0;
        gbc.gridy = 11;
        gbc.gridwidth = 2;
        panel.add(new JLabel("价格:"), gbc);

        priceLabel = new JLabel("请选择座位类型");
        gbc.gridx = 1;
        gbc.gridy = 11;
        panel.add(priceLabel, gbc);

        // 确认订购按钮
        JButton confirmButton = new JButton("确认订购");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 在此可以执行订票操作
                int seatType = 0;
                if (businessSeatButton.isSelected()) {
                    seatType=0;
                } else if (firstSeatButton.isSelected()) {
                    seatType=1;
                } else if (secondSeatButton.isSelected()) {
                    seatType=2;
                }
                confirmBooking(seatType,checiID);

            }
        });
        gbc.gridx = 0;
        gbc.gridy = 12;
        gbc.gridwidth = 2;
        panel.add(confirmButton, gbc);

        add(panel, BorderLayout.CENTER);

        //按钮的触发事件
        businessSeatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatePrice(checiID);
            }
        });
        firstSeatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatePrice(checiID);
            }
        });
        secondSeatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatePrice(checiID);
            }
        });

    }

    // 根据选择的座位等级和类型更新价格
    public void updatePrice(String checiID) {
        Get_Ticket getTicket = new Get_Ticket();
        double price = 0.0;
        if (businessSeatButton.isSelected()) {
            price += getTicket.getTypePrice(system.conn,0,checiID);  // 商务座价格
        } else if (firstSeatButton.isSelected()) {
            price += getTicket.getTypePrice(system.conn,1,checiID);  // 一等座价格
        } else if (secondSeatButton.isSelected()) {
            price += getTicket.getTypePrice(system.conn,2,checiID);  // 二等座价格
        }

        if(price==0){
            priceLabel.setText("暂无");
        }else{
            priceLabel.setText("￥" + price);
        }
    }
    public String updateTicketNumber(int seatType){
        Get_Ticket getTicket=new Get_Ticket();  //用于找票数
        int a=getTicket.getTypeTicket(system.conn,seatType,checiID);
        if(a==0){
            return "暂无";
        }
        return String.valueOf(a);
    }

    // 执行确认订票操作
    void confirmBooking(int seatType,String checiID)  {
        // 这里可以处理实际的订票逻辑
        Get_Ticket getTicket =new Get_Ticket();
        int ticketID=getTicket.searchTicket(system.conn,seatType,checiID);
        getTicket.BookingTicket(system.conn,customer,ticketID);
        JOptionPane.showMessageDialog(this, "订票成功！");
        dispose();
    }

    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
        // 初始时更新价格
        updatePrice(checiID);
    }
}
