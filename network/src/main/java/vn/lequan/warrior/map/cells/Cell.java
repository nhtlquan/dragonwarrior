/*
 * Copyright (c) 2017 Warrior Dragon Contributors
 * This program is made available under the terms of the MIT License.
 */

package vn.lequan.warrior.map.cells;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.WorldManifold;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import vn.lequan.warrior.entity.ai.AISteeringEntity;
import vn.lequan.warrior.map.Map;
import vn.lequan.warrior.util.App;
import vn.lequan.warrior.util.Collisionable;
import vn.lequan.warrior.util.Positions;

import static vn.lequan.warrior.util.Constants.CELL_SIZE;
import static vn.lequan.warrior.util.Constants.MASK_WALLS;
import static vn.lequan.warrior.util.Constants.PPM;
import static vn.lequan.warrior.util.Constants.SCALE;
import static vn.lequan.warrior.util.Constants.WALLS;

/**
 * A small part of the {@link Map}. The {@link Map} is composed by Cells of different
 * types. (e.g. {@link Wall}, {@link Slab}, etc.)
 */
public abstract class Cell implements Collisionable {
    private App app;

    /**
     * The x-coordinate in the array of cells that represent the {@link Map}.
     */
    private int x;

    /**
     * The y-coordinate in the array of cells that represent the {@link Map}.
     */
    private int y;

    protected String[] assets;

    private TextureRegion[] randomTextureRegions;

    int adjustmentY;

    /**
     * {@code true} if a character can walk through this Cell;
     * {@code false} otherwise. (e.g. A {@link Slab} is walkable, a {@link Wall} isn't)
     */
    private boolean walkable;

    private boolean isBody;
    protected int[] bodyBox = new int[]{0, 0, 0, 0};

    private Body body;
    protected AISteeringEntity steeringEntity;

    public Cell(App app) {
        this.app = app;
    }

    /**
     * Sets the coordinates of this Cell.
     *
     * @param x The x-coordinate in the array of cells that represent the {@link Map}.
     * @param y The y-coordinate in the array of cells that represent the {@link Map}.
     */
    public void set(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gets the x-coordinate in the array of cells that represent the {@link Map}.
     *
     * @return The x-coordinate in the array of cells that represent the {@link Map}.
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the y-coordinate in the array of cells that represent the {@link Map}.
     *
     * @return The y-coordinate in the array of cells that represent the {@link Map}.
     */
    public int getY() {
        return y;
    }

    /**
     * Selects the texture used by this Cell. Randomly picks a texture from the given asset. In the
     * asset, textures must be of the same width and height and must be in a horizontal sequence.
     *
     * @return A TextureRegion with the randomly selected texture.
     */
    public TextureRegion[] selectRandomTextures() {
        if (randomTextureRegions == null) {
            TextureRegion[] textureRegions = new TextureRegion[assets.length];

            for (int i = 0; i < assets.length; i++) {
                TextureRegion batchOfTextures = app.getTexture(assets[i]);

                int numberOfTextures = batchOfTextures.getRegionWidth() / CELL_SIZE;
                int randomTexture = ThreadLocalRandom.current().nextInt(0, numberOfTextures);

                textureRegions[i] = new TextureRegion(
                        batchOfTextures,
                        randomTexture * CELL_SIZE,
                        0,
                        CELL_SIZE, batchOfTextures.getRegionHeight());
            }

            randomTextureRegions = textureRegions;
        }

        return randomTextureRegions;
    }

    public void draw(TiledMapTileLayer[] layers, Map map) {
        TextureRegion[] textureRegions = selectRandomTextures();

        for (int i = 0; i < textureRegions.length; i++) {
            TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
            cell.setTile(new StaticTiledMapTile(textureRegions[i]));
            layers[i].setCell(x, y, cell);
        }
    }

    public void drawShadows(TiledMapTileLayer[] layers, Map map) {
        addCorrespondingShadow(layers, assets[0], map);
    }

    private void addCorrespondingShadow(TiledMapTileLayer[] layers, String asset, Map map) {
        if (asset.equals("wall_top") || asset.equals("wall_corner_out_bottom_left") ||
                asset.equals("wall_corner_out_bottom_right")) {
            addShadow(layers, x, y - 1, "shadow_top");
        }

        if (asset.equals("wall_left") || asset.equals("wall_corner_out_bottom_right")) {
            if (!map.getCell(x + 1, y + 1).isWalkable()) {
                addShadow(layers, x + 1, y, "shadow_corner_in_top_left");
            } else if (asset.equals("wall_left")
                    && map.getCell(x + 2, y + 1).getAssets()[0].equals("wall_corner_out_bottom_left")) {
                addShadow(layers, x + 1, y, "shadow_middle_corner_right_top");
            } else {
                addShadow(layers, x + 1, y, "shadow_left");
            }
        }

        if (asset.equals("wall_right") || asset.equals("wall_corner_out_bottom_left")) {
            if (!map.getCell(x - 2, y).isWalkable()) {
                if (map.getCell(x - 2, y).getAssets()[0].equals("wall_corner_out_top_right")) {
                    addShadow(layers, x - 1, y, "shadow_middle_corner_left");
                } else {
                    addShadow(layers, x - 1, y, "shadow_middle");
                }
            } else if (map.getCell(x - 2, y + 1).getAssets()[0].equals("wall_corner_out_bottom_right")) {
                addShadow(layers, x - 1, y, "shadow_middle_corner_left_top");
            } else if (!map.getCell(x - 1, y + 1).isWalkable()) {
                addShadow(layers, x - 1, y, "shadow_corner_in_top_right");
            } else {
                addShadow(layers, x - 1, y, "shadow_right");
            }
        }

        if (asset.equals("wall_corner_out_top_left")) {
            if (map.getCell(x - 2, y).getAssets()[0].equals("wall_left")) {
                addShadow(layers, x - 1, y, "shadow_middle_corner_right");
            } else {
                addShadow(layers, x - 1, y, "shadow_corner_right");
            }
        }

        if (asset.equals("wall_corner_out_top_right")) {
            addShadow(layers, x + 1, y, "shadow_corner_left");
        }

        if (asset.equals("wall_corner_out_bottom_left")) {
            addShadow(layers, x - 1, y - 1, "shadow_corner_right_top");
        }

        if (asset.equals("wall_corner_out_bottom_right")) {
            addShadow(layers, x + 1, y - 1, "shadow_corner_left_top");
        }

    }

    private void addShadow(TiledMapTileLayer[] layers, int x, int y, String texture) {
        TextureRegion textureRegion = app.getTexture(texture);
        TiledMapTileLayer.Cell shadow = new TiledMapTileLayer.Cell();
        TiledMapTile tile = new StaticTiledMapTile(textureRegion);
        shadow.setTile(tile);
        layers[0].setCell(x, y, shadow);
    }

    /**
     * Returns if a character can walk through this Cell.
     *
     * @return {@code true} if a character can walk through this Cell;
     * {@code false} otherwise. (e.g. A {@link Slab} is walkable, a {@link Wall} isn't)
     */
    public boolean isWalkable() {
        return walkable;
    }

    /**
     * Sets if a character can walk through this Cell.
     *
     * @param walkable {@code true} if a character can walk through this Cell;
     *                 {@code false} otherwise. (e.g. A {@link Slab} is walkable, a {@link Wall} isn't)
     */
    void setWalkable(boolean walkable) {
        this.walkable = walkable;
    }

    /**
     * Sets the appropriate drawable resource for a Cell based on the position this Cell occupies
     * in the {@link Map} and what other cells surround it. (e.g. a {@link Wall} has many textures
     * that represent the front, the back, the corners, etc.)
     *
     * @param map The map where this Cell is.
     */
    public abstract void selectDrawableResource(Map map);

    public boolean isBody() {
        return isBody;
    }

    void setBody(boolean body) {
        this.isBody = body;
    }

    public void createBody() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;

        int mapX = (x * CELL_SIZE * SCALE) + (((CELL_SIZE / 2) + bodyBox[0]) * SCALE);
        int mapY = (y * CELL_SIZE * SCALE) + (((CELL_SIZE / 2) + bodyBox[1]) * SCALE);

        bodyDef.position.set(
                Positions.convertMapCoordToWorld(mapX),
                Positions.convertMapCoordToWorld(mapY));

        body = app.getWorld().createBody(bodyDef);
        body.setAwake(false);

        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(
                (CELL_SIZE + bodyBox[2]) * 2 / PPM,
                (CELL_SIZE + bodyBox[3]) * 2 / PPM);

        Fixture fixture = body.createFixture(polygonShape, 0.0f);
        fixture.setUserData(this);
        fixture.setFriction(0);

        Filter filter = new Filter();
        filter.categoryBits = WALLS;
        filter.maskBits = MASK_WALLS;

        fixture.setFilterData(filter);

        steeringEntity = new AISteeringEntity(body, 0, 0, 0, 0, 0);
    }

    public String[] getAssets() {
        return assets;
    }

    @Override
    public void collides(Object object, WorldManifold worldManifold) {
    }

    @Override
    public AISteeringEntity getSteeringEntity() {
        return steeringEntity;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "x=" + x +
                ", y=" + y +
                ", walkable=" + walkable +
                '}';
    }

    @SuppressWarnings("squid:S00122")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cell cell = (Cell) o;

        if (x != cell.x) return false;
        if (y != cell.y) return false;
        if (walkable != cell.walkable) return false;
        if (isBody != cell.isBody) return false;
        return Arrays.equals(assets, cell.assets);
    }

    @Override
    public int hashCode() {
        int result = app != null ? app.hashCode() : 0;
        result = 31 * result + x;
        result = 31 * result + y;
        result = 31 * result + Arrays.hashCode(assets);
        result = 31 * result + (walkable ? 1 : 0);
        result = 31 * result + (isBody ? 1 : 0);
        return result;
    }

}
