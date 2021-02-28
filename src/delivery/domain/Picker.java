package delivery.domain;

public class Picker extends Employee{

    public Picker(int id, String name, int pin) {
        super(id, name, pin);
    }

    public Picker(String name, int pin) {
        super(name, pin);
    }
    @Override
    public String toString() {
        return "Picker " + super.toString();
    }
}
