package fr.kizafox.aloneguy.game.utils;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class WindowUtils {

    public static void drawCenteredString(Graphics graphics, String text, int fontSize, Color color, int tileRow, int tileSize) {
        try {
            final InputStream fontStream = WindowUtils.class.getResourceAsStream("/fonts/Daydream.ttf");

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

        final FontMetrics fm = graphics.getFontMetrics();

        final int
                x = (GameSettings.GAME_WIDTH - fm.stringWidth(text)) / 2,
                y = (tileRow * tileSize) + ((tileSize - fm.getHeight()) / 2) + fm.getAscent();

        graphics.drawString(text, x, y);
    }
}
