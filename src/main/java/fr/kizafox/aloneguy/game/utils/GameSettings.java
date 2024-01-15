package fr.kizafox.aloneguy.game.utils;

import fr.kizafox.aloneguy.game.client.window.sub.WindowAbstract;
import fr.kizafox.aloneguy.game.utils.github.GitHubAPI;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created at 19/12/2023 at 06:38
 * Made by @KIZAFOX (twitter)
 **/

public class GameSettings {
    public static final String
            NAME = GitHubAPI.getRepository().getName(),
            VERSION = "0.0.1-" + GitHubAPI.getCommit().getSHA1().substring(0, 7),
            VERSION_NAME = "Early Access Version",
            DESCRIPTION = GitHubAPI.getRepository().getDescription(),
            URL = GitHubAPI.getRepository().getHtmlUrl().toString();

    public static final String[] AUTHORS = {GitHubAPI.getRepository().getOwnerName()};

    public static final float SCALE = 1.5F;

    public static final int
            TILES_DEFAULT_SIZE = 32,
            TILES_IN_WIDTH = 26,
            TILES_IN_HEIGHT = 15,
            TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE),
            GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH,
            GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;

    public static void renderText(final Graphics graphics, final String text, final int fontSize, final Color color, final int x, final int y) {
        try {
            final InputStream fontStream = WindowAbstract.class.getResourceAsStream("/fonts/BabaMediumPixels.ttf");

            if (fontStream != null) {
                final Font customFont = Font.createFont(Font.TRUETYPE_FONT, fontStream);

                final GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
                graphicsEnvironment.registerFont(customFont);

                graphics.setFont(customFont.deriveFont(Font.PLAIN, fontSize));
            } else {
                System.err.println("Unable to open font stream.");
            }

        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }

        graphics.setColor(color);

        graphics.drawString(text, x, y);
    }
}
