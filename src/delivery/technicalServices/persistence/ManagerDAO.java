package delivery.technicalServices.persistence;

import delivery.domain.Manager;

import java.sql.*;
import java.util.List;

public class ManagerDAO implements GenericDAO<Manager> {

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
    public void insert(Manager object) {
        String sql = "INSERT INTO Manager(Name,PIN) VALUES(?,?)";

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
        String sql = "DELETE FROM Manager WHERE ID = ?";

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
    public Manager getById(int pk) {
        String sql = "SELECT ID, Name, PIN " +"FROM Manager WHERE ID = ?";

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
                        rs.getString("Name") + "\t" +
                        rs.getInt("PIN"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Manager> getAll() {
        String sql = "SELECT * FROM Manager";

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
        }
        return null;
    }

    public static void main(String[] args) {
        ManagerDAO manager = new ManagerDAO();
        System.out.println("\n");
        manager.getAll();
        System.out.println("______________________________________");
        manager.getById(4);
        System.out.println("______________________________________");
//        Manager New = new Manager("Gustav",17579);
//        manager.insert(New);
        System.out.println("Added a row to the database.");
        System.out.println("______________________________________");
        manager.delete(8);
        System.out.println("Deleted a row form the database.");
        System.out.println("______________________________________");
        manager.update(11,"Josh", 11555);
        System.out.println("Updated a row in the database");
        System.out.println("______________________________________");
    }
}
