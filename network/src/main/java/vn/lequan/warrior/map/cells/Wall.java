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
 * A non-walkable {@link Cell} represented by a wall texture. Used for the walls that act as
 * boundaries of the map.
 */
public class Wall extends Cell {

    public Wall(App app) {
        super(app);

        setWalkable(false);
        setBody(true);
    }

    @Override
    public void selectDrawableResource(Map map) {
        selectDrawableResourceForCornersOut(map);
        selectDrawableResourceForSides(map);
        selectDrawableResourceForCornersIn(map);

        if (assets == null) {
            assets = new String[]{"dummy"};
        }
    }

    private void selectDrawableResourceForCornersOut(Map map) {
        if (assets == null) {
            if (map.areAdjacentCellsWalkable(this, new CellDirection[]{NORTH, NORTHEAST, EAST})) {
                assets = new String[]{"wall_corner_out_top_right"};
                bodyBox = new int[]{1, 0, 6, 0};
            } else if (map.areAdjacentCellsWalkable(this, new CellDirection[]{WEST, NORTHWEST, NORTH})) {
                assets = new String[]{"wall_corner_out_top_left"};
                bodyBox = new int[]{-1, 0, 6, 0};
            } else if (map.areAdjacentCellsWalkable(this, new CellDirection[]{EAST, SOUTHEAST, SOUTH})) {
                assets = new String[]{"wall_corner_out_bottom_right"};
                bodyBox = new int[]{1, 0, 6, 0};
            } else if (map.areAdjacentCellsWalkable(this, new CellDirection[]{SOUTH, SOUTHWEST, WEST})) {
                assets = new String[]{"wall_corner_out_bottom_left"};
                bodyBox = new int[]{-1, 0, 6, 0};
            }
        }
    }

    private void selectDrawableResourceForSides(Map map) {
        if (assets == null) {
            if (map.areAdjacentCellsWalkable(this, new CellDirection[]{SOUTH})) {
                assets = new String[]{"wall_top"};
                bodyBox = new int[]{0, 0, 0, 0};
            } else if (map.areAdjacentCellsWalkable(this, new CellDirection[]{EAST})) {
                assets = new String[]{"wall_left"};
                bodyBox = new int[]{1, 0, 6, 0};
            } else if (map.areAdjacentCellsWalkable(this, new CellDirection[]{WEST})) {
                assets = new String[]{"wall_right"};
                bodyBox = new int[]{-1, 0, 6, 0};
            } else if (map.areAdjacentCellsWalkable(this, new CellDirection[]{NORTH})) {
                assets = new String[]{"wall_bottom"};
            }
        }
    }

    private void selectDrawableResourceForCornersIn(Map map) {
        if (assets == null) {
            if (map.areAdjacentCellsWalkable(this, new CellDirection[]{SOUTHEAST})) {
                assets = new String[]{"wall_corner_in_top_left"};
            } else if (map.areAdjacentCellsWalkable(this, new CellDirection[]{SOUTHWEST})) {
                assets = new String[]{"wall_corner_in_top_right"};
            } else if (map.areAdjacentCellsWalkable(this, new CellDirection[]{NORTHEAST})) {
                assets = new String[]{"wall_corner_in_bottom_left"};
            } else if (map.areAdjacentCellsWalkable(this, new CellDirection[]{NORTHWEST})) {
                assets = new String[]{"wall_corner_in_bottom_right"};
            }
        }
    }

}
