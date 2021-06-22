package com.company;

import java.util.ArrayList;

public class Multiplexer {
    private final ArrayList<Float> dividersList = new ArrayList<>();

    public Multiplexer(float[] dividers) {
        for(float f: dividers){
            if(f <= 0){
                System.err.printf("Incorrect divider value: %.1f\n", f);
                continue;
            }
            dividersList.add(f);
        }
    }

    public ArrayList<Float> getDividers() {
        return dividersList;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        for(Float f: dividersList)
            string.append(f).append(" ");

        return string.toString();
    }

}
