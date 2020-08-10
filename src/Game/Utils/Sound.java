package Game.Utils;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * a class for playing sound and manage them.
 * @author Ahmad Foroughi
 * @version 1.0
 */
public class Sound
{

    private File audio;
    private Clip clip;
    private boolean isStoped = false;

    /**
     * Create a new Sound
     */
    public Sound(File audio)
    {
        this.audio = audio;

        try
        {
            clip = AudioSystem.getClip();
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audio);
            clip.open(audioInputStream);
        }
        catch (Exception e)
        {
            System.err.println("There is Something Wrong with music");
        }

    }

    /**
     * a method for stop the sound.
     */
    public synchronized void stopSound()
    {
        clip.stop();

    }

    /**
     * a method for start playing the sound.
     */
    public synchronized void playSound()
    {
        new Thread(new Runnable()
        {

            public void run()
            {

                try
                {
                    clip.start();
                }
                catch (Exception e)
                {
                    System.err.println(e.getMessage());
                }

            }
        }).start();
    }

    /**
     * @return that is the clip active or not
     */
    public boolean isActive()
    {
        return clip.isActive();
    }
}
