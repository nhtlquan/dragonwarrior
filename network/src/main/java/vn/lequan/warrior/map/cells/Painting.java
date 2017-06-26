/*
 * Copyright (c) 2017 Warrior Dragon Contributors
 * This program is made available under the terms of the MIT License.
 */

package vn.lequan.warrior.map.cells;

import vn.lequan.warrior.map.Map;
import vn.lequan.warrior.util.App;

/**
 * A non-walkable {@link Cell} represented by a painting texture. Used for the walls with paintings
 * on them.
 */
public class Painting extends Wall {

    public Painting(App app) {
        super(app);
    }

    @Override
    public void selectDrawableResource(Map map) {
        if (assets == null) {
            super.selectDrawableResource(map);

            assets = new String[]{assets[0], "wall_painting"};
        }
    }

}
