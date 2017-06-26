/*
 * Copyright (c) 2017 Warrior Dragon Contributors
 * This program is made available under the terms of the MIT License.
 */

package vn.lequan.warrior.map;

/**
 * Thrown to indicate that a text file representing a {@link Map} is invalid.
 */
public class InvalidMapException extends Exception {

    InvalidMapException(String message) {
        super(message);
    }

}