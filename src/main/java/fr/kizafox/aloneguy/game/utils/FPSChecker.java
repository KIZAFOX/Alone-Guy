package fr.kizafox.aloneguy.game.utils;

import fr.kizafox.aloneguy.game.client.window.Game;

public class FPSChecker implements Runnable{

    protected final Game game;

    private final int MAX_FPS = 144;
    private final int UPS_SET = 200;

    public FPSChecker(final Game game) {
        this.game = game;
    }

    /**
     * Runs this operation.
     */
    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / MAX_FPS, timePerUpdate = 1000000000.0 / UPS_SET;

        long previousTime = System.nanoTime(), lastCheck = System.currentTimeMillis();

        int frames = 0; int updates = 0;

        double deltaU = 0, deltaF = 0;

        while(true) {
            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaU >= 1) {
                this.game.update();
                updates++;
                deltaU--;
            }

            if (deltaF >= 1) {
                this.game.getGamePanel().repaint();
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                Game.log("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }

    public Game getGame() {
        return game;
    }

    public int getFPS() {
        return MAX_FPS;
    }

    public int getUPS() {
        return UPS_SET;
    }
}