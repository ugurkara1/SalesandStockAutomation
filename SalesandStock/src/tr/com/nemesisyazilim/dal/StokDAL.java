package tr.com.nemesisyazilim.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tr.com.nemesisyazilim.complex.types.StokContractComplex;
import tr.com.nemesisyazilim.core.ObjectHelper;
import tr.com.nemesisyazilim.interfaces.DALInterfaces;
import tr.com.nemesisyazilim.types.StokContract;

public class StokDAL extends ObjectHelper implements DALInterfaces<StokContract> {

    @Override
    public void Insert(StokContract entity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection();
            String sql = "INSERT INTO Stok (UrunId, PersonelId, Tarih, Adet) VALUES (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, entity.getUrunId());
            preparedStatement.setInt(2, entity.getPersonelId());
            preparedStatement.setDate(3, new java.sql.Date(entity.getTarih().getTime()));
            preparedStatement.setInt(4, entity.getAdet());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public StokContract Delete(StokContract entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void Update(StokContract entity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection();
            String sql = "UPDATE Stok SET Adet = ?, Tarih = ? WHERE Id = ?";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, entity.getAdet());
            preparedStatement.setDate(2, new java.sql.Date(entity.getTarih().getTime()));
            preparedStatement.setInt(3, entity.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<StokContract> GetById(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    // Belirtilen UrunId'ye göre stok kaydını getirir
    public StokContract GetStokByUrunId(int urunId) {
        StokContract stokContract = null;

        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM Stok WHERE UrunId = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, urunId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                stokContract = new StokContract();
                stokContract.setId(resultSet.getInt("Id"));
                stokContract.setUrunId(resultSet.getInt("UrunId"));
                stokContract.setPersonelId(resultSet.getInt("PersonelId"));
                stokContract.setTarih(resultSet.getDate("Tarih"));
                stokContract.setAdet(resultSet.getInt("Adet"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return stokContract;
    }

    // Tüm stok kayıtlarını getirir
    public List<StokContractComplex> GetAllStok() {
        List<StokContractComplex> stokList = new ArrayList<>();
        
        try (Connection conn = getConnection()) {
            String sql = "SELECT s.Id, p.AdiSoyadi, u.Adi, s.Adet, s.Tarih " +
                         "FROM Stok s " +
                         "LEFT JOIN Urunler u ON s.UrunId = u.Id " +
                         "LEFT JOIN Personel p ON s.PersonelId = p.Id";
            PreparedStatement statement = conn.prepareStatement(sql);
            
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                StokContractComplex stok = new StokContractComplex();
                stok.setId(resultSet.getInt("Id"));
                stok.setPersonelAdi(resultSet.getString("AdiSoyadi"));
                stok.setUrunAdi(resultSet.getString("Adi"));
                stok.setAdet(resultSet.getInt("Adet"));
                stok.setTarih(resultSet.getDate("Tarih"));
                stokList.add(stok);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return stokList;
    }

    @Override
    public List<StokContract> GetALL() {
        return null;
    }
}
