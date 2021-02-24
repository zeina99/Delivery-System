package delivery.domain;

public class Category {
    private int id;
    private String type;
    private int volume;

    public Category(int id, String type, int volume) {
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

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }
}
