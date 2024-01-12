package fr.kizafox.aloneguy.game.entity.player;

import fr.kizafox.aloneguy.game.client.status.GameState;
import fr.kizafox.aloneguy.game.client.window.Game;
import fr.kizafox.aloneguy.game.entity.Entity;
import fr.kizafox.aloneguy.game.entity.enemy.EnemyHandler;
import fr.kizafox.aloneguy.game.utils.ImageRenderer;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static fr.kizafox.aloneguy.game.utils.GameSettings.*;

public class Player extends Entity {

    protected final Game game;

    private final BufferedImage PLAYER_IMAGE = ImageRenderer.load(ImageRenderer.PLAYER_SHEET);

    private boolean showMap = false, showMinimap = true, hasMiniMap = true;

    public final int screenX, screenY;

    public Player(final Game game) {
        super(EntityType.PLAYER, 14 * TILES_SIZE, 8 * TILES_SIZE, (int) (64 * SCALE), (int) (40 * SCALE), 30.0D, 2);
        this.game = game;

        this.screenX = ((GAME_WIDTH / 2) - (TILES_SIZE / 2));
        this.screenY = ((GAME_HEIGHT / 2) - (TILES_SIZE / 2));

        this.loadAnimations();

        this.initHitBox(40, 15, 45, 60);
    }

    @Override
    public void update() {
        if(this.isDead()) GameState.setStatus(GameState.LOSE);

        this.updatePosition();

        this.checkLevelUp();
    }

    @Override
    public void render(final Graphics graphics) {
        this.renderStats(graphics);

        this.updateAnimationTick();
        this.setAnimation();

        if(wasFacingLeft) {
            graphics.drawImage(ImageRenderer.flipImage(this.animations[this.playerState][this.animationIndex]), screenX, screenY, 128, 80, null);
            this.initAttackBox(screenX - 15, screenY + 15, 45, 60);
        }else {
            graphics.drawImage(this.animations[this.playerState][this.animationIndex], screenX, screenY, 128, 80, null);
            this.initAttackBox(screenX + 2 * TILES_SIZE, screenY + 15, 45, 60);
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

    private void loadAnimations() {
        this.animations = new BufferedImage[11][7];
        for (int j = 0; j < this.animations.length; j++) {
            for (int i = 0; i < this.animations[j].length; i++) {
                this.animations[j][i] = PLAYER_IMAGE.getSubimage(i * 50, j * 37, 50, 37);
            }
        }
    }

    private void updateAnimationTick() {
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

    private void updatePosition() {
        collision = false;

        this.game.getCollisionChecker().checkTile(this);

        //this.game.getPlayMenu().getObjectManager().pickUp(this.game.getCollisionChecker().checkObject(this, true), this);
        //this.game.getPlayMenu().getEnemyManager().attackPlayer(this.game.getCollisionChecker().checkEnemy(this, true), this);

        if (!collision) {
            float speedX = 0, speedY = 0;

            if (this.isUp()) speedY -= this.getSpeed();
            if (this.isDown()) speedY += this.getSpeed();
            if (this.isLeft()) {
                speedX -= this.getSpeed();
                wasFacingLeft = true;
            }
            if (this.isRight()) {
                speedX += this.getSpeed();
                wasFacingLeft = false;
            }

            if (speedX != 0 && speedY != 0) {
                final float length = (float) Math.sqrt(speedX * speedX + speedY * speedY);
                speedX = (speedX / length) * this.getSpeed();
                speedY = (speedY / length) * this.getSpeed();
            }

            this.setWorldX(this.getWorldX() + speedX);
            this.setWorldY(this.getWorldY() + speedY);
        } else {
            wasFacingLeft = this.isLeft();
        }
    }

    private void renderStats(final Graphics graphics) {
        graphics.setColor(new Color(0, 0, 0, 50));
        graphics.fillRect(5, 5, 250, 50);

        int maxHealthWidth = 225;
        int currentHealthWidth = (int) (this.getHealth() / this.getMaxHealth() * maxHealthWidth);

        currentHealthWidth = Math.min(currentHealthWidth, maxHealthWidth);

        graphics.setColor(Color.RED);
        graphics.fillRect(15, 15, currentHealthWidth, 10);

        int maxExpWidth = 225;
        int currentExpWidth = (int) ((double) this.getCurrentExp() / this.getExpToNextLevel() * maxExpWidth);
        currentExpWidth = Math.min(currentExpWidth, maxExpWidth);

        graphics.setColor(Color.YELLOW);
        graphics.fillRect(15, 35, currentExpWidth, 10);
    }

    public void keyPressed(KeyEvent e) {
        if (GameState.getCurrentState().equals(GameState.PLAY)) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_Z -> setUp(true);
                case KeyEvent.VK_S -> setDown(true);
                case KeyEvent.VK_Q -> setLeft(true);
                case KeyEvent.VK_D -> setRight(true);
                case KeyEvent.VK_M -> {
                    if(showMap){
                        showMap = false;

                        if(hasMiniMap) showMinimap = true;
                    }else{
                        showMap = true;
                        showMinimap = false;
                    }
                }
                case KeyEvent.VK_UP -> {
                    if(showMap) return;

                    if(showMinimap){
                        showMinimap = false;
                        hasMiniMap = false;
                    }else{
                        showMinimap = true;
                        hasMiniMap = true;
                    }
                }
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        if (GameState.getCurrentState().equals(GameState.PLAY)) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_Z -> setUp(false);
                case KeyEvent.VK_S -> setDown(false);
                case KeyEvent.VK_Q -> setLeft(false);
                case KeyEvent.VK_D -> setRight(false);
            }
        }
    }

    public void mousePressed(MouseEvent e) {
        if (GameState.getCurrentState().equals(GameState.PLAY)) {
            if(e.getButton() == MouseEvent.BUTTON1) {
                if(isAttacking()) return;

                setAttacking(true);

                for(EnemyHandler enemiesInRange : this.game.getPlayMenu().getEnemyManager().getEnemiesInRange(this.game.getPlayMenu().getPlayer())){
                    enemiesInRange.applyDamage(this.getDamage());
                }
            }
        }
    }
}
