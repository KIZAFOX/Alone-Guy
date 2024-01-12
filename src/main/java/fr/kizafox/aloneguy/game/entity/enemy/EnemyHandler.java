package fr.kizafox.aloneguy.game.entity.enemy;

import fr.kizafox.aloneguy.game.client.window.Game;
import fr.kizafox.aloneguy.game.entity.Entity;
import fr.kizafox.aloneguy.game.entity.player.Player;
import fr.kizafox.aloneguy.game.utils.ImageRenderer;

import java.awt.*;
import java.awt.image.BufferedImage;

import static fr.kizafox.aloneguy.game.utils.GameSettings.*;

public abstract class EnemyHandler extends Entity {

    protected final Game game;

    protected EnemyType enemyType;
    protected BufferedImage image;
    protected int experienceGain;

    public final int screenX, screenY;

    public EnemyHandler(final Game game, EnemyType enemyType, BufferedImage image, int experienceGain, double health, int damage, float speed, int worldX, int worldY) {
        super(EntityType.ENEMY, worldX * TILES_SIZE, worldY * TILES_SIZE, (int) (64 * SCALE), (int) (40 * SCALE), health, damage);
        this.game = game;
        this.enemyType = enemyType;
        this.image = image;
        this.experienceGain = experienceGain;
        this.health = health;
        this.damage = damage;
        this.setSpeed(speed);

        this.screenX = (200 - (TILES_SIZE / 2));
        this.screenY = (500 - (TILES_SIZE / 2));

        this.initHitBox(40, 15, 45, 60);

        setLeft(true);
    }

    @Override
    public void render(Graphics graphics) {
        this.updateAnimationTick();
        this.setAnimation();

        if(wasFacingLeft) {
            graphics.drawImage(ImageRenderer.flipImage(this.animations[this.playerState][this.animationIndex]), screenX, screenY, 128, 80, null);
            this.initAttackBox(screenX - 15, screenY + 15, 45, 60);
        }else {
            graphics.drawImage(this.animations[this.playerState][this.animationIndex], screenX, screenY, 128, 80, null);
            this.initAttackBox(screenX + 2 * TILES_SIZE, screenY + 15, 45, 60);
        }

        this.renderHitBox(graphics, screenX, screenY);
    }

    public void loadAnimations() {
        this.animations = new BufferedImage[11][7];
        for (int j = 0; j < this.animations.length; j++) {
            for (int i = 0; i < this.animations[j].length; i++) {
                this.animations[j][i] = this.image.getSubimage(i * 50, j * 37, 50, 37);
            }
        }
    }

    public void updateAnimationTick() {
        this.animationTick++;

        if (this.animationTick >= this.animationSpeed) {
            this.animationTick = 0;
            this.animationIndex++;

            if (this.animationIndex >= PlayerState.getSpriteAmount(this.playerState)) {
                this.animationIndex = 0;
                setAttacking(false);
            }
        }
    }

    private void setAnimation() {
        int startAnimation = playerState;

        if (this.isMoving()) {
            this.playerState = PlayerState.RUNNING;
        } else {
            this.playerState = PlayerState.IDLE;
        }

        if (isAttacking()) {
            this.playerState = PlayerState.ATTACKING;
        }

        if (startAnimation != playerState) {
            this.animationTick = 0;
            this.animationIndex = 0;
        }
    }

    public void updatePosition() {
        if (this.up) {
            this.worldY -= this.speed;
        }
        if (this.down) {
            this.worldY += this.speed;
        }
        if (this.left) {
            this.worldX -= this.speed;
            this.wasFacingLeft = true;
        }
        if (this.right) {
            this.worldX += this.speed;
            this.wasFacingLeft = false;
        }
    }

    public abstract void enemyAttack(final Player player);

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

    public int getExperienceGain() {
        return experienceGain;
    }

    public void setExperienceGain(int experienceGain) {
        this.experienceGain = experienceGain;
    }

    public enum EnemyType {
        ZOMBIE
    }
}