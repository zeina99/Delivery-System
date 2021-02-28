
package delivery.domain;

public class BoxDescription {
    private int id;
    private String sizeLabel;
    private double volume;


    public BoxDescription(int id, String sizeLabel, double volume){
        this.id = id;
        this.sizeLabel = sizeLabel;
        this.volume = volume;
    }

    // getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public String getSizeLabel() {
        return sizeLabel;
    }

    public void setSize_label(String sizeLabel) {
        this.sizeLabel = sizeLabel;
    }

    @Override
    public String toString() {
        return "BoxDescription{" +
                "id=" + id +
                ", sizeLabel='" + sizeLabel + '\'' +
                ", volume=" + volume +
                '}';
    }
}