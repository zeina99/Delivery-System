package delivery.technicalServices.persistence;

import delivery.domain.Category;
import delivery.domain.Order;
import delivery.domain.OrderItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDAO extends ConnectionFactory implements GenericDAO<OrderItem> {


    @Override
    public void insert(OrderItem object) {
        String sql = "INSERT INTO Order_Item(Order_ID,Category_ID) VALUES(?,?)";

        try (Connection New = this.connect(); PreparedStatement Pstmt = New.prepareStatement(sql)) {

            Pstmt.setInt(1, object.getOrderID());
            Pstmt.setObject(2,object.getCategory());
            Pstmt.executeUpdate();

            closeConnection(New);
        }

        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(OrderItem object) {
        String sql = "UPDATE Order_Item SET Order_ID = ? , Category_ID = ? WHERE ID = ?";

        try (Connection New = this.connect(); PreparedStatement Pstmt = New.prepareStatement(sql)) {

            Pstmt.setInt(1, object.getOrderID());
            Pstmt.setInt(2, object.getCategory().getId());
            Pstmt.setInt(3, object.getId());

            Pstmt.executeUpdate();
            closeConnection(New);
        }

        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(int dID) {
        String sql = "DELETE FROM Order_Item WHERE ID = ?";

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
    public OrderItem getById(int pk) {
        String sql = "SELECT * FROM Order_Item WHERE ID = ?";
        OrderItem orderitem = null;
        CategoryDAO Category = new CategoryDAO();

        try (Connection One = this.connect();
             PreparedStatement pstmt  = One.prepareStatement(sql)){

            // set the value
            pstmt.setInt(1,pk);
            //
            ResultSet rs  = pstmt.executeQuery();
            // loop through the result set
            while (rs.next()) {
                orderitem = new OrderItem(
                        rs.getInt("ID"),
                        rs.getInt("Order_ID"),
                        Category.getById(rs.getInt("Category_ID"))
                );
            }
            closeConnection(One);
            return orderitem;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return orderitem;
    }

    @Override
    public List<OrderItem> getAll() {
        String sql = "SELECT * FROM Order_Item";
        List<OrderItem> orderitemlist = new ArrayList<>();
        CategoryDAO category = new CategoryDAO();

        try (Connection ALL = this.connect();
             Statement stmt  = ALL.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {
                orderitemlist.add(new OrderItem(
                        rs.getInt("ID"),
                        rs.getInt("Order_ID"),
                        category.getById(rs.getInt("Category_ID")))
                );

            }
            closeConnection(ALL);
            return orderitemlist;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return orderitemlist;
    }

    public List<OrderItem> getOrderItemsByOrderId(int orderID) {
        String sql = "SELECT * FROM Order_Item WHERE Order_ID = ?";

        List<OrderItem> orderItemList = new ArrayList<>();
        CategoryDAO categoryDAO = new CategoryDAO();

        try (Connection One = this.connect();
             PreparedStatement pstmt  = One.prepareStatement(sql)){

            // set the value
            pstmt.setInt(1,orderID);
            //
            ResultSet rs  = pstmt.executeQuery();
            // loop through the result set
            while (rs.next()) {
                orderItemList.add( new OrderItem(
                        rs.getInt("ID"),
                        rs.getInt("Order_ID"),
                        categoryDAO.getById(rs.getInt("Category_ID"))
                ));
            }
            closeConnection(One);
            return orderItemList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return orderItemList;
    }

    public void insertAll(List<OrderItem> orderItems) {
        String sql = "INSERT INTO Order_Item (Order_ID, Category_ID) VALUES(?,?)";

        try (Connection New = this.connect(); PreparedStatement Pstmt = New.prepareStatement(sql)) {
            for (OrderItem orderItem :
                    orderItems) {
                Pstmt.setInt(1, orderItem.getOrderID());
                Pstmt.setInt(2, orderItem.getCategory().getId());


                Pstmt.executeUpdate();
            }

            closeConnection(New);
        }

        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
