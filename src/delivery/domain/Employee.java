package delivery.domain;

public abstract class Employee {
    private int id;
    private String name;
    private int pin;

    protected Employee(int id, String name, int pin) {
        this.id = id;
        this.name = name;
        this.pin = pin;
    }

    protected Employee(String name, int pin) {
        this.name = name;
        this.pin = pin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pin=" + pin +
                '}';
    }
}
