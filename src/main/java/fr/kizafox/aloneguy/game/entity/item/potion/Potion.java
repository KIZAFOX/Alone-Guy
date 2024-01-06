package fr.kizafox.aloneguy.game.entity.item.potion;

import fr.kizafox.aloneguy.game.client.window.Game;
import fr.kizafox.aloneguy.game.entity.Entity;
import fr.kizafox.aloneguy.game.entity.item.Item;
import fr.kizafox.aloneguy.game.entity.player.Player;

import java.awt.*;
import java.awt.image.BufferedImage;

import static fr.kizafox.aloneguy.game.utils.GameSettings.*;

public abstract class Potion extends Item {

    protected final Game game;

    protected final Entity entity;

    public Potion(final Game game, final Entity entity, BufferedImage image) {
        super(TILES_SIZE * 23, TILES_SIZE * 25, 32, 32, image);

        this.game = game;
        this.entity = entity;

        this.initHitBox(8, 16, 32, 32);
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics graphics) {
        if (this.isAvailable()) {
            final Player player = this.game.getPlayMenu().getPlayer();
            final int screenX = (int) (this.getX() - player.getWorldX() + player.screenX);
            final int screenY = (int) (this.getY() - player.getWorldY() + player.screenY);

            if (this.getX() + TILES_SIZE > player.getWorldX() - player.screenX &&
                    this.getX() - TILES_SIZE < player.getWorldX() + player.screenX &&
                    this.getY() + TILES_SIZE > player.getWorldY() - player.screenY &&
                    this.getY() - TILES_SIZE < player.getWorldY() + player.screenY) {

                this.renderHitBox(graphics, screenX, screenY);
                graphics.drawImage(this.getImage(), screenX, screenY, TILES_SIZE, TILES_SIZE, null);
            }
        }
    }

    public Game getGame() {
        return game;
    }

    public Entity getEntity() {
        return entity;
    }
}
