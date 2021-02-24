package delivery.domain;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private int id;
    private Customer customer;
    private OrderType orderType;
    private TimeSlots timeSlot;
    private List<OrderItem> items;
    // TODO: inform rest about this change
    private double deliveryFee;

    public Order(int id, Customer customer, OrderType orderType, TimeSlots timeSlot) {
        this.id = id;
        this.customer = customer;
        this.orderType = orderType;
        this.timeSlot = timeSlot;
        this.items = new ArrayList<>();
        this.deliveryFee = 0;
    }
    public void setDeliveryFee(int deliveryFee) {
        // if order type is STORE, apply discount, else no discount
        this.deliveryFee =  OrderType.STORE == this.orderType ? deliveryFee * 0.85 : deliveryFee;
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
        return "Order{" +
                "id=" + id +
                ", customer=" + customer +
                ", orderType=" + orderType +
                ", timeSlot=" + timeSlot +
                ", items=" + items +
                '}';
    }
}
