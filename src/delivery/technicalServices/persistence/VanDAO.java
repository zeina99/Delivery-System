package delivery.technicalServices.persistence;

import delivery.domain.Van;

import java.sql.*;
import java.util.List;

public class VanDAO implements GenericDAO<Van> {

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

    public void closeConnection(Connection conn){
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void insert(Van object) {
        String sql = "INSERT INTO Van(Van_Description_ID,Driver_ID) VALUES(?,?)";

        try (Connection New = this.connect(); PreparedStatement Pstmt = New.prepareStatement(sql)) {

            Pstmt.setObject(1, object.getVanDescription());
            Pstmt.setInt(2,object.getDriver_id());
            Pstmt.executeUpdate();

            closeConnection(New);
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
        String sql = "DELETE FROM Van WHERE ID = ?";

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
    public Van getById(int pk) {
        String sql = "SELECT * FROM Van WHERE ID = ?";

        try (Connection One = this.connect();
             PreparedStatement pstmt  = One.prepareStatement(sql)){

            // set the value
            pstmt.setInt(1,pk);
            //
            ResultSet rs  = pstmt.executeQuery();

            System.out.println("ID: \tVan Description ID: \tDriver ID: ");
            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("ID") +  "\t   " +
                        rs.getObject("Van_Description_ID") + "\t" +        ////Int or Object??
                        rs.getInt("Driver_ID"));
            }
            closeConnection(One);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Van> getAll() {
        String sql = "SELECT * FROM Van";

        try (Connection ALL = this.connect();
             Statement stmt  = ALL.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            System.out.println("ID: \t \tVan Description ID:\t \tDriver ID: ");
            System.out.println("______________________________________");
            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("ID") +  "\t   " +
                        rs.getObject("Van_Description_ID") + "\t" +        ////Int or Object??
                        rs.getInt("Driver_ID"));
            }
            closeConnection(ALL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
