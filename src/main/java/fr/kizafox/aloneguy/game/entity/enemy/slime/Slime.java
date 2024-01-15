package fr.kizafox.aloneguy.game.entity.enemy.slime;

import fr.kizafox.aloneguy.game.client.window.Game;
import fr.kizafox.aloneguy.game.entity.enemy.EnemyHandler;
import fr.kizafox.aloneguy.game.utils.image.ImageRenderer;

import java.awt.*;

import static fr.kizafox.aloneguy.game.utils.GameSettings.*;

/**
 * Done by @KIZAFOX on {13/01/2024} at 12:40.
 **/
public class Slime extends EnemyHandler {
    public Slime(final Game game) {
        super(game, 17 * TILES_SIZE, 12 * TILES_SIZE, 50, 20.0D, 0.25D, .25D);

        this.hitBox.x = 3;
        this.hitBox.y = 18;
        this.hitBox.width = 42;
        this.hitBox.height = 30;
        this.solidAreaDefaultX = this.hitBox.x;
        this.solidAreaDefaultY = this.hitBox.y;

        this.image = ImageRenderer.load(ImageRenderer.SLIME_DOWN_1);
    }

    @Override
    public void render(Graphics graphics) {
        this.showHealth(graphics);
        graphics.drawImage(this.image, (int) this.getAbsoluteX(), (int) this.getAbsoluteY(), 75, 75, null);
    }
}
