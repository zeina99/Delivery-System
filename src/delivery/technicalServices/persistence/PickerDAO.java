package delivery.technicalServices.persistence;

import delivery.domain.Picker;

import java.sql.*;
import java.util.List;

public class PickerDAO implements GenericDAO<Picker> {

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
    public void insert(Picker object) {
        String sql = "INSERT INTO PICKER(Name,PIN) VALUES(?,?)";

        try (Connection New = this.connect(); PreparedStatement Pstmt = New.prepareStatement(sql)) {

            Pstmt.setString(1, object.getName());
            Pstmt.setInt(2,object.getPin());
            Pstmt.executeUpdate();
        }

        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(int id, String name, int pin) {

    }

    @Override
    public void delete(int dID) {
        String sql = "DELETE FROM PICKER WHERE ID = ?";

        try (Connection del = this.connect();
             PreparedStatement Pstmt = del.prepareStatement(sql)) {

            // set the corresponding param
            Pstmt.setInt(1, dID);
            // execute the delete statement
            Pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Picker getById(int pk) {
        String sql = "SELECT ID, Name, PIN " +"FROM PICKER WHERE ID = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)){

            // set the value
            pstmt.setInt(1,pk);
            //
            ResultSet rs  = pstmt.executeQuery();

            System.out.println("ID: \tName: \tPIN: ");
            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("ID") +  "\t   " +
                        rs.getString("Name") + "\t \t" +
                        rs.getInt("PIN"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Picker> getAll() {
        String sql = "SELECT * FROM PICKER";

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            System.out.println("ID: \t \t Name: \t \t PIN: ");
            System.out.println("______________________________________");
            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("ID") +  "\t" + "\t" + "\t" +
                        rs.getString("Name") + "\t" +  "\t" + "\t" +
                        rs.getInt("PIN"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } return null;
    }

    public static void main(String[] args) {
        PickerDAO picker = new PickerDAO();
        System.out.println("\n");
        picker.getAll();
        System.out.println("______________________________________");
        picker.getById(4);
        System.out.println("______________________________________");
        //Picker New = new Picker("Gustav",27579);
        // picker.insert(New);
        //System.out.println("Added a row to the database.");
//        System.out.println("______________________________________");
//        picker.delete(8);
//        System.out.println("Deleted a row form the database.");
//        System.out.println("______________________________________");
//        picker.update(11,"Josh", 21555);
//        System.out.println("Updated a row in the database");
//        System.out.println("______________________________________");

    }

}