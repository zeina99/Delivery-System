package delivery.technicalServices.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public abstract class DAO{

    public Connection cnct() {
        // SQLite connection string
        String url = "jdbc:sqlite:C://Users/Lenovo/Desktop/Delivery/DeliveryDB.db";

        Connection cnct = null;
        try {
            cnct = DriverManager.getConnection(url);
            System.out.println("Works");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return cnct;
    }


    public void closeConnection(Connection conn){
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
