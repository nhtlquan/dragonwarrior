/*
 * Copyright (c) 2017 Warrior Dragon Contributors
 * This program is made available under the terms of the MIT License.
 */

package vn.lequan.warrior.util;


/**
 * Controls the increase/decrease of a value.
 */
public class Counter {

    /**
     * The current value.
     */
    private int value;

    /**
     * The minimum value it can be.
     */
    private final int min;

    /**
     * The maximum value it can be.
     */
    private final int max;

    /**
     * The amount the value has to change on each set.
     */
    private final int change;

    /**
     * If the value is increasing or otherwise decreasing.
     */
    private boolean isIncreasing;

    /**
     * If it has to bounce.
     */
    private boolean bounce;

    public Counter(int value, int min, int max, int change, boolean isIncreasing, boolean bounce) {
        this.value = value;
        this.min = min;
        this.max = max;
        this.change = change;
        this.isIncreasing = isIncreasing;
        this.bounce = bounce;
    }

    /**
     * Updates the value, it has to increase until it reaches the max and then decrease until
     * it reaches the min in an endless loop.
     */
    public void update() {
        if (isIncreasing) {
            value += change;

            if (value >= max) {
                if (bounce) {
                    isIncreasing = false;
                } else {
                    value = min;
                }
            }
        } else {
            value -= change;

            if (value <= min) {
                if (bounce) {
                    isIncreasing = true;
                } else {
                    value = max;
                }
            }
        }
    }

    public int getValue() {
        return value;
    }

}