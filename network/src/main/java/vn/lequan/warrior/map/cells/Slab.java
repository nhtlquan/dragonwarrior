/*
 * Copyright (c) 2017 Warrior Dragon Contributors
 * This program is made available under the terms of the MIT License.
 */

package vn.lequan.warrior.map.cells;

import vn.lequan.warrior.map.Map;
import vn.lequan.warrior.util.App;

/**
 * A walkable {@link Cell} represented by a slab texture.
 */
public class Slab extends Cell {

    public Slab(App app) {
        super(app);

        setWalkable(true);
    }

    @Override
    public void selectDrawableResource(Map map) {
        if (assets == null) {
            assets = new String[]{"slab"};
        }
    }

}
