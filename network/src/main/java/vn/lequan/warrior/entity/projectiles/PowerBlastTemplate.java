/*
 * Copyright (c) 2017 Warrior Dragon Contributors
 * This program is made available under the terms of the MIT License.
 */

package vn.lequan.warrior.entity.projectiles;


import java.util.EnumMap;
import java.util.Map;

import vn.lequan.warrior.entity.Action;
import vn.lequan.warrior.entity.Animation;
import vn.lequan.warrior.entity.Direction;
import vn.lequan.warrior.entity.Template;
import vn.lequan.warrior.util.App;

/**
 * A basic template for a {@link PowerBlast}.
 */
public class PowerBlastTemplate extends Template {

    public PowerBlastTemplate(App app) {
        super(app, 30, 30);
    }

    @Override
    public Map<Action, EnumMap<Direction, Animation>> getAnimations() {
        EnumMap<Action, EnumMap<Direction, Animation>> animations = new EnumMap<>(Action.class);

        animations.put(Action.MOVE, extractMoveAnimations());
        animations.put(Action.EXPLODE, extractExplodeAnimations());

        return animations;
    }

    /**
     * Extract the Move Animations from the Template's bitmap.
     *
     * @return An EnumMap with the Move {@link Animation} corresponding to each {@link Direction}.
     */
    private EnumMap<Direction, Animation> extractMoveAnimations() {
        EnumMap<Direction, Animation> animations = new EnumMap<>(Direction.class);

        int frameLength = 600;

        for (Direction direction : Direction.values()) {
            Animation animation = createAnimation(frameLength, "power_blast_move", 1);
            animation.setBounce(false);

            animations.put(direction, animation);
        }

        return animations;
    }

    /**
     * Extract the Explode Animations from the Template's bitmap.
     *
     * @return An EnumMap with the Explode {@link Animation} corresponding to each {@link Direction}.
     */
    private EnumMap<Direction, Animation> extractExplodeAnimations() {
        EnumMap<Direction, Animation> animations = new EnumMap<>(Direction.class);

        int frameLength = 80;

        for (Direction direction : Direction.values()) {
            Animation animation = createAnimation(frameLength, "power_blast_explode", 9);
            animation.setBounce(false);

            animations.put(direction, animation);
        }

        return animations;
    }

}
