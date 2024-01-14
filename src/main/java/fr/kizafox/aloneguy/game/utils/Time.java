package fr.kizafox.aloneguy.game.utils;

import java.time.Duration;

/**
 * Done by @KIZAFOX on {13/01/2024} at 10:40.
 **/
public class Time {

    protected long startTime;
    protected long pauseTime;

    protected boolean isPaused;

    public Time() {
        this.startTime = System.currentTimeMillis();
        this.pauseTime = 0;
        this.isPaused = false;
    }

    public void restart() {
        this.startTime = System.currentTimeMillis();
        this.pauseTime = 0;
        this.isPaused = false;
    }

    public void pause(){
        if(!this.isPaused){
            this.isPaused = true;
            this.pauseTime = Duration.ofMillis(System.currentTimeMillis() - this.startTime).getSeconds();
        }
    }

    public void resume(){
        if(this.isPaused){
            this.isPaused = false;
            this.startTime = System.currentTimeMillis() - (this.pauseTime * 1000);
            this.pauseTime = 0;
        }
    }

    public String getFormattedTime() {
        long currentTime;

        if(this.isPaused) {
            currentTime = this.pauseTime;
        }else{
            currentTime = Duration.ofMillis(System.currentTimeMillis() - this.startTime).getSeconds();
        }

        return String.format("%02d:%02d:%02d", currentTime / 3600, (currentTime % 3600) / 60, currentTime % 60);
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getPauseTime() {
        return pauseTime;
    }

    public void setPauseTime(long pauseTime) {
        this.pauseTime = pauseTime;
    }

    public boolean isPaused() {
        return isPaused;
    }

    public void setPaused(boolean paused) {
        isPaused = paused;
    }
}
