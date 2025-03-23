package edu.guilford.cardgame.Backend;

import edu.guilford.cardgame.Backend.Data.SimulationResults;
import edu.guilford.cardgame.Backend.Params.ParameterRecord ;


public class EcoRunner {

    public static void main(String[] args) {
        for (int i = 0; i < 1; i++) {
            Ecosystem ecosystem2 = new Ecosystem(new ParameterRecord());
            SimulationResults results2 = ecosystem2.runSimulation();
            System.out.println(results2);
        }




    }


}
