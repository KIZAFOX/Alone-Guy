package fr.kizafox.aloneguy.game.entity;

import fr.kizafox.aloneguy.game.client.window.Game;
import fr.kizafox.aloneguy.game.utils.Colors;
import fr.kizafox.aloneguy.game.utils.Constants;

import java.awt.*;
import java.awt.image.BufferedImage;

import static fr.kizafox.aloneguy.game.utils.GameSettings.*;

public abstract class Entity {

    protected float worldX, worldY, speed = 1.20F * SCALE;
    protected int screenX, screenY;
    protected int width, height;
    protected double maxHealth, health;
    protected int damage;
    protected int currentExp = 50, level = 1, expToNextLevel = 100;
    protected Rectangle hitBox, attackBox;
    protected int solidAreaDefaultX, solidAreaDefaultY;

    public boolean showMap = false, showMinimap = true, hasMiniMap = true;
    public boolean up, down, left, right, wasFacingLeft = false, attacking, collision = false;

    public BufferedImage[][] animations;

    public int animationTick, animationIndex, animationSpeed;
    public int playerState = Constants.PlayerState.IDLE;

    public Entity(float worldX, float worldY, int width, int height, double maxHealth, int damage) {
        this.worldX = worldX;
        this.worldY = worldY;
        this.width = width;
        this.height = height;
        this.maxHealth = maxHealth;
        this.health = this.maxHealth;
        this.damage = damage;

        Game.log(Colors.YELLOW + "Entity: " + this.getClass().getSimpleName() + " loaded.");
        Game.log(Colors.YELLOW + "WorldX: " + this.worldX + " - WorldY: " + this.worldY + " - MaxHealth: " + this.maxHealth + " - Damage: " + this.damage);
        System.out.println();
    }

    public abstract void update();

    public abstract void render(final Graphics graphics);

    protected void initHitBox(){
        this.hitBox = new Rectangle((int) (float) 40, (int) (float) 15, 45, 60);
        this.solidAreaDefaultX = this.hitBox.x;
        this.solidAreaDefaultY = this.hitBox.y;
    }

    protected void renderHitBox(final Graphics graphics, final int screenX, final int screenY){
        graphics.setColor(Color.RED);
        graphics.drawRect(screenX + this.hitBox.x, screenY + this.hitBox.y, this.hitBox.width, this.hitBox.height);
    }

    protected void initAttackBox(final float x, final float y, final int width, final int height){
        this.attackBox = new Rectangle((int) x, (int) y, width, height);
    }

    protected void renderAttackBox(final Graphics graphics){
        if(isAttacking()){
            graphics.setColor(Color.RED);
            graphics.drawRect(this.attackBox.x, this.attackBox.y, this.attackBox.width, this.attackBox.height);
        }
    }

    protected boolean isMoving(){
        return this.isUp() || this.isDown() || this.isLeft() || this.isRight();
    }

    public boolean isDead(){
        return this.health <= 0;
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

    public int getScreenX() {
        return screenX;
    }

    public void setScreenX(int screenX) {
        this.screenX = screenX;
    }

    public int getScreenY() {
        return screenY;
    }

    public void setScreenY(int screenY) {
        this.screenY = screenY;
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

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getCurrentExp() {
        return currentExp;
    }

    public void setCurrentExp(int currentExp) {
        this.currentExp = currentExp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExpToNextLevel() {
        return expToNextLevel;
    }

    public void setExpToNextLevel(int expToNextLevel) {
        this.expToNextLevel = expToNextLevel;
    }

    public String getExpPercentage(){
        return String.format("%.2f%%", (double) this.currentExp / this.expToNextLevel * 100);
    }

    public void gainExperience(int experience){
        this.currentExp += experience;

        if(this.currentExp >= this.expToNextLevel){
            currentExp = 0;
            level++;
            Game.log("Congratulations! You leveled up to level " + level + ".");
            expToNextLevel = level * 100;
        }
    }

    public Rectangle getHitBox() {
        return hitBox;
    }

    public void setHitBox(Rectangle hitBox) {
        this.hitBox = hitBox;
    }

    public Rectangle getAttackBox() {
        return attackBox;
    }

    public void setAttackBox(Rectangle attackBox) {
        this.attackBox = attackBox;
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

    public boolean isShowMap() {
        return showMap;
    }

    public void setShowMap(boolean showMap) {
        this.showMap = showMap;
    }

    public boolean isShowMinimap() {
        return showMinimap;
    }

    public void setShowMinimap(boolean showMinimap) {
        this.showMinimap = showMinimap;
    }

    public boolean isHasMiniMap() {
        return hasMiniMap;
    }

    public void setHasMiniMap(boolean hasMiniMap) {
        this.hasMiniMap = hasMiniMap;
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

    public boolean isWasFacingLeft() {
        return wasFacingLeft;
    }

    public void setWasFacingLeft(boolean wasFacingLeft) {
        this.wasFacingLeft = wasFacingLeft;
    }

    public boolean isAttacking() {
        return attacking;
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }

    public boolean isCollision() {
        return collision;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }

    public BufferedImage[][] getAnimations() {
        return animations;
    }

    public void setAnimations(BufferedImage[][] animations) {
        this.animations = animations;
    }

    public int getAnimationTick() {
        return animationTick;
    }

    public void setAnimationTick(int animationTick) {
        this.animationTick = animationTick;
    }

    public int getAnimationIndex() {
        return animationIndex;
    }

    public void setAnimationIndex(int animationIndex) {
        this.animationIndex = animationIndex;
    }

    public int getAnimationSpeed() {
        return animationSpeed;
    }

    public void setAnimationSpeed(int animationSpeed) {
        this.animationSpeed = animationSpeed;
    }

    public int getPlayerState() {
        return playerState;
    }

    public void setPlayerState(int playerState) {
        this.playerState = playerState;
    }
}
