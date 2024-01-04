package fr.kizafox.aloneguy.game.client.inputs.keyboard;

import fr.kizafox.aloneguy.game.client.status.GameState;
import fr.kizafox.aloneguy.game.client.window.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBoardInputs implements KeyListener {

    protected final GamePanel gamePanel;

    public KeyBoardInputs(final GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    /**
     * Invoked when a key has been typed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key typed event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Invoked when a key has been pressed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key pressed event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        switch (GameState.getCurrentState()){
            case PLAY -> {
                if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    this.gamePanel.getGame().getPauseMenu().setPause(GameState.PAUSE, true);
                }else {
                    this.gamePanel.getGame().getPlayMenu().getPlayer().keyPressed(e);
                }
            }
            case SETTINGS -> {
                if(e.getKeyCode() == KeyEvent.VK_ESCAPE) GameState.setStatus(GameState.MENU);
            }
            case PAUSE -> {
                if(e.getKeyCode() == KeyEvent.VK_ESCAPE) this.gamePanel.getGame().getPauseMenu().setPause(GameState.PLAY, false);
            }
            case LOSE -> {
                if(e.getKeyCode() == KeyEvent.VK_ESCAPE) this.gamePanel.getGame().getPlayMenu().reset();
            }
        }
    }

    /**
     * Invoked when a key has been released.
     * See the class description for {@link KeyEvent} for a definition of
     * a key released event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {
        this.gamePanel.getGame().getPlayMenu().getPlayer().keyReleased(e);
    }
}
