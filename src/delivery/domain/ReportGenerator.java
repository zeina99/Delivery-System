package delivery.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReportGenerator {
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

        int numOfVans = 6;

        // max orders per van is 9
        int timeslotMaxOrders = 9 * numOfVans;

        // box id
        int boxID = 1;

        // loop over all time slots
        for (TimeSlots timeslot:
                this.orderMap.keySet()) {

            List<Order> orders = orderMap.get(timeslot);

            // defer extra orders to unaccomodated list
            int timeslotOrders = orders.size();

            if (timeslotOrders > timeslotMaxOrders){
                int numOfExtraOrders = timeslotOrders - timeslotMaxOrders;


                for (int i = timeslotOrders ; i < timeslotOrders -numOfExtraOrders; i--) {
                    unaccommodatedOrders.add(orders.remove(i));
                }
            }

            // loop over array of orders for curr timeslot;
            for (Order order :
                    orders) {

                // get total volume of order
                double totalOrderVolume = 0;
                for (OrderItem item :
                        order.getItems()) {
                    totalOrderVolume += item.getCategory().getVolume();
                }
                // picking boxes for order
                //double largestSizeBox = this.inventory.getLargestSizeBox();

                List<Double> allBoxSizes = this.inventory.getAllBoxSizesSorted();
                double boxVolToUse;
                int allBoxSizesIndex = 0;

                // order items descending by item volume
                List<OrderItem> orderItemsSortedByVolume = order.getItemsSortedDesc();
                int orderItemsIndex = 0;

                // while loop to fill boxes with current order items
                while (totalOrderVolume > 0)
                {
                    //--- get a box just greater than total order volume in if statements ---

                    // if total order volume is less than current box size and greater than next box size, get curr box
                    if (totalOrderVolume < allBoxSizes.get(allBoxSizesIndex) && (totalOrderVolume > allBoxSizes.get(allBoxSizesIndex+1) ))
                    {
                        boxVolToUse = allBoxSizes.get(allBoxSizesIndex);
                        totalOrderVolume =- allBoxSizes.get(allBoxSizesIndex);

                        // start filling order items to box until box is full
                        while (boxVolToUse >= 0){

                            // creating and adding box to list
                            Box box = new Box(boxID, inventory.getBoxDescriptionFromVolume( boxVolToUse));
                            boxList.add(box);

                            // adding items to box
                            box.addItem(orderItemsSortedByVolume.get(orderItemsIndex));
                            //orderItemsSortedByVolume.remove(orderItemsIndex);
                            boxVolToUse =- orderItemsSortedByVolume.get(orderItemsIndex).getVolume();
                            orderItemsIndex++;
                        }

                        allBoxSizesIndex = 0;
                    }

                    // we're at the largest box size, use it
                    if (allBoxSizesIndex+1 == allBoxSizes.size())
                    {
                        boxVolToUse = allBoxSizes.get(allBoxSizesIndex);
                        totalOrderVolume =- allBoxSizes.get(allBoxSizesIndex);

                        // TODO: duplicate code
                        while (boxVolToUse >= 0){

                            // creating and adding box to list
                            Box box = new Box(boxID, inventory.getBoxDescriptionFromVolume( boxVolToUse));
                            boxList.add(box);

                            // adding items to box
                            box.addItem(orderItemsSortedByVolume.get(orderItemsIndex));
                            //orderItemsSortedByVolume.remove(orderItemsIndex);
                            boxVolToUse =- orderItemsSortedByVolume.get(orderItemsIndex).getVolume();
                            orderItemsIndex++;
                        }
                        allBoxSizesIndex = 0;
                    }

                    allBoxSizesIndex++;
                }


            }


        }
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
}
