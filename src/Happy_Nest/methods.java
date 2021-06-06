
package Happy_Nest;

import java.awt.Component;
import javax.swing.JOptionPane;
import java.util.Date; 



public abstract class methods {

    // <editor-fold defaultstate="collapsed" desc="Helpful methods (isFloat, okcancel)">
    
    public static boolean isFloat(String text)
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
}
