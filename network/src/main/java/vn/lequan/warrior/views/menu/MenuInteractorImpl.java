package vn.lequan.warrior.views.menu;


import javax.inject.Inject;

import vn.lequan.warrior.config.component.DaggerNavigationComponent;
import vn.lequan.warrior.views.play.PlayView;
import vn.lequan.warrior.util.App;

public class MenuInteractorImpl implements MenuInteractor {

    @Inject
    PlayView playView;

    private App app;

    public MenuInteractorImpl(App app) {
        this.app = app;
    }

    @Override
    public void startNewGame(OnMenuActionListener listener) {
        DaggerNavigationComponent.builder().build().inject(this);

        listener.onStartNewGame(playView);

        // TODO if map format error listener.onStartNewGameError(e.getMessage());
    }

}
