package delivery.technicalServices.persistence;

import delivery.domain.BoxDescription;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BoxDescriptionDAO extends ConnectionFactory implements GenericDAO<BoxDescription> {

    @Override
    public void insert(BoxDescription object) {
        String sql = "INSERT INTO Box_Description(Size_Label,Volume,Rate) VALUES(?,?,?)";

        try (Connection New = this.connect(); PreparedStatement Pstmt = New.prepareStatement(sql)) {

            Pstmt.setString(1, object.getSizeLabel());
            Pstmt.setDouble(2, object.getVolume());
            Pstmt.setDouble(3, object.getBoxRate());
            Pstmt.executeUpdate();

            closeConnection(New);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(BoxDescription object) {

        String sql = "UPDATE Box_Description SET Size_Label = ? , Volume = ? , Rate = ? WHERE ID = ?";

        try (Connection New = this.connect(); PreparedStatement Pstmt = New.prepareStatement(sql)) {

            Pstmt.setString(1, object.getSizeLabel());
            Pstmt.setDouble(2, object.getVolume());
            Pstmt.setDouble(3, object.getBoxRate());
            Pstmt.setInt(4, object.getId());


            Pstmt.executeUpdate();
            closeConnection(New);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void delete(int dID) {
        String sql = "DELETE FROM Box_Description WHERE ID = ?";

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
    public BoxDescription getById(int pk) {
        String sql = "SELECT * FROM Box_Description WHERE ID = ?";
        BoxDescription boxDescription = null;

        try (Connection One = this.connect();
             PreparedStatement pstmt = One.prepareStatement(sql)) {

            // set the value
            pstmt.setInt(1, pk);
            //
            ResultSet rs = pstmt.executeQuery();

            // ---- There is an error here, but it's fine it will be fixed when merged
            boxDescription = new BoxDescription(
                    rs.getInt("ID"),
                    rs.getString("Size_Label"),
                    rs.getDouble("Volume"),
                    rs.getDouble("Rate")
            );

            closeConnection(One);

            return boxDescription;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return boxDescription;
    }

    public BoxDescription getBoxDescriptionFromVol(double volume) {
        String sql = "SELECT * FROM Box_Description WHERE Volume = ?";
        BoxDescription boxDescription = null;

        try (Connection One = this.connect();
             PreparedStatement pstmt = One.prepareStatement(sql)) {

            // set the value
            pstmt.setDouble(1, volume);

            ResultSet rs = pstmt.executeQuery();

            boxDescription = new BoxDescription(
                    rs.getInt("ID"),
                    rs.getString("Size_Label"),
                    rs.getDouble("Volume"),
                    rs.getDouble("Rate")
            );

            closeConnection(One);

            return boxDescription;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return boxDescription;

    }

    @Override
    public List<BoxDescription> getAll() {
        String sql = "SELECT * FROM Box_Description";
        List<BoxDescription> boxDescriptionList = new ArrayList<>();

        try (Connection ALL = this.connect();
             Statement stmt = ALL.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // loop through the result set
            while (rs.next()) {
                // the error here is alright, will be fixed when merged with mine
                boxDescriptionList.add(new BoxDescription(
                                rs.getInt("ID"),
                                rs.getString("Size_Label"),
                                rs.getDouble("Volume"),
                        rs.getDouble("Rate"))
                );

            }
            closeConnection(ALL);

            return boxDescriptionList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return boxDescriptionList;
    }

}
