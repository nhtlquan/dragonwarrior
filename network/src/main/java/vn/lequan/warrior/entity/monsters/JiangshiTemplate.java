/*
 * Copyright (c) 2017 Warrior Dragon Contributors
 * This program is made available under the terms of the MIT License.
 */

package vn.lequan.warrior.entity.monsters;


import java.util.EnumMap;
import java.util.Map;

import vn.lequan.warrior.entity.Action;
import vn.lequan.warrior.entity.Animation;
import vn.lequan.warrior.entity.Direction;
import vn.lequan.warrior.entity.Template;
import vn.lequan.warrior.util.App;

/**
 * A basic template for a {@link Jiangshi}.
 */
public class JiangshiTemplate extends Template {

    public JiangshiTemplate(App app) {
        super(app, 32, 38);
        setShadow(app.getTexture("shadow_32px"));
    }

    @Override
    public Map<Action, EnumMap<Direction, Animation>> getAnimations() {
        EnumMap<Action, EnumMap<Direction, Animation>> animations = new EnumMap<>(Action.class);

        animations.put(Action.IDLE, extractIdleAnimations());
        animations.put(Action.MOVE, extractWalkAnimations());

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

        animations.put(Direction.DOWN, createAnimation(frameLength, "jiangshi_idle_down", 2));
        animations.put(Direction.LEFT, createAnimation(frameLength, "jiangshi_idle_left", 2));
        animations.put(Direction.RIGHT, createAnimation(frameLength, "jiangshi_idle_right", 2));
        animations.put(Direction.UP, createAnimation(frameLength, "jiangshi_idle_up", 2));

        return animations;
    }

    /**
     * Extract the Walk Animations from the Template's bitmap.
     *
     * @return An EnumMap with the Walk {@link Animation} corresponding to each {@link Direction}.
     */
    private EnumMap<Direction, Animation> extractWalkAnimations() {
        EnumMap<Direction, Animation> animations = new EnumMap<>(Direction.class);

        int frameLength = 200;

        animations.put(Direction.DOWN, createAnimation(frameLength, "jiangshi_move_down", 2));
        animations.put(Direction.LEFT, createAnimation(frameLength, "jiangshi_move_left", 2));
        animations.put(Direction.RIGHT, createAnimation(frameLength, "jiangshi_move_right", 2));
        animations.put(Direction.UP, createAnimation(frameLength, "jiangshi_move_up", 2));

        return animations;
    }

}