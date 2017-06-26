/*
 * Copyright (c) 2017 Warrior Dragon Contributors
 * This program is made available under the terms of the MIT License.
 */

package vn.lequan.warrior.entity.projectiles;


import vn.lequan.warrior.entity.SpriteSheet;
import vn.lequan.warrior.util.App;

/**
 * A soul breaker attack that moves towards the target and explodes.
 */
public class SoulBreaker extends Projectile {

    public SoulBreaker(App app, SpriteSheet spriteSheet) {
        this.app = app;
        this.spriteSheet = spriteSheet;
        damage = 1;
    }

}
