package fr.kizafox.aloneguy.game.entity.item;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public abstract class Item {

    protected float x, y;
    protected int width, height;
    protected Color color;
    protected Rectangle2D.Float hitBox;

    protected boolean isAvailable;

    public Item(float x, float y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;

        this.isAvailable = true;
    }

    public abstract void update();

    public abstract void render(final Graphics graphics);

    public abstract void applyEffect();

    protected void initHitBox(final float x, final float y, final int width, final int height){
        this.hitBox = new Rectangle2D.Float(x, y, width, height);
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
        if (hitBox != null) {
            hitBox.x = x;
        }
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
        if (hitBox != null) {
            hitBox.y = y;
        }
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

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Rectangle2D.Float getHitBox() {
        return hitBox;
    }

    public void setHitBox(Rectangle2D.Float hitBox) {
        this.hitBox = hitBox;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
