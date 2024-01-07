package fr.kizafox.aloneguy.game.world;

import fr.kizafox.aloneguy.game.client.window.Game;
import fr.kizafox.aloneguy.game.entity.player.Player;
import fr.kizafox.aloneguy.game.utils.ImageRenderer;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static fr.kizafox.aloneguy.game.utils.GameSettings.*;

public class TileManager {

    protected final Game game;

    private final List<String> filesName;
    private final List<String> collision;

    public final Tile[] tile;
    public final int[][] mapTileNumbers;

    public final int MAX_WORLD_COLUMN, MAX_WORLD_ROW;

    public TileManager(final Game game) {
        this.game = game;

        this.filesName = new ArrayList<>();
        this.collision = new ArrayList<>();

        InputStream inputStream = this.getClass().getResourceAsStream("/map/tiles/data/tiledata.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream)));

        String line;

        try {
            while ((line = bufferedReader.readLine()) != null){
                this.filesName.add(line);
                this.collision.add(bufferedReader.readLine());
            }
        }catch (final IOException e){
            throw new RuntimeException(e);
        }

        this.tile = new Tile[this.filesName.size()];

        for(int i = 0; i < this.filesName.size(); i++){
            final String fileName = this.filesName.get(i);
            final String collision = this.collision.get(i);

            this.tile[i] = new Tile();
            this.tile[i].image = ImageRenderer.load("map/tiles/" + fileName);
            this.tile[i].image = ImageRenderer.scaleImage(this.tile[i].image, TILES_SIZE, TILES_SIZE);
            this.tile[i].collision = Boolean.parseBoolean(collision);
        }

        inputStream = this.getClass().getResourceAsStream("/map/default_map.txt");
        bufferedReader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream)));

        try {
            final String line2 = bufferedReader.readLine();
            final String[] maxTile = line2.split(" ");

            MAX_WORLD_COLUMN = maxTile.length;
            MAX_WORLD_ROW = maxTile.length;

            this.mapTileNumbers = new int[MAX_WORLD_COLUMN][MAX_WORLD_ROW];

            bufferedReader.close();
        }catch (final IOException e) {
            throw new RuntimeException(e);
        }

        try {
            inputStream = this.getClass().getResourceAsStream("/map/default_map.txt");
            bufferedReader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream)));

            int column = 0, row = 0;

            while (column < MAX_WORLD_COLUMN && row < MAX_WORLD_ROW){
                String line3 = bufferedReader.readLine();

                while(column < MAX_WORLD_COLUMN){
                    String[] numbers = line3.split(" ");
                    int number = Integer.parseInt(numbers[column]);

                    mapTileNumbers[column][row] = number;
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
            int tileNumber = this.mapTileNumbers[worldColumn][worldRow];

            int worldX = worldColumn * TILES_SIZE, worldY = worldRow * TILES_SIZE;

            final Player player = this.game.getPlayMenu().getPlayer();

            int screenX = (int) (worldX - player.getWorldX() + player.screenX), screenY = (int) (worldY - player.getWorldY() + player.screenY);

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
}
