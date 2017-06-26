package vn.lequan.warrior.views.play;


import vn.lequan.warrior.entity.Entity;
import vn.lequan.warrior.map.Map;
import vn.lequan.warrior.views.View;
import vn.lequan.warrior.util.App;

public class PlayPresenterImpl implements PlayPresenter, vn.lequan.warrior.views.play.OnPlayActionListener {

    private App app;

    private PlayView view;
    private vn.lequan.warrior.views.play.PlayInteractor interactor;

    public PlayPresenterImpl(App app, PlayView view, vn.lequan.warrior.views.play.PlayInteractor interactor) {
        this.app = app;

        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void update(long fps, float delta) {
        interactor.update(fps, delta);
    }

    @Override
    public void drawMap() {
        interactor.drawMap(this);
    }

    @Override
    public void onDrawMap(Map map) {
        view.onDrawMap(map);
    }

    @Override
    public void drawEntities() {
        interactor.drawEntities(this);
    }

    @Override
    public void onDrawEntity(Entity entity) {
        view.onDrawEntity(entity);
    }

    @Override
    public void drawHearts() {
        interactor.drawHearts(this);
    }

    @Override
    public void onDrawHearts(int health, int maxHealth) {
        int y = app.getScreenHeight() - 80;

        for (int i = 0; i < (maxHealth / 2); i++) {
            int x = 8 + (i * 82);

            if (health >= ((i + 1) * 2)) {
                view.onDrawHeart(x, y, vn.lequan.warrior.views.play.HeartType.FULL);
            } else if (health == (((i + 1) * 2) - 1)) {
                view.onDrawHeart(x, y, vn.lequan.warrior.views.play.HeartType.HALF);
            } else {
                view.onDrawHeart(x, y, vn.lequan.warrior.views.play.HeartType.EMPTY);
            }
        }
    }

    @Override
    public void drawScore() {
        interactor.drawScore(this);
    }

    @Override
    public void drawGameOver() {
        interactor.drawGameOver(this);
    }

    @Override
    public void onDrawScore(String scoreText) {
        view.onDrawScore(scoreText);
    }

    @Override
    public void onDrawGameOver(String scoreText, String highScoreText) {
        view.onDrawGameOver(scoreText, highScoreText);
    }

    @Override
    public void openMenu() {
        interactor.openMenu(this);
    }

    @Override
    public void onOpenMenu(View view) {
        this.view.navigateTo(view);
    }

    @Override
    public void mainAttack() {
        interactor.mainAttack();
    }

    @Override
    public void move(float velocityX, float velocityY) {
        interactor.move(velocityX, velocityY);
    }

    @Override
    public boolean isPlayerAlive() {
        return interactor.isPlayerAlive();
    }

    @Override
    public void gameOver() {
        interactor.gameOver(this);
    }

    @Override
    public void onGameOver(int highScore) {
        view.onGameOver(highScore);
    }

}
