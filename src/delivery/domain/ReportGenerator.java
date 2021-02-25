package delivery.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReportGenerator {
    private final BoxContentReportGenerator boxContentReportGenerator = new BoxContentReportGenerator(this);
    private List<Customer> customerList;
    private Map<TimeSlots, List<Order>> orderMap;
    private List<Box> boxList;
    private List<BoxDescription> boxDescriptionList;
    private List<Van> vanList;
    private List<Order> unaccommodatedOrders;
    private Inventory inventory;

    public ReportGenerator() {

        this.customerList= new ArrayList<>();
        this.orderMap = inventory.getOrders();
        this.boxList = new ArrayList<>();
        this.vanList = new ArrayList<>();
        this.unaccommodatedOrders = new ArrayList<>();
        this.inventory = new Inventory();
        this.boxDescriptionList = this.inventory.getBoxDescriptions();
    }
    public void generateLoadingReport(){


    }
    public void generateBoxContentReport(){
        //TODO: refactor this into a class and methods
        //Map {
        // TimeSlot: [order, order, order, order]
        // TimeSlot: [order, order, order, order]
        // }

        // max orders per van is 9

        // box id

        // loop over all time slots
        List<Box> boxList = boxContentReportGenerator.generateBoxContentList();

    }
    public void generateRevenueReport(){

    }

    public void generateUnaccommodatedReport(){

    }
    public void generateVanScheduleReport(){

    }

    public static void main(String[] args) {
        ReportGenerator reportGenerator = new ReportGenerator();
        reportGenerator.generateBoxContentReport();
    }

    public List<Box> getBoxList() {
        return boxList;
    }

    public Map<TimeSlots, List<Order>> getOrderMap() {
        return orderMap;
    }

    public List<Order> getUnaccommodatedOrders() {
        return unaccommodatedOrders;
    }

    public Inventory getInventory() {
        return inventory;
    }
}
