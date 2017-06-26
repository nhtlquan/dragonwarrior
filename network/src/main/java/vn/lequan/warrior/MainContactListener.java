package vn.lequan.warrior;


import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

import vn.lequan.warrior.util.Collisionable;

public class MainContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        Fixture a = contact.getFixtureA();
        Fixture b = contact.getFixtureB();

        if (a != null && b != null && a.getUserData() != null && b.getUserData() != null) {
            ((Collisionable) a.getUserData()).collides(b.getUserData(), contact.getWorldManifold());
            ((Collisionable) b.getUserData()).collides(a.getUserData(), contact.getWorldManifold());
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

}
