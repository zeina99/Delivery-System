package delivery.technicalServices.persistence;

import delivery.domain.Driver;
import delivery.domain.Employee;
import delivery.domain.Picker;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PickerDAO extends EmployeeDAO implements GenericDAO<Picker> {



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


    public boolean validatePicker(int pinEntered) {
        String sql = "SELECT ID, Name, PIN FROM PICKER WHERE PIN = ?";

        boolean doesExist = false;

        doesExist = validateEmployee(pinEntered, sql);

        return doesExist;
    }
}