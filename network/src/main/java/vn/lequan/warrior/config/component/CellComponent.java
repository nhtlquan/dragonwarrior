/*
 * Copyright (c) 2017 Warrior Dragon Contributors
 * This program is made available under the terms of the MIT License.
 */

package vn.lequan.warrior.config.component;


import javax.inject.Singleton;

import dagger.Component;
import vn.lequan.warrior.config.module.AppModule;
import vn.lequan.warrior.config.module.CellModule;
import vn.lequan.warrior.map.cells.Carpet;
import vn.lequan.warrior.map.cells.Lamp;
import vn.lequan.warrior.map.cells.Painting;
import vn.lequan.warrior.map.cells.Slab;
import vn.lequan.warrior.map.cells.Unfilled;
import vn.lequan.warrior.map.cells.Wall;

@Singleton
@Component(modules={AppModule.class, CellModule.class})
public interface CellComponent {

    Wall provideWall();

    Slab provideSlab();

    Carpet provideCarpet();

    Unfilled provideUnfilled();

    Lamp provideLamp();

    Painting providePainting();

}
