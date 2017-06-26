/*
 * Copyright (c) 2017 Warrior Dragon Contributors
 * This program is made available under the terms of the MIT License.
 */

package vn.lequan.warrior;


import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import vn.lequan.warrior.util.App;

class MainGestureListener implements GestureDetector.GestureListener {

    private App app;
    private Vector3 tapPos = new Vector3();

    MainGestureListener(App app) {
        this.app = app;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        tapPos.set(x, y, 0);
        app.getViewport().unproject(tapPos);

        return app.getViewManager().onTap(tapPos.x, tapPos.y);
    }

    @Override
    public boolean longPress(float x, float y) {
        app.getViewManager().onLongPress();

        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return app.getViewManager().onFling(velocityX, velocityY);
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {
        // unused gesture
    }

}
