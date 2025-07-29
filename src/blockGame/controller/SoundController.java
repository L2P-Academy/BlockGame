// Kai P

package blockGame.controller;

import javax.sound.sampled.*; 					// Import für Audio-Handling (Clip, AudioSystem, AudioInputStream)
import java.io.InputStream; 					// Import für das Einlesen von Dateien als Stream
import java.io.BufferedInputStream; 			// ermöglicht gepuffertes Einlesen für bessere Performance

public class SoundController {

    private static SoundController instance; 	// Singleton-Instanz des SoundControllers

    // statische Methode zum Zugriff auf die Singleton-Instanz
    public static SoundController getInstance() {
        if (instance == null) { 				// Wenn keine Instanz existiert ...
            instance = new SoundController(); 	// ... wird eine neue erstellt
        }
        return instance; 						// gibt die vorhandene oder neu erstellte Instanz zurück
    }

    // privater Konstruktor verhindert direkte Objekterstellung außerhalb der Klasse
    private SoundController() {}

    // spielt eine Sounddatei im Loop ab
    public void playLoop(String resourcePath) {
        try {
            // Versucht, die Sounddatei als InputStream aus dem Ressourcenpfad zu laden
            InputStream stream = getClass().getResourceAsStream(resourcePath);
            if (stream == null) { 													// wenn die Datei nicht gefunden wurde ...
                System.err.println("❌ Datei nicht gefunden: " + resourcePath); 		// Fehlermeldung
                return; 															// abbrechen
            }

            // wandelt den InputStream in einen gepufferten Stream um (sicherer und schneller)
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(new BufferedInputStream(stream));

            // erstellt ein Clip-Objekt, das Audio abspielen kann
            Clip clip = AudioSystem.getClip();

            // lädt die Audiodaten in das Clip-Objekt
            clip.open(audioIn);

            // setzt das Abspielen auf Endlosschleife (LOOP_CONTINUOUSLY)
            clip.loop(Clip.LOOP_CONTINUOUSLY);

            // startet das Abspielen
            clip.start();

        } catch (Exception e) { 			// fängt alle Fehler beim Laden oder Abspielen ab
            e.printStackTrace(); 			// gibt die Fehlerdetails in der Konsole aus
        }
    }
}
