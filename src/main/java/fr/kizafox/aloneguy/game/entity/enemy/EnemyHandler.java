package fr.kizafox.aloneguy.game.entity.enemy;

import fr.kizafox.aloneguy.game.client.window.Game;
import fr.kizafox.aloneguy.game.entity.Entity;
import fr.kizafox.aloneguy.game.entity.player.Player;

import java.awt.*;
import java.awt.image.BufferedImage;

import static fr.kizafox.aloneguy.game.utils.GameSettings.*;

public abstract class EnemyHandler extends Entity {

    protected final Game game;

    protected EnemyType enemyType;
    protected BufferedImage image;

    public EnemyHandler(final Game game, EnemyType enemyType, double health, int damage, float speed, int worldX, int worldY, BufferedImage image) {
        super(EntityType.ENEMY, worldX * TILES_SIZE, worldY * TILES_SIZE, (int) (64 * SCALE), (int) (40 * SCALE), health, damage);
        this.game = game;
        this.health = health;
        this.damage = damage;
        this.setSpeed(speed);
        this.enemyType = enemyType;
        this.image = image;

        this.initHitBox(8, 16, TILES_DEFAULT_SIZE, TILES_DEFAULT_SIZE);
    }

    @Override
    public void render(Graphics graphics) {
        final Player player = this.game.getPlayMenu().getPlayer();

        int screenX = (int) (worldX - player.getWorldX() + player.screenX);
        int screenY = (int) (worldY - player.getWorldY() + player.screenY);

        if (worldX + TILES_SIZE > player.getWorldX() - player.screenX &&
                worldX - TILES_SIZE < player.getWorldX() + player.screenX &&
                worldY + TILES_SIZE > player.getWorldY() - player.screenY &&
                worldY - TILES_SIZE < player.getWorldY() + player.screenY) {
            graphics.drawImage(image, screenX, screenY, TILES_SIZE, TILES_SIZE, null);

            graphics.setColor(Color.RED);
            graphics.fillRect(screenX + 5, screenY - 9, (int) ((health / maxHealth) * TILES_SIZE), 5);
        }

        this.renderHitBox(graphics, screenX, screenY);
    }

    public abstract void onEnemyTakeDamage();

    public abstract void onPlayerTakeDamage();

    public Game getGame() {
        return game;
    }

    public EnemyType getEnemyType() {
        return enemyType;
    }

    public void setEnemyType(EnemyType enemyType) {
        this.enemyType = enemyType;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public enum EnemyType {
        ZOMBIE
    }
}