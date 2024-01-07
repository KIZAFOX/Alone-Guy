package fr.kizafox.aloneguy.game.entity;

import fr.kizafox.aloneguy.game.client.window.Game;
import fr.kizafox.aloneguy.game.utils.Colors;
import fr.kizafox.aloneguy.game.utils.GameSettings;

import java.awt.*;

public abstract class Entity {

    protected final EntityType entityType;
    protected float worldX, worldY, speed = 1.5F * GameSettings.SCALE;
    protected int width, height;
    protected double maxHealth, health;
    protected Rectangle hitBox;
    protected int solidAreaDefaultX, solidAreaDefaultY;

    public boolean up, down, left, right, collision = false;
    public int spriteCounter = 0, spriteNumber = 1;

    public Entity(EntityType entityType, float worldX, float worldY, int width, int height, double maxHealth) {
        this.entityType = entityType;
        this.worldX = worldX;
        this.worldY = worldY;
        this.width = width;
        this.height = height;
        this.maxHealth = maxHealth;
        this.health = this.maxHealth;

        System.out.println();
        Game.log(Colors.YELLOW + "Entity: " + entityType + " loaded.");
        Game.log(Colors.YELLOW + "WorldX: " + this.worldX + " - WorldY: " + this.worldY + " - Width: " + this.width + " - Height: " + this.height + " - MaxHealth: " + this.maxHealth);
        System.out.println();

    }

    public abstract void update();

    public abstract void render(final Graphics graphics);

    protected void initHitBox(final float x, final float y, final int width, final int height){
        this.hitBox = new Rectangle((int) x, (int) y, width, height);
        this.solidAreaDefaultX = this.hitBox.x;
        this.solidAreaDefaultY = this.hitBox.y;
    }

    protected void renderHitBox(Graphics graphics, int screenX, int screenY){
        graphics.setColor(Color.RED);
        graphics.drawRect(screenX + this.hitBox.x, screenY + this.hitBox.y, this.hitBox.width, this.hitBox.height);
    }

    protected boolean isMoving(){
        return this.isUp() || this.isDown() || this.isLeft() || this.isRight();
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

    public EntityType getEntityType() {
        return entityType;
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
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
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

    public boolean isCollision() {
        return collision;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }

    public int getSpriteCounter() {
        return spriteCounter;
    }

    public void setSpriteCounter(int spriteCounter) {
        this.spriteCounter = spriteCounter;
    }

    public int getSpriteNumber() {
        return spriteNumber;
    }

    public void setSpriteNumber(int spriteNumber) {
        this.spriteNumber = spriteNumber;
    }

    public enum EntityType {
        PLAYER,
        ENEMY;
    }
}
