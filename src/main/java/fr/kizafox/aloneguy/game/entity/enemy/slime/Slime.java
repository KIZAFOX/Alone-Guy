package fr.kizafox.aloneguy.game.entity.enemy.slime;

import fr.kizafox.aloneguy.game.client.window.Game;
import fr.kizafox.aloneguy.game.entity.enemy.EnemyHandler;
import fr.kizafox.aloneguy.game.utils.image.ImageRenderer;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Done by @KIZAFOX on {13/01/2024} at 12:40.
 **/
public class Slime extends EnemyHandler {

    public Slime(final Game game) {
        super(game, 75, 75, 0.25F, 1.0D, 0.10D, 20);

        this.hitBox = new Rectangle();
        this.hitBox.setRect(this.x, this.y, width, height + 10);
        this.solidAreaDefaultX = this.hitBox.x;
        this.solidAreaDefaultY = this.hitBox.y;

        this.loadAnimations(
                new BufferedImage[]{
                        ImageRenderer.load(ImageRenderer.SLIME_DOWN_1),
                        ImageRenderer.load(ImageRenderer.SLIME_DOWN_2),
                }
        );
    }

    @Override
    public void render(Graphics graphics) {
        this.renderHitBox(graphics);

        this.setAnimation(graphics);
    }
}
