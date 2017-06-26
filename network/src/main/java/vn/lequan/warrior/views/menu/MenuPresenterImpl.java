/*
 * Copyright (c) 2017 Warrior Dragon Contributors
 * This program is made available under the terms of the MIT License.
 */

package vn.lequan.warrior.views.menu;


import vn.lequan.warrior.views.View;
import vn.lequan.warrior.util.App;

public class MenuPresenterImpl implements MenuPresenter, OnMenuActionListener {

    private App app;

    private MenuView view;
    private MenuInteractor interactor;

    public MenuPresenterImpl(App app, MenuView view, MenuInteractor interactor) {
        this.app = app;

        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void startNewGame() {
        interactor.startNewGame(this);
    }

    @Override
    public void onStartNewGameError(String message) {
        // TODO view.showError (e.g. wrong map format)
    }

    @Override
    public void onStartNewGame(View view) {
        this.view.navigateTo(view);
    }

}
