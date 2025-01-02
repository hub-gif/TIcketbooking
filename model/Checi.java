package model;

public class Checi {
    String ID;      //列车ID
    String starStation;
    String endingStation;
    String data;    //发车日期
    String starTime;
    String endTime;
    String way;
    int ticketNumber;
    double price;
    String type;
    int number;
    public Checi(){

    }
    public Checi(Checi other) {     // 拷贝构造函数
        // 直接赋值，因为所有字段都是 String 类型，String 是不可变的
        this.ID = other.ID;
        this.starStation = other.starStation;
        this.endingStation = other.endingStation;
        this.data = other.data;
        this.starTime = other.starTime;
        this.endTime = other.endTime;
        this.way = other.way;
        this.ticketNumber= other.ticketNumber;
        this.price= other.price;
        this.type=other.type;
        this.number=other.number;
    }
    // Getter 和 Setter 方法
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getStarStation() {
        return starStation;
    }

    public void setStarStation(String starStation) {
        this.starStation = starStation;
    }

    public String getEndingStation() {
        return endingStation;
    }

    public void setEndingStation(String endingStation) {
        this.endingStation = endingStation;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getStarTime() {
        return starTime;
    }

    public void setStarTime(String starTime) {
        this.starTime = starTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public int getTicketNumber(){return ticketNumber;}

    public void setTicketNumber(int ticketNumber){this.ticketNumber=ticketNumber;}

    public double getPrice(){return this.price;}
    public void setPrice(double price){this.price=price;}
    public String getType(){return this.type;}
    public void setType(String type){this.type=type;}
    public int getNumber() {return number;}
    public void setNumber(int number) {this.number = number;}
}
