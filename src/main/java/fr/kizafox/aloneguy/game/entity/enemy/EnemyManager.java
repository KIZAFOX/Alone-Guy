package fr.kizafox.aloneguy.game.entity.enemy;

import fr.kizafox.aloneguy.game.client.window.Game;
import fr.kizafox.aloneguy.game.entity.enemy.zombie.Zombie;
import fr.kizafox.aloneguy.game.entity.player.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class EnemyManager {

    protected final Game game;

    private final List<EnemyHandler> enemies;

    public EnemyManager(final Game game) {
        this.game = game;

        this.enemies = new ArrayList<>();
        this.enemies.add(new Zombie(game));
    }

    public void update() {
        final List<EnemyHandler> enemiesCopy = new ArrayList<>(this.enemies);

        for (EnemyHandler enemy : enemiesCopy) {
            if (enemy != null) {
                enemy.update();
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

    public void attackPlayer(final int index, final Player player){
        if(index != 999){
            this.getEnemy(index).enemyAttack(player);
        }
    }

    public List<EnemyHandler> getEnemiesInRange(final Player player) {
        final List<EnemyHandler> enemiesInRange = new ArrayList<>();

        for(final EnemyHandler enemy : this.game.getPlayMenu().getEnemyManager().getEnemies()) {
            final int deltaX = (int) Math.abs(enemy.getWorldX() - player.getWorldX()), deltaY = (int) Math.abs(enemy.getWorldY() - player.getWorldY());

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
