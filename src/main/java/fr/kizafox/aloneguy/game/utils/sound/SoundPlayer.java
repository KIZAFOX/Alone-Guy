package fr.kizafox.aloneguy.game.utils.sound;

import fr.kizafox.aloneguy.game.client.window.Game;
import fr.kizafox.aloneguy.game.utils.Colors;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class SoundPlayer {

    public static final String BUTTON_PRESS_SOUND = "sounds/click_sound.wav";
    public static final String EXPLOSION_SOUND = "sounds/explosion_sound.wav";
    public static final String SLURP_SOUND = "sounds/slurp_sound.wav";

    public static final String TEST = "sounds/test.wav";

    public static void playSound(final String fileName, final Volume volume) {
        final URL file = SoundPlayer.class.getResource("/" + fileName);

        if (file == null) {
            throw new IllegalStateException("File " + fileName + " not found or doesn't exist in 'resources' folder!");
        }

        try {
            final AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            final Clip clip = AudioSystem.getClip();

            clip.open(audioInputStream);

            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(volume.getVolume());

            clip.start();

            final String[] split = fileName.split("/");
            final String name = split[split.length - 1];

            Game.log(Colors.YELLOW + "Playing sound: " + name);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    public enum Volume{
        MUTE(-80F), LOW(-30F), MEDIUM(0F), HIGH(6.0206F);

        private final float volume;

        Volume(float volume) {
            this.volume = volume;
        }

        public float getVolume() {
            return volume;
        }
    }
}
