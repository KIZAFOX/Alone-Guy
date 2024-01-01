package fr.kizafox.aloneguy.game.client.window.sub;

import fr.kizafox.aloneguy.game.client.status.GameState;
import fr.kizafox.aloneguy.game.client.window.Game;
import fr.kizafox.aloneguy.game.entity.enemy.Enemy;
import fr.kizafox.aloneguy.game.entity.enemy.EnemyManager;
import fr.kizafox.aloneguy.game.entity.item.ItemManager;
import fr.kizafox.aloneguy.game.entity.player.Player;
import fr.kizafox.aloneguy.game.utils.WindowUtils;
import fr.kizafox.aloneguy.game.world.WorldCreator;

import javax.swing.*;
import java.awt.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static fr.kizafox.aloneguy.game.utils.GameSettings.*;

public class PlayMenu extends WindowAbstract{

    protected final Game game;

    protected final WorldCreator world;

    protected final Player player;
    protected final EnemyManager enemyManager;
    protected final ItemManager itemManager;

    protected long startTime;

    private int mouseY, mouseX;

    public PlayMenu(final Game game) {
        this.game = game;

        this.world = new WorldCreator(this.game, WorldCreator.WORLD_MAP, TILES_SIZE, TILES_SIZE);

        this.player = new Player((float) GAME_WIDTH / 2, (float) GAME_HEIGHT / 2, TILES_SIZE, TILES_SIZE, 20, Color.BLUE);
        this.enemyManager = new EnemyManager(this.game, this.player);
        this.itemManager = new ItemManager(this.game, this.player );
    }

    @Override
    public void update() {
        this.world.update(this.player);
        this.enemyManager.getEnemies().keySet().forEach(this.world::update);
        this.world.update();

        this.player.update();
        this.enemyManager.update();
        this.itemManager.update();
    }

    @Override
    public void render(Graphics graphics) {
        this.world.showMap(this.world.getTiles(), graphics);

        this.player.render(graphics);
        this.enemyManager.render(graphics);
        this.itemManager.render(graphics);

        WindowUtils.drawCenteredString(graphics, "In Game (" + this.getElapsedTimeFormatted() + ")", 30, Color.WHITE, 2, 10);
        WindowUtils.drawCenteredString(graphics, "Enemies Count: " + this.enemyManager.getAliveEnemies(), 15, Color.WHITE, 6, 10);
        WindowUtils.drawCenteredString(graphics, "Items Count: " + this.itemManager.getItemsCount(), 15, Color.WHITE, 8, 10);

        WindowUtils.drawCenteredString(graphics, "Mouse X: " + getMouseX() + " & Mouse Y: " + getMouseY(), 15, Color.BLACK, 2 * 32, 10);
    }

    public void init(){
        this.startTime = System.currentTimeMillis();
    }

    public void reset() {
        GameState.setStatus(GameState.MENU);
        this.player.reset();
        this.enemyManager.reset();
        this.itemManager.reset();
    }

    private String getElapsedTimeFormatted() {
        final long seconds = Duration.ofMillis(System.currentTimeMillis() - startTime).getSeconds();
        return String.format("%02d:%02d:%02d", seconds / 3600, (seconds % 3600) / 60, seconds % 60);
    }

    public Game getGame() {
        return game;
    }

    public WorldCreator getWorld() {
        return world;
    }

    public Player getPlayer() {
        return player;
    }

    public EnemyManager getEnemyManager() {
        return enemyManager;
    }

    public ItemManager getItemManager() {
        return itemManager;
    }

    public long getStartTime() {
        return startTime;
    }

    public int getMouseY() {
        return mouseY;
    }

    public void setMouseY(int mouseY) {
        this.mouseY = mouseY;
    }

    public int getMouseX() {
        return mouseX;
    }

    public void setMouseX(int mouseX) {
        this.mouseX = mouseX;
    }
}
