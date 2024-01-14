package fr.kizafox.aloneguy.game.client.window.sub;

import fr.kizafox.aloneguy.game.client.status.GameState;
import fr.kizafox.aloneguy.game.client.window.Game;
import fr.kizafox.aloneguy.game.client.window.buttons.SoundButton;
import fr.kizafox.aloneguy.game.client.window.buttons.UrmButton;
import fr.kizafox.aloneguy.game.client.window.buttons.VolumeButton;
import fr.kizafox.aloneguy.game.utils.Colors;
import fr.kizafox.aloneguy.game.utils.image.ImageRenderer;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static fr.kizafox.aloneguy.game.utils.GameSettings.*;
import static fr.kizafox.aloneguy.game.client.window.buttons.PauseButton.*;

public class PauseMenu extends WindowAbstract implements ActionListener{

    protected final Game game;

    protected BufferedImage backgroundImage;
    protected int backgroundX, backgroundY, backgroundWidth, backgroundHeight;

    protected SoundButton musicButton, sfxButton;
    protected UrmButton menuButton, replayButton, unpauseButton;
    protected VolumeButton volumeButton;

    public boolean paused = false;

    public PauseMenu(final Game game) {
        this.game = game;

        this.loadImages();
        this.loadButtons();
    }

    @Override
    public void update() {
        this.musicButton.update();
        this.sfxButton.update();

        this.menuButton.update();
        this.replayButton.update();
        this.unpauseButton.update();

        this.volumeButton.update();
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(new Color(0, 0, 0, 90));
        graphics.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);

        graphics.drawImage(this.backgroundImage, this.backgroundX, this.backgroundY, this.backgroundWidth, this.backgroundHeight, null);

        this.musicButton.render(graphics);
        this.sfxButton.render(graphics);

        this.replayButton.render(graphics);
        this.menuButton.render(graphics);
        this.unpauseButton.render(graphics);

        this.volumeButton.render(graphics);
    }

    @Override
    public void mousePressed(MouseEvent event) {
        if(GameState.getCurrentState().equals(GameState.PAUSE)){
            if(this.isIn(event, this.musicButton)){
                this.musicButton.setMousePressed(true);
            }else if(this.isIn(event, this.sfxButton)){
                this.sfxButton.setMousePressed(true);
            }else if(this.isIn(event, this.menuButton)){
                this.menuButton.setMousePressed(true);
            }else if(this.isIn(event, this.replayButton)){
                this.replayButton.setMousePressed(true);
            }else if(this.isIn(event, this.unpauseButton)){
                this.unpauseButton.setMousePressed(true);
            }else if(this.isIn(event, this.volumeButton)){
                this.volumeButton.setMousePressed(true);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        if(GameState.getCurrentState().equals(GameState.PAUSE)){
            if(this.isIn(event, this.musicButton)){
                if(this.musicButton.isMousePressed()){
                    this.musicButton.setMuted(!this.musicButton.isMuted());
                }
            }else if(this.isIn(event, this.sfxButton)){
                if(this.sfxButton.isMousePressed()){
                    this.sfxButton.setMuted(!this.sfxButton.isMuted());
                }
            }else if(this.isIn(event, this.menuButton)){
                if(this.menuButton.isMousePressed()){
                    GameState.setStatus(GameState.MENU);
                    this.paused = false;
                }
            }else if(this.isIn(event, this.replayButton)){
                if(this.replayButton.isMousePressed()){
                    this.game.getPlayMenu().reset(GameState.PLAY);
                    this.paused = false;

                    Game.log(Colors.RED + "Player has restarted the game.");
                }
            }else if(this.isIn(event, this.unpauseButton)){
                if(this.unpauseButton.isMousePressed()){
                    this.setPause(GameState.PLAY, false);
                }
            }

            this.musicButton.resetBooleans();
            this.sfxButton.resetBooleans();
            this.menuButton.resetBooleans();
            this.replayButton.resetBooleans();
            this.unpauseButton.resetBooleans();
            this.volumeButton.resetBooleans();
        }
    }

    @Override
    public void mouseDragged(MouseEvent event) {
        if(GameState.getCurrentState().equals(GameState.PAUSE)){
            if(this.volumeButton.isMousePressed()) this.volumeButton.changeX(event.getX());
        }
    }

    @Override
    public void mouseMoved(MouseEvent event) {
        if(GameState.getCurrentState().equals(GameState.PAUSE)){
            this.musicButton.setMouseOver(false);
            this.sfxButton.setMouseOver(false);
            this.menuButton.setMouseOver(false);
            this.replayButton.setMouseOver(false);
            this.unpauseButton.setMouseOver(false);
            this.volumeButton.setMouseOver(false);

            if(this.isIn(event, this.musicButton)){
                this.musicButton.setMouseOver(true);
            }else if(this.isIn(event, this.sfxButton)){
                this.sfxButton.setMouseOver(true);
            }else if(this.isIn(event, this.menuButton)){
                this.menuButton.setMouseOver(true);
            }else if(this.isIn(event, this.replayButton)){
                this.replayButton.setMouseOver(true);
            }else if(this.isIn(event, this.unpauseButton)){
                this.unpauseButton.setMouseOver(true);
            }else if(this.isIn(event, this.volumeButton)){
                this.volumeButton.setMouseOver(true);
            }
        }
    }

    public void setPause(final GameState gameState, final boolean pause){
        GameState.setStatus(gameState);
        paused = pause;
    }

    private void loadImages() {
        this.backgroundImage = ImageRenderer.load(ImageRenderer.PAUSE_MENU);
        this.backgroundWidth = (int) (this.backgroundImage.getWidth() * SCALE);
        this.backgroundHeight = (int) (this.backgroundImage.getHeight() * SCALE);
        this.backgroundX = GAME_WIDTH / 2 - this.backgroundWidth / 2;
        this.backgroundY = (int) (25 * SCALE);
    }

    private void loadButtons() {
        int
                soundX = (int) (450 * SCALE),

                musicY = (int) (140 * SCALE),
                sfxY = (int) (186 * SCALE);

        this.musicButton = new SoundButton(soundX, musicY, SOUND_SIZE, SOUND_SIZE);
        this.sfxButton = new SoundButton(soundX, sfxY, SOUND_SIZE, SOUND_SIZE);

        final int
                menuX = (int) (313 * SCALE),
                replayX = (int) (387 * SCALE),
                unpauseX = (int) (462 * SCALE),
                bY = (int) (325 * SCALE);

        this.menuButton = new UrmButton(menuX, bY, URM_SIZE, URM_SIZE, 2);
        this.replayButton = new UrmButton(replayX, bY, URM_SIZE, URM_SIZE, 1);
        this.unpauseButton = new UrmButton(unpauseX, bY, URM_SIZE, URM_SIZE, 0);

        int
                volumeX = (int) (309 * SCALE),
                volumeY = (int) (278 * SCALE);

        this.volumeButton = new VolumeButton(volumeX, volumeY, SLIDER_WIDTH, VOLUME_HEIGHT);
    }
}
