/*
 * Copyright (c) 2017 Warrior Dragon Contributors
 * This program is made available under the terms of the MIT License.
 */

package vn.lequan.warrior.config.component;


import javax.inject.Singleton;

import dagger.Component;
import vn.lequan.warrior.config.module.AppModule;
import vn.lequan.warrior.util.App;

@Singleton
@Component(modules={AppModule.class})
public interface AppComponent {

   App provideApp();

}
