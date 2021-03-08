package delivery.technicalServices.persistence;

import delivery.domain.Driver;
import delivery.domain.Employee;
import delivery.domain.Loader;
import delivery.domain.Manager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ManagerDAO extends ConnectionFactory implements GenericDAO<Manager> {

    @Override
    public void insert(Manager object) {
        String sql = "INSERT INTO Manager(Name,PIN) VALUES(?,?)";

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
    public void update(Manager object) {
        String sql = "UPDATE Manager SET Name = ?, PIN = ? WHERE ID = ?";

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
        String sql = "DELETE FROM Manager WHERE ID = ?";

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
    public Manager getById(int pk) {
        String sql = "SELECT ID, Name, PIN FROM Manager WHERE ID = ?";
        Manager manager = null;

        try (Connection One = this.connect();
             PreparedStatement pstmt  = One.prepareStatement(sql)){

            // set the value
            pstmt.setInt(1,pk);
            //
            ResultSet rs  = pstmt.executeQuery();

            // loop through the result set
            while (rs.next()) {
                manager = new Manager(
                        rs.getInt("ID"),
                        rs.getString("Name"),
                        rs.getInt("PIN")
                );
            }
            closeConnection(One);
            return manager;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return manager;
    }

    @Override
    public List<Manager> getAll() {
        String sql = "SELECT * FROM Manager";
        List<Manager> managerList = new ArrayList<>();

        try (Connection ALL = this.connect();
             Statement stmt  = ALL.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {
                managerList.add(new Manager(
                        rs.getInt("ID"),
                        rs.getString("Name"),
                        rs.getInt("PIN"))
                );
            }
            closeConnection(ALL);
            return managerList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return managerList;
    }

    public boolean validateManager(int pinEntered) {
        String sql = "SELECT ID, Name, PIN FROM Manager WHERE PIN = ?";

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
