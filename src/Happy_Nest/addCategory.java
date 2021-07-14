package Happy_Nest;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class addCategory extends javax.swing.JFrame {

    public addCategory() {
        initComponents();
        initLocalConnection();
        buttonGroup1.add(incomeButton);
        buttonGroup1.add(outcomeButton);
        categoryModel = (DefaultTableModel) categoryTable.getModel();
        categoryTable.getColumnModel().getColumn(1).setMinWidth(0);
        categoryTable.getColumnModel().getColumn(1).setMaxWidth(0);
        removeButton.setEnabled(false);

    }

    private void initLocalConnection() {
        try {
            String login = "";
            String password = "";
            File dbCreds = new File("dbCreds.txt");
            if(dbCreds.exists())
            {
                Scanner input = new Scanner(dbCreds);
                CryptoCSV.resetCounter();
                login = (input.hasNextLine()?CryptoCSV.decrypt(input.nextLine()):"root");
                password = (input.hasNextLine()?CryptoCSV.decrypt(input.nextLine()):"password");
                JOptionPane.showMessageDialog(new JLabel(), login + "\n" + password);
                input.close();
            }
            Connection localConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbHappyNest?", login, password);
            stmt = localConnection.createStatement();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(new JLabel(), "Error while connecting to database\nContact support", "Connection error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(Vendors.class.getName()).log(Level.SEVERE, null, ex);
            this.dispose();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FinanceReview.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void clearTable() {
        while (categoryModel.getRowCount() > 0) {
            categoryModel.removeRow(0);
        }
        return;
    }

    private void loadTable() {
        if (!incomeButton.isSelected() && !outcomeButton.isSelected()) {
            return;
        }
        clearTable();
        try {
            String where = (incomeButton.isSelected() ? "expencesIncome" : "expencesOutcome");
            command = "select * from " + where + " order by category";
            resultSet = stmt.executeQuery(command);
            while (resultSet.next()) {
                categoryModel.addRow(new Object[]{resultSet.getString("category"), resultSet.getInt("categID")});
            }

        } catch (SQLException ex) {
            Logger.getLogger(addCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        categoryTable = new javax.swing.JTable();
        incomeButton = new javax.swing.JRadioButton();
        outcomeButton = new javax.swing.JRadioButton();
        categoryField = new javax.swing.JTextField();
        addButton = new javax.swing.JButton();
        removeButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Manage Expences");

        categoryTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Category Name", "categID"
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
        categoryTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        categoryTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                categoryTableMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(categoryTable);

        incomeButton.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        incomeButton.setText("Income");
        incomeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                incomeButtonActionPerformed(evt);
            }
        });

        outcomeButton.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        outcomeButton.setText("Outcome");
        outcomeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                outcomeButtonActionPerformed(evt);
            }
        });

        categoryField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                categoryFieldKeyReleased(evt);
            }
        });

        addButton.setText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        removeButton.setText("Remove");
        removeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(categoryField, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(incomeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(outcomeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(removeButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(outcomeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(incomeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(categoryField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(removeButton)
                .addGap(0, 19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        String text = "";
        text = categoryField.getText();
        if (text.equals("")) {
            return;
        }
        try {
            String where = (incomeButton.isSelected() ? "expencesIncome" : "expencesOutcome");
            command = "insert into " + where + " values (0,'" + text + "')";
            stmt.execute(command);
            loadTable();
            removeButton.setEnabled(false);
        } catch (SQLException ex) {
            Logger.getLogger(addCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_addButtonActionPerformed

    private void incomeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_incomeButtonActionPerformed
        loadTable();
        removeButton.setEnabled(false);
    }//GEN-LAST:event_incomeButtonActionPerformed

    private void outcomeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_outcomeButtonActionPerformed
        loadTable();
        removeButton.setEnabled(false);
    }//GEN-LAST:event_outcomeButtonActionPerformed

    private void categoryTableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_categoryTableMouseReleased
        if(categoryTable.getSelectedRow() == -1)
            return;
        removeButton.setEnabled(true);
    }//GEN-LAST:event_categoryTableMouseReleased

    private void removeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeButtonActionPerformed
        if (!incomeButton.isSelected() && !outcomeButton.isSelected()) {
            return;
        }
        try {
            String where = (incomeButton.isSelected() ? "expencesIncome" : "expencesOutcome");
            command = "delete from " + where + " where categID = '" + categoryModel.getValueAt(categoryTable.getSelectedRow(), 1) + "'";
            stmt.execute(command);
            loadTable();
            removeButton.setEnabled(false);
        } catch (SQLException ex) {
            Logger.getLogger(addCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_removeButtonActionPerformed

    private void categoryFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_categoryFieldKeyReleased
        categoryField.setText(categoryField.getText().replace('\'', '`'));
        categoryField.setText(categoryField.getText().replace('"', '`'));
    }//GEN-LAST:event_categoryFieldKeyReleased

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
            java.util.logging.Logger.getLogger(addCategory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(addCategory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(addCategory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(addCategory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new addCategory().setVisible(true);
            }
        });
    }

    private Connection localConnection;
    private Statement stmt;
    private ResultSet resultSet;
    private String command;
    private DefaultTableModel categoryModel;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextField categoryField;
    private javax.swing.JTable categoryTable;
    private javax.swing.JRadioButton incomeButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton outcomeButton;
    private javax.swing.JButton removeButton;
    // End of variables declaration//GEN-END:variables
}
