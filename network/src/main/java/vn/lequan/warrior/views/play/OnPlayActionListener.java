package vn.lequan.warrior.views.play;


import vn.lequan.warrior.entity.Entity;
import vn.lequan.warrior.map.Map;
import vn.lequan.warrior.views.View;

public interface OnPlayActionListener {

    void onDrawMap(Map map);

    void onDrawEntity(Entity entity);

    void onDrawHearts(int health, int maxHealth);

    void onDrawScore(String scoreText);

    void onDrawGameOver(String scoreText, String highScoreText);

    void onOpenMenu(View view);

    void onGameOver(int highScore);

}
