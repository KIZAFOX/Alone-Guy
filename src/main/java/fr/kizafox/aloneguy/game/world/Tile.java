package fr.kizafox.aloneguy.game.world;

import java.awt.*;

public record Tile(int x, int y, int width, int height, Color color) {

    public void render(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }
}