package fr.kizafox.aloneguy.game.client.status;

public enum GameState {

    MENU, PLAY, SETTINGS, QUIT, PAUSE, LOSE;

    private static GameState currentState;

    public static void setStatus(final GameState state){
        currentState = state;
    }

    public static GameState getCurrentState() {
        return currentState;
    }
}

