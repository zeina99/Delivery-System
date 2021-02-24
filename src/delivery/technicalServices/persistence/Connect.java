package delivery.technicalServices.persistence;
import java.sql.*;

public class Connect {


    public static Connection cnct() {
        Connection conn = null;
        try {
            // path to the database
            String url = "jdbc:sqlite:C:/Users/Lenovo/Desktop/Delivery/DeliveryDB.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return conn;
    }





    public static void main(String[] args) {
        cnct();
    }

}
