package model;

public class Customer extends Account{
    String name;    //用户名
    String phoneNumber;
    String information;
    public Customer(){
    }
    public Customer(Customer other) {    // 拷贝构造函数
        // 直接赋值基本类型或 String 类型的字段
        this.name = other.name;
        this.password = other.password;
        this.phoneNumber = other.phoneNumber;
        this.ID = other.ID;
        this.information=other.information;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public int getID() {
        return ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }
    public String getInformation(){return information;}
    public void setInformation(String information) {this.information = information;}
}
