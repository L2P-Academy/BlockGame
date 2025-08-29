// Kai P

package blockGame.controller;

import javax.sound.sampled.*;
import java.io.File; // ermöglicht gepuffertes Einlesen für bessere Performance

public class SoundController {

	private static SoundController instance; // Singleton-Instanz des SoundControllers
	private int volume = 85;
	private File file;
	public Clip btnClip, musicClip, sfxClip;

	public static SoundController getInstance() {
		return instance;
	}

	public static void setInstance(SoundController instance) {
		SoundController.instance = instance;
	}

	// Constructor
	public SoundController() {
		//TODO: Volume from Settings.xml
		setVolume(this.volume);
	}

	// Getter & Setter
	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
		adjustVolume(getMusicClip(), volume);
		adjustVolume(getSfxClip(), volume);
		adjustVolume(getButtonClip(), volume);
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public Clip getButtonClip() {
		return btnClip;
	}

	public void setButtonClip(Clip buttonClip) {
		this.btnClip = buttonClip;
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

	/**
	 * plays a Music Loop
	 * 
	 * @author Kain Plan
	 * @param filePath - Path to Res-File
	 * @return the Music Clip
	 */

	public Clip playMusicLoop(String filePath) {
		try {
			if (musicClip == null || !musicClip.isOpen()) { // || = oder, !vorEingabe = Gegenteiltag
				AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(filePath));
				musicClip = AudioSystem.getClip();
				musicClip.open(audioStream);
				adjustVolume(musicClip, volume);
				musicClip.loop(Clip.LOOP_CONTINUOUSLY);
			} else {
				stopMusicLoop();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return musicClip;
	}

	/**
	 * stops Music Loop
	 * 
	 * @author Kain Plan
	 */

	public void stopMusicLoop() {
		musicClip.stop();
		musicClip.close();
		musicClip = null;
	}

	public void adjustVolume(Clip clip, int volume) {
		if (clip != null && clip.isOpen() && clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
			FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			float minVolume = volumeControl.getMinimum(); // -80.0f
			float maxVolume = volumeControl.getMaximum(); // 6.0206f

			float scaledVolume = minVolume + (volume / 100.0f) * (maxVolume - minVolume);
			volumeControl.setValue(scaledVolume);
			System.out.println("Volume set to: " + scaledVolume);
		} else if (clip != null && !clip.isOpen()) {
			System.out.println("❌ Clip not ready or not supported!");
		}

	}

	public void playBtnSound() {
		if (btnClip != null && btnClip.isOpen()) {
			btnClip.stop();
			btnClip.flush();
		}
		try {
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File("src/res/sounds/click.wav"));
			btnClip = AudioSystem.getClip();
			btnClip.open(audioStream);
			btnClip.setFramePosition(0);
			btnClip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}