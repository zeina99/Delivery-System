package delivery.technicalServices.persistence;


import delivery.domain.Driver;
import delivery.domain.Employee;
import java.util.List;

public class DriverDAO extends EmployeeDAO implements GenericDAO<Driver> {

    @Override
    public void insert(Driver driver) {
        String sql = "INSERT INTO Driver(Name,PIN) VALUES(?,?)";

        insertEmp(driver, sql);
    }

    @Override
    public void update(Driver object) {
        String sql = "UPDATE Driver SET Name = ?, PIN = ? WHERE ID = ?";

       updateEmp(object, sql);
    }

    @Override
    public void delete(int dID) {
        String sql = "DELETE FROM Driver WHERE ID = ?";

       deleteEmp(dID, sql);
    }


    @Override
    public Driver getById(int pk) {
        String sql = "SELECT ID, Name, PIN FROM Driver WHERE ID = ?";
        Driver driver = (Driver) getByEmpId(pk, sql);
        return driver;
    }

    @Override
    public List<Driver> getAll() {
        String sql = "SELECT * FROM Driver";
        //TODO: look into oracle docs link on brave regarding this
        List<Driver> DriverList = (List<Driver>)(List<? extends Employee>) getAllEmp(sql);

        return DriverList;
    }

    public boolean validateDriver(int pinEntered) {
        String sql = "SELECT ID, Name, PIN FROM Driver WHERE PIN = ?";

        boolean doesExist;

        doesExist = validateEmployee(pinEntered, sql);

        return doesExist;
    }
}



