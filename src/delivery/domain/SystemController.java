package delivery.domain;

import java.util.List;

public class SystemController {
    Inventory inventory;

    public SystemController(Inventory inventory) {
        this.inventory = inventory;
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
}
