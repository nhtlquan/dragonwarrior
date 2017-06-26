/*
 * Copyright (c) 2017 Warrior Dragon Contributors
 * This program is made available under the terms of the MIT License.
 */

package vn.lequan.warrior.config.module;


import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import dagger.Module;
import dagger.Provides;
import vn.lequan.warrior.entity.Entity;
import vn.lequan.warrior.entity.SpriteSheet;
import vn.lequan.warrior.entity.ai.AISteeringEntity;
import vn.lequan.warrior.entity.projectiles.PowerBlast;
import vn.lequan.warrior.entity.projectiles.PowerBlastTemplate;
import vn.lequan.warrior.entity.projectiles.SoulBreaker;
import vn.lequan.warrior.entity.projectiles.SoulBreakerTemplate;
import vn.lequan.warrior.util.App;

import static vn.lequan.warrior.util.Constants.ENEMY_PROJECTILE;
import static vn.lequan.warrior.util.Constants.FRIEND_PROJECTILE;
import static vn.lequan.warrior.util.Constants.MASK_ENEMY_PROJECTILE;
import static vn.lequan.warrior.util.Constants.MASK_FRIEND_PROJECTILE;
import static vn.lequan.warrior.util.Constants.PPM;

@Module
public class ProjectileModule {

    @Provides
    PowerBlast providePowerBlast(App app, PowerBlastTemplate template) {
        SpriteSheet spriteSheet = new SpriteSheet(app, template);

        PowerBlast powerBlast = new PowerBlast(app, spriteSheet);
        Body body = createBody(powerBlast, app.getWorld(), FRIEND_PROJECTILE, MASK_FRIEND_PROJECTILE);
        powerBlast.setBody(body);

        AISteeringEntity steeringEntity = new AISteeringEntity(body, 0, 30, 0, 0, 0);

        powerBlast.setSteeringEntity(steeringEntity);

        return powerBlast;
    }

    @Provides
    SoulBreaker provideSoulBreaker(App app, SoulBreakerTemplate template) {
        SpriteSheet spriteSheet = new SpriteSheet(app, template);

        SoulBreaker soulBreaker = new SoulBreaker(app, spriteSheet);
        Body body = createBody(soulBreaker, app.getWorld(), ENEMY_PROJECTILE, MASK_ENEMY_PROJECTILE);
        soulBreaker.setBody(body);

        AISteeringEntity steeringEntity = new AISteeringEntity(body, 0, 25, 0, 0, 0);

        soulBreaker.setSteeringEntity(steeringEntity);

        return soulBreaker;
    }

    private Body createBody(Entity entity, World world, short categoryBits, short maskBits) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        Body body = world.createBody(bodyDef);
        body.setBullet(true);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.filter.categoryBits = categoryBits;
        fixtureDef.filter.maskBits = maskBits;
        fixtureDef.friction = 1;
        fixtureDef.density = 100;
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(10 / PPM);

        fixtureDef.shape = circleShape;
        body.createFixture(fixtureDef).setUserData(entity);

        return body;
    }

}
