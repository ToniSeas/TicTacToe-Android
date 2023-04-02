package game;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class GameSound {

    private Clip clip;
    private Long microSecondPosition;

    // To load the sound we used this guide: http://dar10comyr.blogspot.com/2015/06/tips-como-reproducir-sonidos-en-java.html
    public GameSound (String soundPath) {
        this.microSecondPosition = 0L;
        try {
            this.clip = AudioSystem.getClip();
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundPath).getAbsoluteFile());
            this.clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        this.clip.setMicrosecondPosition(this.microSecondPosition);
        this.clip.start();
    }

    public void pause() {
        this.microSecondPosition = this.clip.getMicrosecondPosition();
        this.clip.stop();
    }

    public void stop() {
        this.microSecondPosition = 0L;
        this.clip.setMicrosecondPosition(this.microSecondPosition);
        this.clip.stop();
    }

    public void setVolume (float value) {
        FloatControl volumen = (FloatControl)this.clip.getControl(FloatControl.Type.MASTER_GAIN);
        volumen.setValue(value);
    }
    
}
