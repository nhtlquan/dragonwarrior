/*
 * Copyright (c) 2017 Warrior Dragon Contributors
 * This program is made available under the terms of the MIT License.
 */

package vn.lequan.warrior.config.module;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import vn.lequan.warrior.util.App;

@Module
public class AppModule {

    @Provides
    @Singleton
    App provideApp() {
        return App.get();
    }

}
