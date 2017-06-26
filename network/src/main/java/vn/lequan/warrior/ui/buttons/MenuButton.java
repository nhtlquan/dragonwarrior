/*
 * Copyright (c) 2017 Warrior Dragon Contributors
 * This program is made available under the terms of the MIT License.
 */

package vn.lequan.warrior.ui.buttons;


import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import vn.lequan.warrior.ui.position.Position;
import vn.lequan.warrior.util.App;
import vn.lequan.warrior.util.Cycle;

import static vn.lequan.warrior.util.Constants.FILL_80_DARK_ORANGE;
import static vn.lequan.warrior.util.Constants.FILL_80_LIGHT_ORANGE;
import static vn.lequan.warrior.util.Constants.FILL_80_WHITE;
import static vn.lequan.warrior.util.Constants.SCALE;

public abstract class MenuButton {

    private App app;
    private final TextureRegion texture;
    private final String text;
    private Position alignCenter;

    private int x;
    private int y;
    private int initialY;
    private boolean shadow;
    private boolean disabled;
    private boolean clicked;
    private Mode mode = Mode.NORMAL;
    private BitmapFont fontEnabled = FILL_80_WHITE;
    private BitmapFont fontDisabled = FILL_80_LIGHT_ORANGE;
    private BitmapFont fontShadow = FILL_80_DARK_ORANGE;

    private Cycle clickCycle = new Cycle(1);
    private OnButtonClickedListener listener;

    private enum Mode {
        NORMAL,
        CLICK_DOWN,
        CLICK_UP
    }

    MenuButton(App app, TextureRegion texture, String text, Position alignCenter) {
        this.app = app;
        this.texture = texture;
        this.text = text;
        this.alignCenter = alignCenter;
    }

    public void set(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getWidth() {
        return texture.getRegionWidth() * SCALE;
    }

    private int getHeight() {
        return texture.getRegionHeight() * SCALE;
    }

    public void setShadow(boolean shadow) {
        this.shadow = shadow;
    }

    void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public void setFontEnabled(BitmapFont fontEnabled) {
        this.fontEnabled = fontEnabled;
    }

    public void draw(boolean relative) {
        int textX = x + alignCenter.getX();
        int textY = y + alignCenter.getY();

        app.draw(texture, x, y, relative);

        if (shadow) {
//            app.drawText(fontShadow, text, textX, textY - (2 * SCALE), relative);
        }

        if (disabled) {
            app.drawText(fontEnabled, text, textX, textY, relative);
        } else {
            app.drawText(fontEnabled, text, textX, textY, relative);
        }
    }

    public void update() {
        if (clicked && clickCycle.isCompleted()) {
            if (mode == Mode.NORMAL) {
                initialY = y;
                mode = Mode.CLICK_DOWN;
            }

            if (mode == Mode.CLICK_DOWN) {
                y += 5;
            }

            if (y >= initialY + 15) {
                mode = Mode.CLICK_UP;
            }

            if (mode == Mode.CLICK_UP) {
                y -= 5;

                if (y == initialY) {
                    mode = Mode.NORMAL;
                    clicked = false;
                    listener.onButtonClicked(this);
                }
            }
        }
    }

    public boolean inBounds(float x, float y, boolean relative) {
        if (relative) {
            return x >= app.camRelativeX(this.x)
                    && x <= app.camRelativeX(this.x) + getWidth()
                    && y >= app.camRelativeY(this.y)
                    && y <= app.camRelativeY(this.y) + getHeight();
        } else {
            return x >= this.x
                    && x <= this.x + getWidth()
                    && y >= this.y
                    && y <= this.y + getHeight();
        }
    }

    public void click(OnButtonClickedListener listener) {
        this.listener = listener;
        clicked = true;
    }

}
