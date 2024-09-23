package tr.com.nemesisyazilim.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tr.com.nemesisyazilim.core.ObjectHelper;
import tr.com.nemesisyazilim.interfaces.DALInterfaces;
import tr.com.nemesisyazilim.types.SehirContract;

public class SehirDAL extends ObjectHelper implements DALInterfaces<SehirContract> {

    @Override
    public void Insert(SehirContract entity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection();
            String sql = "INSERT INTO Sehirler (Id, ad) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, entity.getId());
            preparedStatement.setString(2, entity.getAdi());

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

    public List<SehirContract> getAllSehirler() {
        List<SehirContract> sehirler = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            String sql = "SELECT * FROM Sehirler";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                SehirContract sehir = new SehirContract();
                sehir.setId(resultSet.getInt("Id"));
                sehir.setAdi(resultSet.getString("ad"));
                sehirler.add(sehir);
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return sehirler;
    }

    @Override
    public SehirContract Delete(SehirContract entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void Update(SehirContract entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<SehirContract> GetById(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<SehirContract> GetALL() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
