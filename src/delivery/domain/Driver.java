package delivery.domain;

public class Driver extends Employee{

    public Driver(String name, int pin) {
        super(name, pin);
    }

    public Driver(int id, String name, int pin) {
        super(id, name, pin);

    }
    public Driver(String name, int pin){
        super(name, pin);
    }
}
