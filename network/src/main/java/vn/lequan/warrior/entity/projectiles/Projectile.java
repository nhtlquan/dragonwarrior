/*
 * Copyright (c) 2017 Warrior Dragon Contributors
 * This program is made available under the terms of the MIT License.
 */

package vn.lequan.warrior.entity.projectiles;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.WorldManifold;

import vn.lequan.warrior.entity.Action;
import vn.lequan.warrior.entity.Entity;
import vn.lequan.warrior.entity.Monster;
import vn.lequan.warrior.entity.Status;
import vn.lequan.warrior.entity.player.Player;

/**
 * An main attack that moves towards the target and explodes.
 */
public abstract class Projectile extends Entity {

    /**
     * Friendly projectiles are those thrown by the player and only damage enemies. Non friendly
     * projectiles on the contrary, are those thrown by the enemies and only damage the player.
     */
    private boolean friendly;

    @Override
    public void update(long fps, float delta) {
        super.update(fps, delta);

        if (!spriteSheet.getAction().equals(Action.MOVE)) {
            if (spriteSheet.getAction().equals(Action.EXPLODE) &&
                    spriteSheet.isAnimationCompleted()) {
                setStatus(Status.REMOVE);
            } else {
                spriteSheet.updateAnimation();
            }
        }
    }

    public void setFriendly(boolean friendly) {
        this.friendly = friendly;
    }

    @Override
    public void collides(Object object, WorldManifold worldManifold) {
        if (spriteSheet.getAction().equals(Action.MOVE)) {
            stop();
            spriteSheet.setAction(Action.EXPLODE);

            if(friendly && (Monster.class.isInstance(object))) {
                ((Monster) object).takeDamage(damage);
            } else if (!friendly && (Player.class.isInstance(object))) {
                ((Player) object).takeDamage(damage);
            }
        }
    }

    public void setupMovement(Vector2 origin, Vector2 target) {
        float x = target.x - origin.x;
        float y = target.y - origin.y;

        float max = Math.max(Math.abs(x), Math.abs(y));

        if (max > 0) {
            x = x / max * steeringEntity.getMaxLinearSpeed();
            y = y / max * steeringEntity.getMaxLinearSpeed();
            body.setLinearVelocity(x, y);
        }
    }

}
