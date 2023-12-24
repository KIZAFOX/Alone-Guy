package fr.kizafox.aloneguy.game.client.window.button;

import fr.kizafox.aloneguy.game.client.status.GameState;
import fr.kizafox.aloneguy.game.utils.ImageRenderer;

import java.awt.*;
import java.awt.image.BufferedImage;

import static fr.kizafox.aloneguy.game.utils.GameSettings.*;

public class MenuButton {

    private final int xPosition, yPosition, rowIndex;
    private final GameState gameState;

    private BufferedImage[] images;
    private int index;
    private boolean mouseOver, mousePressed;

    private Rectangle bounds;

    private static final int
            B_WIDTH_DEFAULT = 140,
            B_HEIGHT_DEFAULT = 56,

            B_WIDTH = (int) (B_WIDTH_DEFAULT * SCALE),
            B_HEIGHT = (int) (B_HEIGHT_DEFAULT * SCALE);

    public MenuButton(int xPosition, int yPosition, int rowIndex, GameState gameState) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.rowIndex = rowIndex;
        this.gameState = gameState;

        this.images = new BufferedImage[3];
        final BufferedImage temp = ImageRenderer.load(ImageRenderer.BUTTON_ATLAS);

        for(int i = 0; i < this.images.length; i++){
            this.images[i] = temp.getSubimage(i * B_WIDTH_DEFAULT, rowIndex * B_HEIGHT_DEFAULT, B_WIDTH_DEFAULT, B_HEIGHT_DEFAULT);
        }

        this.bounds = new Rectangle((this.xPosition - (B_WIDTH / 2)), yPosition, B_WIDTH, B_HEIGHT);
    }

    public void update(){
        this.index = 0;
        if(this.mouseOver){
            this.index = 1;
        }else if(this.mousePressed){
            this.index = 2;
        }
    }

    public void render(final Graphics graphics){
        graphics.drawImage(this.images[this.index], (this.xPosition - (B_WIDTH / 2)), this.yPosition, B_WIDTH, B_HEIGHT, null);
    }

    public void applyGameStatus(){
        GameState.setStatus(gameState);
    }

    public void resetBooleans(){
        this.mouseOver = false;
        this.mousePressed = false;
    }

    public int getXPosition() {
        return xPosition;
    }

    public int getYPosition() {
        return yPosition;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public GameState getGameState() {
        return gameState;
    }

    public BufferedImage[] getImages() {
        return images;
    }

    public void setImages(BufferedImage[] images) {
        this.images = images;
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

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }
}
