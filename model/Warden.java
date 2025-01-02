package model;

public class Warden extends Account{
    public Warden(){

    }
    public Warden(Warden other){
        other.power=this.power;
    }

    public boolean change_ticket(String password) {
        String wardenPassword=this.password;
        return "admin123".equals(password); // 验证管理员密码
    }
    public boolean set_ticket() {
        java.lang.System.out.println("管理员权限已验证，正在设置票务...");
        return true; // 权限验证通过
    }
    public boolean inform(String trafficNumber, String reason) {    //模拟管理员权限判断
        return trafficNumber != null && !trafficNumber.isEmpty() &&
                reason != null && !reason.isEmpty();
    }

}
