package vn.lequan.warrior.views.menu;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import vn.lequan.warrior.ui.buttons.MenuButton;
import vn.lequan.warrior.ui.buttons.NewGameButton;
import vn.lequan.warrior.ui.buttons.OnButtonClickedListener;
import vn.lequan.warrior.util.App;
import vn.lequan.warrior.util.Constants;
import vn.lequan.warrior.util.Counter;
import vn.lequan.warrior.util.Cycle;
import vn.lequan.warrior.views.View;

import static vn.lequan.warrior.util.Constants.FILL_80_WHITE;
import static vn.lequan.warrior.util.Constants.SCALE;

public class MenuViewImpl implements MenuView, OnButtonClickedListener {

    private App app;

    private final TextureRegion clouds;
    private final TextureRegion logo;
    private final TextureRegion Warrior;

    private final Cycle cloudsCycle;
    private final Counter cloudsCounter;

    private final MenuButton[] buttons;

    private MenuPresenter presenter;

    public MenuViewImpl(App app, MenuButton[] buttons) {
        this.app = app;

        this.clouds = app.getTexture("menu/clouds");
        this.logo = app.getTexture("menu/logo");
        this.Warrior = app.getTexture("menu/Warrior");

        this.cloudsCycle = new Cycle(1);

        int cloudsWidth = clouds.getRegionWidth() * SCALE;
        this.cloudsCounter = new Counter(0, -cloudsWidth, 0, 1, false, false);

        this.buttons = buttons;
    }

    @Override
    public void update(long fps, float delta) {
        if (cloudsCycle.isCompleted()) {
            cloudsCounter.update();
        }

        for (MenuButton button : buttons) {
            button.update();
        }
    }

    @Override
    public void render() {
        app.beginSpriteBatch();

        drawBackground();
        drawClouds();
        drawLogo();
        drawAds();
//        drawWarrior();
        drawButtons();
//        drawVersion();

//        app.getDebugFPS().debug(Gdx.graphics.getFramesPerSecond(), false);
        app.endSpriteBatch();
    }

    private void drawAds() {

    }

    private void drawBackground() {
        Gdx.gl.glClearColor(95 / 255.0f, 185 / 255.0f, 228 / 255.0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    private void drawClouds() {
        int x = cloudsCounter.getValue();
        int y = 1280;

        drawCloudsLine(x, y, 3);
        drawCloudsLine(x - clouds.getRegionWidth() * SCALE / 2,
                y + clouds.getRegionHeight() * SCALE, 4);
    }

    private void drawCloudsLine(int x, int y, int num) {
        for (int i = 0; i < num; i++) {
            app.draw(clouds, x + (i * clouds.getRegionWidth() * SCALE), y, false);
        }
    }

    private void drawLogo() {
        int x = app.getScreenCenter()[0] - ((logo.getRegionWidth() * SCALE) / 2);
        int y =920;

        app.draw(logo, x, y, false);
    }

    private void drawWarrior() {
        int x = (app.getScreenWidth() - (Warrior.getRegionWidth() * SCALE)) / 2;
        int y = 0;

        app.draw(Warrior, x, y, false);
    }

    private void drawButtons() {
        for (MenuButton button : buttons) {
            button.draw(false);
        }
    }

    private void drawVersion() {
        app.drawText(FILL_80_WHITE, Constants.VERSION, 20, 60, false);
    }

    @Override
    public void setPresenter(MenuPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void navigateTo(View view) {
        // TODO showTransitionAnimation

        app.getViewManager().set(view);
    }

    @Override
    public boolean onTap(float eventX, float eventY) {
        for (MenuButton button : buttons) {
            if (button.inBounds(eventX, eventY, false)) {
                button.click(this);
            }
        }

        return true;
    }

    @Override
    public void onButtonClicked(MenuButton button) {
        if (NewGameButton.class.isInstance(button)) {
            presenter.startNewGame();
        }
    }

    @Override
    public boolean onFling(float velocityX, float velocityY) {
        return false;
    }

    @Override
    public boolean onLongPress() {
        return false;
    }

}
