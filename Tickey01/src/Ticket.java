// 抽象类 Ticket
public abstract class Ticket {
    String name;
    double price;
    String type;
    int totalQuantity;
    int remainingQuantity;

    public Ticket(String name, double price, String type, int totalQuantity) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.totalQuantity = totalQuantity;
        this.remainingQuantity = totalQuantity;
    }

    public abstract boolean bookTicket(); // 预定票
    public abstract String getTicketInfo(); // 票信息
}
