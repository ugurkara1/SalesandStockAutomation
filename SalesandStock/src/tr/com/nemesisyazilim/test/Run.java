
package tr.com.nemesisyazilim.test;

import javax.swing.SwingUtilities;
import tr.com.nemesisyazilim.dal.UrunlerDAL;
import tr.com.nemesisyazilim.fe.AnaPencereFE;
import tr.com.nemesisyazilim.fe.LoginFE;

public class Run {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                //new AnaPencereFE();
                new LoginFE();
                //new UrunlerDAL().GetALL();
            }
        
        });
    }
}
