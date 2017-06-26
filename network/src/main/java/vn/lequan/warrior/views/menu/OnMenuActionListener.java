package vn.lequan.warrior.views.menu;


import vn.lequan.warrior.views.View;

public interface OnMenuActionListener {

    void onStartNewGameError(String message);

    void onStartNewGame(View view);

}
