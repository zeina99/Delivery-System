package delivery.technicalServices.persistence;

import delivery.domain.*;
import delivery.domain.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO extends ConnectionFactory implements GenericDAO<Order> {


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
    public void insert(Order object) {
        String sql = "INSERT INTO \"Order\" (Customer_ID,Order_Type,Time_Slot) VALUES(?,?,?)";

        try (Connection New = this.connect(); PreparedStatement Pstmt = New.prepareStatement(sql)) {

            Pstmt.setInt(1, object.getCustomer().getId());
            Pstmt.setString(2, object.getOrderType().toString());
            Pstmt.setString(3, object.getTimeSlot().toString());

            Pstmt.executeUpdate();
            closeConnection(New);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Order object) {
        String sql = "UPDATE \"Order\" SET Customer_ID = ?, Order_Type = ?, Time_Slot = ? WHERE ID = ?";

        try (Connection up = this.connect();
             PreparedStatement Pstmt = up.prepareStatement(sql)) {

            // set the corresponding param

            Pstmt.setInt(1, object.getCustomer().getId());
            Pstmt.setString(2, object.getOrderType().toString());
            Pstmt.setString(3, object.getTimeSlot().toString());
            Pstmt.setInt(4, object.getId());
            // update
            Pstmt.executeUpdate();

            closeConnection(up);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(int dID) {
        String sql = "DELETE FROM \"Order\" WHERE ID = ?";

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
        String sql = "SELECT * FROM \"Order\" WHERE ID = ?";
        Order order = null;
        CustomerDAO Customer = new CustomerDAO();
        OrderItemDAO orderItemDAO = new OrderItemDAO();
        try (Connection One = this.connect();
             PreparedStatement pstmt = One.prepareStatement(sql)) {

            // set the value
            pstmt.setInt(1, pk);
            ResultSet rs = pstmt.executeQuery();

            // loop through the result set
            while (rs.next()) {
                order = new Order(
                        rs.getInt("ID"),
                        Customer.getById(rs.getInt("Customer_ID")),
                        OrderType.valueOf(rs.getString("Order_Type")),
                        TimeSlots.valueOf(rs.getString("Time_Slot")),
                        orderItemDAO.getOrderItemsByOrderId(pk)
                );

            }
            closeConnection(One);
            return order;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return order;
    }

    public void setDeliveryFeeByID(int orderID, double deliveryFee){
        String sql = "UPDATE \"Order\" SET Delivery_Fee = ? WHERE ID = ?";

        try (Connection One = this.connect();
             PreparedStatement pstmt = One.prepareStatement(sql)) {

            // set the value
            pstmt.setDouble(1, deliveryFee);
            pstmt.setInt(2, orderID);

            pstmt.executeUpdate();

            // loop through the result set
//            while (rs.next()) {
//                order = new Order(
//                        rs.getInt("ID"),
//                        Customer.getById(rs.getInt("Customer_ID")),
//                        OrderType.valueOf(rs.getString("Order_Type")),
//                        TimeSlots.valueOf(rs.getString("Time_Slot")),
//                        orderItemDAO.getOrderItemsByOrderId(pk)
//                );
//
//            }
            closeConnection(One);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public List<Order> getAll() {
        String sql = "SELECT * FROM \"Order\"";
        List<Order> orderlist = new ArrayList<>();
        CustomerDAO customer = new CustomerDAO();
        OrderItemDAO orderItemDAO = new OrderItemDAO();
        try (Connection ALL = this.connect();
             Statement stmt = ALL.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // loop through the result set
            while (rs.next()) {
                orderlist.add(new Order(
                        rs.getInt("ID"),
                        customer.getById(rs.getInt("Customer_ID")),
                        OrderType.valueOf(rs.getString("Order_Type")),
                        TimeSlots.valueOf(rs.getString("Time_Slot")),
                        orderItemDAO.getOrderItemsByOrderId(rs.getInt("ID")))
                );

            }
            closeConnection(ALL);
            return orderlist;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return orderlist;
    }

    public void insertAll(List<Order> orders) {
        String sql = "INSERT INTO \"Order\" (Customer_ID,Order_Type,Time_Slot) VALUES(?,?,?)";

        try (Connection New = this.connect(); PreparedStatement Pstmt = New.prepareStatement(sql)) {
            for (Order order :
                    orders) {
                Pstmt.setInt(1, order.getCustomer().getId());
                Pstmt.setString(2, order.getOrderType().toString());
                Pstmt.setString(3, order.getTimeSlot().toString());

                Pstmt.executeUpdate();
            }

            closeConnection(New);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
