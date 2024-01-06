package fr.kizafox.aloneguy.game.world;

import fr.kizafox.aloneguy.game.client.window.Game;
import fr.kizafox.aloneguy.game.entity.Entity;
import fr.kizafox.aloneguy.game.entity.item.Item;
import fr.kizafox.aloneguy.game.entity.item.ItemManager;
import fr.kizafox.aloneguy.game.entity.player.Player;

import java.awt.*;

import static fr.kizafox.aloneguy.game.utils.GameSettings.*;

public class CollisionChecker {

    protected final Game game;

    public CollisionChecker(final Game game) {
        this.game = game;
    }

    public void checkTile(final Entity entity){
        final int entityLeftWorldX = (int) (entity.getWorldX() + entity.getHitBox().x);
        final int entityRightWorldX = (int) (entity.getWorldX() + entity.getHitBox().x + entity.getHitBox().width);
        final int entityTopWorldY = (int) (entity.getWorldY() + entity.getHitBox().y);
        final int entityBottomWorldY = (int) (entity.getWorldY() + entity.getHitBox().y + entity.getHitBox().height);

        int entityLeftColumn = entityLeftWorldX / TILES_SIZE;
        int entityRightColumn = entityRightWorldX / TILES_SIZE;
        int entityTopRow = entityTopWorldY / TILES_SIZE;
        int entityBottomRow = entityBottomWorldY / TILES_SIZE;

        int tileNumber1, tileNumber2;

        final TileManager tileManager = this.game.getPlayMenu().getTileManager();

        if(entity.isUp()) {
            entityTopRow = (int) ((entityTopWorldY - entity.getSpeed()) / TILES_SIZE);
            tileNumber1 = tileManager.mapTileNumber[entityLeftColumn][entityTopRow];
            tileNumber2 = tileManager.mapTileNumber[entityRightColumn][entityTopRow];

            if (tileManager.tile[tileNumber1].collision || tileManager.tile[tileNumber2].collision) entity.collision = true;
        }
        else if(entity.isDown()){
            entityBottomRow = (int) ((entityBottomWorldY + entity.getSpeed()) / TILES_SIZE);
            tileNumber1 = tileManager.mapTileNumber[entityLeftColumn][entityBottomRow];
            tileNumber2 = tileManager.mapTileNumber[entityRightColumn][entityBottomRow];

            if (tileManager.tile[tileNumber1].collision || tileManager.tile[tileNumber2].collision) entity.collision = true;
        }else if(entity.isLeft()){
            entityLeftColumn = (int) ((entityLeftWorldX - entity.getSpeed()) / TILES_SIZE);
            tileNumber1 = tileManager.mapTileNumber[entityLeftColumn][entityTopRow];
            tileNumber2 = tileManager.mapTileNumber[entityLeftColumn][entityBottomRow];

            if (tileManager.tile[tileNumber1].collision || tileManager.tile[tileNumber2].collision) entity.collision = true;
        }else if(entity.isRight()){
            entityRightColumn = (int) ((entityRightWorldX + entity.getSpeed()) / TILES_SIZE);
            tileNumber1 = tileManager.mapTileNumber[entityRightColumn][entityTopRow];
            tileNumber2 = tileManager.mapTileNumber[entityRightColumn][entityBottomRow];

            if (tileManager.tile[tileNumber1].collision || tileManager.tile[tileNumber2].collision) entity.collision = true;
        }

        for (final Item item : this.game.getPlayMenu().getItemManager().getItems().keySet()) {
            final Player player = this.game.getPlayMenu().getPlayer();

            if (player != null && item.isAvailable()) {
                Rectangle playerHitBox = player.getHitBox();
                Rectangle itemHitBox = item.getHitBox();

                if (playerHitBox != null && itemHitBox != null && playerHitBox.intersects(itemHitBox)) {
                    item.applyEffect();
                    item.setAvailable(true);
                }
            }
        }
    }
}
