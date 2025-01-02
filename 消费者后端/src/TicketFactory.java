// 票务工厂类
public class TicketFactory {
    public static Ticket createTicket(String type, String name, double price, String ticketType, int quantity) {
        if ("highspeedrail".equals(type)) {
            return new HighSpeedRailTicket(name, price, ticketType, quantity);
        }
        // 可以扩展其他票类型
        return null;
    }
}
