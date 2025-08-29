package blockGame.model;

public class SettingsModel {
	private int musicVolume;	
	private int effectVolume;	
	private int resolutionX;
	private int resolutionY;
	private boolean fullscreen;
	
	
	
	public SettingsModel(int musicVolume, int effectVolume, int resolutionX, int resolutionY, boolean fullscreen) {
		this.musicVolume = musicVolume;
		this.effectVolume = effectVolume;
		this.resolutionX = resolutionX;
		this.resolutionY = resolutionY;
		this.fullscreen = fullscreen;
	}

	public int getMusicVolume() {
		return musicVolume;
	}

	public void setMusicVolume(int musicVolume) {
		this.musicVolume = musicVolume;
	}

	public int getEffectVolume() {
		return effectVolume;
	}

	public void setEffectVolume(int effectVolume) {
		this.effectVolume = effectVolume;
	}

	public int getResolutionX() {
		return resolutionX;
	}

	public void setResolutionX(int resolutionX) {
		this.resolutionX = resolutionX;
	}

	public int getResolutionY() {
		return resolutionY;
	}

	public void setResolutionY(int resolutionY) {
		this.resolutionY = resolutionY;
	}

	public boolean isFullscreen() {
		return fullscreen;
	}

	public void setFullscreen(boolean fullscreen) {
		this.fullscreen = fullscreen;
	}

	
}

	