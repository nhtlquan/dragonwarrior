/*
 * Copyright (c) 2017 Warrior Dragon Contributors
 * This program is made available under the terms of the MIT License.
 */

package vn.lequan.warrior.entity;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import vn.lequan.warrior.util.App;
import vn.lequan.warrior.util.Cycle;

/**
 * A sequence of bitmaps (frames) that will be displayed in succession to create the illusion of
 * movement.
 */
public class Animation {

    private App app;

    private final String asset;

    /**
     * Number of frames of the Animation.
     */
    private final int numberOfFrames;

    private final int width;

    private final int height;

    private Cycle cycle;

    /**
     * Indicates if the animation frames have to bounce or not. (e.g. given frames: 1, 2, 3,
     * if it's set to {@code true} the frames will be drawn in this order: 1, 2, 3, 2, 1, 2...,
     * if it's set to {@code false} however, the order will be: 1, 2, 3, 1, 2, 3...)
     */
    private boolean bounce = true;

    /**
     * The position of the current frame in the animation sequence.
     */
    private int currentFramePosition = 0;

    /**
     * In case of a non bouncing animation, {@code true} if all frames of the animation have been
     * shown; {@code false} otherwise.
     */
    private boolean completed = false;

    /**
     * Used when bounce = true, it's used to control the bouncing. If it's set to {@code true}
     * current frame position will be increased, if it's set to {@code false} it will be decreased.
     */
    private boolean increaseCurrentFramePosition = true;

    /**
     * Constructs the animation.
     *
     * @param frameLengthInMilliseconds Time to wait in milliseconds before changing to the next
     *                                  frame in the animation.
     * @param asset                     Asset with n frames.
     * @param numberOfFrames            Number of frames in the animation.
     * @param width                     Width of the frames.
     * @param height                    Height of the frames.
     */
    public Animation(App app, int frameLengthInMilliseconds, String asset, int numberOfFrames,
                     int width, int height) {

        this.app = app;
        cycle = new Cycle(frameLengthInMilliseconds);

        this.asset = asset;
        this.numberOfFrames = numberOfFrames;
        this.width = width;
        this.height = height;
    }

    /**
     * Gets the current frame of the Animation to be displayed.
     *
     * @return The current frame to be displayed.
     */
    TextureRegion getCurrentFrame() {
        return app.getAnimationTexture(asset, currentFramePosition, width, height);
    }

    /**
     * Updates the current frame of the Animation to be displayed. It will change the frame
     * every n milliseconds to create the illusion of an animation.
     */
    void updateFrame() {
        if (cycle.isCompleted()) {
            if (bounce) {
                if (increaseCurrentFramePosition) {
                    currentFramePosition++;
                } else {
                    currentFramePosition--;
                }

                if (currentFramePosition == (numberOfFrames - 1)) {
                    increaseCurrentFramePosition = false;
                } else if (currentFramePosition == 0) {
                    increaseCurrentFramePosition = true;
                }
            } else {
                currentFramePosition = (currentFramePosition + 1) % numberOfFrames;
                completed = completed || currentFramePosition == numberOfFrames - 1;
            }
        }
    }

    /**
     * Sets if the Animation's frames have to bounce.
     *
     * @param bounce Indicates if the animation frames have to bounce or not. (e.g. given frames: 1, 2, 3,
     *               if it's set to {@code true} the frames will be drawn in this order: 1, 2, 3, 2, 1, 2...,
     *               if it's set to {@code false} however, the order will be: 1, 2, 3, 1, 2, 3...)
     */
    public void setBounce(boolean bounce) {
        this.bounce = bounce;
    }

    boolean isCompleted() {
        return completed;
    }

}
