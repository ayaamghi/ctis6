package edu.guilford.cardgame.Backend.DataAnalysis;

import edu.guilford.cardgame.Backend.Params.ParameterRecord;

import java.util.Map;

public class BatchSimulationAnalyzer {

    Map<ParameterRecord, SimulationResults> results;


    public BatchSimulationAnalyzer(Map<ParameterRecord, SimulationResults> results) {
        this.results = results;
    }


}
