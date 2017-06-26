/*
 * Copyright (c) 2017 Warrior Dragon Contributors
 * This program is made available under the terms of the MIT License.
 */

package vn.lequan.warrior.entity.monsters;


import java.util.concurrent.ThreadLocalRandom;

import vn.lequan.warrior.config.component.DaggerEntityComponent;
import vn.lequan.warrior.entity.Action;
import vn.lequan.warrior.entity.Monster;
import vn.lequan.warrior.entity.SpriteSheet;
import vn.lequan.warrior.entity.projectiles.Projectile;
import vn.lequan.warrior.map.EntityManager;
import vn.lequan.warrior.util.App;
import vn.lequan.warrior.util.Cycle;

/**
 * A game actor controlled by the AI.
 * <p>
 * A Jiangshi, also known as a Chinese vampire, ghost, or zombie, is a type of reanimated corpse
 * in Chinese legends and folklore.
 * </p>
 */
public class Jiangshi extends Monster {

    private Cycle attackCycle;

    public Jiangshi(App app, SpriteSheet spriteSheet) {
        this.app = app;
        this.spriteSheet = spriteSheet;
        healthPoints = 3;
        maxHealthPoints = 3;
        damage = 1;

        int random = ThreadLocalRandom.current().nextInt(0, 9);
        attackCycle = new Cycle(1000 + (random * 100));
    }

    public void attack(EntityManager entityManager) {
        if (attackCycle.isCompleted() && entityManager.getPlayer().isAlive()) {
            Projectile projectile = DaggerEntityComponent.builder().build().provideSoulBreaker();
            projectile.setAction(Action.MOVE);
            projectile.setFriendly(false);

            entityManager.addEntity(projectile, getPosition());
            projectile.setupMovement(getPosition(), entityManager.getPlayer().getPosition());
        }
    }

}
