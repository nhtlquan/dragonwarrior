/*
 * Copyright (c) 2017 Warrior Dragon Contributors
 * This program is made available under the terms of the MIT License.
 */

package vn.lequan.warrior.util;

/**
 * Interface for objects that can be updated.
 */
public interface Updateable {

    /**
     * Updates the object properties.
     *
     * @param fps Current fps value of the game.
     * @param delta Delta time.
     */
    void update(long fps, float delta);

}
