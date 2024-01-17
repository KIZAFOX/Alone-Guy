package fr.kizafox.aloneguy.game.entity.enemy;

import fr.kizafox.aloneguy.game.client.status.GameState;
import fr.kizafox.aloneguy.game.client.window.Game;
import fr.kizafox.aloneguy.game.entity.player.Player;
import fr.kizafox.aloneguy.game.utils.Colors;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

import static fr.kizafox.aloneguy.game.utils.GameSettings.*;

/**
 * Done by @KIZAFOX on {12/01/2024} at 22:07.
 **/
public abstract class EnemyHandler {

    protected final Game game;

    public float x, y;
    public int width, height;
    public float speed;
    public double maxHealth, health, damage;
    public int expGain;

    public Rectangle hitBox;
    public int solidAreaDefaultX, solidAreaDefaultY;

    public boolean collision = false;
    public String direction = "up;";

    public BufferedImage image;
    public BufferedImage[] animations;
    public int animationTick;

    public long lastAttackTime;
    public long attackCooldown = 1000;

    public EnemyHandler(Game game, int width, int height, float speed, double maxHealth, double damage, int expGain) {
        this.game = game;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.damage = damage;
        this.expGain = expGain;

        Game.log(Colors.YELLOW + "Enemy: " + this.getClass().getSimpleName() + " loaded.");
    }

    public void update() {
        if(!GameState.getCurrentState().equals(GameState.PAUSE)){
            collision = false;

            if (this.health <= 0) {
                game.getPlayMenu().getEnemyManager().getEnemies().remove(this);
                game.getPlayMenu().getPlayer().gainExperience(this.expGain);
            }

            if(!collision){
                final float
                        playerX = game.getPlayMenu().getPlayer().getScreenX(),
                        playerY = game.getPlayMenu().getPlayer().getScreenY();

                float
                        deltaX = playerX - this.getAbsoluteX(),
                        deltaY = playerY - this.getAbsoluteY();

                final float length = (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY);
                deltaX /= length;
                deltaY /= length;

                if(deltaX > 0) {
                    direction = "right";
                }else if (deltaX < 0) {
                    direction = "left";
                }

                if (deltaY > 0) {
                    direction = "down";
                }else if (deltaY < 0) {
                    direction = "up";
                }

                this.setAbsoluteX(this.getAbsoluteX() + (deltaX * this.speed));
                this.setAbsoluteY(this.getAbsoluteY() + (deltaY * this.speed));
            }
        }
    }

    public abstract void render(final Graphics graphics);

    public void loadAnimations(BufferedImage[] image){
        this.animations = new BufferedImage[]{image[0], image[1]};
    }

    public void updateAnimation(){
        final Random random = new Random();
        final int i = random.nextInt(100) + 1;

        final int animationIndex = (i <= 25) ? 0 : (i <= 50) ? 1 : (i <= 75) ? 2 : 3;

        if(animationIndex < this.animations.length) {
            this.image = this.animations[animationIndex];
        }
    }

    public void setAnimation(Graphics graphics){
        animationTick++;

        if(animationTick % 10 == 0){
            this.updateAnimation();
        }

        double
                scale = TILES_SIZE / this.maxHealth,
                healthBar = scale * health;

        graphics.setColor(new Color(35, 35, 35));
        graphics.fillRect((int) this.getAbsoluteX() + 10, (int) this.getAbsoluteY() - 16, TILES_SIZE + 2, 7);

        graphics.setColor(new Color(255, 0, 30));
        graphics.fillRect((int) this.getAbsoluteX() + 11, (int) this.getAbsoluteY() - 15, (int) healthBar, 5);

        graphics.drawImage((this.image == null ? this.animations[0] : this.image), (int) this.getAbsoluteX(), (int) this.getAbsoluteY(), width, height, null);
    }

    protected void renderHitBox(Graphics graphics){
        graphics.setColor(Color.RED);
        graphics.drawRect((int) this.getAbsoluteX() + this.hitBox.x, (int) this.getAbsoluteY() + this.hitBox.y, this.hitBox.width, this.hitBox.height);
    }

    public void receiveDamage(Player player){
        this.health = this.health - player.getDamage();
        Game.log(Colors.RED + "Enemy: " + this.getClass().getSimpleName() + " | Health: " + this.health);
    }

    public void attackPlayer(Player player){
        long currentTime = System.currentTimeMillis();

        if(currentTime - lastAttackTime >= attackCooldown) {
            lastAttackTime = currentTime;

            player.setHealth(player.getHealth() - this.damage);
            Game.log(Colors.RED + "Player: " + player.getClass().getSimpleName() + " | Health: " + player.getHealth());
        }
    }

    public void reset(){
        this.setX(this.x);
        this.setY(this.y);

        this.setHealth(this.maxHealth);

        this.setCollision(false);
    }

    public float randomX(Player player){
        float offsetX = (((new Random().nextFloat() - .5F) * 2) * (5 * TILES_SIZE));
        return player.getScreenX() + offsetX;
    }

    public float randomY(Player player){
        float offsetY = (((new Random().nextFloat() - .5F) * 2) * (5 * TILES_SIZE));
        return player.getScreenY() + offsetY;
    }

    public Game getGame() {
        return game;
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
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
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

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public int getExpGain() {
        return expGain;
    }

    public void setExpGain(int expGain) {
        this.expGain = expGain;
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

    public boolean isCollision() {
        return collision;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public BufferedImage[] getAnimations() {
        return animations;
    }

    public void setAnimations(BufferedImage[] animations) {
        this.animations = animations;
    }

    public int getAnimationTick() {
        return animationTick;
    }

    public void setAnimationTick(int animationTick) {
        this.animationTick = animationTick;
    }

    public float getAbsoluteX(){
        final Player player = this.game.getPlayMenu().getPlayer();
        return this.x - player.getWorldX() + player.getScreenX();
    }

    public void setAbsoluteX(final float x){
        final Player player = this.game.getPlayMenu().getPlayer();
        this.x = x - player.getScreenX() + player.getWorldX();
    }

    public float getAbsoluteY() {
        final Player player = this.game.getPlayMenu().getPlayer();
        return this.y - player.getWorldY() + player.getScreenY();
    }

    public void setAbsoluteY(final float y) {
        final Player player = this.game.getPlayMenu().getPlayer();
        this.y = y - player.getScreenY() + player.getWorldY();
    }
}
