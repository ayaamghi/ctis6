package edu.guilford.cardgame.Backend.Data;

public class Day {

    private int day;
    private int plantCount;
    private int plantEaterCount;
    private int meatEaterCount;
    private double plantMass;
    private double plantEaterMass;
    private double meatEaterMass;



    public Day(int day, int plantCount, int plantEaterCount, int meatEaterCount, double plantMass, double plantEaterMass, double meatEaterMass) {
        this.day = day;
        this.plantCount = plantCount;
        this.plantEaterCount = plantEaterCount;
        this.meatEaterCount = meatEaterCount;
        this.plantMass = plantMass;
        this.plantEaterMass = plantEaterMass;
        this.meatEaterMass = meatEaterMass;

    }

    public int getDay() {
        return day;
    }
    //getters
    public int getPlantCount() {
        return plantCount;
    }
    //getters
    public int getPlantEaterCount() {
        return plantEaterCount;
    }
    //getters
    public int getMeatEaterCount() {
        return meatEaterCount;
    }
    //get the plant mass
    public double getPlantMass() {
        return plantMass;
    }

    public double getPlantEaterMass() {
        return plantEaterMass;
    }

    public double getMeatEaterMass() {
        return meatEaterMass;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Day [day=").append(day).append(", plantCount=").append(plantCount).append(", plantEaterCount=").append(plantEaterCount).append(", meatEaterCount=").append(meatEaterCount).append(", plantMass=").append(plantMass).append(", plantEaterMass=").append(plantEaterMass).append(", meatEaterMass=").append(meatEaterMass).append("]");
        return sb.toString();
    }



}
