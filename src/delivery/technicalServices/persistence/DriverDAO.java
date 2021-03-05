package delivery.technicalServices.persistence;

import delivery.domain.Category;
import delivery.domain.Driver;
import org.w3c.dom.ls.LSOutput;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DriverDAO extends ConnectionFactory implements GenericDAO<Driver> {

/////Heeyyy it's me SARA!
//    private Connection connect() {
//        // SQLite connection string
//        String url = "jdbc:sqlite:/Users/zeinathabet/Downloads/DeliveryDB.db";
//        //jdbc:sqlite:C://Users/Lenovo/Desktop/Delivery/DeliveryDB.db
//        Connection conn = null;
//        try {
//            conn = DriverManager.getConnection(url);
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//        return conn;
//    }
//
//    public void closeConnection(Connection conn){
//        try {
//            if (conn != null) {
//                conn.close();
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//    }

    @Override
    public void insert(Driver object) {
        String sql = "INSERT INTO Driver(Name,PIN) VALUES(?,?)";

        try (Connection New = this.connect(); PreparedStatement Pstmt = New.prepareStatement(sql)) {

            Pstmt.setString(1, object.getName());
            Pstmt.setInt(2, object.getPin());
            Pstmt.executeUpdate();

            closeConnection(New);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Driver object) {
        String sql = "UPDATE Driver SET Name = ?, PIN = ? WHERE ID = ?";

        try (Connection up = this.connect();
             PreparedStatement Pstmt = up.prepareStatement(sql)) {

            // set the corresponding param

            Pstmt.setString(1, object.getName());
            Pstmt.setInt(2, object.getPin());
            Pstmt.setInt(3, object.getId());
            // update
            Pstmt.executeUpdate();

            closeConnection(up);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(int dID) {
        String sql = "DELETE FROM Driver WHERE ID = ?";

        try (Connection del = this.connect();
             PreparedStatement Pstmt = del.prepareStatement(sql)) {

            // set the corresponding param
            Pstmt.setInt(1, dID);
            // execute the delete statement
            Pstmt.executeUpdate();

            closeConnection(del);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public Driver getById(int pk) {
        String sql = "SELECT ID, Name, PIN FROM Driver WHERE ID = ?";
        Driver driver = null;
        try (Connection One = this.connect();
             PreparedStatement pstmt = One.prepareStatement(sql)) {

            // set the value
            pstmt.setInt(1, pk);
            ResultSet rs = pstmt.executeQuery();

            driver = new Driver(
                    rs.getInt("ID"),
                    rs.getString("Name"),
                    rs.getInt("PIN")
            );


            closeConnection(One);
            return driver;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return driver;
    }

    @Override
    public List<Driver> getAll() {
        String sql = "SELECT * FROM Driver";
        List<Driver> DriverList = new ArrayList<>();

        try (Connection ALL = this.connect();
             Statement stmt = ALL.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // loop through the result set
            while (rs.next()) {
                DriverList.add(new Driver(
                        rs.getInt("ID"),
                        rs.getString("Name"),
                        rs.getInt("PIN"))
                );

            }
            closeConnection(ALL);
            return DriverList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return DriverList;
    }


    public static void main(String[] args) {
//        DriverDAO driver = new DriverDAO();
//        System.out.println("\n");
//        System.out.println(driver.getAll());
//        System.out.println("______________________________________");
//        driver.getById(4);
//        System.out.println("______________________________________");
//        Driver New = new Driver("Nerd",49999);
//        driver.insert(New);
//        System.out.println("Added a row to the database.");
//        System.out.println("______________________________________");
//        driver.delete(26);
//        System.out.println("Deleted a row form the database.");
//        System.out.println("______________________________________");
//        Driver d = new Driver("Sam",44444);
//        driver.update(d);
//        System.out.println("Updated a row in the database");
//        System.out.println("______________________________________");

    }

    public boolean validateDriver(int pinEntered) {
        String sql = "SELECT ID, Name, PIN FROM Driver WHERE PIN = ?";

        boolean doesExist = false;
        try (Connection One = this.connect();
             PreparedStatement pstmt = One.prepareStatement(sql)) {

            // set the value
            pstmt.setInt(1, pinEntered);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next())
                doesExist = true;



            closeConnection(One);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return doesExist;
    }
}



