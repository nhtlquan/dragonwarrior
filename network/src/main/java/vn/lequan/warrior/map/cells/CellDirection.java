/*
 * Copyright (c) 2017 Warrior Dragon Contributors
 * This program is made available under the terms of the MIT License.
 */

package vn.lequan.warrior.map.cells;

import vn.lequan.warrior.map.Map;

/**
 * Constants for directions in the {@link Map}.
 */
public enum CellDirection {
    EAST(1, 0),
    NORTH(0, 1),
    NORTHEAST(1, 1),
    NORTHWEST(-1, 1),
    SOUTH(0, -1),
    SOUTHEAST(1, -1),
    SOUTHWEST(-1, -1),
    WEST(-1, 0);

    /**
     * The x-coordinate adjustment to find the {@link Cell} in this direction.
     */
    private int x;

    /**
     * The y-coordinate adjustment to find the {@link Cell} in this direction.
     */
    private int y;

    private static CellDirection[] cardinals = new CellDirection[]{NORTH, EAST, SOUTH, WEST};

    /**
     * Constructs a direction.
     *
     * @param x The x-coordinate adjustment to find the {@link Cell} in this direction.
     * @param y The y-coordinate adjustment to find the {@link Cell} in this direction.
     */
    CellDirection(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gets the x-coordinate adjustment.
     *
     * @return The x-coordinate adjustment to find the {@link Cell} in this direction.
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the y-coordinate adjustment.
     *
     * @return The y-coordinate adjustment to find the {@link Cell} in this direction.
     */
    public int getY() {
        return y;
    }

    public static CellDirection[] getCardinals() {
        return cardinals;
    }

}