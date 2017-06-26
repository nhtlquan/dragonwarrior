/*
 * Copyright (c) 2017 Warrior Dragon Contributors
 * This program is made available under the terms of the MIT License.
 */

package vn.lequan.warrior.map.cells;

import vn.lequan.warrior.map.Map;
import vn.lequan.warrior.util.App;

/**
 * A non-walkable {@link Cell} represented by a black texture. Used for the cells outside of the walls.
 */
public class Unfilled extends Cell {

    public Unfilled(App app) {
        super(app);

        setWalkable(false);
    }

    @Override
    public void selectDrawableResource(Map map) {
        if (assets == null) {
            assets = new String[]{"unfilled"};
        }
    }

}
