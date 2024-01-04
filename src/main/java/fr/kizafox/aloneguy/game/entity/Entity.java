package fr.kizafox.aloneguy.game.entity;

import fr.kizafox.aloneguy.game.utils.GameSettings;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public abstract class Entity {

    protected float worldX, worldY, speed = 1.5F * GameSettings.SCALE;
    protected int width, height;
    protected double maxHealth, health;
    protected Rectangle2D.Float hitBox;

    public boolean up, down, left, right;

    public Entity(float worldX, float worldY, int width, int height, double maxHealth) {
        this.worldX = worldX;
        this.worldY = worldY;
        this.width = width;
        this.height = height;
        this.maxHealth = maxHealth;
        this.health = this.maxHealth;
    }

    public abstract void update();

    public abstract void render(final Graphics graphics);

    protected void initHitBox(final float x, final float y, final int width, final int height){
        this.hitBox = new Rectangle2D.Float(x, y, width, height);
    }

    protected void renderHitBox(final Graphics graphics){
        graphics.setColor(Color.RED);
        graphics.drawRect((int) this.hitBox.x, (int) this.hitBox.y, (int) this.hitBox.width, (int) this.hitBox.height);
    }

    public void teleport(final float x, final float y){
        this.setWorldX(x);
        this.setWorldY(y);
    }

    public void reset() {
        this.setWorldX(this.worldX);
        this.setWorldY(this.worldY);
        this.setHealth(this.maxHealth);
    }

    public float getWorldX() {
        return worldX;
    }

    public void setWorldX(float worldX) {
        this.worldX = worldX;
    }

    public float getWorldY() {
        return worldY;
    }

    public void setWorldY(float worldY) {
        this.worldY = worldY;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
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

    public Rectangle2D.Float getHitBox() {
        return hitBox;
    }

    public void setHitBox(Rectangle2D.Float hitBox) {
        this.hitBox = hitBox;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }
}
