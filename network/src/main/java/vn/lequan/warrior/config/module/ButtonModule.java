/*
 * Copyright (c) 2017 Warrior Dragon Contributors
 * This program is made available under the terms of the MIT License.
 */

package vn.lequan.warrior.config.module;


import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import vn.lequan.warrior.ui.buttons.ContinueButton;
import vn.lequan.warrior.ui.buttons.CreditsButton;
import vn.lequan.warrior.ui.buttons.MenuButton;
import vn.lequan.warrior.ui.buttons.NewGameButton;
import vn.lequan.warrior.ui.buttons.OkButton;
import vn.lequan.warrior.ui.buttons.ScoreButton;
import vn.lequan.warrior.ui.position.Position;
import vn.lequan.warrior.util.App;
import vn.lequan.warrior.util.Positions;
import vn.lequan.warrior.util.UI;

import static vn.lequan.warrior.util.Constants.FILL_80_WHITE;

@Module
public class ButtonModule {

    private static final String TEXTURE = "menu/button";

    @Provides
    @Named("newGame")
    MenuButton provideNewGameButton(App app) {
        String text = app.getMessage("button_newgame");
        InitParameters params = determineInitParameters(app, text, TEXTURE);

        return new NewGameButton(app, params.texture, text, params.alignCenter);
    }

    @Provides
    @Named("continueButton")
    MenuButton provideContinueButton(App app) {
        String text = app.getMessage("button_continue");
        InitParameters params = determineInitParameters(app, text, TEXTURE);

        return new ContinueButton(app, params.texture, text, params.alignCenter);
    }

    @Provides
    @Named("scoreButton")
    MenuButton provideScoreButton(App app) {
        String text = app.getMessage("button_score");
        InitParameters params = determineInitParameters(app, text, TEXTURE);

        return new ScoreButton(app, params.texture, text, params.alignCenter);
    }

    @Provides
    @Named("creditsButton")
    MenuButton provideCreditsButton(App app) {
        String text = app.getMessage("button_credits");
        InitParameters params = determineInitParameters(app, text, TEXTURE);

        return new CreditsButton(app, params.texture, text, params.alignCenter);
    }

    @Provides
    @Named("okButton")
    MenuButton provideOkButton(App app) {
        String text = app.getMessage("button_ok");
        InitParameters params = determineInitParameters(app, text, "button_transparent");

        MenuButton okButton = new OkButton(app, params.texture, text, params.alignCenter);
        okButton.set(app.getScreenCenter()[0] - (okButton.getWidth() / 2), 120);

        return okButton;
    }

    @Provides
    @Named("menuViewButtons")
    MenuButton[] provideMenuViewButtons(
            App app,
            @Named("newGame") MenuButton newGameButton,
            @Named("scoreButton") MenuButton scoreButton,
            @Named("creditsButton") MenuButton creditsButton) {

        MenuButton[] buttons = new MenuButton[3];
        buttons[0] = scoreButton;
        buttons[1] = creditsButton;
        buttons[2] = newGameButton;

        UI.placeButtonsOnTwoColumns(app, buttons, 955);

        return buttons;
    }

    @Provides
    @Named("playViewButtons")
    MenuButton[] providePlayViewButtons(
            @Named("okButton") MenuButton okButton) {

        MenuButton[] buttons = new MenuButton[1];
        buttons[0] = okButton;

        return buttons;
    }

    private InitParameters determineInitParameters(App app, String text, String textureName) {
        TextureRegion texture = app.getTexture(textureName);

        GlyphLayout layout = new GlyphLayout();
        layout.setText(FILL_80_WHITE, text);

        Position alignCenter = new Position();
        Positions.determineAlignCenterPos(texture, layout, alignCenter);

        return new InitParameters(texture, alignCenter);
    }

    private class InitParameters {
        private final TextureRegion texture;
        private final Position alignCenter;

        InitParameters(TextureRegion texture, Position alignCenter) {
            this.texture = texture;
            this.alignCenter = alignCenter;
        }
    }

}
