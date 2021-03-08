package delivery.domain;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ReportGenerator {
    private final BoxContentListGenerator boxContentListGenerator = new BoxContentListGenerator(this);
    private Map<TimeSlots, List<Order>> orderMap;
    private Map<TimeSlots, List<Box>> boxMap;
    private List<BoxDescription> boxDescriptionList;
    // <Order ID, boxList>
    private Map<Integer, List<Box>> unaccommodatedOrders;
    private Inventory inventory = new Inventory();
    Map<TimeSlots, List<Van>> vanLoadingMap;



    public ReportGenerator() {

        this.orderMap = inventory.getAllOrdersMap();
        this.boxMap = new TreeMap<>();

        this.unaccommodatedOrders = new HashMap<>();
        this.boxDescriptionList = this.inventory.getAllBoxDescriptions();
    }

    public void generateVanLoadingReport() {
        VanLoadingListGenerator vanLoadingListGenerator = new VanLoadingListGenerator(boxMap, this);
        vanLoadingMap = vanLoadingListGenerator.generateVanLoadingMap();


        try (FileWriter vanContentWrite = new FileWriter(new File("Reports", "Van-Loading.txt"))) {

            for (TimeSlots timeSlots :
                    vanLoadingMap.keySet()) {
                vanContentWrite.write(String.format("Time-Slot: %s\n", timeSlots.toString()));
                for (Van van :
                        vanLoadingMap.get(timeSlots)) {
                    vanContentWrite.write(String.format("Van Id: %d\n", van.getId()));

                    for (Box box :
                            van.getVanContent()) {
                        vanContentWrite.write(String.format("    - Box ID: %d\n", box.getId()));
                    }
                }



            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public void generateRevenueReport(){
        double dayRevenueTotal;
        DayRevenue dayRevenue = new DayRevenue(this, vanLoadingMap);

        dayRevenueTotal =dayRevenue.getRevenueForTheDay();

        try (FileWriter revenueWrite = new FileWriter(new File("Reports", "Revenue.txt"))){

            revenueWrite.write(String.format("Revenue for the day: %.3f", dayRevenueTotal));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void generateBoxContentReport() {

        // list of boxes including order items - includes all orders for the day
        boxMap = boxContentListGenerator.generateBoxContentList();
        // create a txt report showing details of box contents

        try (FileWriter boxContentWriter = new FileWriter(new File("Reports", "Box-Content.txt"))) {

            for (TimeSlots timeSlot :
                    boxMap.keySet()) {
                for (Box box :
                        boxMap.get(timeSlot)) {


                    boxContentWriter.write(String.format("Box ID: %d, Order ID: %d\n", box.getId(), box.getContent().get(0).getOrderID()));
                    boxContentWriter.write("Items: \n");

                    boxContentWriter.write("Item ID Item Name \n");

                    for (OrderItem orderItem :
                            box.getContent()) {

                        boxContentWriter.write(String.format("%d %s\n", orderItem.getId(), orderItem.getCategory().getType()));
                    }
                }
                boxContentWriter.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void generateUnaccommodatedReport() {

        try (FileWriter unaccomodatedListWriter = new FileWriter(new File("Reports", "Unaccomodated Orders.txt"))) {

            unaccomodatedListWriter.write("Unaccommodated Orders: \n");

            for (Map.Entry<Integer, List<Box>> order :
                    unaccommodatedOrders.entrySet()) {

                // if box list is empty -> all order items are deferred
                // else not all items are deferred, some are only

                if (order.getValue().isEmpty()) {
                    unaccomodatedListWriter.write(String.format("Order ID: %d - All Items - \n", order.getKey()));
                } else {

                    unaccomodatedListWriter.write(String.format("Order ID: %d\n", order.getKey()));
                    unaccomodatedListWriter.write(String.format("    - Box ID: %d\n", order.getValue().get(0).getId()));

                    for (Box box :
                            order.getValue()) {
                        for (OrderItem item :
                                box.getContent()) {
                            unaccomodatedListWriter.write(String.format("        - Item ID: %d, Category: %s\n", item.getId(), item.getCategory()));

                        }
                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void generateVanScheduleReport() {
        try (FileWriter vanScheduleWriter = new FileWriter(new File("Reports", "Van Schedule.txt"))) {
            for (TimeSlots timeslot :
                    vanLoadingMap.keySet()) {
                List<Van> vanList = vanLoadingMap.get(timeslot);
                vanScheduleWriter.write(String.format("Time slot: %s\n", timeslot));
                for (Van van :
                        vanList) {
                    String timings = getVanArrivalLeavingTime(timeslot);
                    String[] arrivalLeaving = timings.split("-");
                    vanScheduleWriter.write(String.format("Van ID: %d\n", van.getId()));
                    vanScheduleWriter.write(String.format("    - Arrival time: %s\n    - Leaving time: %s\n", arrivalLeaving[0], arrivalLeaving[1]));
                    vanScheduleWriter.write(String.format("Assigned to: %s\n\n", inventory.getDriverById(van.getDriver_id())));
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getVanArrivalLeavingTime(TimeSlots timeslot) {
        String timings;
        switch (timeslot){
            case NINE_TWELVE:
                timings = "8:50-11:40";
                break;
            case TWELVE_THREE:
                timings = "11:50-2:40";
                break;
            case THREE_SIX:
                timings = "2:50-5:40";
                break;
            case SIX_NINE:
                timings = "5:50-9";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + timeslot);
        }
        return timings;
    }



    public Map<TimeSlots, List<Box>> getBoxMap() {
        return boxMap;
    }
    public Map<TimeSlots, List<Van>> getVanLoadingMap() {
        return vanLoadingMap;
    }

    public Map<TimeSlots, List<Order>> getOrderMap() {
        /*
        returns orders in a Map with TimeSlot as key and a List of corresponding orders for the time slot as value
         **/
        return orderMap;
    }

    public Map<Integer, List<Box>> getUnaccommodatedOrders() {
        return unaccommodatedOrders;
    }

    public Inventory getInventory() {
        return inventory;
    }
}
