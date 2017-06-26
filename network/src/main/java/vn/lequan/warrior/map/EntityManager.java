/*
 * Copyright (c) 2017 Warrior Dragon Contributors
 * This program is made available under the terms of the MIT License.
 */

package vn.lequan.warrior.map;


import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import vn.lequan.warrior.config.component.DaggerEntityComponent;
import vn.lequan.warrior.entity.Character;
import vn.lequan.warrior.entity.Entity;
import vn.lequan.warrior.entity.Monster;
import vn.lequan.warrior.entity.Status;
import vn.lequan.warrior.entity.items.Coin;
import vn.lequan.warrior.entity.player.Player;
import vn.lequan.warrior.entity.projectiles.Projectile;
import vn.lequan.warrior.util.App;
import vn.lequan.warrior.util.Positions;
import vn.lequan.warrior.util.Updateable;

public class EntityManager implements Updateable {

    private App app;

    private Player player;

    private List<Entity> entities = Collections.synchronizedList(new ArrayList<Entity>());

    private List<Entity> removeEntities = new ArrayList<>();

    /**
     * New entities to be added to the entities list created from the Main Thread, necessary to
     * avoid concurrent modification exception. (e.g. entities created when the player tap).
     */
    private List<Entity> addEntitiesMainThread = new ArrayList<>();

    private List<Entity> addEntities = new ArrayList<>();

    private Queue<Entity> visibleEntities = new PriorityQueue<>(1, new Comparator<Entity>() {
        @Override
        public int compare(Entity c1, Entity c2) {
            if (Projectile.class.isInstance(c1)) {
                return 1;
            } else {
                if (!c1.getStatus().equals(Status.DESTROY) && !c2.getStatus().equals(Status.DESTROY)) {
                    return Float.compare(c2.getPosition().y, c1.getPosition().y);
                } else {
                    if (c1.getStatus().equals(Status.DESTROY)) {
                        return -1;
                    } else {
                        return 1;
                    }
                }
            }
        }
    });

    private int numMonsters = 0;

    public EntityManager(App app, Player player) {
        this.app = app;
        this.player = player;
    }

    @Override
    public void update(long fps, float delta) {
        updateEntityLists();

        int size = entities.size();

        for (int i = 0; i < size; i++) {
            Entity entity = entities.get(i);
            entity.update(fps, delta);

            if (entity.getStatus().equals(Status.REMOVE)) {
                if (entity instanceof Monster) {
                    numMonsters--;
                    spawnCoin(entity);
                }

                entity.destroyBody();
                removeEntities.add(entity);
            } else {
                if (Character.class.isInstance(entity)) {
                    ((Character) entity).attack(this);
                }
            }

            if (entity.isVisibleOnScreen()) {
                visibleEntities.add(entity);
            }
        }
    }

    private void spawnCoin(Entity entity) {
        Coin coin = DaggerEntityComponent.builder().build().provideCoin();
        coin.setPlayer(player);

        addEntity(coin, entity.getPosition());
    }

    private void updateEntityLists() {
        if (!removeEntities.isEmpty()) {
            entities.removeAll(removeEntities);
            removeEntities.clear();
        }

        if (!addEntitiesMainThread.isEmpty()) {
            entities.addAll(addEntitiesMainThread);
            addEntitiesMainThread.clear();
        }

        if (!addEntities.isEmpty()) {
            entities.addAll(addEntities);
            addEntities.clear();
        }
    }

    /**
     * Finds the nearest character to another character.
     *
     * @param character A character from which I have to look for the nearest character.
     * @return The nearest character to the given character.
     */
    public Character findNearestCharacter(Character character) {
        double minDistance = Double.MAX_VALUE;
        Character closestCharacter = null;

        for (int i = 0; i < entities.size(); i++) {
            Entity otherCharacter = entities.get(i);

            if (otherCharacter instanceof Character && character != otherCharacter) {
                double distance = Positions.calculateDistance(
                        character.getPosition(), otherCharacter.getPosition());

                if (distance < minDistance) {
                    minDistance = distance;
                    closestCharacter = (Character) otherCharacter;
                }
            }
        }

        return closestCharacter;
    }

    /**
     * Sets the position of an Entity in the Map and adds it to the list of entities.
     *
     * @param entity An Entity to be positioned in the Map.
     * @param x      X-coordinate.
     * @param y      Y-coordinate.
     */
    public void addEntity(Entity entity, int x, int y) {
        entity.getBody().setTransform(
                Positions.convertMapCoordToWorld(Positions.determineMapPosCoord(x)),
                Positions.convertMapCoordToWorld(Positions.determineMapPosCoord(y))
                        + Positions.convertMapCoordToWorld(10), 0);

        addEntity(entity);
    }

    public void addEntity(Entity entity, Vector2 worldPosition) {
        entity.getBody().setTransform(worldPosition.x, worldPosition.y, 0);

        addEntity(entity);
    }

    private void addEntity(Entity entity) {
        addEntities.add(entity);

        if (Monster.class.isInstance(entity)) {
            numMonsters++;
        }
    }

    void addEntityMainTrhead(Entity entity) {
        addEntitiesMainThread.add(entity);
    }

    public Queue<Entity> getVisibleEntities() {
        return visibleEntities;
    }

    public int getNumMonsters() {
        return numMonsters;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

}
