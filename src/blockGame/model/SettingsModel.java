package blockGame.model;

public class SettingsModel {
	private int musicVolume;	
	private int effectVolume;	
	private int resolutionX;
	private int resolutionY;
	private boolean fullscreen;
	
	// Konstruktor mit Standardwerten (Absicherung falls nichts geladen wird oder Fehler auftreten)
	public SettingsModel() {
		this.musicVolume = 50;
		this.effectVolume = 50;
		this.resolutionX = 1920;
		this.resolutionY = 1080;
		this.fullscreen = true;
	}
	
	// Konstruktor für Übergabe aus XML -> überschreibt Standardwerte
	public SettingsModel(int[] settings) {
		// erst auf Standard setzen
		this();
		
		if (settings != null && settings.length == 4) {
				this.musicVolume = settings[0];
				this.effectVolume = settings[1];
                this.resolutionX  = settings[2];
                this.resolutionY  = settings[3];
                // this.fullscreen   = settings[4];
			
				}
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

	