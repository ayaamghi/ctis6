package edu.guilford.cardgame.Backend.Params;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ParameterRecord {

    private double lowerBoundNumPlantsToChew;
    private double upperBoundNumPlantsToChew;
    private double amountOfPlantMaxChewed;
    private double percentageOfFoodNeedToChew;
    private double floorOnChewAmount;
    private double meatEaterSize;
    private double meatEaterGrowthRate;
    private double meatEaterFoodNeed;
    private double probabilityOldAgeDeathPlantEater;
    private double probabilityOldAgeDeathMeatEater;
    private double probabilityOldAgeDeathPlant;
    private double probabilityOldAgeDeathCreature;
    private int lifespanPlant;
    private int lifespanPlantEater;
    private int lifespanMeatEater;
    private int numMeatEaters;
    private double updateFoodNeedPercentage;
    private double meatEaterProbabilityToCatch;
    private int lowerBoundNumPlantEatersEat;
    private int upperBoundNumPlantEatersEat;
    private int numPlantEaters;
    private int numPlants;
    private int plantSizeLowerBound;
    private int plantSizeUpperBound;
    private int plantGrowthRate;
    private int plantEaterSizeLowerBound;
    private int plantEaterSizeUpperBound;
    private int plantEaterGrowthRate;
    private int plantEaterFoodNeed;
    private double plantReproductionProbability;
    private int floorNewPlantSize;
    private int ceilingNewPlantSize;
    private double plantEaterReproductionProbability;
    private int floorNewPlantEaterSize;
    private int ceilingNewPlantEaterSize;
    private double meatEaterReproductionProbability;

    /**
     * No-argument constructor that sets default values matching the original record.
     */
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
                0.1,   // updateFoodNeedPercentage
                0.4,   // meatEaterProbabilityToCatch
                1,     // lowerBoundNumPlantEatersEat
                3,     // upperBoundNumPlantEatersEat
                300,   // numPlantEaters
                2000,  // numPlants
                900,   // plantSizeLowerBound
                1100,  // plantSizeUpperBound
                5,     // plantGrowthRate
                900,   // plantEaterSizeLowerBound
                1100,  // plantEaterSizeUpperBound
                3,     // plantEaterGrowthRate
                50,    // plantEaterFoodNeed
                0.05,  // plantReproductionProbability
                250,   // floorNewPlantSize
                350,   // ceilingNewPlantSize
                0.05,  // plantEaterReproductionProbability
                900,   // floorNewPlantEaterSize
                1100,  // ceilingNewPlantEaterSize
                1      // meatEaterReproductionProbability
        );
    }

    /**
     * Full constructor mirroring the original record signature.
     */
    public ParameterRecord(
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
            int numMeatEaters,
            double updateFoodNeedPercentage,
            double meatEaterProbabilityToCatch,
            int lowerBoundNumPlantEatersEat,
            int upperBoundNumPlantEatersEat,
            int numPlantEaters,
            int numPlants,
            int plantSizeLowerBound,
            int plantSizeUpperBound,
            int plantGrowthRate,
            int plantEaterSizeLowerBound,
            int plantEaterSizeUpperBound,
            int plantEaterGrowthRate,
            int plantEaterFoodNeed,
            double plantReproductionProbability,
            int floorNewPlantSize,
            int ceilingNewPlantSize,
            double plantEaterReproductionProbability,
            int floorNewPlantEaterSize,
            int ceilingNewPlantEaterSize,
            double meatEaterReproductionProbability
    ) {
        this.lowerBoundNumPlantsToChew = lowerBoundNumPlantsToChew;
        this.upperBoundNumPlantsToChew = upperBoundNumPlantsToChew;
        this.amountOfPlantMaxChewed = amountOfPlantMaxChewed;
        this.percentageOfFoodNeedToChew = percentageOfFoodNeedToChew;
        this.floorOnChewAmount = floorOnChewAmount;
        this.meatEaterSize = meatEaterSize;
        this.meatEaterGrowthRate = meatEaterGrowthRate;
        this.meatEaterFoodNeed = meatEaterFoodNeed;
        this.probabilityOldAgeDeathPlantEater = probabilityOldAgeDeathPlantEater;
        this.probabilityOldAgeDeathMeatEater = probabilityOldAgeDeathMeatEater;
        this.probabilityOldAgeDeathPlant = probabilityOldAgeDeathPlant;
        this.probabilityOldAgeDeathCreature = probabilityOldAgeDeathCreature;
        this.lifespanPlant = lifespanPlant;
        this.lifespanPlantEater = lifespanPlantEater;
        this.lifespanMeatEater = lifespanMeatEater;
        this.numMeatEaters = numMeatEaters;
        this.updateFoodNeedPercentage = updateFoodNeedPercentage;
        this.meatEaterProbabilityToCatch = meatEaterProbabilityToCatch;
        this.lowerBoundNumPlantEatersEat = lowerBoundNumPlantEatersEat;
        this.upperBoundNumPlantEatersEat = upperBoundNumPlantEatersEat;
        this.numPlantEaters = numPlantEaters;
        this.numPlants = numPlants;
        this.plantSizeLowerBound = plantSizeLowerBound;
        this.plantSizeUpperBound = plantSizeUpperBound;
        this.plantGrowthRate = plantGrowthRate;
        this.plantEaterSizeLowerBound = plantEaterSizeLowerBound;
        this.plantEaterSizeUpperBound = plantEaterSizeUpperBound;
        this.plantEaterGrowthRate = plantEaterGrowthRate;
        this.plantEaterFoodNeed = plantEaterFoodNeed;
        this.plantReproductionProbability = plantReproductionProbability;
        this.floorNewPlantSize = floorNewPlantSize;
        this.ceilingNewPlantSize = ceilingNewPlantSize;
        this.plantEaterReproductionProbability = plantEaterReproductionProbability;
        this.floorNewPlantEaterSize = floorNewPlantEaterSize;
        this.ceilingNewPlantEaterSize = ceilingNewPlantEaterSize;
        this.meatEaterReproductionProbability = meatEaterReproductionProbability;
    }

    // -- Getters (named like record accessors) --

    @JsonProperty("lowerBoundNumPlantsToChew")
    public double lowerBoundNumPlantsToChew() {
        return lowerBoundNumPlantsToChew;
    }

    @JsonProperty("upperBoundNumPlantsToChew")
    public double upperBoundNumPlantsToChew() {
        return upperBoundNumPlantsToChew;
    }

    @JsonProperty("amountOfPlantMaxChewed")
    public double amountOfPlantMaxChewed() {
        return amountOfPlantMaxChewed;
    }

    @JsonProperty("percentageOfFoodNeedToChew")
    public double percentageOfFoodNeedToChew() {
        return percentageOfFoodNeedToChew;
    }

    @JsonProperty("floorOnChewAmount")
    public double floorOnChewAmount() {
        return floorOnChewAmount;
    }

    @JsonProperty("meatEaterSize")
    public double meatEaterSize() {
        return meatEaterSize;
    }

    @JsonProperty("meatEaterGrowthRate")
    public double meatEaterGrowthRate() {
        return meatEaterGrowthRate;
    }

    @JsonProperty("meatEaterFoodNeed")
    public double meatEaterFoodNeed() {
        return meatEaterFoodNeed;
    }

    @JsonProperty("probabilityOldAgeDeathPlantEater")
    public double probabilityOldAgeDeathPlantEater() {
        return probabilityOldAgeDeathPlantEater;
    }

    @JsonProperty("probabilityOldAgeDeathMeatEater")
    public double probabilityOldAgeDeathMeatEater() {
        return probabilityOldAgeDeathMeatEater;
    }

    @JsonProperty("probabilityOldAgeDeathPlant")
    public double probabilityOldAgeDeathPlant() {
        return probabilityOldAgeDeathPlant;
    }

    public double probabilityOldAgeDeathCreature() {
        return probabilityOldAgeDeathCreature;
    }


    @JsonProperty("lifespanPlant")
    public int lifespanPlant() {
        return lifespanPlant;
    }

    @JsonProperty("lifespanPlantEater")
    public int lifespanPlantEater() {
        return lifespanPlantEater;
    }

    @JsonProperty("lifespanMeatEater")
    public int lifespanMeatEater() {
        return lifespanMeatEater;
    }

    @JsonProperty("numMeatEaters")
    public int numMeatEaters() {
        return numMeatEaters;
    }

    @JsonProperty("updateFoodNeedPercentage")
    public double updateFoodNeedPercentage() {
        return updateFoodNeedPercentage;
    }

    @JsonProperty("meatEaterProbabilityToCatch")
    public double meatEaterProbabilityToCatch() {
        return meatEaterProbabilityToCatch;
    }

    @JsonProperty("lowerBoundNumPlantEatersEat")
    public int lowerBoundNumPlantEatersEat() {
        return lowerBoundNumPlantEatersEat;
    }

    @JsonProperty("upperBoundNumPlantEatersEat")
    public int upperBoundNumPlantEatersEat() {
        return upperBoundNumPlantEatersEat;
    }

    @JsonProperty("numPlantEaters")
    public int numPlantEaters() {
        return numPlantEaters;
    }

    @JsonProperty("numPlants")
    public int numPlants() {
        return numPlants;
    }

    @JsonProperty("plantSizeLowerBound")
    public int plantSizeLowerBound() {
        return plantSizeLowerBound;
    }

    @JsonProperty("plantSizeUpperBound")
    public int plantSizeUpperBound() {
        return plantSizeUpperBound;
    }

    @JsonProperty("plantGrowthRate")
    public int plantGrowthRate() {
        return plantGrowthRate;
    }

    @JsonProperty("plantEaterSizeLowerBound")
    public int plantEaterSizeLowerBound() {
        return plantEaterSizeLowerBound;
    }

    @JsonProperty("plantEaterSizeUpperBound")
    public int plantEaterSizeUpperBound() {
        return plantEaterSizeUpperBound;
    }

    @JsonProperty("plantEaterGrowthRate")
    public int plantEaterGrowthRate() {
        return plantEaterGrowthRate;
    }

    @JsonProperty("plantEaterFoodNeed")
    public int plantEaterFoodNeed() {
        return plantEaterFoodNeed;
    }

    @JsonProperty("plantReproductionProbability")
    public double plantReproductionProbability() {
        return plantReproductionProbability;
    }


    @JsonProperty("floorNewPlantSize")
    public int floorNewPlantSize() {
        return floorNewPlantSize;
    }

    @JsonProperty("ceilingNewPlantSize")
    public int ceilingNewPlantSize() {
        return ceilingNewPlantSize;
    }

    @JsonProperty("plantEaterReproductionProbability")
    public double plantEaterReproductionProbability() {
        return plantEaterReproductionProbability;
    }

    @JsonProperty("floorNewPlantEaterSize")
    public int floorNewPlantEaterSize() {
        return floorNewPlantEaterSize;
    }

    @JsonProperty("ceilingNewPlantEaterSize")
    public int ceilingNewPlantEaterSize() {
        return ceilingNewPlantEaterSize;
    }

    @JsonProperty("meatEaterReproductionProbability")
    public double meatEaterReproductionProbability() {
        return meatEaterReproductionProbability;
    }

    // -- Setters --

    public void setLowerBoundNumPlantsToChew(double lowerBoundNumPlantsToChew) {
        this.lowerBoundNumPlantsToChew = lowerBoundNumPlantsToChew;
    }

    public void setUpperBoundNumPlantsToChew(double upperBoundNumPlantsToChew) {
        this.upperBoundNumPlantsToChew = upperBoundNumPlantsToChew;
    }

    public void setAmountOfPlantMaxChewed(double amountOfPlantMaxChewed) {
        this.amountOfPlantMaxChewed = amountOfPlantMaxChewed;
    }

    public void setPercentageOfFoodNeedToChew(double percentageOfFoodNeedToChew) {
        this.percentageOfFoodNeedToChew = percentageOfFoodNeedToChew;
    }

    public void setFloorOnChewAmount(double floorOnChewAmount) {
        this.floorOnChewAmount = floorOnChewAmount;
    }

    public void setMeatEaterSize(double meatEaterSize) {
        this.meatEaterSize = meatEaterSize;
    }

    public void setMeatEaterGrowthRate(double meatEaterGrowthRate) {
        this.meatEaterGrowthRate = meatEaterGrowthRate;
    }

    public void setMeatEaterFoodNeed(double meatEaterFoodNeed) {
        this.meatEaterFoodNeed = meatEaterFoodNeed;
    }

    public void setProbabilityOldAgeDeathPlantEater(double probabilityOldAgeDeathPlantEater) {
        this.probabilityOldAgeDeathPlantEater = probabilityOldAgeDeathPlantEater;
    }

    public void setProbabilityOldAgeDeathMeatEater(double probabilityOldAgeDeathMeatEater) {
        this.probabilityOldAgeDeathMeatEater = probabilityOldAgeDeathMeatEater;
    }

    public void setProbabilityOldAgeDeathPlant(double probabilityOldAgeDeathPlant) {
        this.probabilityOldAgeDeathPlant = probabilityOldAgeDeathPlant;
    }

    public void setProbabilityOldAgeDeathCreature(double probabilityOldAgeDeathCreature) {
        this.probabilityOldAgeDeathCreature = probabilityOldAgeDeathCreature;
    }

    public void setLifespanPlant(int lifespanPlant) {
        this.lifespanPlant = lifespanPlant;
    }

    public void setLifespanPlantEater(int lifespanPlantEater) {
        this.lifespanPlantEater = lifespanPlantEater;
    }

    public void setLifespanMeatEater(int lifespanMeatEater) {
        this.lifespanMeatEater = lifespanMeatEater;
    }

    public void setNumMeatEaters(int numMeatEaters) {
        this.numMeatEaters = numMeatEaters;
    }

    public void setUpdateFoodNeedPercentage(double updateFoodNeedPercentage) {
        this.updateFoodNeedPercentage = updateFoodNeedPercentage;
    }

    public void setMeatEaterProbabilityToCatch(double meatEaterProbabilityToCatch) {
        this.meatEaterProbabilityToCatch = meatEaterProbabilityToCatch;
    }

    public void setLowerBoundNumPlantEatersEat(int lowerBoundNumPlantEatersEat) {
        this.lowerBoundNumPlantEatersEat = lowerBoundNumPlantEatersEat;
    }

    public void setUpperBoundNumPlantEatersEat(int upperBoundNumPlantEatersEat) {
        this.upperBoundNumPlantEatersEat = upperBoundNumPlantEatersEat;
    }

    public void setNumPlantEaters(int numPlantEaters) {
        this.numPlantEaters = numPlantEaters;
    }

    public void setNumPlants(int numPlants) {
        this.numPlants = numPlants;
    }

    public void setPlantSizeLowerBound(int plantSizeLowerBound) {
        this.plantSizeLowerBound = plantSizeLowerBound;
    }

    public void setPlantSizeUpperBound(int plantSizeUpperBound) {
        this.plantSizeUpperBound = plantSizeUpperBound;
    }

    public void setPlantGrowthRate(int plantGrowthRate) {
        this.plantGrowthRate = plantGrowthRate;
    }

    public void setPlantEaterSizeLowerBound(int plantEaterSizeLowerBound) {
        this.plantEaterSizeLowerBound = plantEaterSizeLowerBound;
    }

    public void setPlantEaterSizeUpperBound(int plantEaterSizeUpperBound) {
        this.plantEaterSizeUpperBound = plantEaterSizeUpperBound;
    }

    public void setPlantEaterGrowthRate(int plantEaterGrowthRate) {
        this.plantEaterGrowthRate = plantEaterGrowthRate;
    }

    public void setPlantEaterFoodNeed(int plantEaterFoodNeed) {
        this.plantEaterFoodNeed = plantEaterFoodNeed;
    }

    public void setPlantReproductionProbability(double plantReproductionProbability) {
        this.plantReproductionProbability = plantReproductionProbability;
    }

    public void setFloorNewPlantSize(int floorNewPlantSize) {
        this.floorNewPlantSize = floorNewPlantSize;
    }

    public void setCeilingNewPlantSize(int ceilingNewPlantSize) {
        this.ceilingNewPlantSize = ceilingNewPlantSize;
    }

    public void setPlantEaterReproductionProbability(double plantEaterReproductionProbability) {
        this.plantEaterReproductionProbability = plantEaterReproductionProbability;
    }

    public void setFloorNewPlantEaterSize(int floorNewPlantEaterSize) {
        this.floorNewPlantEaterSize = floorNewPlantEaterSize;
    }

    public void setCeilingNewPlantEaterSize(int ceilingNewPlantEaterSize) {
        this.ceilingNewPlantEaterSize = ceilingNewPlantEaterSize;
    }

    public void setMeatEaterReproductionProbability(double meatEaterReproductionProbability) {
        this.meatEaterReproductionProbability = meatEaterReproductionProbability;
    }

    @Override
    public String toString() {
        return "ParameterRecord{" +
                "lowerBoundNumPlantsToChew=" + lowerBoundNumPlantsToChew +
                ", upperBoundNumPlantsToChew=" + upperBoundNumPlantsToChew +
                ", amountOfPlantMaxChewed=" + amountOfPlantMaxChewed +
                ", percentageOfFoodNeedToChew=" + percentageOfFoodNeedToChew +
                ", floorOnChewAmount=" + floorOnChewAmount +
                ", meatEaterSize=" + meatEaterSize +
                ", meatEaterGrowthRate=" + meatEaterGrowthRate +
                ", meatEaterFoodNeed=" + meatEaterFoodNeed +
                ", probabilityOldAgeDeathPlantEater=" + probabilityOldAgeDeathPlantEater +
                ", probabilityOldAgeDeathMeatEater=" + probabilityOldAgeDeathMeatEater +
                ", probabilityOldAgeDeathPlant=" + probabilityOldAgeDeathPlant +
                ", probabilityOldAgeDeathCreature=" + probabilityOldAgeDeathCreature +
                ", lifespanPlant=" + lifespanPlant +
                ", lifespanPlantEater=" + lifespanPlantEater +
                ", lifespanMeatEater=" + lifespanMeatEater +
                ", numMeatEaters=" + numMeatEaters +
                ", updateFoodNeedPercentage=" + updateFoodNeedPercentage +
                ", meatEaterProbabilityToCatch=" + meatEaterProbabilityToCatch +
                ", lowerBoundNumPlantEatersEat=" + lowerBoundNumPlantEatersEat +
                ", upperBoundNumPlantEatersEat=" + upperBoundNumPlantEatersEat +
                ", numPlantEaters=" + numPlantEaters +
                ", numPlants=" + numPlants +
                ", plantSizeLowerBound=" + plantSizeLowerBound +
                ", plantSizeUpperBound=" + plantSizeUpperBound +
                ", plantGrowthRate=" + plantGrowthRate +
                ", plantEaterSizeLowerBound=" + plantEaterSizeLowerBound +
                ", plantEaterSizeUpperBound=" + plantEaterSizeUpperBound +
                ", plantEaterGrowthRate=" + plantEaterGrowthRate +
                ", plantEaterFoodNeed=" + plantEaterFoodNeed +
                ", plantReproductionProbability=" + plantReproductionProbability +
                ", floorNewPlantSize=" + floorNewPlantSize +
                ", ceilingNewPlantSize=" + ceilingNewPlantSize +
                ", plantEaterReproductionProbability=" + plantEaterReproductionProbability;

    }
}
