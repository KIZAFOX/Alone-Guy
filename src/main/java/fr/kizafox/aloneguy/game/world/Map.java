package fr.kizafox.aloneguy.game.world;

import fr.kizafox.aloneguy.game.client.window.Game;
import fr.kizafox.aloneguy.game.entity.enemy.EnemyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

import static fr.kizafox.aloneguy.game.utils.GameSettings.*;

public class Map {

    protected final Game game;

    private final BufferedImage worldMap;

    public Map(Game game) {
        this.game = game;

        final int worldMapWidth = TILES_SIZE * game.getPlayMenu().getTileManager().MAX_WORLD_COLUMN;
        final int worldMapHeight = TILES_SIZE * game.getPlayMenu().getTileManager().MAX_WORLD_ROW;

        this.worldMap = new BufferedImage(worldMapWidth, worldMapHeight, BufferedImage.TYPE_INT_ARGB);

        final Graphics2D graphics = this.worldMap.createGraphics();
        int column = 0, row = 0;

        while (column < game.getPlayMenu().getTileManager().MAX_WORLD_COLUMN && row < game.getPlayMenu().getTileManager().MAX_WORLD_ROW){
            final int tileNumber = game.getPlayMenu().getTileManager().mapTileNumbers[column][row];
            final int tileX = column * TILES_SIZE;
            final int tileY = row * TILES_SIZE;

            graphics.drawImage(game.getPlayMenu().getTileManager().tile[tileNumber].image, tileX, tileY, null);

            column++;
            if(column == game.getPlayMenu().getTileManager().MAX_WORLD_COLUMN){
                column = 0;
                row++;
            }
        }
    }

    public void render(Graphics graphics) {
        graphics.setColor(new Color(0, 0, 0, 0.5F));
        graphics.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);

        final int width = 500, height = 500;
        final int x = GAME_WIDTH / 2 - width / 2, y = GAME_HEIGHT / 2 - height / 2;

        graphics.drawImage(this.worldMap, x, y, width, height, null);

        final double scale = (double) (TILES_SIZE * game.getPlayMenu().getTileManager().MAX_WORLD_COLUMN) / width;

        int
                playerX = (int) ((x + this.game.getPlayMenu().getPlayer().getWorldX() / scale)),
                playerY = (int) ((y + this.game.getPlayMenu().getPlayer().getWorldY() / scale)),
                playerSize = (int) (TILES_SIZE / scale);

        final int xOffset = 5, yOffset = 10;

        playerX += xOffset;
        playerY += yOffset;

        graphics.drawImage(this.game.getPlayMenu().getPlayer().animations[0][0], playerX, playerY, playerSize, playerSize, null);

        graphics.setColor(Color.WHITE);
        graphics.setFont(new Font("Arial", Font.BOLD, 32));
        graphics.drawString("Press M to close", 950, 650);
    }

    public void renderMinimap(Graphics graphics){
        if(graphics instanceof final Graphics2D graphics2D){
            int width = 200, height = 200;
            int x = GAME_WIDTH - width - 50, y = 50;

            graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
            graphics2D.drawImage(this.worldMap, x, y, width, height, null);

            final double scale = (double) (TILES_SIZE * game.getPlayMenu().getTileManager().MAX_WORLD_COLUMN) / width;

            int
                    playerX = (int) ((x + this.game.getPlayMenu().getPlayer().getWorldX() / scale)),
                    playerY = (int) ((y + this.game.getPlayMenu().getPlayer().getWorldY() / scale)),
                    playerSize = (int) ((TILES_SIZE / scale) * 2);


            final int xOffset = 5, yOffset = 10;

            playerX += xOffset;
            playerY += yOffset;

            graphics.drawImage(this.game.getPlayMenu().getPlayer().animations[0][0], playerX, playerY, playerSize, playerSize, null);
            graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

            graphics.setColor(Color.BLACK);
            graphics.setFont(new Font("Arial", Font.BOLD, 10));
            graphics.drawString("Press UP to close", x + 100, y * 5 - 10);
        }
    }
}
