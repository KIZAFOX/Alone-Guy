package fr.kizafox.aloneguy.game.world;

import fr.kizafox.aloneguy.game.client.window.Game;
import fr.kizafox.aloneguy.game.entity.player.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

import static fr.kizafox.aloneguy.game.utils.GameSettings.*;

public class TileManager {

    protected final Game game;

    private final Tile[] tile;
    private final int[][] mapTileNumber;

    public TileManager(final Game game) {
        this.game = game;

        this.tile = new Tile[10];
        this.mapTileNumber = new int[MAX_WORLD_COLUMN][MAX_WORLD_ROW];

        this.tile[0] = new Tile();
        this.tile[0].image = this.getImagePath("grass.png");

        this.tile[1] = new Tile();
        this.tile[1].image = this.getImagePath("wall.png");

        this.tile[2] = new Tile();
        this.tile[2].image = this.getImagePath("water.png");

        this.tile[3] = new Tile();
        this.tile[3].image = this.getImagePath("earth.png");

        this.tile[4] = new Tile();
        this.tile[4].image = this.getImagePath("tree.png");

        this.tile[5] = new Tile();
        this.tile[5].image = this.getImagePath("sand.png");

        try {
            final InputStream inputStream = this.getClass().getResourceAsStream("/map/default_map.txt");
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream)));

            int column = 0, row = 0;

            while (column < MAX_WORLD_COLUMN && row < MAX_WORLD_ROW){
                String line = bufferedReader.readLine();

                while(column < MAX_WORLD_COLUMN){
                    String[] numbers = line.split(" ");
                    int number = Integer.parseInt(numbers[column]);

                    mapTileNumber[column][row] = number;
                    column++;
                }

                if(column == MAX_WORLD_COLUMN){
                    column = 0;
                    row++;
                }
            }

            bufferedReader.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void render(final Graphics graphics){
        int worldColumn = 0, worldRow = 0;

        while (worldColumn < MAX_WORLD_COLUMN && worldRow < MAX_WORLD_ROW){
            int tileNumber = this.mapTileNumber[worldColumn][worldRow];

            int
                    worldX = worldColumn * TILES_SIZE,
                    worldY = worldRow * TILES_SIZE;

            final Player player = this.game.getPlayMenu().getPlayer();

            int
                    screenX = (int) (worldX - player.getWorldX() + player.screenX),
                    screenY = (int) (worldY - player.getWorldY() + player.screenY);

            if(worldX + TILES_SIZE > player.getWorldX() - player.screenX &&
               worldX - TILES_SIZE < player.getWorldX() + player.screenX &&
               worldY + TILES_SIZE > player.getWorldY() - player.screenY &&
               worldY - TILES_SIZE < player.getWorldY() + player.screenY){
                graphics.drawImage(this.tile[tileNumber].image, screenX, screenY, TILES_SIZE, TILES_SIZE, null);
            }

            worldColumn++;

            if (worldColumn == MAX_WORLD_COLUMN){
                worldColumn = 0;
                worldRow++;
            }
        }
    }


    private BufferedImage getImagePath(String fileName){
        try {
            return ImageIO.read(Objects.requireNonNull(this.getClass().getResourceAsStream("/tiles/" + fileName)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
