package fr.kizafox.aloneguy.game.entity.enemy.zombie;

import fr.kizafox.aloneguy.game.client.window.Game;
import fr.kizafox.aloneguy.game.entity.enemy.EnemyHandler;
import fr.kizafox.aloneguy.game.entity.player.Player;
import fr.kizafox.aloneguy.game.utils.ImageRenderer;

public class Zombie extends EnemyHandler {

    public Zombie(Game game) {
        super(game, EnemyType.ZOMBIE, 100, 0, 0.5F, 14, 10, ImageRenderer.load(ImageRenderer.ZOMBIE));
    }

    @Override
    public void update() {
        this.collision = false;
        this.game.getCollisionChecker().checkTile(this);

        if (!collision) {
            final Player player = game.getPlayMenu().getPlayer();

            final double deltaX = player.getWorldX() - worldX;
            final double deltaY = player.getWorldY() - worldY;

            final double angle = Math.atan2(deltaY, deltaX);

            final double moveX = Math.cos(angle) * speed;
            final double moveY = Math.sin(angle) * speed;

            worldX += (float) moveX;
            worldY += (float) moveY;
        }
    }

    @Override
    public void onEnemyTakeDamage() {
        final Player player = this.game.getPlayMenu().getPlayer();

        player.attack(this, player.getDamage());
    }

    @Override
    public void onPlayerTakeDamage() {
        final Player player = this.game.getPlayMenu().getPlayer();

        this.attack(player, this.getDamage());
    }
}
