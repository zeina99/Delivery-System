package delivery.technicalServices.persistence;

import delivery.domain.Driver;
import org.w3c.dom.ls.LSOutput;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DriverDAO implements GenericDAO<Driver> {

    @Override
    public void insert(Driver object) {

    }

    @Override
    public void update(Driver object) {

    }

    @Override
    public void delete(Driver object) {

    }

    @Override
    public Driver getById(int pk) {
        Driver did = null;
        String sql = "SELECT ID FROM Driver";
        try (Connection ID = Connect.cnct();

             Statement stmt  = ID.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            did.setId(rs.getInt("ID"));
            did.setName(rs.getString("Name"));
            did.setPin(rs.getInt("PIN"));


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return did;
    }

    @Override
    public List<Driver> getAll() {

       String sql = "SELECT * FROM Driver";
       List<Driver> driverslist = new ArrayList<>();


        try (Connection ALL = Connect.cnct();
             Statement stmt  = ALL.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            System.out.println(rs);
            // loop through the result set
            while (rs.next()) {
                Driver driver = new Driver(rs.getInt("ID"), rs.getString("Name"), rs.getInt("PIN"));
                System.out.println(rs.getInt("ID"));
                System.out.println(rs.getString("Name"));
                System.out.println(rs.getInt("PIN"));

                //System.out.println();
                driverslist.add(driver);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return driverslist;
    }


    public static void main(String[] args) {

        DriverDAO tst = new DriverDAO();
        //tst.getAll();
        System.out.println(tst.getAll());



    }
}
