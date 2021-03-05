package delivery.technicalServices.persistence;

import delivery.domain.Driver;
import delivery.domain.Employee;
import delivery.domain.Loader;
import delivery.domain.Manager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ManagerDAO extends EmployeeDAO implements GenericDAO<Manager> {

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
    public void insert(Manager object) {
        String sql = "INSERT INTO Manager(Name,PIN) VALUES(?,?)";

        insertEmp(object, sql);
    }

    @Override
    public void update(Manager object) {
        String sql = "UPDATE Manager SET Name = ?, PIN = ? WHERE ID = ?";

        updateEmp(object, sql);
    }

    @Override
    public void delete(int dID) {
        String sql = "DELETE FROM Manager WHERE ID = ?";

        deleteEmp(dID, sql);
    }

    @Override
    public Manager getById(int pk) {
        String sql = "SELECT ID, Name, PIN FROM Manager WHERE ID = ?";
        Manager manager = (Manager) getByEmpId(pk, sql);
        return manager;
    }

    @Override
    public List<Manager> getAll() {
        String sql = "SELECT * FROM Manager";
        List<Manager> managerList =(List<Manager>) (List<? extends Employee>) getAllEmp(sql);
        return managerList;
    }

    public static void main(String[] args) {
        ManagerDAO manager = new ManagerDAO();
        System.out.println("\n");
        manager.getAll();
        System.out.println("______________________________________");
        System.out.println(manager.getById(2));
        System.out.println("______________________________________");
//        Manager New = new Manager("Gustav",17579);
//        manager.insert(New);
        System.out.println("Added a row to the database.");
        System.out.println("______________________________________");
        manager.delete(8);
        System.out.println("Deleted a row form the database.");
        System.out.println("______________________________________");
        //manager.update(11,"Josh", 11555);
        System.out.println("Updated a row in the database");
        System.out.println("______________________________________");
    }

    public boolean validateManager(int pinEntered) {
        String sql = "SELECT ID, Name, PIN FROM Manager WHERE PIN = ?";

        boolean doesExist = false;

        doesExist = validateEmployee(pinEntered, sql);

        return doesExist;
    }
}
