/*
 * Copyright (c) 2017 Warrior Dragon Contributors
 * This program is made available under the terms of the MIT License.
 */

package vn.lequan.warrior.util;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class Constants {

    public static final boolean DEBUG_FPS_ENABLED = true;
    public static final boolean DEBUG_BOX2D = false;

    // max 16 collision categories, binary converted to hex
    public static final short FRIEND = 0x0002; // 0000 0000 0000 0001
    public static final short ENEMY = 0x0004; // 0000 0000 0000 0010
    public static final short WALLS = 0x0008; // 0000 0000 0000 0100
    public static final short FRIEND_PROJECTILE = 0x0010; // 0000 0000 0000 1000
    public static final short ENEMY_PROJECTILE = 0x0020; // 0000 0000 0001 0000
    public static final short ITEM = 0x0040; // 0000 0000 0010 0000

    public static final short MASK_FRIEND = ENEMY | FRIEND | ENEMY_PROJECTILE | WALLS | ITEM;
    public static final short MASK_ENEMY = FRIEND | ENEMY | FRIEND_PROJECTILE | WALLS | ITEM;
    public static final short MASK_WALLS = FRIEND | ENEMY | FRIEND_PROJECTILE | ENEMY_PROJECTILE | ITEM;
    public static final short MASK_FRIEND_PROJECTILE = ENEMY | WALLS;
    public static final short MASK_ENEMY_PROJECTILE = FRIEND | WALLS;
    public static final short MASK_ITEM = FRIEND | ENEMY | WALLS;

    public static final String VERSION = "v0.2.1";

    /**
     * Scale in which to draw objects on the screen.
     */
    public static final int SCALE = 4;

    /**
     * Pixels per meter.
     */
    public static final float PPM = 25f; // 25px = 1m, 1px = 4cm, 32px = 128cm

    public static final int CELL_SIZE = 32;

    public static final int DESKTOP_WIDTH = 1080;
    public static final int DESKTOP_HEIGHT = 1920;

    private static final String TYPEFACE = "fonts/wendy.ttf";
    private static final String SCORE_TYPEFACE = "fonts/dice.ttf";

    private static final String FONT_CHARACTERS =
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789./-";

    public static final BitmapFont FILL_80_WHITE;
    public static final BitmapFont FILL_80_WHITE_W_BORDER;
    public static final BitmapFont FILL_120_WHITE_W_BORDER;
    public static final BitmapFont FILL_80_BROWN;
    public static final BitmapFont FILL_80_DARK_ORANGE;
    public static final BitmapFont FILL_80_LIGHT_ORANGE;
    public static final BitmapFont FILL_80_GREEN;
    public static final BitmapFont FILL_80_ORANGE;
    public static final BitmapFont FILL_80_RED;

    static {
        FILL_80_WHITE = fill(80, 0, TYPEFACE);
        FILL_80_WHITE.setColor(Color.WHITE);

        FILL_80_WHITE_W_BORDER = fill(60, 6, SCORE_TYPEFACE);
        FILL_80_WHITE_W_BORDER.setColor(Color.CLEAR);

        FILL_120_WHITE_W_BORDER = fill(120, 8, SCORE_TYPEFACE);
        FILL_120_WHITE_W_BORDER.setColor(Color.CLEAR);

        FILL_80_BROWN = fill(80, 0, TYPEFACE);
        FILL_80_BROWN.setColor(173/255f, 126/255f, 63/255f, 1);

        FILL_80_DARK_ORANGE = fill(80, 0, TYPEFACE);
        FILL_80_DARK_ORANGE.setColor(185/255f, 79/255f, 0, 1);

        FILL_80_LIGHT_ORANGE = fill(80, 0, TYPEFACE);
        FILL_80_LIGHT_ORANGE.setColor(1, 134/255f, 45/255f, 1);

        FILL_80_GREEN = fill(80, 0, TYPEFACE);
        FILL_80_GREEN.setColor(36/255f, 208/255f, 0, 1);

        FILL_80_ORANGE = fill(80, 0, TYPEFACE);
        FILL_80_ORANGE.setColor(249/255f, 129/255f, 0, 1);

        FILL_80_RED = fill(80, 0, TYPEFACE);
        FILL_80_RED.setColor(249/255f, 12/255f, 0, 1);
    }

    private static BitmapFont fill(int size, float borderSize, String typeface) {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(typeface));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = size;
        parameter.characters = FONT_CHARACTERS;
        parameter.mono = true;

        if (borderSize > 0) {
            parameter.hinting = FreeTypeFontGenerator.Hinting.Slight;
            parameter.borderWidth = borderSize;
        }

        BitmapFont font = generator.generateFont(parameter);
        generator.dispose();

        return font;
    }

}
