package fr.kizafox.aloneguy.game.client.window.buttons;

import fr.kizafox.aloneguy.game.utils.image.ImageRenderer;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Done by @KIZAFOX on {14/01/2024} at 13:42.
 **/
public class UrmButton extends PauseButton{

    private BufferedImage[] images;
    private int rowIndex, index;

    private boolean mouseOver, mousePressed;

    public UrmButton(int x, int y, int width, int height, int rowIndex) {
        super(x, y, width, height);
        this.rowIndex = rowIndex;

        this.images = new BufferedImage[3];
        final BufferedImage temp = ImageRenderer.load(ImageRenderer.URM_BUTTONS);

        for(int i = 0; i < this.images.length; i++){
            this.images[i] = temp.getSubimage(i * URM_SIZE_DEFAULT, rowIndex * URM_SIZE_DEFAULT, URM_SIZE_DEFAULT, URM_SIZE_DEFAULT);
        }
    }

    public void update(){
        this.index = 0;

        if(this.mouseOver) this.index = 1;

        if(this.mousePressed) this.index = 2;
    }

    public void render(final Graphics graphics){
        graphics.drawImage(this.images[index], this.getX(), this.getY(), URM_SIZE, URM_SIZE, null);
    }

    public void resetBooleans(){
        this.mouseOver = false;
        this.mousePressed = false;
    }

    public BufferedImage[] getImages() {
        return images;
    }

    public void setImages(BufferedImage[] images) {
        this.images = images;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
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
}
