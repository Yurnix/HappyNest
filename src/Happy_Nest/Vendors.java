package Happy_Nest;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.sql.*;
import java.util.Scanner;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Vendors extends javax.swing.JFrame {

    public Vendors() {
        initComponents();
        //establish_connection();
        initLocalConnection(); // Initialize local connection
        initTable(); // intialize product table
        loadVendorsList(); // load products

        editCategoryButton.setVisible(false);
        removeCategoryButton.setVisible(false);
        if (vendorsTable.getRowCount() != 0) {
            vendorsTable.addRowSelectionInterval(0, 0); // if not empty select first vendor
            loadCategories(); // load vendors categories

        }

    }

    private void initTable() {
        productsTable.getColumnModel().getColumn(0).setMinWidth(5);
        productsTable.getColumnModel().getColumn(0).setMaxWidth(400);

        productsTable.getColumnModel().getColumn(1).setMinWidth(10);
        productsTable.getColumnModel().getColumn(1).setMaxWidth(80);

        productsTable.getColumnModel().getColumn(2).setMinWidth(10);
        productsTable.getColumnModel().getColumn(2).setMaxWidth(80);

        productsTable.getColumnModel().getColumn(3).setMinWidth(5);
        productsTable.getColumnModel().getColumn(3).setMaxWidth(120);

        productsTable.getColumnModel().getColumn(4).setMinWidth(10);
        productsTable.getColumnModel().getColumn(4).setMaxWidth(200);

        /*
        *
        hidden columns so that it is not needed to create an array
        parrallel to the table
        *
         */
        //hidden column for productID
        productsTable.getColumnModel().getColumn(5).setMinWidth(0);
        productsTable.getColumnModel().getColumn(5).setMaxWidth(0);

        //hidden column for vendorID
        vendorsTable.getColumnModel().getColumn(1).setMinWidth(0);
        vendorsTable.getColumnModel().getColumn(1).setMaxWidth(0);

    }

    private void loadCategories() {
        categoryBox.removeAllItems(); //remove previous categories

        categoryBox.addItem("All Products");
        categoryBox.setSelectedIndex(0); //show all products first

        if (vendorsTable.getSelectedRow() != -1) { //if vendor is selected

            //create query to resolve category Name by supplierID
            command = "select categName from categories where categories.supplierID = "
                    + getSelectedVendor(); //supplierID

            try {
                resultSet = stmt.executeQuery(command);

                while (resultSet.next()) {
                    categoryBox.addItem(resultSet.getString("categName")); //add category to categoryBox
                }
            } catch (SQLException ex) {
                Logger.getLogger(Vendors.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void loadProductTable() {
        if (vendorsTable.getSelectedRow() == -1) { //if vendor not selected exit
            return;
        }
        try {
            if (categoryBox.getSelectedIndex() == 0) { //if selectrd all products load all
                command = "select * from products where products.supplierID = "
                        + getSelectedVendor()
                        + " order by products.productName";
            } else { //load the products by selected category
                
                //resolve categID 
                command = "select * from categories where categName = '"
                        + categoryBox.getSelectedItem()
                        + "' and categories.supplierID = "
                        + getSelectedVendor();

                resultSet = stmt.executeQuery(command);

                command = "select * from products where products.supplierID = "
                        + getSelectedVendor()
                        + " and products.categID = "
                        + (resultSet.next() ? resultSet.getInt("categID") : -1)
                        + " order by products.productName";

            }
            
            //when command created execute query
            resultSet = stmt.executeQuery(command);
            productsTableModel = (DefaultTableModel) productsTable.getModel();
            
            //remove rows
            while (productsTableModel.getRowCount() != 0) { 
                productsTableModel.removeRow(0);
            }

            //while products exist enter them to the products table
            while (resultSet.next()) {
                aDate = new Date(resultSet.getLong("productEntryDate"));
                productsTableModel.addRow(new Object[]{
                    resultSet.getString("productName"),
                    resultSet.getFloat("productPrice"),
                    resultSet.getFloat("productTax"),
                    resultSet.getFloat("productFinalPrice"),
                    String.valueOf(aDate.getDate())
                    + "/"
                    + String.valueOf(aDate.getMonth() + 1)
                    + "/"
                    + String.valueOf(aDate.getYear() + 1900),
                    resultSet.getInt("productID")
                });
            }

        } catch (SQLException ex) {
            Logger.getLogger(Vendors.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private int getSelectedProduct() {
        if (productsTable.getSelectedRow() == -1) {
            return -1;
        }
        //return productID
        return (int) productsTable.getModel().getValueAt(productsTable.getSelectedRow(), 5);
    }

    private int getSelectedVendor() {
        if (vendorsTable.getSelectedRow() == -1) {
            return -1;
        }
        //return supplierID
        return (int) vendorsTable.getModel().getValueAt(vendorsTable.getSelectedRow(), 1);
    }

    private void searchAction() {
        //search by name and decription
        String text = searchField.getText(); //The text of the search request
        if (!text.equals("")) {
            vendorsTable.setEnabled(false);
            addProductButton.setEnabled(false);
            categoryBox.setEnabled(false);
            addCategoryButton.setEnabled(false);
            clearVendorsTable();
            try {
                //empty products table
                while (productsTableModel.getRowCount() != 0) {
                    productsTableModel.removeRow(0);
                }

                //search by name and description
                String columns[] = {"productName", "productDesc"};
                for (int i = 0; i < columns.length; i++) {
                    command = "select * from products where locate('" + text + "'," + columns[i] + ") > 0";
                    resultSet = stmt.executeQuery(command);
                    productsTableModel = (DefaultTableModel) productsTable.getModel();

                    //while found products exist
                    while (resultSet.next()) {
                        aDate = new Date(resultSet.getLong("productEntryDate"));
                        productsTableModel.addRow(new Object[]{
                            resultSet.getString("productName"),
                            resultSet.getFloat("productPrice"),
                            resultSet.getFloat("productTax"),
                            resultSet.getFloat("productFinalPrice"),
                            String.valueOf(aDate.getDate())
                            + "/"
                            + String.valueOf(aDate.getMonth() + 1)
                            + "/"
                            + String.valueOf(aDate.getYear() + 1900),
                            resultSet.getInt("productID")
                        });
                    }
                }

                ResultSet tempResultSet; //to show from wich vendor product is
                for (int i = 0; i < productsTableModel.getRowCount(); i++) {
                    command = "select supplierID from products where productID = " + productsTableModel.getValueAt(i, 5);
                    tempResultSet = stmt.executeQuery(command);
                    tempResultSet.next();
                    command = "select supplierName from suppliers where supplierID = " + tempResultSet.getString("supplierID");
                    tempResultSet = stmt.executeQuery(command);
                    if (!tempResultSet.next()) {
                        //if vendor not found some error happen during the 
                        productsTableModel.setValueAt("Vendor not found: " + productsTableModel.getValueAt(i, 0), i, 0);
                        informationArea.setText("Some products don't have vendors, this happen because of wrong import from backup");
                    } else {
                        //add vendor name
                        productsTableModel.setValueAt(tempResultSet.getString("supplierName") + ": " + productsTableModel.getValueAt(i, 0), i, 0);
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(Vendors.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            //if search text is blank unblock buttons
            vendorsTable.setEnabled(true);
            addProductButton.setEnabled(true);
            categoryBox.setEnabled(true);
            addCategoryButton.setEnabled(true);
            loadVendorsList();
        }
    }

    //connect to mysql database
    private void initLocalConnection() {
        try {
            String login = "";
            String password = "";
            File dbCreds = new File("dbCreds.txt"); //resolve login and password from this file
            if (dbCreds.exists()) {
                Scanner input = new Scanner(dbCreds);
                CryptoCSV.resetCounter();
                login = (input.hasNextLine() ? CryptoCSV.decrypt(input.nextLine()) : "root");
                password = (input.hasNextLine() ? CryptoCSV.decrypt(input.nextLine()) : "password");
                input.close();
            }
            Connection localConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbHappyNest?", login, password);
            stmt = localConnection.createStatement();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(new JLabel(), "Error while connecting to database\nContact support", "Connection error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(Vendors.class.getName()).log(Level.SEVERE, null, ex);
            this.dispose(); //if not connected no reason to work in this frame
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FinanceReview.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void establish_connection() {
        try {
            // The data for the connection will be passed with constructor from Settings Frame
            serverConnection = DriverManager.getConnection(
                    "jdbc:mysql://sql11.freemysqlhosting.net:3306/         ",
                    "         ",
                    "         "
            );
            cloudPicLabel.setText("Cloud Available");
        } catch (SQLException ex) {
            cloudPicLabel.setText("Cloud Unavailable");
            Logger.getLogger(Vendors.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //callback from addVendor
    class AddToList implements PassVendor {

        public void pass(String pname, String pdesc, boolean edit) // Callback from AddVendor function
        {
            JOptionPane.showMessageDialog(new JLabel(), pname + "\n" + pdesc);
            if (edit) {
                int ven = vendorsTable.getSelectedRow();
                if (ven == -1) {
                    return;
                }
                
                //change name and description
                command = "update suppliers set supplierName = '"
                        + pname
                        + "' , supplierDesc = '"
                        + pdesc
                        + "' where supplierID = "
                        + getSelectedVendor();
                try {
                    stmt.executeUpdate(command);
                } catch (SQLException ex) {
                    Logger.getLogger(Vendors.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                //add name, description and time
                command = "insert into suppliers values ( 0, '"
                        + pname
                        + "' , '"
                        + pdesc
                        + "' , "
                        + Long.toString(System.currentTimeMillis())
                        + ")";
                try {
                    stmt.executeUpdate(command);
                } catch (SQLException ex) {
                    Logger.getLogger(Vendors.class.getName()).log(Level.SEVERE, null, "Can't add vendor\n" + ex);
                }
            }
            loadVendorsList();
        }
    }

    private void clearVendorsTable() {
        while (vendorsTable.getRowCount() != 0) {
            vendorsTableModel.removeRow(0);
        }
    }

    private void loadVendorsList() {
        vendorsTableModel = (DefaultTableModel) vendorsTable.getModel();

        try {
            command = "select * from suppliers order by suppliers.supplierName";
            resultSet = stmt.executeQuery(command);
            clearVendorsTable();

            while (resultSet.next()) {
                vendorsTableModel.addRow(new Object[]{resultSet.getString("supplierName"), resultSet.getInt("supplierID")});
            }
            vendorsTable.setModel(vendorsTableModel);
        } catch (SQLException ex) {
            Logger.getLogger(Vendors.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        addVendorButton = new javax.swing.JButton();
        editVendorButton = new javax.swing.JButton();
        removeVendorButton = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        vendorsTable = new javax.swing.JTable();
        cloudPicLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        searchField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jSeparator3 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        informationArea = new javax.swing.JTextPane();
        categoryBox = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        productsTable = new javax.swing.JTable();
        addCategoryButton = new javax.swing.JButton();
        removeCategoryButton = new javax.swing.JButton();
        editCategoryButton = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        costField = new javax.swing.JTextField();
        nameField = new javax.swing.JTextField();
        editProductButton = new javax.swing.JButton();
        addProductButton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        removeProductButton = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        descField = new javax.swing.JTextField();
        taxField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        addVendorButton.setText("Add");
        addVendorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addVendorButtonActionPerformed(evt);
            }
        });

        editVendorButton.setText("Edit");
        editVendorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editVendorButtonActionPerformed(evt);
            }
        });

        removeVendorButton.setText("Remove");
        removeVendorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeVendorButtonActionPerformed(evt);
            }
        });

        vendorsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Vendor", "supplierID"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        vendorsTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        vendorsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                vendorsTableMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                vendorsTableMouseReleased(evt);
            }
        });
        jScrollPane4.setViewportView(vendorsTable);

        cloudPicLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(cloudPicLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addVendorButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(editVendorButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(removeVendorButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(420, Short.MAX_VALUE)
                .addComponent(addVendorButton)
                .addGap(5, 5, 5)
                .addComponent(editVendorButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(removeVendorButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cloudPicLabel)
                .addContainerGap())
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addContainerGap(8, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(121, Short.MAX_VALUE)))
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        jLabel2.setText("VENDORS");

        searchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchFieldKeyReleased(evt);
            }
        });

        jLabel3.setText("Search:");

        informationArea.setBorder(javax.swing.BorderFactory.createTitledBorder("Information"));
        informationArea.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                informationAreaFocusGained(evt);
            }
        });
        jScrollPane2.setViewportView(informationArea);

        categoryBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All Products", "something" }));
        categoryBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                categoryBoxItemStateChanged(evt);
            }
        });
        categoryBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoryBoxActionPerformed(evt);
            }
        });

        productsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product Name", "Cost", "Tax", "Cost with Tax", "Creation Date", "dbID"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Float.class, java.lang.Integer.class, java.lang.Float.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        productsTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        productsTable.setShowGrid(true);
        productsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                productsTableMouseReleased(evt);
            }
        });
        jScrollPane3.setViewportView(productsTable);

        addCategoryButton.setText("Add Category");
        addCategoryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCategoryButtonActionPerformed(evt);
            }
        });

        removeCategoryButton.setText("Remove Category");
        removeCategoryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeCategoryButtonActionPerformed(evt);
            }
        });

        editCategoryButton.setText("Edit Category Name");
        editCategoryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editCategoryButtonActionPerformed(evt);
            }
        });

        costField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                costFieldActionPerformed(evt);
            }
        });

        nameField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                nameFieldFocusGained(evt);
            }
        });
        nameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameFieldActionPerformed(evt);
            }
        });
        nameField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nameFieldKeyReleased(evt);
            }
        });

        editProductButton.setText("Edit");
        editProductButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editProductButtonActionPerformed(evt);
            }
        });

        addProductButton.setText("Add Product");
        addProductButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addProductButtonActionPerformed(evt);
            }
        });

        jLabel5.setText("Cost:");

        removeProductButton.setText("Remove");
        removeProductButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeProductButtonActionPerformed(evt);
            }
        });

        jLabel6.setText("Tax:");

        jLabel4.setText("Name:");

        jLabel1.setText("Description:");

        descField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                descFieldActionPerformed(evt);
            }
        });
        descField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                descFieldKeyReleased(evt);
            }
        });

        taxField.setText("19");

        jLabel7.setText("%");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(descField))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(costField, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addGap(4, 4, 4)
                        .addComponent(taxField, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addProductButton, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(editProductButton, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(removeProductButton, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(costField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(addProductButton)
                    .addComponent(editProductButton)
                    .addComponent(removeProductButton)
                    .addComponent(taxField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(descField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(categoryBox, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addCategoryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(112, 112, 112)
                        .addComponent(editCategoryButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(removeCategoryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 877, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator3)
                .addGap(927, 927, 927))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(categoryBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addCategoryButton)
                    .addComponent(removeCategoryButton)
                    .addComponent(editCategoryButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 894, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 528, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void editVendorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editVendorButtonActionPerformed
        // add from sql 
        int ven = vendorsTable.getSelectedRow();
        if (ven == -1) {
            return;
        }
        
        
        command = "select * from suppliers where supplierID = "
                + getSelectedVendor();
        try {
            resultSet = stmt.executeQuery(command);
            if (resultSet.next()) {
                //load AddVendor frame with ready name and description
                AddVendor addVendor = new AddVendor(resultSet.getString("supplierName"), resultSet.getString("supplierDesc"), true);
                PassVendor listener = new Vendors.AddToList();
                addVendor.addListener(listener);
                addVendor.setVisible(true);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Vendors.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_editVendorButtonActionPerformed

    private void addVendorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addVendorButtonActionPerformed
        AddVendor addVendor = new AddVendor(null, null, false);
        PassVendor listener = new Vendors.AddToList();
        addVendor.addListener(listener);
        addVendor.setVisible(true);
    }//GEN-LAST:event_addVendorButtonActionPerformed

    private void categoryBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_categoryBoxItemStateChanged

        editProductButton.setVisible(false);
        removeProductButton.setVisible(false);
        if (categoryBox.getSelectedIndex() == 0) {
            editCategoryButton.setVisible(false);
            removeCategoryButton.setVisible(false);
            nameField.setEnabled(false);
            costField.setEnabled(false);
            addProductButton.setEnabled(false);
            editProductButton.setEnabled(false);
            removeProductButton.setEnabled(false);
            taxField.setEnabled(false);
        } else {
            editCategoryButton.setVisible(true);
            removeCategoryButton.setVisible(true);
            nameField.setEnabled(true);
            costField.setEnabled(true);
            addProductButton.setEnabled(true);
            editProductButton.setEnabled(true);
            removeProductButton.setEnabled(true);
            taxField.setEnabled(true);
        }
        loadProductTable();
        //System.out.println("categoryStateChanged end");
    }//GEN-LAST:event_categoryBoxItemStateChanged

    private void nameFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nameFieldFocusGained
        //descriptionField.setText("");
    }//GEN-LAST:event_nameFieldFocusGained

    private void removeVendorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeVendorButtonActionPerformed
        if (vendorsTable.getSelectedRow() == -1 || Methods.okcancel("Are you sure you want to delete this item?") != 0) {
            return;
        }

        try {
            command = "delete from products where products.supplierID = " + getSelectedVendor();
            stmt.execute(command);

            command = "delete from categories where categories.supplierID = " + getSelectedVendor();
            stmt.execute(command);

            command = "delete from suppliers where suppliers.supplierID = " + getSelectedVendor();
            stmt.execute(command);
        } catch (SQLException ex) {
            Logger.getLogger(Vendors.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadVendorsList();
        if (vendorsTable.getRowCount() != 0) {
            vendorsTable.addRowSelectionInterval(0, 0);
            loadCategories();
        }
    }//GEN-LAST:event_removeVendorButtonActionPerformed

    private void vendorsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vendorsTableMouseClicked
        editProductButton.setVisible(false);
        removeProductButton.setVisible(false);
        if (vendorsTable.getSelectedRow() == -1) {
            return;
        }
        command = "select * from suppliers where supplierID = "
                + getSelectedVendor();

        try {
            resultSet = stmt.executeQuery(command);
            if (resultSet.next()) {
                informationArea.setText(resultSet.getString("supplierDesc"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Vendors.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadCategories();
    }//GEN-LAST:event_vendorsTableMouseClicked

    private void addProductButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addProductButtonActionPerformed

        String pname = nameField.getText();
        if (pname.contains("'") || pname.contains("\"")) {
            JOptionPane.showMessageDialog(new JLabel(), "Pleas don't use ' or \" special symbols in name field", "Special symbol error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String pdesc = descField.getText();
        if (pdesc.contains("'") || pdesc.contains("\"")) {
            JOptionPane.showMessageDialog(new JLabel(), "Pleas don't use ' or \" special symbols in description field", "Special symbol error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!Methods.isFloat(costField.getText())) {
            JOptionPane.showMessageDialog(new JLabel(), "Error", "Incorrect value in Cost Field", JOptionPane.ERROR_MESSAGE);
            return;
        }

        float pcost = Float.parseFloat(costField.getText());
        String stax = taxField.getText();
        if (!Methods.isFloat(stax)) {
            JOptionPane.showMessageDialog(new JLabel(), "Error", "Incorrect value in Tax Field", JOptionPane.ERROR_MESSAGE);
            return;
        }

        //JOptionPane.showMessageDialog(new JLabel(), stax);
        float tax = Float.parseFloat(stax);
        /*
        create table products (
	productID int not null auto_increment,
        productName varchar(30),
        productDesc varchar(450),
        productEntryDate bigint,
        productPrice float,
        productTax int,
        productFinalPrice float,
        supplierID int,
        categName varchar(30),
        primary key (productID),
         */
        command = "select * from categories where categName = '"
                + categoryBox.getSelectedItem()
                + "' and categories.supplierID = "
                + getSelectedVendor();
        try {
            resultSet = stmt.executeQuery(command);
            if (!resultSet.next()) {
                JOptionPane.showMessageDialog(new JLabel(), "Something went wrong\nMaybe you did not choose a vendor");
                return;
            }

            command = "insert into products values ( 0 , "
                    + "'" + pname + "' , "
                    + "'" + pdesc + "' , "
                    + Long.toString(System.currentTimeMillis()) + " , "
                    + Float.toString(pcost) + " , "
                    + stax + " , "
                    + Float.toString(pcost + (pcost * tax) / 100) + " , "
                    + getSelectedVendor() + " , "
                    + resultSet.getInt("categID") + " )";
        } catch (SQLException ex) {
            Logger.getLogger(Vendors.class.getName()).log(Level.SEVERE, null, ex);
        }

        //informationArea.setText(command);
        try {
            stmt.execute(command);
        } catch (SQLException ex) {
            Logger.getLogger(Vendors.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadProductTable();
        nameField.setText("");
        informationArea.setText("");
        descField.setText("");
        costField.setText("");
    }//GEN-LAST:event_addProductButtonActionPerformed

    private void addCategoryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addCategoryButtonActionPerformed

        int index = vendorsTable.getSelectedRow();
        if (index == -1) {
            return;
        }

        String catName = JOptionPane.showInputDialog("Enter category name:");
        if (catName.contains("'") || catName.contains("\"")) {
            JOptionPane.showMessageDialog(new JLabel(), "Pleas don't use ' or \" special symbols in category name field", "Special symbol error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        command = "insert into categories values(0, \'"
                + catName
                + "\' , "
                + getSelectedVendor()
                + ")";
        try {
            stmt.executeUpdate(command);
        } catch (SQLException ex) {
            Logger.getLogger(Vendors.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadCategories();
        categoryBox.setSelectedItem(catName);
    }//GEN-LAST:event_addCategoryButtonActionPerformed

    private void editCategoryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editCategoryButtonActionPerformed
        String categName = "";
        try {
            if (categoryBox.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(new JLabel(), "Can't change 'All products' category");
                return;
            }
            command = "select * from categories where categName = '"
                    + categoryBox.getSelectedItem()
                    + "' and categories.supplierID = "
                    + getSelectedVendor();
            resultSet = stmt.executeQuery(command);

            if (resultSet.next()) {
                categName = JOptionPane.showInputDialog(null, "enter name", resultSet.getString("categName"), JOptionPane.QUESTION_MESSAGE);
            }

            if (categName.contains("'") || categName.contains("\"")) {
                JOptionPane.showMessageDialog(new JLabel(), "Pleas don't use ' or \" special symbols in category field", "Special symbol error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (categName.equals("")) {
                JOptionPane.showMessageDialog(new JLabel(), "Enter correct category name");
                return;
            }

            if (Methods.okcancel("Category name will be changed for all Vendors\nAre you sure you want to rename it?") != 0) {
                return;
            }

            command = "update categories set categName = '"
                    + categName
                    + "' where categName = '"
                    + categoryBox.getSelectedItem()
                    + "' and categories.supplierID = "
                    + getSelectedVendor();

            stmt.executeUpdate(command);
            //System.out.println("Item is " + categoryBox.getItemAt(categoryBox.getSelectedIndex()));
            //nameField.setEnabled(false);
            loadCategories();
        } catch (SQLException ex) {
            Logger.getLogger(Vendors.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(new JLabel(), "Something went wrong, check if category name already exists");
        }
        loadCategories();
        categoryBox.setSelectedItem(categName);

    }//GEN-LAST:event_editCategoryButtonActionPerformed

    private void informationAreaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_informationAreaFocusGained
        informationArea.select(0, informationArea.getText().length());
    }//GEN-LAST:event_informationAreaFocusGained

    private void editProductButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editProductButtonActionPerformed

        String pname = nameField.getText();
        String productName = "Unknown";
        String productDesc = "Unknown";
        int productTax = 0;
        float productPrice = 0;
        float productFinalPrice = 0;
        String pdesc = descField.getText();
        String cost = costField.getText();
        if (pname.contains("'") || pname.contains("\"")) {
            JOptionPane.showMessageDialog(new JLabel(), "Pleas don't use ' or \" special symbols in name field", "Special symbol error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (pdesc.contains("'") || pdesc.contains("\"")) {
            JOptionPane.showMessageDialog(new JLabel(), "Pleas don't use ' or \" special symbols in description field", "Special symbol error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        float pcost = -1;
        if (!cost.equals("")) {
            if (!Methods.isFloat(costField.getText())) {
                JOptionPane.showMessageDialog(new JLabel(), "Error", "Incorrect value in Cost Field", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        pcost = Float.parseFloat(cost);
        String stax = taxField.getText();
        if (!stax.equals("")) {
            if (!Methods.isFloat(stax)) {
                JOptionPane.showMessageDialog(new JLabel(), "Error", "Incorrect value in Tax Field", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        //JOptionPane.showMessageDialog(new JLabel(), stax);
        int tax = Integer.parseInt(stax);
        try {
            command = "select * from products where productID = "
                    + getSelectedProduct();

            resultSet = stmt.executeQuery(command);
            if (!resultSet.next()) {
                JOptionPane.showMessageDialog(new JLabel(), "Something went wrong durng record update.\nRecord not found");
                return;
            } else {
                productName = resultSet.getString("productName");
                productDesc = resultSet.getString("productDesc");
                productTax = resultSet.getInt("productTax");
                productPrice = resultSet.getFloat("productPrice");
            }

            if (!pname.equals("")) {
                productName = pname;
            }
            if (!pdesc.equals("")) {
                productDesc = pdesc;
            }
            if (productPrice != pcost && pcost != -1) {
                productPrice = pcost;
            }
            if (productTax != tax) {
                productTax = tax;
            }
            productFinalPrice = productPrice + productPrice * productTax / 100;

            command = "update products set "
                    + "productName = '" + productName
                    + "',productDesc = '" + productDesc
                    + "',productPrice = " + productPrice
                    + ",productTax = " + productTax
                    + ",productFinalPrice = " + productFinalPrice
                    + " where productID = " + getSelectedProduct();
            stmt.executeUpdate(command);

        } catch (SQLException ex) {
            Logger.getLogger(Vendors.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadProductTable();
        if (!searchField.getText().equals(""))
            searchAction();
    }//GEN-LAST:event_editProductButtonActionPerformed

    private void removeProductButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeProductButtonActionPerformed
        if (productsTable.getSelectedRow() == -1) {
            return;
        }
        //vendorsTable.getSelectedRow() == -1 || 

        command = "delete from products where productID = " + getSelectedProduct();
        try {
            stmt.execute(command);
        } catch (SQLException ex) {
            Logger.getLogger(Vendors.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (!searchField.getText().isBlank()) {
            searchAction();
        } else {
            loadProductTable();
        }

    }//GEN-LAST:event_removeProductButtonActionPerformed

    private void removeCategoryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeCategoryButtonActionPerformed
        if (vendorsTable.getSelectedRow() == -1) {
            return;
        }
        try {
            command = "select * from categories where categName = '"
                    + categoryBox.getSelectedItem()
                    + "' and categories.supplierID = "
                    + getSelectedVendor();

            resultSet = stmt.executeQuery(command);
            if (!resultSet.next()) {
                JOptionPane.showMessageDialog(new JLabel(), "Something went wrong with sql");
                return;
            }
            int categID = resultSet.getInt("categID");
            command = "delete from products where products.categID = " + categID;
            stmt.execute(command);

            command = "delete from categories where categID = " + categID;
            stmt.execute(command);

        } catch (SQLException ex) {
            Logger.getLogger(Vendors.class.getName()).log(Level.SEVERE, null, ex);
        }
        //loadVendorsList();
        loadCategories();
    }//GEN-LAST:event_removeCategoryButtonActionPerformed

    private void descFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descFieldActionPerformed
        descField.select(0, descField.getText().length());
    }//GEN-LAST:event_descFieldActionPerformed

    private void vendorsTableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vendorsTableMouseReleased
        editProductButton.setVisible(false);
        removeProductButton.setVisible(false);
        if (vendorsTable.getSelectedRow() == -1) {
            return;
        }
        command = "select * from suppliers where supplierID = "
                + getSelectedVendor();

        try {
            resultSet = stmt.executeQuery(command);
            if (resultSet.next()) {
                informationArea.setText(resultSet.getString("supplierDesc"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Vendors.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadCategories();
        addProductButton.setEnabled(false);
    }//GEN-LAST:event_vendorsTableMouseReleased

    private void productsTableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productsTableMouseReleased
        //editCategoryButton.setVisible(true);
        //removeCategoryButton.setVisible(true);
        nameField.setEnabled(true);
        costField.setEnabled(true);
        addProductButton.setEnabled(true);
        editProductButton.setEnabled(true);
        editProductButton.setVisible(true);
        removeProductButton.setEnabled(true);
        removeProductButton.setVisible(true);
        taxField.setEnabled(true);
        if (!searchField.getText().equals("")) {
            addProductButton.setEnabled(false);
        }

        command = "select * from products where productID = "
                + getSelectedProduct();

        try {
            resultSet = stmt.executeQuery(command);
            if (resultSet.next()) {
                String d = resultSet.getString("productDesc");
                informationArea.setText(d);
                nameField.setText(resultSet.getString("productName"));
                costField.setText(Float.toString(resultSet.getFloat("productPrice")));
                descField.setText(d);
                taxField.setText(Integer.toString(resultSet.getInt("productTax")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Vendors.class.getName()).log(Level.SEVERE, null, ex);
        }
        //descriptionField.setText(command);
    }//GEN-LAST:event_productsTableMouseReleased

    private void nameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameFieldActionPerformed
        nameField.select(0, nameField.getText().length());
    }//GEN-LAST:event_nameFieldActionPerformed

    private void costFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_costFieldActionPerformed
        costField.select(0, costField.getText().length());
    }//GEN-LAST:event_costFieldActionPerformed

    private void categoryBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoryBoxActionPerformed
        nameField.setText("");
        costField.setText("");
        descField.setText("");
        informationArea.setText("");
    }//GEN-LAST:event_categoryBoxActionPerformed

    private void nameFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nameFieldKeyReleased
        nameField.setText(nameField.getText().replace('\'', '`'));
        nameField.setText(nameField.getText().replace('"', '`'));
    }//GEN-LAST:event_nameFieldKeyReleased

    private void descFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_descFieldKeyReleased
        descField.setText(descField.getText().replace('\'', '`'));
        descField.setText(descField.getText().replace('"', '`'));
    }//GEN-LAST:event_descFieldKeyReleased

    private void searchFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchFieldKeyReleased
        searchField.setText(searchField.getText().replace('\'', '`'));
        searchField.setText(searchField.getText().replace('"', '`'));
        searchAction();
    }//GEN-LAST:event_searchFieldKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Vendors.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Vendors.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Vendors.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Vendors.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Vendors().setVisible(true);
            }
        });
    }

    // C:\Users\User\Dropbox
    //1280 x 1024
    //windows 7 32bit 4GB , pentium cpu g645
    private Date aDate;
    private DefaultTableModel productsTableModel;
    private DefaultTableModel vendorsTableModel;
    private String command;
    private Connection localConnection;
    private Connection serverConnection;
    private Statement stmt;
    private ResultSet resultSet;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addCategoryButton;
    private javax.swing.JButton addProductButton;
    private javax.swing.JButton addVendorButton;
    private javax.swing.JComboBox<String> categoryBox;
    private javax.swing.JLabel cloudPicLabel;
    private javax.swing.JTextField costField;
    private javax.swing.JTextField descField;
    private javax.swing.JButton editCategoryButton;
    private javax.swing.JButton editProductButton;
    private javax.swing.JButton editVendorButton;
    private javax.swing.JTextPane informationArea;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTextField nameField;
    private javax.swing.JTable productsTable;
    private javax.swing.JButton removeCategoryButton;
    private javax.swing.JButton removeProductButton;
    private javax.swing.JButton removeVendorButton;
    private javax.swing.JTextField searchField;
    private javax.swing.JTextField taxField;
    private javax.swing.JTable vendorsTable;
    // End of variables declaration//GEN-END:variables
}
