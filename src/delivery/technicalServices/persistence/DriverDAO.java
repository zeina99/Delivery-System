package delivery.technicalServices.persistence;

import delivery.domain.Driver;
import org.w3c.dom.ls.LSOutput;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DriverDAO  extends DAO implements GenericDAO<Driver> {


    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:C://Users/Lenovo/Desktop/Delivery/DeliveryDB.db";

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    @Override
    public void insert(Driver object) {

    }

    @Override
    public void update(Driver object) {

    }

    @Override
    public void delete(Driver object) {

    }

    @Override
    public Driver getById(int pk) {
        Driver did = null;
        String sql = "SELECT ID, Name, PIN " +"FROM Driver WHERE ID = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)){

            // set the value
            pstmt.setInt(1,pk);
            //
            ResultSet rs  = pstmt.executeQuery();

            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("ID") +  "\t" +
                        rs.getString("Name") + "\t" +
                        rs.getDouble("PIN"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public List<Driver> getAll() {
        String sql = "SELECT * FROM Driver";

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("ID") +  "\t" + "\t" + "\t" +
                        rs.getString("Name") + "\t" +  "\t" + "\t" +
                        rs.getInt("PIN"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static void main(String[] args) {
        DriverDAO driver = new DriverDAO();
        driver.getAll();
        System.out.println("************");
        driver.getById(5);

    }
}



