package vn.lequan.warrior.views.play;


import vn.lequan.warrior.util.Updateable;

public interface PlayInteractor extends Updateable {

    void drawMap(OnPlayActionListener listener);

    void drawEntities(OnPlayActionListener listener);

    void drawHearts(OnPlayActionListener listener);

    void drawScore(OnPlayActionListener listener);

    void drawGameOver(OnPlayActionListener listener);

    void openMenu(OnPlayActionListener listener);

    void mainAttack();

    void move(float velocityX, float velocityY);

    boolean isPlayerAlive();

    void gameOver(OnPlayActionListener listener);

}
