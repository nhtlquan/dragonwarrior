/*
 * Copyright (c) 2017 Warrior Dragon Contributors
 * This program is made available under the terms of the MIT License.
 */

package vn.lequan.warrior.util;

/**
 * A timer to indicate when cycles complete.
 */
public class Cycle {

    /**
     * Time when the cycle starts.
     */
    private long startTime;

    /**
     * The time the cycle will last in milliseconds.
     */
    private int lengthInMilliseconds;

    public Cycle(int lengthInMilliseconds) {
        set(lengthInMilliseconds);
    }

    public void set(int lengthInMilliseconds) {
        this.lengthInMilliseconds = lengthInMilliseconds;
        startTime = System.currentTimeMillis();
    }

    /**
     * Checks if the cycle is completed and in that case restarts it.
     *
     * @return {@code true} if the cycle is completed; {@code false} otherwise.
     */
    public boolean isCompleted() {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - startTime;

        if (elapsedTime >= lengthInMilliseconds) {
            startTime = currentTime;

            return true;
        } else {
            return false;
        }
    }

    @SuppressWarnings("squid:S00122")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cycle cycle = (Cycle) o;

        return lengthInMilliseconds == cycle.lengthInMilliseconds;

    }

    @Override
    public int hashCode() {
        return lengthInMilliseconds;
    }

}