package delivery.domain;

public class Manager extends Employee{

    public Manager(int id, String name, int pin) {
        super(id, name, pin);
    }

    public Manager(String name, int pin) {
        super(name, pin);
    }
}
