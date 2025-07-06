package com.ciril.forestfire;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FireSimulation {
    private final Forest forest;
    private final Random random = new Random();

    public FireSimulation(Forest forest) {
        this.forest = forest;
    }

    public void applyInitialFires() {
        for(int[] position : this.forest.getInitialFires()) {
            int row = position[0];
            int col = position[1];
            if (row >= 0 && row < this.forest.getHeight() || col >= 0 && col < this.forest.getWidth()) {
                Cell cellToIgnite = this.forest.getGrid()[row][col];
                if (cellToIgnite instanceof TreeCell treeCell) {
                    treeCell.setState(TreeCellState.IS_BURNING);
                }
            }
        }
    }


    public void simulateStep() {
        List<int[]> cellPositionsToPrepareToIgnite = new ArrayList<>();
        int[] directionsRow = {-1, 1, 0, 0};
        int[] directionsCol = {0, 0, -1, 1};

        checkBurningTreeCellsAndPrepareToIgnite(directionsRow, directionsCol, cellPositionsToPrepareToIgnite);

        for(int[] cellPosition : cellPositionsToPrepareToIgnite) {
            int row = cellPosition[0];
            int col = cellPosition[1];
            this.forest.getGrid()[row][col].prepareToIgnite();
        }

        for (int i = 0; i < this.forest.getHeight(); i++) {
            for (int j = 0; j < this.forest.getWidth(); j++) {
                this.forest.getGrid()[i][j].advanceStateForNextStep();
            }
        }
    }

    private void checkBurningTreeCellsAndPrepareToIgnite(int[] directionsRow, int[] directionsCol, List<int[]> cellPositionsToPrepareToIgnite) {
        for (int i = 0; i < this.forest.getHeight(); i++) {
            for (int j = 0; j < this.forest.getWidth(); j++) {
                Cell currentCell = this.forest.getGrid()[i][j];
                if (currentCell.isBurning()) {
                    for (int x = 0; x < 4; x++) {
                        int neighborRow = i + directionsRow[x];
                        int neighborCol = j + directionsCol[x];
                        if (isNeighborCellInRange(neighborRow, neighborCol) && isNeighborCellGoingToIgnite(neighborRow, neighborCol)) {
                                cellPositionsToPrepareToIgnite.add(new int[]{neighborRow, neighborCol});
                            }

                    }
                }
            }
        }
    }

    private boolean isNeighborCellGoingToIgnite(int neighborRow, int neighborCol) {
        return this.forest.getGrid()[neighborRow][neighborCol].isBurnable() && random.nextDouble() < this.forest.getPropagationProbability();
    }

    private boolean isNeighborCellInRange(int neighborRow, int neighborCol) {
        return neighborRow >= 0 && neighborRow < this.forest.getHeight() &&
                neighborCol >= 0 && neighborCol < this.forest.getWidth();
    }
}
