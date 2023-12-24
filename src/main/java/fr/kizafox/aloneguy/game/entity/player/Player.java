package fr.kizafox.aloneguy.game.entity.player;

import fr.kizafox.aloneguy.game.client.status.GameState;
import fr.kizafox.aloneguy.game.client.window.sub.PlayMenu;
import fr.kizafox.aloneguy.game.entity.Entity;
import fr.kizafox.aloneguy.game.entity.enemy.Enemy;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;

import static fr.kizafox.aloneguy.game.entity.enemy.Enemy.BOUNCE_FORCE;

public class Player extends Entity {

    public static int experience;

    public Player(float x, float y, int width, int height, double maxHealth, Color color) {
        super(x, y, width, height, maxHealth, color);
        this.initHitBox(x, y, width, height);
    }

    @Override
    public void update() {
        if(this.getHealth() <= 0) GameState.setStatus(GameState.LOSE);
    }

    @Override
    public void render(final Graphics graphics) {
        graphics.setColor(color);
        graphics.fillOval((int) x, (int) y, width, height);

        graphics.setColor(new Color(0, 0, 0, 50));
        graphics.fillRect(5, 5, 250, 50);

        int
                maxHealthWidth = 225,
                currentHealthWidth = (int) (this.getHealth() / this.getMaxHealth() * maxHealthWidth);

        currentHealthWidth = Math.min(currentHealthWidth, maxHealthWidth);

        graphics.setColor(Color.RED);
        graphics.fillRect(15, 15, currentHealthWidth, 10);

        graphics.setColor(Color.YELLOW);
        graphics.fillRect(15, 35, maxHealthWidth, 10);
    }

    public void keyPressed(KeyEvent e) {
        if (GameState.getCurrentState().equals(GameState.PLAY)) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_Z:
                    up = true;
                    break;
                case KeyEvent.VK_S:
                    down = true;
                    break;
                case KeyEvent.VK_Q:
                    left = true;
                    break;
                case KeyEvent.VK_D:
                    right = true;
                    break;
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_Z:
                up = false;
                break;
            case KeyEvent.VK_S:
                down = false;
                break;
            case KeyEvent.VK_Q:
                left = false;
                break;
            case KeyEvent.VK_D:
                right = false;
                break;
        }
    }
}