package delivery.technicalServices.persistence;

import delivery.domain.Van;
import delivery.domain.VanDescription;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VanDescriptionDAO implements GenericDAO<VanDescription> {

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
    public void insert(VanDescription object) {
        String sql = "INSERT INTO Van_Description(Size_Label,Volume) VALUES(?,?)";

        try (Connection New = this.connect(); PreparedStatement Pstmt = New.prepareStatement(sql)) {

            Pstmt.setString(1, object.getSize_label());
            Pstmt.setDouble(2,object.getVolume());
            Pstmt.executeUpdate();

            closeConnection(New);
        }

        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void update(VanDescription object) {
        String sql = "UPDATE Van_Description SET Size_Label = ?, Volume = ? WHERE ID = ?";

        try (Connection New = this.connect(); PreparedStatement Pstmt = New.prepareStatement(sql)) {

            Pstmt.setString(1, object.getSize_label());
            Pstmt.setDouble(2,object.getVolume());
            Pstmt.executeUpdate();

            closeConnection(New);
        }

        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void delete(int dID) {
        String sql = "DELETE FROM Van_Description WHERE ID = ?";

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
    public VanDescription getById(int pk) {
        String sql = "SELECT * FROM Van_Description WHERE ID = ?";
        VanDescription vandescription = null;
        try (Connection One = this.connect();
             PreparedStatement pstmt  = One.prepareStatement(sql)){

            // set the value
            pstmt.setInt(1,pk);
            //
            ResultSet rs  = pstmt.executeQuery();

                vandescription = new VanDescription(
                        rs.getInt("ID"),
                        rs.getString("Size_Label"),
                        rs.getInt("Volume")
                );

            closeConnection(One);
            return vandescription;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return vandescription;
    }

    @Override
    public List<VanDescription> getAll() {
        String sql = "SELECT * FROM Van_Description";
        List<VanDescription> vanDescriptionList = new ArrayList<>();
        try (Connection ALL = this.connect();
             Statement stmt  = ALL.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {
                vanDescriptionList.add(new VanDescription(
                        rs.getInt("ID"),
                        rs.getString("Size_Label"),
                        rs.getInt("Volume"))
                );
            }
            closeConnection(ALL);
            return vanDescriptionList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return vanDescriptionList;
    }
}
