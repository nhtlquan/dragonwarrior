/*
 * Copyright (c) 2017 Warrior Dragon Contributors
 * This program is made available under the terms of the MIT License.
 */

package vn.lequan.warrior.ui.position;

import vn.lequan.warrior.util.Positions;

/**
 * The position of an object.
 */
public class Position {

    /**
     * X-coordinate.
     */
    private int x;

    /**
     * Y-coordinate.
     */
    private int y;

    public Position() {
    }

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position(Position position) {
        x = position.getX();
        y = position.getY();
    }

    /**
     * Sets the coordinates.
     *
     * @param x X-coordinate.
     * @param y Y-coordinate.
     */
    public void set(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void set(Position position) {
        x = position.getX();
        y = position.getY();
    }

    /**
     * Gets the x-coordinate.
     *
     * @return The x-coordinate.
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the y-coordinate.
     *
     * @return The y-coordinate.
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the x-coordinate.
     *
     * @param x The x-coordinate.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Sets the y-coordinate.
     *
     * @param y The y-coordinate.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Scales down the position by the CANVAS_SCALE.
     */
    public void scaleDown() {
        x = Positions.scaleDown(x);
        y = Positions.scaleDown(y);
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @SuppressWarnings("squid:S00122")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (x != position.x) return false;
        return y == position.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

}