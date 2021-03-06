package delivery.technicalServices.persistence;

import delivery.domain.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO extends ConnectionFactory implements GenericDAO<Customer> {

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

    @Override
	public void update(Customer object) {
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
        Customer customer = null;
        try (Connection One = this.connect();
             PreparedStatement pstmt  = One.prepareStatement(sql)){

            // set the value
            pstmt.setInt(1,pk);
            //
            ResultSet rs  = pstmt.executeQuery();

            // loop through the result set
            while (rs.next()) {
                customer = new Customer(
                        rs.getInt("ID"),
                        rs.getString("Name")
                );

            }
            closeConnection(One);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return customer;
    }

    @Override
    public List<Customer> getAll() {
        String sql = "SELECT * FROM Customer";
        List<Customer> CustomerList  = new ArrayList<>();

        try (Connection ALL = this.connect();
             Statement stmt  = ALL.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){


            // loop through the result set
            while (rs.next()) {
                CustomerList.add(new Customer(
                        rs.getInt("ID"),
                        rs.getString("Name") )
                );

            }
            closeConnection(ALL);
            return CustomerList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return CustomerList;
    }

    public static void main(String[] args) {
        CustomerDAO customer = new CustomerDAO();
        System.out.println("\n");
        customer.getAll();
        System.out.println("______________________________________");
        customer.getById(4);
        System.out.println("______________________________________");
//        Customer New = new Customer();
//        customer.insert(New);
        System.out.println("Added a row to the database.");
        System.out.println("______________________________________");
        customer.delete(14);
        System.out.println("Deleted a row form the database.");
        System.out.println("______________________________________");
        Customer c = new Customer(17, "Samy");
        customer.update(c);
        System.out.println("Updated a row in the database");
        System.out.println("______________________________________");
    }

	
}
