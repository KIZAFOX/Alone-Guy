package fr.kizafox.aloneguy.game.client.window;

import fr.kizafox.aloneguy.game.client.status.GameState;
import fr.kizafox.aloneguy.game.client.window.sub.*;
import fr.kizafox.aloneguy.game.utils.FPSChecker;

import java.awt.*;

/**
 * Created at 19/12/2023 at 06:35
 * Made by @KIZAFOX (twitter)
 **/

public class Game {

    private final GamePanel gamePanel;
    private final GameWindow gameWindow;

    private final MainMenu mainMenu;
    private final PlayMenu playMenu;
    private final SettingsMenu settingsMenu;
    private final PauseMenu pauseMenu;
    private final LoseMenu loseMenu;

    private final FPSChecker fpsChecker;

    private final Object pauseLock = new Object();

    public Game() {
        GameState.setStatus(GameState.MENU);

        this.gamePanel = new GamePanel(this);
        this.gameWindow = new GameWindow(this.gamePanel);

        this.mainMenu = new MainMenu(this);
        this.playMenu = new PlayMenu(this);
        this.settingsMenu = new SettingsMenu();
        this.pauseMenu = new PauseMenu(this);
        this.loseMenu = new LoseMenu(this);

        this.fpsChecker = new FPSChecker(this);

        new Thread(this.fpsChecker).start();
    }

    public void update(){
        synchronized (pauseLock){
            switch (GameState.getCurrentState()){
                case MENU -> this.mainMenu.update();
                case PLAY -> this.playMenu.update();
                case SETTINGS -> this.settingsMenu.update();
                case LOSE -> this.loseMenu.update();
                case PAUSE -> {
                    this.playMenu.update();
                    this.pauseMenu.update();
                }
                default -> System.exit(0);
            }
        }
    }

    public void render(final Graphics graphics){
        synchronized (pauseLock){
            switch (GameState.getCurrentState()){
                case MENU ->  this.mainMenu.render(graphics);
                case PLAY -> this.playMenu.render(graphics);
                case SETTINGS -> this.settingsMenu.render(graphics);
                case LOSE -> this.loseMenu.render(graphics);
                case PAUSE -> {
                    this.playMenu.render(graphics);
                    this.pauseMenu.render(graphics);
                }
            }
        }
    }

    public MainMenu getMainMenu() {
        return mainMenu;
    }

    public PlayMenu getPlayMenu() {
        return playMenu;
    }

    public SettingsMenu getSettingsMenu() {
        return settingsMenu;
    }

    public PauseMenu getPauseMenu() {
        return pauseMenu;
    }

    public LoseMenu getLoseMenu() {
        return loseMenu;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public GameWindow getGameWindow() {
        return gameWindow;
    }

    public FPSChecker getFpsChecker() {
        return fpsChecker;
    }
}
