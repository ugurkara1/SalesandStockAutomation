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
import tr.com.nemesisyazilim.core.ObjectHelper;
import tr.com.nemesisyazilim.interfaces.DALInterfaces;
import tr.com.nemesisyazilim.types.UrunlerContract;

public class UrunlerDAL extends ObjectHelper implements DALInterfaces<UrunlerContract> {

    @Override
    public void Insert(UrunlerContract entity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection();
            String sql = "INSERT INTO Urunler (Adi, KategoriId, Tarih, Fiyat) VALUES (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, entity.getAdi());
            preparedStatement.setInt(2, entity.getKategoriId());
            preparedStatement.setDate(3, new Date(entity.getTarih().getTime()));
            preparedStatement.setInt(4, entity.getFiyat());
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
    public UrunlerContract Delete(UrunlerContract entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void Update(UrunlerContract entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<UrunlerContract> GetById(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<UrunlerContract> GetALL() {
        List<UrunlerContract> dataContracts = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Urunler");

            while (resultSet.next()) {
                UrunlerContract contract = new UrunlerContract();
                contract.setId(resultSet.getInt("Id")); // ID'yi ayarlayın
                contract.setAdi(resultSet.getString("Adi"));
                contract.setKategoriId(resultSet.getInt("KategoriId"));
                contract.setTarih(resultSet.getDate("Tarih"));
                contract.setFiyat(resultSet.getInt("Fiyat"));
                dataContracts.add(contract);
                //System.out.println("Ürün: " + resultSet.getString("Adi") + ", ID: " + resultSet.getInt("Id")); // Debugging
            }
        } catch (SQLException ex) {
            Logger.getLogger(UrunlerDAL.class.getName()).log(Level.SEVERE, "SQL Error", ex);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(UrunlerDAL.class.getName()).log(Level.SEVERE, "Error closing resources", ex);
            }
        }

        return dataContracts;
    }
}