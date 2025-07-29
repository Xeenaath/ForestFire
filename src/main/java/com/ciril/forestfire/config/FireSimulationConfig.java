package com.ciril.forestfire.config;

import java.util.List;

public class FireSimulationConfig {
    private double propagationProbability;
    private List<int[]> initialFires;

    public double getPropagationProbability() {
        return propagationProbability;
    }

    public void setPropagationProbability(double propagationProbability) {
        this.propagationProbability = propagationProbability;
    }

    public List<int[]> getInitialFires() {
        return initialFires;
    }

    public void setInitialFires(List<int[]> initialFires) {
        this.initialFires = initialFires;
    }
}
