/*
 * Copyright (c) 2017 Warrior Dragon Contributors
 * This program is made available under the terms of the MIT License.
 */

package vn.lequan.warrior.util;


import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.I18NBundle;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import box2dLight.PointLight;
import box2dLight.RayHandler;
import vn.lequan.warrior.MainContactListener;
import vn.lequan.warrior.entity.Template;
import vn.lequan.warrior.features.debug.DebugBox2D;
import vn.lequan.warrior.features.debug.DebugFPS;
import vn.lequan.warrior.views.ViewManager;

import static vn.lequan.warrior.util.Constants.DESKTOP_HEIGHT;
import static vn.lequan.warrior.util.Constants.DESKTOP_WIDTH;
import static vn.lequan.warrior.util.Constants.SCALE;

public class App  {

    private static App instance = null;

    private final int screenWidth;
    private final int screenHeight;
    private final int[] screenCenter;

    private int resizedScreenWidth;
    private int resizedScreenHeight;

    private SpriteBatch spriteBatch;
    private Viewport viewport;
    private OrthographicCamera cam;

    private World world;
    private RayHandler rayHandler;
    private boolean rayHandlerDirty;

    private I18NBundle strings;

    private ViewManager viewManager = new ViewManager();

    private HashMap<String, TextureRegion> regionsCache = new HashMap<>();
    private HashMap<String, IntMap<TextureRegion>> animationTextureCache = new HashMap<>();
    private IntMap<Template> templatesCache = new IntMap<>();

    private DebugFPS debugFPS;
    private DebugBox2D debugBox2D;

    private TextureAtlas atlas;

    private App() {
        if (Gdx.app.getType() == Application.ApplicationType.Android) {
            screenWidth = Gdx.graphics.getWidth();
            screenHeight = Gdx.graphics.getHeight();
        } else {
            screenWidth = DESKTOP_WIDTH;
            screenHeight = DESKTOP_HEIGHT;
        }

        screenCenter = new int[]{screenWidth / 2, screenHeight / 2};
        spriteBatch = new SpriteBatch(1000, colorOverlyShader());
        changeColor(Color.CLEAR);

        cam = new OrthographicCamera();
        viewport = new FitViewport(screenWidth, screenHeight, cam);
        cam.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);
        cam.update();

        FileHandle baseFileHandle = Gdx.files.internal("i18n/strings");
        Locale locale = new Locale("en", "US", "VAR1");
        strings = I18NBundle.createBundle(baseFileHandle, locale);

        atlas = new TextureAtlas(Gdx.files.internal("atlas/atlas.atlas"));
    }

    public static App get() {
        if (instance == null) {
            instance = new App();
        }

        return instance;
    }

    public void resetCamPosition() {
        cam.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);
        cam.update();
    }

    public void initWorld() {
        if (world != null) {
            world.dispose();
        }

        world = new World(new Vector2(0, 0), true);
        world.setContactListener(new MainContactListener());
    }

    public void initRayHandler() {
        if (rayHandler == null) {
            RayHandler.setGammaCorrection(true);
            RayHandler.useDiffuseLight(true);
        } else {
            rayHandler.dispose();
        }

        rayHandler = new RayHandler(world);
        rayHandlerDirty = true;
        updateRayHandlerViewport();

        rayHandler.setCombinedMatrix(cam);
        rayHandler.setAmbientLight(90 / 255.0f, 96 / 255.0f, 159 / 255.0f, 1);
        rayHandler.setCulling(false);
    }

    public void newPointLight(int x, int y) {
        PointLight point = new PointLight(rayHandler, 100, new Color(0xff483fff), 500, x, y);
        point.setXray(true);
        point.setStaticLight(true);
    }

    private void updateRayHandlerViewport() {
        int gutterW = viewport.getLeftGutterWidth();
        int gutterH = viewport.getTopGutterHeight();
        int rhWidth = resizedScreenWidth - (2 * gutterW);
        int rhHeight = resizedScreenHeight - (2 * gutterH);
        rayHandler.useCustomViewport(gutterW, gutterH, rhWidth, rhHeight);
    }

    public void resize(int width, int height) {
        resizedScreenWidth = width;
        resizedScreenHeight = height;
        viewport.update(width, height);

        if (rayHandler != null) {
            updateRayHandlerViewport();
        }
    }

    public ViewManager getViewManager() {
        return viewManager;
    }

    public Viewport getViewport() {
        return viewport;
    }

    public void dispose() {
        spriteBatch.dispose();
    }

    /**
     * Creates a texture and puts it in the cache or gets it from the cache if it's already there.
     *
     * @param asset An asset from the assets directory.
     * @return The texture representing the asset.
     */
    public TextureRegion getTexture(String asset) {
        if (regionsCache.get(asset) == null) {
            TextureRegion region = atlas.findRegion(asset);
            regionsCache.put(asset, region);
        }

        return regionsCache.get(asset);
    }

    /**
     * Given an animation asset which contains n frames. Create a texture for one particular
     * frame and puts it in the cache. Or retrieves it from the cache if it's already there.
     *
     * @param asset  Texture with n frames.
     * @param pos   Horizontal position in the texture from which to create the texture.
     * @param width  Width of the frames.
     * @param height Height of the frames.
     * @return The created texture for the given frame of the animation texture.
     */
    public TextureRegion getAnimationTexture(String asset, int pos, int width, int height) {

        TextureRegion animation = getTexture(asset);

        IntMap<TextureRegion> animationTextures = animationTextureCache.get(asset);

        if (animationTextures == null) {
            animationTextures = new IntMap<>();
            animationTextureCache.put(asset, animationTextures);
        }

        TextureRegion animationTexture = animationTextures.get(pos);

        if (animationTexture == null) {
            animationTexture = new TextureRegion(animation, pos * width, 0, width, height);
            animationTextures.put(pos, animationTexture);
        }

        return animationTexture;
    }

    public Template getTemplate(int classKey) {
        if (templatesCache.get(classKey) != null) {
            return templatesCache.get(classKey);
        } else {
            return null;
        }
    }

    public void putTemplate(int classKey, Template template) {
        templatesCache.put(classKey, template);
    }

    public void render() {
        if (world != null) {
            world.step(1 / 60f, 6, 2);
        }

        viewManager.update(Gdx.graphics.getFramesPerSecond(), Gdx.graphics.getDeltaTime());
        cam.update();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        viewManager.render();
    }

    public void renderRayHandler() {
        if (rayHandler != null) {
            if (rayHandlerDirty) {
                rayHandler.update();
                rayHandlerDirty = false;
            }

            rayHandler.setCombinedMatrix(cam);
            rayHandler.render();
        }
    }

    public void beginSpriteBatch() {
        spriteBatch.setProjectionMatrix(cam.combined);
        spriteBatch.begin();
    }

    public void endSpriteBatch() {
        spriteBatch.end();
    }

    public void draw(Texture texture, int x, int y) {
        draw(spriteBatch, texture, x, y);
    }

    public void draw(SpriteBatch batch, Texture texture, int x, int y) {
        batch.draw(texture, x, y, texture.getWidth() * SCALE, texture.getHeight() * SCALE);
    }

    public void changeColor(Color color) {
        spriteBatch.setColor(color);
    }

    public void draw(TextureRegion region, int x, int y, boolean relative) {
        if (relative) {
            draw(spriteBatch, region, camRelativeX(x), camRelativeY(y));
        } else {
            draw(spriteBatch, region, x, y);
        }
    }

    public int camRelativeX(int x) {
        return (int) (x + cam.position.x - viewport.getWorldWidth() / 2);
    }

    public int camRelativeY(int y) {
        return (int) (y + cam.position.y - viewport.getWorldHeight() / 2);
    }

    public void draw(SpriteBatch batch, TextureRegion region, int x, int y) {
        batch.draw(region, x, y, region.getRegionWidth() * SCALE, region.getRegionHeight() * SCALE);
    }

    public void drawText(BitmapFont font, String text, int x, int y, boolean relative) {
        if (relative) {
            font.draw(spriteBatch, text, camRelativeX(x), camRelativeY(y));
        } else {
            font.draw(spriteBatch, text, x, y);
        }
    }

    public String getMessage(String key, Object... args) {
        return strings.format(key, args);
    }

    public DebugFPS getDebugFPS() {
        return debugFPS;
    }

    public void setDebugFPS(DebugFPS debugFPS) {
        this.debugFPS = debugFPS;
    }

    public DebugBox2D getDebugBox2D() {
        return debugBox2D;
    }

    public void setDebugBox2D(DebugBox2D debugBox2D) {
        this.debugBox2D = debugBox2D;
    }

    /**
     * Gets the shared preferences file.
     *
     * @return The shared preferences file.
     */
    private Preferences getPreferences() {
        return Gdx.app.getPreferences("vn.lequan.warrior.preferences");
    }

    /**
     * Saves a new high score to the shared preferences so that it can be retrieved later on.
     *
     * @param score The score to be saved.
     */
    public void saveHighScore(int score) {
        Preferences preferences = getPreferences();

        preferences.putInteger("highscore", score);
        preferences.flush();
    }

    /**
     * Reads the high score from the shared preferences file.
     *
     * @return The high score.
     */
    public int readHighScore() {
        Preferences preferences = getPreferences();

        return preferences.getInteger("highscore", 0);
    }

    public OrthographicCamera getCam() {
        return cam;
    }

    public void updateCamPosition(Vector2 worldPosition) {
        cam.position.x = Positions.convertWorldCoordToMap(worldPosition.x);
        cam.position.y = Positions.convertWorldCoordToMap(worldPosition.y);
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public int[] getScreenCenter() {
        return screenCenter;
    }

    public World getWorld() {
        return world;
    }

    private static ShaderProgram colorOverlyShader() {
        String vertexShader = "attribute vec4 " + ShaderProgram.POSITION_ATTRIBUTE + ";\n" //
                + "attribute vec4 " + ShaderProgram.COLOR_ATTRIBUTE + ";\n" //
                + "attribute vec2 " + ShaderProgram.TEXCOORD_ATTRIBUTE + "0;\n" //
                + "uniform mat4 u_projTrans;\n" //
                + "varying vec4 v_color;\n" //
                + "varying vec2 v_texCoords;\n" //
                + "\n" //
                + "void main()\n" //
                + "{\n" //
                + "   v_color = " + ShaderProgram.COLOR_ATTRIBUTE + ";\n" //
                + "   v_color.a = v_color.a * (255.0/254.0);\n" //
                + "   v_texCoords = " + ShaderProgram.TEXCOORD_ATTRIBUTE + "0;\n" //
                + "   gl_Position =  u_projTrans * " + ShaderProgram.POSITION_ATTRIBUTE + ";\n" //
                + "}\n";
        String fragmentShader = "#ifdef GL_ES\n" //
                + "#define LOWP lowp\n" //
                + "precision mediump float;\n" //
                + "#else\n" //
                + "#define LOWP \n" //
                + "#endif\n" //
                + "varying LOWP vec4 v_color;\n" //
                + "varying vec2 v_texCoords;\n" //
                + "uniform sampler2D u_texture;\n" //
                + "void main()\n"//
                + "{\n" //
                + "  gl_FragColor = texture2D(u_texture, v_texCoords);\n" //
                + "  gl_FragColor.rgb = mix(gl_FragColor.rgb, v_color.rgb, v_color.a);\n" //
                + "}";

        ShaderProgram shader = new ShaderProgram(vertexShader, fragmentShader);

        if (!shader.isCompiled()) {
            throw new IllegalArgumentException("Error compiling shader: " + shader.getLog());
        }

        return shader;
    }

}
