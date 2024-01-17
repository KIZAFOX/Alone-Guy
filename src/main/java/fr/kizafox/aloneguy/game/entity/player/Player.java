package fr.kizafox.aloneguy.game.entity.player;

import fr.kizafox.aloneguy.game.client.status.GameState;
import fr.kizafox.aloneguy.game.client.window.Game;
import fr.kizafox.aloneguy.game.entity.Entity;
import fr.kizafox.aloneguy.game.entity.enemy.EnemyHandler;
import fr.kizafox.aloneguy.game.utils.Constants;
import fr.kizafox.aloneguy.game.utils.image.ImageRenderer;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static fr.kizafox.aloneguy.game.utils.GameSettings.*;

public class Player extends Entity {

    protected final Game game;

    public Player(final Game game) {
        super(14 * TILES_SIZE, 8 * TILES_SIZE, (int) (64 * SCALE), (int) (40 * SCALE), 30.0D, 2);
        this.game = game;

        this.screenX = ((GAME_WIDTH / 2) - (TILES_SIZE / 2));
        this.screenY = ((GAME_HEIGHT / 2) - (TILES_SIZE / 2));

        this.loadAnimations();

        this.initHitBox();
    }

    @Override
    public void update() {
        if(this.isDead()) GameState.setStatus(GameState.LOSE);

        this.updatePosition();
    }

    @Override
    public void render(final Graphics graphics) {
        this.updateStats(graphics);

        if(GameState.getCurrentState().equals(GameState.PLAY)){
            this.updateAnimationTick();
            this.setAnimation();
        }

        if(wasFacingLeft) {
            graphics.drawImage(ImageRenderer.flipImage(this.animations[this.playerState][this.animationIndex]), screenX, screenY, 128, 80, null);
            this.initAttackBox(screenX - 15, screenY + 15, 45, 60);
        }else {
            graphics.drawImage(this.animations[this.playerState][this.animationIndex], screenX, screenY, 128, 80, null);
            this.initAttackBox(screenX + 2 * TILES_SIZE, screenY + 15, 45, 60);
        }

        if (levelUpMessageDisplayed && System.currentTimeMillis() - levelUpDisplayTime < levelUpDisplayDuration) {
            graphics.setColor(Color.YELLOW);
            renderText(graphics, levelUpMessage, 15, Color.YELLOW, screenX + 25, screenY - 10);
        } else {
            levelUpMessageDisplayed = false;
        }

        if(showMap) this.game.getPlayMenu().getMap().render(graphics);
        if(showMinimap) this.game.getPlayMenu().getMap().renderMinimap(graphics);
    }

    public void resetBooleans() {
        this.setUp(false);
        this.setDown(false);
        this.setLeft(false);
        this.setRight(false);
    }


    public void reset() {
        this.setWorldX(this.worldX);
        this.setWorldY(this.worldY);

        this.setScreenX(this.screenX);
        this.setScreenY(this.screenY);

        this.setHealth(this.maxHealth);

        this.setCurrentExp(0);
        this.setLevel(0);
        this.setExpToNextLevel(100);

        this.resetBooleans();
    }

    private void loadAnimations() {
        this.animations = new BufferedImage[11][7];
        for (int j = 0; j < this.animations.length; j++) {
            for (int i = 0; i < this.animations[j].length; i++) {
                this.animations[j][i] =  ImageRenderer.load(ImageRenderer.PLAYER_SHEET).getSubimage(i * 50, j * 37, 50, 37);
            }
        }
    }

    private void updateStats(final Graphics graphics) {
        final int
                statusBarX = (int) (10 * SCALE),
                statusBarY = (int) (10 * SCALE),
                statusBarWidth = (int) (192 * SCALE),
                statusBarHeight = (int) (58 * SCALE);

        graphics.drawImage(ImageRenderer.load(ImageRenderer.PLAYER_HUD), statusBarX, statusBarY, statusBarWidth, statusBarHeight, null);

        final int
                healthBarX = (int) (34 * SCALE),
                healthBarY = (int) (14 * SCALE),
                healthBarWidth = (int) (150 * SCALE),
                healthBarHeight = (int) (4 * SCALE),
                healthWidth = (int) ((this.health / this.maxHealth) * healthBarWidth);

        graphics.setColor(Color.RED);
        graphics.fillRect(healthBarX + statusBarX, healthBarY + statusBarY, healthWidth, healthBarHeight);

        final int
                experienceBarX = (int) (44 * SCALE),
                experienceBarY = (int) (34 * SCALE),
                experienceBarWidth = (int) (expToNextLevel * SCALE / this.level),
                experienceBarHeight = (int) (2 * SCALE),
                experienceWidth = (int) ((this.currentExp / (double) this.expToNextLevel) * experienceBarWidth);

        graphics.setColor(Color.YELLOW);
        graphics.fillRect(experienceBarX + statusBarX, experienceBarY + statusBarY, experienceWidth, experienceBarHeight);
    }

    private void updateAnimationTick() {
        this.animationTick++;

        if (this.animationTick >= this.animationSpeed) {
            this.animationTick = 0;
            this.animationIndex++;

            if (this.animationIndex >= Constants.PlayerState.getSpriteAmount(this.playerState)) {
                this.animationIndex = 0;
                this.setAttacking(false);
            }
        }
    }

    private void setAnimation() {
        int startAnimation = playerState;

        if (this.isMoving()) {
            this.animationSpeed = 10;
            this.playerState = Constants.PlayerState.RUNNING;
        } else {
            this.animationSpeed = 10;
            this.playerState = Constants.PlayerState.IDLE;
        }

        if (isAttacking()) {
            this.animationSpeed = 7;
            this.playerState = Constants.PlayerState.ATTACKING;
        }

        if (startAnimation != playerState) {
            this.animationTick = 0;
            this.animationIndex = 0;
        }
    }

    private void updatePosition() {
        collision = false;

        this.game.getCollisionChecker().checkTile(this);

        this.game.getPlayMenu().getEnemyManager().getEnemies().forEach(enemies -> {
            if(enemies != null) {
                this.game.getCollisionChecker().checkEntity(enemies, this);
            }
        });

        if (!collision) {
            float speedX = 0, speedY = 0;

            if (this.isUp()) speedY -= this.getSpeed();
            if (this.isDown()) speedY += this.getSpeed();
            if (this.isLeft()) {
                speedX -= this.getSpeed();
                this.setWasFacingLeft(true);
            }
            if (this.isRight()) {
                speedX += this.getSpeed();
                this.setWasFacingLeft(false);
            }

            if (speedX != 0 && speedY != 0) {
                final float length = (float) Math.sqrt(speedX * speedX + speedY * speedY);
                speedX = (speedX / length) * this.getSpeed();
                speedY = (speedY / length) * this.getSpeed();
            }

            this.setWorldX(this.getWorldX() + speedX);
            this.setWorldY(this.getWorldY() + speedY);
        } else {
            this.setWasFacingLeft(this.isLeft());
        }
    }

    public void keyPressed(KeyEvent event) {
        if(GameState.getCurrentState().equals(GameState.PLAY)) {
            switch (event.getKeyCode()) {
                case KeyEvent.VK_Z -> this.setUp(true);
                case KeyEvent.VK_S -> this.setDown(true);
                case KeyEvent.VK_Q -> this.setLeft(true);
                case KeyEvent.VK_D -> this.setRight(true);
                case KeyEvent.VK_M -> {
                    if(this.showMap){
                        this.showMap = false;

                        if(this.hasMiniMap) this.showMinimap = true;
                    }else{
                        this.showMap = true;
                        this.showMinimap = false;
                    }
                }
                case KeyEvent.VK_UP -> {
                    if(this.showMap) return;

                    if(this.showMinimap){
                        this.showMinimap = false;
                        this.hasMiniMap = false;
                    }else{
                        this.showMinimap = true;
                        this.hasMiniMap = true;
                    }

                    this.gainExperience(1);
                }
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        if(GameState.getCurrentState().equals(GameState.PLAY)) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_Z -> this.setUp(false);
                case KeyEvent.VK_S -> this.setDown(false);
                case KeyEvent.VK_Q -> this.setLeft(false);
                case KeyEvent.VK_D -> this.setRight(false);
            }
        }
    }

    public void mousePressed(MouseEvent event) {
        if(GameState.getCurrentState().equals(GameState.PLAY) && event.getButton() == MouseEvent.BUTTON1 && !this.isAttacking()) {
            this.setAttacking(true);

            this.game.getPlayMenu().getEnemyManager().getEnemiesInRange(this).forEach(enemies -> enemies.receiveDamage(this));
        }
    }
}
