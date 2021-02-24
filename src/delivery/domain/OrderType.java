package delivery.domain;

import java.util.Random;

public enum OrderType {
    INDIVIDUAL, STORE;
    // TODO: check stackoverflow for optimization
    private static Random ran = new Random();

    // returns random OrderType
    public static OrderType getRandomOrderType(){

        // get random number between 0 , 1
        int num = ran.nextInt(OrderType.values().length);

        // if num is 0 -> INDIVIDUAL
        // if num is 1 -> STORE
        if (num == 1) return STORE;

        else return INDIVIDUAL;
    }

}
