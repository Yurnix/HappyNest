
import java.sql.*;  
import java.util.logging.Level;
import java.util.logging.Logger;

public class JdbcSelectTest {   

    public static void main(String[] args) {
        try {
            String a;
            a = "made some changes";
                                     // The format is: "jdbc:mysql://hostname:port/databaseName", "username", "password"
            //Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbHappyNest?", "root", "ebisonovsekonem");
            
            /*
            Connection cloud = DriverManager.getConnection(
                    "jdbc:mysql://fdb28.awardspace.net:3306/3783121_dbhappynest",
                    "3783121_dbhappynest",
                    "Ebisonovsekonem1!"
            );
            */
            
            Connection cloud = DriverManager.getConnection(
                    "jdbc:mysql://sql11.freemysqlhosting.net:3306/sql11415364",
                    "sql11415364",
                    "fWjVyJaAt9"
            );
            
            
            //Statement stmt = c.createStatement();
            
            Statement c_stmt = cloud.createStatement();
            
            String command;
            /*
            command = "insert into products values ("
                    + "1,"
                    + "Coca Cola,"
                    + "\"En mia malakia j misi \n 8=D\" , "
                    + "1919199191,"
                    + "10,"
                    + "0.3,"
                    + "13,"
                    + "1,"
                    + "1)";
            */
            
            //supplierID 	supplierName 	supplierDesc 	supplierEntryDate 
            command = "insert into suppliers values ("
                    + "1,"
                    + "\"Yuri\","
                    + "\"The desc works\","
                    + "20002929221)";
            System.out.println(command);
            ResultSet result;
            c_stmt.executeUpdate(command);
            //System.out.print("After execution : " + result.toString());
            command = "select supplierName from suppliers";
            result = c_stmt.executeQuery(command);
            /* 
            create table suppliers (
            supplierID int,
            supplierName varchar(30),
            supplierDesc varchar(450),
            supplierEntryDate bigint,
            primary key(supplierID)); 
             */
            
            while (result.next()) {
                System.out.println(result.getString("supplierName"));
                /*System.out.println(
                        result.getInt("supplierID")
                        + " | "
                        + result.getString("supplierName")
                        + " | "
                        + result.getString("supplierDesc")
                        + " | "
                        + result.getLong("supplierEntryDate"));*/
            }

        } catch (SQLException ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
    }
}
