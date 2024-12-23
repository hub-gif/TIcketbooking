// 支付工厂类
public class PaymentFactory {
    public static Payment createPayment(String method) {
        if ("wechat".equals(method)) {
            return new WeChatPayment();
        } else if ("alipay".equals(method)) {
            return new AlipayPayment();
        }
        return null;
    }
}
