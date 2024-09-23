
package tr.com.nemesisyazilim.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tr.com.nemesisyazilim.core.ObjectHelper;
import tr.com.nemesisyazilim.interfaces.DALInterfaces;
import tr.com.nemesisyazilim.types.PersonelContract;

public class PersonelDAL extends ObjectHelper implements DALInterfaces<PersonelContract> {

    @Override
    public void Insert(PersonelContract entity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection(); // ObjectHelper'dan getConnection doğrudan çağrılıyor
            String sql = "INSERT INTO Personel (AdiSoyadi, email) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, entity.getAdiSoyadi());
            preparedStatement.setString(2, entity.getEmail());
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
    public PersonelContract Delete(PersonelContract entity) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void Update(PersonelContract entity) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public List<PersonelContract> GetById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public List<PersonelContract> GetALL() {
        List<PersonelContract> dataContracts = new ArrayList<>();
        Connection connection = getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Personel");
            while (resultSet.next()) {
                PersonelContract contract = new PersonelContract();
                contract.setId(resultSet.getInt("Id"));
                contract.setAdiSoyadi(resultSet.getString("AdiSoyadi"));
                contract.setEmail(resultSet.getString("Email"));

                dataContracts.add(contract);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersonelDAL.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(PersonelDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return dataContracts;
    }
    
}
