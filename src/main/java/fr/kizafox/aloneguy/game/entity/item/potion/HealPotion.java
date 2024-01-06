package fr.kizafox.aloneguy.game.entity.item.potion;

import fr.kizafox.aloneguy.game.client.window.Game;
import fr.kizafox.aloneguy.game.entity.Entity;
import fr.kizafox.aloneguy.game.utils.ImageRenderer;

public class HealPotion extends Potion {

    public HealPotion(Game game, Entity entity) {
        super(game, entity, ImageRenderer.load(ImageRenderer.HEAL_POTION));
    }

    @Override
    public void applyEffect() {
        this.getEntity().setHealth(this.getEntity().getMaxHealth());
        System.out.println("PLAYER IS NOW FULL HEALTH");
    }
}
