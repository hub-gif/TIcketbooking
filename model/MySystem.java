package model;
import database.database;
import java.sql.Connection;

public class MySystem {
    public database database;
    public Connection conn;
    public MySystem(){
        this.database=new database();
        this.conn=database.ConnectDatabase();
    }

}
