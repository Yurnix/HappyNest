package Happy_Nest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class Finances extends javax.swing.JFrame {

    public Finances() {
        initComponents();
        initLocalConnection();
        initTables();
        calendarModel = (DefaultTableModel) calendarTable.getModel();
        incomeModel = (DefaultTableModel) incomeTable.getModel();
        outcomeModel = (DefaultTableModel) outcomeTable.getModel();
        //aDate.setDate(30);
        loadCalendar();
        calendarTable.setRowSelectionInterval(aDate.getDate() - 1, aDate.getDate() - 1);
        buttonGroup.add(incomeButton);
        buttonGroup.add(outcomeButton);
        categoryBox.removeAllItems();
        
        /*
        TableRowSorter<TableModel> incomeSorter = new TableRowSorter<TableModel>(incomeTable.getModel());
        List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
        sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
        incomeSorter.setSortKeys(sortKeys);
        incomeTable.setRowSorter(incomeSorter);
        */
        
        //categoryBox.addItem("...other");
    }

    private void loadCategoryBox() throws SQLException {
        String where = "";
        if (incomeButton.isSelected()) {
            where = "expencesIncome";
        } else if (outcomeButton.isSelected()) {
            where = "expencesOutcome";
        } else {
            return;
        }
        command = "select * from " + where;
        resultSet = stmt.executeQuery(command);
        categoryBox.removeAllItems();

        categoryBox.addItem("...other");
        while (resultSet.next()) {
            categoryBox.addItem(resultSet.getString("category"));
        }
    }

    private void initTables() {

        incomeTable.getColumnModel().getColumn(0).setMinWidth(5);
        incomeTable.getColumnModel().getColumn(0).setMaxWidth(400);

        incomeTable.getColumnModel().getColumn(1).setMinWidth(5);
        incomeTable.getColumnModel().getColumn(1).setMaxWidth(200);

        incomeTable.getColumnModel().getColumn(2).setMinWidth(0);
        incomeTable.getColumnModel().getColumn(2).setMaxWidth(0);

        outcomeTable.getColumnModel().getColumn(0).setMinWidth(5);
        outcomeTable.getColumnModel().getColumn(0).setMaxWidth(400);

        outcomeTable.getColumnModel().getColumn(1).setMinWidth(5);
        outcomeTable.getColumnModel().getColumn(1).setMaxWidth(200);

        outcomeTable.getColumnModel().getColumn(2).setMinWidth(0);
        outcomeTable.getColumnModel().getColumn(2).setMaxWidth(0);

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

    //get selected incomeID
    private int getSelectedIncome() {
        if (incomeTable.getSelectedRow() != -1) {
            //System.out.println("The selected = " + incomeModel.getValueAt(incomeTable.getSelectedRow(), 2));
            return (int) incomeModel.getValueAt(incomeTable.getSelectedRow(), 2);
        } else {
            return -1;
        }
    }

    //get selected outcomeID
    private int getSelectedOutcome() {
        if (outcomeTable.getSelectedRow() != -1) {
            return (int) outcomeModel.getValueAt(outcomeTable.getSelectedRow(), 2);
        } else {
            return -1;
        }
    }

    private void loadOutcomeTable() {
        while (outcomeTable.getRowCount() > 0) {
            outcomeModel.removeRow(0);
        }
        command = "select iname, amount, outcomeID from outcome where iday >= "
                + (calendarTable.getSelectedRow() + 1)
                + " and iday <= "
                + (calendarTable.getSelectedRow() + calendarTable.getSelectedRowCount())
                + " and imonth = "
                + (aDate.getMonth() + 1)
                + " and iyear = "
                + (aDate.getYear() + 1900);
        try {
            resultSet = stmt.executeQuery(command);
            while (resultSet.next()) {
                outcomeModel.addRow(new Object[]{resultSet.getString("iname"), resultSet.getFloat("amount"), resultSet.getInt("outcomeID")});
            }
        } catch (SQLException ex) {
            Logger.getLogger(Finances.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadIncomeTable() {
        while (incomeTable.getRowCount() > 0) {
            incomeModel.removeRow(0);
        }
        command = "select iname, amount, incomeID from income where iday >= "
                + (calendarTable.getSelectedRow() + 1)
                + " and iday <= "
                + (calendarTable.getSelectedRow() + calendarTable.getSelectedRowCount())
                + " and imonth = "
                + (aDate.getMonth() + 1)
                + " and iyear = "
                + (aDate.getYear() + 1900);
        System.out.println(command);
        try {
            resultSet = stmt.executeQuery(command);
            while (resultSet.next()) {
                incomeModel.addRow(new Object[]{resultSet.getString("iname"), resultSet.getFloat("amount"), resultSet.getInt("incomeID")});
            }
        } catch (SQLException ex) {
            Logger.getLogger(Finances.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadCalendar() {
        //calendar.setTimeInMillis(System.currentTimeMillis());
        //System.out.print(aDate.getDate());
        int aDay = 0;
        if ((aDate.getYear() + 1900) % 400 == 0 || ((aDate.getYear() + 1900) % 4 == 0 && (aDate.getYear() + 1900) % 100 != 0)) {
            aDay = 1;
        }
        clearCalendarTable();
        for (int i = 1; i <= daysInMonth[aDate.getMonth()] + (aDate.getMonth() == 1 ? aDay : 0); i++) {
            calendarModel.addRow(new Object[]{
                String.valueOf(i)
                + "/"
                + String.valueOf(aDate.getMonth() + 1)
                + "/"
                + String.valueOf(aDate.getYear() + 1900)});
        }
        calendarTable.setRowSelectionInterval(0, 0);
    }

    private void clearCalendarTable() {
        while (calendarModel.getRowCount() > 0) {
            calendarModel.removeRow(0);
        }
        return;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        calendarTable = new javax.swing.JTable();
        goBackButton = new javax.swing.JButton();
        goForwardButton = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        outcomeTable = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        incomeTable = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        categoryField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        categoryBox = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        descriptionField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        amountField = new javax.swing.JTextField();
        addRecordButton = new javax.swing.JButton();
        editRecordButton = new javax.swing.JButton();
        removeRecordButton = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        informationArea = new javax.swing.JTextArea();
        incomeButton = new javax.swing.JRadioButton();
        outcomeButton = new javax.swing.JRadioButton();
        pushBox = new javax.swing.JCheckBox();
        manageCategories = new javax.swing.JButton();
        searchField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Finances");
        setMinimumSize(new java.awt.Dimension(1000, 600));

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
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                calendarTableMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                calendarTableMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(calendarTable);

        goBackButton.setText("<<");
        goBackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goBackButtonActionPerformed(evt);
            }
        });

        goForwardButton.setText(">>");
        goForwardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goForwardButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(goBackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(goForwardButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(goBackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 21, Short.MAX_VALUE)
                    .addComponent(goForwardButton, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        outcomeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Name", "Amount", "outcomeID"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        outcomeTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                outcomeTableMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(outcomeTable);

        incomeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Name", "Amount", "incomeID"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        incomeTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                incomeTableMouseReleased(evt);
            }
        });
        jScrollPane3.setViewportView(incomeTable);

        categoryField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                categoryFieldFocusGained(evt);
            }
        });

        jLabel1.setText("Category");

        categoryBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        categoryBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoryBoxActionPerformed(evt);
            }
        });

        jLabel2.setText("Description");

        descriptionField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                descriptionFieldFocusGained(evt);
            }
        });

        jLabel3.setText("Amount");

        amountField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                amountFieldFocusGained(evt);
            }
        });

        addRecordButton.setText("Add Record");
        addRecordButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addRecordButtonActionPerformed(evt);
            }
        });

        editRecordButton.setText("Edit");
        editRecordButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editRecordButtonActionPerformed(evt);
            }
        });

        removeRecordButton.setText("Remove");
        removeRecordButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeRecordButtonActionPerformed(evt);
            }
        });

        informationArea.setColumns(20);
        informationArea.setRows(5);
        informationArea.setBorder(javax.swing.BorderFactory.createTitledBorder("Information"));
        jScrollPane4.setViewportView(informationArea);

        incomeButton.setText("Income");
        incomeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                incomeButtonActionPerformed(evt);
            }
        });

        outcomeButton.setText("Outcome");
        outcomeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                outcomeButtonActionPerformed(evt);
            }
        });

        pushBox.setText("Push in List");

        manageCategories.setText("Manage");
        manageCategories.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageCategoriesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane4))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(jLabel1))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(manageCategories, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(categoryField, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pushBox))
                            .addComponent(categoryBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(incomeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(outcomeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43)
                                .addComponent(addRecordButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(descriptionField, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(amountField, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(editRecordButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(removeRecordButton)))))
                .addGap(11, 11, 11))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(categoryBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addRecordButton)
                    .addComponent(editRecordButton)
                    .addComponent(removeRecordButton)
                    .addComponent(incomeButton)
                    .addComponent(outcomeButton)
                    .addComponent(manageCategories))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(categoryField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(descriptionField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(amountField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pushBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(16, 16, 16)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(470, Short.MAX_VALUE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(16, 16, 16)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(288, Short.MAX_VALUE)))
        );

        searchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                searchFieldKeyTyped(evt);
            }
        });

        jLabel4.setText("Search");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(3, 3, 3)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void goBackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goBackButtonActionPerformed
        if (aDate.getMonth() == 0) {
            aDate.setMonth(11);
            aDate.setYear(aDate.getYear() - 1);
        } else {
            aDate.setMonth(aDate.getMonth() - 1);
        }
        loadCalendar();
    }//GEN-LAST:event_goBackButtonActionPerformed

    private void goForwardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goForwardButtonActionPerformed
        if (aDate.getMonth() == 11) {
            aDate.setMonth(0);
            aDate.setYear(aDate.getYear() + 1);
        } else {
            aDate.setMonth(aDate.getMonth() + 1);
        }
        loadCalendar();
    }//GEN-LAST:event_goForwardButtonActionPerformed

    private void addRecordButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addRecordButtonActionPerformed
        if (calendarTable.getSelectedRowCount() > 1) {
            JOptionPane.showMessageDialog(new JLabel(), "Please select one date");
        }
        String name = categoryField.getText();
        String desc = descriptionField.getText();
        String sAmount = amountField.getText();
        String where = "";
        String whereCategory;
        if (incomeButton.isSelected()) {
            where = "income";
            whereCategory = "expencesIncome";
        } else if (outcomeButton.isSelected()) {
            where = "outcome";
            whereCategory = "expencesOutcome";
        } else {
            JOptionPane.showMessageDialog(new JLabel(), "Error\nPlease select income or outcome");
            return;
        }

        if (name.equals("")) {
            JOptionPane.showMessageDialog(new JLabel(), "Error\nPlease check again the Category field");
            return;
        }
        if (!methods.isFloat(sAmount)) {
            JOptionPane.showMessageDialog(new JLabel(), "Error\nPlease check again the Amount field");
            return;
        }

        command = "select * from "
                + whereCategory
                + " where category = '"
                + name
                + "'";
        try {
            resultSet = stmt.executeQuery(command);
            if (pushBox.isSelected()) {
                if (!resultSet.next()) {
                    command = "insert into "
                            + whereCategory
                            + " values(0, '"
                            + name
                            + "')";
                    stmt.execute(command);
                }
            } else {
                if (resultSet.next()) {
                    command = "delete from "
                            + whereCategory
                            + " where category = '"
                            + name
                            + "'";
                    stmt.execute(command);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Finances.class.getName()).log(Level.SEVERE, null, ex);
        }

        command = "insert into "
                + where
                + " values (0, "
                + (calendarTable.getSelectedRow() + 1)
                + ", "
                + (aDate.getMonth() + 1)
                + ", "
                + (aDate.getYear() + 1900)
                + ", '"
                + name
                + "', '"
                + desc
                + "', "
                + sAmount
                + ", "
                + System.currentTimeMillis()
                + ")";
        try {
            stmt.execute(command);
        } catch (SQLException ex) {
            Logger.getLogger(Finances.class.getName()).log(Level.SEVERE, null, ex);
        }
        categoryField.setText("");
        descriptionField.setText("");
        amountField.setText("");
        categoryBox.setSelectedIndex(0);
        informationArea.setText("Record added successfully");
        pushBox.setSelected(false);
        if (incomeButton.isSelected()) {
            loadIncomeTable();
        } else if (outcomeButton.isSelected()) {
            loadOutcomeTable();
        }
        try {
            loadCategoryBox();
        } catch (SQLException ex) {
            Logger.getLogger(Finances.class.getName()).log(Level.SEVERE, null, ex);
        }
        pushBox.setSelected(false);
    }//GEN-LAST:event_addRecordButtonActionPerformed

    private void calendarTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_calendarTableMouseClicked
        /*if(calendarTable.getSelectedRow() != -1) {
            loadIncomeTable();
            loadOutcomeTable();
            //for(int i = 0; i < calendarTable.getSelectedRowCount(); i++)
                //System.out.println(calendarTable.getSelectedRows()[i]);
        } */
    }//GEN-LAST:event_calendarTableMouseClicked

    private void calendarTableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_calendarTableMouseReleased
        if (calendarTable.getSelectedRow() != -1) {
            loadIncomeTable();
            loadOutcomeTable();
        }
    }//GEN-LAST:event_calendarTableMouseReleased

    private void categoryBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoryBoxActionPerformed
        if (categoryBox.getSelectedIndex() == 0) {
            categoryField.setEnabled(true);
        } else {
            categoryField.setText((String) categoryBox.getSelectedItem());
            categoryField.setEnabled(false);
            pushBox.setSelected(true);
        }

    }//GEN-LAST:event_categoryBoxActionPerformed

    private void incomeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_incomeButtonActionPerformed
        try {
            loadCategoryBox();
            pushBox.setSelected(false);
        } catch (SQLException ex) {
            Logger.getLogger(Finances.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_incomeButtonActionPerformed

    private void outcomeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_outcomeButtonActionPerformed
        try {
            loadCategoryBox();
            pushBox.setSelected(false);
        } catch (SQLException ex) {
            Logger.getLogger(Finances.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_outcomeButtonActionPerformed

    private void removeRecordButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeRecordButtonActionPerformed
        String where = "";
        int id;
        if (incomeTable.getSelectedRow() != -1) {
            where = "income";
            id = getSelectedIncome();
        } else if (outcomeTable.getSelectedRow() != -1) {
            where = "outcome";
            id = getSelectedOutcome();
        } else {
            return;
        }

        command = "delete from "
                + where
                + " where incomeID = "
                + id;

        try {
            stmt.execute(command);
        } catch (SQLException ex) {
            informationArea.setText("Something gone wrong");
            Logger.getLogger(Finances.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        informationArea.setText("Record deleted successfully");

        if (incomeTable.getSelectedRow() != -1) {
            loadIncomeTable();
        } else if (outcomeTable.getSelectedRow() != -1) {
            loadOutcomeTable();
        }
    }//GEN-LAST:event_removeRecordButtonActionPerformed

    private void incomeTableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_incomeTableMouseReleased
        outcomeTable.clearSelection();
        if (incomeTable.getSelectedRow() == -1) {
            return;
        }
        command = "select iname, idesc, amount, dateModified from income where incomeID = "
                + getSelectedIncome();
        try {
            String iname, idesc, amount;
            resultSet = stmt.executeQuery(command);
            if (resultSet.next()) {
                iname = resultSet.getString("iname");
                idesc = resultSet.getString("idesc");
                amount = resultSet.getString("amount");
                tempDate.setTime(resultSet.getLong("dateModified"));
                informationArea.setText(
                        "Last Date Modified: "
                        + tempDate.getDate()
                        + "/" + (tempDate.getMonth() + 1)
                        + "/" + (tempDate.getYear() + 1900)
                        + "\n"
                        + idesc); //Text modifier needed for \n
                
                
                descriptionField.setText(idesc);
                amountField.setText(amount);
                incomeButton.setSelected(true);
                
                command = "select * from expencesIncome where category = '"
                        + iname
                        + "'";
                
                resultSet = stmt.executeQuery(command);
                loadCategoryBox();
                categoryField.setText(iname);
                if(resultSet.next()) {
                    categoryBox.setSelectedItem(iname);
                    pushBox.setSelected(true);
                }
                else {
                    categoryBox.setSelectedIndex(0);
                    pushBox.setSelected(false);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Finances.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_incomeTableMouseReleased

    private void outcomeTableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_outcomeTableMouseReleased
        incomeTable.clearSelection();
        if (outcomeTable.getSelectedRow() == -1) {
            return;
        }
        command = "select iname, idesc, amount, dateModified from outcome where outcomeID = "
                + getSelectedOutcome();
        try {
            String iname, idesc, amount;
            resultSet = stmt.executeQuery(command);
            if (resultSet.next()) {
                iname = resultSet.getString("iname");
                idesc = resultSet.getString("idesc");
                amount = resultSet.getString("amount");
                tempDate.setTime(resultSet.getLong("dateModified"));
                informationArea.setText(
                        "Last Date Modified: "
                        + tempDate.getDate()
                        + "/" + (tempDate.getMonth() + 1)
                        + "/" + (tempDate.getYear() + 1900)
                        + "\n"
                        + resultSet.getString("idesc")); //Text modifier needed for \n
                
                
                descriptionField.setText(idesc);
                amountField.setText(amount);
                outcomeButton.setSelected(true);
                
                command = "select * from expencesOutcome where category = '"
                        + iname
                        + "'";
                
                resultSet = stmt.executeQuery(command);
                loadCategoryBox();
                categoryField.setText(iname);
                if(resultSet.next()) {
                    categoryBox.setSelectedItem(iname);
                    pushBox.setSelected(true);
                }
                else {
                    categoryBox.setSelectedIndex(0);
                    pushBox.setSelected(false);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Finances.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_outcomeTableMouseReleased

    private void categoryFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_categoryFieldFocusGained
        categoryField.select(0, categoryField.getText().length());
    }//GEN-LAST:event_categoryFieldFocusGained

    private void descriptionFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_descriptionFieldFocusGained
        descriptionField.select(0, descriptionField.getText().length());
    }//GEN-LAST:event_descriptionFieldFocusGained

    private void amountFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_amountFieldFocusGained
        amountField.select(0, amountField.getText().length());
    }//GEN-LAST:event_amountFieldFocusGained

    private void searchFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchFieldKeyTyped
        String text = searchField.getText();
        if (text.equals("")) {
            return;
        }
        while (incomeTable.getRowCount() != 0) {
            incomeModel.removeRow(0);
        }
        while (outcomeTable.getRowCount() != 0) {
            outcomeModel.removeRow(0);
        }
        String columns[] = {"iname", "idesc"};
        int foundIncome = 0;
        int foundOutcome = 0;
        int id;
        
        try {

            command = "select * from income where locate('" + text + "', iname) > 0 or locate('" + text + "', idesc) > 0 order by dateModified desc"; //desc order
            resultSet = stmt.executeQuery(command);

            while (resultSet.next()) {
                foundIncome++;
                //id = resultSet.getInt("incomeID");

                incomeModel.addRow(new Object[]{
                    resultSet.getString("iday") + "/"
                    + resultSet.getString("imonth") + "/"
                    + resultSet.getString("iyear") + ": "
                    + resultSet.getString("iname"),
                    resultSet.getFloat("amount"),
                    resultSet.getInt("incomeID")
                });
            }

            command = "select * from outcome where locate('" + text + "', iname) > 0 or locate('" + text + "', idesc) > 0 order by dateModified desc"; //desc order
            resultSet = stmt.executeQuery(command);

            while (resultSet.next()) {
                foundOutcome++;
                outcomeModel.addRow(new Object[]{
                    resultSet.getString("iday") + "/"
                    + resultSet.getString("imonth") + "/"
                    + resultSet.getString("iyear") + ": "
                    + resultSet.getString("iname"),
                    resultSet.getFloat("amount"),
                    resultSet.getInt("outcomeID")
                });
            }
        } catch (SQLException ex) {
            Logger.getLogger(Finances.class.getName()).log(Level.SEVERE, null, ex);
        }

        if(foundIncome > 0)
            informationArea.setText("Found " + foundIncome + " income records");
        else
            informationArea.setText("No income records found");
        
        if(foundOutcome > 0)
            informationArea.setText(informationArea.getText() + "\nFound " + foundOutcome + " outcome records");
        else
            informationArea.setText(informationArea.getText() + "\nNo outcome records found");
        
    }//GEN-LAST:event_searchFieldKeyTyped

    private void editRecordButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editRecordButtonActionPerformed
        if (calendarTable.getSelectedRowCount() > 1) {
            JOptionPane.showMessageDialog(new JLabel(), "Please select one date");
        }
        int id = -1;
        String name = categoryField.getText();
        String desc = descriptionField.getText();
        String sAmount = amountField.getText();
        String where = "";
        String whereCategory;
        if (incomeButton.isSelected()) {
            id = getSelectedIncome();
            if(id == -1)
                return;
            where = "income";
            whereCategory = "expencesIncome";
        } else if (outcomeButton.isSelected()) {
            id = getSelectedOutcome();
            if(id == -1)
                return;
            where = "outcome";
            whereCategory = "expencesOutcome";
        } else {
            JOptionPane.showMessageDialog(new JLabel(), "Error\nPlease select income or outcome");
            return;
        }

        if (name.equals("")) {
            JOptionPane.showMessageDialog(new JLabel(), "Error\nPlease check again the Category field");
            return;
        }
        if (!methods.isFloat(sAmount)) {
            JOptionPane.showMessageDialog(new JLabel(), "Error\nPlease check again the Amount field");
            return;
        }

        command = "select * from "
                + whereCategory
                + " where category = '"
                + name
                + "'";
        try {
            resultSet = stmt.executeQuery(command);
            if (pushBox.isSelected()) {
                if (!resultSet.next()) {
                    command = "insert into "
                            + whereCategory
                            + " values(0, '"
                            + name
                            + "')";
                    stmt.execute(command);
                }
            } else {
                if (resultSet.next()) {
                    command = "delete from "
                            + whereCategory
                            + " where category = '"
                            + name
                            + "'";
                    //stmt.execute(command);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Finances.class.getName()).log(Level.SEVERE, null, ex);
        }

        /*
        command = "update products set "
                    + "productName = '" + productName
                    + "',productDesc = '" + productDesc
                    + "',productPrice = " + productPrice
                    + ",productTax = " + productTax
                    + ",productFinalPrice = " + productFinalPrice
                    + " where productID = " + getSelectedProduct();
        */
        command = "update " + where + " set"
                + " iname = '" + name
                + "' ,idesc = '" + desc
                + "' ,amount = " + sAmount
                + " ,dateModified = " + System.currentTimeMillis()
                + " where " + where + "ID = " + id;
               
        try {
            stmt.execute(command);
        } catch (SQLException ex) {
            Logger.getLogger(Finances.class.getName()).log(Level.SEVERE, null, ex);
        }
        categoryField.setText("");
        descriptionField.setText("");
        amountField.setText("");
        categoryBox.setSelectedIndex(0);
        informationArea.setText("Record updated successfully");
        pushBox.setSelected(false);
        if (incomeButton.isSelected()) {
            loadIncomeTable();
        } else if (outcomeButton.isSelected()) {
            loadOutcomeTable();
        }
        
        try {
            loadCategoryBox();
        } catch (SQLException ex) {
            Logger.getLogger(Finances.class.getName()).log(Level.SEVERE, null, ex);
        }
        pushBox.setSelected(false);
    }//GEN-LAST:event_editRecordButtonActionPerformed

    private void manageCategoriesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageCategoriesActionPerformed
        JFrame addCategoryFrame = new addCategory();
        addCategoryFrame.setVisible(true);
    }//GEN-LAST:event_manageCategoriesActionPerformed

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
            java.util.logging.Logger.getLogger(Finances.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Finances.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Finances.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Finances.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Finances().setVisible(true);
            }
        });
    }

    private int daysInMonth[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private Date aDate = new Date(System.currentTimeMillis());
    private Date tempDate = new Date();
    private String[] dates;
    private DefaultTableModel calendarModel;
    private DefaultTableModel incomeModel;
    private DefaultTableModel outcomeModel;
    private Connection localConnection;
    private Connection serverConnection;
    private Statement stmt;
    private ResultSet resultSet;
    private String command;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addRecordButton;
    private javax.swing.JTextField amountField;
    private javax.swing.ButtonGroup buttonGroup;
    private javax.swing.JTable calendarTable;
    private javax.swing.JComboBox<String> categoryBox;
    private javax.swing.JTextField categoryField;
    private javax.swing.JTextField descriptionField;
    private javax.swing.JButton editRecordButton;
    private javax.swing.JButton goBackButton;
    private javax.swing.JButton goForwardButton;
    private javax.swing.JRadioButton incomeButton;
    private javax.swing.JTable incomeTable;
    private javax.swing.JTextArea informationArea;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JButton manageCategories;
    private javax.swing.JRadioButton outcomeButton;
    private javax.swing.JTable outcomeTable;
    private javax.swing.JCheckBox pushBox;
    private javax.swing.JButton removeRecordButton;
    private javax.swing.JTextField searchField;
    // End of variables declaration//GEN-END:variables
}
