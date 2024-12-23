import java.util.UUID;

// 订单类
public class Order {
    private String orderId;
    private Ticket ticket;
    private String status; // 状态: "未支付", "已支付", "已取消"

    public Order(Ticket ticket) {
        this.orderId = "ORD" + UUID.randomUUID().toString().substring(0, 8);
        this.ticket = ticket;
        this.status = "未支付";
    }

    public String getOrderId() {
        return orderId;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void cancelOrder() {
        if ("未支付".equals(status)) {
            this.status = "已取消";
            ticket.remainingQuantity++;
            System.out.println("订单已取消，票务数量已恢复。");
        } else {
            System.out.println("无法取消订单，订单状态: " + status);
        }
    }

    @Override
    public String toString() {
        return "订单号: " + orderId + ", 票务: " + ticket.getTicketInfo() + ", 状态: " + status;
    }
}
