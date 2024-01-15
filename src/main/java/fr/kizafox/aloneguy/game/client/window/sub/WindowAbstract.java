package fr.kizafox.aloneguy.game.client.window.sub;

import fr.kizafox.aloneguy.game.client.window.buttons.MenuButton;
import fr.kizafox.aloneguy.game.client.window.buttons.PauseButton;

import java.awt.*;
import java.awt.event.MouseEvent;

import static fr.kizafox.aloneguy.game.utils.GameSettings.*;

public abstract class WindowAbstract {

    public abstract void update();

    public abstract void render(final Graphics graphics);

    public void drawGrid(final Graphics graphics){
        graphics.setColor(Color.BLACK);
        for (int i = 0; i < GAME_WIDTH / TILES_SIZE; i++) {
            for (int j = 0; j < GAME_HEIGHT / TILES_SIZE; j++) {
                graphics.drawRect(i * TILES_SIZE, j * TILES_SIZE, TILES_SIZE, TILES_SIZE);
            }
        }
    }

    public boolean isIn(final MouseEvent mouseEvent, MenuButton menuButton){
        return menuButton.getBounds().contains(mouseEvent.getX(), mouseEvent.getY());
    }

    public boolean isIn(final MouseEvent mouseEvent, PauseButton pauseButton){
        return pauseButton.getBounds().contains(mouseEvent.getX(), mouseEvent.getY());
    }
}
