package delivery.technicalServices.persistence;

import delivery.domain.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO extends ConnectionFactory implements GenericDAO<Category> {

//    private Connection connect() {
//        // SQLite connection string
//        String url = "jdbc:sqlite:/Users/zeinathabet/Downloads/DeliveryDB.db";
//        //jdbc:sqlite:C://Users/Lenovo/Desktop/Delivery/DeliveryDB.db
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
    public void insert(Category object) {
        String sql = "INSERT INTO Category(Category_Type,Volume) VALUES(?,?)";

        try (Connection New = this.connect(); PreparedStatement Pstmt = New.prepareStatement(sql)) {

            Pstmt.setString(1, object.getType());
            Pstmt.setDouble(2,object.getVolume());
            Pstmt.executeUpdate();

            closeConnection(New);
        }

        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

	@Override
	public void update(Category object) {
        String sql = "UPDATE Category SET Category_Type = ? , Volume = ? WHERE ID = ?";

        try (Connection New = this.connect(); PreparedStatement Pstmt = New.prepareStatement(sql)) {

            Pstmt.setString(1, object.getType());
            Pstmt.setDouble(2, object.getVolume());
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
        String sql = "DELETE FROM Category WHERE ID = ?";

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
    public Category getById(int pk) {
        String sql = "SELECT * FROM Category WHERE ID = ?";
        Category category = null;
        try (Connection One = this.connect();
             PreparedStatement pstmt  = One.prepareStatement(sql)){

            // set the value
            pstmt.setInt(1,pk);
            ResultSet rs  = pstmt.executeQuery();

            // loop through the result set
            while (rs.next()) {
                category = new Category(
                        rs.getInt("ID"),
                        rs.getString("Category_Type"),
                        rs.getDouble("Volume"));

            }
            closeConnection(One);
            return category;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return category;
    }

    @Override
    public List<Category> getAll() {
        String sql = "SELECT * FROM Category";
        List<Category> categoryList  = new ArrayList<>();
        try (Connection ALL = this.connect();
             Statement stmt  = ALL.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {
                categoryList.add(new Category(
                        rs.getInt("ID"),
                        rs.getString("Category_Type"),
                        rs.getDouble("Volume") )
                );
            }

            closeConnection(ALL);
            return categoryList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return categoryList;
    }

    public static void main(String[] args) {
        CategoryDAO cat = new CategoryDAO();
        System.out.println(cat.getAll());
    }

}
