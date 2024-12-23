import java.util.ArrayList;
import java.util.List;

// CustomerAccount 类继承自 Account 类，代表一个客户账户
public class CustomerAccount extends Account {
    // 存储客户预定的票务信息
    private List<Ticket> tickets = new ArrayList<>();
    // 存储客户的订单信息
    private List<Order> orders = new ArrayList<>();
    // 存储所有可用的航班信息
    private List<Flight> flights = new ArrayList<>();

    // 构造函数，初始化客户账户
    public CustomerAccount(String username, String password) {
        super(username, password, "consumer");
    }

    // 查询客户预定的所有票务信息
    public void queryTickets() {
        if (tickets.isEmpty()) {
            System.out.println("您没有预定任何票务！");
        } else {
            // 遍历并打印每张票的信息
            tickets.forEach(ticket -> System.out.println(ticket.getTicketInfo()));
        }
    }

    // 预定一张票务
    public void bookTicket(Ticket ticket) {
        // 尝试预定票务，如果成功则添加到 tickets 列表中
        if (ticket.bookTicket()) {
            tickets.add(ticket);
            System.out.println("成功预定票: " + ticket.getTicketInfo());
        } else {
            System.out.println("票已售罄！");
        }
    }

    // 创建一个订单，前提是客户已经预定了票
    public void createOrder(Ticket ticket) {
        // 检查 ticket 是否在 tickets 列表中
        if (tickets.contains(ticket)) {
            Order order = new Order(ticket);
            orders.add(order);
            System.out.println("成功创建订单: " + order);
        } else {
            System.out.println("您没有预定该票，无法创建订单！");
        }
    }

    // 查看客户所有的订单
    public void viewOrders() {
        if (orders.isEmpty()) {
            System.out.println("您没有任何订单！");
        } else {
            // 遍历并打印每个订单的信息
            orders.forEach(order -> System.out.println(order));
        }
    }

    // 支付指定的订单
    public void payOrder(int orderIndex, Payment payment) {
        // 检查订单索引是否有效
        if (orderIndex >= 0 && orderIndex < orders.size()) {
            Order order = orders.get(orderIndex);
            // 检查订单状态是否为“未支付”
            if ("未支付".equals(order.getStatus())) {
                // 尝试支付订单
                if (payment.pay(order.getTicket().price)) {
                    order.setStatus("已支付");
                    System.out.println("支付成功，订单状态已更新！");
                }
            } else {
                System.out.println("订单无法支付，状态: " + order.getStatus());
            }
        } else {
            System.out.println("无效的订单选择！");
        }
    }

    // 提供对外访问订单列表的方法
    public List<Order> getOrders() {
        return orders;
    }

    // 提供对外访问已预定票务的方法
    public List<Ticket> getTickets() {
        return tickets;
    }

    // 查询特定出发点和目的地的所有航班（车次）
    public void queryFlights(String departure, String destination) {
        if (flights.isEmpty()) {
            System.out.println("没有找到任何航班信息！");
        } else {
            System.out.println("从 " + departure + " 到 " + destination + " 的所有航班：");
            for (Flight flight : flights) {
                // 筛选出发点和目的地匹配的航班(车次)并打印信息
                if (flight.getDeparture().equals(departure) && flight.getDestination().equals(destination)) {
                    System.out.println(flight);
                }
            }
        }
    }
}
