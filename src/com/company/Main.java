package com.company;

public class Main {

    public static void main(String[] args) {
        ClockConfigurator clockConfigurator = new ClockConfigurator(16, 6);

        clockConfigurator.addMultiplexer(new float[]{1, 2, 4, 8, 16});
        clockConfigurator.addMultiplexer(new float[]{1, 2, 3, 4, 5});
        clockConfigurator.addMultiplexer(new float[]{1.8F, 2.2F, 3.0F, 4.1F});
        clockConfigurator.addMultiplexer(new float[]{0.5F, 1.5F, 7.4F, 14.5F});
        clockConfigurator.addMultiplexer(new float[]{1.2F, 0.5F});
        clockConfigurator.addMultiplexer(new float[]{0.2F, 5.1F, 3.2F, 2.0F});
        clockConfigurator.addMultiplexer(new float[]{0.1F, 1.0F, 10.0F});
        clockConfigurator.addMultiplexer(new float[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20});

        clockConfigurator.calculateDividers();

        //Additional part
//        additionalFunctionality(clockConfigurator);
    }

    private static void additionalFunctionality(ClockConfigurator clockConfigurator){
        for(Float f: clockConfigurator.getFinalDividers()){
            System.out.println(f);
        }

        clockConfigurator.removeMultiplexer(1);
        System.out.println(clockConfigurator.getMultiplexers(1));
        System.out.println(clockConfigurator.getMultiplexers());
        clockConfigurator.editMultiplexer(2, new float[]{5.5F, 1.1F, 7.7F});

        clockConfigurator.setBusClock(32);
        clockConfigurator.setRequiredClock(16);
        System.out.println("New BUS_CLOCK: " + clockConfigurator.getBusClock() + "\t New Required_clock: " + clockConfigurator.getRequiredClock());

        System.out.println(clockConfigurator.getMultiplexers());
    }
}
