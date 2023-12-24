package fr.kizafox.aloneguy.game.client.window;

import fr.kizafox.aloneguy.game.client.inputs.keyboard.KeyBoardInputs;
import fr.kizafox.aloneguy.game.client.inputs.mouse.MouseInputs;

import javax.swing.*;
import java.awt.*;

import static fr.kizafox.aloneguy.game.utils.GameSettings.*;

/**
 * Created at 19/12/2023 at 06:39
 * Made by @KIZAFOX (twitter)
 **/

public class GamePanel extends JPanel {

    protected final Game game;

    public GamePanel(final Game game){
        this.game = game;

        this.setFocusable(true);
        this.requestFocusInWindow();

        final Dimension dimension = new Dimension(GAME_WIDTH, GAME_HEIGHT);
        this.setMinimumSize(dimension);
        this.setPreferredSize(dimension);
        this.setMaximumSize(dimension);

        this.addKeyListener(new KeyBoardInputs(this));

        final MouseInputs mouseInputs = new MouseInputs(this);
        this.addMouseListener(mouseInputs);
        this.addMouseMotionListener(mouseInputs);
    }

    @Override
    protected void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        this.game.render(graphics);
    }

    public Game getGame() {
        return game;
    }
}
