package tr.com.nemesisyazilim.types;

import java.util.Date;

public class StokContract {
    private int id;
    private int personelId;
    private int urunId;
    private Date tarih;
    private int adet;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPersonelId() {
        return personelId;
    }

    public void setPersonelId(int personelId) {
        this.personelId = personelId;
    }

    public int getUrunId() {
        return urunId;
    }

    public void setUrunId(int urunId) {
        this.urunId = urunId;
    }

    public Date getTarih() {
        return tarih;
    }

    public void setTarih(Date tarih) {
        this.tarih = tarih;
    }

    public int getAdet() {
        return adet;
    }

    public void setAdet(int adet) {
        this.adet = adet;
    }


    @Override
    public String toString() {
        return id + "" + personelId + "" + urunId + "" + "" + tarih + "" + adet;
    }

}
