package fr.kizafox.aloneguy.game.entity.item;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Item {

    protected float x, y;
    protected int width, height;
    protected BufferedImage image;
    protected Rectangle hitBox;

    protected boolean isAvailable;

    public Item(float x, float y, int width, int height, BufferedImage image) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.image = image;

        this.isAvailable = true;
    }

    public abstract void update();

    public abstract void render(final Graphics graphics);

    public abstract void applyEffect();

    protected void initHitBox(final float x, final float y, final int width, final int height){
        this.hitBox = new Rectangle((int) x, (int) y, width, height);
    }

    protected void renderHitBox(Graphics graphics, int screenX, int screenY){
        graphics.setColor(Color.MAGENTA);
        graphics.drawRect(screenX + this.hitBox.x, screenY + this.hitBox.y, this.hitBox.width, this.hitBox.height);
    }

    public void setLocation(final float x, final float y){
        this.setX(x);
        this.setY(y);
    }

    public void reset() {
        this.setX(this.x);
        this.setY(this.y);
        this.isAvailable = true;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
        if (hitBox != null) {
            hitBox.width = width;
        }
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
        if (hitBox != null) {
            hitBox.height = height;
        }
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

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
