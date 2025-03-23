package edu.guilford.cardgame.Backend;

import edu.guilford.cardgame.Backend.DataAnalysis.SimulationResults;
import edu.guilford.cardgame.Backend.Organisms.Creature;
import edu.guilford.cardgame.Backend.Organisms.MeatEater;
import edu.guilford.cardgame.Backend.Organisms.Plant;
import edu.guilford.cardgame.Backend.Organisms.PlantEater;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import edu.guilford.cardgame.Backend.Params.ParameterRecord;

public class Ecosystem {

    private final ArrayList<Plant> plants;
    private final ArrayList<PlantEater> plantEaters;
    private final ArrayList<MeatEater> meatEaters;
    private ParameterRecord parameterRecord;

    Random random = new Random();

    public Ecosystem(ParameterRecord parameterRecord) throws IOException {

        this.parameterRecord = parameterRecord;
        plants = new ArrayList<>();
        for (int i = 0; i < parameterRecord.numPlants(); i++) {
            plants.add(new Plant(random.nextDouble(parameterRecord.plantSizeLowerBound(),
                    parameterRecord.plantSizeUpperBound()), parameterRecord.plantGrowthRate(), parameterRecord));
        }
        plantEaters = new ArrayList<>();
        for (int i = 0; i < parameterRecord.numPlantEaters(); i++) {
            plantEaters.add(new PlantEater(random.nextDouble(parameterRecord.plantEaterSizeLowerBound(),
                    parameterRecord.plantEaterSizeUpperBound()), parameterRecord.plantEaterGrowthRate(),
                    parameterRecord.plantEaterFoodNeed(), plants, parameterRecord));
        }

        meatEaters = new ArrayList<>();
        for (int i = 0; i < parameterRecord.numMeatEaters(); i++) {
            meatEaters.add(new MeatEater(parameterRecord.meatEaterSize(), parameterRecord.meatEaterGrowthRate(),
                    parameterRecord.meatEaterFoodNeed(), plantEaters, parameterRecord));
        }

    }
//    public Ecosystem(int numPlants, int numPlantEaters, int numMeatEaters) {
//          plants = new ArrayList<>();
//        for (int i = 0; i < numPlants; i++) {
//            plants.add(new Plant(random.nextDouble(900, 1100), 5));
//        }
//        plantEaters = new ArrayList<>();
//        for (int i = 0; i < numPlantEaters; i++) {
//            plantEaters.add(new PlantEater(random.nextDouble(900, 1100), 3, 50, plants));
//        }
//
//        // create 150 meateaters
//        meatEaters = new ArrayList<>();
//        for (int i = 0; i < numMeatEaters; i++) {
//            meatEaters.add(
//                    new MeatEater(Parameters.MEAT_EATER_SIZE.getValue(), Parameters.MEAT_EATER_GROWTH_RATE.getValue(),
//                            Parameters.MEAT_EATER_FOOD_NEED.getValue(), plantEaters));
//        }
//
//    }

//    public Ecosystem(ArrayList<Plant> plants, ArrayList<PlantEater> plantEaters, ArrayList<MeatEater> meatEaters, ParameterRecord parameterRecord) {
//        this.plants = plants;
//        this.plantEaters = plantEaters;
//        this.meatEaters = meatEaters;
//        this.parameterRecord = parameterRecord;
//
//    }

    public SimulationResults runSimulation() {
        Random random = new Random();
        SimulationResults results = new SimulationResults();
        int day = 1;
        while (stillAlive(plantEaters) && stillAlive(plants) && stillAlive(meatEaters)) {
            // to check for them

            // Simulate a day for every Plant (e.g., they grow).
            for (Plant p : plants) {
                p.simulateDay();
            }

            // Simulate a day for every PlantEater.
            //94% of the entire runtime is spent at this stage

            for (PlantEater pe : plantEaters) {
                pe.simulateDay();
            }

            // simulate a day for every MeatEater
            for (MeatEater me : meatEaters) {
                me.simulateDay();
            }

            // Birth process:
            // 5% chance each day to give birth to a new Plant.
            if (random.nextDouble() < parameterRecord.plantReproductionProbability()) {
                double newPlantSize = random.nextDouble(parameterRecord.floorNewPlantSize(), parameterRecord.ceilingNewPlantSize()); // Approximately 300 ± 50 grams.
                plants.add(new Plant(newPlantSize, parameterRecord.plantGrowthRate(), parameterRecord));
            }
            // 30% chance each day to give birth to a new PlantEater.
            if (random.nextDouble() < parameterRecord.plantEaterReproductionProbability()) {
                double newPlantEaterSize = random.nextDouble(parameterRecord.floorNewPlantEaterSize(), parameterRecord.ceilingNewPlantEaterSize()); // Approximately 1000 ± 100 grams.
                plantEaters.add(new PlantEater(newPlantEaterSize, parameterRecord.plantEaterGrowthRate(), parameterRecord.plantEaterFoodNeed(), plants, parameterRecord));
            }

            // 30% chance each day to give birth to a new MeatEater.
            if (random.nextDouble() < parameterRecord.meatEaterReproductionProbability()) {
                meatEaters.add(new MeatEater(parameterRecord.meatEaterSize(), parameterRecord.meatEaterGrowthRate(), parameterRecord.meatEaterFoodNeed(), plantEaters, parameterRecord));
                int i = 0;
                while (i < plantEaters.size()) {
                    if (!plantEaters.get(i).getAlive()) {
                        plantEaters.remove(i);
                    } else {
                        i++;
                    }
                }
                int j = 0;
                while (j < meatEaters.size()) {
                    if (!meatEaters.get(j).getAlive()) {
                        meatEaters.remove(j);
                    } else {
                        j++;
                    }
                }


            }
            // fill out results.addDay() with the correct values
            results.addDay(
                    day,
                    plants.size(),
                    plantEaters.size(),
                    meatEaters.size(),
                    totalMass(plants),
                    totalMass(plantEaters),
                    totalMass(meatEaters));

            day++;


        }
        return results;

    }
    /***
     * This method returns true if any of the creatures in the list are alive, and false otherwise.
     * @param creatures
     * @return boolean true if any of the creatures in the list are alive, and false otherwise.
     */
    private static boolean stillAlive(ArrayList<? extends Creature> creatures) {
        for (Creature c : creatures) {
            if (c.getAlive()) {
                return true;
            }
        }
        return false;
    }

    /***
     * This method returns the total mass of all the creatures in the list.
     * @param creatures
     * @return double total mass of all the creatures in the list.
     */
    private static double totalMass(ArrayList<? extends Creature> creatures) {
        double totalMass = 0;
        for (Creature c : creatures) {
            totalMass += c.getSize();
        }
        return totalMass;
    }

}