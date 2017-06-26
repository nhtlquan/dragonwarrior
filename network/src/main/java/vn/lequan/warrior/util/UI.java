package vn.lequan.warrior.util;


import vn.lequan.warrior.ui.buttons.MenuButton;

public class UI {

    private UI() {
    }

    /**
     * Places a list of buttons on the screen in two columns.
     *
     * @param buttons The list of buttons to be placed on the screen.
     * @param startY  The starter y-coordinate where to place the buttons.
     */
    public static void placeButtonsOnTwoColumns(App app, MenuButton[] buttons, int startY) {
        int x;
        int y = 0;
        int i = 0;

        for (MenuButton button : buttons) {
            x = app.getScreenCenter()[0] - (button.getWidth()) / 2;
            y += 200;
            button.set(x, y);
            i++;
        }
    }

}
