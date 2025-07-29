package com.ciril.forestfire.model;

public class TreeCell implements ICell {
    private TreeCellStateEnum state = TreeCellStateEnum.HEALTHY;
    private boolean willBurnNextStep = false;
    private Position position;


    @Override
    public boolean isBurning() {
        return this.state == TreeCellStateEnum.IS_BURNING;
    }

    //TreeCell is burnable only if it is not already burning or burnt.
    @Override
    public boolean isBurnable() {
        return !(this.state.equals(TreeCellStateEnum.IS_BURNING) || this.state.equals(TreeCellStateEnum.BURNT));
    }



    @Override
    public void burn() {
        if(this.state == TreeCellStateEnum.HEALTHY) {
            this.state = TreeCellStateEnum.IS_BURNING;
        }
    }

    @Override
    public void hasBurnt() {
        if (this.state ==  TreeCellStateEnum.IS_BURNING) {
            this.state = TreeCellStateEnum.BURNT;
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

    public void setState(TreeCellStateEnum state) {
        this.state = state;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position){
        this.position = position;
    }

}
