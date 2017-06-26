/*
 * Copyright (c) 2017 Warrior Dragon Contributors
 * This program is made available under the terms of the MIT License.
 */

package vn.lequan.warrior.entity.items;


import java.util.EnumMap;
import java.util.Map;

import vn.lequan.warrior.entity.Action;
import vn.lequan.warrior.entity.Animation;
import vn.lequan.warrior.entity.Direction;
import vn.lequan.warrior.entity.Template;
import vn.lequan.warrior.util.App;

/**
 * A basic template for a {@link Coin}.
 */
public class CoinTemplate extends Template {

    public CoinTemplate(App app) {
        super(app, 16, 18);
    }

    @Override
    public Map<Action, EnumMap<Direction, Animation>> getAnimations() {
        EnumMap<Action, EnumMap<Direction, Animation>> animations = new EnumMap<>(Action.class);
        animations.put(Action.IDLE, extractIdleAnimations());

        return animations;
    }

    /**
     * Extract the Idle Animations from the Template's bitmap.
     *
     * @return An EnumMap with the Idle {@link Animation} corresponding to each {@link Direction}.
     */
    private EnumMap<Direction, Animation> extractIdleAnimations() {
        EnumMap<Direction, Animation> animations = new EnumMap<>(Direction.class);

        int frameLength = 140;

        for (Direction direction : Direction.values()) {
            Animation animation = createAnimation(frameLength, "item_coin", 4);
            animation.setBounce(false);

            animations.put(direction, animation);
        }

        return animations;
    }

}