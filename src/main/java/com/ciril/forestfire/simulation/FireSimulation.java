package com.ciril.forestfire.simulation;

import com.ciril.forestfire.model.Forest;
import com.ciril.forestfire.model.ICell;
import com.ciril.forestfire.model.TreeCell;
import com.ciril.forestfire.model.TreeCellStateEnum;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class FireSimulation {
    private final Forest forest;
    private final double propagationProbability;
    private final List<int[]> initialFires;
    private final Random random = new Random();

    public FireSimulation(Forest forest, double propagationProbability, List<int[]> initialFires) {
        this.forest = forest;
        this.propagationProbability = propagationProbability;
        this.initialFires = initialFires;
        applyInitialFires();
    }

    private void applyInitialFires() {
        for(int[] position : this.initialFires) {
            int row = position[0];
            int col = position[1];
            if (row >= 0 && row < this.forest.getHeight() && col >= 0 && col < this.forest.getWidth()) {
                ICell cellToBurn = this.forest.getCellInForest(row,col);
                if (cellToBurn instanceof TreeCell treeCell) {
                    treeCell.setState(TreeCellStateEnum.IS_BURNING);
                    forest.getBurningCells().add(cellToBurn);
                }
            }
        }
    }



    public void simulateStep() {
        List<ICell> nextBurningCells = new LinkedList<>();

        if(forest.hasActiveFires()){
            for(ICell burningCell : forest.getBurningCells()){
                nextBurningCells.addAll(simulateNextBurningCellsFromBurningCell(burningCell, forest));
                burningCell.hasBurnt();
            }
        }

        forest.setBurningCells(nextBurningCells);

    }

    private List<ICell> simulateNextBurningCellsFromBurningCell(ICell burningCell, Forest forest){
        int[][] DIRECTIONS = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        List<ICell> nextBurningCells = new LinkedList<>();

        for (int[] direction : DIRECTIONS) {
            int neighborRow = burningCell.getPosition().getX() + direction[0];
            int neighborCol = burningCell.getPosition().getY() + direction[1];

            //check if cell is in grid, burnable and will burn
            if (forest.isCellInForest(neighborRow, neighborCol) &&
                    forest.getCellInForest(neighborRow, neighborCol).isBurnable() &&
                    random.nextDouble() < this.propagationProbability) {
                ICell nextBurningCell = forest.getCellInForest(neighborRow, neighborCol);
                nextBurningCell.burn();
                nextBurningCells.add(nextBurningCell);

            }
        }
        return nextBurningCells;
    }

}
