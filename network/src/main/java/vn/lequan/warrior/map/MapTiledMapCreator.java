/*
 * Copyright (c) 2017 Warrior Dragon Contributors
 * This program is made available under the terms of the MIT License.
 */

package vn.lequan.warrior.map;


import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import vn.lequan.warrior.map.cells.Carpet;
import vn.lequan.warrior.map.cells.Cell;
import vn.lequan.warrior.map.cells.Lamp;
import vn.lequan.warrior.map.cells.Slab;
import vn.lequan.warrior.map.cells.Unfilled;
import vn.lequan.warrior.map.cells.Wall;

import static vn.lequan.warrior.util.Constants.CELL_SIZE;

/**
 * An utility class used to draw the {@link Map} cells' in the proper order.
 */
class MapTiledMapCreator {

    private MapTiledMapCreator() {
    }

    static TiledMap draw(Map map) {
        TiledMap tiledMap = new TiledMap();
        MapLayers layers = tiledMap.getLayers();

        TiledMapTileLayer layer1 = new TiledMapTileLayer(map.getCols(), map.getRows(), CELL_SIZE, CELL_SIZE);
        TiledMapTileLayer layer2 = new TiledMapTileLayer(map.getCols(), map.getRows(), CELL_SIZE, CELL_SIZE);

        TiledMapTileLayer[] tiledMapLayers = new TiledMapTileLayer[]{layer1, layer2};

        addCellsIfTypeMatches(tiledMapLayers, new Class[]{Unfilled.class, Slab.class, Carpet.class}, map);
        addCellsIfTypeMatches(tiledMapLayers, new Class[]{Lamp.class, Wall.class}, map);

        for (int x = 0; x < map.getCols(); x++) {
            for (int y = map.getRows() - 1; y >= 0; y--) {
                map.getCell(x, y).draw(tiledMapLayers, map);

                if (map.getCell(x, y).isBody()) {
                    map.getCell(x, y).createBody();
                }
            }
        }

        for (int y = map.getRows() - 1; y >= 0; y--) {
            for (int x = 0; x < map.getCols(); x++) {
                map.getCell(x, y).drawShadows(tiledMapLayers, map);
            }
        }

        layers.add(layer1);
        layers.add(layer2);

        return tiledMap;
    }

    private static void addCellsIfTypeMatches(TiledMapTileLayer[] layers, Class[] classes, Map map) {
        for (int x = 0; x < map.getCols(); x++) {
            for (int y = map.getRows() - 1; y >= 0; y--) {
                addCellIfTypeMatches(layers, classes, map.getCell(x, y), map);
            }
        }
    }

    private static void addCellIfTypeMatches(TiledMapTileLayer[] layers, Class[] classes, Cell cell, Map map) {
        for (Class clazz : classes) {
            if (clazz.isInstance(cell)) {
                addCell(layers, cell, map);
                break;
            }
        }
    }

    private static void addCell(TiledMapTileLayer[] layers, Cell cell, Map map) {
        cell.selectDrawableResource(map);
    }

}
