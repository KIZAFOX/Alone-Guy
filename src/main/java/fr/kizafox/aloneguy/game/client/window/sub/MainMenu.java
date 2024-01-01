package fr.kizafox.aloneguy.game.client.window.sub;

import fr.kizafox.aloneguy.game.client.status.GameState;
import fr.kizafox.aloneguy.game.client.window.Game;
import fr.kizafox.aloneguy.game.client.window.button.MenuButton;
import fr.kizafox.aloneguy.game.utils.ImageRenderer;
import fr.kizafox.aloneguy.game.utils.WindowUtils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import static fr.kizafox.aloneguy.game.utils.GameSettings.*;

public class MainMenu extends WindowAbstract{

    protected final Game game;

    private final BufferedImage image;
    private final MenuButton[] buttons = new MenuButton[3];

    public MainMenu(final Game game) {
        this.game = game;

        this.image = ImageRenderer.load(ImageRenderer.BACKGROUND);

        this.buttons[0] = new MenuButton(GAME_WIDTH / 2, (int) (150 * SCALE), 0, GameState.PLAY);
        this.buttons[1] = new MenuButton(GAME_WIDTH / 2, (int) (220 * SCALE), 1, GameState.SETTINGS);
        this.buttons[2] = new MenuButton(GAME_WIDTH / 2, (int) (290 * SCALE), 2, GameState.QUIT);
    }

    @Override
    public void update() {
        Arrays.stream(buttons).forEach(MenuButton::update);
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(this.image, 0, 0, GAME_WIDTH, GAME_HEIGHT, null);

        WindowUtils.drawCenteredString(graphics, NAME, 50, new Color(93, 140, 49), 7, 20);

        Arrays.stream(buttons).forEach(button -> button.render(graphics));
    }

    public void mousePressed(MouseEvent event) {
        if(GameState.getCurrentState().equals(GameState.MENU)){
            Arrays.stream(buttons).forEach(button -> {
                if(this.isIn(event, button)) {
                    button.setMousePressed(true);
                }
            });
        }
    }

    public void mouseReleased(MouseEvent event) {
        if(GameState.getCurrentState().equals(GameState.MENU)){
            Arrays.stream(buttons).forEach(button -> {
                if(this.isIn(event, button)) {
                    if(button.isMousePressed()){
                        button.applyGameStatus();

                        if(GameState.getCurrentState().equals(GameState.PLAY)) this.game.getPlayMenu().init();
                    }
                }
            });
            Arrays.asList(buttons).forEach(MenuButton::resetBooleans);
        }
    }

    public void mouseMoved(MouseEvent event) {
        if(GameState.getCurrentState().equals(GameState.MENU)){
            Arrays.asList(buttons).forEach(button -> button.setMouseOver(false));
            Arrays.asList(buttons).forEach(button -> {
                if(this.isIn(event, button)){
                    button.setMouseOver(true);
                }
            });
        }
    }
}