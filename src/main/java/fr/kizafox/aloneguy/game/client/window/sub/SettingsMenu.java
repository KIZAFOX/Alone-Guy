package fr.kizafox.aloneguy.game.client.window.sub;

import fr.kizafox.aloneguy.game.utils.WindowUtils;

import java.awt.*;

import static fr.kizafox.aloneguy.game.utils.GameSettings.*;

public class SettingsMenu extends WindowAbstract{

    public SettingsMenu() {
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);

        WindowUtils.drawCenteredString(graphics, NAME + " - Settings", 30, Color.WHITE, 4, 20);
    }
}
