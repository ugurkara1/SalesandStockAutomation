package tr.com.nemesisyazilim.fe;

import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import tr.com.nemesisyazilim.complex.types.SatisContractComplex;
import tr.com.nemesisyazilim.complex.types.StokContractComplex;
import tr.com.nemesisyazilim.dal.MusteriDAL;
import tr.com.nemesisyazilim.dal.SatisDAL;
import tr.com.nemesisyazilim.dal.Session;
import tr.com.nemesisyazilim.dal.StokDAL;
import tr.com.nemesisyazilim.dal.UrunlerDAL;
import tr.com.nemesisyazilim.interfaces.FeInterfaces;
import tr.com.nemesisyazilim.types.MusteriContract;
import tr.com.nemesisyazilim.types.PersonelContract;
import tr.com.nemesisyazilim.types.SatisContract;
import tr.com.nemesisyazilim.types.StokContract;
import tr.com.nemesisyazilim.types.UrunlerContract;
import tr.com.nemesisyazilim.utilities.MenulerCom;

public class AnaPencereFE extends JFrame implements FeInterfaces {

    public AnaPencereFE() {
        initPencere();
    }

    @Override
    public void initPencere() {
        JPanel panel = initPanel();
        JMenuBar bar = initBar();
        add(panel);
        setJMenuBar(bar);
        setTitle("Satış ve Stok Programı");
        setSize(800, 600);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public JPanel initPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JTabbedPane pane = initTabs();
        panel.add(pane, BorderLayout.CENTER);
        return panel;
    }

    @Override
    public JMenuBar initBar() {
        JMenuBar bar = MenulerCom.initBar();
        return bar;
    }

    @Override
    public JTabbedPane initTabs() {
        JTabbedPane pane = new JTabbedPane();
        ImageIcon icon = new ImageIcon("icons/stock.png");
        ImageIcon icon2 = new ImageIcon("icons/stock.png");

        JPanel stokPanel = new JPanel(new BorderLayout());
        JPanel satisPanel = new JPanel(new BorderLayout());

        JPanel stokSolPanel = new JPanel(new BorderLayout());
        JPanel stokSolUstPanel = new JPanel(new GridBagLayout());
        JPanel stokSolAltPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        stokSolPanel.setBorder(BorderFactory.createTitledBorder("Stok İşlemleri"));

        Object[] stokKolonlar = {"Id", "Ürün Adı", "Personel Adı", "Adeti", "Tarihi"};
        DefaultTableModel model = new DefaultTableModel(stokKolonlar, 0);
        JTable table = new JTable(model);
        JScrollPane stokTablePane = new JScrollPane(table);

        // Stok verilerini tabloya ekleme
        for (StokContractComplex contract : new StokDAL().GetAllStok()) {
            model.addRow(contract.getVeriler());
        }

        JLabel stokurunAdiLabel = new JLabel("Ürün Adı:", JLabel.RIGHT);
        JComboBox stokUrunAdiBox = new JComboBox(new UrunlerDAL().GetALL().toArray());
        JLabel stokAdetLabel = new JLabel("Adet:", JLabel.RIGHT);
        JTextField stokAdetField = new JTextField(10);
        JLabel stokTarihiLabel = new JLabel("Stok Tarihi:", JLabel.RIGHT);
        JDateChooser stokTarihi = new JDateChooser();

        JButton stokEkleButton = new JButton("Stok Ekle");
        JButton stokYenileButton = new JButton("Yenile");
        JButton stokAraButton = new JButton("Ara");
        JTextField stokAraField = new JTextField(10);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        stokSolUstPanel.add(stokurunAdiLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        stokSolUstPanel.add(stokUrunAdiBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        stokSolUstPanel.add(stokAdetLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        stokSolUstPanel.add(stokAdetField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        stokSolUstPanel.add(stokTarihiLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        stokSolUstPanel.add(stokTarihi, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        stokSolUstPanel.add(stokEkleButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        stokSolUstPanel.add(stokYenileButton, gbc);

        stokSolAltPanel.add(new JLabel("Ara:"));
        stokSolAltPanel.add(stokAraField);
        stokSolAltPanel.add(stokAraButton);

        stokSolPanel.add(stokSolUstPanel, BorderLayout.NORTH);
        stokSolPanel.add(stokSolAltPanel, BorderLayout.SOUTH);

        stokPanel.add(stokSolPanel, BorderLayout.WEST);
        stokPanel.add(stokTablePane, BorderLayout.CENTER);

        pane.addTab("Stoklar", icon, stokPanel);
        pane.addTab("Satışlar", icon2, satisPanel);

        // Stok Ekle Butonuna Aksiyon Ekleme
        stokEkleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StokContract contract = new StokContract();
                UrunlerContract urun = (UrunlerContract) stokUrunAdiBox.getSelectedItem();

                // Giriş yapan kullanıcının ID'sini kullan
                contract.setUrunId(urun.getId());
                contract.setPersonelId(Session.loggedInUser.getId());
                contract.setTarih(stokTarihi.getDate());
                int eklenenAdet = Integer.parseInt(stokAdetField.getText());
                contract.setAdet(eklenenAdet);

                // Aynı üründen stokta olup olmadığını kontrol et
                StokDAL stokDAL = new StokDAL();
                StokContract mevcutStok = stokDAL.GetStokByUrunId(urun.getId());

                if (mevcutStok != null) {
                    // Aynı üründen stokta var, miktarını güncelle
                    mevcutStok.setAdet(mevcutStok.getAdet() + eklenenAdet);
                    stokDAL.Update(mevcutStok);
                    JOptionPane.showMessageDialog(null, "Stok Mevcut Kayıt Üzerine Eklendi!");
                } else {
                    // Aynı üründen stokta yok, yeni kayıt ekle
                    stokDAL.Insert(contract);
                    JOptionPane.showMessageDialog(null, "Stok Başarıyla Eklendi!");
                }

                // Mevcut satırları temizle
                model.setRowCount(0);

                // Tabloyu güncellenmiş verilerle doldur
                for (StokContractComplex updatedContract : stokDAL.GetAllStok()) {
                    model.addRow(updatedContract.getVeriler());
                }
            }
        });
        stokYenileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // StokDAL nesnesini tanımla
                StokDAL stokDAL = new StokDAL();

                // Mevcut satırları temizle
                model.setRowCount(0);

                // Tabloyu güncellenmiş verilerle doldur
                List<StokContractComplex> stokList = stokDAL.GetAllStok();
                if (stokList != null && !stokList.isEmpty()) {
                    for (StokContractComplex updatedContract : stokList) {
                        model.addRow(updatedContract.getVeriler());
                    }
                } else {
                    // Eğer stok listesi boşsa veya veri bulunamadıysa
                    JOptionPane.showMessageDialog(null, "Stok verisi bulunamadı.");
                }
            }
        });

        stokAraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchTerm = stokAraField.getText().toLowerCase(); // Arama terimini küçük harfe çevirerek al
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.setRowCount(0); // Mevcut satırları temizle

                // Stok verilerini arama terimine göre filtreleyip tabloya ekle
                for (StokContractComplex contract : new StokDAL().GetAllStok()) {
                    String urunAdi = contract.getUrunAdi().toLowerCase();
                    if (urunAdi.contains(searchTerm)) {
                        model.addRow(contract.getVeriler());
                    }
                }
            }
        });

        //Satış işlemleri
        JPanel satisSagPanel = new JPanel(new BorderLayout());
        JPanel satisSagUstPanel = new JPanel(new GridLayout(6, 2));
        JPanel satisSagAltPanel = new JPanel();

        Object[] satisKolonlar = {"Id", "Personel Adı", "Müşteri Adı", "Ürün Adı", "Adeti", "Tarihi"};

        DefaultTableModel satisModel = new DefaultTableModel(satisKolonlar, 0);
        JTable satisTable = new JTable(satisModel);
        JScrollPane satisTablePane = new JScrollPane(satisTable);

        JLabel musteriLabel = new JLabel("Müşteri Adı", JLabel.RIGHT);
        satisSagUstPanel.add(musteriLabel);
        JComboBox musteriAdiBox = new JComboBox(new MusteriDAL().GetALL().toArray());
        satisSagUstPanel.add(musteriAdiBox);
        JLabel satisUrunAdiLabel = new JLabel("Ürün Adı:", JLabel.RIGHT);
        satisSagUstPanel.add(satisUrunAdiLabel);
        JComboBox satisUrunAdiBox = new JComboBox(new UrunlerDAL().GetALL().toArray());
        satisSagUstPanel.add(satisUrunAdiBox);
        JLabel satisAdetLabel = new JLabel("Adet:", JLabel.RIGHT);
        satisSagUstPanel.add(satisAdetLabel);
        JTextField satisAdetField = new JTextField(15);
        satisSagUstPanel.add(satisAdetField);
        JLabel satisTarihiLabel = new JLabel("Satış Tarihi:", JLabel.RIGHT);
        satisSagUstPanel.add(satisTarihiLabel);
        JDateChooser satisTarihi = new JDateChooser();
        satisSagUstPanel.add(satisTarihi);

        JButton satisEkleButton = new JButton("Satış Yap");
        satisSagUstPanel.add(satisEkleButton);

        satisEkleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Giriş yapan personelin ve seçilen müşteri ile ürünün bilgilerini alıyoruz
                    PersonelContract pContract = (PersonelContract) LoginFE.emailBox.getSelectedItem();
                    UrunlerContract uContract = (UrunlerContract) satisUrunAdiBox.getSelectedItem();
                    MusteriContract mContract = (MusteriContract) musteriAdiBox.getSelectedItem();

                    // Satış bilgilerini topluyoruz
                    SatisContract satisContract = new SatisContract();
                    satisContract.setMusteriId(mContract.getId());
                    satisContract.setPersonelId(pContract.getId());
                    satisContract.setUrunId(uContract.getId());
                    satisContract.setAdet(Integer.parseInt(satisAdetField.getText()));
                    satisContract.setTarih(satisTarihi.getDate());

                    // Satış işlemini veritabanına kaydediyoruz
                    new SatisDAL().Insert(satisContract);

                    // Stok güncellemesi için stok bilgisi alınıyor
                    StokDAL stokDAL = new StokDAL();
                    StokContract mevcutStok = stokDAL.GetStokByUrunId(uContract.getId());

                    int satisAdet = Integer.parseInt(satisAdetField.getText());

                    if (mevcutStok != null) {
                        // Aynı üründen stokta varsa stok miktarını güncelliyoruz
                        int yeniStokAdet = mevcutStok.getAdet() - satisAdet;

                        if (yeniStokAdet < 0) {
                            JOptionPane.showMessageDialog(null, "Yeterli stok yok!", "Hata", JOptionPane.ERROR_MESSAGE);
                            return; // İşlemi iptal ediyoruz
                        }

                        mevcutStok.setAdet(yeniStokAdet);
                        stokDAL.Update(mevcutStok);
                    } else {
                        // Eğer stokta hiç yoksa yeni stok kaydı oluşturuyoruz
                        StokContract yeniStok = new StokContract();
                        yeniStok.setPersonelId(pContract.getId());
                        yeniStok.setUrunId(uContract.getId());
                        yeniStok.setAdet(-satisAdet); // Eksilen stok
                        yeniStok.setTarih(satisTarihi.getDate());

                        stokDAL.Insert(yeniStok);
                    }

                    // Satış sonrası bilgi mesajı
                    JOptionPane.showMessageDialog(null, mContract.getAdiSoyadi() + " adlı müşteriye satış yapılmış ve " + uContract.getAdi() + " adlı ürün stokta " + satisAdet + " adet eksiltilmiştir.");

                    // Tabloyu güncellemek için önceki satırları temizliyoruz
                    satisModel.setRowCount(0);

                    // Satışları tekrar yükleyip tabloya ekliyoruz
                    for (SatisContractComplex satis : new SatisDAL().GetAllSatis()) {
                        satisModel.addRow(satis.getVeriler());
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Lütfen geçerli bir adet girin!", "Hata", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Bir hata oluştu: " + ex.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton satisYenileButton = new JButton("Yenile");
        satisSagUstPanel.add(satisYenileButton);

        satisYenileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int satir = satisModel.getRowCount();
                for (int i = 0; i < satir; i++) {
                    satisModel.removeRow(0);
                }
                for (SatisContractComplex contract : new SatisDAL().GetAllSatis()) {
                    satisModel.addRow(contract.getVeriler());
                }
            }

        });
        satisPanel.add(satisSagPanel, BorderLayout.EAST);
        satisPanel.add(satisTablePane, BorderLayout.CENTER);
        satisSagPanel.add(satisSagUstPanel, BorderLayout.NORTH);
        satisSagPanel.add(satisSagAltPanel, BorderLayout.SOUTH);

        return pane;
    }
}
