package fr.kizafox.aloneguy.game.client.window.sub;

import fr.kizafox.aloneguy.game.client.status.GameState;
import fr.kizafox.aloneguy.game.client.window.Game;
import fr.kizafox.aloneguy.game.entity.enemy.EnemyManager;
import fr.kizafox.aloneguy.game.entity.player.Player;
import fr.kizafox.aloneguy.game.utils.Time;
import fr.kizafox.aloneguy.game.world.Map;
import fr.kizafox.aloneguy.game.world.tile.TileManager;

import java.awt.*;
import java.awt.event.MouseEvent;

import static fr.kizafox.aloneguy.game.utils.GameSettings.*;

public class PlayMenu extends WindowAbstract implements ActionListener{

    protected final Game game;

    protected final TileManager tileManager;

    protected final Player player;
    protected final EnemyManager enemyManager;

    protected Map map;
    protected Time time;

    public PlayMenu(final Game game){
        this.game = game;

        this.tileManager = new TileManager(this.game);

        this.player = new Player(this.game);
        this.enemyManager = new EnemyManager(this.game);
    }

    @Override
    public void update() {
        this.player.update();
        this.enemyManager.update();
    }

    @Override
    public void render(Graphics graphics) {
        this.getGame().getGamePanel().setBackground(Color.BLACK);

        this.tileManager.render(graphics);

        this.player.render(graphics);
        this.enemyManager.render(graphics);

        renderText(graphics, this.time.getFormattedTime(), 30, Color.WHITE, GAME_WIDTH - 210, 35);
    }

    public void init(){
        this.time = new Time();
        this.map = new Map(this.game);
    }

    public void reset(GameState gameState) {
        GameState.setStatus(gameState);
        this.player.reset();
        this.enemyManager.reset();
        this.time.restart();
        this.game.getPauseMenu().paused = false;
    }

    @Override
    public void mousePressed(MouseEvent event) {

    }

    @Override
    public void mouseReleased(MouseEvent event) {

    }

    @Override
    public void mouseDragged(MouseEvent event) {

    }

    @Override
    public void mouseMoved(MouseEvent event) {

    }

    public Game getGame() {
        return game;
    }

    public TileManager getTileManager() {
        return tileManager;
    }

    public Player getPlayer() {
        return player;
    }

    public EnemyManager getEnemyManager() {
        return enemyManager;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}
