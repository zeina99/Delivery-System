package delivery.domain;

import delivery.technicalServices.persistence.*;

import java.util.*;

public class Inventory {

    private List<Van> allVans;
    private List<VanDescription> vanDescriptionList;
    private List<Customer> customerList;

    private List<BoxDescription> boxDescriptionList;
    private List<Category> categoryList;

    // employees
    private List<Loader> loaderList;
    private List<Driver> driverList;
    private List<Picker> pickerList;
    private List<Manager> managerList;

    // orders and orderitems
    private Map<TimeSlots, List<Order>> orders;
    private List<OrderItem> orderItems;


    public Inventory() {

        // vans
        this.allVans = fetchAllVans();
        this.vanDescriptionList = fetchVanDescriptions();

        // box description
        this.boxDescriptionList = fetchAllBoxDescriptions();

        // orders, categories, customers
        this.orders = fetchAllOrdersMap();
        this.orderItems = fetchAllOrderItems();
        this.categoryList = fetchAllCategories();
        this.customerList = fetchAllCustomers();

        // employees
        this.loaderList = fetchAllLoaders();
        this.managerList = fetchAllManagers();
        this.pickerList = fetchAllPickers();
        this.driverList = fetchAllDrivers();
    }

    // fetching from DAO classes

    private List<Van> fetchAllVans() {

        VanDAO vanDAO = new VanDAO();

        return vanDAO.getAll();
    }

    private List<Category> fetchAllCategories() {

        CategoryDAO categoryDAO = new CategoryDAO();

        return categoryDAO.getAll();
    }

    private List<VanDescription> fetchVanDescriptions() {

        VanDescriptionDAO vanDescriptionDAO = new VanDescriptionDAO();

        return vanDescriptionDAO.getAll();
    }

    private List<Customer> fetchAllCustomers() {

        CustomerDAO customerDAO = new CustomerDAO();
        return customerDAO.getAll();
    }

    private List<Driver> fetchAllDrivers() {

        DriverDAO driverDAO = new DriverDAO();

        return driverDAO.getAll();
    }

    private List<Picker> fetchAllPickers() {
        PickerDAO pickerDAO = new PickerDAO();

        return pickerDAO.getAll();
    }

    private List<Loader> fetchAllLoaders() {
        LoaderDAO loaderDAO = new LoaderDAO();

        return loaderDAO.getAll();
    }

    private List<Manager> fetchAllManagers() {
        ManagerDAO managerDAO = new ManagerDAO();

        return managerDAO.getAll();
    }

    private List<Order> fetchAllOrders() {
        OrderDAO orderDAO = new OrderDAO();

        return orderDAO.getAll();
    }

    private List<OrderItem> fetchAllOrderItems() {
        OrderItemDAO orderItemDAO = new OrderItemDAO();

        return orderItemDAO.getAll();
    }

    private List<BoxDescription> fetchAllBoxDescriptions() {

        BoxDescriptionDAO boxDescriptionDAO = new BoxDescriptionDAO();

        return boxDescriptionDAO.getAll();

    }

    private Map<TimeSlots, List<Order>> fetchAllOrdersMap() {
        Map<TimeSlots, List<Order>> orderMap = new TreeMap<>();

        // populate the map with timeslots and empty lists
        for (TimeSlots timeslot :
                TimeSlots.values()) {
            orderMap.put(timeslot, new ArrayList<>());
        }

        // populate the treemap with orders
        // TODO: check that treemap is ordered correctly by timeslots
        for (Order order :
                this.fetchAllOrders()) {
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

        // pick a random number ranging from 1 to category length
        int categoryLength = this.categoryList.size();
        Random random = new Random();

        // return category with randomly chosen index
        int chosenCategory = random.nextInt(categoryLength);
        return categoryList.get(chosenCategory);
    }

    public Customer getRandomCustomer() {

        // pick a random number ranging from 1 to customer length
        int customerListLength = this.customerList.size();
        Random random = new Random();
        int ranCustomerIndex = random.nextInt(customerListLength);

        // return customer with randomly chosen index
        return this.customerList.get(ranCustomerIndex);
    }

    public Double getLargestSizeBox() {

        // returns box with largest volume

        List<BoxDescription> boxDescriptionList = this.getBoxDescriptionList();

        List<Double> volumes = new ArrayList<>();
        for (BoxDescription box :
                boxDescriptionList) {
            volumes.add(box.getVolume());
        }
        return Collections.max(volumes);
    }

    // return a sorted list by volume, descending order
    public List<Double> getAllBoxVolumesSorted() {
        List<Double> boxVolumes = new ArrayList<>();

        for (BoxDescription boxdesc :
                this.boxDescriptionList) {
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

    // getters
    public List<Customer> getCustomerList() {
        return customerList;
    }

    public List<BoxDescription> getBoxDescriptionList() {
        return boxDescriptionList;
    }

    public List<VanDescription> getVanDescriptionList() {
        return vanDescriptionList;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public Map<TimeSlots, List<Order>> getOrders() {
        return orders;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }


    public List<Loader> getLoaderList() {
        return loaderList;
    }

    public List<Driver> getDriverList() {
        return driverList;
    }

    public List<Picker> getPickerList() {
        return pickerList;
    }

    public List<Manager> getManagerList() {
        return managerList;
    }

    public List<Van> getAllVans() {
        return allVans;
    }


    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        System.out.println(inventory.fetchVanDescriptions());
    }
}
