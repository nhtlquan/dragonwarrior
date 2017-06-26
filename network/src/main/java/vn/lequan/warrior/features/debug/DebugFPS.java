/*
 * Copyright (c) 2017 Warrior Dragon Contributors
 * This program is made available under the terms of the MIT License.
 */

package vn.lequan.warrior.features.debug;


/**
 * Shows debug information of the current FPS of the game.
 */
public interface DebugFPS {

    void debug(long fps, boolean relative);

}
