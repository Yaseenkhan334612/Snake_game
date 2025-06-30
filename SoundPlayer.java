import javax.sound.sampled.*;
import java.io.File;

public class SoundPlayer {
    private Clip clip;

    public void playEatSound() {
        try {
            if (clip == null || !clip.isRunning()) {
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File("src/main/resources/eating_sound.wav"));
                clip = AudioSystem.getClip();
                clip.open(audioIn);
                clip.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopSound() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
        }
    }

    public void playGameStartSound() {
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File("src/main/resources/mgs__mp.wav"));
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
