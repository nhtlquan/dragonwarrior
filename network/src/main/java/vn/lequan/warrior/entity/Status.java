/*
 * Copyright (c) 2017 Warrior Dragon Contributors
 * This program is made available under the terms of the MIT License.
 */

package vn.lequan.warrior.entity;

/**
 * Constants for the Status in which an entity can be.
 */
public enum Status {
    NORMAL(0),
    HIT(100),
    DIE(100),
    REMOVE(0),
    DESTROY(60000),
    COLLIDE(60000);

    /**
     * Time in milliseconds that will last this Status, 0 means forever.
     */
    private int lengthInMilliseconds;

    Status(int lengthInMilliseconds) {
        this.lengthInMilliseconds = lengthInMilliseconds;
    }

    int getLengthInMilliseconds() {
        return lengthInMilliseconds;
    }
}
