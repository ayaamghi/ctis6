package edu.guilford.cardgame.GUI;

import edu.guilford.cardgame.Backend.Accounts.AccountJSONManager;
import edu.guilford.cardgame.Backend.Accounts.SessionManager;
import edu.guilford.cardgame.Backend.DataAnalysis.Day;
import edu.guilford.cardgame.Backend.DataAnalysis.SimulationResults;
import edu.guilford.cardgame.Backend.Ecosystem;
import edu.guilford.cardgame.Backend.Params.ParameterRecord;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.CacheHint;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.function.ToIntFunction;
import java.util.List;
import java.util.stream.Collectors;

public class SimulationPageController {
    @FXML private ScatterChart<Number, Number> plantGraph;
    @FXML private ScatterChart<Number, Number> plantEaterGraph;
    @FXML private ScatterChart<Number, Number> meatEaterGraph;
    @FXML private Button graphButton;

    //plants
    @FXML private Slider plantDeathProbabilitySlider;
    @FXML private Label plantDeathPercentageLabel;
    @FXML private TextField lifeSpanPlant;


    //plant eaters

    @FXML private Slider amountOfPlantMaxChewed;
    @FXML private Label amountOfPlantMaxChewedLabel;

    @FXML private Slider percentageOfFoodNeedToChew;
    @FXML private Label percentageOfFoodNeedToChewLabel;

    @FXML private Slider floorOnChewAmount;
    @FXML private Label floorOnChewAmountLabel;

    @FXML private Slider probabilityOldAgeDeathPlantEater;
    @FXML private Label probabilityOldAgeDeathPlantEaterLabel;

    @FXML private Slider lowerBoundNumPlantsToChew;
    @FXML private Label lowerBoundNumPlantsToChewLabel;

    @FXML private Slider upperBoundNumPlantsToChew;
    @FXML private Label upperBoundNumPlantsToChewLabel;

    @FXML private TextField lifeSpanPlantEater;

    //meat eaters

    @FXML private TextField lifeSpanMeatEater;

    @FXML private Slider probabilityOldAgeDeathMeatEater;
    @FXML private Label probabilityOldAgeDeathMeatEaterLabel;

    @FXML private Slider lowerBoundNumPlantEatersEat;
    @FXML private Label lowerBoundNumPlantEatersEatLabel;

    @FXML private Slider upperBoundNumPlantEatersEat;
    @FXML private Label upperBoundNumPlantEatersEatLabel;

    @FXML private Slider meatEaterProbabilityToCatch;
    @FXML private Label meatEaterProbabilityToCatchLabel;

    //stuff for the overall simulation

    @FXML private Slider plantReproductionProbability;
    @FXML private Label plantReproductionProbabilityLabel;

    @FXML private Slider plantEaterReproductionProbability;
    @FXML private Label plantEaterReproductionProbabilityLabel;

    @FXML private Slider meatEaterReproductionProbability;
    @FXML private Label meatEaterReproductionProbabilityLabel;

    @FXML private TextField plantSizeLowerBound;
    @FXML private TextField plantSizeUpperBound;
    @FXML private TextField plantGrowthRate;

    @FXML private TextField plantEaterSizeLowerBound;
    @FXML private TextField plantEaterSizeUpperBound;
    @FXML private TextField plantEaterGrowthRate;
    @FXML private TextField plantEaterFoodNeed;

    @FXML private TextField meatEaterSize;
    @FXML private TextField meatEaterGrowthRate;
    @FXML private TextField meatEaterFoodNeed;

    @FXML private TextField numPlants;
    @FXML private TextField numPlantEaters;
    @FXML private TextField numMeatEaters;


    @FXML private TextField floorNewPlantSize;
    @FXML private TextField ceilingNewPlantSize;

    @FXML private TextField floorNewPlantEaterSize;
    @FXML private TextField ceilingNewPlantEaterSize;
    //critters
    @FXML private Slider updateFoodNeedPercentage;
    @FXML private Label updateFoodNeedPercentageLabel;


    @FXML private Button saveButton;
    @FXML
    public void initialize() throws IOException {
        // Set up the axes for the scatter charrts

        ParameterRecord parameterRecord = AccountJSONManager.getUserParameterRecord("src/main/resources/Users/" + SessionManager.getCurrentUser().getName() + ".json");
        Ecosystem ecosystem = new Ecosystem(parameterRecord);

        //TODO change this to account for the fact parameters will be changed
        graphButton.setOnMouseClicked(event -> {
            clearGraphs();
            ecosystem.resetSimulation();
            SimulationResults results = ecosystem.runSimulation();
            plotData(results);
            System.out.println(results);

        });


        saveButton.setOnMouseClicked(event -> {

            try {
                System.out.println("Saving parameters...");
                System.out.println(parameterRecord);
                AccountJSONManager.updateUserParameterRecord("src/main/resources/Users/" + SessionManager.getCurrentUser().getName() + ".json", parameterRecord);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


        //plants
        plantDeathProbabilitySlider.setValue(parameterRecord.probabilityOldAgeDeathPlant());
        plantDeathPercentageLabel.setText(String.valueOf(parameterRecord.probabilityOldAgeDeathPlant()));
        plantDeathProbabilitySlider.valueProperty().addListener((observable, oldValue, newValue) -> {

            double value = newValue.doubleValue();
            plantDeathPercentageLabel.setText(String.format("%.2f", value));
            parameterRecord.setProbabilityOldAgeDeathPlant(value);
        });
        lifeSpanPlant.setPromptText("Plant Life Span (" + parameterRecord.lifespanPlant() + ")");



        lifeSpanPlant.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                lifeSpanPlant.setStyle("-fx-border-color: black;");
                int value = Integer.parseInt(newValue);
                parameterRecord.setLifespanPlant(value);
                System.out.println(parameterRecord.lifespanPlant());
            } catch (NumberFormatException e) {
                // Handle invalid input
                System.out.println("Invalid input for plant life span: " + newValue);
                //set border color red
                lifeSpanPlant.setStyle("-fx-border-color: red;");
            }
        });
// Amount Of Plant Max Chewed
        amountOfPlantMaxChewed.setValue(parameterRecord.amountOfPlantMaxChewed());
        amountOfPlantMaxChewedLabel.setText(String.format("%.2f", parameterRecord.amountOfPlantMaxChewed()));
        amountOfPlantMaxChewed.valueProperty().addListener((observable, oldValue, newValue) -> {
            double value = newValue.doubleValue();
            amountOfPlantMaxChewedLabel.setText(String.format("%.2f", value));
            parameterRecord.setAmountOfPlantMaxChewed(value);
        });

// Percentage Of Food Need To Chew
        percentageOfFoodNeedToChew.setValue(parameterRecord.percentageOfFoodNeedToChew());
        percentageOfFoodNeedToChewLabel.setText(String.valueOf(parameterRecord.percentageOfFoodNeedToChew()));
        percentageOfFoodNeedToChew.valueProperty().addListener((observable, oldValue, newValue) -> {
            double value = newValue.doubleValue();
            percentageOfFoodNeedToChewLabel.setText(String.format("%.2f", value));
            parameterRecord.setPercentageOfFoodNeedToChew(value);
        });

// Floor On Chew Amount

        floorOnChewAmount.setValue(parameterRecord.floorOnChewAmount());
        floorOnChewAmountLabel.setText(String.format("%.2f", parameterRecord.floorOnChewAmount()));
        floorOnChewAmount.valueProperty().addListener((observable, oldValue, newValue) -> {
            double value = newValue.doubleValue();
            floorOnChewAmountLabel.setText(String.format("%.2f", value));
            parameterRecord.setFloorOnChewAmount(value);
        });

// Probability Old Age Death Plant Eater
        probabilityOldAgeDeathPlantEater.setValue(parameterRecord.probabilityOldAgeDeathPlantEater());
        probabilityOldAgeDeathPlantEaterLabel.setText(String.format("%.2f", parameterRecord.probabilityOldAgeDeathPlantEater()));
        probabilityOldAgeDeathPlantEater.valueProperty().addListener((observable, oldValue, newValue) -> {
            double value = newValue.doubleValue();
            probabilityOldAgeDeathPlantEaterLabel.setText(String.format("%.2f", value));
            parameterRecord.setProbabilityOldAgeDeathPlantEater(value);
        });

// Lower Bound Num Plants To Chew
        lowerBoundNumPlantsToChew.setValue(parameterRecord.lowerBoundNumPlantsToChew());
        lowerBoundNumPlantsToChewLabel.setText(String.format("%.2f", parameterRecord.lowerBoundNumPlantsToChew()));
        lowerBoundNumPlantsToChew.valueProperty().addListener((observable, oldValue, newValue) -> {
            double value = newValue.doubleValue();
            lowerBoundNumPlantsToChewLabel.setText(String.format("%.2f", value));
            parameterRecord.setLowerBoundNumPlantsToChew(value);
        });

// Upper Bound Num Plants To Chew
        upperBoundNumPlantsToChew.setValue(parameterRecord.upperBoundNumPlantsToChew());
        upperBoundNumPlantsToChewLabel.setText(String.format("%.2f", parameterRecord.upperBoundNumPlantsToChew()));
        upperBoundNumPlantsToChew.valueProperty().addListener((observable, oldValue, newValue) -> {
            double value = newValue.doubleValue();
            upperBoundNumPlantsToChewLabel.setText(String.format("%.2f", value));
            parameterRecord.setUpperBoundNumPlantsToChew(value);
        });

// Life Span Plant Eater TextField
        lifeSpanPlantEater.setText(String.valueOf(parameterRecord.lifespanPlantEater()));
        lifeSpanPlantEater.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                lifeSpanPlantEater.setStyle("-fx-border-color: black;");
                int value = Integer.parseInt(newValue);
                parameterRecord.setLifespanPlantEater(value);
                System.out.println("Plant Eater Life Span: " + parameterRecord.lifespanPlantEater());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input for plant eater life span: " + newValue);
                lifeSpanPlantEater.setStyle("-fx-border-color: red;");
            }
        });

// Meat Eater Life Span TextField
        lifeSpanMeatEater.setText(String.valueOf(parameterRecord.lifespanMeatEater()));
        lifeSpanMeatEater.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                lifeSpanMeatEater.setStyle("-fx-border-color: black;");
                int value = Integer.parseInt(newValue);
                parameterRecord.setLifespanMeatEater(value);
                System.out.println("Meat Eater Life Span: " + parameterRecord.lifespanMeatEater());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input for meat eater life span: " + newValue);
                lifeSpanMeatEater.setStyle("-fx-border-color: red;");
            }
        });

// Probability Old Age Death Meat Eater
        probabilityOldAgeDeathMeatEaterLabel.setText(String.format("%.2f", parameterRecord.probabilityOldAgeDeathMeatEater()));
        probabilityOldAgeDeathMeatEater.setValue(parameterRecord.probabilityOldAgeDeathMeatEater());
        probabilityOldAgeDeathMeatEater.valueProperty().addListener((observable, oldValue, newValue) -> {
            double value = newValue.doubleValue();
            probabilityOldAgeDeathMeatEaterLabel.setText(String.format("%.2f", value));
            parameterRecord.setProbabilityOldAgeDeathMeatEater(value);
        });

// Lower Bound Num Plant Eaters Eat
        //TODO fix this later maybe
        lowerBoundNumPlantEatersEatLabel.setText(String.format(String.valueOf(parameterRecord.lowerBoundNumPlantEatersEat())));
        lowerBoundNumPlantEatersEat.setValue(parameterRecord.lowerBoundNumPlantEatersEat());
        lowerBoundNumPlantEatersEat.valueProperty().addListener((observable, oldValue, newValue) -> {
            int value = (int) Math.round(newValue.doubleValue());
            lowerBoundNumPlantEatersEatLabel.setText(String.format("%d", value));
            parameterRecord.setLowerBoundNumPlantEatersEat(value);
        });

// Upper Bound Num Plant Eaters Eat
        //TODO fix this later maybe
        upperBoundNumPlantEatersEatLabel.setText(String.format(String.valueOf( parameterRecord.upperBoundNumPlantEatersEat())));
        upperBoundNumPlantEatersEat.setValue(parameterRecord.upperBoundNumPlantEatersEat());
        upperBoundNumPlantEatersEat.valueProperty().addListener((observable, oldValue, newValue) -> {
            int value = (int) Math.round(newValue.doubleValue());
            upperBoundNumPlantEatersEatLabel.setText(String.format("%d", value));
            parameterRecord.setUpperBoundNumPlantEatersEat(value);
        });

// Meat Eater Probability To Catch

        meatEaterProbabilityToCatchLabel.setText(String.format("%.2f", parameterRecord.meatEaterProbabilityToCatch()));
        meatEaterProbabilityToCatch.setValue(parameterRecord.meatEaterProbabilityToCatch());
        meatEaterProbabilityToCatch.valueProperty().addListener((observable, oldValue, newValue) -> {
            double value = newValue.doubleValue();
            meatEaterProbabilityToCatchLabel.setText(String.format("%.2f", value));
            parameterRecord.setMeatEaterProbabilityToCatch(value);
        });
// Reproduction Probabilities

// Plant Reproduction Probability
        plantReproductionProbability.setValue(parameterRecord.plantReproductionProbability());
        plantReproductionProbabilityLabel.setText(String.format("%.2f", parameterRecord.plantReproductionProbability()));
        plantReproductionProbability.valueProperty().addListener((observable, oldValue, newValue) -> {
            double value = newValue.doubleValue();
            plantReproductionProbabilityLabel.setText(String.format("%.2f", value));
            parameterRecord.setPlantReproductionProbability(value);
        });

// Plant Eater Reproduction Probability
        plantEaterReproductionProbability.setValue(parameterRecord.plantEaterReproductionProbability());
        plantEaterReproductionProbabilityLabel.setText(String.format("%.2f", parameterRecord.plantEaterReproductionProbability()));
        plantEaterReproductionProbability.valueProperty().addListener((observable, oldValue, newValue) -> {
            double value = newValue.doubleValue();
            plantEaterReproductionProbabilityLabel.setText(String.format("%.2f", value));
            parameterRecord.setPlantEaterReproductionProbability(value);
        });

// Meat Eater Reproduction Probability
        meatEaterReproductionProbability.setValue(parameterRecord.meatEaterReproductionProbability());
        meatEaterReproductionProbabilityLabel.setText(String.format("%.2f", parameterRecord.meatEaterReproductionProbability()));
        meatEaterReproductionProbability.valueProperty().addListener((observable, oldValue, newValue) -> {
            double value = newValue.doubleValue();
            meatEaterReproductionProbabilityLabel.setText(String.format("%.2f", value));
            parameterRecord.setMeatEaterReproductionProbability(value);
        });

// Plant Parameters

// Plant Size Lower Bound
        plantSizeLowerBound.setText(String.valueOf(parameterRecord.plantSizeLowerBound()));
        plantSizeLowerBound.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                plantSizeLowerBound.setStyle("-fx-border-color: black;");
                int value = Integer.parseInt(newValue);
                parameterRecord.setPlantSizeLowerBound(value);
            } catch (NumberFormatException e) {
                plantSizeLowerBound.setStyle("-fx-border-color: red;");
            }
        });

// Plant Size Upper Bound
        plantSizeUpperBound.setText(String.valueOf(parameterRecord.plantSizeUpperBound()));
        plantSizeUpperBound.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                plantSizeUpperBound.setStyle("-fx-border-color: black;");
                int value = Integer.parseInt(newValue);
                parameterRecord.setPlantSizeUpperBound(value);
            } catch (NumberFormatException e) {
                plantSizeUpperBound.setStyle("-fx-border-color: red;");
            }
        });

// Plant Growth Rate
        plantGrowthRate.setText(String.valueOf(parameterRecord.plantGrowthRate()));
        plantGrowthRate.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                plantGrowthRate.setStyle("-fx-border-color: black;");
                int value = Integer.parseInt(newValue);
                parameterRecord.setPlantGrowthRate(value);
            } catch (NumberFormatException e) {
                plantGrowthRate.setStyle("-fx-border-color: red;");
            }
        });

// Plant Eater Parameters

// Plant Eater Size Lower Bound
        plantEaterSizeLowerBound.setText(String.valueOf(parameterRecord.plantEaterSizeLowerBound()));
        plantEaterSizeLowerBound.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                plantEaterSizeLowerBound.setStyle("-fx-border-color: black;");
                int value = Integer.parseInt(newValue);
                parameterRecord.setPlantEaterSizeLowerBound(value);
            } catch (NumberFormatException e) {
                plantEaterSizeLowerBound.setStyle("-fx-border-color: red;");
            }
        });

// Plant Eater Size Upper Bound
        plantEaterSizeUpperBound.setText(String.valueOf(parameterRecord.plantEaterSizeUpperBound()));
        plantEaterSizeUpperBound.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                plantEaterSizeUpperBound.setStyle("-fx-border-color: black;");
                int value = Integer.parseInt(newValue);
                parameterRecord.setPlantEaterSizeUpperBound(value);
            } catch (NumberFormatException e) {
                plantEaterSizeUpperBound.setStyle("-fx-border-color: red;");
            }
        });

// Plant Eater Growth Rate
        plantEaterGrowthRate.setText(String.valueOf(parameterRecord.plantEaterGrowthRate()));
        plantEaterGrowthRate.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                plantEaterGrowthRate.setStyle("-fx-border-color: black;");
                int value = Integer.parseInt(newValue);
                parameterRecord.setPlantEaterGrowthRate(value);
            } catch (NumberFormatException e) {
                plantEaterGrowthRate.setStyle("-fx-border-color: red;");
            }
        });

// Plant Eater Food Need
        plantEaterFoodNeed.setText(String.valueOf(parameterRecord.plantEaterFoodNeed()));
        plantEaterFoodNeed.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                plantEaterFoodNeed.setStyle("-fx-border-color: black;");
                int value = Integer.parseInt(newValue);
                parameterRecord.setPlantEaterFoodNeed(value);
            } catch (NumberFormatException e) {
                plantEaterFoodNeed.setStyle("-fx-border-color: red;");
            }
        });

// Meat Eater Parameters

// Meat Eater Size        meatEaterSize.setPromptText("Meat Eater Size (" + parameterRecord.meatEaterSize() + ")");
        meatEaterSize.setText(String.valueOf(parameterRecord.meatEaterSize()));
        meatEaterSize.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                meatEaterSize.setStyle("-fx-border-color: black;");
                int value = Integer.parseInt(newValue);
                parameterRecord.setMeatEaterSize(value);
            } catch (NumberFormatException e) {
                meatEaterSize.setStyle("-fx-border-color: red;");
            }
        });

// Meat Eater Growth Rate
        meatEaterGrowthRate.setText(String.valueOf(parameterRecord.meatEaterGrowthRate()));
        meatEaterGrowthRate.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                meatEaterGrowthRate.setStyle("-fx-border-color: black;");
                int value = Integer.parseInt(newValue);
                parameterRecord.setMeatEaterGrowthRate(value);
            } catch (NumberFormatException e) {
                meatEaterGrowthRate.setStyle("-fx-border-color: red;");
            }
        });

// Meat Eater Food Need
        meatEaterFoodNeed.setText(String.valueOf(parameterRecord.meatEaterFoodNeed()));
        meatEaterFoodNeed.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                meatEaterFoodNeed.setStyle("-fx-border-color: black;");
                int value = Integer.parseInt(newValue);
                parameterRecord.setMeatEaterFoodNeed(value);
            } catch (NumberFormatException e) {
                meatEaterFoodNeed.setStyle("-fx-border-color: red;");
            }
        });

// Count Parameters

// Number of Plants
        numPlants.setText(String.valueOf(parameterRecord.numPlants()));
        numPlants.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                numPlants.setStyle("-fx-border-color: black;");
                int value = Integer.parseInt(newValue);
                parameterRecord.setNumPlants(value);
            } catch (NumberFormatException e) {
                numPlants.setStyle("-fx-border-color: red;");
            }
        });

// Number of Plant Eaters
        numPlantEaters.setText(String.valueOf(parameterRecord.numPlantEaters()));
        numPlantEaters.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                numPlantEaters.setStyle("-fx-border-color: black;");
                int value = Integer.parseInt(newValue);
                parameterRecord.setNumPlantEaters(value);
            } catch (NumberFormatException e) {
                numPlantEaters.setStyle("-fx-border-color: red;");
            }
        });

// Number of Meat Eaters
        numMeatEaters.setText(String.valueOf(parameterRecord.numMeatEaters()));
        numMeatEaters.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                numMeatEaters.setStyle("-fx-border-color: black;");
                int value = Integer.parseInt(newValue);
                parameterRecord.setNumMeatEaters(value);
            } catch (NumberFormatException e) {
                numMeatEaters.setStyle("-fx-border-color: red;");
            }
        });

// New Plant/Plant Eater Sizes

// Floor New Plant Size
        floorNewPlantSize.setText(String.valueOf(parameterRecord.floorNewPlantSize()));
        floorNewPlantSize.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                floorNewPlantSize.setStyle("-fx-border-color: black;");
                int value = Integer.parseInt(newValue);
                parameterRecord.setFloorNewPlantSize(value);
            } catch (NumberFormatException e) {
                floorNewPlantSize.setStyle("-fx-border-color: red;");
            }
        });

// Ceiling New Plant Size
        ceilingNewPlantSize.setText(String.valueOf(parameterRecord.ceilingNewPlantSize()));
        ceilingNewPlantSize.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                ceilingNewPlantSize.setStyle("-fx-border-color: black;");
                int value = Integer.parseInt(newValue);
                parameterRecord.setCeilingNewPlantSize(value);
            } catch (NumberFormatException e) {
                ceilingNewPlantSize.setStyle("-fx-border-color: red;");
            }
        });

// Floor New Plant Eater Size
        floorNewPlantEaterSize.setText(String.valueOf(parameterRecord.floorNewPlantEaterSize()));
        floorNewPlantEaterSize.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                floorNewPlantEaterSize.setStyle("-fx-border-color: black;");
                int value = Integer.parseInt(newValue);
                parameterRecord.setFloorNewPlantEaterSize(value);
            } catch (NumberFormatException e) {
                floorNewPlantEaterSize.setStyle("-fx-border-color: red;");
            }
        });

// Ceiling New Plant Eater Size
        ceilingNewPlantEaterSize.setText(String.valueOf(parameterRecord.ceilingNewPlantEaterSize()));
        ceilingNewPlantEaterSize.setPromptText("Ceiling New Plant Eater Size (" + parameterRecord.ceilingNewPlantEaterSize() + ")");
        ceilingNewPlantEaterSize.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                ceilingNewPlantEaterSize.setStyle("-fx-border-color: black;");
                int value = Integer.parseInt(newValue);
                parameterRecord.setCeilingNewPlantEaterSize(value);
            } catch (NumberFormatException e) {
                ceilingNewPlantEaterSize.setStyle("-fx-border-color: red;");
            }
        });

// Critter Parameter: Update Food Need Percentage
        updateFoodNeedPercentageLabel.setText(String.format("%.2f", parameterRecord.updateFoodNeedPercentage()));
        updateFoodNeedPercentage.setValue(parameterRecord.updateFoodNeedPercentage());
        updateFoodNeedPercentage.valueProperty().addListener((observable, oldValue, newValue) -> {
            double value = newValue.doubleValue();
            updateFoodNeedPercentageLabel.setText(String.format("%.2f", value));
            parameterRecord.setUpdateFoodNeedPercentage(value);
        });


    }

    private void clearGraphs() {
        plantGraph.getData().clear();
        plantEaterGraph.getData().clear();
        meatEaterGraph.getData().clear();
    }



    private void plotData(SimulationResults results) {

        //mass series
        XYChart.Series<Number, Number> plantMassSeries = createScatterSeries("Plant Mass", results, day -> (int) day.getPlantMass());
        XYChart.Series<Number, Number> plantEaterMassSeries = createScatterSeries("Plant Eater Mass", results, day -> (int) day.getPlantEaterMass());
        XYChart.Series<Number, Number> meatEaterMassSeries = createScatterSeries("Meat Eater Mass", results, day -> (int) day.getMeatEaterMass());

        NumberAxis plantYAxis = (NumberAxis) plantGraph.getYAxis();
        NumberAxis plantEaterYAxis = (NumberAxis) plantEaterGraph.getYAxis();
        NumberAxis meatEaterYAxis = (NumberAxis) meatEaterGraph.getYAxis();


//
//        plantGraph.getData().add(plantSeries);
//        plantEaterGraph.getData().add(plantEaterSeries);
//        meatEaterGraph.getData().add(meatEaterSeries);

        plantGraph.getData().add(plantMassSeries);
        plantEaterGraph.getData().add(plantEaterMassSeries);
        meatEaterGraph.getData().add(meatEaterMassSeries);
    }

    private void setAxisBounds(NumberAxis axis, double lower, double upper) {
        axis.setAutoRanging(false);
        axis.setLowerBound(lower);
        axis.setUpperBound(upper);
    }

    private double calculateMedian(XYChart.Series<Number, Number> series) {
        double median = 0;
        double range = 0;

         List<Double> values = series.getData().stream()
                .map(data -> data.getYValue().doubleValue())
                .sorted()
                .collect(Collectors.toList());
        int size = values.size();
        if (size % 2 == 0) {
            median =  (values.get(size / 2) + values.get(size / 2 - 1)) / 2.0;
        } else {
            median =  values.get(size / 2);
        }

        range = values.get(size - 1) - values.get(0);

        return median;
    }




    private XYChart.Series<Number, Number> createScatterSeries(String name, SimulationResults results,
                                                               ToIntFunction<Day> valueExtractor) {
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName(name);

        List<XYChart.Data<Number, Number>> dataPoints = results.getDays().stream()
                .map(day -> new XYChart.Data<Number, Number>(
                        day.getDay(),
                        valueExtractor.applyAsInt(day)
                ))
                .collect(Collectors.toList());

        series.getData().addAll(dataPoints);
        return series;
    }
}