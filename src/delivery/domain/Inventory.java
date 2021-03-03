package delivery.domain;

import delivery.technicalServices.persistence.*;

import java.util.*;
import java.util.stream.Collectors;

public class Inventory {

//    private List<Van> allVans;
//    private List<VanDescription> vanDescriptionList;
//    private List<Customer> customerList;
//
//    private List<BoxDescription> boxDescriptionList;
//    private List<Category> categoryList;
//
//    // employees
//    private List<Loader> loaderList;
//    private List<Driver> driverList;
//    private List<Picker> pickerList;
//    private List<Manager> managerList;
//
//    // orders and orderitems
//    private Map<TimeSlots, List<Order>> orders;
//    private List<OrderItem> orderItems;

//  remove fetch functions??? and make DAO calls in each get method? or leave as is since all calls will be done once program is started.
    // but this might cause memory issues, incase of large data -- dont want to keep the whole database in memory!!!!
    public Inventory() {

        // vans
//        this.allVans = getAllVans();
//        this.vanDescriptionList = getVanDescriptions();
//
//        // box description
//        this.boxDescriptionList = getAllBoxDescriptions();
//
//        // orders, categories, customers
//        this.orders = getAllOrdersMap();
//        this.orderItems = getAllOrderItems();
//        this.categoryList = getAllCategories();
//        this.customerList = getAllCustomers();
//
//        // employees
//        this.loaderList = getAllLoaders();
//        this.managerList = getAllManagers();
//        this.pickerList = getAllPickers();
//        this.driverList = getAllDrivers();
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
}
