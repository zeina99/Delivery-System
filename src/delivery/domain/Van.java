package delivery.domain;

import java.util.ArrayList;
import java.util.List;

public class Van implements Comparable<Van> {
    private int id;
    private VanDescription vanDescription;
    private int driver_id;
    private List<Box> vanContent;


    public Van(int id, VanDescription vanDescription, int driver_id) {
        this.id = id;
        this.vanDescription = vanDescription;
        this.driver_id = driver_id;
        this.vanContent = new ArrayList<>();
    }

    public Van(int id, VanDescription vanDescription, int driver_id, List<Box> vanContent) {
        this.id = id;
        this.vanDescription = vanDescription;
        this.driver_id = driver_id;
        this.vanContent = vanContent;
    }

    public double getVolumeOccupied() {
        double volumeOccupied = 0;
        for (Box box :
                vanContent) {
            volumeOccupied += box.getBoxDescription().getVolume();
        }
        return volumeOccupied;
    }

    public void addBox(Box box) {
        vanContent.add(box);
    }

    public List<Box> getVanContent() {
        return vanContent;
    }

    public void setVanContent(List<Box> vanContent) {
        this.vanContent = vanContent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public VanDescription getVanDescription() {
        return vanDescription;
    }

    public void setVanDescription(VanDescription vanDescription) {
        this.vanDescription = vanDescription;
    }

    public int getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(int driver_id) {
        this.driver_id = driver_id;
    }

    @Override
    public String toString() {
        return "Van{" +
                "id=" + id +
                ", vanDescription=" + vanDescription +
                ", driver_id=" + driver_id +
                "}\n";
    }

    @Override
    public int compareTo(Van van) {
        return Double.compare(this.getVanDescription().getVolume(), van.getVanDescription().getVolume());
    }
}
