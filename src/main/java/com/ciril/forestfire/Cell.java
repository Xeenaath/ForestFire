package com.ciril.forestfire;

public interface Cell {
    boolean isBurning();
    boolean isBurnable();
    void prepareToIgnite();
    void advanceStateForNextStep();
}
