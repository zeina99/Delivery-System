package delivery.domain;

import java.util.*;

public class Inventory {
    // TODO: implement methods
    private List<Van> allVans;
    private List<Customer> allCustomers;

    private List<BoxDescription> boxDescriptions;
    private List<Category> categoryList;

    // employees
    private List<Loader> loaderList;
    private List<Driver> driverList;
    private List<Picker> prickerList;
    private List<Manager> managerList;
    // orders and orderitems
    private Map<TimeSlots, List<Order>> orders;
    private List<OrderItem> orderItems;

    OrderGenerator generator = new OrderGenerator();

    public Inventory() {
        generator.generateOrders();

        this.allVans = fetchAllVans();
        this.boxDescriptions = fetchAllBoxDescriptions();
        this.orders = fetchAllOrders(generator.getOrders());
    }

    public List<Van> fetchAllVans(){
        // TODO

    }
    private List<Category> fetchAllCategories(){


    }

    public Map<TimeSlots, List<Order>> getOrders() {
        return orders;
    }

    public Category fetchRandomCategory(){
        // get all categories

        // pick a random category

        // return the random category


    }
    private List<BoxDescription> fetchAllBoxDescriptions(){


    }





    public Customer fetchRandomCustomer() {
        // TODO: change this later to get a random customer
        Customer cus = new Customer(1, "Jane");
        return cus;
    }
    private Map<TimeSlots, List<Order>> fetchAllOrders(List<Order> orders){
        Map<TimeSlots, List<Order>> orderMap = new TreeMap<>();

        // populate the map with timeslots and empty lists
        for (TimeSlots timeslot :
                TimeSlots.values()) {
            orderMap.put(timeslot, new ArrayList<Order>());
        }

        // populate the treemap with orders
        // TODO: check that treemap is ordered correctly by timeslots
        for (Order order :
                orders) {
            switch (order.getTimeSlot()){
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
    // getters
    public List<Customer> getAllCustomers() {
        return allCustomers;
    }

    public List<BoxDescription> getBoxDescriptions() {
        return boxDescriptions;
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
        return prickerList;
    }

    public List<Manager> getManagerList() {
        return managerList;
    }

    public List<Van> getAllVans() {
        return allVans;
    }

    public Double getLargestSizeBox(){

        // returns box with largest volume

        List<BoxDescription> boxDescriptionList = this.getBoxDescriptions();
        List<Double> volumes = new ArrayList<>();
        for (BoxDescription box :
                boxDescriptionList) {
            volumes.add(box.getVolume());
        }
        return Collections.max(volumes);
    }

    public List<Double> getAllBoxSizesSorted() {
        // TODO: make it return a sorted list
        List<Double> boxVolumes =new ArrayList<>();

        for (BoxDescription boxdesc :
                this.boxDescriptions) {
            boxVolumes.add(boxdesc.getVolume());
        }

        return boxVolumes;
    }

    public BoxDescription getBoxDescriptionFromVolume(double boxVolToUse) {

    }
}
