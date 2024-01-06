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

    public WorldCreator(final Game game) {
        this.game = game;
    }

    public void update(final Entity entity) {
        if(entity instanceof final Player player) {
            for(final Enemy enemy : this.game.getPlayMenu().getEnemyManager().getEnemies().keySet()) {
                Rectangle hitBox1 = player.getHitBox();
                Rectangle hitBox2 = enemy.getHitBox();

                if(hitBox1 != null && hitBox2 != null && hitBox1.intersects(hitBox2)) {
                    float directionX = player.getWorldX() - enemy.getWorldX();
                    float directionY = player.getWorldY() - enemy.getWorldY();

                    float distance = (float) Math.sqrt(directionX * directionX + directionY * directionY);

                    if (distance != 0) {
                        directionX /= distance;
                        directionY /= distance;
                    }

                    float desiredDistance = 50.0F;

                    float overlapFactor = 0.2F;

                    float targetWorldX = player.getWorldX() - directionX * (desiredDistance - overlapFactor * entity.getWidth());
                    float targetWorldY = player.getWorldY() - directionY * (desiredDistance - overlapFactor * entity.getHeight());

                    enemy.setWorldX(targetWorldX);
                    enemy.setWorldY(targetWorldY);

                    player.applyDamage(.25D);
                    enemy.applyDamage(1.0D);

                    enemy.setWorldX(enemy.getWorldX() - directionX * BOUNCE_FORCE);
                    enemy.setWorldY(enemy.getWorldY() - directionY * BOUNCE_FORCE);
                }
            }
        }
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