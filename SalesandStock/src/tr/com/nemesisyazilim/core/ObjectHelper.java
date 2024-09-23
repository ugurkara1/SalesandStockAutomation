package tr.com.nemesisyazilim.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import tr.com.nemesisyazilim.interfaces.CoreInterfaces;

public class ObjectHelper extends CoreFields implements CoreInterfaces {
    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(getUrl(), getUserName(), getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
