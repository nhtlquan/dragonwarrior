/*
 * Copyright (c) 2017 Warrior Dragon Contributors
 * This program is made available under the terms of the MIT License.
 */

package vn.lequan.warrior.entity.ai;


import com.badlogic.gdx.ai.steer.behaviors.Arrive;
import com.badlogic.gdx.ai.steer.behaviors.Wander;
import com.badlogic.gdx.ai.steer.limiters.LinearAccelerationLimiter;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

/**
 * An utility class used for AI.
 */
public class AI {

    private AI() {
    }

    public static void addArriveSB(AISteeringEntity steeringEntity) {
        Arrive<Vector2> arrive = new Arrive<>(steeringEntity, steeringEntity)
                .setArrivalTolerance(2f)
                .setDecelerationRadius(10);

        steeringEntity.setTargetableBehavior(arrive);
        steeringEntity.setSteeringBehavior(arrive);
    }

    public static void addWanderSB(AISteeringEntity steeringEntity) {
        Wander<Vector2> wander = new Wander<Vector2>(steeringEntity)
                .setFaceEnabled(false)
                .setLimiter(new LinearAccelerationLimiter(4))
                .setWanderOffset(3)
                .setWanderOrientation(5)
                .setWanderRadius(1)
                .setWanderRate(MathUtils.PI2 * 4);

        steeringEntity.setSteeringBehavior(wander);
    }

}
