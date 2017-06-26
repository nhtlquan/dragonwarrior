/*
 * Copyright (c) 2017 Warrior Dragon Contributors
 * This program is made available under the terms of the MIT License.
 */

package vn.lequan.warrior.util;


import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import vn.lequan.warrior.ui.position.Position;

import static vn.lequan.warrior.util.Constants.CELL_SIZE;
import static vn.lequan.warrior.util.Constants.PPM;
import static vn.lequan.warrior.util.Constants.SCALE;

/**
 * An utility class used for position conversions.
 */
public class Positions {

    private Positions() {
    }

    /**
     * Converts a cell array coordinate into a map coordinate.
     *
     * @return The corresponding map coordinate.
     */
    public static int determineMapPosCoord(int cellArrayCoordinate) {
        return cellArrayCoordinate * CELL_SIZE * SCALE + (CELL_SIZE / 2 * SCALE);
    }

    /**
     * Calculates distance between two positions.
     *
     * @param position1 Origin position
     * @param position2 Destination position
     * @return Distance between origin position and destination position.
     */
    public static double calculateDistance(Vector2 position1, Vector2 position2) {
        float x = position1.x - position2.x;
        float y = position1.y - position2.y;

        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    /**
     * Scales down the position coordinate by CANVAS_SCALE.
     */
    public static int scaleDown(int coordinate) {
        return coordinate / SCALE;
    }

    /**
     * Determines the position where to draw a text inside a texture in order for it to be
     * centered.
     *
     * @param texture The texture where to draw a centered text inside.
     * @param layout  The layout surrounding the text.
     * @param pos     The position to be returned where to draw a text inside a texture in order
     *                for it to be centered in the texture.
     */
    public static void determineAlignCenterPos(TextureRegion texture, GlyphLayout layout, Position pos) {
        int x = (int) ((texture.getRegionWidth() * SCALE / 2f) - (layout.width / 2f));
        int y = (int) ((texture.getRegionHeight() * SCALE / 2f) + (layout.height / 2f));

        pos.set(x, y);
    }

    public static int convertWorldCoordToMap(float coordinate) {
        return (int) (coordinate * PPM);
    }

    public static float convertMapCoordToWorld(int coordinate) {
        return coordinate / PPM;
    }

    public static float vectorToAngle(Vector2 vector) {
        return (float) (Math.atan2(vector.x, -vector.y) + Math.PI);
    }

    public static Vector2 angleToVector(Vector2 vector, float angle) {
        vector.x = -(float) Math.sin(angle);
        vector.y = (float) Math.cos(angle);

        return vector;
    }

}
