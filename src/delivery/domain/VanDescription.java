package delivery.domain;

public class VanDescription {
    private int id;
    private String size_label;
    private int volume;

    public VanDescription(int id, String size_label, int volume) {
        this.id = id;
        this.size_label = size_label;
        this.volume = volume;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSize_label() {
        return size_label;
    }

    public void setSize_label(String size_label) {
        this.size_label = size_label;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }
}
