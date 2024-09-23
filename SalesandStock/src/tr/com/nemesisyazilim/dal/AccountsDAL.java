package tr.com.nemesisyazilim.dal;

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
import tr.com.nemesisyazilim.types.AccountsContract;

public class AccountsDAL extends ObjectHelper implements DALInterfaces<AccountsContract> {

    @Override
    public void Insert(AccountsContract entity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection(); // ObjectHelper'dan getConnection doğrudan çağrılıyor
            String sql = "INSERT INTO Accounts  (PersonelId, YetkiId,Sifre) VALUES (?, ?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, entity.getPersonelId());
            preparedStatement.setInt(2, entity.getYetkiId());
            preparedStatement.setString(3, entity.getSifre());

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

    public AccountsContract GetPersonelIdVeSifre(int personelId, String sifre) {
        AccountsContract contract = new AccountsContract();
        Connection connection = getConnection();
        try {
            String sql = "SELECT a.PersonelId, a.Sifre, a.YetkiId "
                    + "FROM Accounts a "
                    + "WHERE a.PersonelId = ? AND a.Sifre = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, personelId);
            preparedStatement.setString(2, sifre.trim());
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                contract.setPersonelId(rs.getInt("PersonelId"));
                contract.setSifre(rs.getString("Sifre"));
                contract.setYetkiId(rs.getInt("YetkiId"));
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contract;
    }

    public AccountsContract GetYetkiId(int personelId) {
        AccountsContract contract = new AccountsContract();
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            String sql = "Select * from accounts where PersonelId= " + personelId + " ";
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                contract.setPersonelId(rs.getInt("PersonelId"));
                contract.setSifre(rs.getString("Sifre"));

                contract.setYetkiId(rs.getInt("YetkiId"));
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contract;
    }

    @Override
    public AccountsContract Delete(AccountsContract entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void Update(AccountsContract entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<AccountsContract> GetById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<AccountsContract> GetALL() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
