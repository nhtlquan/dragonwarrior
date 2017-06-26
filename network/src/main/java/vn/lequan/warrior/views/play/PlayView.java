package vn.lequan.warrior.views.play;


import vn.lequan.warrior.entity.Entity;
import vn.lequan.warrior.map.Map;
import vn.lequan.warrior.views.View;

/**
 * The Play View of the game. Is the view where the Player move around the {@link Map}.
 */
public interface PlayView extends View<PlayView, PlayPresenter> {

    void onDrawMap(Map map);

    void onDrawEntity(Entity entity);

    void onDrawHeart(int x, int y, vn.lequan.warrior.views.play.HeartType type);

    void onDrawScore(String scoreText);

    void onDrawGameOver(String scoreText, String highScoreText);

    void onGameOver(int highScore);

}
