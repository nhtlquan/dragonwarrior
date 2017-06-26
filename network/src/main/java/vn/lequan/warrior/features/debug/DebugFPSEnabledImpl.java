/*
 * Copyright (c) 2017 Warrior Dragon Contributors
 * This program is made available under the terms of the MIT License.
 */

package vn.lequan.warrior.features.debug;


import com.badlogic.gdx.graphics.g2d.BitmapFont;

import vn.lequan.warrior.util.App;

import static vn.lequan.warrior.util.Constants.FILL_80_GREEN;
import static vn.lequan.warrior.util.Constants.FILL_80_ORANGE;
import static vn.lequan.warrior.util.Constants.FILL_80_RED;

/**
 * Implementation for this feature if it's enabled.
 */
public class DebugFPSEnabledImpl implements DebugFPS {

    private App app;

    public DebugFPSEnabledImpl(App app) {
        this.app = app;
    }

    @Override
    public void debug(long fps, boolean relative) {
        BitmapFont font;

        if (fps < 30) {
            font = FILL_80_RED;
        } else if (fps >= 30 && fps < 50) {
            font = FILL_80_ORANGE;
        } else {
            font = FILL_80_GREEN;
        }

        app.drawText(font, String.valueOf(fps), app.getScreenWidth() - 80, 60, relative);
    }

}
