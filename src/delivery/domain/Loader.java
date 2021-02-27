package delivery.domain;

public class Loader extends Employee{

    public Loader(int id, String name, int pin) {
        super(id, name, pin);
    }

    public Loader(String name, int pin) {
        super(name, pin);
    }
}
