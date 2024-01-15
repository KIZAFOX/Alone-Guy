package fr.kizafox.aloneguy.game.entity.enemy;

import fr.kizafox.aloneguy.game.client.window.Game;
import fr.kizafox.aloneguy.game.entity.player.Player;
import fr.kizafox.aloneguy.game.utils.Colors;
import fr.kizafox.aloneguy.game.utils.Constants;
import fr.kizafox.aloneguy.game.utils.image.ImageRenderer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Done by @KIZAFOX on {12/01/2024} at 22:07.
 **/
public abstract class EnemyHandler {

    protected final Game game;

    public float x, y;
    public int expGain;
    public double maxHealth, health, speed, damage;

    public BufferedImage image;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public Rectangle hitBox;

    public boolean collision = false;

    public EnemyHandler(final Game game, final float x, final float y, final int expGain, final double maxHealth, final double speed, final double damage) {
        this.game = game;
        this.x = x;
        this.y = y;
        this.expGain = expGain;
        this.maxHealth = maxHealth;
        this.health = this.maxHealth;
        this.speed = speed;
        this.damage = damage;

        this.hitBox = new Rectangle();

        Game.log(Colors.YELLOW + "Enemy: " + this.getClass().getSimpleName() + " loaded.");
        Game.log(Colors.YELLOW + "WorldX: " + this.x + " - WorldY: " + this.y + " - MaxHealth: " + this.maxHealth + " - Speed: " + this.speed + " - Damage: " + this.damage);
        System.out.println();
    }

    public void update(final Game game) {
        collision = false;

        if (this.health <= 0) {
            game.getPlayMenu().getEnemyManager().getEnemies().remove(this);
            game.getPlayMenu().getPlayer().gainExperience(this.expGain);
        }

        if(!collision){
            final float
                    playerX = game.getPlayMenu().getPlayer().getScreenX(),
                    playerY = game.getPlayMenu().getPlayer().getScreenY();

            float
                    deltaX = playerX - this.getAbsoluteX(),
                    deltaY = playerY - this.getAbsoluteY();

            final float length = (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY);
            deltaX /= length;
            deltaY /= length;

            this.setAbsoluteX(this.getAbsoluteX() + (float) (deltaX * this.speed));
            this.setAbsoluteY(this.getAbsoluteY() + (float) (deltaY * this.speed));
        }
    }

    public abstract void render(final Graphics graphics);

    public void showHealth(final Graphics graphics){
        graphics.setColor(Color.RED);
        graphics.fillRect((int) this.getAbsoluteX() + 25, (int) this.getAbsoluteY() + 15, (int) this.maxHealth, 5);
        graphics.setColor(Color.GREEN);
        graphics.fillRect((int) this.getAbsoluteX() + 25, (int) this.getAbsoluteY() + 15, (int) this.health, 5);
    }

    public void receiveDamage(final Player player){
        this.health = this.health - player.getDamage();
        Game.log(Colors.RED + "Enemy: " + this.getClass().getSimpleName() + " | Health: " + this.health);
    }

    public float getAbsoluteX(){
        final Player player = this.game.getPlayMenu().getPlayer();
        return this.x - player.getWorldX() + player.getScreenX();
    }

    public void setAbsoluteX(final float x){
        final Player player = this.game.getPlayMenu().getPlayer();
        this.x = x - player.getScreenX() + player.getWorldX();
    }

    public float getAbsoluteY() {
        final Player player = this.game.getPlayMenu().getPlayer();
        return this.y - player.getWorldY() + player.getScreenY();
    }

    public void setAbsoluteY(final float y) {
        final Player player = this.game.getPlayMenu().getPlayer();
        this.y = y - player.getScreenY() + player.getWorldY();
    }

}
