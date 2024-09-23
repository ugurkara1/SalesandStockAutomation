package tr.com.nemesisyazilim.fe;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import tr.com.nemesisyazilim.dal.AccountsDAL;
import tr.com.nemesisyazilim.dal.PersonelDAL;
import tr.com.nemesisyazilim.dal.YetkilerDAL;
import tr.com.nemesisyazilim.interfaces.FeInterfaces;
import tr.com.nemesisyazilim.types.AccountsContract;
import tr.com.nemesisyazilim.types.PersonelContract;
import tr.com.nemesisyazilim.types.YetkilerContract;

public class SifreİslemleriFE extends JDialog implements FeInterfaces{

    public SifreİslemleriFE() {
        initPencere();
    }
    
    @Override
    public void initPencere() {
        JPanel panel = initPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Şifre Belirleme İşlemleri"));
        add(panel);
        setTitle("Şifre Belirleme İşlemleri");
        pack();
        setModalityType(DEFAULT_MODALITY_TYPE);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
    }

    @Override
    public JPanel initPanel() {
        JPanel panel=new JPanel(new GridLayout(5,2));
        JLabel personelLabel=new JLabel("Personel Seç",JLabel.RIGHT);
        panel.add(personelLabel);
        JComboBox personelBox=new JComboBox(new PersonelDAL().GetALL().toArray());
        panel.add(personelBox);
        JLabel yetkiLabel=new JLabel("Yetki Seç",JLabel.RIGHT);
        panel.add(yetkiLabel);
        JComboBox yetkiBox=new JComboBox(new YetkilerDAL().GetALL().toArray());
        panel.add(yetkiBox);
        JLabel passwordLabel=new JLabel("Şifre Belirle",JLabel.RIGHT);
        panel.add(passwordLabel);
        JPasswordField passField=new JPasswordField(10);
        panel.add(passField);
        JLabel passTekrarLabel=new JLabel("Şifre Tekrar",JLabel.RIGHT);
        panel.add(passTekrarLabel);
        JPasswordField passTekrar=new JPasswordField(15);
        panel.add(passTekrar);
        
        JButton kaydetButton=new JButton("Kaydet");
        panel.add(kaydetButton);
        JButton iptalButton=new JButton("İptal");
        panel.add(iptalButton);
        
        kaydetButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                AccountsContract contract=new AccountsContract();
                PersonelContract pContract=(PersonelContract) personelBox.getSelectedItem();
                YetkilerContract yContract=(YetkilerContract) yetkiBox.getSelectedItem();
                if(passField.getText().equals(passTekrar.getText())){
                    contract.setPersonelId(pContract.getId());
                    contract.setYetkiId(yContract.getId());
                    contract.setSifre(passField.getText());
                    
                    
                    new AccountsDAL().Insert(contract);
                    JOptionPane.showMessageDialog(null, pContract.getAdiSoyadi()+" adlı kişiye "+yContract.getAdi()+" başarılı bir şekilde eklenmiştir");
                }else{
                    JOptionPane.showMessageDialog(null,"Şifreler uyuşmuyor tekrar kontrol ediniz.");
                }
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
