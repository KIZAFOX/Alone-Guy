package fr.kizafox.aloneguy.game.world;

import fr.kizafox.aloneguy.game.client.window.Game;
import fr.kizafox.aloneguy.game.entity.Entity;
import fr.kizafox.aloneguy.game.entity.enemy.Enemy;
import fr.kizafox.aloneguy.game.entity.item.Item;
import fr.kizafox.aloneguy.game.entity.item.potion.Potion;
import fr.kizafox.aloneguy.game.entity.player.Player;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import static fr.kizafox.aloneguy.game.entity.enemy.Enemy.*;
import static fr.kizafox.aloneguy.game.utils.GameSettings.*;

public class WorldCreator {

    protected final Game game;
    protected final Tile[][] tiles;

    public WorldCreator(final Game game, int[][] worldMap, int tileWidth, int tileHeight) {
        this.game = game;

        final int rows = worldMap.length, cols = worldMap[0].length;
        this.tiles = new Tile[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                final Color color = switch (worldMap[i][j]) {
                    case 0 -> Color.GRAY; // mur
                    case 1 -> new Color(139, 69, 19); // terre
                    case 2 -> new Color(224, 205, 169); // sable
                    case 3 -> Color.BLUE; //eau
                    case 4 -> new Color(29, 86, 40); //herbe
                    case 5 -> new Color(58 , 157 , 35); //feuille
                    case 6 -> Color.MAGENTA; //TP
                    default -> Color.BLACK;
                };
                tiles[i][j] = new Tile(j * tileWidth, i * tileHeight, tileWidth, tileHeight, color);
            }
        }
    }

    public void update(final Entity entity) {
        float newX = entity.getX() + (entity.right ? entity.speed : entity.left ? -entity.speed : 0);
        float newY = entity.getY() + (entity.down ? entity.speed : entity.up ? -entity.speed : 0);

        int tileXLeft = (int) (newX / TILES_SIZE);
        int tileYTop = (int) (newY / TILES_SIZE);
        int tileXRight = (int) ((newX + entity.getWidth() - 1) / TILES_SIZE);
        int tileYBottom = (int) ((newY + entity.getHeight() - 1) / TILES_SIZE);

        Tile tileLeftTop = this.getTileAt(this.tiles, tileXLeft, tileYTop);
        Tile tileRightTop = this.getTileAt(this.tiles, tileXRight, tileYTop);
        Tile tileLeftBottom = this.getTileAt(this.tiles, tileXLeft, tileYBottom);
        Tile tileRightBottom = this.getTileAt(this.tiles, tileXRight, tileYBottom);

        if ((tileLeftTop != null && !tileLeftTop.color().equals(Color.GRAY))
                && (tileRightTop != null && !tileRightTop.color().equals(Color.GRAY))
                && (tileLeftBottom != null && !tileLeftBottom.color().equals(Color.GRAY))
                && (tileRightBottom != null && !tileRightBottom.color().equals(Color.GRAY))) {

            entity.setX(newX);
            entity.setY(newY);
        }

        if(entity instanceof final Player player) {
            for(final Enemy enemy : this.game.getPlayMenu().getEnemyManager().getEnemies().keySet()) {
                Rectangle2D.Float hitBox1 = player.getHitBox();
                Rectangle2D.Float hitBox2 = enemy.getHitBox();

                if(hitBox1 != null && hitBox2 != null && hitBox1.intersects(hitBox2)) {
                    float directionX = player.getX() - enemy.getX();
                    float directionY = player.getY() - enemy.getY();

                    float distance = (float) Math.sqrt(directionX * directionX + directionY * directionY);

                    if (distance != 0) {
                        directionX /= distance;
                        directionY /= distance;
                    }

                    float desiredDistance = 50.0F;

                    float overlapFactor = 0.2F;

                    float targetX = player.getX() - directionX * (desiredDistance - overlapFactor * entity.getWidth());
                    float targetY = player.getY() - directionY * (desiredDistance - overlapFactor * entity.getHeight());

                    enemy.setX(targetX);
                    enemy.setY(targetY);

                    player.applyDamage(.25D);
                    enemy.applyDamage(1.0D);

                    enemy.setX(enemy.getX() - directionX * BOUNCE_FORCE);
                    enemy.setY(enemy.getY() - directionY * BOUNCE_FORCE);
                }
            }
        }
    }

    public void update() {
        for (final Item item : this.game.getPlayMenu().getItemManager().getItems().keySet()) {
            final Player player = this.game.getPlayMenu().getPlayer();

            if (player != null && item.isAvailable()) {
                Rectangle2D.Float playerHitBox = player.getHitBox();
                Rectangle2D.Float itemHitBox = item.getHitBox();

                if (playerHitBox != null && itemHitBox != null && playerHitBox.intersects(itemHitBox)) {
                    item.applyEffect();
                    item.setAvailable(false);
                }
            }
        }
    }

    public void showMap(final Tile[][] tiles, final Graphics graphics){
        for (int i = 0; i < WORLD_MAP.length; i++) {
            for (int j = 0; j < WORLD_MAP[i].length; j++) {
                final Tile tile = getTileAt(tiles, j, i);

                tile.render(graphics);
            }
        }
    }

    public void showMap(final Tile[][] tiles, final Graphics graphics, final Player player){
        for (int i = 0; i < WORLD_MAP.length; i++) {
            for (int j = 0; j < WORLD_MAP[i].length; j++) {
                final Tile tile = getTileAt(tiles, j, i);

                double distance = Math.sqrt(Math.pow((j * TILES_SIZE) - player.getX(), 2) + Math.pow((i * TILES_SIZE) - player.getY(), 2));

                if (distance <= 5 * TILES_SIZE) tile.render(graphics);
            }
        }
    }

    public Tile getTileAt(final Tile[][] tiles, final int x, final int y) {
        if (x >= 0 && x < tiles[0].length && y >= 0 && y < tiles.length) {
            return tiles[y][x];
        } else {
            return null;
        }
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public static int[][] getWorldMap() {
        return WORLD_MAP;
    }

    public static int[][] WORLD_MAP = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 0},
            {0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 0},
            {0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 1, 5, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 0},
            {0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 1, 5, 5, 4, 4, 4, 4, 4, 4, 4, 4, 4, 0},
            {0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 0},
            {0, 4, 4, 4, 4, 4, 5, 4, 4, 4, 4, 4, 4, 1, 4, 4, 2, 2, 2, 2, 2, 2, 2, 2, 4, 0},
            {0, 4, 4, 4, 4, 5, 1, 5, 4, 4, 4, 4, 4, 1, 4, 4, 2, 3, 3, 3, 3, 3, 3, 2, 4, 0},
            {0, 4, 4, 4, 5, 5, 1, 5, 5, 4, 4, 4, 4, 4, 4, 4, 2, 3, 3, 3, 3, 3, 3, 2, 4, 0},
            {0, 4, 4, 4, 4, 4, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 2, 3, 3, 3, 3, 3, 3, 2, 4, 0},
            {0, 4, 4, 4, 4, 4, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 2, 3, 3, 3, 3, 3, 3, 2, 4, 0},
            {0, 4, 4, 4, 4, 4, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 2, 2, 2, 2, 2, 2, 2, 2, 4, 0},
            {0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    };
}