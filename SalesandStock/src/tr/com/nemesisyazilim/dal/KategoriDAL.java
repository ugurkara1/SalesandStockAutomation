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
import tr.com.nemesisyazilim.types.KategoriContract;

public class KategoriDAL extends ObjectHelper implements DALInterfaces<KategoriContract> {

    @Override
    public void Insert(KategoriContract entity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection(); // ObjectHelper'dan getConnection doğrudan çağrılıyor
            String sql = "INSERT INTO Kategori (Adi, ParentId) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, entity.getAdi());
            preparedStatement.setInt(2, entity.getParentId());
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
    public List<KategoriContract> GetALL() {
        List<KategoriContract> dataContracts = new ArrayList<>();
        Connection connection = getConnection();
        KategoriContract contract;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Kategori");
            while (resultSet.next()) {
                contract = new KategoriContract();
                contract.setId(resultSet.getInt("Id"));
                contract.setAdi(resultSet.getString("Adi"));
                contract.setParentId(resultSet.getInt("ParentId"));
                dataContracts.add(contract);
            }
        } catch (SQLException ex) {
            Logger.getLogger(KategoriDAL.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(KategoriDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return dataContracts;
    }

    public List<KategoriContract> GetAllParentId() {
        List<KategoriContract> dataContracts = new ArrayList<>();
        Connection connection = getConnection();
        KategoriContract contract;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Kategori Where parentId=0");
            while (resultSet.next()) {
                contract = new KategoriContract();
                contract.setId(resultSet.getInt("Id"));
                contract.setAdi(resultSet.getString("Adi"));
                contract.setParentId(resultSet.getInt("ParentId"));
                dataContracts.add(contract);
            }
        } catch (SQLException ex) {
            Logger.getLogger(KategoriDAL.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(KategoriDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return dataContracts;
    }

    @Override
    public KategoriContract Delete(KategoriContract entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void Update(KategoriContract entity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection(); // ObjectHelper'dan getConnection doğrudan çağrılıyor
            String sql = "UPDATE Kategori SET Adi='" + entity.getAdi() + "',ParentId=" + entity.getParentId() + "where id=" + entity.getId() + " ";
            preparedStatement = connection.prepareStatement(sql);
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
    public List<KategoriContract> GetById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public List<KategoriContract> GetSearchKategori(String kategoriAdi){
        List<KategoriContract> DataContract=new ArrayList<KategoriContract>();
        Connection connection=getConnection();
        try {
            Statement statement =connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Kategori WHERE Adi LIKE '%" + kategoriAdi + "%'");
            while(rs.next()){
                KategoriContract contract=new KategoriContract();
                
                contract.setAdi(rs.getString("Adi"));
                contract.setParentId(rs.getInt("ParentId"));
                
                DataContract.add(contract);
            }
        } catch (SQLException ex) {
            Logger.getLogger(KategoriDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return DataContract;
    }
}
