package fr.kizafox.aloneguy.game.client.window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import static fr.kizafox.aloneguy.game.utils.GameSettings.*;

/**
 * Created at 19/12/2023 at 06:39
 * Made by @KIZAFOX (twitter)
 **/

public class GameWindow extends JFrame {

    /**
     * Constructs a new frame that is initially invisible.
     * <p>
     * This constructor sets the component's locale property to the value
     * returned by <code>JComponent.getDefaultLocale</code>.
     *
     * @throws HeadlessException if GraphicsEnvironment.isHeadless()
     *                           returns true.
     * @see GraphicsEnvironment#isHeadless
     * @see Component#setSize
     * @see Component#setVisible
     * @see JComponent#getDefaultLocale
     */
    public GameWindow(final GamePanel gamePanel) throws HeadlessException {
        this.setTitle(NAME + " - " + VERSION);
        this.add(gamePanel);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);

        this.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {

            }

            @Override
            public void windowLostFocus(WindowEvent e) {

            }
        });

        System.out.println(GAME_WIDTH + "x" + GAME_HEIGHT);
    }
}
