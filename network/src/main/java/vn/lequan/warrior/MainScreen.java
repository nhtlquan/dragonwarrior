/*
 * Copyright (c) 2017 Warrior Dragon Contributors
 * This program is made available under the terms of the MIT License.
 */
package vn.lequan.warrior;


import android.app.Activity;
import android.app.Application;
import android.widget.Toast;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.backends.android.AndroidApplication;

import vn.lequan.warrior.util.App;

public class MainScreen implements Screen {

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        App.get().render();
    }

    @Override
    public void resize(int width, int height) {
        App.get().resize(width, height);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
    }

}
