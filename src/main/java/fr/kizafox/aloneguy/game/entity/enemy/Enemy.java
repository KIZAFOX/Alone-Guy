package fr.kizafox.aloneguy.game.entity.enemy;

import fr.kizafox.aloneguy.game.client.window.Game;
import fr.kizafox.aloneguy.game.entity.Entity;
import fr.kizafox.aloneguy.game.entity.player.Player;

import java.awt.*;

public class Enemy extends Entity {

    protected final Game game;
    protected final Player player;

    public boolean alive;

    public static final float BOUNCE_FORCE = 100.0F / 2;

    public Enemy(final Game game, final Player player, float x, float y, int width, int height, double maxHealth, Color color) {
        super(x, y, width, height, maxHealth, color);
        this.initHitBox(x, y, width, height);

        this.game = game;
        this.player = player;

        this.alive = true;
    }

    @Override
    public void update() {
        if (this.alive) {
            float dx = player.getX() - x;
            float dy = player.getY() - y;
            float distance = (float) Math.sqrt(dx * dx + dy * dy);

            if (distance != 0) {
                dx /= distance;
                dy /= distance;

                float reducedSpeed = 0.5f;
                x += dx * (speed * reducedSpeed);
                y += dy * (speed * reducedSpeed);
            }

            if (this.getHealth() <= 0) {
                if (this.alive) {
                    Player.experience += 1;
                    this.alive = false;
                    this.game.getPlayMenu().getEnemyManager().getEnemies().remove(this);
                }
            }
        }
    }

    @Override
    public void render(Graphics graphics) {
        if (this.alive) {
            graphics.setColor(color);
            graphics.fillOval((int) x, (int) y, width, height);

            graphics.setColor(Color.RED);
            graphics.fillRect((int) x, (int) y - 10, (int) (width * (health / maxHealth)), 5);
        }
    }

    public Game getGame() {
        return game;
    }

    public Player getPlayer() {
        return player;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}