package tr.com.nemesisyazilim.types;

import java.util.Date;

public class UrunlerContract {
    private int id;
    private String adi;
    private String urunAcıklamasi;
    private int kategoriId;
    private Date tarih;
    private int fiyat;
    public int getFiyat() {
        return fiyat;
    }

    public void setFiyat(int fiyat) {
        this.fiyat = fiyat;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdi() {
        return adi;
    }

    public void setAdi(String adi) {
        this.adi = adi;
    }

    public int getKategoriId() {
        return kategoriId;
    }

    public void setKategoriId(int kategoriId) {
        this.kategoriId = kategoriId;
    }

    public Date getTarih() {
        return tarih;
    }

    public void setTarih(Date tarih) {
        this.tarih = tarih;
    }
    
    public String getUrunAcıklamasi() {
        return urunAcıklamasi;
    }

    public void setUrunAcıklamasi(String urunAcıklamasi) {
        this.urunAcıklamasi = urunAcıklamasi;
    }
    @Override
    public String toString() {
        return adi; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

}
