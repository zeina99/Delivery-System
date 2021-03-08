
package delivery.domain;

public class Category {
    private int id;
    private String type;
    private double volume;

    public Category(int id, String type, double volume) {
        this.id = id;
        this.type = type;
        this.volume = volume;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", volume=" + volume +
                '}';
    }
}