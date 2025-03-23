package edu.guilford.cardgame.Backend.Organisms;

import edu.guilford.cardgame.Backend.Params.ParameterRecord ;

import java.util.ArrayList;
import java.util.Random;

public class MeatEater extends Critter {
    private Random random = new Random();
    private ArrayList<PlantEater> plantEaters;

    public MeatEater(double size, double growthRate, double foodNeed, ArrayList<PlantEater> plantEaters, ParameterRecord parameterRecord) {
        super(size, growthRate, foodNeed, parameterRecord.lifespanMeatEater(), parameterRecord);
        this.plantEaters = plantEaters;
        this.parameterRecord = parameterRecord;
    }

    /***
     * Overrides simulate day with the meat eater's behavior. The meat eater will eat a random number of plant eaters, and if the meat eater is too old, it will die.
     */
    @Override 
    public void simulateDay() { 
        int numToEat = random.nextInt(parameterRecord.lowerBoundNumPlantEatersEat(), parameterRecord.upperBoundNumPlantEatersEat());
        //use the prob to catch variable in order to determine if the meat eater will catch the plant eater
        for (int i = 0; i < numToEat; i++) {
            if (random.nextDouble() < parameterRecord.meatEaterProbabilityToCatch()) {
                PlantEater plantEater = plantEaters.get(random.nextInt(plantEaters.size()));
                eat(plantEater.getSize());
                plantEater.die();
            }

            if(age > lifespan && random.nextDouble() < parameterRecord.probabilityOldAgeDeathMeatEater()) {
                die();
            }
            super.simulateDay();
        }

    }

    



    @Override 
    public String toString() {
        return "MeatEater [size=" + size + ", growthRate=" + growthRate + ", alive=" + alive + ", age=" + age + 
               ", foodNeed=" + foodNeed + ", foodEaten=" + foodEaten + " plantEaters=" + plantEaters.size() + "]";
    }
    
    
}
