// Kai P

package blockGame.controller;

import javax.sound.sampled.Clip;	
import java.util.HashMap;			//notwendig zum Stoppen von Musik oder wiederverwendbaren Sounds
import java.util.Map;
import java.util.Set;		//Merkt sich, welche Sounds in Schleife laufen, um sie gezielt zu stoppen
import java.util.HashSet;
import java.util.concurrent.ExecutorService;	// Ermöglicht paralleles Abspielen von Sounds, ohne den Spiel-Thread zu blockieren
import java.util.concurrent.Executors;

public class SoundController {
    private Map<String, Clip> soundMap = new HashMap<>();	// Zentrale Sound-Registrierung
    private ExecutorService executor = Executors.newCachedThreadPool();	// Für paralleles Abspielen ohne Spielblockade
    private boolean muted = false;	// Globales Muten
    private float masterVolume = 0.0f;	 // Lautstärke in dB (z. B. 0.0 = normal, -10.0 = leise)
    private Set<String> loopedSounds = new HashSet<>();		// Um loopende Sounds (z. B. Musik) gezielt stoppen zu können
}