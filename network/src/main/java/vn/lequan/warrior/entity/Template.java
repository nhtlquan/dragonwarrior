/*
 * Copyright (c) 2017 Warrior Dragon Contributors
 * This program is made available under the terms of the MIT License.
 */

package vn.lequan.warrior.entity;


import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.EnumMap;
import java.util.Map;

import vn.lequan.warrior.util.App;

/**
 * Abstract class for {@link Entity} templates.
 */
public abstract class Template {

    private App app;

    /**
     * Width of a single sprite.
     */
    private final int width;

    /**
     * Height of a single sprite.
     */
    private final int height;

    private TextureRegion shadow;

    protected Template(App app, int width, int height) {
        this.app = app;
        this.width = width;
        this.height = height;
    }

    /**
     * Gets a Map with the {@link Animation} corresponding to each {@link Action} and
     * {@link Direction}.
     *
     * @return A Map with the {@link Animation} corresponding to each {@link Action}
     * and {@link Direction}.
     */
    public abstract Map<Action, EnumMap<Direction, Animation>> getAnimations();

    /**
     * Constructs the animation.
     *
     * @param frameLength    Time to wait in milliseconds before changing to the next frame
     *                       in the animation.
     * @param numberOfFrames Number of frames in the animation.
     */
    protected Animation createAnimation(int frameLength, String asset, int numberOfFrames) {
        return new Animation(app, frameLength, asset, numberOfFrames, width, height);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public TextureRegion getShadow() {
        return shadow;
    }

    public void setShadow(TextureRegion shadow) {
        this.shadow = shadow;
    }

}
