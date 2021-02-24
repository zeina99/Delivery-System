package delivery.domain;

public class OrderItem {
    private int id;
    private int orderID;
    private Category category;

    public OrderItem(int id, int orderID, Category category){
        this.id = id;
        this.orderID = orderID;
        this.category = category;
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
