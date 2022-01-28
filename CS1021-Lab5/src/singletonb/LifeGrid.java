/*
 * Course: CS1021 - 021
 * Winter 2021
 * Lab 5 - Game Of Life
 * Name: Derek Riley (edits by Chris Taylor & Benjamin Singleton)
 * Created: 11/25/2016
 * Modified: 1/17/2022
 */

package singletonb;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

import static singletonb.Lab5.updateStats;

/**
 * This class implements the grid of cells used to model Conway's Game of Life.
 */
public class LifeGrid {

    /**
     * This instance variable stores the grid of Cells
     */
    private List<List<Cell>> cells;

    /**
     * This instance variable stores the width of the cell grid
     */
    private final int numberOfCellsWide;

    /**
     * This instance variable stores the height of the cell grid
     */
    private final int numberOfCellsHigh;

    private int step;
    private final ArrayList<Integer> totalPopulationData;

    /**
     * This constructor builds a LifeGrid using the size
     * of the Pane passed and the scale of the cells
     * @param gamePane viewing pane
     */
    public LifeGrid(GridPane gamePane) {
        this(gamePane, (int) gamePane.getPrefWidth(), (int) gamePane.getPrefHeight());
    }

    /**
     * This constructor builds a LifeGrid using the size
     * of the Pane passed and the scale of the cells
     * @param gamePane viewing pane
     * @param width the preferred width of the viewing pane
     * @param height the preferred width of the viewing pane
     */
    public LifeGrid(GridPane gamePane, int width, int height) {
        this.numberOfCellsWide = width/Cell.SCALE;
        this.numberOfCellsHigh = height/Cell.SCALE;
        cells = new ArrayList<>();
        step = 0;
        totalPopulationData = new ArrayList<>();

        //initialize the two dimensional ArrayList
        for(int i = 0; i < numberOfCellsHigh; i++) {
            cells.add(new ArrayList<>());
        }

        //create cells
        for(int i = 0; i < numberOfCellsHigh; i++) {     // yPosition
            for(int j = 0; j < numberOfCellsWide; j++) { // xPosition
                cells.get(i).add(new Cell(j, i));
                cells.get(i).get(j).setOnMouseClicked(this::toggleCellLife);
            }
        }

        //set neighbors for all cells
        for(int i = 0; i < numberOfCellsHigh; i++) {     // yPosition
            for(int j = 0; j < numberOfCellsWide; j++) { // xPosition
                if(i > 0) {
                    if(j > 0) {
                        cells.get(i).get(j).setNeighborAboveLeft(cells.get(i - 1).get(j - 1));
                    }
                    cells.get(i).get(j).setNeighborAboveCenter(cells.get(i - 1).get(j));
                    if(j < numberOfCellsWide - 1) {
                        cells.get(i).get(j).setNeighborAboveRight(cells.get(i - 1).get(j + 1));
                    }
                }
                if(j > 0) {
                    cells.get(i).get(j).setNeighborMiddleLeft(cells.get(i).get(j - 1));
                }
                if(j < numberOfCellsWide - 1) {
                    cells.get(i).get(j).setNeighborMiddleRight(cells.get(i).get(j + 1));
                }
                if(i < numberOfCellsHigh - 1) { // bottom boarder elements
                    if(j > 0) {
                        cells.get(i).get(j).setNeighborBelowLeft(cells.get(i + 1).get(j - 1));
                    }
                    cells.get(i).get(j).setNeighborBelowCenter(cells.get(i + 1).get(j));
                    if(j < numberOfCellsWide - 1) {
                        cells.get(i).get(j).setNeighborBelowRight(cells.get(i + 1).get(j + 1));
                    }
                }
            }
        }

        initialize(gamePane);
    }

    /**
     * This method randomizes the life and death attributes of all cells in the cells.
     * Cells have a 50% chance of being alive or dead.
     */
    public void randomize() {
        clear();
        final double aliveChance = 0.5;
        for(List<Cell> row : cells) {
            for(Cell cell : row) {
                cell.setAlive(Math.random() < aliveChance);
                cell.updateColors();
            }
        }
        updateStats();
    }

    /**
     * This method triggers one iteration (tick) of the game of life rules for the entire grid.
     */
    public void iterate() {
        for(List<Cell> row : cells) {
            for(Cell cell : row) {
                cell.determineNextTick();
            }
        }
        for(List<Cell> row : cells) {
            for(Cell cell : row) {
                cell.updateTick();
            }
        }
        step++;
        totalPopulationData.add(countAliveCells());
        updateStats();
    }

    /**
     * This method adds all the cell rectangles to the Pane
     */
    private void initialize(GridPane gamePane) {
        for(List<Cell> row : cells) {
            for(Cell cell : row) {
                gamePane.add(cell, cells.indexOf(row), row.indexOf(cell));
            }
        }
        updateStats();
    }

    private void toggleCellLife(MouseEvent event) {
        Cell cell = (Cell)event.getSource();
        cell.setAlive(!cell.isAlive());
        cell.updateColors();
        updateStats();
    }

    /**
     * Clears the grid of all cells and population data
     */
    public void clear() {
        for(List<Cell> row : cells) {
            for(Cell cell : row) {
                cell.setAlive(false);
                cell.updateColors();
            }
        }
        step = 0;
        totalPopulationData.clear();
        updateStats();
    }

    /**
     * Counts the number of alive cells on the grid
     * @return the number of alive cells
     */
    public int countAliveCells() {
        int aliveCells = 0;
        for(List<Cell> row : cells) {
            for(Cell cell : row) {
                aliveCells += cell.isAlive() ? 1 : 0;
            }
        }
        return aliveCells;
    }

    /**
     * Counts the total number of cells, alive or dead
     * @return the total number of cells
     */
    public int countCells() {
        return cells.size() * cells.get(0).size();
    }

    public int getStep() {
        return step;
    }

    public ArrayList<Integer> getTotalPopulationData() {
        return totalPopulationData;
    }
}
