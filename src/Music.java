import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

public class Music {
    GamePanel gp;
    private Clip clip;
    Music(GamePanel gp){
        this.gp = gp;
    }
    protected void playMusic(String filepath) {
        // "res/music/Wake Up, Get Up, Get Out There.wav"
        // "res/music/Tokyo Emergency.wav"
        try {
            File musicPath = new File(filepath);
            if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                clip = AudioSystem.getClip();
                clip.open(audioInput);
                setVolume(0.1F);
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                System.out.println("No file. ◉_◉");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setVolume(float volume) {
        if (volume < 0f || volume > 1f)
            throw new IllegalArgumentException("Volume not valid: " + volume);
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(20f * (float) Math.log10(volume));
    }
}
