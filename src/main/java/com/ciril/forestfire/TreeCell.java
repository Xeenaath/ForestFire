package com.ciril.forestfire;

public class TreeCell implements Cell {
    private TreeCellState state = TreeCellState.HEALTHY;
    private boolean willBurnNextStep = false;


    @Override
    public boolean isBurning() {
        return this.state == TreeCellState.IS_BURNING;
    }

    @Override
    public boolean isBurnable() {
        return true;
    }

    @Override
    public void prepareToIgnite() {
        if (this.state == TreeCellState.HEALTHY) {
            this.willBurnNextStep = true;
        }
    }

    @Override
    public void advanceStateForNextStep() {
        if (this.state == TreeCellState.HEALTHY && this.willBurnNextStep) {
            this.state = TreeCellState.IS_BURNING;
        } else if (this.state == TreeCellState.IS_BURNING) {
            this.state = TreeCellState.BURNT;
        }
    }

    @Override
    public String toString() {
        return switch (this.state) {
            case HEALTHY -> "\uD83C\uDF33 ";
            case IS_BURNING -> "\uD83D\uDD25 ";
            case BURNT -> "\uD83C\uDF2B️ ";
        };
    }

    public void setState(TreeCellState state) {
        this.state = state;
    }
}
