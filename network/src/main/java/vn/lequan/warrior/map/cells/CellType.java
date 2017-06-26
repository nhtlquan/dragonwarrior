/*
 * Copyright (c) 2017 Warrior Dragon Contributors
 * This program is made available under the terms of the MIT License.
 */

package vn.lequan.warrior.map.cells;


import vn.lequan.warrior.config.component.DaggerCellComponent;
import vn.lequan.warrior.map.Map;
import vn.lequan.warrior.util.CharRepresentable;

/**
 * Constants for types of cells in the {@link Map}. A {@link Map} can be imported from a plain
 * text file composed of chars. Each char represents a type of {@link Cell}. (e.g. A '#' represents
 * a {@link Wall}, a '.' represents a {@link Slab}, etc.)
 */
public enum CellType implements CharRepresentable {
    WALL('#') {
        @Override
        public Wall getInstance() {
            return DaggerCellComponent.builder().build().provideWall();
        }
    },
    LAMP('o') {
        @Override
        public Lamp getInstance() {
            return DaggerCellComponent.builder().build().provideLamp();
        }
    },
    PAINTING('[') {
        @Override
        public Painting getInstance() {
            return DaggerCellComponent.builder().build().providePainting();
        }
    },
    SLAB('.') {
        @Override
        public Slab getInstance() {
            return DaggerCellComponent.builder().build().provideSlab();
        }
    },
    CARPET('_') {
        @Override
        public Carpet getInstance() {
            return DaggerCellComponent.builder().build().provideCarpet();
        }
    },
    UNFILLED('X') {
        @Override
        public Unfilled getInstance() {
            return DaggerCellComponent.builder().build().provideUnfilled();
        }
    };

    /**
     * A {@code char} that represents a type of {@link Cell}.
     */
    private char c;

    /**
     * Constructs a type of {@link Cell}.
     *
     * @param c The {@code char} that represents this type of {@link Cell}
     */
    CellType(char c) {
        this.c = c;
    }

    @Override
    public char getC() {
        return c;
    }

}
