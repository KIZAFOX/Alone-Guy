package fr.kizafox.aloneguy.game.entity.object.item.potion;

import fr.kizafox.aloneguy.game.client.window.Game;
import fr.kizafox.aloneguy.game.entity.object.ObjectHandler;

import java.awt.image.BufferedImage;

import static fr.kizafox.aloneguy.game.utils.GameSettings.*;

public abstract class Potion extends ObjectHandler {

    public Potion(Game game, String name, int worldX, int worldY, BufferedImage image) {
        super(game, name, false, worldX * TILES_SIZE, worldY * TILES_SIZE, image);

        this.initHitBox(8, 16, TILES_DEFAULT_SIZE, TILES_DEFAULT_SIZE);
    }
}
