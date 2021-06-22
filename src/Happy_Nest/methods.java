
package Happy_Nest;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import java.awt.Component;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.swing.JOptionPane;
import java.sql.*;
import javax.swing.JLabel;


public abstract class methods {
    public static boolean importFromCSV(String path) {
        initLocalConnection();
        
        try {
            //Reader reader;
            command = "truncate tables income, outcome, expencesIncome, expencesOutcome, products, categories, suppliers";
            stmt.execute(command);
            CryptoCSV.resetCounter();
            Reader reader = Files.newBufferedReader(Paths.get(path + "1db.csv"));
            CSVReader csvReader = new CSVReader(reader);
            String line[];
            while ((line = csvReader.readNext()) != null) {
                command = "insert into income values(" +
                        CryptoCSV.decrypt(line[0]) + ", " +
                        CryptoCSV.decrypt(line[1]) + ", " +
                        CryptoCSV.decrypt(line[2]) + ", " +
                        CryptoCSV.decrypt(line[3]) + ", '" +
                        CryptoCSV.decrypt(line[4]) + "', '" +
                        CryptoCSV.decrypt(line[5]) + "', " +
                        CryptoCSV.decrypt(line[6]) + ", " +
                        CryptoCSV.decrypt(line[7]) + ")";
                stmt.execute(command);
                System.out.println(command);
            }
            csvReader.close();
            reader.close();
            
            CryptoCSV.resetCounter();
            reader = Files.newBufferedReader(Paths.get(path + "2db.csv"));
            csvReader = new CSVReader(reader);
            while ((line = csvReader.readNext()) != null) {
                command = "insert into outcome values(" +
                        CryptoCSV.decrypt(line[0]) + ", " +
                        CryptoCSV.decrypt(line[1]) + ", " +
                        CryptoCSV.decrypt(line[2]) + ", " +
                        CryptoCSV.decrypt(line[3]) + ", '" +
                        CryptoCSV.decrypt(line[4]) + "', '" +
                        CryptoCSV.decrypt(line[5]) + "', " +
                        CryptoCSV.decrypt(line[6]) + ", " +
                        CryptoCSV.decrypt(line[7]) + ")";
                stmt.execute(command);
                System.out.println(command);
            }
            csvReader.close();
            reader.close();
            
            CryptoCSV.resetCounter();
            reader = Files.newBufferedReader(Paths.get(path + "3db.csv"));
            csvReader = new CSVReader(reader);
            while ((line = csvReader.readNext()) != null) {
                command = "insert into expencesOutcome values(" +
                        CryptoCSV.decrypt(line[0]) + ", '" +
                        CryptoCSV.decrypt(line[1]) + "')";
                stmt.execute(command);
                System.out.println(command);
            }
            csvReader.close();
            reader.close();
            
            CryptoCSV.resetCounter();
            reader = Files.newBufferedReader(Paths.get(path + "4db.csv"));
            csvReader = new CSVReader(reader);
            while ((line = csvReader.readNext()) != null) {
                command = "insert into expencesIncome values(" +
                        CryptoCSV.decrypt(line[0]) + ", '" +
                        CryptoCSV.decrypt(line[1]) + "')";
                stmt.execute(command);
                System.out.println(command);
            }
            csvReader.close();
            reader.close();
            
            
            /*
                        CryptoCSV.encrypt(Integer.toString(resultSet.getInt("supplierID"))),
                        CryptoCSV.encrypt(resultSet.getString("supplierName")),
                        CryptoCSV.encrypt(resultSet.getString("supplierDesc")),
                        CryptoCSV.encrypt(Long.toString(resultSet.getLong("supplierEntryDate")))
            */
            CryptoCSV.resetCounter();
            reader = Files.newBufferedReader(Paths.get(path + "5db.csv"));
            csvReader = new CSVReader(reader);
            while ((line = csvReader.readNext()) != null) {
                command = "insert into suppliers values(" +
                        CryptoCSV.decrypt(line[0]) + ", '" +
                        CryptoCSV.decrypt(line[1]) + "', '" +
                        CryptoCSV.decrypt(line[2]) + "', " +
                        CryptoCSV.decrypt(line[3]) + ")";
                stmt.execute(command);
                System.out.println(command);
            }
            csvReader.close();
            reader.close();
            
            
        } catch (FileNotFoundException ex) {
            java.util.logging.Logger.getLogger(methods.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            return false;
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(methods.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            return false;
        } catch (CsvValidationException ex) {
            java.util.logging.Logger.getLogger(methods.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            return false;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(methods.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    public static boolean exportToCSV(String path) {
        initLocalConnection();
        try {

            CSVWriter writer = new CSVWriter(new FileWriter(path + "1db.csv"));
            command = "select * from income";
            resultSet = stmt.executeQuery(command);
            CryptoCSV.resetCounter();
            while(resultSet.next()) {
                writer.writeNext( new String[] {
                        CryptoCSV.encrypt(Integer.toString(resultSet.getInt("incomeID"))),
                        CryptoCSV.encrypt(Integer.toString(resultSet.getInt("iday"))),
                        CryptoCSV.encrypt(Integer.toString(resultSet.getInt("imonth"))),
                        CryptoCSV.encrypt(Integer.toString(resultSet.getInt("iyear"))),
                        CryptoCSV.encrypt(resultSet.getString("iname")),
                        CryptoCSV.encrypt(resultSet.getString("idesc")),
                        CryptoCSV.encrypt(Float.toString(resultSet.getFloat("amount"))),
                        CryptoCSV.encrypt(Long.toString(resultSet.getLong("dateModified"))) }
                );
            }
            writer.close();
            
            writer = new CSVWriter(new FileWriter(path + "2db.csv"));
            command = "select * from outcome";
            resultSet = stmt.executeQuery(command);
            CryptoCSV.resetCounter();
            while(resultSet.next()) {
                writer.writeNext( new String[] {
                        CryptoCSV.encrypt(Integer.toString(resultSet.getInt("outcomeID"))),
                        CryptoCSV.encrypt(Integer.toString(resultSet.getInt("iday"))),
                        CryptoCSV.encrypt(Integer.toString(resultSet.getInt("imonth"))),
                        CryptoCSV.encrypt(Integer.toString(resultSet.getInt("iyear"))),
                        CryptoCSV.encrypt(resultSet.getString("iname")),
                        CryptoCSV.encrypt(resultSet.getString("idesc")),
                        CryptoCSV.encrypt(Float.toString(resultSet.getFloat("amount"))),
                        CryptoCSV.encrypt(Long.toString(resultSet.getLong("dateModified"))) }
                );
            }
            writer.close();
            
            writer = new CSVWriter(new FileWriter(path + "3db.csv"));
            command = "select * from expencesOutcome";
            resultSet = stmt.executeQuery(command);
            CryptoCSV.resetCounter();
            while(resultSet.next()) {
                writer.writeNext( new String[] {
                        CryptoCSV.encrypt(Integer.toString(resultSet.getInt("categID"))),
                        CryptoCSV.encrypt(resultSet.getString("category")) }
                );
            }
            writer.close();
            
            writer = new CSVWriter(new FileWriter(path + "4db.csv"));
            command = "select * from expencesIncome";
            resultSet = stmt.executeQuery(command);
            CryptoCSV.resetCounter();
            while(resultSet.next()) {
                writer.writeNext( new String[] {
                        CryptoCSV.encrypt(Integer.toString(resultSet.getInt("categID"))),
                        CryptoCSV.encrypt(resultSet.getString("category")) }
                );
            }
            writer.close();
            
            writer = new CSVWriter(new FileWriter(path + "5db.csv"));
            command = "select * from suppliers";
            resultSet = stmt.executeQuery(command);
            CryptoCSV.resetCounter();
            while(resultSet.next()) {
                writer.writeNext( new String[] {
                        CryptoCSV.encrypt(Integer.toString(resultSet.getInt("supplierID"))),
                        CryptoCSV.encrypt(resultSet.getString("supplierName")),
                        CryptoCSV.encrypt(resultSet.getString("supplierDesc")),
                        CryptoCSV.encrypt(Long.toString(resultSet.getLong("supplierEntryDate"))) }
                );
            }
            writer.close();
            
            writer = new CSVWriter(new FileWriter(path + "6db.csv"));
            command = "select * from categories";
            resultSet = stmt.executeQuery(command);
            CryptoCSV.resetCounter();
            while(resultSet.next()) {
                writer.writeNext( new String[] {
                        CryptoCSV.encrypt(Integer.toString(resultSet.getInt("categID"))),
                        CryptoCSV.encrypt(resultSet.getString("categName")),
                        CryptoCSV.encrypt(Integer.toString(resultSet.getInt("supplierID"))) }
                );
            }
            writer.close();
            
            writer = new CSVWriter(new FileWriter(path + "7db.csv"));
            command = "select * from products";
            resultSet = stmt.executeQuery(command);
            CryptoCSV.resetCounter();
            while(resultSet.next()) {
                writer.writeNext( new String[] {
                        CryptoCSV.encrypt(Integer.toString(resultSet.getInt("productID"))),
                        CryptoCSV.encrypt(resultSet.getString("productName")),
                        CryptoCSV.encrypt(resultSet.getString("productDesc")),
                        CryptoCSV.encrypt(Long.toString(resultSet.getLong("productEntryDate"))),
                        CryptoCSV.encrypt(Float.toString(resultSet.getFloat("productPrice"))),
                        CryptoCSV.encrypt(Integer.toString(resultSet.getInt("productTax"))),
                        CryptoCSV.encrypt(Float.toString(resultSet.getFloat("productFinalPrice"))),
                        CryptoCSV.encrypt(Integer.toString(resultSet.getInt("categID"))),
                        CryptoCSV.encrypt(Integer.toString(resultSet.getInt("supplierID"))) }
                );
            }
            writer.close();

        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(methods.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            return false;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(methods.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            return false;
            
        }
        return true;
    }
    
    private static void initLocalConnection() {
        try {
            Connection localConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbHappyNest?", "root", "ebisonovsekonem");
            stmt = localConnection.createStatement();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(new JLabel(), "Error while connecting to database\nContact support", "Connection error", JOptionPane.ERROR_MESSAGE);
            java.util.logging.Logger.getLogger(methods.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            
        }
    }
    public static int min(int a, int b) {
        if (a > b) {
            return b;
        } else {
            return a;
        }
    }
    
    public static boolean isFloat(String text)
    {
        if(text.equals(""))
            return false;
        boolean p = false; // for the dot or comma
        for(int i = 0; i < text.length(); i++)
        {
            if (text.charAt(i) == '.')
            {
                if (p)
                    return false;
                p = true;
            }
            else if (!(text.charAt(i) >= '0' && text.charAt(i) <= '9'))
                return false;
        }
        return true;
    }
    
    public static int okcancel(String theMessage) 
    {
        int result = JOptionPane.showConfirmDialog((Component) null, theMessage,
        "Alert", JOptionPane.OK_CANCEL_OPTION);
        return result;
    }
    // </editor-fold>
    private static Connection localConnection;
    private static Statement stmt;
    private static ResultSet resultSet;
    private static String command;
}
