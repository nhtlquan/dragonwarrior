/*
 * Copyright (c) 2017 Warrior Dragon Contributors
 * This program is made available under the terms of the MIT License.
 */

package vn.lequan.warrior.entity;


import vn.lequan.warrior.config.component.DaggerEntityComponent;
import vn.lequan.warrior.entity.monsters.Jiangshi;
import vn.lequan.warrior.entity.player.Player;
import vn.lequan.warrior.map.Map;
import vn.lequan.warrior.map.cells.Cell;
import vn.lequan.warrior.util.CharRepresentable;

/**
 * Constants for types of characters in the {@link Map}. A {@link Map} can be imported from a plain
 * text file composed of chars. Each char represents a type of {@link Cell} and can also represent
 * a type of {@link Character}. (e.g. An '@' represents the {@link Player})
 */
public enum CharacterType implements CharRepresentable {
    PLAYER('@') {
        @Override
        public Player getInstance() {
            return DaggerEntityComponent.builder().build().providePlayer();
        }
    },
    JIANGSHI('J') {
        @Override
        public Jiangshi getInstance() {
            return DaggerEntityComponent.builder().build().provideJiangshi();
        }
    };

    /**
     * A {@code char} that represents a type of {@link Character}.
     */
    private char c;

    /**
     * Constructs a type of {@link Character}.
     *
     * @param c The {@code char} that represents this type of {@link Character}
     */
    CharacterType(char c) {
        this.c = c;
    }

    @Override
    public char getC() {
        return c;
    }

}
