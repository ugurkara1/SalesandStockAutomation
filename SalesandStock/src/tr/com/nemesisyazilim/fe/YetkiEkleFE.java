
package tr.com.nemesisyazilim.fe;

import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import tr.com.nemesisyazilim.dal.YetkilerDAL;
import tr.com.nemesisyazilim.interfaces.FeInterfaces;
import tr.com.nemesisyazilim.types.YetkilerContract;

public class YetkiEkleFE extends JDialog implements FeInterfaces{

    public YetkiEkleFE() {
        initPencere();
    }

    @Override
    public void initPencere() {
        JPanel panel=initPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Yetki Ekle"));
        add(panel);
        setTitle("Yetki Ekle");
        pack();
        setModalityType(DEFAULT_MODALITY_TYPE);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        
    }

    @Override
    public JPanel initPanel() {
        JPanel panel=new JPanel(new GridLayout(2,2));
        JLabel adiLabel=new JLabel("Yetki Adı",JLabel.RIGHT);
        panel.add(adiLabel);
        JTextField adiField=new JTextField(15);
        panel.add(adiField);
        
        JButton kaydetButton=new JButton("Kaydet");
        panel.add(kaydetButton);
        
        JButton iptalButton=new JButton("İptal");
        panel.add(iptalButton);
        kaydetButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                YetkilerContract contract =new YetkilerContract();
                contract.setAdi(adiField.getText());
                new YetkilerDAL().Insert(contract);
                JOptionPane.showMessageDialog(null,contract.getAdi()+" adlı yetki başarılı bir şekilde eklenmiştir." );
                
            }
            
        });
        return panel;
    }

    @Override
    public JMenuBar initBar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public JTabbedPane initTabs() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
}
