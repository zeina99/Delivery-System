package delivery.domain;

import delivery.technicalServices.persistence.*;

import java.util.*;

public class Inventory {


    //  remove fetch functions??? and make DAO calls in each get method? or leave as is since all calls will be done once program is started.
    // but this might cause memory issues, incase of large data -- dont want to keep the whole database in memory!!!!
    public Inventory() {

    }

    // fetching from DAO classes

    public List<Van> getAllVans() {

        VanDAO vanDAO = new VanDAO();

        return vanDAO.getAll();
    }

    public List<Category> getAllCategories() {

        CategoryDAO categoryDAO = new CategoryDAO();

        return categoryDAO.getAll();
    }

    public List<VanDescription> getVanDescriptions() {

        VanDescriptionDAO vanDescriptionDAO = new VanDescriptionDAO();

        return vanDescriptionDAO.getAll();
    }

    public List<Customer> getAllCustomers() {

        CustomerDAO customerDAO = new CustomerDAO();
        return customerDAO.getAll();
    }

    public List<Driver> getAllDrivers() {

        DriverDAO driverDAO = new DriverDAO();

        return driverDAO.getAll();
    }

    public List<Picker> getAllPickers() {
        PickerDAO pickerDAO = new PickerDAO();

        return pickerDAO.getAll();
    }

    public List<Loader> getAllLoaders() {
        LoaderDAO loaderDAO = new LoaderDAO();

        return loaderDAO.getAll();
    }

    public List<Manager> getAllManagers() {
        ManagerDAO managerDAO = new ManagerDAO();

        return managerDAO.getAll();
    }

    private List<Order> getAllOrders() {
        OrderDAO orderDAO = new OrderDAO();

        return orderDAO.getAll();
    }

    public List<OrderItem> getAllOrderItems() {
        OrderItemDAO orderItemDAO = new OrderItemDAO();

        return orderItemDAO.getAll();
    }

    public List<BoxDescription> getAllBoxDescriptions() {

        BoxDescriptionDAO boxDescriptionDAO = new BoxDescriptionDAO();

        return boxDescriptionDAO.getAll();

    }

    public Map<TimeSlots, List<Order>> getAllOrdersMap() {
        Map<TimeSlots, List<Order>> orderMap = new TreeMap<>();

        // populate the map with timeslots and empty lists
        for (TimeSlots timeslot :
                TimeSlots.values()) {
            orderMap.put(timeslot, new ArrayList<>());
        }

        // populate the treemap with orders
        for (Order order :
                this.getAllOrders()) {
            switch (order.getTimeSlot()) {
                case NINE_TWELVE:
                    orderMap.get(TimeSlots.NINE_TWELVE).add(order);
                    break;
                case TWELVE_THREE:
                    orderMap.get(TimeSlots.TWELVE_THREE).add(order);
                    break;
                case THREE_SIX:
                    orderMap.get(TimeSlots.THREE_SIX).add(order);
                    break;
                case SIX_NINE:
                    orderMap.get(TimeSlots.SIX_NINE).add(order);
                    break;

            }

        }
        return orderMap;
    }

    // public methods for getting random selection from list
    public Category getRandomCategory() {

        List<Category> categoryList = this.getAllCategories();

        // pick a random number ranging from 1 to category length
        int categoryLength = categoryList.size();
        Random random = new Random();

        // return category with randomly chosen index
        int chosenCategory = random.nextInt(categoryLength);
        return categoryList.get(chosenCategory);
    }

    public Customer getRandomCustomer() {
        List<Customer> customerList = getAllCustomers();
        // pick a random number ranging from 1 to customer length
        int customerListLength = customerList.size();
        Random random = new Random();
        int ranCustomerIndex = random.nextInt(customerListLength);

        // return customer with randomly chosen index
        return customerList.get(ranCustomerIndex);
    }

    public Double getLargestSizeBox() {

        // returns box with largest volume

        List<BoxDescription> boxDescriptionList = this.getAllBoxDescriptions();

        List<Double> volumes = new ArrayList<>();
        for (BoxDescription box :
                boxDescriptionList) {
            volumes.add(box.getVolume());
        }
        return Collections.max(volumes);
    }

    // return a sorted list by volume, descending order
    // TODO: make this return BoxDesc objects sorted
    public List<Double> getAllBoxVolumesSorted() {
        List<BoxDescription> boxDescriptionList = this.getAllBoxDescriptions();

        List<Double> boxVolumes = new ArrayList<>();

        for (BoxDescription boxdesc :
                boxDescriptionList) {
            boxVolumes.add(boxdesc.getVolume());
        }
        Collections.sort(boxVolumes);
        Collections.reverse(boxVolumes);
        return boxVolumes;
    }

    // get boxdescription object from volume
    public BoxDescription getBoxDescriptionFromVolume(double boxVolToUse) {

        BoxDescriptionDAO boxDescriptionDAO = new BoxDescriptionDAO();
        return boxDescriptionDAO.getBoxDescriptionFromVol(boxVolToUse);
    }


    public List<Van> getAllVansSortedByVolume() {
        List<Van> vanList = getAllVans();

        Collections.sort(vanList);
        Collections.reverse(vanList);

        return vanList;
    }

    public Order getOrderByID(int orderID) {
        OrderDAO orderDAO = new OrderDAO();
        return orderDAO.getById(orderID);
    }

    public void setOrderDeliveryFee(int id, double deliveryFee) {
        OrderDAO orderDAO = new OrderDAO();
        orderDAO.setDeliveryFeeByID(id, deliveryFee);
    }

    public Driver getDriverById(int driver_id) {
        DriverDAO driverDAO = new DriverDAO();
        return driverDAO.getById(driver_id);
    }

    public void manageEmployee(String actionToDo, Employee employee) {


        boolean isInstanceOfDriver = employee instanceof Driver;
        boolean isInstanceOfPicker = employee instanceof Picker;
        boolean isInstanceOfLoader = employee instanceof Loader;
        boolean isInstanceOfManager = employee instanceof Manager;

        DriverDAO driverDAO = new DriverDAO();
        LoaderDAO loaderDAO = new LoaderDAO();
        PickerDAO pickerDAO = new PickerDAO();
        ManagerDAO managerDAO = new ManagerDAO();


        switch (actionToDo) {

            case "insert":
                if (isInstanceOfDriver) {
                    driverDAO.insert((Driver) employee);
                } else if (isInstanceOfLoader) {
                    loaderDAO.insert((Loader) employee);
                } else if (isInstanceOfPicker) {
                    pickerDAO.insert((Picker) employee);
                } else if (isInstanceOfManager) {
                    managerDAO.insert((Manager) employee);
                }
                break;

            case "update":
                if (isInstanceOfDriver) {
                    driverDAO.update((Driver) employee);
                } else if (isInstanceOfLoader) {
                    loaderDAO.update((Loader) employee);
                } else if (isInstanceOfPicker) {
                    pickerDAO.update((Picker) employee);
                } else if (isInstanceOfManager) {
                    managerDAO.update((Manager) employee);
                }
                break;

            case "delete":
                if (isInstanceOfDriver) {
                    driverDAO.delete(employee.getId());
                } else if (isInstanceOfLoader) {
                    loaderDAO.delete(employee.getId());
                } else if (isInstanceOfPicker) {
                    pickerDAO.delete(employee.getId());
                } else {
                    managerDAO.delete(employee.getId());
                }
                break;

        }

    }


    public boolean validateEmployee(String employeeType, int pinEntered) {
        boolean isValid = false;

        switch (employeeType){
            case "Driver":
                DriverDAO driverDAO = new DriverDAO();
                isValid = driverDAO.validateDriver(pinEntered);
                break;
            case "Loader":
                LoaderDAO loaderDAO = new LoaderDAO();
                isValid = loaderDAO.validateLoader(pinEntered);
                break;

            case "Picker":
                PickerDAO pickerDAO = new PickerDAO();
                isValid = pickerDAO.validatePicker(pinEntered);
                break;

            case "Manager":
                ManagerDAO managerDAO = new ManagerDAO();
                isValid = managerDAO.validateManager(pinEntered);
                break;

        }
        return isValid;
    }
}
