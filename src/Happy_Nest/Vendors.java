
package Happy_Nest;
import java.sql.*;  
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Vendors extends javax.swing.JFrame {

  
    public Vendors() {
        initComponents();
        establish_connection();
        initLocalConnection();
        initTable();
        loadVendorsList();
        editCategoryButton.setVisible(false);
        removeCategoryButton.setVisible(false);
    }
    
    public void initTable()
    {
        productsTable.getColumnModel().getColumn(0).setMinWidth(5);
        productsTable.getColumnModel().getColumn(0).setMaxWidth(400);
        
        productsTable.getColumnModel().getColumn(1).setMinWidth(5);
        productsTable.getColumnModel().getColumn(1).setMaxWidth(70);
        
        productsTable.getColumnModel().getColumn(2).setMinWidth(5);
        productsTable.getColumnModel().getColumn(2).setMaxWidth(70);
        
        productsTable.getColumnModel().getColumn(3).setMinWidth(5);
        productsTable.getColumnModel().getColumn(3).setMaxWidth(120);
        
        productsTable.getColumnModel().getColumn(4).setMinWidth(10);
        productsTable.getColumnModel().getColumn(4).setMaxWidth(200);
        
        productsTable.getColumnModel().getColumn(5).setMinWidth(0);
        productsTable.getColumnModel().getColumn(5).setMaxWidth(0);
    }
    
    public void loadProductTable()
    {
        try {
            if(categoryBox.getSelectedIndex() != 0) {
                command = "select * from products where products.supplierID = "
                    + Integer.toString((int)vendorsTable.getModel().getValueAt(vendorsTable.getSelectedRow(), 1))
                    + " and products.categName = \'"
                    + categoryBox.getSelectedItem().toString()
                    + "\' order by products.productName";
            }
            else {
                command = "select * from products where products.supplierID = "
                    + Integer.toString((int)vendorsTable.getModel().getValueAt(vendorsTable.getSelectedRow(), 1))
                    + " order by products.productName";
            }
            
            descriptionField.setText(command);
            resultSet = stmt.executeQuery(command);
            
            productsTableModel = (DefaultTableModel) productsTable.getModel();
            while(productsTableModel.getRowCount() != 0)
                productsTableModel.removeRow(0);
            while(resultSet.next())
                productsTableModel.addRow(new Object[] 
                {
                    resultSet.getString("productName"),
                    resultSet.getFloat("productPrice"),
                    resultSet.getFloat("productTax")*100,
                    resultSet.getFloat("productFinalPrice"),
                    Long.toString(resultSet.getLong("productEntryDate")),
                    resultSet.getInt("productID")
                });
                
                
        } catch (SQLException ex) {
            Logger.getLogger(Vendors.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void initLocalConnection()
    {
        try {
            Connection localConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbHappyNest?", "root", "ebisonovsekonem");
            stmt = localConnection.createStatement();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(new JLabel(), "Error while connecting to database\nContact support", "Connection error", JOptionPane.ERROR_MESSAGE );
            Logger.getLogger(Vendors.class.getName()).log(Level.SEVERE, null, ex);
            super.dispose();
        }
    }
    
    public void establish_connection()
    {
        try {
            // The data for the connection will be passed with constructor from Settings Frame
            serverConnection = DriverManager.getConnection(
                    "jdbc:mysql://sql11.freemysqlhosting.net:3306/sql11415364",
                    "sql11415364",
                    "fWjVyJaAt9"
            );
            cloudPicLabel.setText("Cloud Available");
        } catch (SQLException ex) {
            cloudPicLabel.setText("Cloud Unavailable");
            Logger.getLogger(Vendors.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    class AddToList implements PassVendor
    {
        public void pass(String pname, String pdesc, boolean edit) // Callback from AddVendor function
        {
        //JOptionPane.showMessageDialog(new JLabel(), pname + "\n" + pdesc);
            if(edit) 
            {
                int ven = vendorsTable.getSelectedRow();
                if(ven == -1) // if nothing selected
                    return;
            }
            else
            {
                command = "insert into suppliers values( 0, " 
                        + pname 
                        + " , " 
                        + pdesc 
                        + " , " 
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
    
    private void loadVendorsList()
    {
        vendorsTableModel = (DefaultTableModel) vendorsTable.getModel();
        //listModel.clear();
        
        try {
            command = "select * from suppliers order by suppliers.supplierName";
            resultSet = stmt.executeQuery(command);
            while (vendorsTable.getRowCount() != 0)
                vendorsTableModel.removeRow(0);
            
            while (resultSet.next())
            {
                vendorsTableModel.addRow(new Object[] { resultSet.getString("supplierName"), resultSet.getInt("supplierID") });
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
        jSeparator2 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        addVendorButton = new javax.swing.JButton();
        editVendorButton = new javax.swing.JButton();
        removeVendorButton = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        vendorsTable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        searchField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jSeparator3 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        descriptionField = new javax.swing.JTextPane();
        jLabel4 = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        costField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        taxBox = new javax.swing.JComboBox<>();
        addProductButton = new javax.swing.JButton();
        editProductButton = new javax.swing.JButton();
        removeProdutButton = new javax.swing.JButton();
        categoryBox = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        productsTable = new javax.swing.JTable();
        addCategoryButton = new javax.swing.JButton();
        removeCategoryButton = new javax.swing.JButton();
        editCategoryButton = new javax.swing.JButton();
        cloudPicLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        jLabel1.setText("Vendor");

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

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane4.setViewportView(vendorsTable);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addVendorButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(editVendorButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(removeVendorButton, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addVendorButton)
                .addGap(5, 5, 5)
                .addComponent(editVendorButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(removeVendorButton))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addContainerGap(39, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(107, Short.MAX_VALUE)))
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        jLabel2.setText("VENDORS");

        jLabel3.setText("Search:");

        descriptionField.setBorder(javax.swing.BorderFactory.createTitledBorder("Description"));
        jScrollPane2.setViewportView(descriptionField);

        jLabel4.setText("Name:");

        jLabel5.setText("Cost:");

        jLabel6.setText("Tax:");

        taxBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "10%", "19%" }));

        addProductButton.setText("Add Product");

        editProductButton.setText("Edit");

        removeProdutButton.setText("Remove");

        categoryBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All Products", "something" }));
        categoryBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                categoryBoxItemStateChanged(evt);
            }
        });

        productsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Product Name", "Cost", "Tax", "Cost with Tax", "Creation Date", "dbID"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Float.class, java.lang.Integer.class, java.lang.Float.class, java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        productsTable.setShowGrid(true);
        jScrollPane3.setViewportView(productsTable);

        addCategoryButton.setText("Add Category");

        removeCategoryButton.setText("Remove Category");

        editCategoryButton.setText("Edit Category Name");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(costField, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel6)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(taxBox, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(addProductButton, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(editProductButton, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(removeProdutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jScrollPane2))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(categoryBox, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(addCategoryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(editCategoryButton)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(removeCategoryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 852, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
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
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(costField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(taxBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addProductButton)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(removeProdutButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(editProductButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        cloudPicLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        cloudPicLabel.setText("A");

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
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(266, 266, 266))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(275, 275, 275))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cloudPicLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cloudPicLabel))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1086, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void editVendorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editVendorButtonActionPerformed
        // add from sql 
        int ven = vendorsTable.getSelectedRow();
                if(ven == -1) // if nothing selected
                    return;
        AddVendor addVendor = new AddVendor(null, null, true);
        PassVendor listener = new Vendors.AddToList();
        addVendor.addListener(listener);
        addVendor.setVisible(true);
    }//GEN-LAST:event_editVendorButtonActionPerformed

    private void addVendorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addVendorButtonActionPerformed
        AddVendor addVendor = new AddVendor(null, null, false);
        PassVendor listener = new Vendors.AddToList();
        addVendor.addListener(listener);
        addVendor.setVisible(true);
    }//GEN-LAST:event_addVendorButtonActionPerformed

    private void categoryBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_categoryBoxItemStateChanged
        if(categoryBox.getSelectedIndex() == 0)
        {
            editCategoryButton.setVisible(false);
            removeCategoryButton.setVisible(false);
        }
        else
        {
            editCategoryButton.setVisible(true);
            removeCategoryButton.setVisible(true);
        }
        loadProductTable();
    }//GEN-LAST:event_categoryBoxItemStateChanged

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
    private javax.swing.JTextPane descriptionField;
    private javax.swing.JButton editCategoryButton;
    private javax.swing.JButton editProductButton;
    private javax.swing.JButton editVendorButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTextField nameField;
    private javax.swing.JTable productsTable;
    private javax.swing.JButton removeCategoryButton;
    private javax.swing.JButton removeProdutButton;
    private javax.swing.JButton removeVendorButton;
    private javax.swing.JTextField searchField;
    private javax.swing.JComboBox<String> taxBox;
    private javax.swing.JTable vendorsTable;
    // End of variables declaration//GEN-END:variables
}
