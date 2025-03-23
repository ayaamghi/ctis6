package edu.guilford.cardgame.Backend.Params;

public record ParameterRecord(
        double lowerBoundNumPlantsToChew,
        double upperBoundNumPlantsToChew,
        double amountOfPlantMaxChewed,
        double percentageOfFoodNeedToChew,
        double floorOnChewAmount,
        double meatEaterSize,
        double meatEaterGrowthRate,
        double meatEaterFoodNeed,
        double probabilityOldAgeDeathPlantEater,
        double probabilityOldAgeDeathMeatEater,
        double probabilityOldAgeDeathPlant,
        double probabilityOldAgeDeathCreature,
        int lifespanPlant,
        int lifespanPlantEater,
        int lifespanMeatEater,
        int numMeatEaters, //150 by default
        double updateFoodNeedPercentage, //was .1 by default
        double meatEaterProbabilityToCatch, //was .4 by default
        int lowerBoundNumPlantEatersEat, //was 1 by default
        int upperBoundNumPlantEatersEat, //was 3 by default
        int numPlantEaters, //300 by defaul
        int numPlants, //2000 by default
        int plantSizeLowerBound, //900 by default
        int plantSizeUpperBound, //1100 by default
        int plantGrowthRate, //5 by default
        int plantEaterSizeLowerBound, //900 by default
        int plantEaterSizeUpperBound, //1100 by default
        int plantEaterGrowthRate, //3 by default
        int plantEaterFoodNeed, //50 by default
        double plantReproductionProbability, //was .05 by default
        int floorNewPlantSize, //was 250 by default
        int ceilingNewPlantSize, //was 350 by default
        double plantEaterReproductionProbability, //was .05 by default
        int floorNewPlantEaterSize, //was 900 by default
        int ceilingNewPlantEaterSize, //was 1100 by default
        double meatEaterReproductionProbability //was .30 by default
 ) {

    public ParameterRecord() {
        this(
                Parameters.LOWER_BOUND_NUM_PLANTS_TO_CHEW.getValue(),
                Parameters.UPPER_BOUND_NUM_PLANTS_TO_CHEW.getValue(),
                Parameters.AMOUNT_OF_PLANT_MAX_CHEWED.getValue(),
                Parameters.PERCENTAGE_OF_FOODNEED_TO_CHEW.getValue(),
                Parameters.FLOOR_ON_CHEW_AMOUNT.getValue(),
                Parameters.MEAT_EATER_SIZE.getValue(),
                Parameters.MEAT_EATER_GROWTH_RATE.getValue(),
                Parameters.MEAT_EATER_FOOD_NEED.getValue(),
                Parameters.PROBABILITY_OLD_AGE_DEATH_PLANT_EATER.getValue(),
                Parameters.PROBABILITY_OLD_AGE_DEATH_MEAT_EATER.getValue(),
                Parameters.PROBABILITY_OLD_AGE_DEATH_PLANT.getValue(),
                Parameters.PROBABILITY_OLD_AGE_DEATH_CREATURE.getValue(),
                Parameters.LIFESPAN_PLANT.getIntValue(),
                Parameters.LIFESPAN_PLANT_EATER.getIntValue(),
                Parameters.LIFESPAN_MEAT_EATER.getIntValue(),
                Parameters.NUM_MEAT_EATERS.getIntValue(),
                0.1, // updateFoodNeedPercentage
                0.4, // meatEaterProbabilityToCatch
                1,   // lowerBoundNumPlantEatersEat
                3,    // upperBoundNumPlantEatersEat
                300,  // numPlantEaters
                2000, // numPlants
                900,  // plantSizeLowerBound
                1100, // plantSizeUpperBound
                5,    // plantGrowthRate
                900,  // plantEaterSizeLowerBound
                1100, // plantEaterSizeUpperBound
                3,    // plantEaterGrowthRate
                50,    // plantEaterFoodNeed
                0.05, // plantReproductionProbability
                250,  // floorNewPlantSize
                350,  // ceilingNewPlantSize
                0.05, // plantEaterReproductionProbability
                900,  // floorNewPlantEaterSize
                1100, // ceilingNewPlantEaterSize
                1 // meatEaterReproductionProbability
        );
    }



}





