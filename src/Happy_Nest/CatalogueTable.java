
package Happy_Nest;

import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Yunix
 */
public class CatalogueTable extends javax.swing.JScrollPane
{
    private javax.swing.JTable jT;
    private javax.swing.JScrollPane pSP;
    private String name;
    
    public CatalogueTable()
    {
        pSP = new javax.swing.JScrollPane();
        jT = new javax.swing.JTable();
        name = "Catalogue";
        jT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
                
            new String [] {
                "Name", "Cost", "Cost + %Tax", "Tax", "Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Float.class, java.lang.Float.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jT.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        
        jT.setShowGrid(true);
        jT.getColumnModel().getColumn(0).setMinWidth(5);
        jT.getColumnModel().getColumn(0).setMaxWidth(400);
        jT.getColumnModel().getColumn(1).setMinWidth(5);
        jT.getColumnModel().getColumn(1).setMaxWidth(100);
        jT.getColumnModel().getColumn(2).setMinWidth(5);
        jT.getColumnModel().getColumn(2).setMaxWidth(100);
        jT.getColumnModel().getColumn(3).setMinWidth(5);
        jT.getColumnModel().getColumn(3).setMaxWidth(100);
        jT.getColumnModel().getColumn(4).setMinWidth(5);
        jT.getColumnModel().getColumn(4).setMaxWidth(100);
        
        jT.getColumnModel().getColumn(1).setWidth(70);
        jT.getColumnModel().getColumn(1).setPreferredWidth(70);
        jT.getColumnModel().getColumn(2).setWidth(70);
        jT.getColumnModel().getColumn(2).setPreferredWidth(70);
        jT.getColumnModel().getColumn(3).setWidth(70);
        jT.getColumnModel().getColumn(3).setPreferredWidth(70);
        jT.getColumnModel().getColumn(4).setWidth(70);
        jT.getColumnModel().getColumn(4).setPreferredWidth(70);
        
        pSP.setViewportView(jT);
        
        
    }
    
    public void makeItSearchTable()
    {
        name = "Search Results";
        jT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
                
            new String [] {
                "Name", "Cost", "Cost + %Tax", "Tax", "Date", "Vendor"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Float.class, java.lang.Float.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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
        jT.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jT.setShowGrid(true);
        jT.getColumnModel().getColumn(0).setMinWidth(5);
        jT.getColumnModel().getColumn(0).setMaxWidth(400);
        jT.getColumnModel().getColumn(1).setMinWidth(5);
        jT.getColumnModel().getColumn(1).setMaxWidth(100);
        jT.getColumnModel().getColumn(2).setMinWidth(5);
        jT.getColumnModel().getColumn(2).setMaxWidth(100);
        jT.getColumnModel().getColumn(3).setMinWidth(5);
        jT.getColumnModel().getColumn(3).setMaxWidth(100);
        jT.getColumnModel().getColumn(4).setMinWidth(5);
        jT.getColumnModel().getColumn(4).setMaxWidth(100);
        jT.getColumnModel().getColumn(5).setMinWidth(5);
        jT.getColumnModel().getColumn(5).setMaxWidth(100);
        
        jT.getColumnModel().getColumn(1).setWidth(70);
        jT.getColumnModel().getColumn(1).setPreferredWidth(70);
        jT.getColumnModel().getColumn(2).setWidth(70);
        jT.getColumnModel().getColumn(2).setPreferredWidth(70);
        jT.getColumnModel().getColumn(3).setWidth(70);
        jT.getColumnModel().getColumn(3).setPreferredWidth(70);
        jT.getColumnModel().getColumn(4).setWidth(70);
        jT.getColumnModel().getColumn(4).setPreferredWidth(70);
        jT.getColumnModel().getColumn(4).setWidth(100);
        jT.getColumnModel().getColumn(4).setPreferredWidth(100);
        
        pSP.setViewportView(jT);
    }
    
    public DefaultTableModel getModel()
    {
        return (DefaultTableModel) jT.getModel();
    }
    
    public int getSelected()
    {
        return jT.getSelectedRow();
    }
    
    public void addListener(java.awt.event.MouseListener l)
    {
        jT.addMouseListener(l);
    }
    
    public void setCatalogueName(String n)
    {
        name = n;
    }
    
    public String getCatalogueName()
    {
        return name;
    }
    
    public JScrollPane getComponent()
    {
        return pSP;
    }
}
