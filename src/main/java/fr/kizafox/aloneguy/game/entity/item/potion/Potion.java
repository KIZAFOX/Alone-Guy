package fr.kizafox.aloneguy.game.entity.item.potion;

import fr.kizafox.aloneguy.game.entity.Entity;
import fr.kizafox.aloneguy.game.entity.item.Item;

import java.awt.*;

public abstract class Potion extends Item {

    protected final Entity entity;

    public Potion(final Entity entity, float x, float y, Color color) {
        super(x, y, 16, 16, color);
        this.initHitBox(x, y, width, height);

        this.entity = entity;
    }

    @Override
    public void update() {
    }

    @Override
    public void render(Graphics graphics) {
        if(this.isAvailable()){
            graphics.setColor(new Color(184, 102, 20));
            graphics.fillRect((int) x + height - 12, (int) y - height + 10, width / 2, height / 2);

            graphics.setColor(color);
            graphics.fillOval((int) x, (int) y, width, height);
        }
    }

    public Entity getEntity() {
        return entity;
    }
}
