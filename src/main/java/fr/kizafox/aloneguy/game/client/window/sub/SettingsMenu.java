package fr.kizafox.aloneguy.game.client.window.sub;

import java.awt.*;
import java.awt.event.MouseEvent;

import static fr.kizafox.aloneguy.game.utils.GameSettings.*;

public class SettingsMenu extends WindowAbstract implements ActionListener{

    public SettingsMenu() {
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);

        renderText(graphics, NAME + " - Settings", 30, Color.WHITE, 4, 20);
    }

    @Override
    public void mousePressed(MouseEvent event) {

    }

    @Override
    public void mouseReleased(MouseEvent event) {

    }

    @Override
    public void mouseDragged(MouseEvent event) {

    }

    @Override
    public void mouseMoved(MouseEvent event) {

    }
}
