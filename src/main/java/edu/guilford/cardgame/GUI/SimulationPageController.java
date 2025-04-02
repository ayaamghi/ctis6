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

    @FXML private Slider plantDeathProbabilitySlider;

    @FXML private Label plantDeathPercentageLabel;

    @FXML private TextField lifeSpanPlant;





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
            System.out.println(SessionManager.getCurrentUser().getName());

        });


        plantDeathProbabilitySlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            double value = newValue.doubleValue();
            plantDeathPercentageLabel.setText(String.format("%.2f", value));
        });


        plantDeathProbabilitySlider.setValue(parameterRecord.probabilityOldAgeDeathPlant());

        lifeSpanPlant.setPromptText("Plant Life Span (" + parameterRecord.lifespanPlant() + ")");

        lifeSpanPlant.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                int value = Integer.parseInt(newValue);
                parameterRecord.setLifespanPlant(value);
            } catch (NumberFormatException e) {
                // Handle invalid input
                System.out.println("Invalid input for plant life span: " + newValue);
            }
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