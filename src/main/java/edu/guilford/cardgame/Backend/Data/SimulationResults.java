package edu.guilford.cardgame.Backend.Data;

import java.util.ArrayList;

public class SimulationResults {


    private ArrayList<Day> days;
    private Day maxPlantCountDay;
    private Day maxPlantEaterCountDay;
    private Day maxMeatEaterCountDay;
    private Day maxPlantMassDay;
    private Day maxPlantEaterMassDay;
    private Day maxMeatEaterMassDay;

    private int maxPlantCount;
    private int maxPlantEaterCount;
    private int maxMeatEaterCount;
    private double maxPlantMass;
    private double maxPlantEaterMass;
    private double maxMeatEaterMass;



    public ArrayList<Day> getDays() {
        return days;
    }
    public SimulationResults() {
        days = new ArrayList<>();
    }

    public void addDay(int day, int plantCount, int plantEaterCount, int meatEaterCount, double plantMass,
                       double plantEaterMass, double meatEaterMass) {
        days.add(new Day(day, plantCount, plantEaterCount, meatEaterCount, plantMass, plantEaterMass, meatEaterMass));
        calculateMaxValues();
    }

    private void calculateMaxValues() {
        maxPlantCount = 0;
        maxPlantEaterCount = 0;
        maxMeatEaterCount = 0;
        maxPlantMass = 0;
        maxPlantEaterMass = 0;
        maxMeatEaterMass = 0;
        for (Day d : days) {
            if (d.getPlantCount() > maxPlantCount || d.getDay() == 0) {
                maxPlantCount = d.getPlantCount();
                maxPlantCountDay = d;
            }
            if (d.getPlantEaterCount() > maxPlantEaterCount || d.getDay() == 0) {
                maxPlantEaterCount = d.getPlantEaterCount();
                maxPlantEaterCountDay = d;
            }
            if (d.getMeatEaterCount() > maxMeatEaterCount || d.getDay() == 0) {
                maxMeatEaterCount = d.getMeatEaterCount();
                maxMeatEaterCountDay = d;
            }
            if (d.getPlantMass() > maxPlantMass || d.getDay() == 0) {
                maxPlantMass = d.getPlantMass();
                maxPlantMassDay = d;
            }
            if (d.getPlantEaterMass() > maxPlantEaterMass || d.getDay() == 0) {
                maxPlantEaterMass = d.getPlantEaterMass();
                maxPlantEaterMassDay = d;
            }
            if (d.getMeatEaterMass() > maxMeatEaterMass || d.getDay() == 0) {
                maxMeatEaterMass = d.getMeatEaterMass();
                maxMeatEaterMassDay = d;
            }
        }
    }

    public Day getMaxPlantCountDay() {
        return maxPlantCountDay;
    }

    public Day getMaxPlantEaterCountDay() {
        return maxPlantEaterCountDay;
    }

    public Day getMaxMeatEaterCountDay() {
        return maxMeatEaterCountDay;
    }

    public Day getMaxPlantMassDay() {
        return maxPlantMassDay;
    }

    public Day getMaxPlantEaterMassDay() {
        return maxPlantEaterMassDay;
    }

    public Day getMaxMeatEaterMassDay() {
        return maxMeatEaterMassDay;
    }

    public int getMaxPlantCount() {
        return maxPlantCount;
    }

    public int getMaxPlantEaterCount() {
        return maxPlantEaterCount;
    }

    public int getMaxMeatEaterCount() {
        return maxMeatEaterCount;
    }

    public double getMaxPlantMass() {
        return maxPlantMass;
    }

    public double getMaxPlantEaterMass() {
        return maxPlantEaterMass;
    }

    public double getMaxMeatEaterMass() {
        return maxMeatEaterMass;
    }

    @Override
    public String toString() {


            return "Simulation ran for " + days.size() + " days. Max plant count: " + maxPlantCount + " on day " + maxPlantCountDay.getDay() +
                    ". Max plant eater count: " + maxPlantEaterCount + " on day " + maxPlantEaterCountDay.getDay() +
                    ". Max meat eater count: " + maxMeatEaterCount + " on day " + maxMeatEaterCountDay.getDay() +
                    ". Max plant mass: " + maxPlantMass + " on day " + maxPlantMassDay.getDay() +
                    ". Max plant eater mass: " + maxPlantEaterMass + " on day " + maxPlantEaterMassDay.getDay() +
                    ". Max meat eater mass: " + maxMeatEaterMass + " on day " + maxMeatEaterMassDay.getDay();


    }









}
