package com.company;

import java.util.ArrayList;

public class ClockConfigurator {

    private float busClock;
    private float requiredClock;
    private float closestClock;
    private float finalDifference;
    private final ArrayList<Multiplexer> multiplexers = new ArrayList();
    private final ArrayList<Float> dividers = new ArrayList<>();
    private ArrayList<Float> finalDividers;
    private boolean isComputed = false;

    public ClockConfigurator(float busClock, float requiredClock) {
        this.requiredClock = requiredClock;
        this.busClock = busClock;
        this.closestClock = busClock;
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

        ArrayList<Integer> combination = new ArrayList<>();
        for (int i = 0; i < multiplexers.size(); i++) {
            combination.add(0);
        }
        while (!isComputed) {
            for(int i = 0; i < combination.size(); i++){
                dividers.add(multiplexers.get(i).getDividers().get(combination.get(i)));
            }

            boolean isCombinationChanged = false;
            for(int i = combination.size() - 1; i >= 0; i--){
                if(combination.get(i) < multiplexers.get(i).getDividers().size() - 1){
                    combination.set(i, combination.get(i) + 1);
                    isCombinationChanged = true;
                    for(int j = i+1; j < combination.size(); j++){
                        combination.set(j, 0);
                    }
                    break;
                }
            }
            isComputed = !isCombinationChanged;

            if(nearestFinder())
                isComputed = true;
            dividers.clear();
        }
        System.out.println("Closest frequency is: " + closestClock);
        System.out.println("Dividers are:" + finalDividers);
    }

    private float divider(ArrayList<Float> dividers){
        float result = busClock;
        for(Float f: dividers) {
            result /= f;
        }
//        System.out.println(result); //Show all calculated frequencies
        return result;
    }

    private boolean nearestFinder(){
        float quotient = divider(dividers);
        if(quotient == requiredClock){
            closestClock = requiredClock;
            finalDividers = new ArrayList<>(dividers);
            dividers.clear();
            return true;
        } else {
            float difference = Math.abs(requiredClock - quotient);
            if(finalDifference > difference){
                finalDifference = difference;
                closestClock = quotient;
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
