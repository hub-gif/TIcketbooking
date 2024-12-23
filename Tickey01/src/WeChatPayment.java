// 微信支付实现
public class WeChatPayment extends Payment {
    @Override
    public boolean pay(double amount) {
        System.out.println("使用微信支付，金额: " + amount);
        return true;
    }
}
