package delivery.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OrderGenerator {
    private List<Order> orders;
    private List<OrderItem> orderItems;
    private int total_orders;
    private int order_id;
    private Inventory inventory;

    private Random random = new Random();

    public OrderGenerator() {
        this.orders = new ArrayList<>();
        this.total_orders = 100;
        this.order_id = 1;
        this.inventory = new Inventory();


    }

    public List<Order> getOrders() {
        return orders;
    }

    public void generateOrders(){

        int orderItemId = 1;

        // generate 100 orders
        for (int i = 0; i < total_orders; i++) {

            // order params
            Customer randomCustomer = inventory.fetchRandomCustomer();
            OrderType orderType = OrderType.getRandomOrderType();
            TimeSlots timeslot = TimeSlots.getRandomTimeSlot();

            // -- generating random order items --

            // get random order items count for curr order
            int orderItemsCount = random.nextInt(14);

            // adding order items to current order
            for (int j = 0; j < orderItemsCount; j++) {
                Category randomCategory = inventory.fetchRandomCategory();
                OrderItem item  = new OrderItem( orderItemId, order_id, randomCategory);
                orderItems.add(item);
            }

            // creating the order and adding to list
            Order order = new Order(order_id,randomCustomer, orderType, timeslot, orderItems);
            this.orders.add(order);

            order_id++;
            orderItemId++;
        }

    }

    public static void main(String[] args) {
        OrderGenerator gen = new OrderGenerator();
        gen.generateOrders();
       List<Order> or = gen.getOrders();
        for (Order order:
             or) {
            System.out.println(order);
        }
    }
}
