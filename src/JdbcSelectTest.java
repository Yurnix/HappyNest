
import java.sql.*;  // Using 'Connection', 'Statement' and 'ResultSet' classes in java.sql package
import java.util.logging.Level;
import java.util.logging.Logger;
//some changes

//EDO EGRAPSE O POUYAS
public class JdbcSelectTest {   // Save as "JdbcSelectTest.java"

    public static void main(String[] args) {
        try {
            String a;
            a = "made some changes";
                                     // The format is: "jdbc:mysql://hostname:port/databaseName", "username", "password"
            //Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbHappyNest?", "root", "ebisonovsekonem");
            
            Connection cloud = DriverManager.getConnection(
                    "jdbc:mysql://fdb28.awardspace.net:3306/3783121_dbhappynest",
                    "3783121_dbhappynest",
                    "Ebisonovsekonem1!");
            
            
            //Statement stmt = c.createStatement();
            Statement c_stmt = cloud.createStatement();
            
            String command;
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
            
       
            
            
            ResultSet result;
            
            result = c_stmt.executeQuery(command);
            System.out.print("After execution : " + result.toString());
            command = "select productName from suppliers";
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
                System.out.println(result.getString("productName"));
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
