package fr.kizafox.aloneguy.game.entity;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public abstract class Entity {

    protected float x, y;
    protected int width, height;
    protected double maxHealth, health;
    protected Color color;
    protected Rectangle2D.Float hitBox;

    public boolean up, down, left, right;
    public final float speed = 2.0F;

    public Entity(float x, float y, int width, int height, double maxHealth, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.maxHealth = maxHealth;
        this.health = this.maxHealth;
        this.color = color;
    }

    public abstract void update();

    public abstract void render(final Graphics graphics);

    public void teleport(final float x, final float y){
        this.setX(x);
        this.setY(y);
        System.out.println(this.getClass().getName() + " teleported to x: " + x + ", y: " + y);
    }

    public void reset() {
        this.setX(this.x);
        this.setY(this.y);
        this.setHealth(this.maxHealth);
    }

    protected void initHitBox(final float x, final float y, final int width, final int height){
        this.hitBox = new Rectangle2D.Float(x, y, width, height);
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

    public double getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(double maxHealth) {
        this.maxHealth = maxHealth;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public void applyDamage(double damage){
        this.setHealth(this.getHealth() - damage);
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
}
