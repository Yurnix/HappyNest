/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Happy_Nest;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Yunix
 */
public class FinanceReview extends javax.swing.JFrame {

    /**
     * Creates new form FinanceReview
     */
    public FinanceReview() {
        initComponents();
        initLocalConnection();
        calendarModel = (DefaultTableModel) calendarTable.getModel();
        resultModel = (DefaultTableModel) resultTable.getModel();
        loadCalendar();
        
        resultTable.getColumnModel().getColumn(0).setMinWidth(200);
        resultTable.getColumnModel().getColumn(0).setMaxWidth(200);
        holdTo = 0; // for the result table
        try {
            
            incomeCategories = new Vector < String > ();
            outcomeCategories = new Vector < String > ();
            
            command = "select category from expencesIncome";
            resultSet = stmt.executeQuery(command);
            while (resultSet.next()) {
                incomeCategories.add(resultSet.getString("category"));
            }
            
            command = "select category from expencesOutcome";
            resultSet = stmt.executeQuery(command);
            while (resultSet.next()) {
                outcomeCategories.add(resultSet.getString("category"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(FinanceReview.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
        //resultTable.getModel().setValueAt( "<html>Text color: <font color='red'>red</font></html>", 0, 0);
        
    }

    private void initLocalConnection() {
        try {
            Connection localConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbHappyNest?", "root", "ebisonovsekonem");
            stmt = localConnection.createStatement();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(new JLabel(), "Error while connecting to database\nContact support", "Connection error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(Vendors.class.getName()).log(Level.SEVERE, null, ex);
            super.dispose();
        }
    }
    
    private void clearCalendarTable() {
        while (calendarModel.getRowCount() > 0) {
            calendarModel.removeRow(0);
        }
        return;
    }
    
    private void clearResultTable() {
        while (resultModel.getRowCount() > holdTo) {
            resultModel.removeRow(resultModel.getRowCount() - 1);
        }
        return;
    }
    
    private void loadCalendar() {
        //calendar.setTimeInMillis(System.currentTimeMillis());
        //System.out.print(aDate.getDate());
        int aDay = 0;
        if ((aDate.getYear() + 1900) % 400 == 0 || ((aDate.getYear() + 1900) % 4 == 0 && (aDate.getYear() + 1900) % 100 != 0)) {
            aDay = 1;
        }
        clearCalendarTable();
        if (scaleBox.getSelectedIndex() == 0) {
            for (int i = 1; i <= daysInMonth[aDate.getMonth()] + (aDate.getMonth() == 1 ? aDay : 0); i++) {
                calendarModel.addRow(new Object[]{
                    String.valueOf(i)
                    + "/"
                    + String.valueOf(aDate.getMonth() + 1)
                    + "/"
                    + String.valueOf(aDate.getYear() + 1900)});
            }
        } else if (scaleBox.getSelectedIndex() == 1) {
            for (int i = 0; i < 12; i++) {
                calendarModel.addRow(new Object[]{
                    months[i] + "/" + (aDate.getYear() + 1900)
                });
            }
            calendarTable.setRowSelectionInterval(0, 0);
        }
        else {
            try {
                int imin = -1;
                command = "select min(iyear) from income";
                resultSet = stmt.executeQuery(command);
                if(resultSet.next()) {
                    imin = resultSet.getInt("min(iyear)");
                }
                command = "select min(iyear) from outcome";
                resultSet = stmt.executeQuery(command);
                if(resultSet.next()) {
                    imin = methods.min(resultSet.getInt("min(iyear)"),imin);
                }
                int year = aDate.getYear() + 1900;
                for (int i = imin; i <= year; i++) {
                calendarModel.addRow(new Object[]{
                    i
                });
            }
            } catch (SQLException ex) {
                Logger.getLogger(FinanceReview.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        calendarTable = new javax.swing.JTable();
        goBackButton = new javax.swing.JButton();
        goForwordButton = new javax.swing.JButton();
        scaleBox = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        resultTable = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        infoPane = new javax.swing.JTextArea();
        holdRecordsButton = new javax.swing.JButton();
        releaseRecordsButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        calendarTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        calendarTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                calendarTableMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(calendarTable);
        if (calendarTable.getColumnModel().getColumnCount() > 0) {
            calendarTable.getColumnModel().getColumn(0).setResizable(false);
        }

        goBackButton.setText("<<");
        goBackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goBackButtonActionPerformed(evt);
            }
        });

        goForwordButton.setText(">>");
        goForwordButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goForwordButtonActionPerformed(evt);
            }
        });

        scaleBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Daily", "Monthly", "Annually" }));
        scaleBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scaleBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(goBackButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scaleBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(goForwordButton)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(goBackButton)
                    .addComponent(goForwordButton)
                    .addComponent(scaleBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1))
        );

        resultTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date", "Total Income", "Total Outcome", "NET"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(resultTable);

        infoPane.setColumns(20);
        infoPane.setRows(5);
        jScrollPane4.setViewportView(infoPane);

        holdRecordsButton.setText("Hold Records");
        holdRecordsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                holdRecordsButtonActionPerformed(evt);
            }
        });

        releaseRecordsButton.setText("Release Records");
        releaseRecordsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                releaseRecordsButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(holdRecordsButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(releaseRecordsButton)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(holdRecordsButton)
                    .addComponent(releaseRecordsButton))
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
                    .addComponent(jScrollPane4))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void scaleBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scaleBoxActionPerformed
        if(scaleBox.getSelectedIndex() == 2) {
            goBackButton.setEnabled(false);
            goForwordButton.setEnabled(false);
            aDate.setTime(System.currentTimeMillis());
        } else {
            goBackButton.setEnabled(true);
            goForwordButton.setEnabled(true);
        }
        loadCalendar();
    }//GEN-LAST:event_scaleBoxActionPerformed

    private void goBackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goBackButtonActionPerformed
        if (scaleBox.getSelectedIndex() == 0) {
            aDate.setMonth(aDate.getMonth() - 1);
        } else {
            aDate.setYear(aDate.getYear() - 1);
        }
        loadCalendar();
    }//GEN-LAST:event_goBackButtonActionPerformed

    private void goForwordButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goForwordButtonActionPerformed
        if (scaleBox.getSelectedIndex() == 0) {
            aDate.setMonth(aDate.getMonth() + 1);
        } else {
            aDate.setYear(aDate.getYear() + 1);
        }
        loadCalendar();
    }//GEN-LAST:event_goForwordButtonActionPerformed

    private void calendarTableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_calendarTableMouseReleased
        if(calendarTable.getSelectedRow() == -1)
            return;
        float total_income = 0,total_outcome = 0, net = 0, income, outcome, temp;
        Date tempDate = new Date();
        int first = -1, last = -1;
        try {
            if(scaleBox.getSelectedIndex() == 0) { // daily
                first = calendarTable.getSelectedRow() + 1;
                last = calendarTable.getSelectedRow() + calendarTable.getSelectedRowCount();
                income = outcome = 0;
                clearResultTable();
                
                for (int i = first; i <= last; i++) {
                    // ===================== Get from income ==========================
                    command = "select sum(amount) from income where iyear = "
                            + (aDate.getYear() + 1900)
                            + " and imonth = "
                            + (aDate.getMonth() + 1)
                            + " and iday = "
                            + i;
                    resultSet = stmt.executeQuery(command);
                    if(resultSet.next()) {
                        income = resultSet.getFloat("sum(amount)");
                    }
                    // ===================== Get from outcome ==========================
                    command = "select sum(amount) from outcome where iyear = "
                            + (aDate.getYear() + 1900)
                            + " and imonth = "
                            + (aDate.getMonth() + 1)
                            + " and iday = "
                            + i;
                    resultSet = stmt.executeQuery(command);
                    if(resultSet.next()) {
                        outcome = resultSet.getFloat("sum(amount)");
                    }
                    total_income += income;
                    total_outcome += outcome;
                    if (!(income == 0 && outcome == 0))
                        resultModel.addRow(new Object[]{
                            calendarModel.getValueAt(i - 1, 0),
                            "<html><font color='green'>" + income + "</font></html>",
                            "<html><font color='red'>" + outcome + "</font></html>",
                            "<html><font color='"
                            + ((income - outcome) > 0 ? "green" : "red")
                            + "'>"
                            + (income - outcome) + "</font></html>"});
                }
                // information by categories
                if (first != last)
                    resultModel.addRow(new Object[]{
                        "<html><font color='blue'>" 
                                + calendarModel.getValueAt(first - 1, 0) 
                                + " - " 
                                + calendarModel.getValueAt(last - 1, 0) 
                                + "</font></html>",
                        "<html><font color='green'>" + total_income + "</font></html>",
                        "<html><font color='red'>" + total_outcome + "</font></html>",
                        "<html><font color='"
                        + ((total_income - total_outcome) > 0 ? "green" : "red")
                        + "'>"
                        + (total_income - total_outcome) + "</font></html>"
                    });
                
                
                text = ((first == last) 
                        ? calendarModel.getValueAt(first - 1, 0)
                        : calendarModel.getValueAt(first - 1, 0) + " - " + calendarModel.getValueAt(last - 1, 0))
                        + "\n\nIncome:";
                for(int i = 0; i < incomeCategories.size(); i++) {
                    command = "select sum(amount) from income where iday >= "
                            + first
                            + " and iday <= "
                            + last
                            + " and imonth = "
                            + (aDate.getMonth() + 1)
                            + " and iyear = "
                            + (aDate.getYear() + 1900)
                            + " and iname = '"
                            + incomeCategories.get(i)
                            + "'";
                    resultSet = stmt.executeQuery(command);
                    temp = 0;
                    if(resultSet.next())
                        temp = resultSet.getFloat("sum(amount)");
                    income += temp;
                    if(temp != 0)
                        text += "\n" + incomeCategories.get(i) + ": " + temp;
                }
                text += "\n...other: " + (total_income - income);
                
                text += "\n\nOutcome:";
                outcome = 0;
                for(int i = 0; i < outcomeCategories.size(); i++) {
                    command = "select sum(amount) from outcome where iday >= "
                            + first
                            + " and iday <= "
                            + last
                            + " and imonth = "
                            + (aDate.getMonth() + 1)
                            + " and iyear = "
                            + (aDate.getYear() + 1900)
                            + " and iname = '"
                            + outcomeCategories.get(i)
                            + "'";
                    resultSet = stmt.executeQuery(command);
                    temp = 0;
                    if(resultSet.next())
                        temp = resultSet.getFloat("sum(amount)");
                    outcome += temp;
                    if(temp != 0)
                        text += "\n" + outcomeCategories.get(i) + ": " + temp;
                }
                text += "\n...other: " + (total_outcome - outcome);
                infoPane.setText(text);
                    
            }
            else if(scaleBox.getSelectedIndex() == 1) { // monthly
                
                first = calendarTable.getSelectedRow() + 1;
                last = calendarTable.getSelectedRow() + calendarTable.getSelectedRowCount();
                income = outcome = 0;
                clearResultTable();
                
                for (int i = first; i <= last; i++) {
                    // ===================== Get from income ==========================
                    command = "select sum(amount) from income where iyear = "
                            + (aDate.getYear() + 1900)
                            + " and imonth = "
                            + i;
                    resultSet = stmt.executeQuery(command);
                    if(resultSet.next()) {
                        income = resultSet.getFloat("sum(amount)");
                    }
                    // ===================== Get from outcome ==========================
                    command = "select sum(amount) from outcome where iyear = "
                            + (aDate.getYear() + 1900)
                            + " and imonth = "
                            + i;
                    resultSet = stmt.executeQuery(command);
                    if(resultSet.next()) {
                        outcome = resultSet.getFloat("sum(amount)");
                    }
                    total_income += income;
                    total_outcome += outcome;
                    if (!(income == 0 && outcome == 0))
                        resultModel.addRow(new Object[]{
                            calendarModel.getValueAt(i - 1, 0),
                            "<html><font color='green'>" + income + "</font></html>",
                            "<html><font color='red'>" + outcome + "</font></html>",
                            "<html><font color='"
                            + ((income - outcome) > 0 ? "green" : "red")
                            + "'>"
                            + (income - outcome) + "</font></html>"});
                }
                // information by categories
                if (first != last)
                    resultModel.addRow(new Object[]{
                        "<html><font color='blue'>" 
                                + calendarModel.getValueAt(first - 1, 0) 
                                + " - " 
                                + calendarModel.getValueAt(last - 1, 0) 
                                + "</font></html>",
                        "<html><font color='green'>" + total_income + "</font></html>",
                        "<html><font color='red'>" + total_outcome + "</font></html>",
                        "<html><font color='"
                        + ((total_income - total_outcome) > 0 ? "green" : "red")
                        + "'>"
                        + (total_income - total_outcome) + "</font></html>"
                    });
                
                
                text = ((first == last) 
                        ? calendarModel.getValueAt(first - 1, 0)
                        : calendarModel.getValueAt(first - 1, 0) + " - " + calendarModel.getValueAt(last - 1, 0))
                        + "\n\nIncome:";
                for(int i = 0; i < incomeCategories.size(); i++) {
                    command = "select sum(amount) from income where imonth >= "
                            + first
                            + " and imonth <= "
                            + last
                            + " and iyear = "
                            + (aDate.getYear() + 1900)
                            + " and iname = '"
                            + incomeCategories.get(i)
                            + "'";
                    resultSet = stmt.executeQuery(command);
                    temp = 0;
                    if(resultSet.next())
                        temp = resultSet.getFloat("sum(amount)");
                    income += temp;
                    if(temp != 0)
                        text += "\n" + incomeCategories.get(i) + ": " + temp;
                }
                text += "\n...other: " + (total_income - income);
                
                text += "\n\nOutcome:";
                outcome = 0;
                for(int i = 0; i < outcomeCategories.size(); i++) {
                    command = "select sum(amount) from outcome where imonth >= "
                            + first
                            + " and imonth <= "
                            + last
                            + " and iyear = "
                            + (aDate.getYear() + 1900)
                            + " and iname = '"
                            + outcomeCategories.get(i)
                            + "'";
                    resultSet = stmt.executeQuery(command);
                    temp = 0;
                    if(resultSet.next())
                        temp = resultSet.getFloat("sum(amount)");
                    outcome += temp;
                    if(temp != 0)
                        text += "\n" + outcomeCategories.get(i) + ": " + temp;
                }
                text += "\n...other: " + (total_outcome - outcome);
                infoPane.setText(text);
                
            }
            else { // annualy
                first = calendarTable.getSelectedRow();
                last = calendarTable.getSelectedRow() + calendarTable.getSelectedRowCount() - 1;
                income = outcome = 0;
                clearResultTable();
                int first_year = Integer.parseInt(calendarTable.getValueAt(first, 0).toString());
                for (int i = first; i <= last; i++) {
                    // ===================== Get from income ==========================
                    command = "select sum(amount) from income where iyear = "
                            + (first_year + i);
                    resultSet = stmt.executeQuery(command);
                    if(resultSet.next()) {
                        income = resultSet.getFloat("sum(amount)");
                    }
                    // ===================== Get from outcome ==========================
                    command = "select sum(amount) from outcome where iyear = "
                            + (first_year + i);
                    resultSet = stmt.executeQuery(command);
                    if(resultSet.next()) {
                        outcome = resultSet.getFloat("sum(amount)");
                    }
                    total_income += income;
                    total_outcome += outcome;
                    if (!(income == 0 && outcome == 0))
                        resultModel.addRow(new Object[]{
                            calendarModel.getValueAt(i, 0),
                            "<html><font color='green'>" + income + "</font></html>",
                            "<html><font color='red'>" + outcome + "</font></html>",
                            "<html><font color='"
                            + ((income - outcome) > 0 ? "green" : "red")
                            + "'>"
                            + (income - outcome) + "</font></html>"});
                }
                // information by categories
                if (first != last)
                    resultModel.addRow(new Object[]{
                        "<html><font color='blue'>" 
                                + calendarModel.getValueAt(first, 0) 
                                + " - " 
                                + calendarModel.getValueAt(last, 0) 
                                + "</font></html>",
                        "<html><font color='green'>" + total_income + "</font></html>",
                        "<html><font color='red'>" + total_outcome + "</font></html>",
                        "<html><font color='"
                        + ((total_income - total_outcome) > 0 ? "green" : "red")
                        + "'>"
                        + (total_income - total_outcome) + "</font></html>"
                    });
                
                
                text = ((first == last) 
                        ? calendarModel.getValueAt(first, 0)
                        : calendarModel.getValueAt(first, 0) + " - " + calendarModel.getValueAt(last, 0))
                        + "\n\nIncome:";
                for(int i = 0; i < incomeCategories.size(); i++) {
                    command = "select sum(amount) from income where iyear >= "
                            + first_year
                            + " and iyear <= "
                            + (first_year + last)
                            + " and iname = '"
                            + incomeCategories.get(i)
                            + "'";
                    resultSet = stmt.executeQuery(command);
                    temp = 0;
                    if(resultSet.next())
                        temp = resultSet.getFloat("sum(amount)");
                    income += temp;
                    if(temp != 0)
                        text += "\n" + incomeCategories.get(i) + ": " + temp;
                }
                text += "\n...other: " + (total_income - income);
                
                text += "\n\nOutcome:";
                outcome = 0;
                for(int i = 0; i < outcomeCategories.size(); i++) {
                    command = "select sum(amount) from outcome where iyear >= "
                            + first_year
                            + " and iyear <= "
                            + (first_year + last)
                            + " and iname = '"
                            + outcomeCategories.get(i)
                            + "'";
                    resultSet = stmt.executeQuery(command);
                    temp = 0;
                    if(resultSet.next())
                        temp = resultSet.getFloat("sum(amount)");
                    outcome += temp;
                    if(temp != 0)
                        text += "\n" + outcomeCategories.get(i) + ": " + temp;
                }
                text += "\n...other: " + (total_outcome - outcome);
                infoPane.setText(text);
                
            }
        } catch (SQLException ex) { 
            Logger.getLogger(FinanceReview.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_calendarTableMouseReleased

    private void holdRecordsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_holdRecordsButtonActionPerformed
        int prevHold = holdTo;
        holdTo = resultTable.getRowCount();
        for(int i = prevHold; i < holdTo; i++)
            if(resultModel.getValueAt(i, 0).toString().charAt(0) != '<')
            resultModel.setValueAt("<html><font color='orange'>" + resultModel.getValueAt(i, 0) + "</font></html>", i, 0);
            
    }//GEN-LAST:event_holdRecordsButtonActionPerformed

    private void releaseRecordsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_releaseRecordsButtonActionPerformed
            holdTo = 0;
            clearResultTable();
    }//GEN-LAST:event_releaseRecordsButtonActionPerformed

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
            java.util.logging.Logger.getLogger(FinanceReview.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FinanceReview.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FinanceReview.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FinanceReview.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FinanceReview().setVisible(true);
            }
        });
    }
    
    private String text;
    private String months[] = {
        "January"
        , "February"
        , "March"
        , "April"
        , "May"
        , "June"
        , "July"
        , "August"
        , "September"
        , "October"
        , "November"
        , "December"
    };
    private Vector < String > incomeCategories, outcomeCategories;
    private int daysInMonth[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private Date aDate = new Date(System.currentTimeMillis());
    private Date tempDate = new Date();
    private String[] dates;
    private DefaultTableModel calendarModel;
    private DefaultTableModel resultModel;
    private Connection localConnection;
    private Connection serverConnection;
    private Statement stmt;
    private ResultSet resultSet, resultSet1;
    private String command;
    private int holdTo;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable calendarTable;
    private javax.swing.JButton goBackButton;
    private javax.swing.JButton goForwordButton;
    private javax.swing.JButton holdRecordsButton;
    private javax.swing.JTextArea infoPane;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JButton releaseRecordsButton;
    private javax.swing.JTable resultTable;
    private javax.swing.JComboBox<String> scaleBox;
    // End of variables declaration//GEN-END:variables
}
