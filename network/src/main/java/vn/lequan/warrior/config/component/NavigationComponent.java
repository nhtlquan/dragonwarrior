/*
 * Copyright (c) 2017 Warrior Dragon Contributors
 * This program is made available under the terms of the MIT License.
 */

package vn.lequan.warrior.config.component;


import javax.inject.Singleton;

import dagger.Component;
import vn.lequan.warrior.Main;
import vn.lequan.warrior.config.module.AppModule;
import vn.lequan.warrior.config.module.ButtonModule;
import vn.lequan.warrior.config.module.FeatureModule;
import vn.lequan.warrior.config.module.ViewModule;
import vn.lequan.warrior.views.menu.MenuInteractorImpl;
import vn.lequan.warrior.views.play.PlayInteractorImpl;

@Singleton
@Component(modules={AppModule.class, FeatureModule.class, ViewModule.class, ButtonModule.class})
public interface NavigationComponent {

    void inject(Main main);

    void inject(MenuInteractorImpl interactor);

    void inject(PlayInteractorImpl interactor);

}
