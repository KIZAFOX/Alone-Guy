package fr.kizafox.aloneguy.game.client.window.sub;

import fr.kizafox.aloneguy.game.client.status.GameState;
import fr.kizafox.aloneguy.game.client.window.Game;
import fr.kizafox.aloneguy.game.client.window.buttons.MenuButton;
import fr.kizafox.aloneguy.game.utils.image.ImageRenderer;
import fr.kizafox.aloneguy.game.utils.sound.SoundPlayer;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import static fr.kizafox.aloneguy.game.utils.GameSettings.*;

public class MainMenu extends WindowAbstract implements ActionListener{

    protected final Game game;

    private final BufferedImage backgroundImage, backgroundMenu;
    private final MenuButton[] buttons = new MenuButton[3];

    public MainMenu(final Game game) {
        this.game = game;

        this.backgroundImage = ImageRenderer.load(ImageRenderer.BACKGROUND);
        this.backgroundMenu = ImageRenderer.load(ImageRenderer.MAIN_MENU);

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
        graphics.drawImage(this.backgroundImage, 0, 0, GAME_WIDTH, GAME_HEIGHT, null);
        graphics.drawImage(this.backgroundMenu, (int) (GAME_WIDTH / 2 -  this.backgroundMenu.getWidth() * SCALE / 2), (int) (45 * SCALE), (int) (this.backgroundMenu.getWidth() * SCALE), (int) (this.backgroundMenu.getHeight() * SCALE), null);

        Arrays.stream(buttons).forEach(button -> button.render(graphics));
    }

    @Override
    public void mousePressed(MouseEvent event) {
        if(GameState.getCurrentState().equals(GameState.MENU)){
            Arrays.stream(buttons).forEach(button -> {
                if(this.isIn(event, button)) {
                    button.setMousePressed(true);
                    SoundPlayer.playSound(SoundPlayer.BUTTON_PRESS_SOUND, SoundPlayer.Volume.HIGH);
                }
            });
        }
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        if(GameState.getCurrentState().equals(GameState.MENU)){
            Arrays.stream(buttons).forEach(button -> {
                if(this.isIn(event, button)) {
                    if(button.isMousePressed()){
                        button.applyGameStatus();

                        if(GameState.getCurrentState().equals(GameState.PLAY)){
                            this.game.getPlayMenu().init();
                            //SoundPlayer.playSound(SoundPlayer.TEST, SoundPlayer.Volume.LOW);
                        };
                    }
                }
            });
            Arrays.asList(buttons).forEach(MenuButton::resetBooleans);
        }
    }

    @Override
    public void mouseDragged(MouseEvent event) {

    }

    @Override
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