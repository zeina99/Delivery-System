package delivery.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class VanLoadingListGenerator {
    ReportGenerator reportGenerator;
    private Map<TimeSlots, List<Van>> vanLoadingMap;
    private final Inventory inventory = new Inventory();
    Map<TimeSlots, List<Box>> timeslotBoxMap;

    public VanLoadingListGenerator(Map<TimeSlots, List<Box>> timeslotBoxMap, ReportGenerator reportGenerator) {
        this.reportGenerator = reportGenerator;
        vanLoadingMap = populateTreeMap();
        this.timeslotBoxMap = timeslotBoxMap;
    }

    private Map<TimeSlots, List<Van>> populateTreeMap() {
        Map<TimeSlots, List<Van>> vanLoadingMap = new TreeMap<>();
        for (TimeSlots timeSlot :
                TimeSlots.values()) {
            vanLoadingMap.put(timeSlot, new ArrayList<>());
        }
        return vanLoadingMap;
    }

    public Map<TimeSlots, List<Van>> generateVanLoadingMap() {
        // TODO: look into the order in one van constraint


        // loop over each time slot
        for (TimeSlots timeSlot :
                timeslotBoxMap.keySet()) {

            List<Van> vanList = inventory.getAllVansSortedByVolume();
            int vanListIndex = 0;

            // total box volume in curr timeslot
            double totalBoxVolume = getBoxVolumeForTimeSlot(timeSlot);

            List<Box> boxListInTimeSlot = timeslotBoxMap.get(timeSlot);

            while (!boxListInTimeSlot.isEmpty() && !vanList.isEmpty()) {


                //double remainingBoxVols = boxListInTimeSlot

                double currVanVolume = vanList.get(vanListIndex).getVanDescription().getVolume();
                double currLargestVanVol = vanList.get(0).getVanDescription().getVolume();
                boolean isLastVan = vanList.size() == 1;

                if (vanListIndex == vanList.size() - 1) {
                    vanListIndex = 0;

                    Van van = vanList.get(0);
                    totalBoxVolume = fillBoxInVan(van, boxListInTimeSlot, totalBoxVolume, isLastVan);
                    vanLoadingMap.get(timeSlot).add(vanList.remove(0));
                }


                // if boxvol > largest van vol available
                else if (totalBoxVolume > currLargestVanVol) {
                    Van van = vanList.get(0);
                    totalBoxVolume = fillBoxInVan(van, boxListInTimeSlot, totalBoxVolume, isLastVan);
                    vanLoadingMap.get(timeSlot).add(vanList.remove(0));
                }

                // totalBoxVolume > nextVanVol && totalBoxVolume < currVolume
                else if (totalBoxVolume > vanList.get(vanListIndex + 1).getVanDescription().getVolume() && totalBoxVolume < currVanVolume) {

                    // got the van, start filling items
                    Van van = vanList.get(vanListIndex);
                    totalBoxVolume = fillBoxInVan(van, boxListInTimeSlot, totalBoxVolume, isLastVan);
                    vanLoadingMap.get(timeSlot).add(vanList.remove(vanListIndex));
                    vanListIndex--;

                    // Next smaller van is larger than totalBoxVol
                } else {
                    vanListIndex++;
                }

            }
            //TODO: when at the last van, get order volume and see if it fits in remaining van vol
            deferExtraOrders(boxListInTimeSlot, reportGenerator.getUnaccommodatedOrders());
        }
        // get the list of all vans ordered from largest to smallest volume

        // get volume of all boxes for time slot

        // loop while total boxes volume is greater than 0
        // if van is the smallest one, pick curr van
        // if boxes volume is less than greatest size van, find the van with just right volume
        // if van size is greater than curr total box volume and greater than next smallest van, pick curr van
        // else pick the largest van


        return vanLoadingMap;
    }

    private void deferExtraOrders(List<Box> boxListInTimeSlot, Map<Integer, List<Box>> unaccommodatedOrders) {

        List<Box> boxToRemove = new ArrayList<>();
        for (Box box :
                boxListInTimeSlot) {
            int orderIDInBox = box.getContent().get(0).getOrderID();

            // if order id does not already exist in unaccomodated orders, remove box
            if (!unaccommodatedOrders.containsKey(orderIDInBox)) {
                unaccommodatedOrders.put(orderIDInBox, new ArrayList<>());


                boxToRemove.add(box);

            } else if (unaccommodatedOrders.containsKey(orderIDInBox)) {
                unaccommodatedOrders.get(orderIDInBox).add(box);

                boxToRemove.add(box);
            }
        }
        boxListInTimeSlot.removeAll(boxToRemove);
    }

    private double fillBoxInVan(Van van, List<Box> boxListInTimeSlot, double totalBoxVolume, boolean isLastVan) {
        // while van still has space, add boxes to van
        //if ()
        while (!boxListInTimeSlot.isEmpty() && van.getVolumeOccupied() < van.getVanDescription().getVolume()) {
            //TODO: figure out a way to decide how to deal with the last order getting filled into the van.

//            while (isLastVan && !boxListInTimeSlot.isEmpty()) {
//                int index = 0;
//                int boxOrderID = boxListInTimeSlot.get(index).getContent().get(0).getOrderID();
//                Order nextOrderToFill = inventory.getOrderByID(boxOrderID);
//                // van has no more space to fit the next order
//
//                if (nextOrderToFill.getTotalOrderVolume() > van.getVolumeOccupied()) {
//                    // remove boxes with this order id from boxListInTimeSlot
//                    while (boxOrderID == nextOrderToFill.getId() && !boxListInTimeSlot.isEmpty()){
//                        index++;
//                        boxOrderID = boxListInTimeSlot.get(index).getContent().get(0).getOrderID();
////                        boxListInTimeSlot.remove(0);
//                    }
//
//                    break;
//                }
//            }

            Box boxToFill = boxListInTimeSlot.remove(0);
            van.addBox(boxToFill);
            totalBoxVolume -= boxToFill.getBoxDescription().getVolume();
        }
        return totalBoxVolume;
    }

    private double getBoxVolumeForTimeSlot(TimeSlots timeSlot) {
        double totalBoxVolumeInTimeSlot = 0;
        for (Box box :
                timeslotBoxMap.get(timeSlot)) {
            totalBoxVolumeInTimeSlot += box.getBoxDescription().getVolume();
        }
        return totalBoxVolumeInTimeSlot;
    }
}
