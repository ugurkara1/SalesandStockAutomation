package tr.com.nemesisyazilim.dal;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tr.com.nemesisyazilim.complex.types.SatisContractComplex;
import tr.com.nemesisyazilim.core.ObjectHelper;
import tr.com.nemesisyazilim.interfaces.DALInterfaces;
import tr.com.nemesisyazilim.types.SatisContract;

public class SatisDAL extends ObjectHelper implements DALInterfaces<SatisContract> {

    @Override
    public void Insert(SatisContract entity) {
        String sql = "INSERT INTO Satis (UrunId, MusteriId, Tarih, Adet, PersonelId) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, entity.getUrunId());
            preparedStatement.setInt(2, entity.getMusteriId());
            preparedStatement.setDate(3, new java.sql.Date(entity.getTarih().getTime()));
            preparedStatement.setInt(4, entity.getAdet());
            preparedStatement.setInt(5, entity.getPersonelId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Daha gelişmiş hata yönetimi uygulanabilir.
        }
    }

    @Override
    public SatisContract Delete(SatisContract entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void Update(SatisContract entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<SatisContract> GetById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<SatisContractComplex> GetAllSatis() {
        List<SatisContractComplex> dataContract = new ArrayList<>();
        Connection conn = getConnection();
        SatisContractComplex contract;

        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT "
                    + "    Satis.Id, "
                    + "    Personel.AdiSoyadi AS PersonelAdi, "
                    + "    Musteri.AdiSoyadi AS MusteriAdi,"
                    + "    Urunler.Adi AS UrunAdi, "
                    + "    Satis.Adet, "
                    + "    Satis.Tarih "
                    + "FROM "
                    + "    Satis "
                    + "LEFT JOIN "
                    + "    Musteri ON Satis.MusteriId = Musteri.Id "
                    + "LEFT JOIN  "
                    + "    Urunler ON Satis.UrunId = Urunler.Id "
                    + "LEFT JOIN  "
                    + "    Personel ON Satis.PersonelId = Personel.Id "
                    + "ORDER BY  "
                    + "    Satis.Id ASC");

            while (rs.next()) {
                contract = new SatisContractComplex();
                contract.setId(rs.getInt("Id"));
                contract.setPersonelAdi(rs.getString("PersonelAdi")); // Personel adı alias'ını doğru kullanma
                contract.setMusteriAdi(rs.getString("MusteriAdi"));  // Müşteri adı alias'ını doğru kullanma

                contract.setTarih(rs.getDate("Tarih"));
                contract.setUrunAdi(rs.getString("UrunAdi"));
                contract.setAdet(rs.getInt("Adet"));
                dataContract.add(contract);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SatisDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dataContract;
    }

    @Override
    public List<SatisContract> GetALL() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
