package blockGame; 

import blockGame.view.StartMenuView; 				// Import der Startmenü-Klasse (GUI)
import blockGame.controller.SoundController; 		// Import des SoundControllers

public class Launcher { 							

    public static void main(String[] args) {		       		  	
        SoundController.getInstance().playLoop("/blockGame/src/res/sounds/circuit_lofi_key.wav");	// Startet beim Spielstart die Hintergrundmusik im Loop        
        new StartMenuView().setVisible(true);		// Öffnet das Startmenü-Fenster der Anwendung
    }
}
