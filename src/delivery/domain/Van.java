package delivery.domain;

public class Van {
    private int id;
    private VanDescription vanDescription;
    private int driver_id;

    public Van(int id, VanDescription vanDescription, int driver_id) {
        this.id = id;
        this.vanDescription = vanDescription;
        this.driver_id = driver_id;
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
                '}';
    }
}
