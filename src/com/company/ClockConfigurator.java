package com.company;

import java.util.ArrayList;

public class ClockConfigurator {

    private float busClock;
    private float requiredClock;
    private float nearestClock;
    private float finalDifference;
    private final ArrayList<Multiplexer> multiplexers = new ArrayList();
    private final ArrayList<Float> dividers = new ArrayList<>();
    private ArrayList<Float> finalDividers;

    public ClockConfigurator(float busClock, float requiredClock) {
        this.requiredClock = requiredClock;
        this.busClock = busClock;
        this.nearestClock = busClock;
        this.finalDifference = busClock;
    }

    public void addMultiplexer(float[] dividers){
        multiplexers.add(new Multiplexer(dividers));
    }

    public String getMultiplexers(){
        StringBuilder string = new StringBuilder();
        for(Multiplexer m: multiplexers){
            string.append("Multiplexer ").append(multiplexers.indexOf(m)).append(": ").append(m.toString()).append("\n");
        }

        return string.substring(0, string.length()-2);
    }

    public String getMultiplexers(int index){
        return "Multiplexer " + index + ": " + multiplexers.get(index).toString();
    }

    public void removeMultiplexer(int index){
        if(index >= multiplexers.size() || index < 0) {
            System.err.println("Incorrect index");
            return;
        }
        multiplexers.remove(index);
    }

    public void editMultiplexer(int index, float[] dividers){
        if(index >= multiplexers.size() || index < 0) {
            System.err.println("Incorrect index");
            return;
        } else if (dividers == null || dividers.length == 0){
            System.err.println("Dividers array is empty");
            return;
        }
        multiplexers.set(index, new Multiplexer(dividers));
    }

    public float getBusClock() {
        return busClock;
    }

    public void setBusClock(float busClock) {
        this.busClock = busClock;
    }

    public float getRequiredClock() {
        return requiredClock;
    }

    public void setRequiredClock(float requiredClock) {
        this.requiredClock = requiredClock;
    }

    public void calculateDividers(){
        int size = multiplexers.size();
        if(size == 0){
            System.err.println("Please, add at least one multiplexer");
            return;
        } else {
            for(Multiplexer m: multiplexers){
                if(m.getDividers().isEmpty()){
                    System.err.println("One of the multiplexers is empty");
                    return;
                }
            }
        }

        for(Float a: multiplexers.get(0).getDividers()){
            if(size > 1){
                for(Float b: multiplexers.get(1).getDividers()){
                    if(size > 2){
                        for(Float c: multiplexers.get(2).getDividers()){
                            if(size > 3){
                                for(Float d: multiplexers.get(3).getDividers()) {
                                    if (size > 4) {
                                        for (Float e : multiplexers.get(4).getDividers()) {
                                            dividers.add(a); dividers.add(b); dividers.add(c);dividers.add(d); dividers.add(e);
                                            if(nearestFinder())
                                                return;
                                        }
                                    } else {
                                        dividers.add(a); dividers.add(b); dividers.add(c);dividers.add(d);
                                        if(nearestFinder())
                                            return;
                                    }
                                }
                            } else {
                                dividers.add(a); dividers.add(b); dividers.add(c);
                                if(nearestFinder())
                                    return;
                            }
                        }
                    } else {
                        dividers.add(a); dividers.add(b);
                        if(nearestFinder())
                            return;
                    }
                }
            } else {
                dividers.add(a);
                if(nearestFinder())
                    return;
            }
        }
        System.out.println("Nearest frequency is: " + nearestClock );
        System.out.println("Dividers are:" + finalDividers);
    }

    private float divider(ArrayList<Float> dividers){
        float result = busClock;
        for(Float f: dividers) {
            result /= f;
        }
        System.out.println(result); //Show all calculated frequencies
        return result;
    }

    private boolean nearestFinder(){
        float temp = divider(dividers);
        if(temp == requiredClock){
            System.out.println("Success: ");
            for(Float i: dividers){
                System.out.print(i + "\t" );
            }
            finalDividers = new ArrayList<>(dividers);
            dividers.clear();
            return true;
        } else {
            float difference = Math.abs(requiredClock - temp);
            if(finalDifference > difference){
                finalDifference = difference;
                nearestClock = temp;
                finalDividers = new ArrayList<>(dividers);
            }
        }
        dividers.clear();
        return false;
    }

    public ArrayList<Float> getFinalDividers() {
        return finalDividers;
    }
}
