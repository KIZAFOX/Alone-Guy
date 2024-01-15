package fr.kizafox.aloneguy.game.client.window.buttons;

import java.awt.*;

import static fr.kizafox.aloneguy.game.utils.GameSettings.*;

/**
 * Done by @KIZAFOX on {14/01/2024} at 13:13.
 **/
public class PauseButton {

    public int x, y, width, height;

    public Rectangle bounds;

    public static final int
            SOUND_DEFAULT_SIZE = 42,
            SOUND_SIZE = (int) (SOUND_DEFAULT_SIZE * SCALE);

    public static final int
            URM_SIZE_DEFAULT = 56,
            URM_SIZE = (int) (URM_SIZE_DEFAULT * SCALE);

    public static final int
            VOLUME_DEFAULT_WIDTH = 28,
            VOLUME_DEFAULT_HEIGHT = 44,
            SLIDER_DEFAULT_WIDTH = 215,

            VOLUME_WIDTH = (int) (VOLUME_DEFAULT_WIDTH * SCALE),
            VOLUME_HEIGHT = (int) (VOLUME_DEFAULT_HEIGHT * SCALE),
            SLIDER_WIDTH = (int) (SLIDER_DEFAULT_WIDTH * SCALE);

    public PauseButton(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        this.bounds = new Rectangle(x, y, width, height);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }
}
