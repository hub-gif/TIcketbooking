package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
public class Main {

        public static void main(String[] args) {
            database database=new database();
            Connection conn=database.ConnectDatabase();
            /*Insert_user use=new Insert_user();
            use.CreateNewUser(conn,"TeseUser4",
                    "Test1","000004",100004);
            创造用户测试成功，可以使用*/

            /*Get_User Getinf=new Get_User();
            Getinf.GetUserDate(conn);
            返回用户信息测试成功，可以打印*/

            /*Update_User Update=new Update_User();
            Update.Update_database(conn,100001,
                    "NewUser1","NewTest1","New7355608");
                更新用户信息测试成功，
                */
            /*Insert_CheCi CheCi=new Insert_CheCi();
            CheCi.CreateCheCi(conn,"G6063");
            //创造车次成功



            Update_Checi UpdateCheci=new Update_Checi();
            UpdateCheci.Update_database(conn,"G6063","广州南","深圳北",
                    "2024.12.20","6:50","10:50","广州东、虎门",240.4,
                    "一等座","靠窗");
               //  修改车次信息测试成功，可以使用。
                */


            /*Get_Checi infCheci=new Get_Checi();
            infCheci.Get_CheCi(conn);
            车次信息打印成功
               */
            /*
            Delete_user Del=new Delete_user();
            Del.Delete_information(conn,100004);
            测试成功，可以删除用户信息。
            */

            /*测试成功，可以删除车次信息
            Delete_Checi DelCheci=new Delete_Checi();
            DelCheci.Delete_information(conn,"G6063");
            */


            /*Insert_Ticker Tic=new Insert_Ticker();
            Tic.CreateCheCi(conn,"G6063");
            测试成功，可以插入票价信息，注意票的RailID要与车次的railID相等
            */

           /* Update_Ticket Tic1=new Update_Ticket();
            Tic1.Update_database(conn,"A1001","G6063","广州东、虎门","深圳北",
                    "广州南","靠窗","一等座",240.2);
            测试成功，可以插入票据信息
            */


            /*Get_Ticket Tick=new Get_Ticket();
            Tick.Get_Ticket(conn);
            测试成功，可以输出数据
            */

            /*Delete_Ticket DTic=new Delete_Ticket();
            DTic.Delete_information(conn,"A1001");
            测试成功，可以删除
             */
        }



}