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
import vn.lequan.warrior.entity.Action;
import vn.lequan.warrior.entity.Direction;
import vn.lequan.warrior.entity.Entity;
import vn.lequan.warrior.entity.SpriteSheet;
import vn.lequan.warrior.entity.ai.AI;
import vn.lequan.warrior.entity.ai.AISteeringEntity;
import vn.lequan.warrior.entity.items.Coin;
import vn.lequan.warrior.entity.items.CoinTemplate;
import vn.lequan.warrior.util.App;

import static vn.lequan.warrior.util.Constants.ITEM;
import static vn.lequan.warrior.util.Constants.MASK_ITEM;
import static vn.lequan.warrior.util.Constants.PPM;

@Module
public class ItemModule {

    @Provides
    Coin provideCoin(App app, CoinTemplate template) {
        SpriteSheet spriteSheet = new SpriteSheet(app, template);
        spriteSheet.setAction(Action.IDLE);
        spriteSheet.setDirection(Direction.NONE);

        Coin coin = new Coin(app, spriteSheet);
        Body body = createBody(coin, app.getWorld());
        coin.setBody(body);

        AISteeringEntity steeringEntity = new AISteeringEntity(body, 100, 15, 200, 50, 50);
        AI.addArriveSB(steeringEntity);

        coin.setSteeringEntity(steeringEntity);

        return coin;
    }

    private Body createBody(Entity entity, World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        Body body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = 0;
        fixtureDef.filter.categoryBits = ITEM;
        fixtureDef.filter.maskBits = MASK_ITEM;
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(15 / PPM);

        fixtureDef.shape = circleShape;
        body.createFixture(fixtureDef).setUserData(entity);

        return body;
    }

}
