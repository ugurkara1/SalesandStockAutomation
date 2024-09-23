package tr.com.nemesisyazilim.fe;

import com.toedter.calendar.JDateChooser;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import tr.com.nemesisyazilim.dal.KategoriDAL;
import tr.com.nemesisyazilim.dal.UrunlerDAL;
import tr.com.nemesisyazilim.interfaces.FeInterfaces;
import tr.com.nemesisyazilim.types.KategoriContract;
import tr.com.nemesisyazilim.types.UrunlerContract;

public class UrunEkleFE extends JDialog implements FeInterfaces {

    public UrunEkleFE() {
        initPencere();
    }

    @Override
    public void initPencere() {
        
        JPanel panel = initPanel();
        
        panel.setBorder(BorderFactory.createTitledBorder("Ürün Kayıt Alanı"));
        add(panel);
        setTitle("Ürün Ekleyin");
        pack();
        setModalityType(DEFAULT_MODALITY_TYPE);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
    }

    @Override
    public JPanel initPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 2));
        JLabel adiLabel = new JLabel("Ürün Adı:", JLabel.RIGHT);
        panel.add(adiLabel);
        JTextField adiField = new JTextField(10);
        panel.add(adiField);
        JLabel kategoriLabel = new JLabel("Kategori", JLabel.RIGHT);
        panel.add(kategoriLabel);
        JComboBox kategoriBox = new JComboBox(new KategoriDAL().GetALL().toArray());
        panel.add(kategoriBox);
        JLabel tarihLabel = new JLabel("Tarih", JLabel.RIGHT);
        panel.add(tarihLabel);
        JDateChooser tarihDate = new JDateChooser();
        panel.add(tarihDate);
        JLabel fiyatLabel = new JLabel("Fiyat", JLabel.RIGHT);
        panel.add(fiyatLabel);
        JTextField fiyatField = new JTextField(10);
        panel.add(fiyatField);

        JButton kaydetButton = new JButton("Kaydet");
        panel.add(kaydetButton);
        
        JButton iptal = new JButton("İptal");
        panel.add(iptal);
        kaydetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UrunlerContract contract=new UrunlerContract();
                KategoriContract casContract=(KategoriContract) kategoriBox.getSelectedItem();
                contract.setAdi(adiField.getText());
                contract.setKategoriId(casContract.getId());
                Date today = Calendar.getInstance().getTime();
                contract.setTarih(new Date(today.getTime()));
                try {
                    int fiyat = Integer.parseInt(fiyatField.getText());
                    contract.setFiyat(fiyat);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Lütfen geçerli bir fiyat giriniz.", "Hata", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                new UrunlerDAL().Insert(contract);
                JOptionPane.showMessageDialog(null, contract.getAdi() + " adlı ürün başarılı bir şekilde eklenmiştir.");
            }
        });
        return panel;
    }

    @Override
    public JMenuBar initBar() {
        return null;
    }

    @Override
    public JTabbedPane initTabs() {
        return null;
    }

}
