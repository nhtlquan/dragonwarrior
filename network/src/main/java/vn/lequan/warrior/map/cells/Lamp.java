/*
 * Copyright (c) 2017 Warrior Dragon Contributors
 * This program is made available under the terms of the MIT License.
 */

package vn.lequan.warrior.map.cells;

import vn.lequan.warrior.map.Map;
import vn.lequan.warrior.util.App;

/**
 * A non-walkable {@link Cell} represented by a lamp texture. Used for the walls with lamps on
 * them.
 */
public class Lamp extends Wall {

    public Lamp(App app) {
        super(app);
    }

    @Override
    public void selectDrawableResource(Map map) {
        if (assets == null) {
            super.selectDrawableResource(map);

            assets = new String[]{assets[0], "wall_lamp"};
        }
    }

}
