/*
 * Copyright (c) 2017 Warrior Dragon Contributors
 * This program is made available under the terms of the MIT License.
 */

package vn.lequan.warrior.entity;


import vn.lequan.warrior.map.EntityManager;

/**
 * A game actor controlled or not by the player.
 */
public abstract class Character extends Entity {

    /**
     * Number of health points this Character has. When health points reach 0 the Character dies.
     */
    protected int healthPoints;

    /**
     * Number of maximum health points this Character can have.
     */
    protected int maxHealthPoints;

    @Override
    public void update(long fps, float delta) {
        super.update(fps, delta);

        if (isMoving()) {
            spriteSheet.setAction(Action.MOVE);
        } else {
            spriteSheet.setAction(Action.IDLE);
        }

        updateStatus(); //FIXME

        if (!getStatus().equals(Status.REMOVE)) {
            spriteSheet.updateAnimation();
        }
    }

    public void attack(EntityManager entityManager) {
    }

    public void takeDamage(int damage) {
        setStatus(Status.HIT);
        healthPoints -= damage;

        if (healthPoints <= 0) {
            setStatus(Status.DIE);
        }
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    protected void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public boolean isAlive() {
        return healthPoints > 0;
    }

    public int getMaxHealthPoints() {
        return maxHealthPoints;
    }

    protected void setMaxHealthPoints(int maxHealthPoints) {
        this.maxHealthPoints = maxHealthPoints;
    }

}
