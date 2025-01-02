import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 创建票务
        Ticket ticket1 = TicketFactory.createTicket("highspeedrail", "北京-上海高铁", 500, "硬座", 100);
        Ticket ticket2 = TicketFactory.createTicket("highspeedrail", "广州-深圳高铁", 300, "软座", 50);

        // 创建用户账户
        CustomerAccount customerAccount = new CustomerAccount("user1", "password");

        // 模拟用户操作
        while (true) {
            System.out.println("选择操作: 1. 查询票务 2. 预定票务 3. 创建订单并支付 4. 查看订单 5. 退出");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    customerAccount.queryTickets();
                    break;
                case 2:
                    System.out.println("选择票务: 1. 北京-上海高铁 2. 广州-深圳高铁");
                    int ticketChoice = scanner.nextInt();
                    if (ticketChoice == 1) {
                        customerAccount.bookTicket(ticket1);
                    } else if (ticketChoice == 2) {
                        customerAccount.bookTicket(ticket2);
                    }
                    break;
                case 3:
                    System.out.println("选择创建订单的票务: ");
                    customerAccount.queryTickets(); // 显示已预定的票务
                    if (!customerAccount.getTickets().isEmpty()) {
                        System.out.println("选择订单的票务编号 (0 或 1): ");
                        int ticketIndex = scanner.nextInt();
                        if (ticketIndex >= 0 && ticketIndex < customerAccount.getTickets().size()) {
                            customerAccount.createOrder(customerAccount.getTickets().get(ticketIndex));
                        } else {
                            System.out.println("无效的票务选择！");
                        }
                    }
                    break;
                case 4:
                    customerAccount.viewOrders();
                    if (!customerAccount.getOrders().isEmpty()) {
                        System.out.println("选择支付订单编号 (0 或 1): ");
                        int orderIndex = scanner.nextInt();
                        if (orderIndex >= 0 && orderIndex < customerAccount.getOrders().size()) {
                            System.out.println("选择支付方式: 1. 微信支付 2. 支付宝");
                            int payChoice = scanner.nextInt();
                            Payment payment = (payChoice == 1) ? PaymentFactory.createPayment("wechat") : PaymentFactory.createPayment("alipay");
                            customerAccount.payOrder(orderIndex, payment);
                        } else {
                            System.out.println("无效的订单选择！");
                        }
                    }
                    break;
                case 5:
                    System.out.println("退出系统");
                    return;
                default:
                    System.out.println("无效选择");
            }
        }
    }
}
