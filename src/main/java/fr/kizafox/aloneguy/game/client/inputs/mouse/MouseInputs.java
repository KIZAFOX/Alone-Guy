package fr.kizafox.aloneguy.game.client.inputs.mouse;

import fr.kizafox.aloneguy.game.client.window.GamePanel;
import fr.kizafox.aloneguy.game.client.window.sub.MainMenu;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInputs extends MouseAdapter implements MouseListener, MouseMotionListener {

    protected final GamePanel gamePanel;

    public MouseInputs(final GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    /**
     * Invoked when the mouse button has been clicked (pressed
     * and released) on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    /**
     * Invoked when a mouse button has been pressed on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {
        /**
        final Button play = this.gamePanel.getGame().getMainMenu().play;
        final Button settings = this.gamePanel.getGame().getMainMenu().settings;
        final Button quit = this.gamePanel.getGame().getMainMenu().quit;

        if(play.isEnabled() && play.isInside(e.getX(), e.getY())){
            GameState.setStatus(GameState.PLAY);
            WorldUtils.WorldPlayer.setWorld(WorldUtils.WorldPlayer.WORLD);
        }else if(settings.isEnabled() && settings.isInside(e.getX(), e.getY())){
            GameState.setStatus(GameState.SETTINGS);
        }else if(quit.isEnabled() && quit.isInside(e.getX(), e.getY())){
            GameState.setStatus(GameState.QUIT);
            System.exit(0);
        }

        final Button back = this.gamePanel.getGame().getPauseMenu().back;
        final Button mainMenu = this.gamePanel.getGame().getPauseMenu().mainMenu;
        final Button restart = this.gamePanel.getGame().getPauseMenu().restart;

        if(back.isEnabled() && back.isInside(e.getX(), e.getY())){
            GameState.setStatus(GameState.PLAY);
        }else if(mainMenu.isEnabled() && mainMenu.isInside(e.getX(), e.getY())){
            GameState.setStatus(GameState.MENU);
        }else if(restart.isEnabled() && restart.isInside(e.getX(), e.getY())){
            this.gamePanel.getGame().getPlayMenu().reset();
        }
         **/

        this.gamePanel.getGame().getMainMenu().mousePressed(e);
    }

    /**
     * Invoked when a mouse button has been released on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        this.gamePanel.getGame().getMainMenu().mouseReleased(e);
    }

    /**
     * Invoked when the mouse enters a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    /**
     * Invoked when the mouse exits a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }

    /**
     * Invoked when a mouse button is pressed on a component and then
     * dragged.  {@code MOUSE_DRAGGED} events will continue to be
     * delivered to the component where the drag originated until the
     * mouse button is released (regardless of whether the mouse position
     * is within the bounds of the component).
     * <p>
     * Due to platform-dependent Drag&amp;Drop implementations,
     * {@code MOUSE_DRAGGED} events may not be delivered during a native
     * Drag&amp;Drop operation.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseDragged(MouseEvent e) {

    }

    /**
     * Invoked when the mouse cursor has been moved onto a component
     * but no buttons have been pushed.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        this.gamePanel.getGame().getMainMenu().mouseMoved(e);

        this.gamePanel.getGame().getPlayMenu().setMouseX(e.getX());
        this.gamePanel.getGame().getPlayMenu().setMouseY(e.getY());

    }
}
