/*
 * Copyright (c) 2017 Warrior Dragon Contributors
 * This program is made available under the terms of the MIT License.
 */
package vn.lequan.warrior;


import android.app.FragmentManager;
import android.content.Context;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.input.GestureDetector;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.smile.studio.libsmilestudio.R;

import javax.inject.Inject;

import vn.lequan.warrior.config.component.DaggerAppComponent;
import vn.lequan.warrior.config.component.DaggerNavigationComponent;
import vn.lequan.warrior.features.debug.DebugBox2D;
import vn.lequan.warrior.features.debug.DebugFPS;
import vn.lequan.warrior.util.App;
import vn.lequan.warrior.views.menu.MenuView;

public class Main extends Game {

    @Inject
    DebugFPS debugFPS;

    private AdRequest adRequest;
    private InterstitialAd interstitialAd;

    @Inject
    DebugBox2D debugBox2D;

    @Inject
    MenuView menuView;
    public static Context context;
    public static FragmentManager fragmentManager;

    public Main(Context context, FragmentManager fragmentManager) {
        this.context = context;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public void create() {
        App app = DaggerAppComponent.builder().build().provideApp();

        Gdx.input.setInputProcessor(new GestureDetector(new MainGestureListener(app)));

        DaggerNavigationComponent.builder().build().inject(this);

        app.setDebugFPS(debugFPS);
        app.setDebugBox2D(debugBox2D);
        app.getViewManager().push(menuView);
//        loadads();
        setScreen(new MainScreen());
    }

    private void loadads() {
        interstitialAd = new InterstitialAd(context);
        interstitialAd.setAdUnitId("ca-app-pub-6201103843470497/1275978968");
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                interstitialAd.show();
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();

            }

            @Override
            public void onAdLeftApplication() {
                super.onAdLeftApplication();
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
            }

            @Override
            public void onAdClosed() {
                super.onAdClosed();
            }
        });

        adRequest = new AdRequest.Builder()
                .build();
        interstitialAd.loadAd(adRequest);
    }

    @Override
    public void dispose() {
        App.get().dispose();
    }

}
