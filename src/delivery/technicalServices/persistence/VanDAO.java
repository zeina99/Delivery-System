package delivery.technicalServices.persistence;

import delivery.domain.Van;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VanDAO extends ConnectionFactory implements GenericDAO<Van> {


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
    public void update(Van object) {
        String sql = "UPDATE VAN SET Van_Description_ID = ?, Driver_ID = ? WHERE ID = ?";
        try (Connection New = this.connect(); PreparedStatement Pstmt = New.prepareStatement(sql)) {

           Pstmt.setInt(1, object.getVanDescription().getId());
           Pstmt.setInt(2, object.getDriver_id());
           Pstmt.setInt(3, object.getId());

            closeConnection(New);
        }

        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
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
        Van van = null;
        VanDescriptionDAO VanDes = new VanDescriptionDAO();

        try (Connection One = this.connect();
             PreparedStatement pstmt  = One.prepareStatement(sql)){

            // set the value
            pstmt.setInt(1,pk);
            ResultSet rs  = pstmt.executeQuery();

            // loop through the result set
            while (rs.next()) {
                van = new Van(
                        rs.getInt("ID"),
                        VanDes.getById(rs.getInt("Van_Description_ID")),
                        rs.getInt("Driver_ID")
                );

            }
            closeConnection(One);
            return van;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return van;
    }

    @Override
    public List<Van> getAll() {
        String sql = "SELECT * FROM Van";
        List<Van> vanlist = new ArrayList<>();
        VanDescriptionDAO VanDes = new VanDescriptionDAO();

        try (Connection ALL = this.connect();
             Statement stmt  = ALL.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {
                vanlist.add(new Van(
                        rs.getInt("ID"),
                        VanDes.getById(rs.getInt("Van_Description_ID")),
                        rs.getInt("Driver_ID"))
                );

            }
            closeConnection(ALL);
            return vanlist;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return vanlist;
    }
}
