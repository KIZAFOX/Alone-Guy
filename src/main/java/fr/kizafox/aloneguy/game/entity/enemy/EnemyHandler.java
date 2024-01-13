package fr.kizafox.aloneguy.game.entity.enemy;

import fr.kizafox.aloneguy.game.client.window.Game;
import fr.kizafox.aloneguy.game.entity.player.Player;
import fr.kizafox.aloneguy.game.utils.Colors;

import java.awt.*;

/**
 * Done by @KIZAFOX on {12/01/2024} at 22:07.
 **/
public abstract class EnemyHandler {

    public float x, y;
    public int expGain;
    public double maxHealth, health, speed, damage;

    public EnemyHandler(final float x, final float y, final int expGain, final double maxHealth, final double speed, final double damage) {
        this.x = x;
        this.y = y;
        this.expGain = expGain;
        this.maxHealth = maxHealth;
        this.health = this.maxHealth;
        this.speed = speed;
        this.damage = damage;

        System.out.println();
        Game.log(Colors.YELLOW + "Enemy: " + this.getClass().getSimpleName() + " loaded.");
        Game.log(Colors.YELLOW + "WorldX: " + this.x + " - WorldY: " + this.y + " - MaxHealth: " + this.maxHealth + " - Speed: " + this.speed + " - Damage: " + this.damage);
        System.out.println();
    }

    public void update(final Game game){
        final Player player = game.getPlayMenu().getPlayer();

        if(this.health <= 0){
            game.getPlayMenu().getEnemyManager().getEnemies().remove(this);
            player.gainExperience(this.expGain);
        }

        float
                playerX = player.getScreenX(),
                playerY = player.getScreenY(),

                deltaX = playerX - this.x,
                deltaY = playerY - this.y,

                distance = (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY),

                directionX = deltaX / distance, directionY = deltaY / distance;

        this.x += (float) (directionX * speed);
        this.y += (float) (directionY * speed);
    }

    public abstract void render(final Graphics graphics);

    public void showHealth(final Graphics graphics){
        graphics.setColor(Color.RED);
        graphics.fillRect((int) this.x + 7, (int) this.y - 10, (int) this.maxHealth, 5);
        graphics.setColor(Color.GREEN);
        graphics.fillRect((int) this.x + 7, (int) this.y - 10, (int) this.health, 5);
    }

    public void receiveDamage(final Player player){
        this.health = this.health - player.getDamage();
        Game.log(Colors.RED + "Enemy: " + this.getClass().getSimpleName() + " | Health: " + this.health);
    }
}
