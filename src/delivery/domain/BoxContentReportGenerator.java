package delivery.domain;

import java.util.ArrayList;
import java.util.List;

public class BoxContentReportGenerator {

    private final ReportGenerator reportGenerator;
    private Inventory inventory = new Inventory();
    // box id
    private int boxID;
    private List<Box> boxList;
    private final List<Double> boxVolumes;
    private int boxVolumesIndex;
    private int orderItemsIndex;
    private double totalOrderVolume;


    public BoxContentReportGenerator(ReportGenerator reportGenerator) {

        this.reportGenerator = reportGenerator;
        this.boxID = 1;
        this.boxList = new ArrayList<>();

        // allBoxSizes Sorted descending , starting with largest box size
        this.boxVolumes = inventory.getAllBoxVolumesSorted();
        this.boxVolumesIndex = 0;
        this.orderItemsIndex = 0;

    }

    public List<Box> generateBoxContentList() {

        //TODO: refactor this into a class and methods
        //Map {
        // TimeSlot: [order, order, order, order]
        // TimeSlot: [order, order, order, order]
        // }

        int numOfVans = 6;

        // max orders per van is 9
        int timeslotMaxOrders = 9 * numOfVans;


        // loop over all time slots
        for (TimeSlots timeslot :
                reportGenerator.getOrderMap().keySet()) {

            // get orders for current timeslot
            List<Order> timeslotOrders = inventory.getAllOrdersMap().get(timeslot);

            // defer extra orders to unaccomodated list
            deferExtraOrders(timeslotMaxOrders, timeslotOrders);

            // loop over array of orders for curr timeslot
            // here we try to fill boxes with order items for each order
            fillOrderItemsInBox(timeslotOrders);


        }
        return boxList;
    }

    private void fillOrderItemsInBox(List<Order> timeslotOrders) {

        // looping over each order
        for (Order order :
                timeslotOrders) {


            // order items descending by item volume
            List<OrderItem> orderItemsSortedByVolume = order.getItemsSortedDesc();

            totalOrderVolume = getTotalOrderVolume(order);
            // fills order items in boxes
            fillAllOrderItems(orderItemsSortedByVolume);


        }
    }


    private void fillAllOrderItems(List<OrderItem> orderItemsSortedByVolume) {

        // refactor this method further,
        // method to return the suitable box volume
        // method to fill the items in the box
        // getting total volume of order


        orderItemsIndex = 0;


        while (totalOrderVolume > 0) {
            // -- picking a box


            //--- get a box just greater than total order volume in if statements ---

            // if the next smaller box is smaller than current total order volume, continue
            // else, pick the current one
            // if we are at the smallest box, use it


            // pick a suitable size box
            Double boxVolRemaining = getBoxVol(totalOrderVolume);


            // fills a box with items
            fillBoxWithItems(boxVolRemaining, orderItemsSortedByVolume);


            this.boxVolumesIndex = 0;
        }

    }

    private Double getBoxVol(double totalOrderVolume) {

        if (this.boxVolumesIndex + 1 == this.boxVolumes.size()) {
            return this.boxVolumes.get(boxVolumesIndex);
        } else if (totalOrderVolume < boxVolumes.get(boxVolumesIndex + 1)) {
            // avoid getting out of index
            while (boxVolumesIndex + 1 != boxVolumes.size() && totalOrderVolume < boxVolumes.get(boxVolumesIndex + 1)) {
                this.boxVolumesIndex++;
            }
            return this.boxVolumes.get(boxVolumesIndex); // - 1
        } else {
            return this.boxVolumes.get(this.boxVolumesIndex);
        }

    }

    private void fillBoxWithItems(double boxVolRemaining, List<OrderItem> orderItemsSortedByVolume) {


        Box box = new Box(boxID, inventory.getBoxDescriptionFromVolume(boxVolRemaining));
        // creating and adding box to list
        this.boxList.add(box);
        this.boxID++;

        // start filling order items to box until box is full
        // while box can still fit the next item in line


        // while box can still fit next item in line AND we are not at the last item in order items
        while (this.orderItemsIndex != orderItemsSortedByVolume.size() && boxVolRemaining >= orderItemsSortedByVolume.get(this.orderItemsIndex).getVolume()) {


            OrderItem itemToAddToBox = orderItemsSortedByVolume.get(this.orderItemsIndex);
            // reduce remaining order volume with volume of item added to box RECENTLY ADDED -------------------
            // adding items to box
            box.addItem(itemToAddToBox);
            totalOrderVolume -= itemToAddToBox.getVolume(); //-------------------------
            boxVolRemaining -= itemToAddToBox.getVolume();

            this.orderItemsIndex++;

//            if (this.orderItemsIndex+1 == orderItemsSortedByVolume.size()) {
//                break;
//            }
//                    nextItemVolume = orderItemsSortedByVolume.get(orderItemsIndex + 1).getVolume();
        }


        this.boxVolumesIndex++;
    }

    private double getTotalOrderVolume(Order order) {
        // get total volume of order
        double orderVolume = 0;
        for (OrderItem item :
                order.getItems()) {
            orderVolume += item.getCategory().getVolume();
        }
        return orderVolume;
    }

    private void deferExtraOrders(int timeslotMaxOrders, List<Order> orders) {
        int timeslotOrders = orders.size();

        if (timeslotOrders > timeslotMaxOrders) {
            int numOfExtraOrders = timeslotOrders - timeslotMaxOrders;


            for (int i = timeslotOrders; i > timeslotOrders - numOfExtraOrders; i--) {
                reportGenerator.getUnaccommodatedOrders().add(orders.remove(i));
            }
        }
    }
}


//            if (this.allBoxSizesIndex + 1 == this.allBoxSizes.size()) {
//                // start of box filling, starting box volume
//                boxVolRemaining = this.allBoxSizes.get(allBoxSizesIndex);
//                // remaining order volume reduced according to box volume selected
//                totalOrderVolume -= boxVolRemaining;
//
//                // creating and adding box to list
//                Box box = new Box(boxID, inventory.getBoxDescriptionFromVolume(boxVolRemaining));
//                this.boxList.add(box);
//                this.boxID++;
//
//
//                // start filling order items to box until box is full
//                // while box can still fit next item in line AND we are not at the last item in order items
////                if ()
//                while (orderItemsIndex + 1 != orderItemsSortedByVolume.size() && boxVolRemaining >= orderItemsSortedByVolume.get(orderItemsIndex + 1).getVolume()) {
//
//
//                    OrderItem itemToAddToBox = orderItemsSortedByVolume.get(orderItemsIndex);
//                    // adding items to box
//                    box.addItem(itemToAddToBox);
//                    //orderItemsSortedByVolume.remove(orderItemsIndex);
//                    boxVolRemaining -= itemToAddToBox.getVolume();
//
//                    orderItemsIndex++;
//
////                    nextItemVolume = orderItemsSortedByVolume.get(orderItemsIndex + 1).getVolume();
//                }
//
//                allBoxSizesIndex++;
//
//            } else if (totalOrderVolume < allBoxSizes.get(allBoxSizesIndex + 1)) {
//                this.allBoxSizesIndex++;
//                continue;
//            } else {
//                // start of box filling, starting box volume
//                boxVolRemaining = allBoxSizes.get(allBoxSizesIndex);
//                // remaining order volume reduced according to box volume selected
//                totalOrderVolume -= boxVolRemaining;
//
//                Box box = new Box(boxID, inventory.getBoxDescriptionFromVolume(boxVolRemaining));
//                // creating and adding box to list
//                this.boxList.add(box);
//                this.boxID++;
//
//                // start filling order items to box until box is full
//                // while box can still fit the next item in line
//
//
//
//                // while box can still fit next item in line AND we are not at the last item in order items
//                while (orderItemsIndex + 1 != orderItemsSortedByVolume.size() && boxVolRemaining >= orderItemsSortedByVolume.get(orderItemsIndex + 1).getVolume()) {
//
//
//                    OrderItem itemToAddToBox = orderItemsSortedByVolume.get(orderItemsIndex);
//                    // adding items to box
//                    box.addItem(itemToAddToBox);
//                    //orderItemsSortedByVolume.remove(orderItemsIndex);
//                    boxVolRemaining -= itemToAddToBox.getVolume();
//
//                    orderItemsIndex++;
//
////                    nextItemVolume = orderItemsSortedByVolume.get(orderItemsIndex + 1).getVolume();
//                }
//
//
//                allBoxSizesIndex++;
//            }
//            // if total order volume is less than current box size and greater than next box size, get curr box
//            if (totalOrderVolume < allBoxSizes.get(allBoxSizesIndex) && (totalOrderVolume > allBoxSizes.get(allBoxSizesIndex + 1)))
//            {
//                // start of box filling, starting box volume
//                boxVolRemaining = allBoxSizes.get(allBoxSizesIndex);
//                // remaining order volume reduced according to box volume selected
//                totalOrderVolume =- allBoxSizes.get(allBoxSizesIndex);
//
//                // start filling order items to box until box is full
//                while (boxVolRemaining > 0) {
//
//                    // creating and adding box to list
//                    Box box = new Box(boxID, inventory.getBoxDescriptionFromVolume(boxVolRemaining));
//                    boxList.add(box);
//                    boxID++;
//                    // adding items to box
//                    box.addItem(orderItemsSortedByVolume.get(orderItemsIndex));
//                    //orderItemsSortedByVolume.remove(orderItemsIndex);
//                    boxVolRemaining =- orderItemsSortedByVolume.get(orderItemsIndex).getVolume();
//                    orderItemsIndex++;
//                }
//
//                allBoxSizesIndex = 0;
//            }
//
//            // we're at the largest box size, use it
//            if (allBoxSizesIndex + 1 == allBoxSizes.size()) {
//                boxVolRemaining = allBoxSizes.get(allBoxSizesIndex);
//                totalOrderVolume = -allBoxSizes.get(allBoxSizesIndex);
//
//
//                while (boxVolRemaining > 0) {
//
//                    // creating and adding box to list
//                    Box box = new Box(boxID, inventory.getBoxDescriptionFromVolume(boxVolRemaining));
//                    boxList.add(box);
//                    boxID++;
//                    // adding items to box
//                    box.addItem(orderItemsSortedByVolume.get(orderItemsIndex));
//                    //orderItemsSortedByVolume.remove(orderItemsIndex);
//                    boxVolRemaining =- orderItemsSortedByVolume.get(orderItemsIndex).getVolume();
//                    orderItemsIndex++;
//                }
//                allBoxSizesIndex = 0;
//            }
