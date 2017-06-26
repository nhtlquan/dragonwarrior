/*
 * Copyright (c) 2017 Warrior Dragon Contributors
 * This program is made available under the terms of the MIT License.
 */

package vn.lequan.warrior.views;


import vn.lequan.warrior.util.Updateable;

/**
 * Interface for the game views.
 */
public interface View<V, P extends Presenter<V>> extends Updateable {

    void render();

    void setPresenter(P presenter);

    void navigateTo(View view);

    /**
     * Event of tapping.
     *
     * @param eventX X-coordinate where user has tapped.
     * @param eventY Y-coordinate where user has tapped.
     * @return {@code true} if the event was handled;
     * {@code false} otherwise.
     */
    boolean onTap(float eventX, float eventY);

    /**
     * Event of sliding.
     *
     * @param velocityX X velocity of the sliding.
     * @param velocityY Y velocity of the sliding.
     * @return {@code true} if the event was handled;
     * {@code false} otherwise.
     */
    boolean onFling(float velocityX, float velocityY);

    /**
     * Event of double tapping.
     *
     * @return {@code true} if the event was handled;
     * {@code false} otherwise.
     */
    boolean onLongPress();

}
