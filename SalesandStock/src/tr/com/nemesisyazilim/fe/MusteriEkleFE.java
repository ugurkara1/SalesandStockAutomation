
package tr.com.nemesisyazilim.fe;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import tr.com.nemesisyazilim.dal.MusteriDAL;
import tr.com.nemesisyazilim.dal.SehirDAL;
import tr.com.nemesisyazilim.interfaces.FeInterfaces;
import tr.com.nemesisyazilim.types.MusteriContract;
import tr.com.nemesisyazilim.types.SehirContract;

public class MusteriEkleFE extends JDialog implements FeInterfaces {

    // JComboBox for cities
    private JComboBox<SehirContract> sehirlerBox;

    public MusteriEkleFE() {
        initPencere();
    }

    @Override
    public void initPencere() {
        JPanel panel = initPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Müşteri Ekle"));
        add(panel);
        setTitle("Müşteri Ekle");
        pack();
        setLocationRelativeTo(null);
        setModalityType(DEFAULT_MODALITY_TYPE);
        setVisible(true);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
    }

    @Override
    public JPanel initPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel fieldPanel = new JPanel(new GridLayout(5, 2));
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));

        JLabel adiSoyadiLabel = new JLabel("Adı Soyadı", JLabel.RIGHT);
        fieldPanel.add(adiSoyadiLabel);
        JTextField adiSoyadiField = new JTextField(15);
        fieldPanel.add(adiSoyadiField);

        JLabel telefonLabel = new JLabel("Telefon", JLabel.RIGHT);
        fieldPanel.add(telefonLabel);
        JTextField telefonField = new JTextField(15);
        fieldPanel.add(telefonField);

        JLabel sehirSeclabel = new JLabel("Şehir Seç", JLabel.RIGHT);
        fieldPanel.add(sehirSeclabel);

        // Initialize JComboBox for cities
        sehirlerBox = new JComboBox<>();
        loadSehirler();
        fieldPanel.add(sehirlerBox);

        JLabel adresLabel = new JLabel("Adres", JLabel.RIGHT);
        fieldPanel.add(adresLabel);
        JTextArea adresArea = new JTextArea(7, 1);
        JScrollPane pane = new JScrollPane(adresArea);
        pane.setBorder(BorderFactory.createTitledBorder(""));

        JButton kaydetButton = new JButton("Kaydet");
        buttonPanel.add(kaydetButton);
        kaydetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MusteriContract contract =new MusteriContract();
                contract.setAdiSoyadi(adiSoyadiField.getText());
                contract.setTelefon(telefonField.getText());
                // JComboBox'tan seçili şehri al
                SehirContract selectedSehir = (SehirContract) sehirlerBox.getSelectedItem();
                if (selectedSehir != null) {
                    contract.setSehirId(selectedSehir.getId());
                } else {
                    JOptionPane.showMessageDialog(null, "Şehir seçiniz.", "Hata", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                contract.setAdres(adresArea.getText());
                new MusteriDAL().Insert(contract);
                JOptionPane.showMessageDialog(null,contract.getAdiSoyadi()+" adlı müşteri başarılı bir şekilde eklenmiştir." );
                
            }
        });        
        JButton iptalButton = new JButton("İptal");
        buttonPanel.add(iptalButton);

        panel.add(fieldPanel, BorderLayout.NORTH);
        panel.add(pane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private void loadSehirler() {
        SehirDAL sehirDal = new SehirDAL();
        List<SehirContract> sehirler = sehirDal.getAllSehirler();

        for (SehirContract sehir : sehirler) {
            System.out.println("Adding city: " + sehir); // Debugging için
            sehirlerBox.addItem(sehir);
        }
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
