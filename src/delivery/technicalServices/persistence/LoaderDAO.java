package delivery.technicalServices.persistence;

import delivery.domain.Employee;
import delivery.domain.Loader;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoaderDAO extends EmployeeDAO implements GenericDAO<Loader> {

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
    public void insert(Loader object) {
        String sql = "INSERT INTO Loader(Name,PIN) VALUES(?,?)";

        insertEmp(object, sql);
    }

    @Override
    public void update(Loader object) {
        String sql = "UPDATE Loader SET Name = ?, PIN = ? WHERE ID = ?";

        updateEmp(object, sql);
    }

    @Override
    public void delete(int dID) {
        String sql = "DELETE FROM Loader WHERE ID = ?";

        deleteEmp(dID, sql);

    }

    @Override
    public Loader getById(int pk) {
        String sql = "SELECT ID, Name, PIN " + "FROM Loader WHERE ID = ?";
        Loader loader = (Loader) getByEmpId(pk, sql);
        return loader;
    }

    @Override
    public List<Loader> getAll() {
        String sql = "SELECT * FROM Loader";
        List<Loader> loaderList = (List<Loader>) (List<? extends Employee>) getAllEmp(sql);
        return loaderList;
    }

    public static void main(String[] args) {

    }

    public boolean validateLoader(int pinEntered) {
        String sql = "SELECT ID, Name, PIN FROM Loader WHERE PIN = ?";

        boolean doesExist = false;

        doesExist = validateEmployee(pinEntered, sql);

        return doesExist;
    }
}
