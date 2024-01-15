package fr.kizafox.aloneguy.game.entity.enemy;

import fr.kizafox.aloneguy.game.client.window.Game;
import fr.kizafox.aloneguy.game.entity.enemy.slime.Slime;
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
        this.addEnemies();
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

        for (final EnemyHandler enemy : this.game.getPlayMenu().getEnemyManager().getEnemies()) {
            final int deltaX = (int) Math.abs(enemy.getAbsoluteX() - player.getScreenX());
            final int deltaY = (int) Math.abs(enemy.getAbsoluteY() - player.getScreenY());

            if (deltaX < 100 && deltaY < 100 && this.isPlayerFacingEnemy(player, enemy)) {
                enemiesInRange.add(enemy);
            }
        }

        return enemiesInRange;
    }

    private boolean isPlayerFacingEnemy(Player player, EnemyHandler enemy) {
        if(player.isWasFacingLeft() && enemy.getAbsoluteX() < player.getScreenX()){
            return true;
        }else return !player.isWasFacingLeft() && enemy.getAbsoluteX() > player.getScreenX();
    }

    public EnemyHandler getEnemy(int enemyIndex) {
        if (enemyIndex >= 0 && enemyIndex < enemies.size()) {
            return enemies.get(enemyIndex);
        } else {
            return null;
        }
    }

    public void addEnemies() {
        this.enemies.add(new Slime(this.game));
    }

    public void reset() {
        this.enemies.clear();
        this.addEnemies();
    }

    public Game getGame() {
        return game;
    }

    public LinkedList<EnemyHandler> getEnemies() {
        return enemies;
    }
}
