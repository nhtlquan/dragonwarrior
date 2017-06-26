/*
 * Copyright (c) 2017 Warrior Dragon Contributors
 * This program is made available under the terms of the MIT License.
 */

package vn.lequan.warrior.entity.items;


import com.badlogic.gdx.physics.box2d.WorldManifold;

import vn.lequan.warrior.entity.SpriteSheet;
import vn.lequan.warrior.entity.Status;
import vn.lequan.warrior.entity.player.Player;
import vn.lequan.warrior.util.App;
import vn.lequan.warrior.util.Positions;

/**
 * A coin that spins and can be picked up.
 */
public class Coin extends Item {

    private Player player;

    public Coin(App app, SpriteSheet spriteSheet) {
        this.app = app;
        this.spriteSheet = spriteSheet;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public void update(long fps, float delta) {
        super.update(fps, delta);
        spriteSheet.updateAnimation();

        if (Positions.calculateDistance(player.getPosition(), body.getPosition()) < 10f) {
            steeringEntity.setSteeringTarget(player.getSteeringEntity());
        } else {
            stop();
        }
    }

    @Override
    public void collides(Object object, WorldManifold worldManifold) {
        if (Player.class.isInstance(object)) {
            body.getFixtureList().get(0).setSensor(true);
            Player player = (Player) object;
            player.setScore(player.getScore() + 1);
            setStatus(Status.REMOVE);
        }
    }

}
