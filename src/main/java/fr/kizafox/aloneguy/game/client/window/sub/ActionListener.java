package fr.kizafox.aloneguy.game.client.window.sub;

import fr.kizafox.aloneguy.game.client.inputs.mouse.MouseInputs;

import java.awt.event.MouseEvent;

/**
 * Done by @KIZAFOX on {14/01/2024} at 12:13.
 **/
public interface ActionListener {

    void mousePressed(final MouseEvent event);

    void mouseReleased(final MouseEvent event);

    void mouseDragged(final MouseEvent event);

    void mouseMoved(final MouseEvent event);
}
