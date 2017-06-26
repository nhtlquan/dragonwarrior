/*
 * Copyright (c) 2017 Warrior Dragon Contributors
 * This program is made available under the terms of the MIT License.
 */

package vn.lequan.warrior.views;

import java.util.ArrayDeque;
import java.util.Deque;

import vn.lequan.warrior.util.Updateable;

/**
 * The game's view manager, used to manage all the game's views.
 */
public class ViewManager implements Updateable {

    /**
     * A stack of views.
     */
    private Deque<View> views;

    public ViewManager() {
        views = new ArrayDeque<>();
    }

    /**
     * Pushes a view to the top of the stack.
     *
     * @param view A game view.
     */
    public void push(View view) {
        views.push(view);
    }

    /**
     * Removes the view from the top of the stack and pushes a new view to the top of it.
     *
     * @param view A game view.
     */
    public void set(View view) {
        views.pop();
        views.push(view);
    }

    public void pop() {
        views.pop();
    }

    @Override
    public void update(long fps, float delta) {
        views.peek().update(fps, delta);
    }

    /**
     * Draw the game elements from the current game view.
     */
    public void render() {
        views.peek().render();
    }

    /**
     * Event of tapping.
     *
     * @param eventX X-coordinate where user has tapped.
     * @param eventY Y-coordinate where user has tapped.
     * @return {@code true} if the event was handled;
     * {@code false} otherwise.
     */
    public boolean onTap(float eventX, float eventY) {
        return views.peek().onTap(eventX, eventY);
    }

    /**
     * Event of sliding.
     *
     * @param velocityX X velocity of the sliding.
     * @param velocityY Y velocity of the sliding.
     * @return {@code true} if the event was handled;
     * {@code false} otherwise.
     */
    public boolean onFling(float velocityX, float velocityY) {
        return views.peek().onFling(velocityX, velocityY);
    }

    /**
     * Event of long press.
     *
     * @return {@code true} if the event was handled;
     * {@code false} otherwise.
     */
    public boolean onLongPress() {
        return views.peek().onLongPress();
    }
}
