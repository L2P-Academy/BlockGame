// Kai P

package blockGame.controller;

import javax.sound.sampled.AudioInputStream;	// Audio-Datenstrom für .wav-Dateien
import javax.sound.sampled.AudioSystem;			// Zugriff auf Java Sound API
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import java.io.InputStream;						// Grundklasse für das Laden der Datei
import java.io.BufferedInputStream;				// puffert den Stream für schnelleren Zugriff
import java.util.HashMap;						// notwendig zum Stoppen von Musik oder wiederverwendbaren Sounds
import java.util.HashSet;
import java.util.Map;
import java.util.Set;							// merkt sich, welche Sounds in Schleife laufen, um sie gezielt zu stoppen
import java.util.concurrent.ExecutorService;	// ermöglicht paralleles Abspielen von Sounds, ohne Spiel zu blockieren
import java.util.concurrent.Executors;


public class SoundController {

    private static SoundController instance;

    private final Map<String, Clip> soundMap = new HashMap<>();
    private final Set<String> loopedSounds = new HashSet<>();
    private final ExecutorService executor = Executors.newCachedThreadPool();
    private boolean muted = false;

    // privater Konstruktor
    private SoundController() {}

    // öffentliche Zugriffsmethode
    public static SoundController getInstance() {
        if (instance == null) {
            instance = new SoundController();
        }
        return instance;
    }

    
    //spielt einen Sound ab, optional im Loop
    public void playLoopedSound(String name, String resourcePath) {			
        if (muted) return;

        executor.submit(() -> {
            try {
                Clip clip = soundMap.get(name);
                if (clip == null) {
                    InputStream audioSrc = getClass().getResourceAsStream(resourcePath);
                    if (audioSrc == null) {
                        System.err.println("Sounddatei nicht gefunden: " + resourcePath); 	// falls Datei nicht gefunden wird
                        return;
                    }

                    BufferedInputStream bufferedIn = new BufferedInputStream(audioSrc);
                    AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);
                    clip = AudioSystem.getClip();
                    clip.open(audioStream);

                    soundMap.put(name, clip);  // registrieren
                }

                if (clip.isRunning()) {
                    clip.stop();
                }

                clip.setFramePosition(0);
                clip.loop(Clip.LOOP_CONTINUOUSLY);
                loopedSounds.add(name);

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    
    // Musik stoppen    	
    public void stopSound(String name) {		
        Clip clip = soundMap.get(name);
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
        loopedSounds.remove(name);
    }

    // alle Sounds stoppen (z.B. beim Szenenwechsel)
    public void stopAll() {		
        for (Clip clip : soundMap.values()) {
            if (clip.isRunning()) {
                clip.stop();
            }
        }
        loopedSounds.clear();
    }

    // globales Stummschalten
    public void setMuted(boolean muted) {
        this.muted = muted;
        if (muted) {
            stopAll();
        }
    }
}
