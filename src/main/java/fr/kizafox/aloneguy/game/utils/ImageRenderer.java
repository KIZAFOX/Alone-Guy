package fr.kizafox.aloneguy.game.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class ImageRenderer {

    public static final String BACKGROUND = "background.jpg";

    public static final String BUTTON_ATLAS = "button_atlas.png";

    public static final String PLAYER_UP_1 = "sprites/boy_up_1.png";
    public static final String PLAYER_UP_2 = "sprites/boy_up_2.png";

    public static final String PLAYER_DOWN_1 = "sprites/boy_down_1.png";
    public static final String PLAYER_DOWN_2 = "sprites/boy_down_2.png";

    public static final String PLAYER_LEFT_1 = "sprites/boy_left_1.png";
    public static final String PLAYER_LEFT_2 = "sprites/boy_left_2.png";

    public static final String PLAYER_RIGHT_1 = "sprites/boy_right_1.png";
    public static final String PLAYER_RIGHT_2 = "sprites/boy_right_2.png";

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

}
