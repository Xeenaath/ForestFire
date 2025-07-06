package com.ciril.forestfire;

import java.util.List;

public class Forest {
    private Cell[][] grid;
    private int height;
    private int width;
    private double propagationProbability;
    private List<int[]> initialFires;

    public void initialize() {
        this.grid = new Cell[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                this.grid[i][j] = new TreeCell();
            }
        }
    }

    public boolean hasActiveFires() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Cell currentCell = this.grid[i][j];
                if (currentCell.isBurning()) {
                    return true;
                }
            }
        }
        return false;
    }

    public void display() {
        System.out.println("\n------- Forest -------");
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Cell currentCell = this.grid[i][j];
                System.out.print(currentCell.toString() + " ");
            }
            System.out.println();
        }
        System.out.println("------- ------ -------\n");
    }

    public Cell[][] getGrid() {
        return grid;
    }

    public void setGrid(Cell[][] grid) {
        this.grid = grid;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

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
