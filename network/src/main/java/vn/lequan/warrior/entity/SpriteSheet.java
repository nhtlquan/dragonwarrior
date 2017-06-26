/*
 * Copyright (c) 2017 Warrior Dragon Contributors
 * This program is made available under the terms of the MIT License.
 */

package vn.lequan.warrior.entity;


import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.EnumMap;
import java.util.Map;

import vn.lequan.warrior.ui.position.WorldLocation;
import vn.lequan.warrior.util.App;
import vn.lequan.warrior.util.Positions;

import static vn.lequan.warrior.util.Constants.SCALE;

/**
 * Sheet of sprites which contains all the animations of an object.
 */
public class SpriteSheet {

    private App app;

    private Template template;

    /**
     * A Map with the {@link Animation} corresponding to each {@link Action} and {@link Direction}.
     */
    private Map<Action, EnumMap<Direction, Animation>> animations;

    private WorldLocation worldLocation = new WorldLocation();

    private Action action = Action.IDLE;
    private Direction actionDirection = Direction.DOWN;

    public SpriteSheet(App app, Template template) {
        this.app = app;
        this.template = template;

        animations = this.template.getAnimations();
    }

    /**
     * Gets the {@link Template} used by this sprite sheet.
     *
     * @return The {@link Template} used by this sprite sheet.
     */
    Template getTemplate() {
        return template;
    }

    public void setWorldLocation(Vector2 worldLocation) {
        this.worldLocation.set(worldLocation);
    }

    /**
     * Gets the {@link Action} selected from the sprite sheet.
     *
     * @return The {@link Action} selected from the sprite sheet.
     */
    public Action getAction() {
        return action;
    }

    /**
     * Sets the {@link Action} to select from the sprite sheet.
     *
     * @param action The {@link Action} to select from the sprite sheet.
     */
    public void setAction(Action action) {
        this.action = action;
    }

    /**
     * Sets the {@link Direction} to select from the sprite sheet.
     *
     * @param actionDirection The {@link Direction} to select from the sprite sheet.
     */
    public void setDirection(Direction actionDirection) {
        this.actionDirection = actionDirection;
    }

    public void draw(boolean changedColor) {
        TextureRegion currentFrame = getCurrentFrame();

        if (currentFrame != null) {
            int mapX = Positions.convertWorldCoordToMap(worldLocation.getX());
            int mapY = Positions.convertWorldCoordToMap(worldLocation.getY());

            int textureX = mapX - ((currentFrame.getRegionWidth() * SCALE) / 2);
            int textureY = mapY - (4 * SCALE);

            if (template.getShadow() != null && !changedColor) {
                app.draw(template.getShadow(), textureX, textureY - SCALE, false);
            }

            app.draw(currentFrame, textureX, textureY, false);
        }
    }

    /**
     * Gets the current frame from the {@link Animation} to be drawn on the screen.
     *
     * @return The frame to be drawn on the screen.
     */
    private TextureRegion getCurrentFrame() {
        Animation animation = getCurrentAnimation();

        if (animation != null) {
            return animation.getCurrentFrame();
        } else {
            return null;
        }
    }

    /**
     * Updates the current frame from the {@link Animation} based on the current {@link Action} and {@link Direction}
     */
    public void updateAnimation() {
        Animation animation = getCurrentAnimation();

        if (animation != null) {
            animation.updateFrame();
        }
    }

    /**
     * In case of a non bouncing animation, if it has been completed.
     *
     * @return {@code true} if all frames of the animation have been shown;
     * {@code false} otherwise.
     */
    public boolean isAnimationCompleted() {
        Animation animation = getCurrentAnimation();

        return animation != null && animation.isCompleted();
    }

    /**
     * Gets the current animation being shown.
     *
     * @return The current animation being shown.
     */
    private Animation getCurrentAnimation() {
        if (animations.containsKey(action)) {
            EnumMap<Direction, Animation> animationsByDirection = animations.get(action);
            return animationsByDirection.get(actionDirection);
        } else {
            return null;
        }
    }
}
