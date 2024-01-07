package fr.kizafox.aloneguy.game.entity.object;

import fr.kizafox.aloneguy.game.client.window.Game;
import fr.kizafox.aloneguy.game.entity.Entity;
import fr.kizafox.aloneguy.game.entity.player.Player;
import fr.kizafox.aloneguy.game.utils.Colors;

import java.awt.*;
import java.awt.image.BufferedImage;

import static fr.kizafox.aloneguy.game.utils.GameSettings.*;

public abstract class ObjectHandler {

    protected final Game game;

    protected String name;
    protected boolean collision;
    protected int worldX, worldY;
    protected BufferedImage image;
    protected Rectangle hitBox;
    protected int solidAreaDefaultX = 0, solidAreaDefaultY = 0;

    public ObjectHandler(final Game game, String name, boolean collision, int worldX, int worldY, BufferedImage image) {
        this.game = game;
        this.name = name;
        this.collision = collision;
        this.worldX = worldX;
        this.worldY = worldY;
        this.image = image;

        Game.log(Colors.YELLOW + "Object: " + name + " loaded.");
        Game.log(Colors.YELLOW + "Collision: " + this.collision + " - WorldX: " + this.worldX + " - WorldY: " + this.worldY + " - Image: " + this.image);
    }

    public abstract void applyEffect(Entity player);

    public void render(Graphics graphics){
        final Player player = this.game.getPlayMenu().getPlayer();

        int
                screenX = (int) (worldX - player.getWorldX() + player.screenX),
                screenY = (int) (worldY - player.getWorldY() + player.screenY);

        if(worldX + TILES_SIZE > player.getWorldX() - player.screenX &&
                worldX - TILES_SIZE < player.getWorldX() + player.screenX &&
                worldY + TILES_SIZE > player.getWorldY() - player.screenY &&
                worldY - TILES_SIZE < player.getWorldY() + player.screenY){
            graphics.drawImage(image, screenX, screenY, TILES_SIZE, TILES_SIZE, null);
        }

        graphics.setColor(Color.RED);
        graphics.drawRect(screenX + this.hitBox.x, screenY + this.hitBox.y, this.hitBox.width, this.hitBox.height);
    }

    protected void initHitBox(final float x, final float y, final int width, final int height){
        this.hitBox = new Rectangle((int) x, (int) y, width, height);
        this.solidAreaDefaultX = this.hitBox.x;
        this.solidAreaDefaultY = this.hitBox.y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCollision() {
        return collision;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }

    public int getWorldX() {
        return worldX;
    }

    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }

    public int getWorldY() {
        return worldY;
    }

    public void setWorldY(int worldY) {
        this.worldY = worldY;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public Rectangle getHitBox() {
        return hitBox;
    }

    public void setHitBox(Rectangle hitBox) {
        this.hitBox = hitBox;
    }

    public int getSolidAreaDefaultX() {
        return solidAreaDefaultX;
    }

    public void setSolidAreaDefaultX(int solidAreaDefaultX) {
        this.solidAreaDefaultX = solidAreaDefaultX;
    }

    public int getSolidAreaDefaultY() {
        return solidAreaDefaultY;
    }

    public void setSolidAreaDefaultY(int solidAreaDefaultY) {
        this.solidAreaDefaultY = solidAreaDefaultY;
    }
}
