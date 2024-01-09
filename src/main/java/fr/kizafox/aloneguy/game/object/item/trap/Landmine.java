package fr.kizafox.aloneguy.game.object.item.trap;

import fr.kizafox.aloneguy.game.client.window.Game;
import fr.kizafox.aloneguy.game.entity.Entity;
import fr.kizafox.aloneguy.game.utils.ImageRenderer;
import fr.kizafox.aloneguy.game.utils.sound.SoundPlayer;

public class Landmine extends Trap {
    public Landmine(Game game) {
        super(game, "Landmine", 17, 8, ImageRenderer.load(ImageRenderer.LANDMINE));
    }

    @Override
    public void applyEffect(Entity player) {
        player.setHealth(player.getHealth() - 5);
        SoundPlayer.playSound(SoundPlayer.EXPLOSION_SOUND, SoundPlayer.Volume.MEDIUM);
    }
}
