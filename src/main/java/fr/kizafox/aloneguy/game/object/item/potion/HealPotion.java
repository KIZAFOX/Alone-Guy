package fr.kizafox.aloneguy.game.object.item.potion;

import fr.kizafox.aloneguy.game.client.window.Game;
import fr.kizafox.aloneguy.game.entity.Entity;
import fr.kizafox.aloneguy.game.utils.ImageRenderer;
import fr.kizafox.aloneguy.game.utils.sound.SoundPlayer;

public class HealPotion extends Potion {
    public HealPotion(Game game) {
        super(game, "HealPotion", 10, 8, ImageRenderer.load(ImageRenderer.HEAL_POTION));
    }

    @Override
    public void applyEffect(Entity entity) {
        entity.setHealth(entity.getMaxHealth());
        SoundPlayer.playSound(SoundPlayer.SLURP_SOUND, SoundPlayer.Volume.MEDIUM);
    }
}
