/*
 * Copyright (c) 2017 Warrior Dragon Contributors
 * This program is made available under the terms of the MIT License.
 */

package vn.lequan.warrior.entity;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import vn.lequan.warrior.entity.ai.AISteeringEntity;
import vn.lequan.warrior.ui.position.WorldLocation;
import vn.lequan.warrior.util.App;
import vn.lequan.warrior.util.Collisionable;
import vn.lequan.warrior.util.Cycle;
import vn.lequan.warrior.util.Drawable;
import vn.lequan.warrior.util.Updateable;

/**
 * A game entity. (e.g. A character, a fireball, etc.)
 */
public abstract class Entity implements Updateable, Drawable, Collisionable {
    protected App app;

    private Status status = Status.NORMAL;
    private Cycle statusCycle = new Cycle(1000);
    protected SpriteSheet spriteSheet;
    private boolean isVisibleOnScreen;
    protected int damage = 0;
    protected WorldLocation target = new WorldLocation();
    protected Body body;
    protected AISteeringEntity steeringEntity;

    public Status getStatus() {
        return status;
    }

    protected void setStatus(Status status) {
        this.status = status;
        statusCycle.set(status.getLengthInMilliseconds());
    }

    /**
     * Updates the status of the entity, change it back to normal if the length in milliseconds
     * of the current status has passed.
     */
    protected void updateStatus() {
        if (status != Status.NORMAL && status != Status.REMOVE && statusCycle.isCompleted()) {
            if (status == Status.DIE || status == Status.DESTROY) {
                status = Status.REMOVE;
            } else {
                status = Status.NORMAL;
            }
        }
    }

    /**
     * Sets the {@link Action} the entity is doing.
     *
     * @param action The {@link Action} the entity is doing.
     */
    public void setAction(Action action) {
        spriteSheet.setAction(action);
    }

    public int getWidth() {
        return spriteSheet.getTemplate().getWidth();
    }

    public int getHeight() {
        return spriteSheet.getTemplate().getHeight();
    }

    public boolean isVisibleOnScreen() {
        return isVisibleOnScreen;
    }

    public Body getBody() {
        return body;
    }

    @Override
    public void update(long fps, float delta) {
        if (status != Status.DIE) {
            steeringEntity.update(delta);
        }

        isVisibleOnScreen = determineIfIsVisibleOnScreen();
    }

    protected boolean isMoving() {
        float idleSpeed = 1;
        Vector2 velocity = body.getLinearVelocity();
        float currentSpeedSquare = velocity.len2();

        return currentSpeedSquare > idleSpeed * idleSpeed;
    }

    /**
     * Determines if the entity is visible on the screen.
     *
     * @return {@code true} if the entity is visible on the screen; {@code false} otherwise.
     */
    private boolean determineIfIsVisibleOnScreen() {
        // TODO
        return true;
    }

    @Override
    public void draw() {
        spriteSheet.updateAnimation();
        spriteSheet.setDirection(determineDirection());

        spriteSheet.setWorldLocation(body.getPosition());

        if (status.equals(Status.HIT) || status.equals(Status.DIE)) {
            app.changeColor(Color.WHITE);
            spriteSheet.draw(true);
            app.changeColor(Color.CLEAR);
        } else {
            spriteSheet.draw(false);
        }
    }

    /**
     * Determines the nearest direction of movement. It's used to select the appropriate
     * {@link Animation} from the {@link SpriteSheet}.
     *
     * @return Nearest direction of movement.
     */
    private Direction determineDirection() {
        int degrees = (int) Math.toDegrees(body.getAngle());

        if (degrees <= 315 && degrees >= 225) {
            return Direction.RIGHT;
        } else if (degrees <= 225 && degrees >= 135) {
            return Direction.DOWN;
        } else if (degrees <= 135 && degrees >= 45) {
            return Direction.LEFT;
        } else {
            return Direction.UP;
        }
    }

    public void destroyBody() {
        app.getWorld().destroyBody(body);
    }

    protected void stop() {
        steeringEntity.setSteeringTarget(steeringEntity);
        body.setLinearVelocity(0, 0);
    }

    @Override
    public AISteeringEntity getSteeringEntity() {
        return steeringEntity;
    }

    public void setSteeringEntity(AISteeringEntity steeringEntity) {
        this.steeringEntity = steeringEntity;
    }

    public Vector2 getPosition() {
        return body.getPosition();
    }

    public void setBody(Body body) {
        this.body = body;
    }

}
