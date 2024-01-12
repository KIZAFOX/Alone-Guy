package fr.kizafox.aloneguy.game.client.window.sub;

import fr.kizafox.aloneguy.game.client.status.GameState;
import fr.kizafox.aloneguy.game.client.window.Game;
import fr.kizafox.aloneguy.game.entity.enemy.EnemyManager;
import fr.kizafox.aloneguy.game.object.ObjectManager;
import fr.kizafox.aloneguy.game.entity.player.Player;
import fr.kizafox.aloneguy.game.utils.GameSettings;
import fr.kizafox.aloneguy.game.utils.WindowUtils;
import fr.kizafox.aloneguy.game.world.Map;
import fr.kizafox.aloneguy.game.world.tile.TileManager;

import java.awt.*;
import java.time.Duration;

public class PlayMenu extends WindowAbstract{

    protected final Game game;

    protected final TileManager tileManager;
    protected Map map;

    protected final Player player;

    protected final EnemyManager enemyManager;
    protected final ObjectManager objectManager;

    protected long startTime;

    public PlayMenu(final Game game){
        this.game = game;

        this.tileManager = new TileManager(this.game);

        this.player = new Player(this.game);

        this.enemyManager = new EnemyManager(this.game);
        this.objectManager = new ObjectManager(this.game);
    }

    @Override
    public void update() {
        this.player.update();

        this.enemyManager.update();
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, GameSettings.GAME_WIDTH, GameSettings.GAME_HEIGHT);

        this.tileManager.render(graphics);

        this.player.render(graphics);

        this.enemyManager.render(graphics);

        //this.objectManager.render(graphics);

        WindowUtils.drawCenteredString(graphics, "In Game (" + this.getElapsedTimeFormatted() + ")", 30, Color.WHITE, 2, 10);
        WindowUtils.drawCenteredString(graphics, "Level: " + this.getPlayer().getLevel(), 20, Color.BLACK, 5, 10);
    }

    public void init(){
        this.startTime = System.currentTimeMillis();
        this.map = new Map(this.game);
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

    public Map getMap() {
        return map;
    }

    public Player getPlayer() {
        return player;
    }

    public EnemyManager getEnemyManager() {
        return enemyManager;
    }

    public ObjectManager getObjectManager() {
        return objectManager;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }
}
