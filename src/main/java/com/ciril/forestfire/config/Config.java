package com.ciril.forestfire.config;

public class Config {
    private ForestConfig forestConfig;
    private FireSimulationConfig fireSimulationConfig;

    public ForestConfig getForestConfig() {
        return forestConfig;
    }

    public void setForestConfig(ForestConfig forestConfig) {
        this.forestConfig = forestConfig;
    }

    public FireSimulationConfig getFireSimulationConfig() {
        return fireSimulationConfig;
    }

    public void setFireSimulationConfig(FireSimulationConfig fireSimulationConfig) {
        this.fireSimulationConfig = fireSimulationConfig;
    }
}
