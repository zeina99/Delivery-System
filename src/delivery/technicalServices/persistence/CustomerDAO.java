package delivery.technicalServices.persistence;

import delivery.domain.Customer;

import java.sql.*;
import java.util.List;

public class CustomerDAO implements GenericDAO<Customer> {

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
    public void insert(Customer object) {
        String sql = "INSERT INTO Customer(Name) VALUES(?)";

        try (Connection New = this.connect(); PreparedStatement Pstmt = New.prepareStatement(sql)) {

            Pstmt.setString(1, object.getName());
            Pstmt.executeUpdate();

            closeConnection(New);
        }

        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // @Override
    // public void update(int id, String name, int pin) {
    //     String sql = "UPDATE Customer SET Name = ?, PIN = ? WHERE ID = ?";

    //     try (Connection up = this.connect();
    //          PreparedStatement Pstmt = up.prepareStatement(sql)) {

    //         // set the corresponding param

    //         Pstmt.setString(1, name);
    //         Pstmt.setInt(2, pin);
    //         Pstmt.setInt(3, id);
    //         // update
    //         Pstmt.executeUpdate();

    //         closeConnection(up);
    //     } catch (SQLException e) {
    //         System.out.println(e.getMessage());
    //     }
    // }
    @Override
	public void update(Customer object) {
		// TODO Auto-generated method stub
	    String sql = "UPDATE Customer SET Name = ?  WHERE ID = ?";

        try (Connection up = this.connect();
             PreparedStatement Pstmt = up.prepareStatement(sql)) {

            // set the corresponding param

            Pstmt.setString(1, object.getName());
            Pstmt.setInt(2, object.getId());
            // update
            Pstmt.executeUpdate();

            closeConnection(up);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }	
	}
    @Override
    public void delete(int dID) {
        String sql = "DELETE FROM Customer WHERE ID = ?";

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
    public Customer getById(int pk) {
        String sql = "SELECT ID, Name FROM Customer WHERE ID = ?";

        try (Connection One = this.connect();
             PreparedStatement pstmt  = One.prepareStatement(sql)){

            // set the value
            pstmt.setInt(1,pk);
            //
            ResultSet rs  = pstmt.executeQuery();

            System.out.println("ID: \t Name: ");
            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("ID") +  "\t   " +
                        rs.getString("Name") + "\t");
            }
            closeConnection(One);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Customer> getAll() {
        String sql = "SELECT * FROM Customer";

        try (Connection ALL = this.connect();
             Statement stmt  = ALL.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            System.out.println("ID: \t \t Name:  ");
            System.out.println("______________________________________");
            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("ID") +  "\t" + "\t" + "\t" +
                        rs.getString("Name") + "\t" +  "\t" + "\t");
            }
            closeConnection(ALL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static void main(String[] args) {
        CustomerDAO customer = new CustomerDAO();
        System.out.println("\n");
        customer.getAll();
        System.out.println("______________________________________");
        customer.getById(4);
        System.out.println("______________________________________");
        //Driver New = new Driver("Gustav",47579);
        // driver.insert(New);
        //System.out.println("Added a row to the database.");
        //System.out.println("______________________________________");
//        customer.delete(8);
//        System.out.println("Deleted a row form the database.");
//        System.out.println("______________________________________");
//        customer.update(11,"Josh", 41555);
//        System.out.println("Updated a row in the database");
//        System.out.println("______________________________________");
    }

	
}
