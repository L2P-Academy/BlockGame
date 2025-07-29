// Kai P

package blockGame.controller;

import javax.sound.sampled.*; 					// Import für Audio-Handling (Clip, AudioSystem, AudioInputStream)
import java.io.InputStream; 					// Import für das Einlesen von Dateien als Stream
import java.io.BufferedInputStream;
import java.io.File; 			// ermöglicht gepuffertes Einlesen für bessere Performance

public class SoundController {

    private static SoundController instance; 	// Singleton-Instanz des SoundControllers
    private int volume = 50;
    private File file;
    public Clip buttonClip, musicClip, sfxClip;

    // Getter - Setter
    public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
		adjustVolume(getMusicClip(), volume);
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public Clip getButtonClip() {
		return buttonClip;
	}

	public void setButtonClip(Clip buttonClip) {
		this.buttonClip = buttonClip;
	}

	public Clip getMusicClip() {
		return musicClip;
	}

	public void setMusicClip(Clip musicClip) {
		this.musicClip = musicClip;
	}

	public Clip getSfxClip() {
		return sfxClip;
	}

	public void setSfxClip(Clip sfxClip) {
		this.sfxClip = sfxClip;
	}
    
    public SoundController() {
    		setVolume(this.volume);
    }   

	/**
	 * plays a Music Loop
	 * @author Kain Plan
	 * @param filePath - Path to Res-File
	 * @return the Music Clip
	 */
    
    public Clip playMusicLoop(String filePath) {
        try {
			if (musicClip == null || !musicClip.isOpen()) {		// || = oder, !vorEingabe = Gegenteiltag
				AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(filePath));
				musicClip = AudioSystem.getClip();
				musicClip.open(audioStream);	
				adjustVolume(musicClip, volume);
				musicClip.loop(Clip.LOOP_CONTINUOUSLY);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
        return musicClip;
    }
    
    public void stopMusicLoop() {
    		musicClip.stop();
    		musicClip.close();
    }
    
    public void adjustVolume(Clip clip, int volume) {
    		if (clip != null && clip.isOpen() && clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
			FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			float minVolume = volumeControl.getMinimum();		// -80.0f
			float maxVolume = volumeControl.getMaximum();		// 6.0206f
			
			float scaledVolume = minVolume + (volume / 100.0f) * (maxVolume - minVolume);
			volumeControl.setValue(scaledVolume);
			System.out.println("Volume set to: " + scaledVolume);
    		} else {
			System.out.println("❌ Clip not ready or not supported!");
			}
    		
    }
}