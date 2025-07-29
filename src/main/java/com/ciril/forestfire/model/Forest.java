package com.ciril.forestfire.model;

import java.util.LinkedList;
import java.util.List;

public class Forest {
    private final ICell[][] grid;
    private List<ICell> burningCells = new LinkedList<>();
    private final int height;
    private final int width;


    public Forest(int height, int width) {
        this.height = height;
        this.width = width;
        this.grid = new ICell[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                TreeCell treeCell = new TreeCell();
                treeCell.setPosition(new Position(i, j));
                this.grid[i][j] = treeCell;
            }
        }
    }

    public boolean hasActiveFires() {
        return !burningCells.isEmpty();
    }

    public List<ICell> getBurningCells() {
        return burningCells;
    }

    public void setBurningCells(List<ICell> burningICells) {
        this.burningCells = burningICells;
    }

    public boolean isCellInForest(int x, int y){
        return y >= 0 && y < this.getHeight() &&
                x >= 0 && x < this.getWidth();
    }

    public ICell getCellInForest(int x, int y){
        return this.getGrid()[x][y];
    }

    public void display() {
        System.out.println("\n------- Forest -------");
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                ICell currentICell = this.grid[i][j];
                System.out.print(currentICell.toString() + " ");
            }
            System.out.println();
        }
        System.out.println("------------------------\n");
    }

    public ICell[][] getGrid() {
        return grid;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
