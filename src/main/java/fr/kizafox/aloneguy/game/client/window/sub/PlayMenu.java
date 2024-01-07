package fr.kizafox.aloneguy.game.client.window.sub;

import fr.kizafox.aloneguy.game.client.status.GameState;
import fr.kizafox.aloneguy.game.client.window.Game;
import fr.kizafox.aloneguy.game.entity.enemy.EnemyManager;
import fr.kizafox.aloneguy.game.entity.object.ObjectHandler;
import fr.kizafox.aloneguy.game.entity.object.ObjectManager;
import fr.kizafox.aloneguy.game.entity.player.Player;
import fr.kizafox.aloneguy.game.utils.WindowUtils;
import fr.kizafox.aloneguy.game.world.TileManager;

import javax.swing.*;
import java.awt.*;
import java.time.Duration;

public class PlayMenu extends WindowAbstract{

    protected final Game game;

    protected final TileManager tileManager;

    protected final Player player;
    protected final ObjectManager objectManager;

    protected long startTime;

    public PlayMenu(final Game game){
        this.game = game;

        this.tileManager = new TileManager(this.game);

        this.player = new Player(this.game);
        this.objectManager = new ObjectManager(this.game);
    }

    @Override
    public void update() {
        this.player.update();
    }

    @Override
    public void render(Graphics graphics) {
        this.tileManager.render(graphics);

        this.player.render(graphics);
        this.objectManager.render(graphics);

        WindowUtils.drawCenteredString(graphics, "In Game (" + this.getElapsedTimeFormatted() + ")", 30, Color.WHITE, 2, 10);
    }

    public void init(){
        this.startTime = System.currentTimeMillis();
    }

    public void reset() {
        GameState.setStatus(GameState.MENU);
        this.player.reset();
    }

    private String getElapsedTimeFormatted() {
        final long seconds = Duration.ofMillis(System.currentTimeMillis() - startTime).getSeconds();
        return String.format("%02d:%02d:%02d", seconds / 3600, (seconds % 3600) / 60, seconds % 60);
    }

    public Game getGame() {
        return game;
    }

    public TileManager getTileManager() {
        return tileManager;
    }

    public ObjectManager getObjectManager() {
        return objectManager;
    }

    public Player getPlayer() {
        return player;
    }

    public long getStartTime() {
        return startTime;
    }
}
