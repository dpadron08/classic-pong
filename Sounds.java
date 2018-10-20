package main;

import java.io.BufferedInputStream;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sounds  {

	/**
	 * Only works in Eclipse. Method <code>PlaySound</code>
	 * works in jar AND Eclipse
	 * @param path
	 */
	public synchronized void playSound(String path) {
        new Thread(new Runnable() {
            public void run() {
				try {
					Clip clip = AudioSystem.getClip();
					//AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
					AudioInputStream inputStream = AudioSystem.getAudioInputStream(Sounds.class.getResourceAsStream(path));
					
                    clip.open(inputStream);
                    clip.start();
                } catch (Exception e) {
                    e.printStackTrace();
                    System.err.println(e.getMessage());
                }
            }
        }).start();
        
    }
	
	/**
	 * Successfully loads and plays music in jar AND Eclipse . The ONE WORKING VERSION
	 * @param filename
	 */
	public void PlaySound(String filename) {
		try (InputStream in = getClass().getResourceAsStream(filename)) {
			InputStream bufferedIn = new BufferedInputStream(in);
			try (AudioInputStream audioIn = AudioSystem
					.getAudioInputStream(bufferedIn)) {
				Clip clip = AudioSystem.getClip();
				clip.open(audioIn);
				clip.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}