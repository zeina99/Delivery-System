package delivery.domain;

import delivery.technicalServices.persistence.OrderDAO;
import delivery.technicalServices.persistence.OrderItemDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OrderGenerator {
    private List<Order> orders;
    private List<OrderItem> allOrderItems;
    private int total_orders;
    private int order_id;
    private Inventory inventory;

    private Random random = new Random();

    public OrderGenerator() {
        this.orders = new ArrayList<>();
        this.total_orders = 100;
        this.order_id = 1;
        this.inventory = new Inventory();
        this.allOrderItems = new ArrayList<>();



    }

    public List<Order> getOrders() {
        return orders;
    }

    public void generateOrders() {

         List<OrderItem> orderItems;

        // generate 100 orders
        for (int i = 0; i < total_orders; i++) {
            orderItems = new ArrayList<>();
            // order params
            Customer randomCustomer = inventory.getRandomCustomer();
            OrderType orderType = OrderType.getRandomOrderType();
            TimeSlots timeslot = TimeSlots.getRandomTimeSlot();

            // -- generating random order items --

            // get random order items count for curr order
            int orderItemsCount = random.nextInt(14) + 1;

            // generating order items to current order
            for (int j = 0; j < orderItemsCount; j++) {
                Category randomCategory = inventory.getRandomCategory();
                OrderItem item = new OrderItem(order_id, randomCategory);
                orderItems.add(item);
                allOrderItems.add(item);

            }

            // creating the order and adding to list
            Order order = new Order(randomCustomer, orderType, timeslot, orderItems);
            this.orders.add(order);

            order_id++;
        }


    }
    private void saveToDatabase(){
        OrderDAO orderDAO = new OrderDAO();
        orderDAO.insertAll(this.orders);

        OrderItemDAO orderItemDAO = new OrderItemDAO();
        orderItemDAO.insertAll(this.allOrderItems);
    }

    public static void main(String[] args) {
        OrderGenerator gen = new OrderGenerator();
        gen.generateOrders();
        List<Order> or = gen.getOrders();
        for (Order order :
                or) {
            System.out.println(order);
        }
    }
}
