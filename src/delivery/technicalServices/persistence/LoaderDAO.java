package delivery.technicalServices.persistence;

import delivery.domain.Employee;
import delivery.domain.Loader;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoaderDAO extends ConnectionFactory implements GenericDAO<Loader> {

    @Override
    public void insert(Loader object) {
        String sql = "INSERT INTO Loader(Name,PIN) VALUES(?,?)";

        try (Connection New = this.connect(); PreparedStatement Pstmt = New.prepareStatement(sql)) {

            Pstmt.setString(1, object.getName());
            Pstmt.setInt(2,object.getPin());
            Pstmt.executeUpdate();

            closeConnection(New);
        }

        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Loader object) {
        String sql = "UPDATE Loader SET Name = ?, PIN = ? WHERE ID = ?";

        try (Connection up = this.connect();
             PreparedStatement Pstmt = up.prepareStatement(sql)) {

            // set the corresponding param

            Pstmt.setString(1, object.getName());
            Pstmt.setInt(2, object.getPin());
            Pstmt.setInt(3, object.getId());
            // update
            Pstmt.executeUpdate();

            closeConnection(up);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(int dID) {
        String sql = "DELETE FROM Loader WHERE ID = ?";

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
    public Loader getById(int pk) {
        String sql = "SELECT ID, Name, PIN " +"FROM Loader WHERE ID = ?";
        Loader loader = null;
        try (Connection One = this.connect();
             PreparedStatement pstmt  = One.prepareStatement(sql)){

            // set the value
            pstmt.setInt(1,pk);
            //
            ResultSet rs  = pstmt.executeQuery();

            System.out.println("ID: \tName: \tPIN: ");
            // loop through the result set
            while (rs.next()) {
                loader = new Loader(
                        rs.getInt("ID"),
                        rs.getString("Name"),
                        rs.getInt("PIN"));

            }
            closeConnection(One);
            return loader;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return loader;
    }

    @Override
    public List<Loader> getAll() {
        String sql = "SELECT * FROM Loader";
        List<Loader> LoaderList  = new ArrayList<>();

        try (Connection ALL = this.connect();
             Statement stmt  = ALL.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {
                LoaderList.add(new Loader(
                        rs.getInt("ID"),
                        rs.getString("Name"),
                        rs.getInt("PIN"))
                );

            }
            closeConnection(ALL);
            return LoaderList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return LoaderList;
    }

    public static void main(String[] args) {

    }

    public boolean validateLoader(int pinEntered) {
        String sql = "SELECT ID, Name, PIN FROM Loader WHERE PIN = ?";

        boolean doesExist = false;
        try (Connection One = this.connect();
             PreparedStatement pstmt = One.prepareStatement(sql)) {

            // set the value
            pstmt.setInt(1, pinEntered);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next())
                doesExist = true;



            closeConnection(One);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return doesExist;
    }
}
