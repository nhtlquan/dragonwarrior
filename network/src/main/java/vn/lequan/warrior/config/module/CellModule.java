/*
 * Copyright (c) 2017 Warrior Dragon Contributors
 * This program is made available under the terms of the MIT License.
 */

package vn.lequan.warrior.config.module;


import dagger.Module;
import dagger.Provides;
import vn.lequan.warrior.map.cells.Carpet;
import vn.lequan.warrior.map.cells.Lamp;
import vn.lequan.warrior.map.cells.Painting;
import vn.lequan.warrior.map.cells.Slab;
import vn.lequan.warrior.map.cells.Unfilled;
import vn.lequan.warrior.map.cells.Wall;
import vn.lequan.warrior.util.App;

@Module
public class CellModule {

    @Provides
    Wall provideWall(App app) {
        return new Wall(app);
    }

    @Provides
    Lamp provideLamp(App app) {
        return new Lamp(app);
    }

    @Provides
    Painting providePainting(App app) {
        return new Painting(app);
    }

    @Provides
    Slab provideSlab(App app) {
        return new Slab(app);
    }

    @Provides
    Carpet provideCarpet(App app) {
        return new Carpet(app);
    }

    @Provides
    Unfilled provideUnfilled(App app) {
        return new Unfilled(app);
    }

}
