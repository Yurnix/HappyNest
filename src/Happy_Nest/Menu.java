/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Happy_Nest;

import java.awt.image.BufferedImage;
import static java.awt.image.BufferedImage.TYPE_INT_RGB;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import static java.lang.Math.ceil;
import static java.lang.Math.random;
import static java.lang.Math.round;
import static java.lang.Thread.sleep;
import java.util.Date;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Yunix
 */
public class Menu extends javax.swing.JFrame {

    /**
     * Creates new form Menu
     */
    public Menu() {
        initComponents();
        changesMade = false;
        /*
        BufferedImage myPicture;
        try {
            myPicture = ImageIO.read(new File("back1.jpg"));
            JLabel picLabel = new JLabel(new ImageIcon(myPicture));
            picLabel.setBounds(0, 0, this.getWidth(), this.getHeight());
            
            jPanel1.removeAll();
            jPanel1.add(picLabel);
        } catch (IOException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        financesReviewButton = new javax.swing.JButton();
        vendorsButton = new javax.swing.JButton();
        financesButton = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu5 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(187, 187, 187));
        jPanel1.setPreferredSize(new java.awt.Dimension(600, 800));

        financesReviewButton.setBackground(new java.awt.Color(102, 102, 102));
        financesReviewButton.setText("Finances Review");
        financesReviewButton.setBorder(null);
        financesReviewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                financesReviewButtonActionPerformed(evt);
            }
        });

        vendorsButton.setBackground(new java.awt.Color(102, 102, 102));
        vendorsButton.setText("Vendors");
        vendorsButton.setBorder(null);
        vendorsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vendorsButtonActionPerformed(evt);
            }
        });

        financesButton.setBackground(new java.awt.Color(102, 102, 102));
        financesButton.setText("Caffee Finances");
        financesButton.setBorder(null);
        financesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                financesButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(financesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(financesReviewButton, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(vendorsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(vendorsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(financesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(financesReviewButton, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        jMenuBar1.setBackground(new java.awt.Color(51, 51, 51));
        jMenuBar1.setForeground(new java.awt.Color(51, 51, 51));

        jMenu5.setText("Settings");
        jMenu5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu5MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void vendorsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vendorsButtonActionPerformed
        
        if (ven == null) {
            ven = new Vendors();
            ven.addWindowListener(vendorsFrameListener);
            ven.setVisible(true);
        }
        else if (!vendorsFrameListener.isItActive()) {
            ven = new Vendors();
            vendorsFrameListener.setOn();
            ven.addWindowListener(vendorsFrameListener);
            ven.setVisible(true);
        }
        System.out.print("\n frame is " + vendorsFrameListener.isItActive());
        changesMade = true;
    }//GEN-LAST:event_vendorsButtonActionPerformed

    private void financesReviewButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_financesReviewButtonActionPerformed
        
        if (finRev == null) {
            finRev = new FinanceReview();
            finRev.addWindowListener(financeReviewFrameListener);
            finRev.setVisible(true);
        }
        else if (!financeFrameListener.isItActive()) {
            finRev = new FinanceReview();
            financeReviewFrameListener.setOn();
            finRev.addWindowListener(financeFrameListener);
            finRev.setVisible(true);
        }
        //System.out.print("\n frame is " + financeReviewFrameListener.isItActive());
        changesMade = true;
    }//GEN-LAST:event_financesReviewButtonActionPerformed

    //Settings
    private void jMenu5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu5MouseClicked
        Settings set = new Settings();
        set.setVisible(true);
    }//GEN-LAST:event_jMenu5MouseClicked

    private void financesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_financesButtonActionPerformed
        if (fin == null) {
            fin = new Finances();
            fin.addWindowListener(financeFrameListener);
            fin.setVisible(true);
        }
        else if (!financeFrameListener.isItActive()) {
            fin = new Finances();
            financeFrameListener.setOn();
            fin.addWindowListener(financeFrameListener);
            fin.setVisible(true);
        }
        //System.out.print("\n frame is " + financeFrameListener.isItActive());
        changesMade = true;
    }//GEN-LAST:event_financesButtonActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        //System.out.println("Worked");
        try {
            String path;
            File defaultPath = new File("backupPath.txt");
            if(!defaultPath.exists()) {
                if(!defaultPath.createNewFile()) {
                    JOptionPane.showMessageDialog(new JLabel(), "Cant create backupPath.txt");
                    return;
                }
                FileWriter write = new FileWriter(defaultPath);
                path = JOptionPane.showInputDialog("Give default backup path");
                write.write(path);
                write.close();
            }
            int days = 5;
            path = "";
            Scanner read = new Scanner(defaultPath);
            if(read.hasNextLine()) {
                path = read.nextLine();
            }
            if(read.hasNextLine()) {
                days = Integer.parseInt(read.nextLine());
            }
            
            //backup files
            //
            
            //delete old backup
            File location = new File(path);
            String names[];
            names = location.list();
            Date now = new Date(System.currentTimeMillis());
            Date previous = new Date();
            //JOptionPane.showMessageDialog(new JLabel(), names);
            for(String dir : names) {
                if(dir.contains("VHappyNest") && dir.length() > 26) {
                    previous.setTime(Long.parseLong(dir.substring(26)));
                    if(System.currentTimeMillis() - previous.getTime() > 1000 * 60 * 60 * 24 * days || //if longer than n days
                       now.getDate() == previous.getDate() && now.getMonth() == previous.getMonth() && now.getYear() == previous.getYear()) { //if less than one day
                        //JOptionPane.showMessageDialog(new JLabel(),path+ dir);
                        File oldDir = new File(path + "\\" + dir);
                        String oldTables[] = oldDir.list();
                        for(String tables : oldTables) {
                            File oldFile = new File(oldDir.getPath() + "\\" + tables);
                            boolean f = oldFile.delete(); //delete csv tables inside first
                            /*
                            if(f)
                                System.out.println("Deleted " + oldFile.getPath());
                            else
                                System.out.println("Suck " + oldFile.getPath());*/
                        }
                        boolean f = oldDir.delete(); //delete the folder
                        //JOptionPane.showMessageDialog(new JLabel(), (f?dir + " deleted" : "not deleted"));
                    }
                }
            }
            Date today = new Date(System.currentTimeMillis());
            
            path += "VHappyNest - " +
                    ((today.getDate()) < 10 ? "0" + (today.getDate()) : (today.getDate())) +
                    "-" +
                    ((today.getMonth() + 1) < 10 ? "0" + (today.getMonth() + 1) : (today.getMonth() + 1)) +
                    "-" +
                    (today.getYear() + 1900) +
                    " - " +
                    System.currentTimeMillis();
            
            Methods.exportToCSV(path);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formWindowClosing

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
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
                
            }
        });
    }
    
    private IsItClosed financeReviewFrameListener = new IsItClosed();
    private IsItClosed vendorsFrameListener = new IsItClosed();
    private IsItClosed financeFrameListener = new IsItClosed();
    private Finances fin;
    private Vendors ven;
    private FinanceReview finRev;
    private boolean changesMade;
    private String path;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton financesButton;
    private javax.swing.JButton financesReviewButton;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton vendorsButton;
    // End of variables declaration//GEN-END:variables
}
