/*
 * Copyright (c) 2017 Warrior Dragon Contributors
 * This program is made available under the terms of the MIT License.
 */

package vn.lequan.warrior.entity;


import com.badlogic.gdx.physics.box2d.WorldManifold;

/**
 * A hostile game actor controlled by the MoveAI.
 */
public class Monster extends Character {

    @Override
    public void takeDamage(int damage) {
        if (getHealthPoints() > 0) {
            super.takeDamage(damage);
        }
    }

    @Override
    public void collides(Object object, WorldManifold worldManifold) {
        // TODO
    }

}
