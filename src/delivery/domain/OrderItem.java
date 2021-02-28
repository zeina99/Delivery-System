package delivery.domain;

public class OrderItem {
    private int id;
    private int orderID;
    private Category category;
    private double volume;
    public OrderItem(int id, int orderID, Category category){
        this.id = id;
        this.orderID = orderID;
        this.category = category;
        this.volume = this.setVolume();
    }

    public OrderItem(int orderID, Category category) {
        this.orderID = orderID;
        this.category = category;
        this.volume = volume;
    }

    private double setVolume() {
        return this.category.getVolume();
    }

    public double getVolume() {
        return volume;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}