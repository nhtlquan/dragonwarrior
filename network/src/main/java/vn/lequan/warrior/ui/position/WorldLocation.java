package vn.lequan.warrior.ui.position;


import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.math.Vector2;

public class WorldLocation implements Location<Vector2> {

    private final Vector2 position = new Vector2();

    public void set(Vector2 position) {
        set(position.x, position.y);
    }

    public void set(float x, float y) {
        position.x = x;
        position.y = y;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    @Override
    public Vector2 getPosition() {
        return position;
    }

    @Override
    public float getOrientation() {
        return 0;
    }

    @Override
    public void setOrientation(float orientation) {
    }

    @Override
    public float vectorToAngle(Vector2 vector) {
        return 0;
    }

    @Override
    public Vector2 angleToVector(Vector2 outVector, float angle) {
        return null;
    }

    @Override
    public Location<Vector2> newLocation() {
        return null;
    }
}
