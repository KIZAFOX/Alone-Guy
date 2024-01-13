package fr.kizafox.aloneguy.game.world.tile;

import fr.kizafox.aloneguy.game.client.window.Game;
import fr.kizafox.aloneguy.game.entity.player.Player;
import fr.kizafox.aloneguy.game.utils.image.ImageRenderer;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static fr.kizafox.aloneguy.game.utils.GameSettings.*;

public class TileManager {

    protected final Game game;

    public final Tile[] tile;
    public final int[][] mapTileNumbers;

    public int MAX_WORLD_COLUMN, MAX_WORLD_ROW;

    public TileManager(final Game game) {
        this.game = game;

        final List<String> filesName = new ArrayList<>();
        final List<String> collision1 = new ArrayList<>();

        InputStream inputStream = this.getClass().getResourceAsStream("/map/tiles/data/tiledata.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream)));

        String line;

        try {
            while ((line = bufferedReader.readLine()) != null){
                filesName.add(line);
                collision1.add(bufferedReader.readLine());
            }
        }catch (final IOException e){
            throw new RuntimeException(e);
        }

        this.tile = new Tile[filesName.size()];

        for(int i = 0; i < filesName.size(); i++){
            final String fileName = filesName.get(i);
            final String collision = collision1.get(i);

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

            int screenX = (int) (worldX - player.getWorldX() + player.getScreenX()), screenY = (int) (worldY - player.getWorldY() + player.getScreenY());

            if(worldX + TILES_SIZE > player.getWorldX() - player.getScreenX() &&
                    worldX - TILES_SIZE < player.getWorldX() + player.getScreenX() &&
                    worldY + TILES_SIZE > player.getWorldY() - player.getScreenY() &&
                    worldY - TILES_SIZE < player.getWorldY() + player.getScreenY()){
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
