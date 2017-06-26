/*
 * Copyright (c) 2017 Warrior Dragon Contributors
 * This program is made available under the terms of the MIT License.
 */

package vn.lequan.warrior.views.play;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import vn.lequan.warrior.entity.Entity;
import vn.lequan.warrior.map.Map;
import vn.lequan.warrior.ui.buttons.MenuButton;
import vn.lequan.warrior.ui.buttons.OkButton;
import vn.lequan.warrior.ui.buttons.OnButtonClickedListener;
import vn.lequan.warrior.util.App;
import vn.lequan.warrior.views.View;

import static vn.lequan.warrior.util.Constants.FILL_120_WHITE_W_BORDER;
import static vn.lequan.warrior.util.Constants.FILL_80_BROWN;
import static vn.lequan.warrior.util.Constants.FILL_80_WHITE_W_BORDER;
import static vn.lequan.warrior.util.Constants.SCALE;

public class PlayViewImpl implements PlayView, OnButtonClickedListener {

    private App app;

    private vn.lequan.warrior.views.play.PlayPresenter presenter;

    private TextureRegion heartFull;
    private TextureRegion heartHalf;
    private TextureRegion heartEmpty;
    private TextureRegion scoreboard;
    private TextureRegion coinSmall;

    private MenuButton[] buttons;

    private vn.lequan.warrior.views.play.PlayMode mode = vn.lequan.warrior.views.play.PlayMode.PLAYING;

    public PlayViewImpl(App app, MenuButton[] buttons) {
        this.app = app;

        this.heartFull = app.getTexture("heart_full");
        this.heartHalf = app.getTexture("heart_half");
        this.heartEmpty = app.getTexture("heart_empty");

        this.scoreboard = app.getTexture("scoreboard");
        this.coinSmall = app.getTexture("coin_small");

        this.buttons = buttons;
    }

    @Override
    public void update(long fps, float delta) {
        presenter.update(fps, delta);

        if (mode == vn.lequan.warrior.views.play.PlayMode.GAME_OVER) {
            for (MenuButton button : buttons) {
                button.update();
            }
        } else {
            if (!presenter.isPlayerAlive()) {
                presenter.gameOver();
            }
        }
    }

    @Override
    public void onGameOver(int highScore) {
        mode = vn.lequan.warrior.views.play.PlayMode.GAME_OVER;
    }

    @Override
    public void render() {
        presenter.drawMap();

        app.renderRayHandler();

        app.beginSpriteBatch();

        presenter.drawEntities();
        presenter.drawHearts();
        presenter.drawScore();

        if (mode == vn.lequan.warrior.views.play.PlayMode.GAME_OVER) {
            presenter.drawGameOver();
        }

//        app.getDebugFPS().debug(Gdx.graphics.getFramesPerSecond(), true);
        app.endSpriteBatch();

        app.getDebugBox2D().debug();
    }

    @Override
    public void onDrawMap(Map map) {
        map.drawTiledMap();
    }

    @Override
    public void onDrawEntity(Entity entity) {
        entity.draw();
    }

    @Override
    public void onDrawHeart(int x, int y, HeartType type) {
        switch (type) {
            case FULL:
                app.draw(heartFull, x, y, true);
                break;
            case HALF:
                app.draw(heartHalf, x, y, true);
                break;
            case EMPTY:
                app.draw(heartEmpty, x, y, true);
                break;
            default:
        }
    }

    @Override
    public void onDrawScore(String scoreText) {
        app.draw(coinSmall, 8, app.getScreenHeight() - 160, true);
        app.drawText(FILL_80_WHITE_W_BORDER, scoreText, 90, app.getScreenHeight() - 104, true);
    }

    @Override
    public void onDrawGameOver(String scoreText, String highScoreText) {
        int scoreboardX = app.getScreenCenter()[0] - (scoreboard.getRegionWidth() * SCALE / 2);
        app.draw(scoreboard, scoreboardX, 0, true);

        int scoreX = scoreboardX + 200;
        app.drawText(FILL_80_BROWN, app.getMessage("label_score"), scoreX, 400, true);
        app.drawText(FILL_120_WHITE_W_BORDER, scoreText, scoreX, 320, true);

        scoreX = scoreboardX + 550;
        app.drawText(FILL_80_BROWN, app.getMessage("label_best"), scoreX, 400, true);
        app.drawText(FILL_120_WHITE_W_BORDER, highScoreText, scoreX, 320, true);

        for (MenuButton button : buttons) {
            button.draw(true);
        }
    }

    @Override
    public void setPresenter(vn.lequan.warrior.views.play.PlayPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void navigateTo(View view) {
        // TODO showTransitionAnimation

        app.getViewManager().set(view);
    }

    @Override
    public boolean onTap(float eventX, float eventY) {
        if (mode == vn.lequan.warrior.views.play.PlayMode.PLAYING) {
            presenter.mainAttack();
        }

        if (mode == vn.lequan.warrior.views.play.PlayMode.GAME_OVER) {
            for (MenuButton button : buttons) {
                if (button.inBounds(eventX, eventY, true)) {
                    button.click(this);
                }
            }
        }

        return true;
    }

    @Override
    public void onButtonClicked(MenuButton button) {
        if (OkButton.class.isInstance(button)) {
            app.resetCamPosition();
            presenter.openMenu();
        }
    }

    @Override
    public boolean onFling(float velocityX, float velocityY) {
        if (mode == vn.lequan.warrior.views.play.PlayMode.PLAYING) {
            presenter.move(velocityX, velocityY);
        }

        return true;
    }

    @Override
    public boolean onLongPress() {
        return true;
    }

}
