/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Happy_Nest;

import java.awt.Component;
import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Float.parseFloat;
import java.util.Scanner;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Yunix
 */
public class VendorsFrame extends javax.swing.JFrame {

    public VendorsFrame() {
        initComponents();
        
        loadFromFile();
        loadVendorList();
        setTax();
        Dimension maxSize = new Dimension(jTB.getWidth() - 10, jTB.getHeight());
        jTB.setMaximumSize(maxSize);
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        vendorsPanel = new javax.swing.JPanel();
        removeVendorButton = new javax.swing.JButton();
        addVendorButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        vendorsTable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        editVendorButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jTB = new javax.swing.JTabbedPane();
        tableButtonsPanel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();
        costField = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        lDesc = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        taxBox = new javax.swing.JComboBox<>();
        removeProductButton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        addCategoryButton = new javax.swing.JButton();
        removeCategoryButton = new javax.swing.JButton();
        editCategoryButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        lDetails = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        searchField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Happy Nest Vendors");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        jLabel1.setText("Vendors");

        vendorsPanel.setBackground(new java.awt.Color(204, 204, 204));

        removeVendorButton.setText("Remove");
        removeVendorButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 0), 5));
        removeVendorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeVendorButtonActionPerformed(evt);
            }
        });

        addVendorButton.setText("Add");
        addVendorButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 255, 0), 5));
        addVendorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addVendorButtonActionPerformed(evt);
            }
        });

        vendorsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Name"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        vendorsTable.setRowHeight(30);
        vendorsTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        vendorsTable.setShowHorizontalLines(true);
        vendorsTable.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                VendorsFrame.this.focusGained(evt);
            }
        });
        vendorsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                vendorsTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(vendorsTable);

        jLabel2.setBackground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Vendors List");

        editVendorButton.setText("Edit");
        editVendorButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 255, 0), 5));
        editVendorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editVendorButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout vendorsPanelLayout = new javax.swing.GroupLayout(vendorsPanel);
        vendorsPanel.setLayout(vendorsPanelLayout);
        vendorsPanelLayout.setHorizontalGroup(
            vendorsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(vendorsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(vendorsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                    .addComponent(removeVendorButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addVendorButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(editVendorButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        vendorsPanelLayout.setVerticalGroup(
            vendorsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, vendorsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addVendorButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(removeVendorButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editVendorButton, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                .addGap(18, 18, 18))
        );

        jLabel3.setText("Search");

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));

        jTB.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);

        jLabel4.setText("Name");

        jLabel5.setText("Cost");

        jLabel6.setText("Tax");

        jLabel7.setText("Description");

        lDesc.setColumns(20);
        lDesc.setRows(5);
        jScrollPane3.setViewportView(lDesc);

        jButton1.setText("Add Record");
        jButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 255, 0), 3));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Edit Record");
        jButton2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 255, 0), 3));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        taxBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "5%", "10%", "19%" }));

        removeProductButton.setText("Remove Record");
        removeProductButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 51), 3));
        removeProductButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeProductButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tableButtonsPanelLayout = new javax.swing.GroupLayout(tableButtonsPanel);
        tableButtonsPanel.setLayout(tableButtonsPanelLayout);
        tableButtonsPanelLayout.setHorizontalGroup(
            tableButtonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tableButtonsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tableButtonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tableButtonsPanelLayout.createSequentialGroup()
                        .addGroup(tableButtonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(nameField, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tableButtonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(costField, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tableButtonsPanelLayout.createSequentialGroup()
                        .addGroup(tableButtonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tableButtonsPanelLayout.createSequentialGroup()
                                .addComponent(taxBox, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4))
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(removeProductButton, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                .addContainerGap())
        );
        tableButtonsPanelLayout.setVerticalGroup(
            tableButtonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tableButtonsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tableButtonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tableButtonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(removeProductButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(tableButtonsPanelLayout.createSequentialGroup()
                        .addGroup(tableButtonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(costField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(taxBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2))
                    .addComponent(jScrollPane3))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tableButtonsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTB))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tableButtonsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

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

        lDetails.setColumns(20);
        lDetails.setRows(5);
        jScrollPane2.setViewportView(lDetails);

        jLabel8.setText("Details:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addCategoryButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(removeCategoryButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(editCategoryButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(addCategoryButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(removeCategoryButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editCategoryButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                .addContainerGap())
        );

        searchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                searchFieldKeyTyped(evt);
            }
        });

        jLabel9.setText("Search");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(vendorsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(searchField))
                .addGap(583, 583, 583)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addGap(36, 36, 36))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(vendorsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1201, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    
    
    // <editor-fold defaultstate="collapsed" desc="Event handlers">  
    
    // Dynamically make Handler for Catalogue Tables
    private void jTMouseClicked(java.awt.event.MouseEvent evt) {                                
        int ven = vendorsTable.getSelectedRow();
        int cat = jTB.getSelectedIndex();
        
        //JOptionPane.showMessageDialog(new JLabel(), "Vendor = " + ven + "\nCategory = " + cat);
        
        if (ven >= 0 && cat >= 0)
        {
            if(tables.get(ven).get(cat).getSelected() == -1)
                return;
                
            lDetails.setText(
                            "\nVendor name: " +
                            lista.get(ven).getName() + 
                            "\nCategory: " + 
                            tables.get(ven).get(cat).getCatalogueName() +
                            "\nName: " +
                            lista.get(ven).getProduct(cat, tables.get(ven).get(cat).getSelected()).getName() +
                            "\nDescription: " +
                            lista.get(ven).getProduct(cat, tables.get(ven).get(cat).getSelected()).getDescription()
            );
        }
    }
    
    // Add a record
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int tabIndex = jTB.getSelectedIndex();
        int sel = vendorsTable.getSelectedRow();
       
        if(sel < 0)
        {
            JOptionPane.showMessageDialog(new JLabel(), "Select vendor please");
            return;
        }
        // Add attention
        if (tabIndex == -1 || nameField.equals("") || costField.equals(""))
            return;
        if (!isFloat(costField.getText()))
            return;
        
        // String name, String description, float cost, int tax
        Product newProduct = new Product(
                nameField.getText(),
                lDesc.getText(),
                Float.parseFloat(costField.getText()),
                taxes.get(taxBox.getSelectedIndex()));
        
        lista.get(vendorsTable.getSelectedRow()).addProduct(tabIndex, newProduct);
        lista.get(vendorsTable.getSelectedRow()).sortByName(tabIndex);
        //loadTab(tabIndex);
        loadTab(sel, tabIndex);
        jTB.setSelectedIndex(tabIndex);
        
        //Initialize the fields after record addition
        nameField.setText("");
        costField.setText("");
        lDesc.setText("");
    }//GEN-LAST:event_jButton1ActionPerformed
                          
    //edit vendor button
    private void editVendorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editVendorButtonActionPerformed
        int ven = vendorsTable.getSelectedRow();
        if(ven == -1)
            return;
        AddVendor addVendor = new AddVendor(lista.get(ven).getName(), lista.get(ven).getDesc(), true);
        PassVendor listener = new AddToList();
        addVendor.addListener(listener);
        addVendor.setVisible(true);
    }//GEN-LAST:event_editVendorButtonActionPerformed

        
    // selected vendor
    private void focusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_focusGained
        editVendorButton.setVisible(true);
        int index = vendorsTable.getSelectedRow();
        if(index != -1)
        {
            loadProducts(index);
            // Details
            lDetails.setText(
                            "Date Created: " +
                            lista.get(index).getSDate() + 
                            "\n" + 
                            lista.get(index).getDesc());
        }
    }//GEN-LAST:event_focusGained
    
    // Add Vendor
    private void addVendorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addVendorButtonActionPerformed

        AddVendor addVendor = new AddVendor(null, null, false);
        PassVendor listener = new AddToList();
        addVendor.addListener(listener);
        addVendor.setVisible(true);
        
    }//GEN-LAST:event_addVendorButtonActionPerformed

    //Vendor Selected
    private void vendorsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vendorsTableMouseClicked
        editVendorButton.setVisible(true);
        int index = vendorsTable.getSelectedRow();
        if(index != -1)
        {
            loadProducts(index);
            // Details
            lDetails.setText(
                            "Date Created: " +
                            lista.get(index).getSDate() + 
                            "\n" + 
                            lista.get(index).getDesc());
            // Details
        }
    }//GEN-LAST:event_vendorsTableMouseClicked
    
    //Add Category
    private void addCategoryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addCategoryButtonActionPerformed
        int index = vendorsTable.getSelectedRow();
        if(index == -1)
            return;
        String catName = JOptionPane.showInputDialog( "Enter category name:" );
        if(!catName.isEmpty())
        {
            lista.get(index).addCategory(catName);
            CatalogueTable table = createNewTable();
            table.setCatalogueName(catName);
            tables.get(index).add(table);
            jTB.add(catName, table.getComponent());
        }
    }//GEN-LAST:event_addCategoryButtonActionPerformed

    //Rename Category
    private void editCategoryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editCategoryButtonActionPerformed
        int index = vendorsTable.getSelectedRow();
        if(index == -1)
            return;
        if(jTB.getSelectedIndex() != -1)
        {
            String catName = JOptionPane.showInputDialog( "Enter category name:" );
            if(!catName.isEmpty())
            {
                jTB.setTitleAt(jTB.getSelectedIndex(), catName);
                tables.get(index).get(jTB.getSelectedIndex()).setCatalogueName(catName);
                lista.get(index).setCategoryName(jTB.getSelectedIndex(), catName);
            }
        }
    }//GEN-LAST:event_editCategoryButtonActionPerformed

    //remove category
    private void removeCategoryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeCategoryButtonActionPerformed
        int ven = vendorsTable.getSelectedRow();
        int cat = jTB.getSelectedIndex();
        
        if(ven < 0 || cat < 0)
            return;
        
        if (okcancel("Are you sure you want to remove " + lista.get(ven).getCategoryName(cat) + " ?") == 0) {
            tables.get(ven).remove(cat);
            lista.get(ven).removeCategory(cat);
            loadProducts(ven);
        }
    }//GEN-LAST:event_removeCategoryButtonActionPerformed

    //Remove vendor
    private void removeVendorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeVendorButtonActionPerformed
        int ven = vendorsTable.getSelectedRow();
        if(ven != -1)
        {
            if(okcancel("Are you sure you want to remove " + lista.get(ven).getName() + " ?") == 0)
            {
                lista.remove(ven);
                tables.remove(ven);
                jTB.removeAll();
                loadVendorList();
            }
        }
    }//GEN-LAST:event_removeVendorButtonActionPerformed

    //Search Bar
    private void searchFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchFieldKeyTyped
        String text = searchField.getText();
        boolean foundSomething = false;
        if(text.isEmpty())
        {
            lDetails.setText("Nothing Found");
            loadVendorList();
            if(lista.size() != 0)
                loadProducts(0);
            return;
        }
        clearVendorsTable();
        DefaultTableModel model = (DefaultTableModel) vendorsTable.getModel();
        
        CatalogueTable table = new CatalogueTable();
        table.setCatalogueName("Search Results");
        table.makeItSearchTable();
        DefaultTableModel cmodel = (DefaultTableModel) table.getModel();
        
        
        jTB.removeAll();
        jTB.add(table.getCatalogueName(), table.getComponent());
        
        Product pr;
        for(int i = 0; i < lista.size(); i++)
        {
            if(lista.get(i).getName().toLowerCase().lastIndexOf(text.toLowerCase()) != -1)
            {
                model.addRow(new Object[] {lista.get(i).getName()});
                foundSomething = true;
            }
            
            for (int c = 0; c < lista.get(i).getCategoriesNum(); c++) {
                
                for (int p = 0; p < lista.get(i).getProductsNum(c); p++) {
                    
                    pr = lista.get(i).getProduct(c, p);
                    if (pr.getName().toLowerCase().lastIndexOf(text.toLowerCase()) != -1) {
                        cmodel.addRow(new Object[]{
                            pr.getName(),
                            pr.getCost(),
                            pr.getCost() + (pr.getCost() * pr.getTax() / 100),
                            pr.getTax() + "%",
                            pr.getSDate(),
                            lista.get(i).getName()});
                        foundSomething = true;
                    }
                }
            }
        }
        
        for(int i = 0; i < lista.size(); i++)
        {
            if(lista.get(i).getDesc().toLowerCase().lastIndexOf(text.toLowerCase()) != -1)
            {
                model.addRow(new Object[] {lista.get(i).getName()});
                foundSomething = true;
            }
            
            for (int c = 0; c < lista.get(i).getCategoriesNum(); c++) {
                
                for (int p = 0; p < lista.get(i).getProductsNum(c); p++) {
                    
                    pr = lista.get(i).getProduct(c, p);
                    if (pr.getDescription().toLowerCase().lastIndexOf(text.toLowerCase()) != -1) {
                        cmodel.addRow(new Object[]{
                            pr.getName(),
                            pr.getCost(),
                            pr.getCost() + (pr.getCost() * pr.getTax() / 100),
                            pr.getTax() + "%",
                            pr.getSDate(),
                            lista.get(i).getName()});
                        foundSomething = true;
                    }
                }
            }
        }
        
        if(foundSomething)
            lDetails.setText("Results found");
        else
            lDetails.setText("Results not found");
    }//GEN-LAST:event_searchFieldKeyTyped

    //edit record
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int ven = vendorsTable.getSelectedRow();
        int cat = jTB.getSelectedIndex();
        if(ven < 0 || cat < 0)
            return;
        
        int pro = tables.get(ven).get(cat).getSelected();
        
        if(pro < 0)
            return;
        if(!nameField.getText().isBlank())
        {
            lista.get(ven).getProduct(cat, pro).setName(nameField.getText());
            
        }
        
        if(!costField.getText().isBlank())
        {
            if(isFloat(costField.getText()))
            {
                lista.get(ven).getProduct(cat, pro).setCost(Float.parseFloat(costField.getText()));
            }
            else
            {
                JOptionPane.showMessageDialog(new JLabel(), "Incorrect value in cost Field");
            }
        }
        
        lista.get(ven).getProduct(cat, pro).setTax(taxes.get(taxBox.getSelectedIndex()));
        
        if(!lDesc.getText().isBlank())
        {
            lista.get(ven).getProduct(cat, pro).setDescription(lDesc.getText());
        }
        loadTab(ven, cat);
    }//GEN-LAST:event_jButton2ActionPerformed

    //remove a record
    private void removeProductButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeProductButtonActionPerformed
        
        int ven = vendorsTable.getSelectedRow();
        int cat = jTB.getSelectedIndex();
        if(ven < 0 || cat < 0)
            return;
        
        int pro = tables.get(ven).get(cat).getSelected();
        
        if(pro < 0)
            return;
        
        if(okcancel("Are you sure you want to remove " + lista.get(ven).getProduct(cat, pro).getName() + " ?") == 0)
        {
            lista.get(ven).removeAt(cat, pro);
            loadTab(ven, cat);
        }
        
    }//GEN-LAST:event_removeProductButtonActionPerformed
    
    //frame closing (save to file)
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        saveToFile();
    }//GEN-LAST:event_formWindowClosing
    // </editor-fold> 
    
    // load default taxes
    private void setTax()
    {
        taxes.add(5);
        taxes.add(10);
        taxes.add(19);
    }
    
    
    // refresh a specific tab
    private void loadTab(int ven, int cat) {
        DefaultTableModel model = (DefaultTableModel) tables.get(ven).get(cat).getModel();

        while (model.getRowCount() != 0) {
            model.removeRow(0);
        }

        Product p;
        for (int i = 0; i < lista.get(ven).getProductsNum(cat); i++) {
            p = lista.get(ven).getProduct(cat, i);
            model.addRow(new Object[]{
                p.getName(),
                p.getCost(),
                p.getCost() + (p.getCost() * p.getTax() / 100),
                p.getTax() + "%",
                p.getSDate()});
        }
    }
    
    // load tabs and categories
    private void loadProducts(int vendor)
    {
        jTB.removeAll();
        Product temp = new Product();
        
        for(int c = 0; c < lista.get(vendor).getCategoriesNum(); c++)
        {
            
            DefaultTableModel iModel = tables.get(vendor).get(c).getModel();
            jTB.add(lista.get(vendor).getCategoryName(c), tables.get(vendor).get(c).getComponent());
            
            while(iModel.getRowCount()!=0)
                iModel.removeRow(0);
            
            for(int p = 0; p < lista.get(vendor).getProductsNum(c); p++)
            {
                temp = lista.get(vendor).getProduct(c, p);
                // Name | Cost | Cost + % | Tax | Date
                iModel.addRow(new Object[]{ 
                    temp.getName(),
                    temp.getCost(),
                    temp.getCost() + (temp.getCost() * temp.getTax() / 100),
                    temp.getTax() + "%",
                    temp.getSDate() });
            }
            //newTable.setVisible(true);
            
        }
    }
    
    // sort vendors
    private void sortList()
    {
        Vendor temp = new Vendor();
        Vector < CatalogueTable > tempC = new Vector < CatalogueTable >();
        for(int i = 0; i < lista.size(); i++)
        {
            for(int j = i + 1; j < lista.size(); j++)
            {
                if (lista.get(i).getName().compareToIgnoreCase(lista.get(j).getName()) > 0)
                {
                    temp = lista.get(i);
                    lista.setElementAt(lista.get(j), i);
                    lista.setElementAt(temp, j);
                    
                    tempC = tables.get(i);
                    tables.setElementAt(tables.get(j), i);
                    tables.setElementAt(tempC, j);
                }
            }
        }
    }
    
    // clear vendors table
    private void clearVendorsTable()
    {
        DefaultTableModel model = (DefaultTableModel) vendorsTable.getModel();
        while(model.getRowCount() != 0)
                model.removeRow(0);
        
    }
    
    // load vendors table
    private void loadVendorList()
    {
        sortList();
        clearVendorsTable();
        DefaultTableModel model = (DefaultTableModel) vendorsTable.getModel();
        for(int i = 0; i < lista.size(); i++)
            model.addRow(new Object[] {lista.get(i).getName()});
    }
    
    // add a vendor
    class AddToList implements PassVendor
    {
        public void pass(String pname, String pdesc, boolean edit) // Callback from AddVendor function
        {
        //JOptionPane.showMessageDialog(new JLabel(), pname + "\n" + pdesc);
            if(edit)
            {
                int ven = vendorsTable.getSelectedRow();
                if(ven == -1)
                    return;
                lista.get(ven).setName(pname);
                lista.get(ven).setDescription(pdesc);
            }
            else
            {
                Vendor newVendor = new Vendor(pname, pdesc);
                lista.add(newVendor);
                tables.add(new Vector < CatalogueTable > ());
                tables.get(lista.size() - 1).add(createNewTable());
            }
            loadVendorList();
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="Helpful methods (isFloat, okcancel)">
    public boolean isFloat(String text)
    {
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
            java.util.logging.Logger.getLogger(VendorsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VendorsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VendorsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VendorsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VendorsFrame().setVisible(true);
            }
        });
    }
    
    public CatalogueTable createNewTable() {
        CatalogueTable table = new CatalogueTable();
        table.addListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTMouseClicked(evt);
            }
        });
        //table.add(jPF); Add pop-up menu here?
        return table;
    }
    
    private void saveToFile() {
        File ven = new File("Ven.file");
        File pro = new File("Pro.file");
        if (ven.exists()) {
            ven.delete();
        }
        try {
            ven.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(VendorsFrame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(new JLabel(), "Error while creating file, contact konikyuri@gmail.com");
        }

        if (pro.exists()) {
            pro.delete();
        }
        try {
            pro.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(VendorsFrame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(new JLabel(), "Error while creating file, contact konikyuri@gmail.com");
        }

        try {
            FileWriter vFW = new FileWriter("ven.file");
            FileWriter pFW = new FileWriter("pro.file");

            for (int i = 0; i < lista.size(); i++) {
                vFW.write(lista.get(i).getName() + "\n");
                vFW.write(lista.get(i).getCreationTime() + "\n");
                vFW.write(lista.get(i).getDesc().trim() + "\n##endOfDesc##\n");

                for (int c = 0; c < lista.get(i).getCategoriesNum(); c++) {
                    vFW.write(lista.get(i).getCategoryName(c) + "\n");
                    for (int p = 0; p < lista.get(i).getProductsNum(c); p++) {
                        pFW.write(i + "\n");
                        pFW.write(c + "\n");
                        pFW.write(lista.get(i).getProduct(c, p).getName() + "\n");
                        pFW.write(lista.get(i).getProduct(c, p).getCreationTime() + "\n");
                        pFW.write(lista.get(i).getProduct(c, p).getTax() + "\n");
                        pFW.write(lista.get(i).getProduct(c, p).getCost() + "\n");
                        pFW.write(lista.get(i).getProduct(c, p).getDescription().trim() + "\n##endOfRecord##\n");
                    }
                }
                vFW.write("\n##endOfCat##\n");
            }

            vFW.close();
            pFW.close();

        } catch (IOException ex) {
            Logger.getLogger(VendorsFrame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(new JLabel(), "Error while writing to file, contact konikyuri@gmail.com");
        }

    }

    private void loadFromFile() {
        File ven = new File("Ven.file");
        File pro = new File("Pro.file");
        if (ven.exists()) {
            
            /*
                vFW.write(lista.get(i).getName() + "\n");
                vFW.write(lista.get(i).getCreationTime() + "\n");
                vFW.write(lista.get(i).getDesc().trim() + "\n##endOfDesc##\n");
             */
            
            Scanner vFR;

            try {
                
                vFR = new Scanner(ven);
                while (vFR.hasNextLine()) {
                    Vendor vendor = new Vendor();
                    vendor.setName(vFR.nextLine());
                    //JOptionPane.showMessageDialog(new JLabel(), vendor.getName());
                    if (vendor.getName().isBlank()) {
                        break;
                    }
                    vendor.setDate(Long.parseLong(vFR.nextLine()));
                    String desc = "";
                    String line;

                    line = vFR.nextLine();
                    while (!line.equals("##endOfDesc##")) {
                        desc += "\n" + line;
                        line = vFR.nextLine();
                    }

                    vendor.setDescription(desc);
                    //vFW.write("\n##endOfCat##\n");

                    line = vFR.nextLine();
                    lista.add(vendor);
                    tables.add(new Vector< CatalogueTable>());

                    while (!line.equals("##endOfCat##")) {
                        lista.get(lista.size() - 1).addCategory(line);
                        tables.get(lista.size() - 1).add(createNewTable());
                        tables.get(lista.size() - 1).get(tables.get(lista.size() - 1).size() - 1).setCatalogueName(line);
                        line = vFR.nextLine();
                    }

                }
                vFR.close();

            } catch (FileNotFoundException ex) {
                Logger.getLogger(VendorsFrame.class.getName()).log(Level.SEVERE, "File not found VendorsFrame", ex);
            }
        }

        if (pro.exists()) {
            Scanner pFR;
            try {
                pFR = new Scanner(pro);

                /*
                pFW.write(i + "\n");
                pFW.write(c + "\n");
                pFW.write(lista.get(i).getProduct(c, p).getName() + "\n");
                pFW.write(lista.get(i).getProduct(c, p).getCreationTime() + "\n");
                pFW.write(lista.get(i).getProduct(c, p).getTax() + "\n");
                pFW.write(lista.get(i).getProduct(c, p).getDescription().trim() + "\n##endOfRecord##\n");
                 */
                while (pFR.hasNextLine()) {
                    Product product = new Product();
                    String vv = pFR.nextLine();
                    if (vv.equals("")) {
                        break;
                    }
                    int v = Integer.parseInt(vv);
                    int c = Integer.parseInt(pFR.nextLine());
                    product.setName(pFR.nextLine());
                    product.setDate(Long.parseLong(pFR.nextLine()));
                    product.setTax(Integer.parseInt(pFR.nextLine()));
                    product.setCost(Float.parseFloat(pFR.nextLine()));
                    String desc = "";
                    String line;

                    line = pFR.nextLine();
                    while (!line.equals("##endOfRecord##")) {
                        desc += "\n" + line;
                        line = pFR.nextLine();
                    }

                    product.setDescription(desc.trim());
                    lista.get(v).addProduct(c, product);
                }
                pFR.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(VendorsFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    private JPopupMenu jPF = new JPopupMenu();
    private Vector < Vector < CatalogueTable > > tables = new Vector < Vector < CatalogueTable > >();
    private Vector < Integer > taxes = new Vector < Integer > ();
    private DefaultTableModel searchModel;
    private DefaultTableModel tModel = new DefaultTableModel();
    private Vector < Vendor > lista = new Vector < Vendor > ();
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addCategoryButton;
    private javax.swing.JButton addVendorButton;
    private javax.swing.JTextField costField;
    private javax.swing.JButton editCategoryButton;
    private javax.swing.JButton editVendorButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTB;
    private javax.swing.JTextArea lDesc;
    private javax.swing.JTextArea lDetails;
    private javax.swing.JTextField nameField;
    private javax.swing.JButton removeCategoryButton;
    private javax.swing.JButton removeProductButton;
    private javax.swing.JButton removeVendorButton;
    private javax.swing.JTextField searchField;
    private javax.swing.JPanel tableButtonsPanel;
    private javax.swing.JComboBox<String> taxBox;
    private javax.swing.JPanel vendorsPanel;
    private javax.swing.JTable vendorsTable;
    // End of variables declaration//GEN-END:variables
}
