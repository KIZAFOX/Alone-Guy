package fr.kizafox.aloneguy.game.entity.enemy.zombie;

import fr.kizafox.aloneguy.game.entity.enemy.EnemyHandler;

import java.awt.*;

import static fr.kizafox.aloneguy.game.utils.GameSettings.*;

/**
 * Done by @KIZAFOX on {13/01/2024} at 12:40.
 **/
public class Zombie extends EnemyHandler {
    public Zombie() {
        super((14 * 2) * TILES_SIZE, 12 * TILES_SIZE, 50, 20.0D, 0.25D, 1.0D);
    }


    @Override
    public void render(Graphics graphics) {
        this.showHealth(graphics);

        graphics.setColor(Color.MAGENTA);
        graphics.fillOval((int) this.x, (int) this.y, 32, 32);
    }
}
