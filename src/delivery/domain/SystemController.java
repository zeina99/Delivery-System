package delivery.domain;

import delivery.technicalServices.persistence.DriverDAO;
import delivery.technicalServices.persistence.LoaderDAO;
import delivery.technicalServices.persistence.ManagerDAO;
import delivery.technicalServices.persistence.PickerDAO;

import javax.swing.*;
import java.util.List;

public class SystemController {
    Inventory inventory;

    public SystemController() {
        this.inventory = new Inventory();
    }

    public String getBoxContentReport() {
        return "Reports/Box-Content.txt";
    }

    public String getRevenueReport() {
        return "Reports/Revenue.txt";
    }

    public String getUnaccomodatedOrdersReport() {
        return "Reports/Unaccomodated Orders.txt";
    }

    public String getVanScheduleReport() {
        return "Reports/Van Schedule.txt";
    }

    public String getVanLoadingReport() {
        return "Reports/Van-Loading.txt";
    }

    public void manageEmployee(String actionToDo, Employee employee) {
        inventory.manageEmployee(actionToDo, employee);
    }

    public List<Picker> getAllPickers() {
        return inventory.getAllPickers();
    }

    public List<Loader> getAllLoaders() {
        return inventory.getAllLoaders();
    }

    public List<Manager> getAllManagers() {
        return inventory.getAllManagers();
    }

    public List<Driver> getAllDrivers() {
        return inventory.getAllDrivers();
    }

    public boolean validateUser(String employeeType, int pinEntered) {

        boolean isValid = inventory.validateEmployee(employeeType, pinEntered);

        return isValid;
    }


    public void deleteEmp(int id, String employeeType){
        inventory.deleteEmployee(id, employeeType);
    }
    public void manageEmp(String actionToDo, Employee employee) {
        inventory.manageEmployee(actionToDo, employee);

    }
}

