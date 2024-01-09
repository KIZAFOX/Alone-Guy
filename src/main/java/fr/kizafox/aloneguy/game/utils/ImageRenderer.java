package fr.kizafox.aloneguy.game.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class ImageRenderer {

    public static final String BACKGROUND = "background.jpg";

    public static final String BUTTON_ATLAS = "button_atlas.png";

    public static final String PLAYER_SHEET = "sprites/player/test/player_sheet.png";

    public static final String PLAYER_UP_1 = "sprites/player/boy_up_1.png";
    public static final String PLAYER_UP_2 = "sprites/player/boy_up_2.png";

    public static final String PLAYER_DOWN_1 = "sprites/player/boy_down_1.png";
    public static final String PLAYER_DOWN_2 = "sprites/player/boy_down_2.png";

    public static final String PLAYER_LEFT_1 = "sprites/player/boy_left_1.png";
    public static final String PLAYER_LEFT_2 = "sprites/player/boy_left_2.png";

    public static final String PLAYER_RIGHT_1 = "sprites/player/boy_right_1.png";
    public static final String PLAYER_RIGHT_2 = "sprites/player/boy_right_2.png";

    public static final String ZOMBIE = "sprites/enemies/zombie.png";

    public static final String HEAL_POTION = "sprites/potions/heal_potion.png";

    public static final String LANDMINE = "sprites/traps/landmine.png";

    public static BufferedImage load(final String fileName){
        final InputStream inputStream = ImageRenderer.class.getResourceAsStream("/" + fileName);
        final BufferedImage image;

        if(inputStream == null){
            throw new IllegalStateException("File " + fileName + " not found or doesn't exist in 'resources' folder!");
        }

        try {
            image = ImageIO.read(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                inputStream.close();
            }catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return image;
    }

    public static BufferedImage flipImage(BufferedImage image){
        final AffineTransform affineTransform = AffineTransform.getScaleInstance(-1, 1);
        affineTransform.translate(-image.getWidth(null), 0);
        AffineTransformOp affineTransformOp = new AffineTransformOp(affineTransform, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        image = affineTransformOp.filter(image, null);
        return image;
    }

    public static BufferedImage scaleImage(final BufferedImage original, final int width, final int height){
        final BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
        final Graphics2D graphics2D = scaledImage.createGraphics();

        graphics2D.drawImage(original, 0, 0, width, height, null);
        graphics2D.dispose();

        return scaledImage;
    }
}
