package tr.com.nemesisyazilim.complex.types;

import java.util.Date;

public class SatisContractComplex {

    private int id;
    private String musteriAdi;
    private String personelAdi;
    private String urunAdi;
    private int adet;
    private Date tarih;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMusteriAdi() {
        return musteriAdi;
    }

    public void setMusteriAdi(String musteriAdi) {
        this.musteriAdi = musteriAdi;
    }

    public String getPersonelAdi() {
        return personelAdi;
    }

    public void setPersonelAdi(String personelAdi) {
        this.personelAdi = personelAdi;
    }

    public String getUrunAdi() {
        return urunAdi;
    }

    public void setUrunAdi(String urunAdi) {
        this.urunAdi = urunAdi;
    }

    public int getAdet() {
        return adet;
    }

    public void setAdet(int adet) {
        this.adet = adet;
    }

    public Date getTarih() {
        return tarih;
    }

    public void setTarih(Date tarih) {
        this.tarih = tarih;
    }

    public Object[] getVeriler() {
        Object[] veriler = {id, personelAdi, musteriAdi, urunAdi, adet, tarih};
        return veriler;
    }

    @Override
    public String toString() {
        return musteriAdi + " " + personelAdi + " " + urunAdi; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

}
