package tr.com.nemesisyazilim.utilities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import tr.com.nemesisyazilim.dal.AccountsDAL;
import tr.com.nemesisyazilim.fe.KategoriDüzenleFE;
import tr.com.nemesisyazilim.fe.KategoriEkleFE;
import tr.com.nemesisyazilim.fe.LoginFE;
import tr.com.nemesisyazilim.fe.MusteriEkleFE;
import tr.com.nemesisyazilim.fe.PersonelEkleFE;
import tr.com.nemesisyazilim.fe.SifreİslemleriFE;
import tr.com.nemesisyazilim.fe.UrunEkleFE;
import tr.com.nemesisyazilim.fe.YetkiEkleFE;
import tr.com.nemesisyazilim.types.PersonelContract;

public class MenulerCom {
    public static JMenuBar initBar() {
        JMenuBar bar=new JMenuBar();
        JMenu dosyaMenu=new JMenu("Dosya");
        bar.add(dosyaMenu);
        JMenuItem cikisItem=new JMenuItem("Çıkış");
        dosyaMenu.add(cikisItem);
        //Ürünler menü
        JMenu urunlerMenu=new JMenu("Ürünler");
        bar.add(urunlerMenu);
        JMenuItem urunEkleItem=new JMenuItem("Ürün Ekle");
        urunlerMenu.add(urunEkleItem);
        JMenuItem kategoriEkleItem=new JMenuItem("Kategori Ekle");
        urunlerMenu.add(kategoriEkleItem);
        
        JMenuItem urunDuzenleItem=new JMenuItem("Ürünü Düzenle");
        urunlerMenu.add(urunDuzenleItem);
        
        JMenuItem KategoriDuzenleItem=new JMenuItem("Kategoriyi Düzenle");
        urunlerMenu.add(KategoriDuzenleItem);
        KategoriDuzenleItem.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable(){
                    @Override
                    public void run() {
                        new KategoriDüzenleFE();
                    }
                    
                });
            }
            
        });
        //Personel Menüsü
        JMenu personellerMenu=new JMenu("Personel İşlemleri");
        bar.add(personellerMenu);
        JMenuItem personelEkleItem=new JMenuItem("Personel Ekle");
        personellerMenu.add(personelEkleItem);
        JMenuItem yetkiEkleItem=new JMenuItem("Yetki Ekle");
        personellerMenu.add(yetkiEkleItem);
        JMenuItem sifreBelirleItem=new JMenuItem("Şifre Belirleme");
        personellerMenu.add(sifreBelirleItem);
        personellerMenu.addSeparator();
        JMenuItem personelDüzenleItem=new JMenuItem("Personel Düzenle");
        personellerMenu.add(personelDüzenleItem);
        JMenuItem yetkiDüzenle=new JMenuItem("Yetki Düzenle");
        personellerMenu.add(yetkiDüzenle);
        JMenuItem sifreDüzenleItem=new JMenuItem("Şifre Düzenle");
        personellerMenu.add(sifreDüzenleItem);
        
        //öüsteri menüsü
        JMenu musterilerMenu=new JMenu("Müşteriler");
        bar.add(musterilerMenu);
        JMenuItem musteriEkleItem=new JMenuItem("Müşteri Ekle");
        musterilerMenu.add(musteriEkleItem);
        JMenuItem sehirEkleItem=new JMenuItem("Şehir Ekle");
        musterilerMenu.add(sehirEkleItem);
        musterilerMenu.addSeparator();
        
        
        JMenuItem musteriDüzenleItem=new JMenuItem("Müşteri Düzenle");
        musterilerMenu.add(musteriDüzenleItem);
        JMenuItem sehirDüzenleItem=new JMenuItem("Şehir Düzenle");
        musterilerMenu.add(sehirDüzenleItem);
        
        PersonelContract contract=(PersonelContract) LoginFE.emailBox.getSelectedItem();
        if(new AccountsDAL().GetYetkiId(contract.getId()).getYetkiId()!=1){
            personellerMenu.hide();
            musterilerMenu.hide();
            urunlerMenu.hide();
        }
        urunEkleItem.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable(){
                    @Override
                    public void run() {
                        new UrunEkleFE();
                    }
                    
                });
            }
            
        });
       
        kategoriEkleItem.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable(){
                    @Override
                    public void run() {
                        new KategoriEkleFE();
                    }
                    
                });
            }
            
        });
        personelEkleItem.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable(){
                    @Override
                    public void run() {
                        new PersonelEkleFE();
                    }
                    
                });
            }
            
            
        });
        
        
        //Yetki ekle
        yetkiEkleItem.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                new YetkiEkleFE();
            }
            
        });
        
        //Şifre belirleme
        sifreBelirleItem.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                new SifreİslemleriFE();
            }
            
        });
        
        //musteriEkle
        musteriEkleItem.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable(){
                    @Override
                    public void run() {
                        new MusteriEkleFE(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                    }
                    
                });
            }
            
        });
        return bar ;
       
       
    }
}
