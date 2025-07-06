package com.ciril.forestfire;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        ObjectMapper mapper = new ObjectMapper();
        try {
            Forest forest = mapper.readValue(new File("src/main/resources/forest.json"), Forest.class);
            forest.initialize();
            FireSimulation fireSimulation = new FireSimulation(forest);
            fireSimulation.applyInitialFires();
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
