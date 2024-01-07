package fr.kizafox.aloneguy.game.utils.sound;

import fr.kizafox.aloneguy.game.client.window.Game;
import fr.kizafox.aloneguy.game.utils.Colors;

import javax.sound.sampled.*;
import java.util.LinkedList;

public final class VolumeControl {

    private static final LinkedList<Line> speakers = new LinkedList<>();

    private static void findSpeakers() {
        Mixer.Info[] mixers = AudioSystem.getMixerInfo();

        for(Mixer.Info mixerInfo : mixers) {
            if(!mixerInfo.getName().equals("Java Sound Audio Engine")) continue;

            Mixer mixer = AudioSystem.getMixer(mixerInfo);
            Line.Info[] lines = mixer.getSourceLineInfo();

            for(Line.Info info : lines) {
                try {
                    Line line = mixer.getLine(info);
                    speakers.add(line);
                } catch (LineUnavailableException | IllegalArgumentException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    static {
        findSpeakers();
    }

    public static void setVolume(float level) {
        Game.log(Colors.YELLOW + "Setting volume to " + level);
        for(Line line : speakers) {
            try {
                line.open();
                FloatControl control = (FloatControl)line.getControl(FloatControl.Type.MASTER_GAIN);
                control.setValue(limit(control,level));
            } catch (LineUnavailableException | IllegalArgumentException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static float limit(FloatControl control,float level) {
        return Math.min(control.getMaximum(), Math.max(control.getMinimum(), level));
    }
}