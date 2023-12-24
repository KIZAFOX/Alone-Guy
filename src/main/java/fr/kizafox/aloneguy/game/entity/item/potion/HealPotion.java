package fr.kizafox.aloneguy.game.entity.item.potion;

import fr.kizafox.aloneguy.game.entity.Entity;

import java.awt.*;

public class HealPotion extends Potion {

    public HealPotion(Entity entity, float x, float y) {
        super(entity, x, y, Color.RED);
    }

    @Override
    public void applyEffect() {
        this.getEntity().setHealth(this.getEntity().getMaxHealth());
        System.out.println("+5");
    }
}
