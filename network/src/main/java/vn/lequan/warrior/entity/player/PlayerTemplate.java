/*
 * Copyright (c) 2017 Warrior Dragon Contributors
 * This program is made available under the terms of the MIT License.
 */

package vn.lequan.warrior.entity.player;


import java.util.EnumMap;
import java.util.Map;

import vn.lequan.warrior.entity.Action;
import vn.lequan.warrior.entity.Animation;
import vn.lequan.warrior.entity.Direction;
import vn.lequan.warrior.entity.Template;
import vn.lequan.warrior.util.App;

/**
 * A basic template for a {@link Player}.
 */
public class PlayerTemplate extends Template {

    public PlayerTemplate(App app) {
        super(app, 32, 36);
        setShadow(app.getTexture("shadow_32px"));
    }

    @Override
    public Map<Action, EnumMap<Direction, Animation>> getAnimations() {
        EnumMap<Action, EnumMap<Direction, Animation>> animations = new EnumMap<>(Action.class);

        animations.put(Action.IDLE, extractIdleAnimations());
        animations.put(Action.MOVE, extractMoveAnimations());

        return animations;
    }

    /**
     * Extract the Idle Animations from the Template's bitmap.
     *
     * @return An EnumMap with the Idle {@link Animation} corresponding to each {@link Direction}.
     */
    private EnumMap<Direction, Animation> extractIdleAnimations() {
        EnumMap<Direction, Animation> animations = new EnumMap<>(Direction.class);

        int frameLength = 600;

        animations.put(Direction.DOWN, createAnimation(frameLength, "player_idle_down", 2));
        animations.put(Direction.LEFT, createAnimation(frameLength, "player_idle_left", 2));
        animations.put(Direction.RIGHT, createAnimation(frameLength, "player_idle_right", 2));
        animations.put(Direction.UP, createAnimation(frameLength, "player_idle_up", 2));

        return animations;
    }

    /**
     * Extract the Move Animations from the Template's bitmap.
     *
     * @return An EnumMap with the Move {@link Animation} corresponding to each {@link Direction}.
     */
    private EnumMap<Direction, Animation> extractMoveAnimations() {
        EnumMap<Direction, Animation> animations = new EnumMap<>(Direction.class);

        int frameLength = 80;

        animations.put(Direction.DOWN, createAnimation(frameLength, "player_move_down", 6));
        animations.put(Direction.LEFT, createAnimation(frameLength, "player_move_left", 6));
        animations.put(Direction.RIGHT, createAnimation(frameLength, "player_move_right", 6));
        animations.put(Direction.UP, createAnimation(frameLength, "player_move_up", 6));

        for (Animation animation : animations.values()) {
            animation.setBounce(false);
        }

        return animations;
    }

}
