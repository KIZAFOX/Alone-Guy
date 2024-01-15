package fr.kizafox.aloneguy.game.client.window.sub;

import fr.kizafox.aloneguy.game.client.window.Game;

import java.awt.*;
import java.awt.event.MouseEvent;

import static fr.kizafox.aloneguy.game.utils.GameSettings.*;

public class LoseMenu extends WindowAbstract implements ActionListener{

    protected final Game game;

    public LoseMenu(final Game game) {
        this.game = game;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(new Color(0, 0, 0, 100));
        graphics.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);

        renderText(graphics, NAME + " - VOUS AVEZ PERDU", 30, Color.WHITE, 4, 20);
        renderText(graphics, "Appuie sur Echap pour revenir au menu principal", 20, Color.WHITE, 6, 20);
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
