// 账户类
public abstract class Account {
    protected String username;
    protected String password;
    protected String accountType; // 账户类型，消费者或管理员

    public Account(String username, String password, String accountType) {
        this.username = username;
        this.password = password;
        this.accountType = accountType;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getAccountType() {
        return accountType;
    }
}
