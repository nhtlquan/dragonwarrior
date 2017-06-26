/*
 * Copyright (c) 2017 Warrior Dragon Contributors
 * This program is made available under the terms of the MIT License.
 */

package vn.lequan.warrior.config.module;


import com.badlogic.gdx.ai.steer.behaviors.Arrive;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import dagger.Module;
import dagger.Provides;
import vn.lequan.warrior.entity.Entity;
import vn.lequan.warrior.entity.SpriteSheet;
import vn.lequan.warrior.entity.ai.AI;
import vn.lequan.warrior.entity.ai.AISteeringEntity;
import vn.lequan.warrior.entity.monsters.Jiangshi;
import vn.lequan.warrior.entity.monsters.JiangshiTemplate;
import vn.lequan.warrior.entity.player.Player;
import vn.lequan.warrior.entity.player.PlayerTemplate;
import vn.lequan.warrior.util.App;

import static vn.lequan.warrior.util.Constants.ENEMY;
import static vn.lequan.warrior.util.Constants.FRIEND;
import static vn.lequan.warrior.util.Constants.MASK_ENEMY;
import static vn.lequan.warrior.util.Constants.MASK_FRIEND;
import static vn.lequan.warrior.util.Constants.PPM;

@Module
public class CharacterModule {

    @Provides
    Player providePlayer(App app, PlayerTemplate template) {
        SpriteSheet spriteSheet = new SpriteSheet(app, template);

        Player player = new Player(app, spriteSheet);
        Body body = createBody(player, app.getWorld(), FRIEND, MASK_FRIEND);
        player.setBody(body);

        AISteeringEntity steeringEntity = new AISteeringEntity(body, 0, 25, 300, 50, 50);

        Arrive<Vector2> steeringBehavior = new Arrive<>(steeringEntity, steeringEntity)
                .setArrivalTolerance(2f)
                .setDecelerationRadius(5);

        steeringEntity.setTargetableBehavior(steeringBehavior);
        steeringEntity.setSteeringBehavior(steeringBehavior);

        player.setSteeringEntity(steeringEntity);

        return player;
    }

    @Provides
    Jiangshi provideJiangshi(App app, JiangshiTemplate template) {
        SpriteSheet spriteSheet = new SpriteSheet(app, template);

        Jiangshi jiangshi = new Jiangshi(app, spriteSheet);
        Body body = createBody(jiangshi, app.getWorld(), ENEMY, MASK_ENEMY);
        jiangshi.setBody(body);

        AISteeringEntity steeringEntity = new AISteeringEntity(body, 10, 15, 200, 50, 50);
        AI.addArriveSB(steeringEntity);

        jiangshi.setSteeringEntity(steeringEntity);

        return jiangshi;
    }

    private Body createBody(Entity entity, World world, short categoryBits, short maskBits) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        Body body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = 0.5f;
        fixtureDef.filter.categoryBits = categoryBits;
        fixtureDef.filter.maskBits = maskBits;

        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(20 / PPM);

        fixtureDef.shape = circleShape;
        body.createFixture(fixtureDef).setUserData(entity);

        return body;
    }

}
