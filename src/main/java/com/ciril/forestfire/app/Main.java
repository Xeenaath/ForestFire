package com.ciril.forestfire.app;

import com.ciril.forestfire.config.Config;
import com.ciril.forestfire.simulation.FireSimulation;
import com.ciril.forestfire.model.Forest;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Config config = mapper.readValue(new File("src/main/resources/data.json"), Config.class);
            Forest forest = new Forest(config.getForestConfig().getHeight(), config.getForestConfig().getWidth());
            FireSimulation fireSimulation = new FireSimulation(forest, config.getFireSimulationConfig().getPropagationProbability(), config.getFireSimulationConfig().getInitialFires());
            while (forest.hasActiveFires()) {
                forest.display();
                TimeUnit.SECONDS.sleep(1);
                fireSimulation.simulateStep();
            }
            forest.display();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
