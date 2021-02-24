package delivery.domain;

import java.util.Random;

public enum TimeSlots {
    NINE_TWELVE, TWELVE_THREE, THREE_SIX, SIX_NINE;

    private static Random ran = new Random();
    private static int num;
    static int length = TimeSlots.values().length;

    // returns random OrderType
    public static TimeSlots getRandomTimeSlot(){

        // get random number between 0 , 3
        num = ran.nextInt(TimeSlots.values().length);

        // if num is 0 -> NINE_TWELVE
        // if num is 1 -> TWELVE-THREE
        // num == 2 -> THREE-SIX
        // num== 3 -> SIX-NINE
        TimeSlots order;

        switch (num){
            case 0: order = NINE_TWELVE; break;
            case 1: order = TWELVE_THREE; break;
            case 2: order = THREE_SIX; break;
            case 3: order = SIX_NINE; break;
            default:
                throw new IllegalStateException("Unexpected value: " + num);
        }
        return order;

    }
}