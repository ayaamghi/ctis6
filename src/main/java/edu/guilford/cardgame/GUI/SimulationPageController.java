package edu.guilford.cardgame.GUI;

import edu.guilford.cardgame.Backend.DataAnalysis.Day;
import edu.guilford.cardgame.Backend.DataAnalysis.SimulationResults;
import edu.guilford.cardgame.Backend.Ecosystem;
import edu.guilford.cardgame.Backend.Params.ParameterRecord;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import java.io.IOException;
import java.util.function.ToIntFunction;
import java.util.List;
import java.util.stream.Collectors;

public class SimulationPageController {
    @FXML private ScatterChart<Number, Number> plantGraph;
    @FXML private ScatterChart<Number, Number> plantEaterGraph;
    @FXML private ScatterChart<Number, Number> meatEaterGraph;
    @FXML private Button graphButton;

    @FXML
    public void initialize() throws IOException {
        Ecosystem ecosystem = new Ecosystem(new ParameterRecord());
        SimulationResults results = ecosystem.runSimulation();

        graphButton.setOnMouseClicked(event -> {
            clearGraphs();
            plotData(results);
        });
    }

    private void clearGraphs() {
        plantGraph.getData().clear();
        plantEaterGraph.getData().clear();
        meatEaterGraph.getData().clear();
    }

    private void plotData(SimulationResults results) {
        // Create and add series
        XYChart.Series<Number, Number> plantSeries = createScatterSeries("Plants", results, Day::getPlantCount);
        XYChart.Series<Number, Number> plantEaterSeries = createScatterSeries("Plant Eaters", results, Day::getPlantEaterCount);
        XYChart.Series<Number, Number> meatEaterSeries = createScatterSeries("Meat Eaters", results, Day::getMeatEaterCount);

        NumberAxis plantYAxis = (NumberAxis) plantGraph.getYAxis();

        plantYAxis.setAutoRanging(false);
        plantYAxis.setLowerBound(1975);
        plantYAxis.setUpperBound(2025);
        plantGraph.getData().add(plantSeries);
        plantEaterGraph.getData().add(plantEaterSeries);
        meatEaterGraph.getData().add(meatEaterSeries);

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