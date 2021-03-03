package delivery.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DayRevenue {
    private final ReportGenerator reportGenerator;
    Map<TimeSlots, List<Van>> vanLoadingMap;
    public DayRevenue(ReportGenerator reportGenerator, Map<TimeSlots, List<Van>> vanLoadingMap ) {
        this.reportGenerator = reportGenerator;
        this.vanLoadingMap = vanLoadingMap;
    }

    public double getRevenueForTheDay() {
        // order id, delivery fee
        Map<Integer, Double> deliveryFeeMap = new HashMap<>();

        double totalRevenue = 0;

        for (List<Van> vanList :
                vanLoadingMap.values()) {
            for (Van van :
                    vanList) {
                for (Box box :
                        van.getVanContent()) {

                    // get order ID of each box, if contained in map -> move on
                    // if not -> add order id , delivery fee to map

                    int orderID = box.getContent().get(0).getOrderID();

                    if (!deliveryFeeMap.containsKey(orderID)) {
                        deliveryFeeMap.put(orderID, reportGenerator.getInventory().getOrderByID(orderID).getDeliveryFee());
                    }
                }
            }
        }
        for (Double deliveryFee :
                deliveryFeeMap.values()) {
            totalRevenue += deliveryFee;

        }
        return totalRevenue;
    }


}