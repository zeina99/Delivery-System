package delivery.domain;

import java.util.ArrayList;
import java.util.List;

public class BoxContentReportGenerator {
    private final ReportGenerator reportGenerator;

    public BoxContentReportGenerator(ReportGenerator reportGenerator) {
        this.reportGenerator = reportGenerator;
    }

    public List<Box> generateBoxContentList() {
        List<Box> boxList = new ArrayList<>();
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
        for (TimeSlots timeslot :
                reportGenerator.getOrderMap().keySet()) {

            List<Order> orders = reportGenerator.getOrderMap().get(timeslot);

            // defer extra orders to unaccomodated list
            deferExtraOrders(timeslotMaxOrders, orders);

            // loop over array of orders for curr timeslot
            // here we try to fill boxes with order items for each order
            boxID = fillOrderItemsInBox(boxID, orders, boxList);


        }
        return boxList;
    }

    private int fillOrderItemsInBox(int boxID, List<Order> orders, List<Box> boxList) {
        for (Order order :
                orders) {

            // getting total volume of order
            double totalOrderVolume = getTotalOrderVolume(order);

            // picking boxes for order
            // allBoxSizes Sorted descending , starting with largest box size
            List<Double> allBoxSizes = reportGenerator.getInventory().getAllBoxSizesSorted();
            double boxVolToUse;
            int allBoxSizesIndex = 0;

            // order items descending by item volume
            List<OrderItem> orderItemsSortedByVolume = order.getItemsSortedDesc();
            int orderItemsIndex = 0;

            // while loop to fill boxes with current order items
            boxID = fillBoxes(boxID, boxList, totalOrderVolume, allBoxSizes, allBoxSizesIndex, orderItemsSortedByVolume, orderItemsIndex);


        }
        return boxID;
    }

    private int fillBoxes(int boxID, List<Box> boxList, double totalOrderVolume, List<Double> allBoxSizes, int allBoxSizesIndex, List<OrderItem> orderItemsSortedByVolume, int orderItemsIndex) {
        double boxVolRemaining;
        while (totalOrderVolume > 0) {
            //--- get a box just greater than total order volume in if statements ---

            // if total order volume is less than current box size and greater than next box size, get curr box
            if (totalOrderVolume < allBoxSizes.get(allBoxSizesIndex) && (totalOrderVolume > allBoxSizes.get(allBoxSizesIndex + 1)))
            {
                boxVolRemaining = allBoxSizes.get(allBoxSizesIndex);
                totalOrderVolume =- allBoxSizes.get(allBoxSizesIndex);

                // start filling order items to box until box is full
                while (boxVolRemaining > 0) {

                    // creating and adding box to list
                    Box box = new Box(boxID, reportGenerator.getInventory().getBoxDescriptionFromVolume(boxVolRemaining));
                    boxList.add(box);
                    boxID++;
                    // adding items to box
                    box.addItem(orderItemsSortedByVolume.get(orderItemsIndex));
                    //orderItemsSortedByVolume.remove(orderItemsIndex);
                    boxVolRemaining =- orderItemsSortedByVolume.get(orderItemsIndex).getVolume();
                    orderItemsIndex++;
                }

                allBoxSizesIndex = 0;
            }

            // we're at the largest box size, use it
            if (allBoxSizesIndex + 1 == allBoxSizes.size()) {
                boxVolRemaining = allBoxSizes.get(allBoxSizesIndex);
                totalOrderVolume = -allBoxSizes.get(allBoxSizesIndex);

                // TODO: duplicate code
                while (boxVolRemaining > 0) {

                    // creating and adding box to list
                    Box box = new Box(boxID, reportGenerator.getInventory().getBoxDescriptionFromVolume(boxVolRemaining));
                    boxList.add(box);
                    boxID++;
                    // adding items to box
                    box.addItem(orderItemsSortedByVolume.get(orderItemsIndex));
                    //orderItemsSortedByVolume.remove(orderItemsIndex);
                    boxVolRemaining =- orderItemsSortedByVolume.get(orderItemsIndex).getVolume();
                    orderItemsIndex++;
                }
                allBoxSizesIndex = 0;
            }

            allBoxSizesIndex++;
        }
        return boxID;
    }

    private double getTotalOrderVolume(Order order) {
        // get total volume of order
        double totalOrderVolume = 0;
        for (OrderItem item :
                order.getItems()) {
            totalOrderVolume += item.getCategory().getVolume();
        }
        return totalOrderVolume;
    }

    private void deferExtraOrders(int timeslotMaxOrders, List<Order> orders) {
        int timeslotOrders = orders.size();

        if (timeslotOrders > timeslotMaxOrders) {
            int numOfExtraOrders = timeslotOrders - timeslotMaxOrders;


            for (int i = timeslotOrders; i < timeslotOrders - numOfExtraOrders; i--) {
                reportGenerator.getUnaccommodatedOrders().add(orders.remove(i));
            }
        }
    }
}