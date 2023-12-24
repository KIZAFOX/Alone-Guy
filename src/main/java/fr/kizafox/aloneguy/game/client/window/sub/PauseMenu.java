package fr.kizafox.aloneguy.game.client.window.sub;

import fr.kizafox.aloneguy.game.client.status.GameState;
import fr.kizafox.aloneguy.game.client.window.Game;
import fr.kizafox.aloneguy.game.utils.WindowUtils;

import java.awt.*;

import static fr.kizafox.aloneguy.game.utils.GameSettings.*;

public class PauseMenu extends WindowAbstract {

    protected final Game game;

    public Button back, mainMenu, restart;

    public boolean paused = false;

    public PauseMenu(final Game game) {
        this.game = game;
    }

    @Override
    public void update() {
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(new Color(0, 0, 0, 90));
        graphics.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);

        WindowUtils.drawCenteredString(graphics, NAME + " - Pause", 30, Color.WHITE, 4, 20);

        final int rectangleWidth = 250, rectangleHeight = 350;

        graphics.setColor(Color.BLACK);
        graphics.fillRect(((GAME_WIDTH - rectangleWidth) / 2), ((GAME_HEIGHT - rectangleHeight) / 2), rectangleWidth, rectangleHeight);
    }

    public void setPause(final GameState gameState, final boolean pause){
        GameState.setStatus(gameState);
        paused = pause;
    }
}
