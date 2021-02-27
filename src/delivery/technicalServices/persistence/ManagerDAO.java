package delivery.technicalServices.persistence;

import delivery.domain.Driver;
import delivery.domain.Loader;
import delivery.domain.Manager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ManagerDAO extends ConnectionFactory implements GenericDAO<Manager> {

//    private Connection connect() {
//        // SQLite connection string
//        String url = "jdbc:sqlite:/Users/zeinathabet/Downloads/DeliveryDB.db";
//
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
    public void insert(Manager object) {
        String sql = "INSERT INTO Manager(Name,PIN) VALUES(?,?)";

        try (Connection New = this.connect(); PreparedStatement Pstmt = New.prepareStatement(sql)) {

            Pstmt.setString(1, object.getName());
            Pstmt.setInt(2,object.getPin());
            Pstmt.executeUpdate();

            closeConnection(New);
        }

        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Manager object) {
        String sql = "UPDATE Manager SET Name = ?, PIN = ? WHERE ID = ?";

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
        String sql = "DELETE FROM Manager WHERE ID = ?";

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
    public Manager getById(int pk) {
        String sql = "SELECT ID, Name, PIN FROM Manager WHERE ID = ?";
        Manager manager = null;

        try (Connection One = this.connect();
             PreparedStatement pstmt  = One.prepareStatement(sql)){

            // set the value
            pstmt.setInt(1,pk);
            //
            ResultSet rs  = pstmt.executeQuery();

            // loop through the result set
            while (rs.next()) {
                manager = new Manager(
                        rs.getInt("ID"),
                        rs.getString("Name"),
                        rs.getInt("PIN")
                );
            }
            closeConnection(One);
            return manager;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return manager;
    }

    @Override
    public List<Manager> getAll() {
        String sql = "SELECT * FROM Manager";
        List<Manager> managerList = new ArrayList<>();

        try (Connection ALL = this.connect();
             Statement stmt  = ALL.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {
                managerList.add(new Manager(
                        rs.getInt("ID"),
                        rs.getString("Name"),
                        rs.getInt("PIN"))
                );
            }
            closeConnection(ALL);
            return managerList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return managerList;
    }

    public static void main(String[] args) {
        ManagerDAO manager = new ManagerDAO();
        System.out.println("\n");
        manager.getAll();
        System.out.println("______________________________________");
        System.out.println(manager.getById(2));
        System.out.println("______________________________________");
//        Manager New = new Manager("Gustav",17579);
//        manager.insert(New);
        System.out.println("Added a row to the database.");
        System.out.println("______________________________________");
        manager.delete(8);
        System.out.println("Deleted a row form the database.");
        System.out.println("______________________________________");
        //manager.update(11,"Josh", 11555);
        System.out.println("Updated a row in the database");
        System.out.println("______________________________________");
    }
}
