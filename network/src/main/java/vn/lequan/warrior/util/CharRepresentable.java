/*
 * Copyright (c) 2017 Warrior Dragon Contributors
 * This program is made available under the terms of the MIT License.
 */

package vn.lequan.warrior.util;


/**
 * Interface for objects that can be represented as a {@code char} in a plain text file.
 */
public interface CharRepresentable {

    /**
     * Gets the {@code char} that represents the object.
     *
     * @return The {@code char} that represents the object.
     */
    char getC();

    /**
     * Gets an instance of the object class.
     *
     * @return An instance of the object class.
     */
    Object getInstance();

}
