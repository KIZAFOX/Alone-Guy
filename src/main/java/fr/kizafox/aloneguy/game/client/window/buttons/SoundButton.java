package fr.kizafox.aloneguy.game.client.window.buttons;

import fr.kizafox.aloneguy.game.utils.image.ImageRenderer;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Done by @KIZAFOX on {14/01/2024} at 13:16.
 **/
public class SoundButton extends PauseButton{

    private final BufferedImage[][] images;

    private int rowIndex, columnIndex;
    private boolean mouseOver, mousePressed, muted;

    public SoundButton(int x, int y, int width, int height) {
        super(x, y, width, height);

        this.images = new BufferedImage[2][3];
        final BufferedImage temp = ImageRenderer.load(ImageRenderer.SOUND_BUTTON);

        for(int i = 0; i < this.images.length; i++){
            for(int j = 0; j < this.images[i].length; j++){
                this.images[i][j] = temp.getSubimage(j * SOUND_DEFAULT_SIZE, i * SOUND_DEFAULT_SIZE, SOUND_DEFAULT_SIZE, SOUND_DEFAULT_SIZE);
            }
        }
    }

    public void update(){
        if(this.muted){
            this.rowIndex = 1;
        }else{
            this.rowIndex = 0;
        }

        this.columnIndex = 0;

        if(this.mouseOver){
            this.columnIndex = 1;
        }

        if(this.mousePressed){
            this.columnIndex = 2;
        }
    }

    public void render(final Graphics graphics){
        graphics.drawImage(this.images[this.rowIndex][this.columnIndex], this.getX(), this.getY(), this.getWidth(), this.getHeight(), null);
    }

    public void resetBooleans(){
        this.mouseOver = false;
        this.mousePressed = false;
    }

    public BufferedImage[][] getImages() {
        return images;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public void setColumnIndex(int columnIndex) {
        this.columnIndex = columnIndex;
    }

    public boolean isMouseOver() {
        return mouseOver;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public boolean isMuted() {
        return muted;
    }

    public void setMuted(boolean muted) {
        this.muted = muted;
    }
}
