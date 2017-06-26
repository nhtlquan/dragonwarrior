/*
 * Copyright (c) 2017 Warrior Dragon Contributors
 * This program is made available under the terms of the MIT License.
 */

package vn.lequan.warrior.map.cells;

import vn.lequan.warrior.map.Map;
import vn.lequan.warrior.util.App;

import static vn.lequan.warrior.map.cells.CellDirection.EAST;
import static vn.lequan.warrior.map.cells.CellDirection.NORTH;
import static vn.lequan.warrior.map.cells.CellDirection.NORTHEAST;
import static vn.lequan.warrior.map.cells.CellDirection.NORTHWEST;
import static vn.lequan.warrior.map.cells.CellDirection.SOUTH;
import static vn.lequan.warrior.map.cells.CellDirection.SOUTHEAST;
import static vn.lequan.warrior.map.cells.CellDirection.SOUTHWEST;
import static vn.lequan.warrior.map.cells.CellDirection.WEST;

/**
 * A walkable {@link Cell} represented by a carpet texture.
 */
public class Carpet extends Cell {

    public Carpet(App app) {
        super(app);

        setWalkable(true);
    }

    @Override
    public void selectDrawableResource(Map map) {
        selectDrawableResourceForCorners(map);
        selectDrawableResourceForSides(map);

        if (assets == null) {
            assets = new String[]{"carpet_red_center"};
        }
    }

    private void selectDrawableResourceForCorners(Map map) {
        if (assets != null) {
            return;
        }

        if (map.arentAdjacentCellsOf(this, new CellDirection[]{NORTH, NORTHEAST, EAST}, Carpet.class) &&
                map.areAdjacentCellsOf(this, new CellDirection[]{SOUTH, SOUTHWEST, WEST}, Carpet.class)) {
            assets = new String[]{"carpet_red_top_right"};
        } else if (map.arentAdjacentCellsOf(this, new CellDirection[]{WEST, NORTHWEST, NORTH}, Carpet.class) &&
                map.areAdjacentCellsOf(this, new CellDirection[]{EAST, SOUTHEAST, SOUTH}, Carpet.class)) {
            assets = new String[]{"carpet_red_top_left"};
        } else if (map.arentAdjacentCellsOf(this, new CellDirection[]{EAST, SOUTHEAST, SOUTH}, Carpet.class) &&
                map.areAdjacentCellsOf(this, new CellDirection[]{WEST, NORTHWEST, NORTH}, Carpet.class)) {
            assets = new String[]{"carpet_red_bottom_right"};
        } else if (map.arentAdjacentCellsOf(this, new CellDirection[]{SOUTH, SOUTHWEST, WEST}, Carpet.class) &&
                map.areAdjacentCellsOf(this, new CellDirection[]{NORTH, NORTHEAST, EAST}, Carpet.class)) {
            assets = new String[]{"carpet_red_bottom_left"};
        }
    }

    private void selectDrawableResourceForSides(Map map) {
        if (assets != null) {
            return;
        }

        if (map.arentAdjacentCellsOf(this, new CellDirection[]{SOUTH}, Carpet.class) &&
                map.areAdjacentCellsOf(this, new CellDirection[]{NORTH}, Carpet.class)) {
            assets = new String[]{"carpet_red_bottom"};
        } else if (map.arentAdjacentCellsOf(this, new CellDirection[]{EAST}, Carpet.class) &&
                map.areAdjacentCellsOf(this, new CellDirection[]{WEST}, Carpet.class)) {
            assets = new String[]{"carpet_red_right"};
        } else if (map.arentAdjacentCellsOf(this, new CellDirection[]{WEST}, Carpet.class) &&
                map.areAdjacentCellsOf(this, new CellDirection[]{EAST}, Carpet.class)) {
            assets = new String[]{"carpet_red_left"};
        } else if (map.arentAdjacentCellsOf(this, new CellDirection[]{NORTH}, Carpet.class) &&
                map.areAdjacentCellsOf(this, new CellDirection[]{SOUTH}, Carpet.class)) {
            assets = new String[]{"carpet_red_top"};
        }
    }

}