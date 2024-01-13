package fr.kizafox.aloneguy.game.utils;

import java.time.Duration;

/**
 * Done by @KIZAFOX on {13/01/2024} at 10:40.
 **/
public class Time {

    protected long startTime;

    public Time() {
        this.startTime = System.currentTimeMillis();
    }

    public String getFormattedTime() {
        final long seconds = Duration.ofMillis(System.currentTimeMillis() - startTime).getSeconds();
        return String.format("%02d:%02d:%02d", seconds / 3600, (seconds % 3600) / 60, seconds % 60);
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }
}
