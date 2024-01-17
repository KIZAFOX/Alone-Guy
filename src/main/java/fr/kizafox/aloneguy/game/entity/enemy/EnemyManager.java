package fr.kizafox.aloneguy.game.entity.enemy;

import fr.kizafox.aloneguy.game.client.status.GameState;
import fr.kizafox.aloneguy.game.client.window.Game;
import fr.kizafox.aloneguy.game.entity.enemy.slime.Slime;
import fr.kizafox.aloneguy.game.entity.player.Player;

import java.awt.*;
import java.util.*;
import java.util.List;

public class EnemyManager {

    protected final Game game;

    private final List<EnemyHandler> enemies;

    public EnemyManager(final Game game) {
        this.game = game;

        this.enemies = new ArrayList<>();
    }

    public void update() {
        final List<EnemyHandler> enemiesCopy = new ArrayList<>(this.enemies);

        enemiesCopy.parallelStream()
                .filter(Objects::nonNull)
                .forEach(enemyHandler -> {
                    enemyHandler.update();

                    if (enemyHandler.getHealth() <= 0) {
                        this.enemies.remove(enemyHandler);
                        game.getPlayMenu().getPlayer().gainExperience(enemyHandler.getExpGain());
                    }
        });
    }

    public void render(Graphics graphics) {
        for (final EnemyHandler enemy : this.enemies) {
            if (enemy != null) {
                enemy.render(graphics);
            }
        }
    }

    public TimerTask spawnEnemy() {
        return new TimerTask() {
            @Override
            public void run() {
                if (!GameState.getCurrentState().equals(GameState.PAUSE)) {
                    final Slime newSlime = new Slime(game);

                    newSlime.setAbsoluteX(newSlime.randomX(game.getPlayMenu().getPlayer()));
                    newSlime.setAbsoluteY(newSlime.randomY(game.getPlayMenu().getPlayer()));

                    enemies.add(newSlime);
                }
            }
        };
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

    public void reset() {
        this.enemies.forEach(enemies -> {
            enemies.setHealth(0.0D);
            enemies.reset();
        });
        this.enemies.clear();
    }

    public Game getGame() {
        return game;
    }

    public List<EnemyHandler> getEnemies() {
        return enemies;
    }
}
