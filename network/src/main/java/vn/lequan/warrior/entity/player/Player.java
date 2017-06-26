/*
 * Copyright (c) 2017 Warrior Dragon Contributors
 * This program is made available under the terms of the MIT License.
 */

package vn.lequan.warrior.entity.player;


import com.badlogic.gdx.physics.box2d.WorldManifold;

import vn.lequan.warrior.entity.Character;
import vn.lequan.warrior.entity.Direction;
import vn.lequan.warrior.entity.SpriteSheet;
import vn.lequan.warrior.map.cells.Cell;
import vn.lequan.warrior.util.App;

/**
 * A game actor controlled by the player.
 */
public class Player extends Character {

    private int score = 0;
    private String scoreText = "0";

    public Player(App app, SpriteSheet spriteSheet) {
        this.app = app;
        this.spriteSheet = spriteSheet;
        healthPoints =6;
        maxHealthPoints = 6;
    }

    @Override
    public void update(long fps, float delta) {
        super.update(fps, delta);

        float threshold = 0.35f;

        if (Math.abs(body.getPosition().x - target.getX()) < threshold
                && Math.abs(body.getPosition().y - target.getY()) < threshold) {
            stop();
        }
    }

    public void move(float velocityX, float velocityY) {
        float max = Math.max(Math.abs(velocityX), Math.abs(velocityY));
        int movementDistance = 20;

        if (max > 0) {
            float x = (velocityX / max * movementDistance) + body.getPosition().x;
            float y = (velocityY / max * movementDistance) + body.getPosition().y;

            target.set(x, y);
            steeringEntity.setSteeringTarget(target);
        }
    }

    @Override
    public void collides(Object object, WorldManifold worldManifold) {
        Direction collisionDirection = determineCollisionDirection(worldManifold);

        if (Cell.class.isInstance(object)) {
            collidesWithCell((Cell) object, collisionDirection);
        }
    }

    private Direction determineCollisionDirection(WorldManifold worldManifold) {
        Direction collisionDirection = Direction.NONE;

        if (worldManifold.getNumberOfContactPoints() > 0) {
            float x = worldManifold.getPoints()[0].x;
            float y = worldManifold.getPoints()[0].y;
            float threshold = 0.5f;

            if (Math.abs(x - body.getPosition().x) < threshold) {
                if (y > body.getPosition().y) {
                    collisionDirection = Direction.UP;
                } else {
                    collisionDirection = Direction.DOWN;
                }
            } else if (Math.abs(y - body.getPosition().y) < threshold) {
                if (x > body.getPosition().x) {
                    collisionDirection = Direction.RIGHT;
                } else {
                    collisionDirection = Direction.LEFT;
                }
            }
        }

        return collisionDirection;
    }

    private void collidesWithCell(Cell cell, Direction collisionDirection) {
        int degrees = (int) Math.toDegrees(body.getAngle());
        int threshold = 60;

        if (collisionDirection == Direction.UP
                && (degrees <= threshold || degrees >= 360 - threshold)) {
            stop();
        }

        if (collisionDirection == Direction.RIGHT
                && (degrees <= 270 + threshold && degrees >= 270 - threshold)) {
            stop();
        }

        if (collisionDirection == Direction.DOWN
                && (degrees <= 180 + threshold && degrees >= 180 - threshold)) {
            stop();
        }

        if (collisionDirection == Direction.LEFT
                && (degrees <= 90 + threshold && degrees >= 90 - threshold)) {
            stop();
        }
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
        scoreText = String.valueOf(score);
    }

    public String getScoreText() {
        return scoreText;
    }

}
