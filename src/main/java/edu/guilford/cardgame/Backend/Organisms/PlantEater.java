package edu.guilford.cardgame.Backend.Organisms;

import edu.guilford.cardgame.Backend.Params.ParameterRecord ;

import java.util.ArrayList;
import java.util.Random;

/***
 * This class is the child class of Critter. It represents the plant eaters in the simulation.
 * It has the attributes of the plants that the plant eater can eat.
 * It has the methods to simulate a day in the life of the plant eater and to chew on the plants.
 * It also has a toString method to print out the attributes of the plant eater.
 * @author Ayaam Ghimire
 */
public class PlantEater extends Critter {
    private ArrayList<Plant> plants;
    private Random random = new Random();

    public PlantEater(double size, double growthRate, double foodNeed, ArrayList<Plant> plants, ParameterRecord parameterRecord) {
        super(size, growthRate, foodNeed, parameterRecord.lifespanPlantEater(), parameterRecord);
        this.plants = plants;
    }




    /***
     * This method simulates the plant eater chewing on a plant. It changes the size of the plant by the amount passed in.
     * @param plant the plant to chew on
     */
    public void chew(Plant plant) { //TODO abstract this out
        double maxChewAmount = Math.min(Math.min(plant.getSize() * parameterRecord.amountOfPlantMaxChewed(), stillNeed()), foodNeed * parameterRecord.percentageOfFoodNeedToChew()); //changed these from the assignment as well
        if (maxChewAmount <= 0) {
            return;
        }
        //TODO abstract this out
        double chewAmount = random.nextDouble(parameterRecord.floorOnChewAmount() * maxChewAmount,  maxChewAmount); //added a floor
        plant.chewedOn(chewAmount);
        eat(chewAmount);
    }

    /***
     * This method simulates a day in the life of the plant eater. It determines how many plants the plant eater will chew on and then calls the chew method.
     */
    @Override
    public void simulateDay() {
        //TODO abstract this out
        if (age > lifespan && random.nextDouble() < parameterRecord.probabilityOldAgeDeathPlantEater()) {
            die();
        }
        //TODO abstract this out
        int numPlantsToChew = (int) (random.nextDouble(parameterRecord.lowerBoundNumPlantsToChew(), parameterRecord.upperBoundNumPlantsToChew()) * plants.size()); //had to change nextDouble range  otherwise kept crashing to 0 immediatly
        for (int i = 0; i < numPlantsToChew; i++) {
            Plant plant = plants.get(random.nextInt(plants.size())); //takes 21000 milliseconds just on htis line
            chew(plant);
        }

        super.simulateDay();
    }

    @Override
    public String toString() {
        return "PlantEater [size=" + size + ", growthRate=" + growthRate + ", alive=" + alive + ", age=" + age +
                ", foodNeed=" + foodNeed + ", foodEaten=" + foodEaten + ", plants=" + plants.size() + "]";
    }
}