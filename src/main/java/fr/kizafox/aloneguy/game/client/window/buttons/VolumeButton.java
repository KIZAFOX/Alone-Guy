package fr.kizafox.aloneguy.game.client.window.buttons;

import fr.kizafox.aloneguy.game.utils.image.ImageRenderer;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Done by @KIZAFOX on {14/01/2024} at 13:42.
 **/
public class VolumeButton extends PauseButton{

    private BufferedImage[] images;
    private BufferedImage sliders;
    private int rowIndex, index, buttonX, minX, maxX;
    private boolean mouseOver, mousePressed;

    public VolumeButton(int x, int y, int width, int height) {
        super(x + width / 2, y, VOLUME_WIDTH, height);
        this.bounds.x -= VOLUME_WIDTH / 2;
        this.buttonX = x + width / 2;
        this.x = x;
        this.width = width;
        this.minX = x + VOLUME_WIDTH / 2;
        this.maxX = x + width - VOLUME_WIDTH / 2;

        this.images = new BufferedImage[3];
        final BufferedImage temp = ImageRenderer.load(ImageRenderer.VOLUME_BUTTONS);

        for(int i = 0; i < this.images.length; i++){
            this.images[i] = temp.getSubimage(i * VOLUME_DEFAULT_WIDTH, 0, VOLUME_DEFAULT_WIDTH, VOLUME_DEFAULT_HEIGHT);
        }
        this.sliders = temp.getSubimage(3 * VOLUME_DEFAULT_WIDTH, 0, SLIDER_DEFAULT_WIDTH, VOLUME_DEFAULT_HEIGHT);
    }

    public void update(){
        this.index = 0;

        if(this.mouseOver){
            this.index = 1;
        }

        if(this.mousePressed){
            this.index = 2;
        }
    }

    public void render(final Graphics graphics){
        graphics.drawImage(this.sliders, this.x, this.y, this.width, this.height, null);
        graphics.drawImage(this.images[this.index], this.buttonX - VOLUME_WIDTH / 2, this.y, VOLUME_WIDTH, this.height, null);
    }

    public void changeX(final int x){
        if(x < this.minX){
            this.buttonX = minX;
        }else this.buttonX = Math.min(x, maxX);

        this.bounds.x = this.buttonX - VOLUME_WIDTH / 2;
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

    public BufferedImage getSliders() {
        return sliders;
    }

    public void setSliders(BufferedImage sliders) {
        this.sliders = sliders;
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

    public int getButtonX() {
        return buttonX;
    }

    public void setButtonX(int buttonX) {
        this.buttonX = buttonX;
    }

    public int getMinX() {
        return minX;
    }

    public void setMinX(int minX) {
        this.minX = minX;
    }

    public int getMaxX() {
        return maxX;
    }

    public void setMaxX(int maxX) {
        this.maxX = maxX;
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
