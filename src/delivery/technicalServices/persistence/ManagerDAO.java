package delivery.technicalServices.persistence;

import delivery.domain.Driver;
import delivery.domain.Employee;
import delivery.domain.Loader;
import delivery.domain.Manager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ManagerDAO extends EmployeeDAO implements GenericDAO<Manager> {

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

    public boolean validateManager(int pinEntered) {
        String sql = "SELECT ID, Name, PIN FROM Manager WHERE PIN = ?";

        boolean doesExist = false;

        doesExist = validateEmployee(pinEntered, sql);

        return doesExist;
    }
}
