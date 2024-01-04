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

    private final BufferedImage playerUp1 = ImageRenderer.load(ImageRenderer.PLAYER_UP_1);
    private final BufferedImage playerUp2 = ImageRenderer.load(ImageRenderer.PLAYER_UP_2);

    private final BufferedImage playerDown1 = ImageRenderer.load(ImageRenderer.PLAYER_DOWN_1);
    private final BufferedImage playerDown2 = ImageRenderer.load(ImageRenderer.PLAYER_DOWN_2);

    private final BufferedImage playerLeft1 = ImageRenderer.load(ImageRenderer.PLAYER_LEFT_1);
    private final BufferedImage playerLeft2 = ImageRenderer.load(ImageRenderer.PLAYER_LEFT_2);

    private final BufferedImage playerRight1 = ImageRenderer.load(ImageRenderer.PLAYER_RIGHT_1);
    private final BufferedImage playerRight2 = ImageRenderer.load(ImageRenderer.PLAYER_RIGHT_2);

    public final int screenX, screenY;

    public Player(final Game game) {
        super(TILES_SIZE * 23, TILES_SIZE * 21, (int) (64 * SCALE), (int) (40 * SCALE), 30.0D);
        this.game = game;

        this.screenX = ((GAME_WIDTH / 2) - (TILES_SIZE / 2));
        this.screenY = ((GAME_HEIGHT / 2) - (TILES_SIZE / 2));
    }

    @Override
    public void update() {
        if (this.getHealth() <= 0) GameState.setStatus(GameState.LOSE);

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

    @Override
    public void render(final Graphics graphics) {
        this.renderStats(graphics);

        graphics.drawImage(playerDown1, screenX, screenY, TILES_SIZE, TILES_SIZE, null);
    }

    private void renderStats(final Graphics graphics){
        graphics.setColor(new Color(0, 0, 0, 50));
        graphics.fillRect(5, 5, 250, 50);

        int maxHealthWidth = 225;
        int currentHealthWidth = (int) (this.getHealth() / this.getMaxHealth() * maxHealthWidth);

        currentHealthWidth = Math.min(currentHealthWidth, maxHealthWidth);

        graphics.setColor(Color.RED);
        graphics.fillRect(15, 15, currentHealthWidth, 10);

        graphics.setColor(Color.YELLOW);
        graphics.fillRect(15, 35, maxHealthWidth, 10);
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
