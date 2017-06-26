/*
 * Copyright (c) 2017 Warrior Dragon Contributors
 * This program is made available under the terms of the MIT License.
 */

package vn.lequan.warrior.features.debug;


import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;

import vn.lequan.warrior.util.App;

import static vn.lequan.warrior.util.Constants.PPM;

/**
 * Implementation for this feature if it's enabled.
 */
public class DebugBox2DEnabledImpl implements DebugBox2D {

    private App app;
    private Matrix4 debugMatrix;
    private Box2DDebugRenderer renderer;

    public DebugBox2DEnabledImpl(App app) {
        this.app = app;
        debugMatrix = new Matrix4(app.getCam().combined);
        debugMatrix.scale(PPM, PPM, 1f);
        renderer = new Box2DDebugRenderer();
    }

    @Override
    public void debug() {
        debugMatrix.set(app.getCam().combined);
        debugMatrix.scale(PPM, PPM, 1f);
        renderer.render(app.getWorld(), debugMatrix);
    }

}
