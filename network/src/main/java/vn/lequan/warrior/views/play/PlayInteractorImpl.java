package vn.lequan.warrior.views.play;


import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v4.util.ArraySet;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

import javax.inject.Inject;

import vn.lequan.warrior.Main;
import vn.lequan.warrior.config.component.DaggerEntityComponent;
import vn.lequan.warrior.config.component.DaggerNavigationComponent;
import vn.lequan.warrior.entity.Action;
import vn.lequan.warrior.entity.Character;
import vn.lequan.warrior.entity.Entity;
import vn.lequan.warrior.entity.monsters.Jiangshi;
import vn.lequan.warrior.entity.player.Player;
import vn.lequan.warrior.entity.projectiles.Projectile;
import vn.lequan.warrior.map.EntityManager;
import vn.lequan.warrior.map.Map;
import vn.lequan.warrior.map.cells.Cell;
import vn.lequan.warrior.util.App;
import vn.lequan.warrior.views.DialogError;

public class PlayInteractorImpl implements PlayInteractor {

    @Inject
    vn.lequan.warrior.views.menu.MenuView menuView;

    private App app;

    private Map map;
    private EntityManager entityManager;
    private Player player;
    private int highScore;
    private String highScoreText;
    private int numMonsters = 4;
    private static final String MyPREFERENCES = "DANHGIA";
    int PRIVATE_MODE = 0;
    public static SharedPreferences sharedpreferences;

    public PlayInteractorImpl(App app, Map map, EntityManager entityManager, Player player) {
        this.app = app;
        this.map = map;
        this.entityManager = entityManager;
        this.player = player;
    }

    public PlayInteractorImpl() {
    }

    @Override
    public void update(long fps, float delta) {
        entityManager.update(fps, delta);
        if (player.isAlive()) {
            app.updateCamPosition(player.getPosition());
        }

        if (entityManager.getNumMonsters() == 0) {
            numMonsters = numMonsters + 1;
            for (int i = 0; i < numMonsters; i++) {
                Cell cell = map.findRandomWalkableCell();

                if (cell != null) {
                    Jiangshi jiangshi = DaggerEntityComponent.builder().build().provideJiangshi();
                    jiangshi.getSteeringEntity().setSteeringTarget(player.getSteeringEntity());

                    entityManager.addEntity(jiangshi, cell.getX(), cell.getY());
                }
            }
        }
    }

    @Override
    public void drawMap(OnPlayActionListener listener) {
        listener.onDrawMap(map);
    }

    @Override
    public void drawEntities(OnPlayActionListener listener) {
        Queue<Entity> visibleEntities = entityManager.getVisibleEntities();

        while (!visibleEntities.isEmpty()) {
            Entity entity = visibleEntities.remove();
            listener.onDrawEntity(entity);
        }
    }

    @Override
    public void drawHearts(OnPlayActionListener listener) {
        int health = player.getHealthPoints();
        int maxHealth = player.getMaxHealthPoints();

        listener.onDrawHearts(health, maxHealth);
    }

    @Override
    public void drawScore(OnPlayActionListener listener) {
        listener.onDrawScore(player.getScoreText());
    }

    @Override
    public void drawGameOver(OnPlayActionListener listener) {
        listener.onDrawGameOver(player.getScoreText(), highScoreText);
    }

    @Override
    public void openMenu(OnPlayActionListener listener) {
        DaggerNavigationComponent.builder().build().inject(this);

        listener.onOpenMenu(menuView);
    }

    @Override
    public void mainAttack() {
        if (player.getHealthPoints() > 0) {
            Character closestCharacter = entityManager.findNearestCharacter(player);

            if (closestCharacter != null) {
                Projectile projectile = DaggerEntityComponent.builder().build().providePowerBlast();
                projectile.setAction(Action.MOVE);
                projectile.setFriendly(true);

                entityManager.addEntity(projectile, player.getPosition());
                projectile.setupMovement(player.getPosition(), closestCharacter.getPosition());
            }
        }
    }

    @Override
    public void move(float velocityX, float velocityY) {
        player.move(velocityX, -velocityY);
        player.setAction(Action.MOVE);
    }

    @Override
    public boolean isPlayerAlive() {
        return player.getHealthPoints() > 0;
    }

    @Override
    public void gameOver(OnPlayActionListener listener) {
        highScore = app.readHighScore();
        if (highScore > player.getScore()) {
            highScore = player.getScore();
//            app.saveHighScore(highScore);
            DialogError cdd = new DialogError(highScore, Main.context);
            cdd.show();
        }

        sharedpreferences = Main.context.getSharedPreferences(MyPREFERENCES, PRIVATE_MODE);
        Set<String> set = sharedpreferences.getStringSet("key", new HashSet<String>());
        SharedPreferences.Editor edit = sharedpreferences.edit();
        set.add(String.valueOf(player.getScore()));
        edit.putStringSet("key", set);
        edit.apply();
        Set<String> set2 = sharedpreferences.getStringSet("key", new HashSet<String>());
        for (String set1 : set2) {
            Log.e("gameOver: ", set1);
        }
        highScoreText = String.valueOf(highScore);
        listener.onGameOver(highScore);
    }

}
