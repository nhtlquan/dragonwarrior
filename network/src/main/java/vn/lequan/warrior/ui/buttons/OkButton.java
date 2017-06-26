/*
 * Copyright (c) 2017 Warrior Dragon Contributors
 * This program is made available under the terms of the MIT License.
 */

package vn.lequan.warrior.ui.buttons;


import com.badlogic.gdx.graphics.g2d.TextureRegion;

import vn.lequan.warrior.ui.position.Position;
import vn.lequan.warrior.util.App;

import static vn.lequan.warrior.util.Constants.FILL_80_BROWN;

public class OkButton extends MenuButton {

    public OkButton(App app, TextureRegion texture, String text, Position alignCenter) {
        super(app, texture, text, alignCenter);

        setFontEnabled(FILL_80_BROWN);
    }

}
