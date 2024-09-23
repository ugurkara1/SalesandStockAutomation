
package tr.com.nemesisyazilim.fe;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
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
import tr.com.nemesisyazilim.dal.PersonelDAL;
import tr.com.nemesisyazilim.interfaces.FeInterfaces;
import tr.com.nemesisyazilim.types.PersonelContract;

public class PersonelEkleFE extends JDialog implements FeInterfaces {

    public PersonelEkleFE() {
        initPencere();
    }
    
    @Override
    public void initPencere() {
        JPanel panel=initPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Personel Ekle"));
        add(panel);
        setTitle("Personel Ekle");
        pack();
        setLocationRelativeTo(null);
        setModalityType(DEFAULT_MODALITY_TYPE);
        setVisible(true);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        
    }

    @Override
    public JPanel initPanel() {
        JPanel panel=new JPanel(new GridLayout(3,2));
        
        JLabel adiSoyadiLabel=new JLabel("Adı Soyadı",JLabel.RIGHT);
        panel.add(adiSoyadiLabel);
        JTextField adiSoyadiField =new JTextField(15);
        panel.add(adiSoyadiField);
        
        JLabel epostaLabel=new JLabel("E-mail",JLabel.RIGHT);
        panel.add(epostaLabel);
        JTextField emailField=new JTextField(20);
        panel.add(emailField);
        
        JButton kaydetButton=new JButton("Kaydet");
        panel.add(kaydetButton);
        JButton iptalButton=new JButton("İptal");
        panel.add(iptalButton);
        kaydetButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                PersonelContract contract=new PersonelContract();                
                contract.setAdiSoyadi(adiSoyadiField.getText());
                contract.setEmail(emailField.getText());
                new PersonelDAL().Insert(contract);
                
                JOptionPane.showMessageDialog(null, contract.getAdiSoyadi()+" adlı personel eklenmiştir");
                
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
