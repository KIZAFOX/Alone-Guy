package fr.kizafox.aloneguy.game.utils;

/**
 * Done by @KIZAFOX on {13/01/2024} at 13:52.
 **/
public class Constants {
    public static class PlayerState {
        public static final int IDLE = 0;
        public static final int RUNNING = 1;
        public static final int ATTACKING = 7;
        public static final int DYING = 9;

        public static int getSpriteAmount(final int playerState){
            switch (playerState){
                case IDLE, ATTACKING -> {
                    return 4;
                }
                case RUNNING -> {
                    return 6;
                }
                case DYING -> {
                    return 5;
                }
                default -> {
                    return 1;
                }
            }
        }
    }

    public static class EnemyState {
        public static final int IDLE = 0;
        public static final int RUNNING = 1;
        public static final int ATTACKING = 7;
        public static final int DYING = 9;

        public static int getSpriteAmount(final int playerState){
            switch (playerState){
                case IDLE, ATTACKING -> {
                    return 4;
                }
                case RUNNING -> {
                    return 6;
                }
                case DYING -> {
                    return 5;
                }
                default -> {
                    return 1;
                }
            }
        }
    }
}
