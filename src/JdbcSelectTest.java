
import java.sql.*;  // Using 'Connection', 'Statement' and 'ResultSet' classes in java.sql package
import java.util.logging.Level;
import java.util.logging.Logger;

public class JdbcSelectTest {   // Save as "JdbcSelectTest.java"

    public static void main(String[] args) {
        try {
            
                                     // The format is: "jdbc:mysql://hostname:port/databaseName", "username", "password"
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbHappyNest?", "root", "ebisonovsekonem");
            Statement stmt = c.createStatement();
            String command;
            ResultSet result;
            command = "select * from suppliers";

            result = stmt.executeQuery(command);

            /* 
            create table suppliers (
            supplierID int,
            supplierName varchar(30),
            supplierDesc varchar(450),
            supplierEntryDate bigint,
            primary key(supplierID)); 
             */
            
            while (result.next()) {
                System.out.println(
                        result.getInt("supplierID")
                        + " | "
                        + result.getString("supplierName")
                        + " | "
                        + result.getString("supplierDesc")
                        + " | "
                        + result.getLong("supplierEntryDate"));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
