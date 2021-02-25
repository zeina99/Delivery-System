package delivery.technicalServices.persistence;

import delivery.domain.Order;

import java.sql.*;
import java.util.List;

public class OrderDAO implements  GenericDAO<Order> {


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
    public void insert(Order object) {
        String sql = "INSERT INTO Order(Customer_ID,Order_Type,Time_Slot) VALUES(?,?,?)";

        try (Connection New = this.connect(); PreparedStatement Pstmt = New.prepareStatement(sql)) {

            Pstmt.setObject(1, object.getCustomer());
            Pstmt.setObject(2, object.getOrderType());
            Pstmt.setObject(3, object.getTimeSlot());

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

    ////__________________________

    public void UpdateOrder(int Customer_id, String Type, String Time){
        String sql = "UPDATE Order SET Customer_ID = ?, Order_Type = ?, Time_Slot = ? WHERE ID = ?";

        try (Connection up = this.connect();
             PreparedStatement Pstmt = up.prepareStatement(sql)) {

            // set the corresponding param

            Pstmt.setInt(1, Customer_id);
            Pstmt.setString(2, Type);    ////or Pstmt.setObject??
            Pstmt.setString(3, Time);
            // update
            Pstmt.executeUpdate();
            closeConnection(up);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(int dID) {
        String sql = "DELETE FROM Order WHERE ID = ?";

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
    public Order getById(int pk) {
        String sql = "SELECT * FROM Order WHERE ID = ?";

        try (Connection One = this.connect();
             PreparedStatement pstmt  = One.prepareStatement(sql)){

            // set the value
            pstmt.setInt(1,pk);
            //
            ResultSet rs  = pstmt.executeQuery();

            System.out.println("Order ID:  \tCustomer ID:   \tOrder Type:   \tTime Slot:");
            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("ID") +  "\t   " +
                        rs.getObject("Customer_ID") + "\t" +
                        rs.getObject("Order_Type") + "\t" +
                        rs.getObject("Time_Slot"));
            }
            closeConnection(One);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Order> getAll() {
        String sql = "SELECT * FROM Order";

        try (Connection ALL = this.connect();
             Statement stmt  = ALL.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            System.out.println("Order ID: \t \t Customer ID: \t \t Order Type: \t \t Time Slot:");
            System.out.println("______________________________________");
            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("ID") +  "\t   " +
                        rs.getObject("Customer_ID") + "\t" +
                        rs.getObject("Order_Type") + "\t" +
                        rs.getObject("Time_Slot"));
            }
            closeConnection(ALL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
