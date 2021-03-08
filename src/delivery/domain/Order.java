package delivery.domain;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Order {
    private int id;
    private Customer customer;
    private OrderType orderType;
    private TimeSlots timeSlot;
    private List<OrderItem> items;
    // TODO: inform rest about this change
    private double deliveryFee;

    public Order(int id, Customer customer, OrderType orderType, TimeSlots timeSlot, List<OrderItem> items) {
        this.id = id;
        this.customer = customer;
        this.orderType = orderType;
        this.timeSlot = timeSlot;
        this.items = items;
    }

    public Order(Customer customer, OrderType orderType, TimeSlots timeSlot, List<OrderItem> items) {
        this.customer = customer;
        this.orderType = orderType;
        this.timeSlot = timeSlot;
        this.items = items;
    }

    public Order(int id, Customer customer, OrderType orderType, TimeSlots timeSlot, List<OrderItem> items, double deliveryFee) {
        this.id =id;
        this.customer = customer;
        this.orderType = orderType;
        this.timeSlot = timeSlot;
        this.items = items;
        this.deliveryFee = deliveryFee;
    }

    // TODO: set delivery fee when order items are added to boxes
    public void setDeliveryFee(double deliveryFee) {
        // if order type is STORE, apply discount, else no discount
        this.deliveryFee = OrderType.STORE == this.orderType ? deliveryFee * 0.85 : deliveryFee;
    }
    public double getTotalOrderVolume() {
        // get total volume of order
        double orderVolume = 0;
        for (OrderItem item :
                items) {
            orderVolume += item.getCategory().getVolume();

        }
        return orderVolume;
    }
    public double getDeliveryFee() {
        return deliveryFee;
    }

    public void addItem(OrderItem item){
        this.items.add(item);
    }
    public List<OrderItem> getItems() {
        return items;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public TimeSlots getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(TimeSlots timeSlot) {
        this.timeSlot = timeSlot;
    }

    @Override
    public String toString() {
        return "\nOrder{" +
                "id=" + id +
                ", customer=" + customer +
                ", orderType=" + orderType +
                ", timeSlot=" + timeSlot +
                ", items=" + items +
                ", deliveryFee=" + deliveryFee +
                '}';
    }

    public List<OrderItem> getItemsSortedDesc() {
        // order items descending by volume
        List<OrderItem> sortedItems = items.stream().sorted(Comparator.comparing(OrderItem::getVolume).reversed()).collect(Collectors.toList());
        return sortedItems;

    }


}
