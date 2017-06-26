/*
 * Copyright (c) 2017 Warrior Dragon Contributors
 * This program is made available under the terms of the MIT License.
 */

package vn.lequan.warrior.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import vn.lequan.warrior.entity.Character;
import vn.lequan.warrior.entity.CharacterType;
import vn.lequan.warrior.entity.player.Player;
import vn.lequan.warrior.map.cells.Cell;
import vn.lequan.warrior.map.cells.CellType;
import vn.lequan.warrior.map.cells.Lamp;
import vn.lequan.warrior.map.cells.Slab;
import vn.lequan.warrior.util.App;
import vn.lequan.warrior.util.CharRepresentable;

import static vn.lequan.warrior.util.Constants.CELL_SIZE;
import static vn.lequan.warrior.util.Constants.SCALE;

/**
 * A parser to parse a text file into a Map.
 */
public class MapParser {

    private MapParser() {
    }

    /**
     * Parse a text file representing a Map. Read the file and use it to populate the matrix
     * of cells and combine all the cell bitmaps into one.
     *
     * @param map           The {@link Map} to be populated.
     * @param entityManager The {@link EntityManager} to manage entity additions.
     * @param player        The {@link Player} to be placed on the map.
     * @param mapAsset      A text file representing a Map.  @return The Map represented by the text file.
     */
    @SuppressWarnings("squid:S1160")
    public static void parse(App app, Map map, EntityManager entityManager, Player player, String mapAsset)
            throws IOException, InvalidMapException {

        FileHandle fileHandle = Gdx.files.internal(mapAsset);

        InputStream is = fileHandle.read();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        String line;
        ArrayList<String> lines = new ArrayList<>();
        int maxLineLength = 0;

        while ((line = br.readLine()) != null) {
            lines.add(line);
            maxLineLength = (maxLineLength < line.length()) ? line.length() : maxLineLength;
        }

        map.set(maxLineLength, lines.size());
        populate(app, lines, map, entityManager, player);

        map.setMapTiledMap(MapTiledMapCreator.draw(map));

        App.get().initRayHandler();

        for (int x = 0; x < map.getCols(); x++) {
            for (int y = map.getRows() - 1; y >= 0; y--) {
                if (Lamp.class.isInstance(map.getCell(x, y))) {
                    App.get().newPointLight(
                            (x * CELL_SIZE * SCALE) + (CELL_SIZE / 2 * SCALE),
                            (y * CELL_SIZE * SCALE) + (CELL_SIZE / 2 * SCALE));
                }
            }
        }
    }

    /**
     * Parse the file lines and use them to populate the matrix of cells.
     *
     * @param lines         Lines of characters representing Map cells.
     * @param map           Map to populate.
     * @param entityManager The {@link EntityManager} to manage entity additions.
     * @param player        The {@link Player} to be placed on the map.
     */
    private static void populate(App app, List<String> lines, Map map, EntityManager entityManager,
                                 Player player) throws InvalidMapException {

        int y = 0;

        for (int i = lines.size() - 1; i >= 0; i--) {
            int x = 0;

            for (char c : lines.get(i).toCharArray()) {
                populate(app, c, x, y, map, entityManager, player);

                x++;
            }

            y++;
        }
    }

    /**
     * Populates a single cell from the matrix of cells with the appropriate Cell type. In case
     * of a Character, populates the cell with a {@link Slab} and place
     * the character to the correct spot in the Map.
     *
     * @param c             A {@code char} representing a Cell type or a Character type.
     * @param x             X-coordinate.
     * @param y             Y-coordinate.
     * @param map           Map to populate.
     * @param entityManager The {@link EntityManager} to manage entity additions.
     * @param player        The {@link Player} to be placed on the map.
     */
    private static void populate(App app, char c, int x, int y, Map map, EntityManager entityManager,
                                 Player player) throws InvalidMapException {

        if (isMember(c, CellType.values())) {
            CharRepresentable[] cellTypes = CellType.values();
            map.setCell(x, y, (Cell) determineRepresented(app, cellTypes, c));
        } else {
            map.setCell(x, y, (Cell) CellType.SLAB.getInstance());

            if (c == CharacterType.PLAYER.getC()) {
                addPlayer(player, x, y, entityManager);
            } else {
                CharRepresentable[] characterTypes = CharacterType.values();
                Character character = (Character) determineRepresented(app, characterTypes, c);
                entityManager.addEntity(character, x, y);
            }
        }

        map.getCell(x, y).set(x, y);

    }

    private static boolean isMember(char c, CharRepresentable[] charRepresentables) {
        for (CharRepresentable charRepresentable : charRepresentables) {
            if (c == charRepresentable.getC()) {
                return true;
            }
        }

        return false;
    }

    /**
     * Determines which of the char representable objects represents the given {@code char}.
     *
     * @param charRepresentables An array of char representable objects.
     * @param c                  A {@code char} representing an object.
     * @return The char representable object that represents the given {@code char}.
     */
    private static Object determineRepresented(App app, CharRepresentable[] charRepresentables,
                                               char c) throws InvalidMapException {
        for (CharRepresentable charRepresentable : charRepresentables) {
            if (c == charRepresentable.getC()) {
                return charRepresentable.getInstance();
            }
        }

        throw new InvalidMapException(
                app.getMessage("map_exceptioninvalidcharacter", String.valueOf(c)));
    }

    /**
     * Sets the position of the Player in the Map and Screen, makes the camera follow it and add
     * it to the list of characters.
     *
     * @param player        The Player.
     * @param x             X-coordinate.
     * @param y             Y-coordinate.
     * @param entityManager The {@link EntityManager} to manage entity additions.
     */
    private static void addPlayer(Player player, int x, int y, EntityManager entityManager) {
        entityManager.addEntity(player, x, y);
    }

}
