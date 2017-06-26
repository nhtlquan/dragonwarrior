/*
 * Copyright (c) 2017 Warrior Dragon Contributors
 * This program is made available under the terms of the MIT License.
 */

package vn.lequan.warrior.util;


import com.badlogic.gdx.physics.box2d.WorldManifold;

import vn.lequan.warrior.entity.ai.AISteeringEntity;

public interface Collisionable {

    void collides(Object object, WorldManifold worldManifold);

    AISteeringEntity getSteeringEntity();

}
