// 具体票类：高铁票
public class HighSpeedRailTicket extends Ticket {
    public HighSpeedRailTicket(String name, double price, String type, int totalQuantity) {
        super(name, price, type, totalQuantity);
    }

    @Override
    public boolean bookTicket() {
        if (remainingQuantity > 0) {
            remainingQuantity--;
            return true;
        }
        return false;
    }

    @Override
    public String getTicketInfo() {
        return "高铁票: " + name + ", 价格: " + price + ", 剩余票数: " + remainingQuantity;
    }
}
