/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Happy_Nest;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 *
 * @author Yunix
 */
public class IsItClosed implements WindowListener {

    private boolean active;
    
    public IsItClosed()
    {
        System.out.print("Constructor Worked!!!\n");
        active = true;
    }
    
    public void setOn()
    {
        System.out.print("Set Worked!!!\n");
        active = true;
    }
    
    public boolean isItActive()
    {
        return active;
    }
    
    @Override
    public void windowOpened(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosing(WindowEvent e) {
        active = false;
        System.out.print("CLOSING\n");
    }

    @Override
    public void windowClosed(WindowEvent e) {
        active = false;
        System.out.print("CLOSED\n");
    }

    @Override
    public void windowIconified(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowActivated(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
}
