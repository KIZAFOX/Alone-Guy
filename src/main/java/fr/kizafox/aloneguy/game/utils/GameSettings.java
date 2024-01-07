package fr.kizafox.aloneguy.game.utils;

/**
 * Created at 19/12/2023 at 06:38
 * Made by @KIZAFOX (twitter)
 **/

public class GameSettings {
    public static final String
            NAME = "AloneGuy",
            VERSION = "PreAlpha-1.0.0",
            DESCRIPTION = "AloneGuy is a 2D game made in Java. (RogueLike)",
            URL = "https://github.com/KIZAFOX/AloneGuy";

    public static final String[] AUTHORS = {"KIZAFOX"};

    public static final float SCALE = 1.5F;

    public static final int
            TILES_DEFAULT_SIZE = 32,
            TILES_IN_WIDTH = 26,
            TILES_IN_HEIGHT = 15,
            TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE),
            GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH,
            GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;
}
