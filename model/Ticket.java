package model;

public class Ticket {
    String level;       //票的等级，如一等座
    Double price;       //价格
    String Checi_ID;     //车次ID、主键
    String location;    //用户位置，比如靠窗、过道
    String startStation;//始发站
    String endingStation;//终点站
    String way;             //途径站点
    String tick_ID;     //票的ID,具有唯一性
    int customer_ID;    //改票所属用户
    int number;
    int checiNumber;
    public Ticket(Ticket other) {    // 深度拷贝构造函数
        if (other != null) {
            this.customer_ID= other.customer_ID;
            this.level = other.level;
            this.price = other.price;
            this.Checi_ID = other.Checi_ID;
            this.location = other.location;
            this.startStation = other.startStation;
            this.endingStation = other.endingStation;
            this.way = other.way;
            this.tick_ID = other.tick_ID;
            this.number=other.number;
            this.checiNumber=other.checiNumber;
        }
    }
    public Ticket() {

    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCheci_ID() {
        return Checi_ID;
    }

    public void setCheci_ID(String railId) {
        this.Checi_ID = railId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStartStation() {
        return startStation;
    }

    public void setStartStation(String startStation) {
        this.startStation = startStation;
    }

    public String getEndingStation() {
        return endingStation;
    }

    public void setEndingStation(String endingStation) {
        this.endingStation = endingStation;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public String getTickId() {
        return tick_ID;
    }
    public void setTickId(String tickId) {
        this.tick_ID = tickId;
    }
    public int getCustomer_ID(){return customer_ID;}
    public void setCustomer_ID(int customer_ID){this.customer_ID=customer_ID;}
    public int getNumber() {return number;}
    public void setNumber(int number) {this.number = number;}
    public int getCheciNumber() {return checiNumber;}
    public void setCheciNumber(int checiNumber) {this.checiNumber = checiNumber;}
}
