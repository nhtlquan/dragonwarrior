package vn.lequan.warrior.views.play;


import vn.lequan.warrior.util.Updateable;
import vn.lequan.warrior.views.Presenter;

public interface PlayPresenter extends Presenter<PlayView>, Updateable {

    void drawMap();

    void drawEntities();

    void drawHearts();

    void drawScore();

    void drawGameOver();

    void openMenu();

    void mainAttack();

    void move(float velocityX, float velocityY);

    boolean isPlayerAlive();

    void gameOver();

}
