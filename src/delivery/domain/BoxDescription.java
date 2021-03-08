
package delivery.domain;

public class BoxDescription {
    private int id;
    private String sizeLabel;
    private double volume;
    private final double boxRate;


    public BoxDescription(int id, String sizeLabel, double volume, double boxRate){
        this.id = id;
        this.sizeLabel = sizeLabel;
        this.volume = volume;
        this.boxRate = boxRate;
    }


    public double getBoxRate() {
        return boxRate;
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
                ", boxRate=" + boxRate +
                '}';
    }
}