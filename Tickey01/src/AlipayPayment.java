// 支付宝支付实现
public class AlipayPayment extends Payment {
    @Override
    public boolean pay(double amount) {
        System.out.println("使用支付宝支付，金额: " + amount);
        return true;
    }
}
