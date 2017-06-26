/*
 * Copyright (c) 2017 Warrior Dragon Contributors
 * This program is made available under the terms of the MIT License.
 */

package vn.lequan.warrior.map;


import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import vn.lequan.warrior.map.cells.Cell;
import vn.lequan.warrior.map.cells.CellDirection;
import vn.lequan.warrior.map.cells.Slab;
import vn.lequan.warrior.map.cells.Wall;
import vn.lequan.warrior.util.App;

import static vn.lequan.warrior.util.Constants.SCALE;

/**
 * A matrix of Cells of different types. (e.g. {@link Wall}, {@link Slab}, etc.)
 */
public class Map {

    private App app;

    /**
     * Number of columns.
     */
    private int cols;

    /**
     * Number of rows.
     */
    private int rows;

    /**
     * A matrix of Cells of different types. (e.g. {@link Wall}, {@link Slab}, etc.)
     */
    private Cell[][] cells;

    private TiledMapRenderer tiledMapRenderer;

    public Map(App app) {
        this.app = app;
    }

    /**
     * Sets the number of columns and rows of the Map and initialize the matrix of cells.
     *
     * @param cols Number of columns.
     * @param rows Number of rows.
     */
    public void set(int cols, int rows) {
        this.cols = cols;
        this.rows = rows;

        cells = new Cell[cols][rows];
    }

    /**
     * Gets the number of columns.
     */
    int getCols() {
        return cols;
    }

    /**
     * Gets the number of rows.
     */
    int getRows() {
        return rows;
    }

    public void drawTiledMap() {
        tiledMapRenderer.setView(app.getCam());
        tiledMapRenderer.render();
    }

    /**
     * Returns if a cell in the Map has adjacent cells in the given directions and are walkable.
     *
     * @param cell       A cell in the map.
     * @param directions Directions in which to check if an adjacent cell exists.
     * @return {@code true} if an adjacent cell exists in every given direction and are walkable;
     * {@code false} otherwise.
     */
    public boolean areAdjacentCellsWalkable(Cell cell, CellDirection[] directions) {
        for (CellDirection direction : directions) {
            if (!isAdjacentCellWalkable(cell, direction)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Returns if a cell in the Map has adjacent cells in the given directions and are of the
     * given Class.
     *
     * @param cell       A cell in the map.
     * @param directions Directions in which to check if an adjacent cell inherits of a class.
     * @param clazz      The Class to check if the adjacent cells inherits of.
     * @return {@code true} if an adjacent cell exists in every given direction and inherits of the
     * given class; {@code false} otherwise.
     */
    public boolean areAdjacentCellsOf(Cell cell, CellDirection[] directions, Class clazz) {
        for (CellDirection direction : directions) {
            Cell adjacentCell = determineAdjacentCell(cell, direction);

            if (adjacentCell == null || !clazz.isInstance(adjacentCell)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Returns if a cell in the Map has adjacent cells in the given directions and aren't of the
     * given Class.
     *
     * @param cell       A cell in the map.
     * @param directions Directions in which to check if an adjacent cell doesn't inherits of a
     *                   class.
     * @param clazz      The Class to check if the adjacent cells don't inherit of.
     * @return {@code true} if an adjacent cell exists in every given direction and doesn't inherit
     * of the given class; {@code false} otherwise.
     */
    public boolean arentAdjacentCellsOf(Cell cell, CellDirection[] directions, Class clazz) {
        for (CellDirection direction : directions) {
            Cell adjacentCell = determineAdjacentCell(cell, direction);

            if (adjacentCell != null && clazz.isInstance(adjacentCell)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Returns if a cell in the Map has an adjacent cell in a given direction and it's walkable.
     *
     * @param cell      A cell in the map.
     * @param direction Direction in which to check if an adjacent cell exists.
     * @return {@code true} if an adjacent cell exists in the given direction and it's walkable;
     * {@code false} otherwise.
     */
    boolean isAdjacentCellWalkable(Cell cell, CellDirection direction) {
        Cell adjacentCell = determineAdjacentCell(cell, direction);

        return adjacentCell != null && adjacentCell.isWalkable();
    }

    /**
     * Returns the adjacent cell of the given cell that exists in the given direction.
     *
     * @param cell      A cell in the map.
     * @param direction A direction where to look for an adjacent cell.
     * @return The adjacent cell of the given cell that exists in the given direction.
     */
    public Cell determineAdjacentCell(Cell cell, CellDirection direction) {
        int x = cell.getX() + direction.getX();
        int y = cell.getY() + direction.getY();

        if (existsCell(x, y)) {
            return cells[x][y];
        } else {
            return null;
        }
    }

    /**
     * Gets the cell in the given coordinates.
     *
     * @param x X-coordinate.
     * @param y Y-coordinate.
     * @return The cell in the given coordinates.
     */
    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    public boolean existsCell(int x, int y) {
        if (x >= 0 && x < cells.length && y >= 0 && y < cells[x].length) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Sets a cell in the given coordinates.
     *
     * @param x    X-coordinate.
     * @param y    Y-coordinate.
     * @param cell A cell to be placed in the given coordinates.
     */
    void setCell(int x, int y, Cell cell) {
        cells[x][y] = cell;
    }

    void setMapTiledMap(TiledMap mapTiledMap) {
        tiledMapRenderer = new OrthoCachedTiledMapRenderer(mapTiledMap, SCALE);
        ((OrthoCachedTiledMapRenderer) tiledMapRenderer).setBlending(true);
    }

    /**
     * Finds a random walkable Cell.
     *
     * @return A random walkable Cell.
     */
    public Cell findRandomWalkableCell() {
        List<Cell> walkableCells = findWalkableCells();

        if (!walkableCells.isEmpty()) {
            int randomNumber = new Random().nextInt(walkableCells.size());

            return walkableCells.get(randomNumber);
        } else {
            return null;
        }
    }

    /**
     * Finds the walkable cells of the Map.
     *
     * @return The walkable cells of the Map.
     */
    private List<Cell> findWalkableCells() {
        List<Cell> emptyCells = new ArrayList<>();

        for (int x = 0; x < cols; x++) {
            for (int y = 0; y < rows; y++) {
                if (cells[x][y].isWalkable()) {
                    emptyCells.add(cells[x][y]);
                }
            }
        }

        return emptyCells;
    }

}
