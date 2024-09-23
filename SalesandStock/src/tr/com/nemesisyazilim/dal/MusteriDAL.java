
package tr.com.nemesisyazilim.dal;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tr.com.nemesisyazilim.core.ObjectHelper;
import tr.com.nemesisyazilim.interfaces.DALInterfaces;
import tr.com.nemesisyazilim.types.MusteriContract;

public class MusteriDAL extends ObjectHelper implements DALInterfaces<MusteriContract> {

    @Override
    public void Insert(MusteriContract entity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection(); // ObjectHelper'dan getConnection doğrudan çağrılıyor
            String sql = "INSERT INTO Musteri (AdiSoyadi, Telefon,Adres,SehirId) VALUES (?, ?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, entity.getAdiSoyadi());
            preparedStatement.setString(2, entity.getTelefon());
            preparedStatement.setString(3, entity.getAdres());
            preparedStatement.setInt(4,entity.getSehirId());
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

    public List<MusteriContract> Select() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public MusteriContract Delete(MusteriContract entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void Update(MusteriContract entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<MusteriContract> GetById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<MusteriContract> GetALL() {
        List<MusteriContract> dataContracts = new ArrayList<>();
        Connection connection = getConnection();
        MusteriContract contract;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from Musteri");
            while (resultSet.next()) {
                contract = new MusteriContract();
                contract.setId(resultSet.getInt("Id"));
                contract.setAdiSoyadi(resultSet.getString("AdiSoyadi"));
                contract.setAdres(resultSet.getString("Adres"));
                contract.setSehirId(resultSet.getInt("SehirId"));
                contract.setTelefon(resultSet.getString("Telefon"));
                dataContracts.add(contract);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            
        }
        return dataContracts; 
    }
    
}
