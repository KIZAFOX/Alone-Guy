package fr.kizafox.aloneguy.game.world;

import fr.kizafox.aloneguy.game.client.window.Game;
import fr.kizafox.aloneguy.game.entity.Entity;
import fr.kizafox.aloneguy.game.entity.object.ObjectHandler;

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
            tileNumber1 = tileManager.mapTileNumbers[entityLeftColumn][entityTopRow];
            tileNumber2 = tileManager.mapTileNumbers[entityRightColumn][entityTopRow];

            if (tileManager.tile[tileNumber1].collision || tileManager.tile[tileNumber2].collision) entity.collision = true;
        }
        else if(entity.isDown()){
            entityBottomRow = (int) ((entityBottomWorldY + entity.getSpeed()) / TILES_SIZE);
            tileNumber1 = tileManager.mapTileNumbers[entityLeftColumn][entityBottomRow];
            tileNumber2 = tileManager.mapTileNumbers[entityRightColumn][entityBottomRow];

            if (tileManager.tile[tileNumber1].collision || tileManager.tile[tileNumber2].collision) entity.collision = true;
        }else if(entity.isLeft()){
            entityLeftColumn = (int) ((entityLeftWorldX - entity.getSpeed()) / TILES_SIZE);
            tileNumber1 = tileManager.mapTileNumbers[entityLeftColumn][entityTopRow];
            tileNumber2 = tileManager.mapTileNumbers[entityLeftColumn][entityBottomRow];

            if (tileManager.tile[tileNumber1].collision || tileManager.tile[tileNumber2].collision) entity.collision = true;
        }else if(entity.isRight()){
            entityRightColumn = (int) ((entityRightWorldX + entity.getSpeed()) / TILES_SIZE);
            tileNumber1 = tileManager.mapTileNumbers[entityRightColumn][entityTopRow];
            tileNumber2 = tileManager.mapTileNumbers[entityRightColumn][entityBottomRow];

            if (tileManager.tile[tileNumber1].collision || tileManager.tile[tileNumber2].collision) entity.collision = true;
        }
    }

    public int checkObject(final Entity entity, final boolean player){
        int index = 999;

        final ObjectHandler[] objectHandler = this.game.getPlayMenu().getObjectManager().getObjectHandler();

        for(int i = 0; i < objectHandler.length; i++){
            if(objectHandler[i] != null){
                entity.getHitBox().x = (int) (entity.getWorldX() + entity.getHitBox().x);
                entity.getHitBox().y = (int) (entity.getWorldY() + entity.getHitBox().y);

                objectHandler[i].getHitBox().x = objectHandler[i].getWorldX() + objectHandler[i].getHitBox().x;
                objectHandler[i].getHitBox().y = objectHandler[i].getWorldY() + objectHandler[i].getHitBox().y;

                if(entity.isUp()){
                    entity.getHitBox().y -= (int) entity.getSpeed();

                    if(entity.getHitBox().intersects(objectHandler[i].getHitBox())){
                        if(objectHandler[i].isCollision()){
                            entity.collision = true;
                        }

                        if(player){
                            index = i;
                        }
                    }
                }else if(entity.isDown()){
                    entity.getHitBox().y += (int) entity.getSpeed();

                    if(entity.getHitBox().intersects(objectHandler[i].getHitBox())){
                        if(objectHandler[i].isCollision()){
                            entity.collision = true;
                        }

                        if(player){
                            index = i;
                        }
                    }
                }else if(entity.isLeft()){
                    entity.getHitBox().x -= (int) entity.getSpeed();

                    if(entity.getHitBox().intersects(objectHandler[i].getHitBox())){
                        if(objectHandler[i].isCollision()){
                            entity.collision = true;
                        }

                        if(player){
                            index = i;
                        }
                    }
                }else if(entity.isRight()){
                    entity.getHitBox().x += (int) entity.getSpeed();

                    if(entity.getHitBox().intersects(objectHandler[i].getHitBox())){
                        if(objectHandler[i].isCollision()){
                            entity.collision = true;
                        }

                        if(player){
                            index = i;
                        }
                    }
                }

                entity.getHitBox().x = entity.getSolidAreaDefaultX();
                entity.getHitBox().y = entity.getSolidAreaDefaultY();

                objectHandler[i].getHitBox().x = objectHandler[i].getSolidAreaDefaultX();
                objectHandler[i].getHitBox().y = objectHandler[i].getSolidAreaDefaultY();
            }
        }

        return index;
    }
}
