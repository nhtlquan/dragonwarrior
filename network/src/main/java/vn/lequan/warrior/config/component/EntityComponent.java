/*
 * Copyright (c) 2017 Warrior Dragon Contributors
 * This program is made available under the terms of the MIT License.
 */

package vn.lequan.warrior.config.component;


import javax.inject.Singleton;

import dagger.Component;
import vn.lequan.warrior.config.module.AppModule;
import vn.lequan.warrior.config.module.CharacterModule;
import vn.lequan.warrior.config.module.ItemModule;
import vn.lequan.warrior.config.module.ProjectileModule;
import vn.lequan.warrior.config.module.TemplateModule;
import vn.lequan.warrior.entity.items.Coin;
import vn.lequan.warrior.entity.monsters.Jiangshi;
import vn.lequan.warrior.entity.player.Player;
import vn.lequan.warrior.entity.projectiles.PowerBlast;
import vn.lequan.warrior.entity.projectiles.SoulBreaker;

@Singleton
@Component(modules={AppModule.class, ItemModule.class, ProjectileModule.class,
        CharacterModule.class, TemplateModule.class})
public interface EntityComponent {

    Coin provideCoin();

    PowerBlast providePowerBlast();

    SoulBreaker provideSoulBreaker();

    Player providePlayer();

    Jiangshi provideJiangshi();

}
