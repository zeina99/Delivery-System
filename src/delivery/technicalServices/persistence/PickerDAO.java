package delivery.technicalServices.persistence;

import delivery.domain.Driver;
import delivery.domain.Employee;
import delivery.domain.Picker;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PickerDAO extends EmployeeDAO implements GenericDAO<Picker> {

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
    public void insert(Picker object) {
        String sql = "INSERT INTO PICKER(Name,PIN) VALUES(?,?)";

        insertEmp(object, sql);

    }

    @Override
    public void update(Picker object) {
        String sql = "UPDATE PICKER SET Name = ?, PIN = ? WHERE ID = ?";

        updateEmp(object, sql);
    }

    @Override
    public void delete(int dID) {
        String sql = "DELETE FROM PICKER WHERE ID = ?";

       deleteEmp(dID, sql);

    }

    @Override
    public Picker getById(int pk) {
        String sql = "SELECT ID, Name, PIN FROM PICKER WHERE ID = ?";
        Picker picker = (Picker) getByEmpId(pk, sql);


        return picker;
    }

    @Override
    public List<Picker> getAll() {
        String sql = "SELECT * FROM PICKER";
        List<Picker> PickerList = (List<Picker>) (List<? extends Employee>) getAllEmp(sql);
        return PickerList;
    }

    public static void main(String[] args) {
        PickerDAO picker = new PickerDAO();
        System.out.println("\n");
        picker.getAll();
        System.out.println("______________________________________");
        picker.getById(4);
        System.out.println("______________________________________");
        //Picker New = new Picker("Gustav",27579);
        // picker.insert(New);
        //System.out.println("Added a row to the database.");
//        System.out.println("______________________________________");
//        picker.delete(8);
//        System.out.println("Deleted a row form the database.");
//        System.out.println("______________________________________");
//        picker.update(11,"Josh", 21555);
//        System.out.println("Updated a row in the database");
//        System.out.println("______________________________________");

    }

    public boolean validatePicker(int pinEntered) {
        String sql = "SELECT ID, Name, PIN FROM PICKER WHERE PIN = ?";

        boolean doesExist = false;

        doesExist = validateEmployee(pinEntered, sql);

        return doesExist;
    }
}