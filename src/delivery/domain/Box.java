package delivery.domain;

import java.util.ArrayList;
import java.util.List;

public class Box {
    private int id;
    private BoxDescription boxDescription;
    private List<OrderItem> content;

    public Box(int id, BoxDescription boxDescription) {
        this.boxDescription = boxDescription;
        this.id = id;
        this.content = new ArrayList<>();
    }

    public List<OrderItem> getContent() {
        return content;
    }

    public void addItem(OrderItem item) {
        this.content.add(item);
    }

    public BoxDescription getBoxDescription() {
        return boxDescription;
    }

    public void setBoxDescription(BoxDescription boxDescription) {
        this.boxDescription = boxDescription;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Box{" +
                "id=" + id +
                ", boxDescription=" + boxDescription +
                ", content=" + content +
                '}';
    }


}
