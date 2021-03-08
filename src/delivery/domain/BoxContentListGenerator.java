package delivery.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class BoxContentListGenerator {

    private final ReportGenerator reportGenerator;
    private Inventory inventory = new Inventory();
    // box id
    private int boxID;
    private Map<TimeSlots, List<Box>> timeslotBoxMap;
    private final List<Double> boxVolumes;
    private int boxVolumesIndex;
    private int orderItemsIndex;
    private double totalOrderVolume;
    private double boxesCostPerOrder;
    private double boxVolRemaining;
    private final int NUMOFVANS = 6;
    private final int ORDERS_PER_VAN = 8;
    // max orders per van is 9
    private final int MAX_ORDERS_IN_TIMESLOT = ORDERS_PER_VAN * NUMOFVANS;

    public BoxContentListGenerator(ReportGenerator reportGenerator) {

        this.reportGenerator = reportGenerator;
        this.boxID = 1;
        this.timeslotBoxMap = populateTimeSlotBoxMap();

        // allBoxSizes Sorted descending , starting with largest box size
        this.boxVolumes = inventory.getAllBoxVolumesSorted();
        this.boxVolumesIndex = 0;

    }

    private Map<TimeSlots, List<Box>> populateTimeSlotBoxMap(){
        Map<TimeSlots, List<Box>> timeslotBoxMap = new TreeMap<>();
        for (TimeSlots timeslot :
                TimeSlots.values()) {
            timeslotBoxMap.put(timeslot, new ArrayList<>());
        }
        return timeslotBoxMap;
    }


    public Map<TimeSlots, List<Box>> generateBoxContentList() {



        // loop over all time slots
        for (TimeSlots timeslot :
                reportGenerator.getOrderMap().keySet()) {

            // get orders for current timeslot
            List<Order> timeslotOrders = inventory.getAllOrdersMap().get(timeslot);

            // defer extra orders to unaccomodated list
            deferExtraOrders(MAX_ORDERS_IN_TIMESLOT, timeslotOrders);

            // loop over array of orders for curr timeslot
            // here we try to fill boxes with order items for each order
            fillOrderItemsInBox(timeslotOrders, timeslot);

        }
        return timeslotBoxMap;
    }

    private void fillOrderItemsInBox(List<Order> timeslotOrders, TimeSlots timeSlot) {
        // looping over each order
        for (Order order :
                timeslotOrders) {

            // order items descending by item volume
            List<OrderItem> orderItemsSortedByVolume = order.getItemsSortedDesc();

            totalOrderVolume = order.getTotalOrderVolume();

            // fills order items in boxes
            fillAllOrderItems(orderItemsSortedByVolume, timeSlot);

            // set delivery fee for order and update db with order fee
            order.setDeliveryFee(boxesCostPerOrder);
            inventory.setOrderDeliveryFee(order.getId(), order.getDeliveryFee());

        }
    }

    private void fillAllOrderItems(List<OrderItem> orderItemsSortedByVolume, TimeSlots timeSlot) {

        // refactor this method further,
        // method to return the suitable box volume
        // method to fill the items in the box
        // getting total volume of order
        orderItemsIndex = 0;
        boxesCostPerOrder = 0;
        while (totalOrderVolume > 0) {
            //--- get a box just greater than total order volume in if statements ---

            // if the next smaller box is smaller than current total order volume, continue
            // else, pick the current one
            // if we are at the smallest box, use it

            // pick a suitable size box
            boxVolRemaining = getBoxVol(totalOrderVolume);

            // fills a box with items
            fillBoxWithItems(orderItemsSortedByVolume, timeSlot);

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
            return this.boxVolumes.get(boxVolumesIndex);
        } else {
            return this.boxVolumes.get(this.boxVolumesIndex);
        }

    }

    private void fillBoxWithItems(List<OrderItem> orderItemsSortedByVolume, TimeSlots timeSlots) {

        Box box = new Box(boxID, inventory.getBoxDescriptionFromVolume(boxVolRemaining));
        // creating and adding box to list
        timeslotBoxMap.get(timeSlots).add(box);
        boxID++;

        boxesCostPerOrder+= inventory.getBoxDescriptionFromVolume(boxVolRemaining).getBoxRate();
        // start filling order items to box until box is full

        // while box can still fit next item in line AND we are not at the last item in order items
        while (this.orderItemsIndex != orderItemsSortedByVolume.size() && boxVolRemaining >= orderItemsSortedByVolume.get(this.orderItemsIndex).getVolume()) {

            OrderItem itemToAddToBox = orderItemsSortedByVolume.get(this.orderItemsIndex);
            // adding items to box
            box.addItem(itemToAddToBox);
            totalOrderVolume -= itemToAddToBox.getVolume();
            boxVolRemaining -= itemToAddToBox.getVolume();

            this.orderItemsIndex++;

        }
        this.boxVolumesIndex++;

    }



    private void deferExtraOrders(int timeslotMaxOrders, List<Order> orders) {
        int timeslotOrders = orders.size();

        if (timeslotOrders > timeslotMaxOrders) {
            int numOfExtraOrders = timeslotOrders - timeslotMaxOrders;


            for (int i = timeslotOrders; i > timeslotOrders - numOfExtraOrders; i--) {
                Order orderToRemove = orders.remove(i - 1);
                reportGenerator.getUnaccommodatedOrders().put(orderToRemove.getId(), new ArrayList<>());

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
