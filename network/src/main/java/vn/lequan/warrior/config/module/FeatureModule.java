/*
 * Copyright (c) 2017 Warrior Dragon Contributors
 * This program is made available under the terms of the MIT License.
 */

package vn.lequan.warrior.config.module;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import vn.lequan.warrior.features.debug.DebugBox2D;
import vn.lequan.warrior.features.debug.DebugBox2DDisabledImpl;
import vn.lequan.warrior.features.debug.DebugBox2DEnabledImpl;
import vn.lequan.warrior.features.debug.DebugFPS;
import vn.lequan.warrior.features.debug.DebugFPSDisabledImpl;
import vn.lequan.warrior.features.debug.DebugFPSEnabledImpl;
import vn.lequan.warrior.util.App;

import static vn.lequan.warrior.util.Constants.DEBUG_BOX2D;
import static vn.lequan.warrior.util.Constants.DEBUG_FPS_ENABLED;

/**
 * Configures the enabling/disabling of some features.
 */
@Module
public class FeatureModule {

    @Provides
    @Singleton
    DebugFPS provideDebugFPS(App app) {
        if (DEBUG_FPS_ENABLED) {
            return new DebugFPSEnabledImpl(app);
        } else {
            return new DebugFPSDisabledImpl();
        }
    }

    @Provides
    @Singleton
    DebugBox2D provideDebugBox2D(App app) {
        if (DEBUG_BOX2D) {
            return new DebugBox2DEnabledImpl(app);
        } else {
            return new DebugBox2DDisabledImpl();
        }
    }

}
