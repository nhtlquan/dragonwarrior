/*
 * Copyright (c) 2017 Warrior Dragon Contributors
 * This program is made available under the terms of the MIT License.
 */

package vn.lequan.warrior.config.module;


import com.badlogic.gdx.Gdx;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import vn.lequan.warrior.entity.CharacterType;
import vn.lequan.warrior.entity.player.Player;
import vn.lequan.warrior.map.EntityManager;
import vn.lequan.warrior.map.Map;
import vn.lequan.warrior.map.MapParser;
import vn.lequan.warrior.ui.buttons.MenuButton;
import vn.lequan.warrior.util.App;
import vn.lequan.warrior.views.menu.MenuInteractor;
import vn.lequan.warrior.views.menu.MenuInteractorImpl;
import vn.lequan.warrior.views.menu.MenuPresenter;
import vn.lequan.warrior.views.menu.MenuPresenterImpl;
import vn.lequan.warrior.views.menu.MenuView;
import vn.lequan.warrior.views.menu.MenuViewImpl;
import vn.lequan.warrior.views.play.PlayInteractor;
import vn.lequan.warrior.views.play.PlayInteractorImpl;
import vn.lequan.warrior.views.play.PlayPresenter;
import vn.lequan.warrior.views.play.PlayPresenterImpl;
import vn.lequan.warrior.views.play.PlayView;
import vn.lequan.warrior.views.play.PlayViewImpl;

@Module
public class ViewModule {

    @Provides
    PlayView providePlayView(App app, @Named("playViewButtons") MenuButton[] buttons) {
        app.initWorld();

        Map map = new Map(app);
        Player player = (Player) CharacterType.PLAYER.getInstance();
        EntityManager entityManager = new EntityManager(app, player);

        try {
            MapParser.parse(app, map, entityManager, player, "rooms/map");
        } catch (Exception e) {
            Gdx.app.error(this.getClass().getSimpleName(), e.getMessage(), e);
        }

        PlayView view = new PlayViewImpl(app, buttons);
        PlayInteractor interactor = new PlayInteractorImpl(app, map, entityManager, player);
        PlayPresenter presenter = new PlayPresenterImpl(app, view, interactor);

        view.setPresenter(presenter);

        return view;
    }

    @Provides
    MenuView provideMenuView(App app, @Named("menuViewButtons") MenuButton[] buttons) {
        MenuView view = new MenuViewImpl(app, buttons);
        MenuInteractor interactor = new MenuInteractorImpl(app);
        MenuPresenter presenter = new MenuPresenterImpl(app, view, interactor);

        view.setPresenter(presenter);

        return view;
    }

}
