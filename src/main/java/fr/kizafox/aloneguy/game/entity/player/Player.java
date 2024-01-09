package fr.kizafox.aloneguy.game.entity.player;

import fr.kizafox.aloneguy.game.client.status.GameState;
import fr.kizafox.aloneguy.game.client.window.Game;
import fr.kizafox.aloneguy.game.entity.Entity;
import fr.kizafox.aloneguy.game.utils.ImageRenderer;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import static fr.kizafox.aloneguy.game.utils.GameSettings.*;

public class Player extends Entity {

    protected final Game game;

    private final BufferedImage PLAYER_IMAGE = ImageRenderer.load(ImageRenderer.PLAYER_SHEET);

    public final int screenX, screenY;

    public Player(final Game game) {
        super(EntityType.PLAYER, 14 * TILES_SIZE, 8 * TILES_SIZE, (int) (64 * SCALE), (int) (40 * SCALE), 30.0D, 2);
        this.game = game;

        this.screenX = ((GAME_WIDTH / 2) - (TILES_SIZE / 2));
        this.screenY = ((GAME_HEIGHT / 2) - (TILES_SIZE / 2));

        this.loadAnimations();

        this.initHitBox(47, 32, 50, 32);
    }

    @Override
    public void update() {
        if (this.getHealth() <= 0) GameState.setStatus(GameState.LOSE);

        collision = false;
        this.game.getCollisionChecker().checkTile(this);

        this.game.getPlayMenu().getObjectManager().pickUp(this.game.getCollisionChecker().checkObject(this, true), this);
        this.game.getPlayMenu().getEnemyManager().attackPlayer(this.game.getCollisionChecker().checkEnemy(this, true), this);

        if(!collision){
            float speedX = 0, speedY = 0;

            if(this.isUp()) speedY -= this.getSpeed();
            if(this.isDown()) speedY += this.getSpeed();
            if(this.isLeft()) speedX -= this.getSpeed();
            if(this.isRight()) speedX += this.getSpeed();

            if (speedX != 0 && speedY != 0) {
                final float length = (float) Math.sqrt(speedX * speedX + speedY * speedY);
                speedX = (speedX / length) * this.getSpeed();
                speedY = (speedY / length) * this.getSpeed();
            }

            this.setWorldX(this.getWorldX() + speedX);
            this.setWorldY(this.getWorldY() + speedY);
        }

        this.checkLevelUp();
    }

    @Override
    public void render(final Graphics graphics) {
        this.renderStats(graphics);

        this.updateAnimationTick();
        this.setAnimation();

        if(isLeft()){
            graphics.drawImage(ImageRenderer.flipImage(this.animations[this.playerState][this.animationIndex]), screenX, screenY, 128, 80, null);
        }else{
            graphics.drawImage(this.animations[this.playerState][this.animationIndex], screenX, screenY, 128, 80, null);
        }

        this.renderHitBox(graphics, screenX, screenY);
    }

    public void resetBooleans(){
        this.setUp(false);
        this.setDown(false);
        this.setLeft(false);
        this.setRight(false);
    }

    private void loadAnimations(){
        this.animations = new BufferedImage[11][7];
        for(int j = 0; j < this.animations.length; j++){
            for(int i = 0; i < this.animations[j].length; i++){
                this.animations[j][i] = PLAYER_IMAGE.getSubimage(i * 50, j * 37, 50, 37);
            }
        }
    }

    private void updateAnimationTick(){
        this.animationTick++;

        if(this.animationTick >= this.animationSpeed){
            this.animationTick = 0;
            this.animationIndex++;

            if(this.animationIndex >= PlayerState.getSpriteAmount(this.playerState)){
                this.animationIndex = 0;
            }
        }
    }

    private void setAnimation(){
        if(this.isMoving()){
            this.playerState = PlayerState.RUNNING;
        }else{
            this.playerState = PlayerState.IDLE;
        }
    }

    private void renderStats(final Graphics graphics){
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
            }
        }
    }

    public void keyReleased(KeyEvent e){
        if (GameState.getCurrentState().equals(GameState.PLAY)) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_Z -> setUp(false);
                case KeyEvent.VK_S -> setDown(false);
                case KeyEvent.VK_Q -> setLeft(false);
                case KeyEvent.VK_D -> setRight(false);
            }
        }
    }
}
