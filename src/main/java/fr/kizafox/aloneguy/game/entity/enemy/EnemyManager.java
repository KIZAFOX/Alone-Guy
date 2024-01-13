package fr.kizafox.aloneguy.game.entity.enemy;

import fr.kizafox.aloneguy.game.client.window.Game;
import fr.kizafox.aloneguy.game.entity.enemy.zombie.Zombie;
import fr.kizafox.aloneguy.game.entity.player.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class EnemyManager {

    protected final Game game;

    private final LinkedList<EnemyHandler> enemies;

    public EnemyManager(final Game game) {
        this.game = game;

        this.enemies = new LinkedList<>();
        this.enemies.add(new Zombie());
    }

    public void update() {
        for (EnemyHandler enemy : this.enemies) {
            if (enemy != null) {
                enemy.update(game);
            }
        }
    }

    public void render(Graphics graphics) {
        for(final EnemyHandler enemy : this.enemies) {
            if (enemy != null) {
                enemy.render(graphics);
            }
        }
    }

    public List<EnemyHandler> getEnemiesInRange(final Player player) {
        final List<EnemyHandler> enemiesInRange = new ArrayList<>();

        for(final EnemyHandler enemy : this.game.getPlayMenu().getEnemyManager().getEnemies()) {
            final int deltaX = (int) Math.abs(enemy.x - player.getScreenX());
            final int deltaY = (int) Math.abs(enemy.y - player.getScreenY());

            if(deltaX < 100 && deltaY < 100) {
                enemiesInRange.add(enemy);
            }
        }

        return enemiesInRange;
    }

    public EnemyHandler getEnemy(int enemyIndex) {
        if (enemyIndex >= 0 && enemyIndex < enemies.size()) {
            return enemies.get(enemyIndex);
        } else {
            return null;
        }
    }

    public void removeEnemy(final int index){
        this.enemies.remove(index);
    }

    public Game getGame() {
        return game;
    }

    public List<EnemyHandler> getEnemies() {
        return enemies;
    }
}
