package fr.kizafox.aloneguy.game.entity.enemy;

import fr.kizafox.aloneguy.game.client.window.Game;
import fr.kizafox.aloneguy.game.entity.Entity;
import fr.kizafox.aloneguy.game.entity.player.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static fr.kizafox.aloneguy.game.utils.GameSettings.*;

public class EnemyManager {

    protected final Game game;
    protected final Player player;

    protected final Enemy firstEnemy, secondEnemy;

    protected final Map<Enemy, Integer> enemies;

    public EnemyManager(final Game game, final Player player) {
        this.game = game;

        this.player = player;

        this.firstEnemy = new Enemy(this.game, this.player, (float) GAME_WIDTH / 2, (float) GAME_HEIGHT / 2 + 50, TILES_SIZE, TILES_SIZE, 10);
        this.secondEnemy = new Enemy(this.game, this.player, (float) GAME_WIDTH / 2, (float) GAME_HEIGHT / 2 + 250, TILES_SIZE, TILES_SIZE, 10 * 2);

        this.enemies = new HashMap<>();

        this.enemies.put(firstEnemy, 0);
        this.enemies.put(secondEnemy, 1);
    }

    public void update(){
        final List<Enemy> enemiesToRemove = new ArrayList<>();

        for (final Map.Entry<Enemy, Integer> entry : enemies.entrySet()) {
            final Enemy enemy = entry.getKey();

            enemy.update();
            if (!enemy.isAlive()) {
                enemiesToRemove.add(enemy);
            }
        }

        enemiesToRemove.forEach(this.enemies::remove);

        final Enemy[] enemiesArray = enemies.keySet().toArray(new Enemy[0]);

        for (int i = 0; i < enemiesArray.length; i++) {
            for (int j = i + 1; j < enemiesArray.length; j++) {
                Enemy enemy1 = enemiesArray[i];
                Enemy enemy2 = enemiesArray[j];

                if (enemy1.isAlive() && enemy2.isAlive() && enemy1.getHitBox().intersects(enemy2.getHitBox().getBounds2D())) {
                    float directionX = enemy1.getWorldX() - enemy2.getWorldX();
                    float directionY = enemy1.getWorldY() - enemy2.getWorldY();

                    float distance = (float) Math.sqrt(directionX * directionX + directionY * directionY);
                    directionX /= distance;
                    directionY /= distance;

                    float overlapFactor = 0.1f;
                    float targetWorldX1 = enemy1.getWorldX() + directionX * overlapFactor;
                    float targetWorldY1 = enemy1.getWorldY() + directionY * overlapFactor;
                    float targetWorldX2 = enemy2.getWorldX() - directionX * overlapFactor;
                    float targetWorldY2 = enemy2.getWorldY() - directionY * overlapFactor;

                    enemy1.setWorldX(targetWorldX1);
                    enemy1.setWorldY(targetWorldY1);
                    enemy2.setWorldX(targetWorldX2);
                    enemy2.setWorldY(targetWorldY2);
                }
            }
        }
    }

    public void render(final Graphics graphics){
        this.enemies.keySet().forEach(enemy -> enemy.render(graphics));
    }

    public void reset(){
        this.enemies.keySet().forEach(Entity::reset);
    }

    public int getAliveEnemies() {
        int aliveCount = 0;

        for (Map.Entry<Enemy, Integer> entry : enemies.entrySet()) {
            if (entry.getKey().isAlive()) aliveCount++;
        }

        return aliveCount;
    }

    public Game getGame() {
        return game;
    }

    public Player getPlayer() {
        return player;
    }

    public Map<Enemy, Integer> getEnemies() {
        return enemies;
    }

    public Enemy getFirstEnemy() {
        return firstEnemy;
    }

    public Enemy getSecondEnemy() {
        return secondEnemy;
    }
}
